package dto;

import java.io.Serializable;
	//フィールド
public class User implements Serializable {
	private int 	number;				/*管理番号*/
	private String 	user_name;			/*ユーザーネーム*/
	private String 	height;				/*身長*/
	private String 	gender;				/*性別*/
	private String 	target_weight;		/*目標体重*/
	private String 	logical_delete;		/*論理削除*/
	private String 	user_id;			/*ユーザーID*/
	private String 	password;			/*パスワード*/
	private String 	icon_id;			/*アイコン番号*/
	private String 	design_id;			/*着せ替え番号*/
	private String 	point;				/*豆ポイント*/
	private String 	date_time;			/*ログインの時間*/
	//↓因数ありコンストラクタ
	public User(int number, String user_name, String height, String gender, String target_weight, String logical_delete,
			String user_id, String password, String icon_id, String design_id, String point, String date_time) {
		super();
		this.number = number;
		this.user_name = user_name;
		this.height = height;
		this.gender = gender;
		this.target_weight = target_weight;
		this.logical_delete = logical_delete;
		this.user_id = user_id;
		this.password = password;
		this.icon_id = icon_id;
		this.design_id = design_id;
		this.point = point;
		this.date_time = date_time;
	}
	//因数なしコンストラクタ
	public User() {
		super();
	}
	//ゲッター・セッター
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
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

	public String getTarget_weight() {
		return target_weight;
	}

	public void setTarget_weight(String target_weight) {
		this.target_weight = target_weight;
	}

	public String getLogical_delete() {
		return logical_delete;
	}

	public void setLogical_delete(String logical_delete) {
		this.logical_delete = logical_delete;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIcon_id() {
		return icon_id;
	}

	public void setIcon_id(String icon_id) {
		this.icon_id = icon_id;
	}

	public String getDesign_id() {
		return design_id;
	}

	public void setDesign_id(String design_id) {
		this.design_id = design_id;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getDate_time() {
		return date_time;
	}

	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}

}
