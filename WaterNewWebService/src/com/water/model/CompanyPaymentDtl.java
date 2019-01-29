package com.water.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CompanyPaymentDtl")
public class CompanyPaymentDtl  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = " COMPANY_PAYMENTDTL_ID", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer companyPaymentDtlID;
	
	@ManyToOne(targetEntity = CompanyDtl.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "APP_ID")
	private String appId;

	@Column(name = "PAYMENT_TYPE", nullable = true, length = 100)
	private String paymentType;

	@Column(name = "PAYMENT_AMOUNT", nullable = true, length = 100)
	private String paymentAmount;

	@Column(name = "PAYMENT_STATUS_FLAG", length = 1)
	private Character paymenSstatusFlag;

	@Column(name = "DD_DATE", nullable = true, length = 100)
	private String ddDate;
	@Column(name = "DD_BANK_NAME", nullable = true, length = 100)
	private String ddBankName;
	@Column(name = "DD_BANK_BRANCH", nullable = true, length = 100)
	private String ddBankBranch;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TS", nullable = false, length = 26)
	private Date createTs;

	@Column(name = "CREATE_USERID", nullable = true, length = 30)
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

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Character getPaymenSstatusFlag() {
		return paymenSstatusFlag;
	}

	public void setPaymenSstatusFlag(Character paymenSstatusFlag) {
		this.paymenSstatusFlag = paymenSstatusFlag;
	}

	public String getDdDate() {
		return ddDate;
	}

	public void setDdDate(String ddDate) {
		this.ddDate = ddDate;
	}

	public String getDdBankName() {
		return ddBankName;
	}

	public void setDdBankName(String ddBankName) {
		this.ddBankName = ddBankName;
	}

	public String getDdBankBranch() {
		return ddBankBranch;
	}

	public void setDdBankBranch(String ddBankBranch) {
		this.ddBankBranch = ddBankBranch;
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

	public Integer getCompanyPaymentDtlID() {
		return companyPaymentDtlID;
	}

	public void setCompanyPaymentDtlID(Integer companyPaymentDtlID) {
		this.companyPaymentDtlID = companyPaymentDtlID;
	}

}
