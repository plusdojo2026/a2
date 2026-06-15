package dto;

import java.io.Serializable;

public class Friend implements Serializable{
	
	private int friend_id;			//フレンドid
	private String user_id;			//ユーザーid
	private String friend_user_id;	//フレンドのユーザーid
	private int friend_request;		//申請の承認フラグ


	
	//コンストラクタ
	public Friend(int friend_id, String user_id, String friend_user_id, int friend_request) {
		super();
		this.friend_id = friend_id;
		this.user_id = user_id;
		this.friend_user_id = friend_user_id;
		this.friend_request = friend_request;
	}
	
	//setter getter
	public int getFriend_id() {
		return friend_id;
	}

	public void setFriend_id(int friend_id) {
		this.friend_id = friend_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getFriend_user_id() {
		return friend_user_id;
	}

	public void setFriend_user_id(String friend_user_id) {
		this.friend_user_id = friend_user_id;
	}

	public int getFriend_request() {
		return friend_request;
	}

	public void setFriend_request(int friend_request) {
		this.friend_request = friend_request;
	}
	
}
