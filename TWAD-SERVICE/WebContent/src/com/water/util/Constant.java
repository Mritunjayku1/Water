/**
 * 
 */
package com.water.util;

/**
 * @author Mahalingam
 * 
 *         Class contains constant values which can be used throughout the
 *         application.
 * 
 *         Created by Mahalingam on 27-May-2017
 * 
 *         SQL Query for entire application.
 */
public class Constant {
	
	
	
	public static final String Get_SMSTemplate="select app_id as appid,mobile_number as toMobilenumber,'TemplateType' as temptype,'body' as bodymessage,email_addr as emailId from app where app_id=:applicationId  ";
	
	public static final String FieldOfficer_Escalation_Select = "";

	public static final String Get_ComplaintDtls = " ";

	public static final String Get_publicdashboardCount = " ";

	public static final String Get_publicdashboardCount_1 = " "; 
	
	public static final String smsProcessingFee="1";
	
	public static final String appRegister = " ";
	public static final String Get_dashboardCountcha="";
	public static final String Get_dashboardCount = " "; 

	public static final String Update_Complaint = "";
	public static final String Update_ReAssign = "";

	public static final String Reject_Complaint = "";
	public static final String FieldOfficer_ComplaintClose ="";
	public static final String FieldOfficer_Acknowledge =""; 
	public static final String Agent_ComplaintClose = "";
	
	
}
