package businessLogic.promotion;


import java.util.ArrayList;

import org.junit.Test;

import businessLogic.promotionbl.PriceCalc;
import vo.hotelVO.hotelblVO.HotelVO;
import vo.hotelVO.hotelblVO.RoomVO;

public class PriceCalcTest {
	PriceCalc price=new PriceCalc();
	@Test
	public void testPriceCut() {
		RoomVO room1=new RoomVO("单人房", "111", 200, null, null);
		RoomVO room2=new RoomVO("双人房", "112", 300, null, null);
		ArrayList<RoomVO> rooms=new ArrayList<RoomVO>();
		rooms.add(room1);
		rooms.add(room2);
		HotelVO hotel=new HotelVO();
		hotel.setRoom(rooms);
		
//		OrderVO order=new OrderVO();
//		ArrayList<Integer> roomnum=new ArrayList<Integer>();
//		roomnum.add(5);
//		order.setRoomnum(roomnum);
//		ArrayList<String> roomty=new ArrayList<String>();
//		roomty.add("单人房");
//		order.setRoomtype(roomty);
//		assertEquals(1000,price.priceCut(hotel, order),1.0);
	}
}
