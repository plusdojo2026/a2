package dto;

import java.io.Serializable;

public class Group implements Serializable {
	
	private int number;				//管理番号
	private String userName;		//ユーザー名
	private double height;			//身長
	private String gender;			//性別
	private double targetWeight;	//目標体重
	private int logicalDelete;		//論理削除フラグ(0:有効, 1:削除済)
	private String userId;			//ユーザーID
	private String password;		//パスワード
	private int iconId;				//アイコンID
	private int designId;			//背景ID
	private int point;				//豆ポイント
	
	public Group() {
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

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getTargetWeight() {
		return targetWeight;
	}

	public void setTargetWeight(double targetWeight) {
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

	public Group(int number, String userName, double height, String gender, double targetWeight, int logicalDelete,
			String userId, String password, int iconId, int designId, int point) {
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
	}
}