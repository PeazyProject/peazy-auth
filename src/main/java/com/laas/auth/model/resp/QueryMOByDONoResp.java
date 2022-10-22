package com.laas.auth.model.resp;

public class QueryMOByDONoResp {

	private String doNo;
	
	private String pdNo;
	
	private String moStatus;
	
	private String soldToName;
	
	private String customerNotes;
	
	private String facility;

	public String getDoNo() {
		return doNo;
	}

	public void setDoNo(String doNo) {
		this.doNo = doNo;
	}

	public String getPdNo() {
		return pdNo;
	}

	public void setPdNo(String pdNo) {
		this.pdNo = pdNo;
	}

	public String getMoStatus() {
		return moStatus;
	}

	public void setMoStatus(String moStatus) {
		this.moStatus = moStatus;
	}

	public String getSoldToName() {
		return soldToName;
	}

	public void setSoldToName(String soldToName) {
		this.soldToName = soldToName;
	}

	public String getCustomerNotes() {
		return customerNotes;
	}

	public void setCustomerNotes(String customerNotes) {
		this.customerNotes = customerNotes;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}
	
}
