package dto;

import java.io.Serializable;

public class Design implements Serializable{

	private int IconId;		//アイコンid
	private int Icon;			//アイコン
	private int BackgroundId;	//背景id
	private int Background;		//背景
	public int getIconId() {
		return IconId;
	}
	public void setIconId(int iconId) {
		IconId = iconId;
	}
	public int getIcon() {
		return Icon;
	}
	public void setIcon(int icon) {
		Icon = icon;
	}
	public int getBackgroundId() {
		return BackgroundId;
	}
	public void setBackgroundId(int backgroundId) {
		BackgroundId = backgroundId;
	}
	public int getBackground() {
		return Background;
	}
	public void setBackground(int background) {
		Background = background;
	}
	public Design(int iconId, int icon, int backgroundId, int background) {
		super();
		IconId = iconId;
		Icon = icon;
		BackgroundId = backgroundId;
		Background = background;
	}

}
