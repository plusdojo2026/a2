package dto;

import java.io.Serializable;

public class Design implements Serializable{

	private int icon_id;		//アイコンid
	private int icon;			//アイコン
	private int background_id;	//背景id
	private int background;		//背景

public Design(int icon_id, int icon, int background_id, int background) {
	super();
	this.icon_id = icon_id;
	this.icon = icon;
	this.background_id = background_id;
	this.background = background;
 }

public int getIcon_id() {
	return icon_id;
}

public void setIcon_id(int icon_id) {
	this.icon_id = icon_id;
}

public int getIcon() {
	return icon;
}

public void setIcon(int icon) {
	this.icon = icon;
}

public int getBackground_id() {
	return background_id;
}

public void setBackground_id(int background_id) {
	this.background_id = background_id;
}

public int getBackground() {
	return background;
}

public void setBackground(int background) {
	this.background = background;
}

}
