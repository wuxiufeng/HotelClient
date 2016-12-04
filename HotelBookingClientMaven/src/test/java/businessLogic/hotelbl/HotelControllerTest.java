package businessLogic.hotelbl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import businessLogic.hotelbl.HotelController;
import rmi.ClientRunner;
import vo.hotelVO.hotelblVO.HotelConditionVO;
import vo.hotelVO.hotelblVO.HotelVO;

public class HotelControllerTest {
	ClientRunner cr=new ClientRunner();
	HotelController hotel=new HotelController();
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testShowHotelInfo() {
		assertEquals(5, hotel.showHotelInfo("南京大酒店").getStar());
	}

	@Test
	public void testModifyHotelInfo() {
		HotelVO hotelinfo=new HotelVO();
		hotelinfo.setHotelname("南京大酒店");
//		hotelinfo.setCircle("新街口");
//		assertEquals(true,hotel.modifyHotelInfo(hotelinfo));
//		hotelinfo=null;
		assertEquals(true, hotel.modifyHotelInfo(hotelinfo));
	}

	@Test
	public void testAddComment() {
		assertEquals(true, hotel.addComment("很好", "小李", "饭店"));
	}

	@Test
	public void testRoomModify() {
		assertEquals(false, hotel.roomModify(null,7));
		assertEquals(false, hotel.roomModify("单人房", 0));
	}

	@Test
	public void testFindWithReq() {
		HotelConditionVO po=new HotelConditionVO();
		po.setHotelname("111");
		po.setAddress("111");
		po.setCircle("111");
		HotelConditionVO po2=new HotelConditionVO();
		po2.setHotelname("111");
		po2.setAddress("111");
		po2.setCircle("111");
//		assertEquals("很好，很棒",hotel.findWithReq(po, po2).get(0).getComment().get(0));
	}

}
