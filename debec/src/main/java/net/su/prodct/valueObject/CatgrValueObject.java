package net.su.prodct.valueObject;

import net.su.login.valueObject.PageValueObject;

public class CatgrValueObject extends PageValueObject{
	private String parentCatgrSeq;
	private String catgrSeq;
	private String catgrNme = "소분류";
	private String catgrIcon;

	private String parent;
	private String id;
	private String text;
	private String icon;
	
	//콤보박스
	private String topCatgrNme="대분류";
	private String midCatgrNme="중분류";
	private String botCatgrNme="소분류";
	private String selectedTopCatgrNme="대분류";
	private String selectedMidCatgrNme="중분류";
	private String selectedBotCatgrNme="소분류";
	private String catgrSize;
	
	
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getParentCatgrSeq() {
		return parentCatgrSeq;
	}
	public void setParentCatgrSeq(String parentCatgrSeq) {
		this.parentCatgrSeq = parentCatgrSeq;
	}
	public String getCatgrSeq() {
		return catgrSeq;
	}
	public void setCatgrSeq(String catgrSeq) {
		this.catgrSeq = catgrSeq;
	}
	public String getCatgrNme() {
		return catgrNme;
	}
	public void setCatgrNme(String catgrNme) {
		this.catgrNme = catgrNme;
	}
	public String getCatgrIcon() {
		return catgrIcon;
	}
	public void setCatgrIcon(String catgrIcon) {
		this.catgrIcon = catgrIcon;
	}
	public String getTopCatgrNme() {
		return topCatgrNme;
	}
	public void setTopCatgrNme(String topCatgrNme) {
		this.topCatgrNme = topCatgrNme;
	}
	public String getMidCatgrNme() {
		return midCatgrNme;
	}
	public void setMidCatgrNme(String midCatgrNme) {
		this.midCatgrNme = midCatgrNme;
	}
	public String getBotCatgrNme() {
		return botCatgrNme;
	}
	public void setBotCatgrNme(String botCatgrNme) {
		this.botCatgrNme = botCatgrNme;
	}
	public String getSelectedTopCatgrNme() {
		return selectedTopCatgrNme;
	}
	public void setSelectedTopCatgrNme(String selectedTopCatgrNme) {
		this.selectedTopCatgrNme = selectedTopCatgrNme;
	}
	public String getSelectedMidCatgrNme() {
		return selectedMidCatgrNme;
	}
	public void setSelectedMidCatgrNme(String selectedMidCatgrNme) {
		this.selectedMidCatgrNme = selectedMidCatgrNme;
	}
	public String getSelectedBotCatgrNme() {
		return selectedBotCatgrNme;
	}
	public void setSelectedBotCatgrNme(String selectedBotCatgrNme) {
		this.selectedBotCatgrNme = selectedBotCatgrNme;
	}
	public String getCatgrSize() {
		return catgrSize;
	}
	public void setCatgrSize(String catgrSize) {
		this.catgrSize = catgrSize;
	}
	
	
	
	
	
}
