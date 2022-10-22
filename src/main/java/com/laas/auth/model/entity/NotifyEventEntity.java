package com.laas.auth.model.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NotifyEvent", schema = "Notify")
public class NotifyEventEntity {
    private Long seqNo;
    private String notifyEventCode;
    private String notifyEventName;
    private String notifyEventEngName;
    private Integer isActivated;
    private Integer isByOwner;
    private Integer isShowTenant;
    private Integer isShowFacility;
    private Integer isShowForwarder;
    private Integer isUserVisible;
    private String createUser;
    private Date createDt;
    private String updateUser;
    private Date updateDt;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SeqNo", unique = true, nullable = false)
    public Long getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Long seqNo) {
        this.seqNo = seqNo;
    }

    public String getNotifyEventName() {
        return notifyEventName;
    }

    public void setNotifyEventName(String notifyEventName) {
        this.notifyEventName = notifyEventName;
    }

    public Integer getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(Integer isActivated) {
        this.isActivated = isActivated;
    }

    public Integer getIsByOwner() {
        return isByOwner;
    }

    public void setIsByOwner(Integer isByOwner) {
        this.isByOwner = isByOwner;
    }

    public Integer getIsShowTenant() {
        return isShowTenant;
    }

    public void setIsShowTenant(Integer isShowTenant) {
        this.isShowTenant = isShowTenant;
    }

    public Integer getIsShowFacility() {
        return isShowFacility;
    }

    public void setIsShowFacility(Integer isShowFacility) {
        this.isShowFacility = isShowFacility;
    }

    public Integer getIsShowForwarder() {
        return isShowForwarder;
    }

    public void setIsShowForwarder(Integer isShowForwarder) {
        this.isShowForwarder = isShowForwarder;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }
    
    public String getNotifyEventCode() {
        return notifyEventCode;
    }

    public void setNotifyEventCode(String notifyEventCode) {
        this.notifyEventCode = notifyEventCode;
    }

    public Integer getIsUserVisible() {
        return isUserVisible;
    }

    public void setIsUserVisible(Integer isUserVisible) {
        this.isUserVisible = isUserVisible;
    }

    public String getNotifyEventEngName() {
        return notifyEventEngName;
    }

    public void setNotifyEventEngName(String notifyEventEngName) {
        this.notifyEventEngName = notifyEventEngName;
    }

    @Override
    public String toString() {
        return "NotifyEventEntity [seqNo=" + seqNo + ", notifyEventCode=" + notifyEventCode
                + ", notifyEventName=" + notifyEventName + ", notifyEventEngName="
                + notifyEventEngName + ", isActivated=" + isActivated + ", isByOwner=" + isByOwner
                + ", isShowTenant=" + isShowTenant + ", isShowFacility=" + isShowFacility
                + ", isShowForwarder=" + isShowForwarder + ", isUserVisible=" + isUserVisible
                + ", createUser=" + createUser + ", createDt=" + createDt + ", updateUser="
                + updateUser + ", updateDt=" + updateDt + "]";
    }
    
}
