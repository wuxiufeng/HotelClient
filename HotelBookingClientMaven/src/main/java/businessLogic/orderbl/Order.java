package businessLogic.orderbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import businessLogic.userbl.UserController;
import po.OrderPO;
import rmi.RemoteHelper;
import vo.OrderVO;
import dataService.orderDataService.OrderDataService;
/**
 * 
 * @author 谢铠联
 * @author 武秀峰
 *
 */
public class Order{
	
	private OrderDataService orderDataService;
	
	/**
	 * 构造方法
	 */
	public Order(){
		orderDataService=RemoteHelper.getInstance().getOrderDataService();
	}
	
	/**
	 * 处理异常订单，将订单状态变为已执行并且增加用户被扣除的信用值
	 * @param order
	 * @param percentOfCredit 为网站营销人员选择的恢复信用值的一半还是全部，1代表一半，2代表全部
	 * @return boolean
	 * @throws RemoteException 
	 */
	public boolean handleAbnormalOrder(OrderVO order, int percentOfCredit) throws RemoteException {
		//改变订单状态
		order.setOrderstate("alreadyExecute");//此步将异常订单变为已执行
		OrderPO orderpo=new OrderPO(order);
		orderDataService.modify(orderpo);
		//恢复信用值
		UserController user=new UserController();
		int credit=percentOfCredit* order.getOrderprice() /2;
		return user.changeCredit(order.getPersonname(), credit);
	}

	/**
	 * 撤销订单，将订单状态变为已撤销，根据撤销时间减少客户信用值
	 * @param order 订单信息
	 * @return boolean 是否撤销成功
	 * @throws RemoteException 
	 */
	public boolean reverseOrder(OrderVO order) throws RemoteException {
		if(order.getOrderstate().equals("nonExecute")){//当订单是未执行订单时，进行操作；否则报错
			//撤销订单
			order.setCanceltime(Calendar.getInstance());
			order.setOrderstate("cancel");
			OrderPO orderPO=new OrderPO(order);
			boolean isReverse=orderDataService.modify(orderPO);
			//减少客户信用值
			boolean isCreditChange=true;
			if(Calendar.getInstance().compareTo(order.getLatestExecutetime())<6*60*60*1000){//如果撤销的订单距离最晚订单执行时间不足6个小时，扣除信用值
				UserController user=new UserController();
				isCreditChange=user.changeCredit(order.getPersonname(), -(order.getOrderprice()/2));
			}

			return isReverse&&isCreditChange;
		}else{
			return false;
		}
	}

	/**
	 * 执行订单，将订单状态变为已执行并且增加客户信用值
	 * @param order
	 * @return boolean 是否执行成功
	 * @throws RemoteException 
	 */
	public boolean finishOrder(OrderVO order) throws RemoteException {
		//将订单状态变为已执行
		order.setExecutetime(Calendar.getInstance());
		order.setOrderstate("alreadyExecute");
		OrderPO orderPO=new OrderPO(order);
		orderDataService.modify(orderPO);
		//增加客户信用值
		UserController user=new UserController();
		return user.changeCredit(order.getPersonname(), order.getOrderprice());
	}

	/**
	 * 生成订单，持久化保存订单信息
	 * @param order
	 * @return boolean 是否成功生成订单
	 * @throws RemoteException 
	 */
	public boolean createOrder(OrderVO order) throws RemoteException {
		UserController user=new UserController();
		if(user.getPersonInfo(order.getPersonname()).getCredit()<0){//如果客户的信用值小于0，用户不能生成订单
			return false;
		}
		OrderPO orderPO=new OrderPO(order);
		return orderDataService.add(orderPO);
	}

	/**
	 * 查看个人订单，根据用户名返回个人订单列表
	 * @param personname
	 * @return ArrayList<OrderVO> 订单列表
	 * @throws RemoteException 
	 */
	public ArrayList<OrderVO> personOrders(String personname) throws RemoteException {
		ArrayList<OrderPO> personList=orderDataService.personFind(personname);
		ArrayList<OrderVO> personListVO=new ArrayList<OrderVO>();
		for(int i=0; i<personList.size(); i++){
			OrderVO ordervo=new OrderVO(personList.get(i));
			personListVO.add(ordervo);
		}
		return personListVO;
	}

	/**
	 * 酒店工作人员浏览酒店订单，根据酒店名称返回酒店订单列表
	 * @param hotelname
	 * @return ArrayList<OrderVO> 订单列表
	 * @throws RemoteException 
	 */
	public ArrayList<OrderVO> hotelOrders(String hotelname) throws RemoteException {
		ArrayList<OrderPO> hotelList=orderDataService.hotelFind(hotelname);
		ArrayList<OrderVO> hotelListVO=new ArrayList<OrderVO>();
		for (OrderPO hotelPO : hotelList) {
			OrderVO hotelVO = new OrderVO(hotelPO);
			hotelListVO.add(hotelVO);
		}
		return hotelListVO;
	}

	/**
	 * 网站营销人员浏览网站订单，返回网站未执行和异常订单列表
	 * @param  无参数
	 * @return ArrayList<OrderVO> 网站订单列表
	 * @throws RemoteException 
	 */
	public ArrayList<OrderVO> netOrders() throws RemoteException {
		ArrayList<OrderPO> netList=orderDataService.exceptionFind();
		ArrayList<OrderVO> netListVO=new ArrayList<OrderVO>();
		for (OrderPO netPO : netList) {
			OrderVO netVO = new OrderVO(netPO);
			netListVO.add(netVO);
		}
		return netListVO;
	}

	/**
	 * 在个人订单查看过程中，进一步查看某个状态（未执行，已执行，已撤销，异常）的订单
	 * @param personname
	 * @param 订单状态
	 * 	 附：订单状态,"nonExecute"代表未执行订单、"alreadyExecute"代表已执行订单、
			"cancel"代表已撤销订单、"abnormal"代表异常订单、"delay"代表延期订单
	 * @return 符合条件的酒店列表
	 * @throws RemoteException 
	 */
	public ArrayList<OrderVO> personStateOrders(String personname, String state)throws RemoteException{
		Order order=new Order();
		ArrayList<OrderVO> personStateList=new ArrayList<OrderVO>();
		try {
			personStateList = order.personOrders(personname);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0; i<personStateList.size(); i++){
			if(!(personStateList.get(i).getOrderstate().equals(state))){
				personStateList.remove(i);
			}
		}
		return personStateList;
	}
	
	/**
	 * 在酒店订单查看过程中，进一步查看某状态订单
	 * @param hotelname
	 * @param state 订单状态
	 * 	 附：订单状态,"nonExecute"代表未执行订单、"alreadyExecute"代表已执行订单、
			"cancel"代表已撤销订单、"abnormal"代表异常订单、"delay"代表延期订单
	 * @return ArrayList<OrderVO> 订单列表
	 * @throws RemoteException 
	 */
	public ArrayList<OrderVO> hotelStateOrders(String hotelname, String state)throws RemoteException{
		ArrayList<OrderVO> hotelStateList=new ArrayList<OrderVO>();
		try {
			hotelStateList.addAll(hotelOrders(hotelname));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0; i<hotelStateList.size(); i++){
			if(!(hotelStateList.get(i).equals(state))){
				hotelStateList.remove(i);
			}
		}
		return hotelStateList;
	}
	
	/**
	 * 在浏览网站订单的过程中，进一步查看某日订单
	 * @param date 需要精确到年月日
	 * @return ArrayList<OrderVO> 订单列表
	 * @throws RemoteException 
	 */
	public ArrayList<OrderVO> netNumOrders(Calendar date)throws RemoteException{
		ArrayList<OrderVO> netNumList=new ArrayList<OrderVO>();
		try {
			netNumList.addAll(netOrders());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0; i<netNumList.size(); i++){
			OrderVO ordervo=netNumList.get(i);
			if((ordervo.getProducttime().getTime().getYear()!=date.getTime().getYear())
					||(ordervo.getProducttime().getTime().getMonth()!=date.getTime().getMonth())
					||(ordervo.getProducttime().getTime().getDate()!=date.getTime().getDate())){
				netNumList.remove(i);
			}
		}
		return netNumList;
	}
}


