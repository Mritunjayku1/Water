package com.water.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "CompanyDtl")
public class CompanyDtl implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "sequence_app_id", strategy = "com.water.daoImpl.ApplicationIdGenerator")
	@GeneratedValue(generator = "sequence_app_id")
	@Column(name = "APP_ID", unique = true, nullable = false, precision = 10, scale = 0)
	private String appId;

	@Column(name = "LEG_COMP_NAME", length = 100)
	private String legCompName;

	@Column(name = "C_DOOR_NO", length = 100)
	private String cdoorNo;
	@Column(name = "C_PLOT_NO", nullable = true, length = 100)
	private String cplotNo;
	@Column(name = "C_STREET_NAME", nullable = true, length = 100)
	private String cstreetName;
	@Column(name = "C_LOCATION", nullable = true, length = 100)
	private String clocation;
	@Column(name = "C_PIN_CODE", nullable = false, length = 100)
	private String cpinCode;

	@Column(name = "SALUTATION", length = 100)
	private String salutation;

	@Column(name = "CON_PERSON_NAME", length = 100)
	private String contactPersonName;

	@Column(name = "MOBILE_NUMBER", precision = 15, scale = 0)
	private Long mobileNum;

	@Column(name = "LANDLINE_NUMBER", precision = 15, scale = 0)
	private Long landLineNo;

	@Column(name = "EMAIL_ADDR", length = 254)
	private String emailAddr;

	@ManyToOne(targetEntity = MasterCategory.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "CATEGORY_ID")
	private MasterCategory categoryType;

	@Column(name = "ADDR_PREM_SOUGHT", length = 100)
	private String addrPremSought;

	@Column(name = "DOOR_NO", length = 100)
	private String doorNo;
	@Column(name = "PLOT_NO", nullable = true, length = 100)
	private String plotNo;
	@Column(name = "STREET_NAME", nullable = true, length = 100)
	private String streetName;
	@Column(name = "LOCATION", nullable = true, length = 100)
	private String location;
	@Column(name = "DISTRICT", nullable = false, length = 100)
	private String district;
	@Column(name = "TALUK", nullable = false, length = 100)
	private String taluk;
	@Column(name = "VILLAGE", nullable = false, length = 100)
	private String village;
	@Column(name = "PINCODE", nullable = false, length = 100)
	private String pincode;
	@Column(name = "SURVEY_FIELDNO", nullable = true, length = 100)
	private String surveyFieldNo;

	@Column(name = "IS_NEW_CONNECTION", nullable = false, length = 100)
	private Integer isNewConnection;

	@Column(name = "REQ_KLD", nullable = false, length = 100)
	private String reqKld;
	
	@Column(name = "GST_AMOUNT", nullable = false, length = 100)
	private String gstAmount;
	
	@Column(name = "TOTAL_AMOUNT", nullable = false, length = 100)
	private String totalAmount;

	@Column(name = "INTR_PLUM_STATUS", nullable = true, length = 100)
	private Integer intrPlumStatus;

	@Column(name = "WORK_TYPE", nullable = false, length = 100)
	private Integer workType;

	@Column(name = "UPLOAD_SITE_PLAN", nullable = true, length = 100)
	private String uploadSitePlan;
	@Column(name = "UPLOAD_SUMP_DRAWING", nullable = true, length = 100)
	private String uploadSumpDrawing;
	@Column(name = "UPLOAD_OWNERSHIP", nullable = true, length = 100)
	private String uploadOwnerShip;

	@Column(name = "FROM_WEBSITE", nullable = false, length = 100)
	private String fromWebSite;

	@JoinColumn(name = "USERID")
	private EmployeeDetails userId;

	@ManyToOne(optional = false)
	@JoinColumn(name = "SMS_ID")
	private SmsTemp SmsId;

	@Column(name = "APPLICATION_STATUS", nullable = false, length = 100)
	private Integer applicationStatus=0;
	
	@Column(name = "ACTIVE", nullable = false, length = 100)
	private Integer active=0;

	// @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INSPECTION_DATE", nullable = true, length = 26)
	private String inspectionDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "COMPLETION_DATE", nullable = true, length = 26)
	private Date completionDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "COMMISSION_DATE", nullable = true, length = 26)
	private Date commissionDate;

	// @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TENTATIVE_DATE", nullable = true, length = 26)
	private String tentativeDate;

	@Column(name = "CAF_ID", length = 100)
	private String cafId;
	
	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TS", nullable = false, length = 26)
	private Date createTs;

	
	@Column(name = "CREATE_USERID", nullable = false, length = 30)
	private String createUserId;

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_TS", nullable = true, length = 26)
	private Date updateTs;

	
	@Column(name = "UPDATE_USERID", nullable = true, length = 30)
	private String updateUserId;


	public String getAppId() {
		return appId;
	}


	public void setAppId(String appId) {
		this.appId = appId;
	}


	public String getLegCompName() {
		return legCompName;
	}


	public void setLegCompName(String legCompName) {
		this.legCompName = legCompName;
	}


	public String getCdoorNo() {
		return cdoorNo;
	}


	public void setCdoorNo(String cdoorNo) {
		this.cdoorNo = cdoorNo;
	}


	public String getCplotNo() {
		return cplotNo;
	}


	public void setCplotNo(String cplotNo) {
		this.cplotNo = cplotNo;
	}


	public String getCstreetName() {
		return cstreetName;
	}


	public void setCstreetName(String cstreetName) {
		this.cstreetName = cstreetName;
	}


	public String getClocation() {
		return clocation;
	}


	public void setClocation(String clocation) {
		this.clocation = clocation;
	}


	public String getCpinCode() {
		return cpinCode;
	}


	public void setCpinCode(String cpinCode) {
		this.cpinCode = cpinCode;
	}


	public String getSalutation() {
		return salutation;
	}


	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}


	public String getContactPersonName() {
		return contactPersonName;
	}


	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}


	public Long getMobileNum() {
		return mobileNum;
	}


	public void setMobileNum(Long mobileNum) {
		this.mobileNum = mobileNum;
	}


	public Long getLandLineNo() {
		return landLineNo;
	}


	public void setLandLineNo(Long landLineNo) {
		this.landLineNo = landLineNo;
	}


	public String getEmailAddr() {
		return emailAddr;
	}


	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
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


	public String getDoorNo() {
		return doorNo;
	}


	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}


	public String getPlotNo() {
		return plotNo;
	}


	public void setPlotNo(String plotNo) {
		this.plotNo = plotNo;
	}


	public String getStreetName() {
		return streetName;
	}


	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getDistrict() {
		return district;
	}


	public void setDistrict(String district) {
		this.district = district;
	}


	public String getTaluk() {
		return taluk;
	}


	public void setTaluk(String taluk) {
		this.taluk = taluk;
	}


	public String getVillage() {
		return village;
	}


	public void setVillage(String village) {
		this.village = village;
	}


	public String getPincode() {
		return pincode;
	}


	public void setPincode(String pincode) {
		this.pincode = pincode;
	}


	public String getSurveyFieldNo() {
		return surveyFieldNo;
	}


	public void setSurveyFieldNo(String surveyFieldNo) {
		this.surveyFieldNo = surveyFieldNo;
	}


	public Integer getIsNewConnection() {
		return isNewConnection;
	}


	public void setIsNewConnection(Integer isNewConnection) {
		this.isNewConnection = isNewConnection;
	}


	public String getReqKld() {
		return reqKld;
	}


	public void setReqKld(String reqKld) {
		this.reqKld = reqKld;
	}


	public Integer getIntrPlumStatus() {
		return intrPlumStatus;
	}


	public void setIntrPlumStatus(Integer intrPlumStatus) {
		this.intrPlumStatus = intrPlumStatus;
	}


	public Integer getWorkType() {
		return workType;
	}


	public void setWorkType(Integer workType) {
		this.workType = workType;
	}


	public String getUploadSitePlan() {
		return uploadSitePlan;
	}


	public void setUploadSitePlan(String uploadSitePlan) {
		this.uploadSitePlan = uploadSitePlan;
	}


	public String getUploadSumpDrawing() {
		return uploadSumpDrawing;
	}


	public void setUploadSumpDrawing(String uploadSumpDrawing) {
		this.uploadSumpDrawing = uploadSumpDrawing;
	}


	public String getUploadOwnerShip() {
		return uploadOwnerShip;
	}


	public void setUploadOwnerShip(String uploadOwnerShip) {
		this.uploadOwnerShip = uploadOwnerShip;
	}


	public String getFromWebSite() {
		return fromWebSite;
	}


	public void setFromWebSite(String fromWebSite) {
		this.fromWebSite = fromWebSite;
	}


	public EmployeeDetails getUserId() {
		return userId;
	}


	public void setUserId(EmployeeDetails userId) {
		this.userId = userId;
	}


	public SmsTemp getSmsId() {
		return SmsId;
	}


	public void setSmsId(SmsTemp smsId) {
		SmsId = smsId;
	}

	public String getInspectionDate() {
		return inspectionDate;
	}


	public void setInspectionDate(String inspectionDate) {
		this.inspectionDate = inspectionDate;
	}


	public Date getCompletionDate() {
		return completionDate;
	}


	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}


	public Date getCommissionDate() {
		return commissionDate;
	}


	public void setCommissionDate(Date commissionDate) {
		this.commissionDate = commissionDate;
	}


	public String getTentativeDate() {
		return tentativeDate;
	}


	public void setTentativeDate(String tentativeDate) {
		this.tentativeDate = tentativeDate;
	}


	public String getCafId() {
		return cafId;
	}


	public void setCafId(String cafId) {
		this.cafId = cafId;
	}


	public Date getCreateTs() {
		return createTs;
	}


	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}


	public String getCreateUserId() {
		return createUserId;
	}


	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}


	public Date getUpdateTs() {
		return updateTs;
	}


	public void setUpdateTs(Date updateTs) {
		this.updateTs = updateTs;
	}


	public String getUpdateUserId() {
		return updateUserId;
	}


	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}


	public String getGstAmount() {
		return gstAmount;
	}


	public void setGstAmount(String gstAmount) {
		this.gstAmount = gstAmount;
	}


	public String getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Integer getActive() {
		return active;
	}


	public void setActive(Integer active) {
		this.active = active;
	}


	public Integer getApplicationStatus() {
		return applicationStatus;
	}


	public void setApplicationStatus(Integer applicationStatus) {
		this.applicationStatus = applicationStatus;
	}


	
}
