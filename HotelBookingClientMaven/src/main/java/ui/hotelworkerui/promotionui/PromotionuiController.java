package ui.hotelworkerui.promotionui;

import businessLogic.userbl.UserController;
import businessLogicService.userblService.UserblService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ui.hotelworkerui.hotelInfoui.HotelInfoui;
import ui.hotelworkerui.orderViewui.OrderViewui;
import ui.hotelworkerui.roomInfoui.RoomInfoui;

public class PromotionuiController {
	
	@FXML
	private Button hotelOrderButton;
	@FXML
	private Button promotionButton;
	@FXML
	private Button hotelInfoButton;
	@FXML
	private Button roomInfoButton;
	@FXML
	private Button addButton;
	@FXML
	private Button editButton;
	@FXML
	private Button deleteButton;
	@FXML
	private Pane mainPane;

	private UserblService userbl;

	// 酒店订单浏览界面
	private Pane hotelOrderPane;

	// 酒店详情查看界面
	private Pane promotionPane;

	// 个人信息界面
	private Pane hotelInfoPane;

	// 房间信息界面
	private Pane roomInfoPane;

	private Stage primaryStage;

	private String hotelName;

	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public PromotionuiController() {
		userbl = new UserController();
	}

	/**
	 * 跳转到酒店订单浏览界面
	 */
	@FXML
	private void viewHotelOrder() {
		hotelOrderPane = new OrderViewui(primaryStage, hotelName);
		mainPane.getChildren().remove(0);
		mainPane.getChildren().add(hotelOrderPane);
	}

	/**
	 * 跳转到促销策略界面
	 */
	@FXML
	private void viewPromotion() {
		promotionPane = new Promotionui(primaryStage, hotelName);
		mainPane.getChildren().remove(0);
		mainPane.getChildren().add(promotionPane);
	}

	/**
	 * 跳转到酒店信息维护界面
	 */
	@FXML
	private void viewHotelInfo() {
		// hotelInfo = hotelbl.showHotelInfo(hotelName);
		hotelInfoPane = new HotelInfoui(primaryStage, hotelName);
		mainPane.getChildren().remove(0);
		mainPane.getChildren().add(roomInfoPane);
	}

	/**
	 * 跳转到房间信息查看界面
	 */
	@FXML
	private void viewRoomInfo() {
		// hotelInfo = hotelbl.showHotelInfo(hotelName);
		roomInfoPane = new RoomInfoui(primaryStage, hotelName);
		mainPane.getChildren().remove(0);
		mainPane.getChildren().add(roomInfoPane);
	}
	
	/**
	 * 新增酒店促销策略
	 */
	@FXML
	private void addPromotion() {
		
	}

	/**
	 * 编辑原有酒店促销策略
	 */
	@FXML
	private void editPromotion() {
		
	}

	/**
	 * 传递Main的primaryStage
	 * 
	 * @param primaryStage
	 */
	public void launchStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	/**
	 * 传递用户名
	 * 
	 * @param hotelName
	 */
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
}
