package com.laas.auth.model.resp;

public class QueryMOItemByPDNoResp {
	
	private String organizationKey;
	
	private String deliveryType;
	
	private String pdNo;
	
	private String sku;
	
	private Integer originalQty;

	public String getOrganizationKey() {
		return organizationKey;
	}

	public void setOrganizationKey(String organizationKey) {
		this.organizationKey = organizationKey;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getPdNo() {
		return pdNo;
	}

	public void setPdNo(String pdNo) {
		this.pdNo = pdNo;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Integer getOriginalQty() {
		return originalQty;
	}

	public void setOriginalQty(Integer originalQty) {
		this.originalQty = originalQty;
	}
}
