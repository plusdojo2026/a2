package dto;

import java.io.Serializable;

public class  TrItem implements Serializable{
	private int TrId;			//トレーニングid
	private String TrItem;		//トレーニング項目
	public int getTrId() {
		return TrId;
	}
	public void setTrId(int trId) {
		TrId = trId;
	}
	public String getTrItem() {
		return TrItem;
	}
	public void setTrItem(String trItem) {
		TrItem = trItem;
	}
	public TrItem(int trId, String trItem) {
		super();
		TrId = trId;
		TrItem = trItem;
	}

}
