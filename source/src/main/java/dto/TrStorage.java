package dto;

import java.io.Serializable;

//フレンド一覧のフレンドのTr内容表示用のDTO
public class TrStorage  implements Serializable{
	private String friendUserId;
	private String trItem;
	private int trWeight;
	private int count;
	private int sets;
	private String memo;
	private String date;
	public TrStorage(String friendUserId, String trItem, int trWeight, int count, int sets, String memo, String date) {
		super();
		this.friendUserId = friendUserId;
		this.trItem = trItem;
		this.trWeight = trWeight;
		this.count = count;
		this.sets = sets;
		this.memo = memo;
		this.date = date;
	}
	public TrStorage() {
		super();
	}
	public String getFriendUserId() {
		return friendUserId;
	}
	public void setFriendUserId(String friendUserId) {
		this.friendUserId = friendUserId;
	}
	public String getTrItem() {
		return trItem;
	}
	public void setTrItem(String trItem) {
		this.trItem = trItem;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
