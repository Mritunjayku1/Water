package com.water.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;

import com.water.bean.SmsDatas;
import com.water.bean.SmsBean;
import com.water.dao.SmsDatadao;
import com.water.model.Application;
import com.water.model.TblSMSDatas;
import com.water.util.Constant;
import com.water.util.HibernateUtil;

public class SmsDataDaoImp implements SmsDatadao {

	SessionFactory sessionfact = HibernateUtil.getSessionFactory();

	@Override
	public String smsData(String sender, String message) {

		System.out.println("Tracing " + sender + "message:" + message);
		TblSMSDatas tblsmsdata = new TblSMSDatas();
		Session session = sessionfact.openSession();
		Criteria crit = session.createCriteria(TblSMSDatas.class);
		tblsmsdata.setsMSSenterNumber(sender);
		tblsmsdata.setsMSContent(message);
		// tblsmsdata.setCreatedDate(date.getDate());
		tblsmsdata.setEmailID("maha@gmail.com");
		session.save(tblsmsdata);
		session.beginTransaction().commit();

		// TODO Auto-generated method stub

		return "Message Sent Successfully";
	}

	@Override
	public SmsDatas receiveSMs(String sender, String message) {
		// TODO Auto-generated method stub
		TblSMSDatas tblsmsdata = new TblSMSDatas();
		SmsDatas smsdata = new SmsDatas();
		Session session = sessionfact.openSession();
		Criteria crit = session.createCriteria(TblSMSDatas.class);
		tblsmsdata.setsMSSenterNumber(sender);
		tblsmsdata.setsMSContent(message);
		// tblsmsdata.setCreatedDate(date.getDate());
		tblsmsdata.setEmailID("maha@gmail.com");
		session.save(tblsmsdata);
		session.beginTransaction().commit();
		
		smsdata.setsMSSenterNumber(tblsmsdata.getsMSSenterNumber());
		smsdata.setsMSContent(tblsmsdata.getsMSContent());
		System.out.println("Number in Service :" + smsdata.getsMSSenterNumber()
				+ "  Message  : " + smsdata.getsMSContent());

		return smsdata;
	}

	@Override
	public List<Application> getTemplateID(String applicationId, Integer smsType) {
		Session session = sessionfact.openSession();
		List<Application> SmsTemplate = new ArrayList<Application>();
		Criteria cr = session.createCriteria(Application.class);
		cr.add(Restrictions.eq("appId", applicationId));
		SmsTemplate = cr.list();
		return SmsTemplate;
	}
}
