package dto;

import java.io.Serializable;
import java.util.List;

public class FriendSet implements Serializable {
	private Friend friend;
	private Friend friendInfo;
	private List<TrStorage> latestTraining;
	public FriendSet(Friend friend, Friend friendInfo, List<TrStorage> latestTraining) {
		super();
		this.friend = friend;
		this.friendInfo = friendInfo;
		this.latestTraining = latestTraining;
	}
	public FriendSet() {
		super();
	}
	public Friend getFriend() {
		return friend;
	}
	public void setFriend(Friend friend) {
		this.friend = friend;
	}
	public Friend getFriendInfo() {
		return friendInfo;
	}
	public void setFriendInfo(Friend friendInfo) {
		this.friendInfo = friendInfo;
	}
	public List<TrStorage> getLatestTraining() {
		return latestTraining;
	}
	public void setLatestTraining(List<TrStorage> latestTraining) {
		this.latestTraining = latestTraining;
	}
	
}
