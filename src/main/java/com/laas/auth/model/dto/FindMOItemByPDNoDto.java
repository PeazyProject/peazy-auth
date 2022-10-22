package com.laas.auth.model.dto;

public interface FindMOItemByPDNoDto {
	String getOrganizationKey();
	String getDeliveryType();
	String getPdNo();
	String getSku();
	Integer getOriginalQty();
}
