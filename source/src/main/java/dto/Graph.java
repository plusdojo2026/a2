package dto;

import java.io.Serializable;

public class Graph implements Serializable{
	//グラフ作成用DTO
	private String tr_item;
	private int tr_id;
	private int tr_weight;
	private int counts;
	private int sets;
	private String TD_date;
	
	//コンストラクタ
	public Graph(String tr_item, int tr_weight, int counts, int sets, String TD_date) {
		super();
		this.tr_item = tr_item;
		this.tr_weight = tr_weight;
		this.counts = counts;
		this.sets = sets;
		this.TD_date = TD_date;
	}

	//項目用コンストラクタ
	public Graph(String tr_item, int tr_id) {
		super();
		this.tr_item = tr_item;
		this.tr_id =tr_id;
	}

	public String getTr_item() {
		return tr_item;
	}

	public void setTr_item(String tr_item) {
		this.tr_item = tr_item;
	}

	public int getTr_id() {
		return tr_id;
	}

	public void setTr_id(int tr_id) {
		this.tr_id = tr_id;
	}

	public int getTr_weight() {
		return tr_weight;
	}

	public void setTr_weight(int tr_weight) {
		this.tr_weight = tr_weight;
	}

	public int getCounts() {
		return counts;
	}

	public void setCounts(int counts) {
		this.counts = counts;
	}

	public int getSets() {
		return sets;
	}

	public void setSets(int sets) {
		this.sets = sets;
	}

	public String getTD_date() {
		return TD_date;
	}

	public void setTD_date(String tD_date) {
		TD_date = tD_date;
	}
	
	//getter setter

}