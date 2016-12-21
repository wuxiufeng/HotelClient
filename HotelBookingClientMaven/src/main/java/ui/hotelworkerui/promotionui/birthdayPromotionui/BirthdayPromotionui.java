package ui.hotelworkerui.promotionui.birthdayPromotionui;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BirthdayPromotionui extends Pane{
	private Stage primaryStage;

	private String workerName;

	private BirthdayPromotionuiController birthdayPromotionuiController;

	/**
		 * 接受primarystage用来完成界面最小化和可移动化设置
		 * 
		 * @param primaryStage
		 */
		public BirthdayPromotionui(Stage primaryStage, String workerName) {
			this.primaryStage = primaryStage;
			this.workerName = workerName;
			initPromotionViewui();
		};

	/**
	 * 初始化界面
	 */
	public void initPromotionViewui() {
		// 设置新的Pane
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/生日促销策略.fxml"));
		loader.setRoot(this);
		try {
			loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Scene scene = new Scene(this);
		// primaryStage.setScene(scene);
		birthdayPromotionuiController = loader.getController();
		birthdayPromotionuiController.launchStage(primaryStage);
		birthdayPromotionuiController.setWorkerNameAndShowInfo(workerName);
	}
}
