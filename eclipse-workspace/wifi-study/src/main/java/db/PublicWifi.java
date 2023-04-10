package db;

import java.util.Date;

public class PublicWifi {
	private Double km; 	//거리
	private String mgrNo; 	//관리번호
	private String wrdofc; 	//자치구	
	private String mainNM; 	//와이파이명
	private String adres1; 	//도로명주소
	private String adres2; 	//상세주소
	private String instlFloor; 	//설치위치(층)
	private String instlTV; 	//설치유형
	private String instlMbv; 	//설치기관
	private String svcSe; 		//서비스구분
	private String cmcwr; 	//망종류
	private String cnstcYear; 	//설치년도
	private String inoutDoor; 	//실내외구분
	private String remars3; 	//접속환경
	private Double LAT; 	//x좌표
	private Double LNT;	//y좌표
	private Date workDttm; 	//작업일자
	
	//getters and setters
	public double getKm() {
		return km;
	}
	public void setKm(Double km) {
		this.km = km;
	}
	public String getMgrNo() {
		return mgrNo;
	}
	public void setMgrNo(String mgrNo) {
		this.mgrNo = mgrNo;
	}
	public String getWrdofc() {
		return wrdofc;
	}
	public void setWrdofc(String wrdofc) {
		this.wrdofc = wrdofc;
	}
	public String getMainNM() {
		return mainNM;
	}
	public void setMainNM(String mainNM) {
		this.mainNM = mainNM;
	}
	public String getAdres1() {
		return adres1;
	}
	public void setAdres1(String adres1) {
		this.adres1 = adres1;
	}
	public String getAdres2() {
		return adres2;
	}
	public void setAdres2(String adres2) {
		this.adres2 = adres2;
	}
	public String getInstlFloor() {
		return instlFloor;
	}
	public void setInstlFloor(String instlFloor) {
		this.instlFloor = instlFloor;
	}
	public String getInstlTV() {
		return instlTV;
	}
	public void setInstlTV(String instlTV) {
		this.instlTV = instlTV;
	}
	public String getInstlMbv() {
		return instlMbv;
	}
	public void setInstlMbv(String instlMbv) {
		this.instlMbv = instlMbv;
	}
	public String getSvcSe() {
		return svcSe;
	}
	public void setSvcSe(String svcSe) {
		this.svcSe = svcSe;
	}
	public String getCmcwr() {
		return cmcwr;
	}
	public void setCmcwr(String cmcwr) {
		this.cmcwr = cmcwr;
	}
	public String getCnstcYear() {
		return cnstcYear;
	}
	public void setCnstcYear(String cnstcYear) {
		this.cnstcYear = cnstcYear;
	}
	public String getInoutDoor() {
		return inoutDoor;
	}
	public void setInoutDoor(String inoutDoor) {
		this.inoutDoor = inoutDoor;
	}
	public String getRemars3() {
		return remars3;
	}
	public void setRemars3(String remars3) {
		this.remars3 = remars3;
	}
	public Double getLAT() {
		return LAT;
	}
	public void setLAT(Double lAT) {
		LAT = lAT;
	}
	public Double getLNT() {
		return LNT;
	}
	public void setLNT(Double lNT) {
		LNT = lNT;
	}
	public Date getWorkDttm() {
		return workDttm;
	}
	public void setWorkDttm(Date workDttm) {
		this.workDttm = workDttm;
	}
}
