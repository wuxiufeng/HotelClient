package businessLogicService.hotelblService;

import java.util.ArrayList;
import java.util.Calendar;

import vo.hotelVO.hotelblVO.CommentVO;
import vo.hotelVO.hotelblVO.HotelConditionVO;
import vo.hotelVO.hotelblVO.HotelVO;
import vo.hotelVO.hotelblVO.RoomVO;
import vo.hotelVO.hoteluiVO.HotelSearchVO;
import vo.hotelVO.hoteluiVO.RoomInfoVO;


public interface HotelblService {
	/**
	 * 
	 * @param Hotelname 酒店名
	 * @return 获取酒店信息（PO）
	 */
	public HotelVO showHotelInfo(String Hotelname);
	
	/**
	 * 
	 * @param hotelinfo 修改的酒店信息（VO）
	 * @return 是否修改成功（成功则返回true,不能则返回false）
	 */
	public boolean modifyHotelInfo(HotelVO hotelinfo);
	
	/**
	 * 
	 * @param comment 酒店评价内容
	 * @param username 用户名
	 * @param hotelname 酒店名
	 * @return 是否增加评论成功
	 */
	public boolean addComment(CommentVO commentvo);
	
	/**
	 * 
	 * @param roomtype 房间类型
	 * @param number 修改数量（包括正数和负数）
	 * @return 是否修改房间数量成功
	 */
	public boolean roomModify(String hotelname, ArrayList<RoomVO> rooms);
	
	/**
	 * 
	 * @param condition 筛选条件
	 * @return 符合条件的酒店PO列表
	 */
	public ArrayList<HotelSearchVO> findWithReq(HotelConditionVO worstCondition, HotelConditionVO bestCondition);
	
	/**
	 *增加酒店 
	 * @param hotelvo
	 * @return
	 */
	public boolean addHotel(HotelVO hotelvo);
	
	/**
	 * 返回符合条件的对应酒店的房间
	 * @param hotelname
	 * @param roomtype
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public RoomInfoVO findReqRoom(String hotelname, String roomtype, Calendar starttime, Calendar endtime);	
	
	/**
	 * 返回符合条件的对应酒店的可用房间数量
	 * @param hotelname
	 * @param roomtype
	 * @param starttime
	 * @param endtime
	 * @return 返回对应的房间剩余数量
	 */
	public int getAvailableNumber(String hotelname, String roomtype, String starttime, String endtime);
	
	
}
