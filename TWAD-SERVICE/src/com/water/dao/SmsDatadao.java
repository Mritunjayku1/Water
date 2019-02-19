package com.water.dao;

import java.util.List;

import com.water.bean.SmsDatas;
import com.water.model.Application;
import com.water.model.CompanyDtl;

public interface SmsDatadao {

	public String smsData(String sender, String message);

	public SmsDatas receiveSMs(String sender, String message);

	List<CompanyDtl> getTemplateID(String applicationId,Integer applicationID);
}
