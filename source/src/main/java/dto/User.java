package dto;

import java.io.Serializable;
	//フィールド
public class User implements Serializable {
	private int 	number;				/*管理番号*/
	private String 	userName;			/*ユーザーネーム*/
	private String 	height;				/*身長*/
	private String 	gender;				/*性別*/
	private String 	targetWeight;		/*目標体重*/
	private String 	logicalDelete;		/*論理削除*/
	private String 	userId;				/*ユーザーID*/
	private String 	password;			/*パスワード*/
	private String 	iconId;				/*アイコン番号*/
	private String 	designId;			/*着せ替え番号*/
	private String 	point;				/*豆ポイント*/
	private String 	dateTime;			/*ログインの時間*/
	public User(int number, String userName, String height, String gender, String targetWeight, String logicalDelete,
			String userId, String password, String iconId, String designId, String point, String dateTime) {
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
	public User() {
		super();
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
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getTargetWeight() {
		return targetWeight;
	}
	public void setTargetWeight(String targetWeight) {
		this.targetWeight = targetWeight;
	}
	public String getLogicalDelete() {
		return logicalDelete;
	}
	public void setLogicalDelete(String logicalDelete) {
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
	public String getIconId() {
		return iconId;
	}
	public void setIconId(String iconId) {
		this.iconId = iconId;
	}
	public String getDesignId() {
		return designId;
	}
	public void setDesignId(String designId) {
		this.designId = designId;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
}