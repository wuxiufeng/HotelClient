package businessLogic.hotelbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataService.hotelDataService.HotelDataService;
import po.hotelPO.CommentPO;
import po.hotelPO.HotelPO;
import po.hotelPO.RoomPO;
import rmi.RemoteHelper;
import vo.OrderVO;
import vo.hotelVO.hotelblVO.CommentVO;
import vo.hotelVO.hotelblVO.HotelConditionVO;
import vo.hotelVO.hotelblVO.HotelVO;
import vo.hotelVO.hotelblVO.RoomVO;
import vo.hotelVO.hoteluiVO.HotelSearchVO;
import businessLogic.orderbl.Order;

/**
 * 
 * @author 武秀峰
 *
 */
public class Hotel {
	private HotelDataService hoteldataservice;
	Order order=new Order();
	
	/**
	 * 根据酒店名称，获取酒店信息
	 * @param Hotelname
	 * @return HotelVO
	 * @throws RemoteException 
	 */
	public HotelVO showHotelInfo(String Hotelname) throws RemoteException{
		HotelPO hotelpo=hoteldataservice.showHotelinfo(Hotelname);
		HotelVO hotelvo=new HotelVO(hotelpo);
		return hotelvo;
	}
	
	/**
	 * 修改酒店信息
	 * @param hotelinfo
	 * @return 是否修改成功（成功则返回true,不能则返回false）
	 * @throws RemoteException 
	 */
	public boolean modifyHotelInfo(HotelVO hotelinfo) throws RemoteException{
		HotelPO PO=new HotelPO(hotelinfo);
		return hoteldataservice.modify(PO);
	}
	
	/**
	 * 客户评论
	 * @param commentvo 评价内容
	 * @return 是否增加评论成功
	 * @throws RemoteException 
	 */
	public boolean addComment(CommentVO commentvo) throws RemoteException{
		CommentPO commentpo=new CommentPO(commentvo);
		return hoteldataservice.addComment(commentpo);
	}
	
	/**
	 * 房间内容修改
	 * @param hotelname 酒店名称
	 * @param roomvoList 房间的信息
	 * @return 是否修改房间信息成功
	 * @throws RemoteException 
	 */
	public boolean roomModify(String hotelname, ArrayList<RoomVO> roomvoList) throws RemoteException{
		ArrayList<RoomPO> roompoList=new ArrayList<RoomPO>();
		for(int i=0; i<roomvoList.size(); i++){
			RoomPO roompo=new RoomPO(roomvoList.get(i));
			roompoList.add(roompo);
		}
		return hoteldataservice.roomModify(hotelname, roompoList);
	}
	
	/**
	 * 客户给出要求的房间条件区间，筛选符合条件的酒店，返回酒店列表
	 * @param worstCondition 最坏酒店条件
	 * @param bestCondition 最好酒店条件
	 * @return 符合条件的酒店清单
	 * @throws RemoteException 
	 * 注意：浏览酒店详细信息时，需要先明确地址和商圈，才能进行查看
	 */
	public ArrayList<HotelSearchVO> findWithReq(HotelConditionVO worstCondition, HotelConditionVO bestCondition) throws RemoteException {
		ArrayList<HotelVO> resulthotelvolist=new ArrayList<HotelVO>();//resulthotelvoList将转化为HotelSearchVO
		ArrayList<HotelVO> hotelvoList=new ArrayList<HotelVO>();//hotelvoList为中间值
		boolean isConditionRight=(worstCondition.getAddress().equals(bestCondition.getAddress()))&&
				(worstCondition.getCircle().equals(bestCondition.getCircle()))&&
				(worstCondition.isBooked()==bestCondition.isBooked());//最坏和最好条件的地址、商圈、是否预订过需要相等
		boolean isConditionComplete=((worstCondition.getAddress()!=null)&&(worstCondition.getCircle()!=null));//需要先明确地址和商圈，才能进行查看
		if(isConditionRight&&isConditionComplete){//当输入的条件正确时，进行酒店搜索
			HotelPO worstConditionPO=new HotelPO(worstCondition.toHotelVO(worstCondition));
			HotelPO bestConditionPO=new HotelPO(bestCondition.toHotelVO(bestCondition));
			ArrayList<HotelPO> hotelpoList= hoteldataservice.findWithReq(worstConditionPO, bestConditionPO);
			
			for(int i=0; i<hotelpoList.size(); i++){
				HotelVO hotelvo=new HotelVO(hotelpoList.get(i));
				hotelvoList.add(hotelvo);
			}
			
		}else{
			return null;//需要提醒客户先明确地址和商圈
		}
		
		//做是否预订过的筛选
		if(!worstCondition.isBooked()){//如果没有限制是自己已预订过的，直接返回hotelvoList
			resulthotelvolist= hotelvoList;
		}else{//如果限制是自己已预订过的，则对hotelvoList进行筛选
			ArrayList<OrderVO> ordervoList=order.personOrders(worstCondition.getPersonname());

			for(int i=0; i<ordervoList.size(); i++){
				String hotelname=ordervoList.get(i).getHotelname();//客户已预订过的酒店名称
				for(int j=0; j<hotelvoList.size(); j++){
					if(hotelvoList.get(j).getHotelname().equals(hotelname)){
						resulthotelvolist.add(hotelvoList.get(j));
					}
				}
			}
			
		}
		/*String hotelName, String star, String area, String location*/
		ArrayList<HotelSearchVO> hotelSearchVOList=new ArrayList<HotelSearchVO>();
		for(int i=0; i<resulthotelvolist.size(); i++){
			HotelVO hotel=resulthotelvolist.get(i);
			HotelSearchVO hotelSearchVO=new HotelSearchVO(hotel.getHotelname(), Integer.toString(hotel.getStar()), hotel.getCircle(), hotel.getAddress());
			hotelSearchVOList.add(hotelSearchVO);
		}
		
		return hotelSearchVOList;
	}
	
	/**
	 * 增加酒店
	 * @param hotelvo 待增加酒店信息
	 * @return 是否增加成功
	 * @throws RemoteException 
	 */
	public boolean addHotel(HotelVO hotelvo) throws RemoteException{
		try {
			HotelPO hotelpo=new HotelPO(hotelvo);
			return hoteldataservice.addHotel(hotelpo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 构造方法
	 */
	public Hotel(){
		hoteldataservice=RemoteHelper.getInstance().getHotelDataService();
	}

}
