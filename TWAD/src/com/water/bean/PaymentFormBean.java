package com.water.bean;

public class PaymentFormBean {
	
	private String paymentId;
	private String companyPaymentDtlID;
	private String inspectedDate;
	private String appId;
	private String paymentTypeId;
	private String paymentType;
	private String paymentAmount;
	private String paymentTypeDesc;
	private String paymentDesc;
	private String gstAmount;
	private String gstPercent;
	private String totalAmount;
	private String mcUser;
	
	public String getGstAmount() {
		return gstAmount;
	}
	public void setGstAmount(String gstAmount) {
		this.gstAmount = gstAmount;
	}
	public String getGstPercent() {
		return gstPercent;
	}
	public void setGstPercent(String gstPercent) {
		this.gstPercent = gstPercent;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public String getPaymentDesc() {
		return paymentDesc;
	}
	public void setPaymentDesc(String paymentDesc) {
		this.paymentDesc = paymentDesc;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getPaymentTypeDesc() {
		return paymentTypeDesc;
	}
	public void setPaymentTypeDesc(String paymentTypeDesc) {
		this.paymentTypeDesc = paymentTypeDesc;
	}
	public String getPaymentTypeId() {
		return paymentTypeId;
	}
	public void setPaymentTypeId(String paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getCompanyPaymentDtlID() {
		return companyPaymentDtlID;
	}
	public void setCompanyPaymentDtlID(String companyPaymentDtlID) {
		this.companyPaymentDtlID = companyPaymentDtlID;
	}
	public String getInspectedDate() {
		return inspectedDate;
	}
	public void setInspectedDate(String inspectedDate) {
		this.inspectedDate = inspectedDate;
	}
	public String getMcUser() {
		return mcUser;
	}
	public void setMcUser(String mcUser) {
		this.mcUser = mcUser;
	}

}
