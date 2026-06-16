package dto;

import java.io.Serializable;
	//フィールド
public class User implements Serializable {
	private int 	number;				/*管理番号*/
	private String 	userName;			/*ユーザーネーム*/
	private Double 	height;				/*身長*/
	private String 	gender;				/*性別*/
	private Double 	targetWeight;		/*目標体重*/
	private int 	logicalDelete;		/*論理削除*/
	private String 	userId;			/*ユーザーID*/
	private String 	password;			/*パスワード*/
	private int 	iconId;			/*アイコン番号*/
	private int 	designId;			/*着せ替え番号*/
	private int 	point;				/*豆ポイント*/
	private String 	dateTime;
	
	public User() {
		
	}
	public User(int number, String userName, Double height, String gender, Double targetWeight, int logicalDelete,
			String userId, String password, int iconId, int designId, int point, String dateTime) {
		super();
		this.number = number;
		this.userName = userName;
		this.height = height;
		this.gender = gender;
		this.targetWeight = targetWeight;
		this.logicalDelete = logicalDelete;
		this.userId = userId;
		this.password = password;
		this.iconId = iconId;
		this.designId = designId;
		this.point = point;
		this.dateTime = dateTime;
		
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Double getTargetWeight() {
		return targetWeight;
	}
	public void setTargetWeight(Double targetWeight) {
		this.targetWeight = targetWeight;
	}
	public int getLogicalDelete() {
		return logicalDelete;
	}
	public void setLogicalDelete(int logicalDelete) {
		this.logicalDelete = logicalDelete;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getIconId() {
		return iconId;
	}
	public void setIconId(int iconId) {
		this.iconId = iconId;
	}
	public int getDesignId() {
		return designId;
	}
	public void setDesignId(int designId) {
		this.designId = designId;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}			/*ログインの時間*/
}