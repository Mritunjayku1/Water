package com.water.daoImpl;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.water.bean.AppRegBean;
import com.water.bean.ComplaintBean;
import com.water.dao.ApplicationDao;
import com.water.model.Application;
import com.water.model.ComplaintDetails;
import com.water.util.HibernateUtil;
import com.water.util.Constant;
import com.water.util.SMSBuilder;

public class ApplicationDaoImpl implements ApplicationDao {
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public Application appRegister(AppRegBean appRegBean) {
		Session session = sessionFactory.openSession();

		Application applicationDtls = null;
		try {
			if (appRegBean.getApplicatioEscalated() == null
					|| "".equals(appRegBean.getApplicatioEscalated())) {
				appRegBean.setApplicatioEscalated(true);
			} else {
				appRegBean.setApplicatioEscalated(true);
			}
			
			if (appRegBean.getAppId() != null
					&& appRegBean.getAppId()> 0) {
	
				session.beginTransaction();
				applicationDtls = new Application();
				if(appRegBean.getAppStatus().equals(Constant.smsProcessingFee))
				{
					applicationDtls = new Application();
					SQLQuery query = session
							.createSQLQuery("");
					query.setParameter(0, 2);

					query.executeUpdate();
					session.beginTransaction().commit();
					final Integer app_Id = appRegBean.getAppId();
					Thread notify = new Thread(new Runnable() {
						@Override
						public void run() {
							SMSBuilder obj = new SMSBuilder();
							//obj.getSmsTemplate(app_Id);
						}
					}, "notify");
					notify.start();
					
				}
			}

		} catch (Exception e) {

		}
		return applicationDtls;
	}
	
	public Application saveApplication(Application application) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(application);
		session.beginTransaction().commit();
		return null;
	}
	
}
