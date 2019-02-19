package com.water.util;

/*Developed by Mahalingam
 * This class used to send sms &email 2017-06-06
 * */
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.water.daoImpl.SmsDataDaoImp;
import com.water.model.Application;

public class SMSBuilder {

	ResourceBundle rb1 = ResourceBundle.getBundle("resources/constant");
	// String CountryCode;
	String MsgTxt;
	String SMSUrl;
	String MobileNo;
	String isSMSEmailActive = "1";

	byte data[] = null;

	public SMSBuilder() {
		// this.CountryCode = rb1.getString("CountryCode");
		this.MsgTxt = rb1.getString("MsgTxt");
		this.SMSUrl = rb1.getString("SMSUrl");
		this.MobileNo = rb1.getString("MobileNo");
		this.isSMSEmailActive = rb1.getString("isSMSEmailActive");
	}

	public void getSmsTemplate(Integer applicationID, Integer smsType) {

		String Message = "";
		String mobnumber = "";

		System.out
				.println(" Application : getSmsTemplate === " + applicationID);

		SmsDataDaoImp smsDataDaoImp = new SmsDataDaoImp();

		List<Application> lst = smsDataDaoImp.getTemplateID(applicationID,
				smsType);
		for (Iterator iterator = lst.iterator(); iterator.hasNext();) {
			// employees.iterator(); iterator.hasNext();){
			HashMap<String, String> smsdtls = new HashMap<String, String>();

			Application app = (Application) iterator.next();
			System.out.print("First Name: " + app.getAppId());
			System.out.print("  Last Name: " + app.getEmailAddr());
			System.out.println("  Salary: " + app.getMobileNum());

			smsdtls.put("ToMobileNo", app.getMobileNum().toString());
			smsdtls.put("TemplateType", "SMS");
			smsdtls.put("Body", "HI");
			smsdtls.put("applicationID", app.getAppId().toString());
			smsdtls.put("EmailId", app.getEmailAddr());

			Message = getMsgBody(smsdtls.get("Body"), smsdtls);
			mobnumber = getMsgBody(smsdtls.get("ToMobileNo"), smsdtls);
String isSMSEmailActive="3";
			try {
				if ("SMS".equalsIgnoreCase("SMS")
						&& smsdtls.get("ToMobileNo") != null
						&& !"".equals(smsdtls.get("ToMobileNo"))) {

					Message = getMsgBody(smsdtls.get("Body"), smsdtls);
					mobnumber = getMsgBody(smsdtls.get("ToMobileNo"), smsdtls);

					String smsURL = SMSUrl + MobileNo + mobnumber + MsgTxt
							+ URLEncoder.encode(Message, "UTF-8");

					URL url = new URL(smsURL);

					if ("1".equals(isSMSEmailActive)) {
						System.out.println("SMS URL :" + smsURL);
						HttpURLConnection connection = (HttpURLConnection) url
								.openConnection();
						connection.connect();
						System.out.println("Response Code: "
								+ connection.getResponseCode());
					} else
						System.out
								.println("SMS Notification is Inactive or NA : "
										+ smsURL);

				}
				String TemplateType = "EMAIL";
				if ("EMAIL".equalsIgnoreCase(TemplateType)
						&& app.getEmailAddr() != null
						&& !"".equals(app.getEmailAddr())
						&& "3".equals(isSMSEmailActive)) {

					Map<String, Object> params = new HashMap<String, Object>();
/*
					String body = EmailTemplateBuilder.getEmailTemplate(
							"ComplaintClosure.ftl", params);*/
					String body = "Welcome to Register Water board New Connection Your Ref No is="+app.getAppId();

					new SendEMailUtil().sendMail(
							app.getEmailAddr(),
							"TN Water New Connection  - Applicationt# "
									+ app.getAppId(), body, "", data);
					System.out.println("Email Sent To : " + app.getEmailAddr());
				} else
					System.out.println("Email Notification is Inactive or NA");

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		/*
		 * for (Object[] templateData :
		 * smsDataDaoImp.getTemplateID(applicationID,smsType)) {/*
		 * 
		 * String ToMobileNo = templateData[0] != null ? templateData[0]
		 * .toString() : ""; String app_id = templateData[1] != null ?
		 * templateData[1] .toString() : ""; String TemplateType =
		 * templateData[2] != null ? templateData[2] .toString() : ""; String
		 * Body = templateData[3] != null ? templateData[3].toString() : "";
		 * 
		 * 
		 * 
		 * String email_addr = templateData[4] != null ? templateData[4]
		 * .toString() : "";
		 * 
		 * 
		 * 
		 * HashMap<String, String> smsdtls = new HashMap<String, String>();
		 * 
		 * smsdtls.put("ToMobileNo", ToMobileNo); smsdtls.put("TemplateType",
		 * TemplateType); smsdtls.put("Body", Body);
		 * smsdtls.put("applicationID", app_id); smsdtls.put("EmailId",
		 * email_addr);
		 * 
		 * 
		 * 
		 * try { if ("SMS".equalsIgnoreCase(TemplateType) &&
		 * smsdtls.get("ToMobileNo") != null &&
		 * !"".equals(smsdtls.get("ToMobileNo"))) {
		 * 
		 * Message = getMsgBody(smsdtls.get("Body"), smsdtls); mobnumber =
		 * getMsgBody(smsdtls.get("ToMobileNo"), smsdtls);
		 * 
		 * String smsURL = SMSUrl + MobileNo + mobnumber + MsgTxt +
		 * URLEncoder.encode(Message, "UTF-8");
		 * 
		 * URL url = new URL(smsURL);
		 * 
		 * if ("1".equals(isSMSEmailActive)) { System.out.println("SMS URL :" +
		 * smsURL); HttpURLConnection connection = (HttpURLConnection) url
		 * .openConnection(); connection.connect();
		 * System.out.println("Response Code: " + connection.getResponseCode());
		 * } else System.out .println("SMS Notification is Inactive or NA : " +
		 * smsURL);
		 * 
		 * } TemplateType="EMAIL"; if ("EMAIL".equalsIgnoreCase(TemplateType) &&
		 * email_addr != null && !"".equals(email_addr) &&
		 * "1".equals(isSMSEmailActive)) {
		 * 
		 * Map<String, Object> params = new HashMap<String, Object>();
		 * 
		 * 
		 * String body = EmailTemplateBuilder.getEmailTemplate(
		 * "ComplaintClosure.ftl", params);
		 * 
		 * new SendEMailUtil().sendMail(email_addr,
		 * "TN Water New Connection  - Applicationt# " + app_id, body, "",
		 * data); System.out.println("Email Sent To : " + email_addr); } else
		 * System.out.println("Email Notification is Inactive or NA");
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 * 
		 * }
		 */

	}

	public String getMsgBody(String message, HashMap<String, String> mapVal) {
		HashSet<String> keys = new HashSet<String>();

		if (message != null && mapVal.size() > 0) {
			char[] text = message.toCharArray();
			for (int i = 0; i < text.length; i++) {
				String key = "";

				int a = i;
				if (text[a] == '{') {
					while (text[a] != '}') {
						if (text[a] != '}' && text[a] != '{') {

							key = key + text[a];
						}

						a++;

					}

					keys.add(key);

				}
				key = "";

			}

		}

		message = getContent(keys, message, mapVal);
		return message;
	}

	public String getContent(HashSet<String> al, String message,
			HashMap<String, String> map) {
		String old = "";
		try {
			if (message != null)
				for (String key : al) {
					System.out.println("Key : " + key + ", map : " + map
							+ " , message : " + message + "\n old :" + old);

					old = "\\{" + key + "\\}";
					message = message.replaceAll(old, map.get(key));
				}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return message;
	}

}
