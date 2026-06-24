package dto;

import java.io.Serializable;
import java.util.Date;


//tr_storagesと　storages　というテーブルが入っている。　記録の保存用のdto
public class Storage implements Serializable{
	private int storage_id;
	private String user_id;
	private double weight;
	private Double fat;
	private String memo;
	private String comments;
	private int stamp;
	private Date date;
	private int id;
	private int tr_id; 
	private int tr_weight;
	private int counts;
	private int sets;
	private String trItem;
	
	
	
	public  String getTrItem() {
		return trItem;
	}
	public void setTrItem(String trItem) {
		this.trItem = trItem;
	}
	
	//ゲッターとセッター
	public int getStorage_id() {
		return storage_id;
	}
	public void setStorage_id(int storage_id) {
		this.storage_id = storage_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public Double getFat() {
		return fat;
	}
	public void setFat(Double fat) {
		this.fat = fat;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getStamp() {
		return stamp;
	}
	public void setStamp(int stamp) {
		this.stamp = stamp;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	public Storage(){
		
	}
	
	//コンストラクタ
	public Storage(int storage_id, String user_id, double weight, Double fat, String memo, String comments, int stamp,
			Date date, int id, int tr_id, int tr_weight, int counts, int sets) {
		super();
		this.storage_id = storage_id;
		this.user_id = user_id;
		this.weight = weight;
		this.fat = fat;
		this.memo = memo;
		this.comments = comments;
		this.stamp = stamp;
		this.date = date;
		this.id = id;
		this.tr_id = tr_id;
		this.tr_weight = tr_weight;
		this.counts = counts;
		this.sets = sets;
	}
	
	
}
	
	

	
	