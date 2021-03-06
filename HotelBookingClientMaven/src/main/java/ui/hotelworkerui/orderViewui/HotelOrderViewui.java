package ui.hotelworkerui.orderViewui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HotelOrderViewui extends Pane{
	private Stage primaryStage;

	private String personname;
	
	private ObservableList<String> states=FXCollections.observableArrayList();

	private OrderViewuiController orderViewuiController;

	/**
		 * 接受primarystage用来完成界面最小化和可移动化设置
		 * 
		 * @param primaryStage
		 */
		public HotelOrderViewui(Stage primaryStage, String personname) {
			this.primaryStage = primaryStage;
			this.personname = personname;
			states.addAll("未执行","已执行","已撤销","异常","全部");
			initOrderViewui();
		};

	/**
	 * 初始化界面
	 */
	public void initOrderViewui() {
		// 设置新的Pane
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/酒店工作人员首界面.fxml"));
		loader.setRoot(this);
		try {
			loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Scene scene = new Scene(this);
		// primaryStage.setScene(scene);
		orderViewuiController = loader.getController();
		orderViewuiController.launchStage(primaryStage);
		orderViewuiController.setWorkerName(personname);
		orderViewuiController.modifyStageSize();
		orderViewuiController.initOrderTable();
		orderViewuiController.setBookedChoiceBox(states);
	}
}
