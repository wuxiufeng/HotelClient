package ui.hotelworkerui.roomInfoui;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RoomInfoui extends Pane{
	private Stage primaryStage;

	private String hotelName;

	private RoomInfouiController roomInfoViewuiController;

	/**
		 * 接受primarystage用来完成界面最小化和可移动化设置
		 * 
		 * @param primaryStage
		 */
		public RoomInfoui(Stage primaryStage, String hotelName) {
			this.primaryStage = primaryStage;
			this.hotelName = hotelName;
			initRoomInfoViewui();
		};

	/**
	 * 初始化界面
	 */
	public void initRoomInfoViewui() {
		// 设置新的Pane
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/酒店客房信息修改界面.fxml"));
		loader.setRoot(this);
		try {
			loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Scene scene = new Scene(this);
		// primaryStage.setScene(scene);
		roomInfoViewuiController = loader.getController();
		roomInfoViewuiController.launchStage(primaryStage);
		roomInfoViewuiController.setHotelName(hotelName);
	}
}
