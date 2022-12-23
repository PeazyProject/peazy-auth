package com.peazy.auth.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Auth_Menu", schema = "alanlee")
@Data
public class MenuEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer seqNo;
	private String nodeCode;
	private String nodeNameTW;
	private String nodeNameEN;
	private String nodeType;
	private String url;
	private String parentNodeSeqNo;
	private Integer sortPriority;
	private Integer permissionCode;
	private String remark;
	private String icon;
	private String createUser;
	private Date createDt;
	private String updateUser;
	private Date updateDt;
}
