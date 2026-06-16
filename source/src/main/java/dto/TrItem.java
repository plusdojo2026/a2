package dto;

import java.io.Serializable;

public class  TrItem implements Serializable{
	private int tr_id;			//トレーニングid
	private String tr_item;		//トレーニング項目
	
	public TrItem(int tr_id, String tr_item) {
		super();
		this.tr_id = tr_id;
		this.tr_item = tr_item;
	}

	public int getTr_id() {
		return tr_id;
	}

	public void setTr_id(int tr_id) {
		this.tr_id = tr_id;
	}

	public String getTr_item() {
		return tr_item;
	}

	public void setTr_item(String tr_item) {
		this.tr_item = tr_item;
	}
	
	
}
