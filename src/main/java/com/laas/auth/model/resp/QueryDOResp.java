package com.laas.auth.model.resp;

public class QueryDOResp {

	private String doNo;

	private String customerNotes;

	private String doStatus;

	private String soldToAddress;

	private String consigneeAddress;

	private String consigneeKey;

	private String storer;

	private String facility;

	public String getDoNo() {
		return doNo;
	}

	public void setDoNo(String doNo) {
		this.doNo = doNo;
	}

	public String getCustomerNotes() {
		return customerNotes;
	}

	public void setCustomerNotes(String customerNotes) {
		this.customerNotes = customerNotes;
	}

	public String getDoStatus() {
		return doStatus;
	}

	public void setDoStatus(String doStatus) {
		this.doStatus = doStatus;
	}

	public String getSoldToAddress() {
		return soldToAddress;
	}

	public void setSoldToAddress(String soldToAddress) {
		this.soldToAddress = soldToAddress;
	}

	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	public String getConsigneeKey() {
		return consigneeKey;
	}

	public void setConsigneeKey(String consigneeKey) {
		this.consigneeKey = consigneeKey;
	}

	public String getStorer() {
		return storer;
	}

	public void setStorer(String storer) {
		this.storer = storer;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}
}
