package dto;

import java.io.Serializable;

public class  TrItem implements Serializable{
	private String TrId;			//トレーニングid
	private String TrItem;		//トレーニング項目

//ゲッターセッタ―
	public String getTrId() {
		return TrId;
	}
	public void setTrId(String trId) {
		TrId = trId;
	}
	public String getTrItem() {
		return TrItem;
	}

//コンストラクタ
	public void setTrItem(String trItem) {
		TrItem = trItem;
	}
	public TrItem(String trId, String trItem) {
		super();
		TrId = trId;
		TrItem = trItem;
	}

}
