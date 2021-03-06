package dataService.userDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.*;
import po.personPO.PersonPO;
import po.personPO.RecordPO;
import vo.personVO.RecordVO;

public interface UserDataService extends Remote{
	public boolean addPerson (PersonPO personInfo)
			throws RemoteException;
	public PersonPO findPerson (String personname)
			throws RemoteException;
	public PersonPO findPerson (int personID)
			throws RemoteException;
	public boolean modifyPerson (PersonPO personInfo)
			throws RemoteException;
	public boolean personLogin(String personname,String password)
			throws RemoteException;
	public MarketPO findMarket (String marketname)
			throws RemoteException;
	public boolean modifyMarket (MarketPO marketInfo)
			throws RemoteException;
	public boolean marketLogin(String marketname,String password)
			throws RemoteException;
	public boolean changeCredit(String username,int credit)
			throws RemoteException;
	public HotelWorkerPO findHotelWorker (String hotelWorkername)
			throws RemoteException;
	public boolean modifyHotelWorker (HotelWorkerPO hotelWorkerInfo)
			throws RemoteException;
	public boolean hotelWorkerLogin(String hotelWorkername,String password)
			throws RemoteException;
	public boolean addMarket(MarketPO marketInfo)
			throws RemoteException;
	public boolean managerLogin(String managername,String password)
			throws RemoteException;
	public boolean addHotelWorker(HotelWorkerPO hotelworker)
			throws RemoteException;
	public boolean writeRecord(String personname, RecordPO record)
			throws RemoteException;// 新增用于写入客户信用记录，传入客户名和此次写入的record对象
	public ArrayList<RecordPO> getRecord(String personname)
			throws RemoteException;
}
