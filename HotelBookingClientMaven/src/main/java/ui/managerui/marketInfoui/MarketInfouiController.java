package ui.managerui.marketInfoui;

import java.rmi.RemoteException;

import businessLogic.userbl.UserController;
import businessLogicService.userblService.UserblService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ui.managerui.userSearchAndAddui.UserSearchAndAddui;
import vo.MarketVO;

public class MarketInfouiController {
	@FXML
	private Button returnButton;
	@FXML
	private Button saveButton;
	@FXML
	private TextField marketNameField;
	@FXML
	private TextField passwordField;
	@FXML
	private Label feedbackLabel;
	@FXML
	private Pane mainPane;

	private UserblService userbl;

	// 网站管理人员首界面
	private Pane userSearchAndAddPane;

	private Stage primaryStage;

	private MarketVO marketInfo;

	private String managerName;

	@SuppressWarnings("unused")
	private String marketName;

	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public MarketInfouiController() {
		userbl = new UserController();
	}

	/**
	 * 返回网站管理人员首界面
	 */
	@FXML
	private void returnToFirst() {
		userSearchAndAddPane = new UserSearchAndAddui(primaryStage, managerName);
		mainPane.getChildren().remove(0);
		mainPane.getChildren().add(userSearchAndAddPane);
	}

	/**
	 * 添加酒店工作人员
	 */
	@FXML
	private void saveChanges() {
		String marketName = marketNameField.getText();
		String password = passwordField.getText();
		if (marketName.equals("") || password.equals("")) {
			feedbackLabel.setTextFill(Color.web("#f80202"));
			feedbackLabel.setText("信息填写不完整！");
		} else {
			marketInfo = new MarketVO(marketName, password);
			try {
				if (userbl.marketSave(marketInfo)) {
					feedbackLabel.setTextFill(Color.web("#058cff"));
					feedbackLabel.setText("修改成功！");
				}
				else{
					feedbackLabel.setTextFill(Color.web("#f80202"));
					feedbackLabel.setText("修改的名称已被使用！");
				}
			} catch (RemoteException e) {
				feedbackLabel.setTextFill(Color.web("#f80202"));
				feedbackLabel.setText("网络连接出错！");
				e.printStackTrace();
			}
		}
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
	 * @param managerName
	 */
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	/**
	 * 登录之后调整界面大小，和之后更大的工作区域匹配
	 */
	public void modifyStageSize() {
		primaryStage.setWidth(1000);
		primaryStage.setHeight(800);
		primaryStage.setX(400);
	}

	/**
	 * 设置并显示网站营销人员名称
	 * @param marketName
	 */
	public void setMarketAndShowInfo(String marketName) {
		this.marketName = marketName;
		marketInfo=userbl.getMarketInfo(marketName);
		marketNameField.setText(marketName);
		passwordField.setText(marketInfo.getPassword());
	}
}
