package businessLogic.userbl;

import vo.HotelWorkerVO;
import vo.MarketVO;
import vo.personVO.PersonVO;

import java.rmi.RemoteException;
import java.text.ParseException;

import businessLogicService.userblService.UserblService;

/**
 * 
 * @author 武秀峰
 *
 */
public class UserController  implements UserblService{
	private Person person=new Person();
	private Market market=new Market();
	private Manager manager=new Manager();
	private HotelWorker hotelworker=new HotelWorker();
	
	/**
	 * 客户注册
	 * @param userinfo
	 * @return 是否注册成功
	 */
	public boolean register(PersonVO userinfo) {
		try {
			return person.register(userinfo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 客户信息修改，信用不能修改，只能查看
	 * @param personInfo
	 * @return boolean 是否修改成功
	 */
	public boolean personSave(PersonVO personInfo) {
		try {
			return person.modifyPerson(personInfo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 保存网站营销人员信息
	 * @param marketInfo
	 * @return 是否保存成功
	 */
	public boolean marketSave(MarketVO marketInfo) throws RemoteException {
		return manager.marketSave(marketInfo);
	}

	/**
	 * 保存酒店工作人员信息
	 * @param hotelWorkerInfo
	 * @return 是否保存成功
	 */
	public boolean hotelWorkerSave(HotelWorkerVO hotelWorkerInfo) {
		try {
			return manager.hotelWorkerSave(hotelWorkerInfo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 修改信用值
	 * @param personname
	 * @param credit
	 * @return 是否修改成功
	 */
	public boolean changeCredit(String personname, int credit) {
		try {
			return market.changeCredit(personname, credit);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 会员注册
	 * @param personvo
	 * @param vipType no代表不是VIP，ordinary代表是普通VIP，enterprise代表是企业VIP
	 * @param vipInfo 若是普通VIP，info为生日，格式如：20160120；若是企业VIP，格式为非空字符
	 * @return 是否注册成功
	 * @throws ParseException
	 */
	public boolean registeMember(PersonVO personInfo, String vipType, String vipInfo) throws ParseException {
		try {
			return person.registeMember(personInfo, vipType, vipInfo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 根据客户名称获取客户信息
	 * @param personname
	 * @return 客户信息
	 */
	public PersonVO getPersonInfo(String personname) {
	try {
		return person.getPersonInfo(personname);
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	
	}

	/**
	 * 根据客户ID获取客户信息
	 * @param personID
	 * @return 客户信息
	 */
	public PersonVO getPersonInfo(int personID) {
		// TODO Auto-generated method stub
		try {
			return person.getPersonInfo(personID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取酒店工作人员信息
	 * @param hotelWorkername
	 * @return 酒店工作人员信息
	 */
	public HotelWorkerVO getHotelWorkerInfo(String hotelWorkername) {
		try {
			return hotelworker.getHotelWorkerInfo(hotelWorkername);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @param usertype
	 * @return 是否登陆成功
	 */
	public boolean userLogin(String username, String password, String usertype) {
		try {
			if(usertype.equals("person")){
				return person.personLogin(username, password);
			}else if(usertype.equals("hotelworker")){
				return hotelworker.hotelworkerLogin(username, password);
			}else if(usertype.equals("market")){
				return market.marketlogin(username, password);
			}else if(usertype.equals("manager")){
				return manager.managerLogin(username, password);
			}
			else{
				return false;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 增加网站营销人员
	 * @param marketInfo
	 * @return 是否增加成功
	 */
	public boolean marketAdd(MarketVO marketInfo) {
		try {
			return manager.marketAdd(marketInfo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 增加酒店工作人员
	 * @param hotelworkerInfo
	 * @return 是否增加成功
	 */
	public boolean hotelWorkerAdd(HotelWorkerVO hotelworkerInfo) {
		try {
			return manager.hotelWorkerAdd(hotelworkerInfo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 获取网站营销人员信息
	 * @param marketname
	 * @return 网站营销人员信息
	 */
	public MarketVO getMarketInfo(String marketname) {
		try {
			return market.getMarketInfo(marketname);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 判断某用户是否存在
	 * @param username 用户名称
	 * @param usertype 用户类型
	 * @return 若存在，返回true；若不存在，返回false
	 */
	public boolean isExist(String username, String usertype) {
		// TODO Auto-generated method stub
		try {
			if(usertype.equals("person")){
				return person.isExist(username);
			}else if(usertype.equals("hotelworker")){
				return hotelworker.isExist(username);
			}else if(usertype.equals("market")){
				return market.isExist(username);
			}else{
				return false;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	
}	
