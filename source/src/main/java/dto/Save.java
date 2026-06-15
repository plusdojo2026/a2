package dto;

import java.io.Serializable;
import java.util.Date;


//savesと　tr_saves　というテーブルが入っている。　記録の一時保存用のdto
public class Save implements Serializable{

	private int save_id;
	private String user_id;
	private double weight;
	private double fat;
	private String memo;
	private int stamp;
	private Date date;
	private int id;
	private int tr_id;
	private int tr_weight;
	private int counts;
	private int sets;
	
	
	
	
	
	//ゲッターとセッター
	
	public int getSave_id() {
		return save_id;
	}
	public void setSave_id(int save_id) {
		this.save_id = save_id;
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
	public double getFat() {
		return fat;
	}
	public void setFat(double fat) {
		this.fat = fat;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
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
	
	
	
//	コンストラクタ　初期化する
	
	
	public Save() {
		
		
		
	}
	
	
	
	
	public Save(int save_id, String user_id, double weight, double fat, String memo,
			int stamp, Date date, int id, int tr_id, int tr_weight, int counts, int sets) {
		super();
		this.save_id = save_id;
		this.user_id = user_id;
		this.weight = weight;
		this.fat = fat;
		this.memo = memo;
		this.stamp = stamp;
		this.date = date;
		this.id = id;
		this.tr_id = tr_id;
		this.tr_weight = tr_weight;
		this.counts = counts;
		this.sets = sets;
	}
	
}
