package com.water.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;




 
@Entity
@Table(name = "APP")
public class Application  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) 
	

	
	@Column(name = "APP_ID", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer appId;

	
	@Column(name = "CON_PERSON_NAME", length = 100)
	private String contactPersonName;

	
	/*@Column(name = "APP_FIRST_NAME", nullable = false, length = 100)
	private String userFirstName;

	F
	@Column(name = "APP_MIDDLE_NAME", length = 100)
	private String userMiddleName;

	
	@Column(name = "APP_LAST_NAME", nullable = false, length = 100)
	private String userLastName;
*/
	
	@Column(name = "LEG_COMP_NAME", length = 100)
	private String legCompName;

	
	@Column(name = "MOBILE_NUMBER", precision = 15, scale = 0)
	private Long mobileNum;
	
	
	@Column(name = "EMAIL_ADDR", length = 254)
	private String emailAddr;

	
	@Column(name = "COR_ADDR", nullable = false, length = 100)
	private String correspondenceAddr;

	
	
	@ManyToOne(targetEntity = MasterCategory.class, fetch = FetchType.LAZY,cascade=CascadeType.ALL) 
	@JoinColumn(name="CATEGORY_ID")
	private MasterCategory categoryType;

	
	@Column(name = "ADDR_PREM_SOUGHT", length = 100)
	private String addrPremSought;

	
	@Column(name = "IS_EXIST_CONNECTION_ALTERATION", nullable = false, length = 100)
	private Integer isExistConnectionForAlteration;
	
	
	@Column(name = "IS_RECON_SERVICE", nullable = false, length = 100)
	private Integer isReconnectionForService;
	
	
	@ManyToOne(targetEntity = MasterReconnection.class, fetch = FetchType.LAZY,cascade=CascadeType.ALL) 
	@JoinColumn(name="RE_CONN_ID")
	private MasterReconnection reconnectionType;

	
	@Column(name = "REQ_MLD", nullable = false, length = 100)
	private Integer reqMld;
	
	
	@ManyToOne(targetEntity = MasterZone.class, fetch = FetchType.LAZY,cascade=CascadeType.ALL) 
	@JoinColumn(name="Zone_ID",nullable = true)
	private MasterZone cmwssbZoneNum;
	
	
	@Column(name = "DIVISION_ID", nullable = true, length = 100)
	private Integer divId;
	
	
	@Column(name = "ANN_ASSMT_VAL", nullable = true, length = 100)
	private Long annAssmtVal;
	
	@Column(name = "BILL_NUM", nullable = true, length = 100)
	private String billNum;
	
	
	@Column(name = "INTR_PLUM_STATUS", nullable = true, length = 100)
	private Integer intrPlumStatus;
	
	
	@Column(name = "WAT_SEV_PROP", nullable = true, length = 100)
	private Integer watSevProp;
	
	@Column(name = "WORK_TYPE", nullable = false, length = 100)
	private Integer workType;
	
	
	@Column(name = "PAYMENT_TYPE", nullable = false, length = 100)
	private Integer paymentType;
	

	@Column(name = "DD_NUM", nullable = true, length = 100)
	private Integer ddNum;
	
	@Column(name = "FROM_WEBSITE", nullable = false, length = 100)
	private String fromWebSite;
	
	/*
	@Column(name = "ACTIVE_FLAG", length = 1)
	private Character activeFlag;

	
	@Column(name = "DELETE_FLAG", length = 1)
	private Character deleteFlag;
	
	
	@Column(name = "SUPV_NAME", length = 100)
	private String supvName;
	*/

	
	/*@Column(name = "APP_STATUS_ID", nullable = false)
	private Integer appStatusId ;	*/

	/*
	@Column(name = "APP_LOCKED_FLAG", length = 1)
	private Character appLockedFlag;
	*/
	
	
	@Column(name = "STATUS_FLAG", length = 1)
	private Character statusFlag;
	
	
	@ManyToOne(targetEntity = MasterStatus.class, fetch = FetchType.LAZY,cascade=CascadeType.ALL) 
	@JoinColumn(name="EE_STATUS")
	private MasterStatus eeStatus;
	
	@ManyToOne(targetEntity = MasterStatus.class, fetch = FetchType.LAZY,cascade=CascadeType.ALL) 
	@JoinColumn(name="CE_STATUS")
	private MasterStatus ceStatus;
	
	
	@ManyToOne(targetEntity = MasterStatus.class, fetch = FetchType.LAZY,cascade=CascadeType.ALL) 
	@JoinColumn(name="MC_STATUS")
	private MasterStatus mcStatus;
	
	

	
	
	@Column(name = "INS_STATUS_ID", length = 1)
	private Integer	insStatusId;

	
	/*
	@ManyToOne(optional = false)
	@JoinColumn(name="CONN_ID")
	private Connection connTypes;
	
	
	@ManyToOne(optional = false)
	@JoinColumn(name="RE_CONN_ID")
	private Reconnection reconnTypes;
	
	
	@ManyToOne(optional = false)
	@JoinColumn(name="ZONE_ID")
	private Zone zonetypes;
	
	*/
	
	
   
    @JoinColumn(name = "USERID")
    private EmployeeDetails userId;
	
	
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "appId", cascade = CascadeType.ALL)
	private Set<Payment> payDtls;




	public Integer getAppId() {
		return appId;
	}




	public void setAppId(Integer appId) {
		this.appId = appId;
	}




	public String getContactPersonName() {
		return contactPersonName;
	}




	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}




	public String getLegCompName() {
		return legCompName;
	}




	public void setLegCompName(String legCompName) {
		this.legCompName = legCompName;
	}




	public Long getMobileNum() {
		return mobileNum;
	}




	public void setMobileNum(Long mobileNum) {
		this.mobileNum = mobileNum;
	}




	public String getEmailAddr() {
		return emailAddr;
	}




	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}




	public String getCorrespondenceAddr() {
		return correspondenceAddr;
	}




	public void setCorrespondenceAddr(String correspondenceAddr) {
		this.correspondenceAddr = correspondenceAddr;
	}




	public MasterCategory getCategoryType() {
		return categoryType;
	}




	public void setCategoryType(MasterCategory categoryType) {
		this.categoryType = categoryType;
	}




	public String getAddrPremSought() {
		return addrPremSought;
	}




	public void setAddrPremSought(String addrPremSought) {
		this.addrPremSought = addrPremSought;
	}




	public Integer getIsExistConnectionForAlteration() {
		return isExistConnectionForAlteration;
	}




	public void setIsExistConnectionForAlteration(Integer isExistConnectionForAlteration) {
		this.isExistConnectionForAlteration = isExistConnectionForAlteration;
	}




	public Integer getIsReconnectionForService() {
		return isReconnectionForService;
	}




	public void setIsReconnectionForService(Integer isReconnectionForService) {
		this.isReconnectionForService = isReconnectionForService;
	}




	public MasterReconnection getReconnectionType() {
		return reconnectionType;
	}




	public void setReconnectionType(MasterReconnection reconnectionType) {
		this.reconnectionType = reconnectionType;
	}




	public Integer getReqMld() {
		return reqMld;
	}




	public void setReqMld(Integer reqMld) {
		this.reqMld = reqMld;
	}




	public MasterZone getCmwssbZoneNum() {
		return cmwssbZoneNum;
	}




	public void setCmwssbZoneNum(MasterZone cmwssbZoneNum) {
		this.cmwssbZoneNum = cmwssbZoneNum;
	}




	public Integer getDivId() {
		return divId;
	}




	public void setDivId(Integer divId) {
		this.divId = divId;
	}




	public Long getAnnAssmtVal() {
		return annAssmtVal;
	}




	public void setAnnAssmtVal(Long annAssmtVal) {
		this.annAssmtVal = annAssmtVal;
	}




	public String getBillNum() {
		return billNum;
	}




	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}




	public Integer getIntrPlumStatus() {
		return intrPlumStatus;
	}




	public void setIntrPlumStatus(Integer intrPlumStatus) {
		this.intrPlumStatus = intrPlumStatus;
	}




	public Integer getWatSevProp() {
		return watSevProp;
	}




	public void setWatSevProp(Integer watSevProp) {
		this.watSevProp = watSevProp;
	}




	public Integer getWorkType() {
		return workType;
	}




	public void setWorkType(Integer workType) {
		this.workType = workType;
	}




	public Integer getPaymentType() {
		return paymentType;
	}




	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}




	public Integer getDdNum() {
		return ddNum;
	}




	public void setDdNum(Integer ddNum) {
		this.ddNum = ddNum;
	}




	public String getFromWebSite() {
		return fromWebSite;
	}




	public void setFromWebSite(String fromWebSite) {
		this.fromWebSite = fromWebSite;
	}




	public Character getStatusFlag() {
		return statusFlag;
	}




	public void setStatusFlag(Character statusFlag) {
		this.statusFlag = statusFlag;
	}




	public MasterStatus getEeStatus() {
		return eeStatus;
	}




	public void setEeStatus(MasterStatus eeStatus) {
		this.eeStatus = eeStatus;
	}




	public MasterStatus getCeStatus() {
		return ceStatus;
	}




	public void setCeStatus(MasterStatus ceStatus) {
		this.ceStatus = ceStatus;
	}




	public MasterStatus getMcStatus() {
		return mcStatus;
	}




	public void setMcStatus(MasterStatus mcStatus) {
		this.mcStatus = mcStatus;
	}




	public Integer getInsStatusId() {
		return insStatusId;
	}




	public void setInsStatusId(Integer insStatusId) {
		this.insStatusId = insStatusId;
	}




	public EmployeeDetails getUserId() {
		return userId;
	}




	public void setUserId(EmployeeDetails userId) {
		this.userId = userId;
	}




	public Set<Payment> getPayDtls() {
		return payDtls;
	}




	public void setPayDtls(Set<Payment> payDtls) {
		this.payDtls = payDtls;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}






	

}
