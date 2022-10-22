package com.laas.auth.model.args;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

public class DOTransmitHistory implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotBlank(message = "DoNo is mandatory")
	private String doNo;
	@NotBlank(message = "MoStatus is mandatory")
	private String moStatus;
	private Date transmitDt;

	public String getDoNo() {
		return doNo;
	}

	public void setDoNo(String doNo) {
		this.doNo = doNo;
	}

	public String getMoStatus() {
		return moStatus;
	}

	public void setMoStatus(String moStatus) {
		this.moStatus = moStatus;
	}

	public Date getTransmitDt() {
		return transmitDt;
	}

	public void setTransmitDt(Date transmitDt) {
		this.transmitDt = transmitDt;
	}

}