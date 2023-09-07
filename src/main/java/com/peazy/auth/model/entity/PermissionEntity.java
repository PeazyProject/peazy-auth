package com.peazy.auth.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name = "Auth_Permission", schema = "alanlee")
@Data
public class PermissionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SeqNo", unique = true, nullable = false)
	private Long seqNo;
	private String permissionCode;
	private String permissionName;
	private String isActivated;
	private String createUser;
	private Date createDt;
	private String updateUser;
	private Date updateDt;
}
