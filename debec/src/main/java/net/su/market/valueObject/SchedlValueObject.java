package net.su.market.valueObject;

import java.util.List;

/**
 * 일정관련  관련  VO입니다.
 * 
 * @see   net.su.market.valueObject.SchedlValueObject
 * @version  1.0 
 * @ author 최재욱, 2016/08/09
 */
public class SchedlValueObject{
	// fullcalendar(시작) 고정, 못바꿈 / 이 이름 그대로 내가 원하는대로 변환해서 사용해야한다.
	private int id; // 일정 seq
	private String title; // 제목
	private String start; // 시작일
	private String end; // 종료일
	private String url; // url
	private String color; // 색
	// fullcalendar(끝)
	private String schedlCnt; // 일정 내용
	private String schedlRept; // 반복 기간(주,월,년)
	private String schedlReptDat=""; // 반복 기간 종료일, 언제까지 반복할건지
	//
	private List<Integer> reptCont;
	
	
	public List<Integer> getReptCont() {
		return reptCont;
	}
	public void setReptCont(List<Integer> reptCont) {
		this.reptCont = reptCont;
	}
	public String getSchedlCnt() {
		return schedlCnt;
	}
	public void setSchedlCnt(String schedlCnt) {
		this.schedlCnt = schedlCnt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSchedlRept() {
		return schedlRept;
	}
	public void setSchedlRept(String schedlRept) {
		this.schedlRept = schedlRept;
	}
	public String getSchedlReptDat() {
		return schedlReptDat;
	}
	public void setSchedlReptDat(String schedlReptDat) {
		this.schedlReptDat = schedlReptDat;
	}

	

}
