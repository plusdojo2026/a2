package dto;

import java.io.Serializable;

public class Graph implements Serializable{
	//グラフ作成用DTO
	private int tr_id; 
	private int tr_weight;
	private int counts;
	private int sets;
	private String group_date;
	
	//コンストラクタ
	public int getTr_id() {
		return tr_id;
	}
	public Graph(int tr_id, int tr_weight, int counts, int sets, String group_date) {
		super();
		this.tr_id = tr_id;
		this.tr_weight = tr_weight;
		this.counts = counts;
		this.sets = sets;
		this.group_date = group_date;
	}
	
	//getter setter
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
	public String getGroup_date() {
		return group_date;
	}
	public void setGroup_date(String group_date) {
		this.group_date = group_date;
	}
	

}