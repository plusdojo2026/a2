package dto;

import java.io.Serializable;
import java.util.Date;

//フレンド一覧のフレンドのTr内容表示用のDTO
public class TrStorage  implements Serializable{
	private String userId;
	private int trId;
	private int trWeight;
	private int count;
	private int sets;
	private String memo;
	private Date date;
	public TrStorage(String userId, int trId, int trWeight, int count, int sets, String memo, Date date) {
		super();
		this.userId = userId;
		this.trId = trId;
		this.trWeight = trWeight;
		this.count = count;
		this.sets = sets;
		this.memo = memo;
		this.date = date;
	}
	public TrStorage() {
		super();
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getTrId() {
		return trId;
	}
	public void setTrId(int trId) {
		this.trId = trId;
	}
	public int getTrWeight() {
		return trWeight;
	}
	public void setTrWeight(int trWeight) {
		this.trWeight = trWeight;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getSets() {
		return sets;
	}
	public void setSets(int sets) {
		this.sets = sets;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
