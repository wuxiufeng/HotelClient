package businessLogic.userbl;

import java.rmi.RemoteException;

import dataService.userDataService.UserDataService;
import po.HotelWorkerPO;
import po.MarketPO;
import po.personPO.PersonPO;
import rmi.RemoteHelper;
import vo.HotelWorkerVO;
import vo.MarketVO;
import vo.personVO.PersonVO;
/**
 * @author xiamutian
 * @author 武秀峰
 */
public class Manager {
	
	PersonPO person=new PersonPO();
	HotelWorkerPO hotelworker=new HotelWorkerPO();
	MarketPO market=new MarketPO();
	UserDataService userDataService=RemoteHelper.getInstance().getUserDataService();
	
	/**
	 * 网站管理人员登录
	 * @param managername
	 * @param password
	 * @return 是否登陆成功
	 * @throws RemoteException
	 */
	public boolean managerLogin (String managername,String password) throws RemoteException{
		return userDataService.managerLogin(managername, password);
	}
	
	/**
	 * 保存或修改网站营销人员信息
	 * @param marketInfo
	 * @return 是否保存或修改成功
	 * @throws RemoteException
	 */
	public boolean marketSave(MarketVO marketInfo) throws RemoteException{
		MarketPO marketPO=new MarketPO(marketInfo);
		return userDataService.modifyMarket(marketPO);
	}
	
	/**
	 * 保存或修改酒店工作人员信息
	 * @param hotelWorkerInfo
	 * @return 是否保存或修改成功
	 * @throws RemoteException
	 */
	public boolean hotelWorkerSave(HotelWorkerVO hotelWorkerInfo) throws RemoteException{
		HotelWorkerPO hotelWorkerPO=new HotelWorkerPO(hotelWorkerInfo);
		return userDataService.modifyHotelWorker(hotelWorkerPO);
	}
	
	/**
	 * 获取客户信息
	 * @param personname
	 * @return 客户信息
	 * @throws RemoteException
	 */
	public PersonVO getPersonInfo(String personname) throws RemoteException{
		PersonPO po=userDataService.findPerson(personname);
		PersonVO vo=new PersonVO();
		if(vo!=null){
			vo=new PersonVO(po);
			return vo;
		}
		return null;
		
	}
	
	/**
	 * 获取酒店工作人员信息
	 * @param hotelWorkername
	 * @return 酒店工作人员信息
	 * @throws RemoteException
	 */
	public HotelWorkerVO getHotelWorkerInfo(String hotelWorkername) throws RemoteException{
		HotelWorkerPO po=userDataService.findHotelWorker(hotelWorkername);
		HotelWorkerVO vo=new HotelWorkerVO();
		if(po!=null){
			vo=new HotelWorkerVO(po);
			return vo;
		}
		return null;
	}
	
	/**
	 * 增加网站营销人员
	 * @param marketInfo
	 * @return 是否增加成功
	 * @throws RemoteException
	 */
	public boolean marketAdd (MarketVO marketInfo) throws RemoteException{
		MarketPO marketPO=new MarketPO(marketInfo);
		return userDataService.addMarket(marketPO);
		
	}
	
	/**
	 * 增加酒店工作人员
	 * @param hotelworkerInfo
	 * @return 是否增加成功
	 * @throws RemoteException
	 */
	public boolean hotelWorkerAdd (HotelWorkerVO hotelworkerInfo) throws RemoteException{
		HotelWorkerPO hotelworkerPO=new HotelWorkerPO(hotelworkerInfo);
		return userDataService.addHotelWorker(hotelworkerPO);
		
	}
	
	/**
	 * 获取网站营销人员信息
	 * @param marketname
	 * @return 网站营销人员信息
	 * @throws RemoteException
	 */
	public MarketVO getMarketInfo(String marketname) throws RemoteException{
		MarketPO po=userDataService.findMarket(marketname);
		MarketVO vo=new MarketVO();
		if(po!=null){
			vo=new MarketVO(po);
			return vo;
		}
		return null;
	}
	
}
