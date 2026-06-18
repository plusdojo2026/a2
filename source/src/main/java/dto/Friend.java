package dto;

import java.io.Serializable;

public class Friend implements Serializable{
	
	private int friendId;			//フレンドid
	private String userId;			//ユーザーid
	private String friendUserId;	//フレンドのユーザーid
	private int friendRequest;		//申請の承認フラグ
	private String userName;		//ユーザーネーム
	private int iconId;				//アイコンID
	private int point;				//ポイント
	
	public Friend() {
		super();
	}

	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFriendUserId() {
		return friendUserId;
	}

	public void setFriendUserId(String friendUserId) {
		this.friendUserId = friendUserId;
	}

	public int getFriendRequest() {
		return friendRequest;
	}

	public void setFriendRequest(int friendRequest) {
		this.friendRequest = friendRequest;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getIconId() {
		return iconId;
	}

	public void setIconId(int iconId) {
		this.iconId = iconId;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public Friend(int friendId, String userId, String friendUserId, int friendRequest, String userName, int iconId,
			int point) {
		super();
		this.friendId = friendId;
		this.userId = userId;
		this.friendUserId = friendUserId;
		this.friendRequest = friendRequest;
		this.userName = userName;
		this.iconId = iconId;
		this.point = point;
	}
	
	
	
}

