package com.water.ws;

import java.util.ResourceBundle;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.google.gson.Gson;
import com.water.bean.AppFormBean;
import com.water.dao.ApplicationDao;
import com.water.daoImpl.ApplicationDaoImpl;
import com.water.model.Application;
import com.water.model.MasterCategory;
import com.water.model.MasterReconnection;
import com.water.model.MasterStatus;
import com.water.model.MasterZone;
import com.water.model.SmsTemp;
import com.water.util.HibernateUtil;
import com.water.util.SMSBuilder;

@Path("ApplicationService")
public class ApplicationService {
	ApplicationDao AppDao;
	Gson gson;
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	ResourceBundle rb = ResourceBundle.getBundle("resources/constant");

	String APP_Folder = rb.getString("APP_Folder");

	@POST
	@Path("saveApplication")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String saveApplication(AppFormBean appFormBean) {
		// gson = new Gson();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		AppDao = new ApplicationDaoImpl();
		Application applicationDtls = new Application();
		SmsTemp smsId = new SmsTemp();

		applicationDtls.setAddrPremSought(appFormBean.getAddrPremSought());
		applicationDtls.setAnnAssmtVal(appFormBean.getAnnAssmtVal());
		applicationDtls.setBillNum(appFormBean.getBillNum());
		applicationDtls
				.setContactPersonName(appFormBean.getContactPersonName());
		applicationDtls.setCorrespondenceAddr(appFormBean
				.getCorrespondenceAddr());
		applicationDtls.setDdNum(appFormBean.getDdNum());
		applicationDtls.setDivId(appFormBean.getDivId());
		applicationDtls.setEmailAddr(appFormBean.getEmailAddr());
		applicationDtls.setFromWebSite("Industry");
		applicationDtls.setInsStatusId(0);
		applicationDtls.setIntrPlumStatus(appFormBean.getIntrPlumStatus());
		applicationDtls.setIsExistConnectionForAlteration(appFormBean
				.getIsExistConnectionForAlteration());
		applicationDtls.setIsReconnectionForService(appFormBean
				.getIsReconnectionForService());
		applicationDtls.setLegCompName(appFormBean.getLegCompName());
		applicationDtls.setMobileNum(appFormBean.getMobileNum());
		applicationDtls.setPayDtls(null);
		applicationDtls.setPaymentType(appFormBean.getPaymentType());
		applicationDtls.setReqMld(appFormBean.getReqMld());
		applicationDtls.setStatusFlag('Y');
		applicationDtls.setUserId(null);
		applicationDtls.setWatSevProp(appFormBean.getWatSevProp());
		applicationDtls.setWorkType(appFormBean.getWorkType());
		applicationDtls.setSmsId(smsId);

		applicationDtls.setCategoryType((MasterCategory) session.get(
				MasterCategory.class, appFormBean.getCategoryType()));
		if (appFormBean.getReconnectionType() != null
				&& !appFormBean.getReconnectionType().equals("")) {
			applicationDtls.setReconnectionType((MasterReconnection) session
					.get(MasterReconnection.class,
							appFormBean.getReconnectionType()));
		}
		applicationDtls.setCeStatus((MasterStatus) session.get(
				MasterStatus.class, 1));
		applicationDtls.setEeStatus((MasterStatus) session.get(
				MasterStatus.class, 1));
		applicationDtls.setMcStatus((MasterStatus) session.get(
				MasterStatus.class, 1));
		if (appFormBean.getCmwssbZoneNum() != null
				&& !appFormBean.getCmwssbZoneNum().equals("")) {
			applicationDtls.setCmwssbZoneNum((MasterZone) session.get(
					MasterZone.class, appFormBean.getCmwssbZoneNum()));
		}

		session.save(applicationDtls);
		session.beginTransaction().commit();

		final Integer smsType = 1;
		final Integer application_ID = applicationDtls.getAppId();
		Thread notify = new Thread(new Runnable() {
			@Override
			public void run() {
				SMSBuilder obj = new SMSBuilder();
				obj.getSmsTemplate(application_ID, smsType);
			}
		}, "notify");
		notify.start();

		return String.valueOf(applicationDtls.getAppId());

	}

}
