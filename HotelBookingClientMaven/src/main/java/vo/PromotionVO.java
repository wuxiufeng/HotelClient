package vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import po.PromotionPO;


public class PromotionVO {
	private String promotionID;//promotion的唯一标识
	private String promotionName;//promotion的名称，客户可见
	private String promotionType;
	private String hotelnameOrWeb;//若是酒店促销策略，则为酒店名称;若是网站促销策略，则为“WebPromotion”
	
	
	public PromotionVO() {
		super();
	}

	public PromotionVO(String promotionID, String promotionName, String promotionType, String hotelnameOrWeb) {
		super();
		this.promotionID = promotionID;
		this.promotionName = promotionName;
		this.promotionType = promotionType;
		this.hotelnameOrWeb = hotelnameOrWeb;
	}
	
//	public PromotionVO(PromotionPO po) {
//		this.promotionID = po.getPromotionID();
//		this.promotionName = po.getPromotionName();
//		this.promotionType = po.getPromotionType();
//		this.hotelnameOrWeb = po.getHotelnameOrWeb();
//	}

	public String getPromotionID() {
		return promotionID;
	}
	public void setPromotionID(String promotionID) {
		this.promotionID = promotionID;
	}
	public String getPromotionName() {
		return promotionName;
	}
	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}
	public String getPromotionType() {
		return promotionType;
	}
	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}
	public String getHotelnameOrWeb() {
		return hotelnameOrWeb;
	}
	public void setHotelnameOrWeb(String hotelnameOrWeb) {
		this.hotelnameOrWeb = hotelnameOrWeb;
	}
	//vo to po
	public PromotionPO toPO(PromotionVO promotionvo){
		return null;
	}
	
}
