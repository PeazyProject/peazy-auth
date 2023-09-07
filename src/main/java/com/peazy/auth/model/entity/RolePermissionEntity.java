package com.peazy.auth.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Auth_RolePermission", schema = "alanlee")
public class RolePermissionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SeqNo", unique = true, nullable = false)
	private Long seqNo;
	private Long roleSeqNo;
	private Long permissionSeqNo;
	private Integer isActivated;
	private String createUser;
	private Date createDt;
	private String updateUser;
	private Date updateDt;	
}
