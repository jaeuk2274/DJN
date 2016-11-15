package net.su.prodct.valueObject;

public class ProdctFestivalValueObject extends DebecFestivalValueObject  {
	
	private long prodctSeq; //상품고유값
	private int evntBridgSeq; //행사 브릿지 고유값
	private String mainImg; //상품 메인 이미지
	private String detlImg1; //상품 상세 이미지1
	private String detlImg2; //상품 상세 이미지2
	private String detlImg3; //상품 상세 이미지3
	private String prodctNme; //상품 이름
	private int purchsPric; // 매입가
	private int selPric; //판매가
	private int evntDiscntRat=0; //할인율
	private int salePric; //할인가
	private int margnRat; //마진율
	private String displyCheck; //진열여부
	private int prodctQunt; //재고량
	private int prodctType; //상품 타입
	private int[] prodctSeq_arry;
	private int[] discntRat_arry;
	private int[] salePric_arry;
	private int[] margnRat_arry;
	private String prodctSechText;
	private String tempSechText;
	private String fileName;
	private String excelFile;	// 엑셀파일 경로
	
	public String getExcelFile() {
		return excelFile;
	}
	public void setExcelFile(String excelFile) {
		this.excelFile = excelFile;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getProdctSeq() {
		return prodctSeq;
	}
	public void setProdctSeq(long prodctSeq) {
		this.prodctSeq = prodctSeq;
	}
	public int getEvntBridgSeq() {
		return evntBridgSeq;
	}
	public void setEvntBridgSeq(int evntBridgSeq) {
		this.evntBridgSeq = evntBridgSeq;
	}
	public String getMainImg() {
		return mainImg;
	}
	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
	}
	public String getDetlImg1() {
		return detlImg1;
	}
	public void setDetlImg1(String detlImg1) {
		this.detlImg1 = detlImg1;
	}
	public String getDetlImg2() {
		return detlImg2;
	}
	public void setDetlImg2(String detlImg2) {
		this.detlImg2 = detlImg2;
	}
	public String getDetlImg3() {
		return detlImg3;
	}
	public void setDetlImg3(String detlImg3) {
		this.detlImg3 = detlImg3;
	}
	public String getProdctNme() {
		return prodctNme;
	}
	public void setProdctNme(String prodctNme) {
		this.prodctNme = prodctNme;
	}
	public int getPurchsPric() {
		return purchsPric;
	}
	public void setPurchsPric(int purchsPric) {
		this.purchsPric = purchsPric;
	}
	public int getSelPric() {
		return selPric;
	}
	public void setSelPric(int selPric) {
		this.selPric = selPric;
	}
	public int getEvntDiscntRat() {
		return evntDiscntRat;
	}
	public void setEvntDiscntRat(int evntDiscntRat) {
		this.evntDiscntRat = evntDiscntRat;
	}
	public int getSalePric() {
		return salePric;
	}
	public void setSalePric(int salePric) {
		this.salePric = salePric;
	}
	public int getMargnRat() {
		return margnRat;
	}
	public void setMargnRat(int margnRat) {
		this.margnRat = margnRat;
	}
	public String getDisplyCheck() {
		return displyCheck;
	}
	public void setDisplyCheck(String displyCheck) {
		this.displyCheck = displyCheck;
	}
	public int getProdctQunt() {
		return prodctQunt;
	}
	public void setProdctQunt(int prodctQunt) {
		this.prodctQunt = prodctQunt;
	}
	public int getProdctType() {
		return prodctType;
	}
	public void setProdctType(int prodctType) {
		this.prodctType = prodctType;
	}
	public int[] getProdctSeq_arry() {
		return prodctSeq_arry;
	}
	public void setProdctSeq_arry(int[] prodctSeq_arry) {
		this.prodctSeq_arry = prodctSeq_arry;
	}
	public int[] getDiscntRat_arry() {
		return discntRat_arry;
	}
	public void setDiscntRat_arry(int[] discntRat_arry) {
		this.discntRat_arry = discntRat_arry;
	}
	public int[] getSalePric_arry() {
		return salePric_arry;
	}
	public void setSalePric_arry(int[] salePric_arry) {
		this.salePric_arry = salePric_arry;
	}
	public int[] getMargnRat_arry() {
		return margnRat_arry;
	}
	public void setMargnRat_arry(int[] margnRat_arry) {
		this.margnRat_arry = margnRat_arry;
	}
	public String getProdctSechText() {
		return prodctSechText;
	}
	public void setProdctSechText(String prodctSechText) {
		this.prodctSechText = prodctSechText;
	}
	public String getTempSechText() {
		return tempSechText;
	}
	public void setTempSechText(String tempSechText) {
		this.tempSechText = tempSechText;
	}

}
