package net.su.app.appComnty.valueObject;

public class ComntyValueObject {
	
	private int boardSeq; //게시판 고유값
	private String boardImg; //게시판 이미지
	private String boardTitl; //게시판 이름
	private String boardIntrodun; //게시판 소개글
	private String boardPw; //게시판 비밀번호
	private int custmrSeq; //고객 고유값
	private int empSeq; //관리자 고유값
	private int boardEntryCount; //게시판 참여자수
	private int hotlistBoardSeq; //즐겨찾는 게시판 고유값
	private int talkSeq; //글 고유값
	private String talk; //글
	private String talkRegstrtnDat; //글 등록일
	private String custmrNme; //고객 이름
	private String custmrId; //고객 아이디
	private String hotlistCheck; //즐겨찾기 체크
	private String boardPwCheck; //비밀번호 체크
	private String searchText;

	public int getEmpSeq() {
		return empSeq;
	}
	public void setEmpSeq(int empSeq) {
		this.empSeq = empSeq;
	}
	public String getHotlistCheck() {
		return hotlistCheck;
	}
	public void setHotlistCheck(String hotlistCheck) {
		this.hotlistCheck = hotlistCheck;
	}
	public int getBoardEntryCount() {
		return boardEntryCount;
	}
	public void setBoardEntryCount(int boardEntryCount) {
		this.boardEntryCount = boardEntryCount;
	}
	public int getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}
	public String getBoardImg() {
		return boardImg;
	}
	public void setBoardImg(String boardImg) {
		this.boardImg = boardImg;
	}
	public String getBoardTitl() {
		return boardTitl;
	}
	public void setBoardTitl(String boardTitl) {
		this.boardTitl = boardTitl;
	}
	public String getBoardIntrodun() {
		return boardIntrodun;
	}
	public void setBoardIntrodun(String boardIntrodun) {
		this.boardIntrodun = boardIntrodun;
	}
	public String getBoardPw() {
		return boardPw;
	}
	public void setBoardPw(String boardPw) {
		this.boardPw = boardPw;
	}
	public int getCustmrSeq() {
		return custmrSeq;
	}
	public void setCustmrSeq(int custmrSeq) {
		this.custmrSeq = custmrSeq;
	}
	public int getHotlistBoardSeq() {
		return hotlistBoardSeq;
	}
	public void setHotlistBoardSeq(int hotlistBoardSeq) {
		this.hotlistBoardSeq = hotlistBoardSeq;
	}
	public int getTalkSeq() {
		return talkSeq;
	}
	public void setTalkSeq(int talkSeq) {
		this.talkSeq = talkSeq;
	}
	public String getTalk() {
		return talk;
	}
	public void setTalk(String talk) {
		this.talk = talk;
	}
	public String getTalkRegstrtnDat() {
		return talkRegstrtnDat;
	}
	public void setTalkRegstrtnDat(String talkRegstrtnDat) {
		this.talkRegstrtnDat = talkRegstrtnDat;
	}
	public String getCustmrNme() {
		return custmrNme;
	}
	public void setCustmrNme(String custmrNme) {
		this.custmrNme = custmrNme;
	}
	public String getCustmrId() {
		return custmrId;
	}
	public void setCustmrId(String custmrId) {
		this.custmrId = custmrId;
	}
	public String getBoardPwCheck() {
		return boardPwCheck;
	}
	public void setBoardPwCheck(String boardPwCheck) {
		this.boardPwCheck = boardPwCheck;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
}
