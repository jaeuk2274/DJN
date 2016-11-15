package net.su.login.valueObject;

public class PageValueObject {
	
	// 페이징 관련 정보 변수
	private int currentPageNo = 1;//현재 페이지 번호
	private int recordCountPerPage = 10;//한 페이지당 게시되는 게시물 건 수
	private int pageSize = 10;//페이지 리스트에 게시되는 페이지 건 수	
	private int totalRecordCount = 0;//전체 게시물 건 수
	private int totalPageCount = 0;//전체 페이지 개수
	private int firstRecordIndex = 0;//첫 페이지 인덱스 번호(글 번호)
	private int lastRecordIndex = 0;//마지막 페이지 인덱스 번호(글 번호)
	private int firstPageNoOnPageList = 1;//페이지 리스트의 첫 페이지 번호
	private int lastPageNoOnPageList = 1;//페이지 리스트의 마지막 페이지 번호
	
////
	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public int getTotalPageCount() {
		totalPageCount = ((getTotalRecordCount()-1)/getRecordCountPerPage()) + 1;
		return totalPageCount;
	}

//	public void setTotalPageCount(int totalPageCount) {
//		this.totalPageCount = totalPageCount;
//	}

	public int getFirstRecordIndex() {
		firstRecordIndex = (getCurrentPageNo() - 1) * getRecordCountPerPage();
		return firstRecordIndex;
	}

//	public void setFirstRecordIndex(int firstRecordIndex) {
//		this.firstRecordIndex = firstRecordIndex;
//	}

	public int getLastRecordIndex() {
		lastRecordIndex = getCurrentPageNo() * getRecordCountPerPage();
		return lastRecordIndex;
	}

//	public void setLastRecordIndex(int lastRecordIndex) {
//		this.lastRecordIndex = lastRecordIndex;
//	}

	public int getFirstPageNoOnPageList() {
		firstPageNoOnPageList = ((getCurrentPageNo()-1)/getPageSize())*getPageSize() + 1;
		return firstPageNoOnPageList;
	}

//	public void setFirstPageNoOnPageList(int firstPageNoOnPageList) {
//		this.firstPageNoOnPageList = firstPageNoOnPageList;
//	}

	public int getLastPageNoOnPageList() {
		lastPageNoOnPageList = getFirstPageNoOnPageList() + getPageSize() - 1;		
		if(lastPageNoOnPageList > getTotalPageCount()){
			lastPageNoOnPageList = getTotalPageCount();
		}
		return lastPageNoOnPageList;
	}

//	public void setLastPageNoOnPageList(int lastPageNoOnPageList) {
//		this.lastPageNoOnPageList = lastPageNoOnPageList;
//	}
	
}