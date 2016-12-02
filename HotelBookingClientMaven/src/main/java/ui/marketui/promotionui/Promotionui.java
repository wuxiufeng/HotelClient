package ui.marketui.promotionui;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class Promotionui {
	private Stage primaryStage;

	private String personname;

	private PromotionuiController promotionViewuiController;

	/**
		 * 接受primarystage用来完成界面最小化和可移动化设置
		 * 
		 * @param primaryStage
		 */
		public Promotionui(Stage primaryStage, String personname) {
			this.primaryStage = primaryStage;
			this.personname = personname;
			initPromotionViewui();
		};

	/**
	 * 初始化界面
	 */
	public void initPromotionViewui() {
		// 设置新的Pane
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Promotionui.fxml"));
		loader.setRoot(this);
		try {
			loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Scene scene = new Scene(this);
		// primaryStage.setScene(scene);
		promotionViewuiController = loader.getController();
		promotionViewuiController.launchStage(primaryStage);
		promotionViewuiController.setPersonname(personname);
	}
}