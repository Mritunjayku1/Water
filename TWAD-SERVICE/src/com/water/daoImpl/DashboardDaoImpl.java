package com.water.daoImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.JoinType;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.water.bean.ApplicationBean;
import com.water.bean.CategoryFormBean;
import com.water.bean.CmwWaterConnBean;
import com.water.bean.CompanyDtlBean;
import com.water.bean.ConnectionFormBean;
import com.water.bean.DDPaymentFormBean;
import com.water.bean.DashboardCountBean;
import com.water.bean.DistrictFormBean;
import com.water.bean.DistrictTalukFormBean;
import com.water.bean.EmployeeFormBean;
import com.water.bean.OfficeFormBean;
import com.water.bean.PaymentFormBean;
import com.water.bean.TalukVillageFormBean;
import com.water.bean.ZoneDivisionFormBean;
import com.water.dao.DashboardDao;
import com.water.model.Application;
import com.water.model.CompanyDtl;
import com.water.model.CompanyPaymentDtl;
import com.water.model.EmployeeDetails;
import com.water.model.MasterCategory;
import com.water.model.MasterDistrict;
import com.water.model.MasterDivision;
import com.water.model.MasterOffice;
import com.water.model.MasterPayment;
import com.water.model.MasterPaymentType;
import com.water.model.MasterReconnection;
import com.water.model.MasterRole;
import com.water.model.MasterStatus;
import com.water.model.MasterTaluk;
import com.water.model.MasterVillage;
import com.water.model.MasterZone;
import com.water.model.SmsTemp;
import com.water.util.Constant;
import com.water.util.HibernateUtil;
import com.water.util.SMSBuilder;

public class DashboardDaoImpl implements DashboardDao {
	
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	private SessionFactory oraclesessionfactory = HibernateUtil.getOraclesessionfactory();
	
	@Override
	public Application getApplicationDtls(ApplicationBean appFormBean) {
		// TODO Auto-generated method stub

		Application appDetails = new Application();
		Session session = sessionFactory.openSession();
		try {
			Criteria cr = session.createCriteria(Application.class,"app");
			if(appFormBean.getCmwssbZoneNum() != null &&  !appFormBean.getCmwssbZoneNum().equals(""))
			cr.createCriteria("app.cmwssbZoneNum" , "zone");
			if(appFormBean.getIsReconnectionForService() != null &&  !appFormBean.getIsReconnectionForService().equals("NO"))
			cr.createCriteria("app.reconnectionType","reconnection");
			cr.add(Restrictions.eq("app.appId",appFormBean.getAppId()));
			appDetails = (Application) cr.uniqueResult();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return appDetails;
	}
	@Override
	public Application getApplicationDetails(ApplicationBean appFormBean) {
		// TODO Auto-generated method stub

		Application appDetails = new Application();
		Session session = sessionFactory.openSession();
		try {
			appDetails = (Application)session.get(Application.class, appFormBean.getAppId());
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return appDetails;
	}
	
	
	@Override
	public List<Application> listCePendingApplicationDtls() {
		// TODO Auto-generated method stub

		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<Application> appDetails = new ArrayList<Application>();
		try {
			

			Criteria cr = session.createCriteria(Application.class,"app")
					.createCriteria("app.eeStatus","status")
			         .add(Restrictions.eq("status.statusId", 1))
					.add(Restrictions.eq("app.statusFlag", 'Y')).add(Restrictions.isNotNull("app.paidProcessFee")).add(Restrictions.eq("app.insStatusId",1 ));
			
			appDetails=cr.list();
			
			
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return appDetails;
	}
	
	@Override
	public List<Application> listEePaymentPendingApplicationDtls() {
		// TODO Auto-generated method stub

		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<Application> appDetails = new ArrayList<Application>();
		try {
			

			Criteria cr = session.createCriteria(Application.class,"app")
					.createCriteria("app.mcStatus","status")
			         .add(Restrictions.eq("status.statusId", 3))
					.add(Restrictions.eq("app.statusFlag", 'Y'))					
					.add(Restrictions.isNull("app.paidfinalFee"))
					.add(Restrictions.eq("app.insStatusId",3));
			
			
			
		/*	Criteria cr = session.createCriteria(Application.class,"app")
			.createCriteria("app.eeStatus","status")
	         .add(Restrictions.eq("status.statusId", 1))
			.add(Restrictions.eq("app.statusFlag", 'Y'))
					.add(Restrictions.eq("app.insStatusId",1 )
					*/
					
			
			appDetails=cr.list();
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return appDetails;
	}
	
	
	
	@Override
	public List<Application> listTrackApplication() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<Application> appDetails = new ArrayList<Application>();
		try {
			
			
			
			Criteria cr = session.createCriteria(Application.class,"app")
					.createCriteria("app.mcStatus","status")
			         .add(Restrictions.eq("status.statusId", 3))
					.add(Restrictions.eq("app.statusFlag", 'Y'))
							.add(Restrictions.eq("app.insStatusId",3 )
							
					
					);
			
			appDetails=cr.list();
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return appDetails;
	}
	@Override
	public List<Application> listMcPendingApplicationDtls() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<Application> appDetails = new ArrayList<Application>();
		try {
			
		
			
			Criteria cr = session.createCriteria(Application.class,"app")
					.createCriteria("app.mcStatus","status")
			         .add(Restrictions.eq("status.statusId", 2))
					.add(Restrictions.eq("app.statusFlag", 'Y'))
							.add(Restrictions.eq("app.insStatusId",3)
							
					
					);
			
			appDetails=cr.list();
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return appDetails;
	}
	
	
	@Override
	public List<Application> listViewAllApplication() {
		// TODO Auto-generated method stub

		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<Application> appDetails = new ArrayList<Application>();
		try {
		
			Criteria cr = session.createCriteria(Application.class,"app").
			//.createCriteria("app.eeStatus","status");
			add(Restrictions.ne("app.statusFlag", 'E')).
			add(Restrictions.ne("app.statusFlag", 'F'));
			//add(Restrictions.eq("app.statusFlag", 'Y')).add(Restrictions.eq("app.statusFlag", 'P')).add(Restrictions.eq("app.statusFlag", 'R')).add(Restrictions.eq("app.statusFlag", 'C'));
			
			appDetails=cr.list();
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return appDetails;
	}
	@Override
	public List<Application> listEeApprovedApplicationDtls() {
		// TODO Auto-generated method stub

		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<Application> appDetails = new ArrayList<Application>();
		try {
		
			Criteria cr = session.createCriteria(Application.class,"app")
			.createCriteria("app.eeStatus","status");
			cr.add(Restrictions.eq("status.statusId", 3));			
			cr.add(Restrictions.eq("app.statusFlag", 'Y')).add(Restrictions.eq("app.insStatusId",3));
			
			appDetails=cr.list();
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return appDetails;
	}
	
	@Override
	public List<Application> listMcApprovedApplicationDtls() {
		// TODO Auto-generated method stub

		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<Application> appDetails = new ArrayList<Application>();
		try {
		
			Criteria cr = session.createCriteria(Application.class,"app")
			.createCriteria("app.mcStatus","status");
			cr.add(Restrictions.eq("status.statusId", 3));			
			cr.add(Restrictions.eq("app.statusFlag", 'Y'));
			
			appDetails=cr.list();
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return appDetails;
	}
	
	@Override
	public List<Application> listceApprovedApplicationDtls() {
		// TODO Auto-generated method stub

		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<Application> appDetails = new ArrayList<Application>();
		try {
		
			Criteria cr = session.createCriteria(Application.class,"app")
			.createCriteria("app.mcStatus","status");
			cr.add(Restrictions.eq("status.statusId", 3));
			cr.add(Restrictions.eq("app.statusFlag", 'Y'));
			
			appDetails=cr.list();
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return appDetails;
	}
	/*@Override
	public List<Application> listBeforeInspectionolds() {
		// TODO Auto-generated method stub

		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<Application> appDetails = new ArrayList<Application>();
		try {
		
			
			
			Criteria cr = session.createCriteria(Application.class,"app")
					.createCriteria("app.eeStatus","status")
			         .add(Restrictions.eq("status.statusId", 1))
					.add(Restrictions.eq("app.statusFlag", 'Y')).add(Restrictions.isNotNull("app.paidProcessFee")).add(Restrictions.eq("app.insStatusId",1 ));
			
			appDetails=cr.list();
			
			

		
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return appDetails;
	}*/
	//@Override
	public String SendInspectionDateOLD(ApplicationBean applicationBean) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Application applicationmodel = (Application) session.get(
				Application.class, applicationBean.getAppId());
		//applicationmodel.setUserId((EmployeeDetails) session.get(EmployeeDetails.class, Integer.parseInt(applicationBean.getUserId())));
		
		applicationmodel.setInsStatusId(2);
		applicationmodel.setEeStatus((MasterStatus) session.get(
				MasterStatus.class, 2));
		applicationmodel.setSmsId((SmsTemp)session.get(SmsTemp.class, 2));//Inspection.ftl
		applicationmodel.setInspectionDate(applicationBean.getInspectionDate());
		session.update(applicationmodel);
		session.beginTransaction().commit();
		
		
		final Integer smsType=3;
		final Integer emailType=3;
		final String status="EE%20SCHEDULED%20INSPECTION%20DATE%20"+applicationBean.getInspectionDate();
		
		final String smsTemp="App%20No%20"+applicationBean.getAppId()+"%20CMWSSB%20Officials%20will%20be%20inspecting%20your%20site%20on%20"+applicationBean.getInspectionDate();
		final String application_ID = applicationBean.getAppId();
		Thread notify = new Thread(new Runnable() {
			@Override
			public void run() {
				SMSBuilder obj = new SMSBuilder();
				obj.getSmsTemplate(application_ID,smsType,smsTemp);
				obj.getStatus(application_ID, status);
			}
		}, "notify");
		notify.start();
		

		session.close();
		return "Success";
	}
	public String SendInspectionDate(CompanyDtlBean applicationBean) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		/*Application applicationmodel = (Application) session.get(
				Application.class, applicationBean.getAppId());*/
		CompanyDtl applicationmodel = (CompanyDtl) session.get(
				CompanyDtl.class, applicationBean.getAppId());
		//applicationmodel.setUserId((EmployeeDetails) session.get(EmployeeDetails.class, Integer.parseInt(applicationBean.getUserId())));
		
		applicationmodel.setInsStatusId(2);
		applicationmodel.setEeStatus((MasterStatus) session.get(
				MasterStatus.class, 2));
		applicationmodel.setSmsId((SmsTemp)session.get(SmsTemp.class, 2));//Inspection.ftl
		applicationmodel.setInspectionDate(applicationBean.getInspectionDate());
		session.update(applicationmodel);
		session.beginTransaction().commit();
		
		
		final Integer smsType=3;
		final Integer emailType=3;
		final String status="EE%20SCHEDULED%20INSPECTION%20DATE%20"+applicationBean.getInspectionDate();
		
		final String smsTemp="App%20No%20"+applicationBean.getAppId()+"%20CMWSSB%20Officials%20will%20be%20inspecting%20your%20site%20on%20"+applicationBean.getInspectionDate();
		final String application_ID = applicationBean.getAppId();
		Thread notify = new Thread(new Runnable() {
			@Override
			public void run() {
				SMSBuilder obj = new SMSBuilder();
				obj.getSmsTemplate(application_ID,smsType,smsTemp);
				obj.getStatus(application_ID, status);
			}
		}, "notify");
		notify.start();
		

		session.close();
		return "Success";
	}
	@Override
	public List<CompanyDtl> listAfterInspection() {
		// TODO Auto-generated method stub

		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<CompanyDtl> appDetails = new ArrayList<CompanyDtl>();
		try {
			Criteria cr = session.createCriteria(CompanyDtl.class,"app")
					.createCriteria("app.eeStatus","status")
			      .add(Restrictions.eq("status.statusId", 2))
					.add(Restrictions.eq("app.statusFlag", 'Y')).add(Restrictions.eq("app.insStatusId",2));
			
			/*Criteria cr = session.createCriteria(Application.class,"app")
			.createCriteria("app.eeStatus","status");
			cr.add(Restrictions.eq("status.statusId", 1));
			cr.add(Restrictions.eq("app.statusFlag", 'Y'))
			.add(Restrictions.eq("app.insStatusId",2 ));*/
			//.add(Restrictions.eq("app.fixedFinalFee",null ));
			
			// future added payment received only show the data.
			
			appDetails=cr.list();
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return appDetails;
	}
	@Override
	public List<CompanyDtl> listEstimate() {
		// TODO Auto-generated method stub

		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<CompanyDtl> appDetails = new ArrayList<CompanyDtl>();
		try {
			Criteria cr = session.createCriteria(CompanyDtl.class,"app")
					.createCriteria("app.eeStatus","status")
			      .add(Restrictions.eq("status.statusId", 2))
					.add(Restrictions.eq("app.statusFlag", 'E')).add(Restrictions.eq("app.insStatusId",2));
			
			/*Criteria cr = session.createCriteria(Application.class,"app")
			.createCriteria("app.eeStatus","status");
			cr.add(Restrictions.eq("status.statusId", 1));
			cr.add(Restrictions.eq("app.statusFlag", 'Y'))
			.add(Restrictions.eq("app.insStatusId",2 ));*/
			//.add(Restrictions.eq("app.fixedFinalFee",null ));
			
			// future added payment received only show the data.
			
			appDetails=cr.list();
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return appDetails;
	}
	@Override
	public List<CompanyDtl> listMOU() {
		// TODO Auto-generated method stub

		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<CompanyDtl> appDetails = new ArrayList<CompanyDtl>();
		try {
			Criteria cr = session.createCriteria(CompanyDtl.class,"app")
					.createCriteria("app.eeStatus","status")
			      .add(Restrictions.eq("status.statusId", 2))
					.add(Restrictions.eq("app.statusFlag", 'E')).add(Restrictions.eq("app.insStatusId",2));
			
			/*Criteria cr = session.createCriteria(Application.class,"app")
			.createCriteria("app.eeStatus","status");
			cr.add(Restrictions.eq("status.statusId", 1));
			cr.add(Restrictions.eq("app.statusFlag", 'Y'))
			.add(Restrictions.eq("app.insStatusId",2 ));*/
			//.add(Restrictions.eq("app.fixedFinalFee",null ));
			
			// future added payment received only show the data.
			
			appDetails=cr.list();
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return appDetails;
	}
	@Override
	public List<Application> listProsFeePendingPayment(){
		// TODO Auto-generated method stub

		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<Application> appDetails = new ArrayList<Application>();
		try {
		
			Criteria cr = session.createCriteria(Application.class,"appf")
			.createCriteria("appf.eeStatus","status").
			//cr.add(Restrictions.eq("status.statusId", 3)).
			add(Restrictions.eq("appf.statusFlag", 'Y'))
			//.add(Restrictions.eq("appf.insStatusId",3 ))
			.add(Restrictions.isNull("appf.paidProcessFee"))
			.add(Restrictions.ne("appf.fixedProcessFee",new Integer(24)));
			//.add(Restrictions.isNull("appf.paidfinalFee"));//,new Integer(24)));
			
			// future added payment received only show the data.
		
			appDetails=cr.list();
			
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return appDetails;

	}
	
	public String SendOracleDbwork(CmwWaterConnBean cmwWaterConnBean) {
		// TODO Auto-generated method stub

		Session session = oraclesessionfactory.openSession();
		Session sessionpost = sessionFactory.openSession();
		
		/*Application applicationmodel = (Application) sessionpost.get(Application.class, oracleDbBean.getAppId());
		oracleDbBean.setAddrPremSought(applicationmodel.getAddrPremSought());
		oracleDbBean.setLegCompName(applicationmodel.getLegCompName());
		oracleDbBean.setMobileNum(applicationmodel.getMobileNum().toString());*/
		Application applicationmodel = (Application) sessionpost.get(Application.class, cmwWaterConnBean.getChALLAN_NO());
		/*cmwWaterConnBean.setNAME(applicationmodel.getContactPersonName());
		cmwWaterConnBean.setDOOR_NO(applicationmodel.getDoorNo());
		cmwWaterConnBean.setPLOT_NO(applicationmodel.getPlotNo());
		cmwWaterConnBean.setSTREET_NAME(applicationmodel.getStreetName());
		cmwWaterConnBean.setLOCATION(applicationmodel.getLocation());
		cmwWaterConnBean.setPIN_CODE(applicationmodel.getPinCode());
		cmwWaterConnBean.setNAREA((int)session.get(MasterZone.class, applicationmodel.getCmwssbZoneNum()));
		cmwWaterConnBean.setDIVISION_NO((int)session.get(MasterZone.class, applicationmodel.getDivId()));*/
		sessionpost.close();
		session.beginTransaction();
		
		//Application applicationmodels = (Application) session.get(Application.class, cmwWaterConnBean.getChALLAN_NO());
		//applicationmodel.setUserId((EmployeeDetails) session.get(EmployeeDetails.class, Integer.parseInt(applicationBean.getUserId())));
		
		/*applicationmodel.setInsStatusId(3);
		applicationmodel.setSmsId((SmsTemp)session.get(SmsTemp.class, 6));// CompletionConnection.ftl
		
		applicationmodel.setCeStatus((MasterStatus) session.get(MasterStatus.class, 3));// ce Approved.
		applicationmodel.setEeStatus((MasterStatus) session.get(MasterStatus.class, 3));// ce Approved.
		applicationmodel.setMcStatus((MasterStatus) session.get(MasterStatus.class, 3));// EE Approved.
*/		
		//applicationmodel.setFixedFinalFee(Integer.parseInt(applicationBean.getEstimationCost()));
		//applicationmodel.setTentativeDate(applicationBean.getTentativeDate());
		
		/*cmwWaterConnBean.setChALLAN_NO(cmwWaterConnBean.getChALLAN_NO());
		cmwWaterConnBean.setADDITIONAL_AMT(Integer.parseInt(applicationmodel.getPaidProcessFee()));
		cmwWaterConnBean.setCHALLAN_AMT(applicationmodel.getPaidfinalFee());*/
		//cmwWaterConnBean.setSeWER_CHALLAN("test");
		
		
		session.save(cmwWaterConnBean);
		/*session.update(applicationmodel);*/
		session.beginTransaction().commit();
		/*
		
		final Integer smsType=3;
		final Integer emailType=3;
		final String smsTemp="App%20No%20"+applicationBean.getAppId()+"%20You%20are%20requested%20to%20pay%20the%20estimated%20cost%20of%20Rs%20"+applicationBean.getEstimationCost()+"%20at%20your%20earliest.";
		//final String smsTemp="";
		final String application_ID = applicationBean.getAppId();
		Thread notify = new Thread(new Runnable() {
			@Override
			public void run() {
				SMSBuilder obj = new SMSBuilder();
				obj.getSmsTemplate(application_ID,smsType,smsTemp);
			}
		}, "notify");
		notify.start();
		*/

		session.close();
		return "Successfully Saved !!";
	}
	public String SendOracleDb(CmwWaterConnBean cmwWaterConnBean) {
		// TODO Auto-generated method stub

		Session sessionoracle = oraclesessionfactory.openSession();
		Session sessionpost = sessionFactory.openSession();
		
		
		
		
		Application applicationmodel = (Application) sessionpost.get(
				Application.class, cmwWaterConnBean.getChALLAN_NO());
		
		applicationmodel.setSmsId((SmsTemp)sessionpost.get(SmsTemp.class, 6));// CompletionConnection.ftl
		
		/*applicationmodel.setCeStatus((MasterStatus) sessionpost.get(MasterStatus.class, 3));// ce Approved.
		applicationmodel.setEeStatus((MasterStatus) sessionpost.get(MasterStatus.class, 3));// ce Approved.
		applicationmodel.setMcStatus((MasterStatus) sessionpost.get(MasterStatus.class, ));// EE Approved.
*/		/*.add(Restrictions.eq("appf.statusFlag", 'P'))
		.add(Restrictions.eq("appf.insStatusId",4 ));*/
		applicationmodel.setStatusFlag('C');
		sessionpost.beginTransaction();
		sessionpost.update(applicationmodel);
		sessionpost.beginTransaction().commit();
		
		
		
		final String status="Connection%20Completed%20Successfully";
		final Integer smsType=3;
		final Integer emailType=3;
		final String smsTemp="App%20No%20"+cmwWaterConnBean.getChALLAN_NO()+"%20Connection%20Completed%20Successfully";
		//final String smsTemp="";
		final String application_ID = cmwWaterConnBean.getChALLAN_NO();
		Thread notify = new Thread(new Runnable() {
			@Override
			public void run() {
				SMSBuilder obj = new SMSBuilder();
				obj.getSmsTemplate(application_ID,smsType,smsTemp);
				obj.getStatus(application_ID, status);
			}
		}, "notify");
		notify.start();
		
		
		sessionoracle.beginTransaction();
		
		sessionoracle.save(cmwWaterConnBean);
		/*session.update(applicationmodel);*/
		sessionoracle.beginTransaction().commit();
		
		
		
		
		
		
		
		sessionpost.close();
		return "Successfully Saved In Oracle Database!!";
	}
	
	public String SendOracleDb2121(CmwWaterConnBean cmwWaterConnBean) {
		// TODO Auto-generated method stub

		Session session = oraclesessionfactory.openSession();
		Session sessionpost = sessionFactory.openSession();
		
		/*Application applicationmodel = (Application) sessionpost.get(Application.class, oracleDbBean.getAppId());
		oracleDbBean.setAddrPremSought(applicationmodel.getAddrPremSought());
		oracleDbBean.setLegCompName(applicationmodel.getLegCompName());
		oracleDbBean.setMobileNum(applicationmodel.getMobileNum().toString());*/
		/*Application applicationmodel = (Application) sessionpost.get(Application.class, cmwWaterConnBean.getCHALLAN_NO());
		cmwWaterConnBean.setNAME(applicationmodel.getContactPersonName());
		cmwWaterConnBean.setDOOR_NO(applicationmodel.getDoorNo());
		cmwWaterConnBean.setPLOT_NO(applicationmodel.getPlotNo());
		cmwWaterConnBean.setSTREET_NAME(applicationmodel.getStreetName());
		cmwWaterConnBean.setLOCATION(applicationmodel.getLocation());
		cmwWaterConnBean.setPIN_CODE(applicationmodel.getPinCode());
		cmwWaterConnBean.setNAREA((int)session.get(MasterZone.class, applicationmodel.getCmwssbZoneNum()));
		cmwWaterConnBean.setDIVISION_NO((int)session.get(MasterZone.class, applicationmodel.getDivId()));
		sessionpost.close();
		session.beginTransaction();*/
		/*Application applicationmodel = (Application) session.get(
				Application.class, oracleDbBean.getAppId());
		applicationmodel.setUserId((EmployeeDetails) session.get(EmployeeDetails.class, Integer.parseInt(applicationBean.getUserId())));
		
		applicationmodel.setInsStatusId(3);
		applicationmodel.setSmsId((SmsTemp)session.get(SmsTemp.class, 4));// estimationcost.ftl
		
		applicationmodel.setCeStatus((MasterStatus) session.get(MasterStatus.class, 3));// ce Approved.
		applicationmodel.setEeStatus((MasterStatus) session.get(MasterStatus.class, 3));// ce Approved.
		applicationmodel.setMcStatus((MasterStatus) session.get(MasterStatus.class, 2));// EE Approved.
		
		applicationmodel.setFixedFinalFee(Integer.parseInt(applicationBean.getEstimationCost()));
		applicationmodel.setTentativeDate(applicationBean.getTentativeDate());
		*/

		//cmwWaterConnBean.setChALLAN_NO("APlicationId");
		//cmwWaterConnBean.setSeWER_CHALLAN("test");
		
		
		session.save(cmwWaterConnBean);
		/*session.update(applicationmodel);*/
		session.beginTransaction().commit();
		/*
		
		final Integer smsType=3;
		final Integer emailType=3;
		final String smsTemp="App%20No%20"+applicationBean.getAppId()+"%20You%20are%20requested%20to%20pay%20the%20estimated%20cost%20of%20Rs%20"+applicationBean.getEstimationCost()+"%20at%20your%20earliest.";
		//final String smsTemp="";
		final String application_ID = applicationBean.getAppId();
		Thread notify = new Thread(new Runnable() {
			@Override
			public void run() {
				SMSBuilder obj = new SMSBuilder();
				obj.getSmsTemplate(application_ID,smsType,smsTemp);
			}
		}, "notify");
		notify.start();
		*/

		session.close();
		return "Successfully Saved !!";
	}
	public String SendEstimationCost(ApplicationBean applicationBean) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		Transaction tx= session.beginTransaction();
		Application applicationmodel = (Application) session.get(
				Application.class, applicationBean.getAppId());
		//applicationmodel.setUserId((EmployeeDetails) session.get(EmployeeDetails.class, Integer.parseInt(applicationBean.getUserId())));
		
		applicationmodel.setInsStatusId(3);
		applicationmodel.setSmsId((SmsTemp)session.get(SmsTemp.class, 4));// estimationcost.ftl
		
		applicationmodel.setCeStatus((MasterStatus) session.get(MasterStatus.class, 3));// ce Approved.
		applicationmodel.setEeStatus((MasterStatus) session.get(MasterStatus.class, 3));// ce Approved.
		applicationmodel.setMcStatus((MasterStatus) session.get(MasterStatus.class, 2));// EE Approved.
		
		applicationmodel.setFixedFinalFee(Integer.parseInt(applicationBean.getTotalAmount()));
		applicationmodel.setTentativeDate(applicationBean.getTentativeDate());
		applicationmodel.setGstAmount(Integer.parseInt(applicationBean.getGstAmount()));
		applicationmodel.setTotalAmount(Integer.parseInt(applicationBean.getTotalAmount()));
		applicationmodel.setPaymentAmount(Integer.parseInt(applicationBean.getPaymentAmount()));
		
		session.update(applicationmodel);

		tx.commit();
	final String status="EE%20APPROVED";
		
		final Integer smsType=3;
		final Integer emailType=3;
		final String smsTemp="App%20No%20"+applicationBean.getAppId()+"%20You%20are%20requested%20to%20pay%20the%20estimated%20cost%20of%20Rs%20"+applicationBean.getEstimationCost()+"%20at%20your%20earliest.";
		//final String smsTemp="";
		final String application_ID = applicationBean.getAppId();
		Thread notify = new Thread(new Runnable() {
			@Override
			public void run() {
				SMSBuilder obj = new SMSBuilder();
				obj.getSmsTemplate(application_ID,smsType,smsTemp);
				obj.getStatus(application_ID, status);
			}
		}, "notify");
		notify.start();
		

		session.close();
		return "Successfully Saved !!";
	}
	
	@Override
	public List<Application> listConPendingPayment() {
		// TODO Auto-generated method stub

		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<Application> appDetails = new ArrayList<Application>();
		try {
		
			Criteria cr = session.createCriteria(Application.class,"appf")
			.createCriteria("appf.eeStatus","status");
			cr.add(Restrictions.eq("status.statusId", 3)).
			add(Restrictions.eq("appf.statusFlag", 'Y'))
			.add(Restrictions.eq("appf.insStatusId",3 ))
			//.add(Restrictions.ne("appf.paidProcessFee",new Integer(24)))
			//.add(Restrictions.ne("appf.fixedFinalFee",new Integer(24)))
			.add(Restrictions.isNull("appf.paidfinalFee"));//,new Integer(24)));
			
			// future added payment received only show the data.
			
			appDetails=cr.list();
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return appDetails;
	}
	@Override
	public List<Application> listInitialPendingPayment() {
		// TODO Auto-generated method stub

		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<Application> appDetails = new ArrayList<Application>();
		try {
		
			Criteria cr = session.createCriteria(Application.class,"appf")
			.createCriteria("appf.eeStatus","status").
			//cr.add(Restrictions.eq("status.statusId", 3)).
			add(Restrictions.eq("appf.statusFlag", 'Y'))
			//.add(Restrictions.eq("appf.insStatusId",3 ))
			.add(Restrictions.isNull("appf.paidProcessFee"))
			.add(Restrictions.ne("appf.fixedProcessFee",new Integer(24)));
			//.add(Restrictions.isNull("appf.paidfinalFee"));//,new Integer(24)));
			
			// future added payment received only show the data.
		
			appDetails=cr.list();
			
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return appDetails;

	}
	@Override
	public List<Application> listConPaidDtls() {
		// TODO Auto-generated method stub

		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<Application> appDetails = new ArrayList<Application>();
		try {
		
			Criteria cr = session.createCriteria(Application.class,"appf")
			//.createCriteria("appf.mcStatus","status").
			//add(Restrictions.eq("status.statusId", 4)).
			.add(Restrictions.eq("appf.statusFlag", 'P'))
			.add(Restrictions.eq("appf.insStatusId",4 ));
		//	.add(Restrictions.ne("appf.paidfinalFee",new Integer(24) ));
		//	.add(Restrictions.isNotNull("appf.paidfinalFee"));
			// future added payment received only show the data.
			
			
			
			appDetails=cr.list();
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return appDetails;
	}
	
	
	
	
	
	@Override
	public String ceApproved(ApplicationBean applicationBean) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Application applicationmodel = (Application) session.get(
				Application.class, applicationBean.getAppId());
		applicationmodel.setUserId((EmployeeDetails) session.get(EmployeeDetails.class, Integer.parseInt(applicationBean.getUserId())));
		applicationmodel.setCeStatus((MasterStatus) session.get(MasterStatus.class, 3));// 3
		applicationmodel.setEeStatus((MasterStatus) session.get(MasterStatus.class, 2));// 2
		applicationmodel.setSmsId((SmsTemp)session.get(SmsTemp.class, 2));
		applicationmodel.setRemarks(applicationBean.getRemarks());
		
		// remarks addeds later
		session.update(applicationmodel);
		session.beginTransaction().commit();
		
		
		final Integer smsType=2;
		final Integer emailType=2;
		
		final String smsTemp="";final String application_ID = applicationBean.getAppId();
		Thread notify = new Thread(new Runnable() {
			@Override
			public void run() {
				SMSBuilder obj = new SMSBuilder();
				obj.getSmsTemplate(application_ID,smsType,smsTemp);
			}
		}, "notify");
		notify.start();
		

		session.close();
		return "Success";
	}
	
	
	@Override
	public String  eeFinalApproved(ApplicationBean applicationBean) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Application applicationmodel = (Application) session.get(
				Application.class, applicationBean.getAppId());
		applicationmodel.setUserId((EmployeeDetails) session.get(EmployeeDetails.class, Integer.parseInt(applicationBean.getUserId())));
		
		applicationmodel.setMcStatus((MasterStatus) session.get(MasterStatus.class, 2));// 3
		applicationmodel.setEeStatus((MasterStatus) session.get(MasterStatus.class, 3));// 2
		applicationmodel.setSmsId((SmsTemp)session.get(SmsTemp.class, 6));// MC Status
		applicationmodel.setRemarks(applicationBean.getRemarks());
		// remarks addeds later
		session.update(applicationmodel);
		session.beginTransaction().commit();
		
		
		final Integer smsType=2;
		final Integer emailType=2;
		final String status="EE%20FINAL%20APPROVED";
		
		final String smsTemp="";final String application_ID = applicationBean.getAppId();
		Thread notify = new Thread(new Runnable() {
			@Override
			public void run() {
				SMSBuilder obj = new SMSBuilder();
				obj.getSmsTemplate(application_ID,smsType,smsTemp);
				obj.getStatus(application_ID, status);
			}
		}, "notify");
		notify.start();
		

		session.close();
		return "Success";
	}
	
	
	@Override
	public String  eeWidthdraw(ApplicationBean applicationBean) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Application applicationmodel = (Application) session.get(
				Application.class, applicationBean.getAppId());
		applicationmodel.setUserId((EmployeeDetails) session.get(EmployeeDetails.class, Integer.parseInt(applicationBean.getUserId())));
		
	applicationmodel.setCeStatus((MasterStatus) session.get(MasterStatus.class, 3));// Approved
		applicationmodel.setEeStatus((MasterStatus) session.get(MasterStatus.class, 4));// Widthdrwa
		applicationmodel.setSmsId((SmsTemp)session.get(SmsTemp.class, 6));// MC Status
		applicationmodel.setStatusFlag('W');
		
		session.update(applicationmodel);
		session.beginTransaction().commit();
		final String status="EE%20WIDTHDRAW";
		
		final Integer smsType=2;
		final Integer emailType=2;
		
		final String smsTemp="";final String application_ID = applicationBean.getAppId();
		Thread notify = new Thread(new Runnable() {
			@Override
			public void run() {
				SMSBuilder obj = new SMSBuilder();
				obj.getSmsTemplate(application_ID,smsType,smsTemp);
				obj.getStatus(application_ID, status);
			}
		}, "notify");
		notify.start();
		

		session.close();
		return "Success";
	}
	
	
	
	@Override
	public String  eeRejected(ApplicationBean applicationBean) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Application applicationmodel = (Application) session.get(
				Application.class, applicationBean.getAppId());
		//applicationmodel.setUserId((EmployeeDetails) session.get(EmployeeDetails.class, Integer.parseInt(applicationBean.getUserId())));
		
	applicationmodel.setCeStatus((MasterStatus) session.get(MasterStatus.class, 3));// Approved
		applicationmodel.setEeStatus((MasterStatus) session.get(MasterStatus.class, 5));// Rejected
		applicationmodel.setSmsId((SmsTemp)session.get(SmsTemp.class, 6));// MC Status
		applicationmodel.setStatusFlag('R');
		applicationmodel.setRemarks(applicationBean.getRemarks());
		session.update(applicationmodel);
		session.beginTransaction().commit();
		
		
		final Integer smsType=2;
		final Integer emailType=2;
		final String status="APPLICATION%20REJECTED";
		
		final String smsTemp="Application Rejected";final String application_ID = applicationBean.getAppId();
		Thread notify = new Thread(new Runnable() {
			@Override
			public void run() {
				SMSBuilder obj = new SMSBuilder();
				obj.getSmsTemplate(application_ID,smsType,smsTemp);
				obj.getStatus(application_ID, status);
			}
		}, "notify");
		notify.start();
		

		session.close();
		return "Success";
	}
	
	
	@Override
	public String  eeAfterInspectionApproved(ApplicationBean applicationBean) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Application applicationmodel = (Application) session.get(
				Application.class, applicationBean.getAppId());
	//	applicationmodel.setUserId((EmployeeDetails) session.get(EmployeeDetails.class, Integer.parseInt(applicationBean.getUserId())));
		applicationmodel.setCeStatus((MasterStatus) session.get(MasterStatus.class, 3));// 3
		applicationmodel.setEeStatus((MasterStatus) session.get(MasterStatus.class, 2));// 2
		applicationmodel.setSmsId((SmsTemp)session.get(SmsTemp.class, 3));// afterInspection.ftl
		applicationmodel.setRemarks(applicationBean.getRemarks());
		// remarks addeds later
		session.update(applicationmodel);
		session.beginTransaction().commit();
		
		
		final Integer smsType=2;
		final Integer emailType=2;
		final String status="EE%20AFTER%20INSPECTION%20APPROVED";
		
		final String smsTemp="WaterConnectionApproved";final String application_ID = applicationBean.getAppId();
		Thread notify = new Thread(new Runnable() {
			@Override
			public void run() {
				SMSBuilder obj = new SMSBuilder();
				obj.getSmsTemplate(application_ID,smsType,smsTemp);
				obj.getStatus(application_ID, status);
			}
		}, "notify");
		notify.start();
		

		session.close();
		return "Success";
	}
	
	
	@Override
	public String  eeAfterInspectionRejected(ApplicationBean applicationBean) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Application applicationmodel = (Application) session.get(
				Application.class, applicationBean.getAppId());
		applicationmodel.setUserId((EmployeeDetails) session.get(EmployeeDetails.class, Integer.parseInt(applicationBean.getUserId())));
		applicationmodel.setCeStatus((MasterStatus) session.get(MasterStatus.class, 3));// 3
		applicationmodel.setEeStatus((MasterStatus) session.get(MasterStatus.class, 5));// 2
		applicationmodel.setSmsId((SmsTemp)session.get(SmsTemp.class, 5));// EE Rejected message || Rejected message
		applicationmodel.setRemarks(applicationBean.getRemarks());
		// remarks addeds later
		session.update(applicationmodel);
		session.beginTransaction().commit();
		
		
		final Integer smsType=2;
		final Integer emailType=2;
		final String status="EE%20REJECTED";
		
		final String smsTemp="";final String application_ID = applicationBean.getAppId();
		Thread notify = new Thread(new Runnable() {
			@Override
			public void run() {
				SMSBuilder obj = new SMSBuilder();
				obj.getSmsTemplate(application_ID,smsType,smsTemp);
				obj.getStatus(application_ID, status);
			}
		}, "notify");
		notify.start();
		

		session.close();
		return "Success";
	}
	
	@Override
	public String isMcTrckDicision(ApplicationBean applicationBean) {
		// TODO Auto-generated method stub
		
		
		 String result=null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Application applicationmodel = (Application) session.get(
				Application.class, applicationBean.getAppId());
		applicationmodel.setUserId((EmployeeDetails) session.get(EmployeeDetails.class, Integer.parseInt(applicationBean.getUserId())));
		if(applicationBean.getIsMcDicision().equals("1")){
			
			applicationmodel.setEeStatus((MasterStatus) session.get(MasterStatus.class, 3));// 3
			applicationmodel.setMcStatus((MasterStatus) session.get(MasterStatus.class, 6));// Rejected
			applicationmodel.setSmsId((SmsTemp)session.get(SmsTemp.class, 2));
			result= "MC%20REJECTED";
		}
		if(applicationBean.getIsMcDicision().equals("2")){
			
			applicationmodel.setEeStatus((MasterStatus) session.get(MasterStatus.class, 3));// 3
			applicationmodel.setMcStatus((MasterStatus) session.get(MasterStatus.class, 4));// 2Cancelled
			applicationmodel.setSmsId((SmsTemp)session.get(SmsTemp.class, 2));
			result= "MC%20CANCELLED";
		}
		
		
		//applicationmodel.setRemarks(applicationBean.getRemarks());
		// remarks addeds later
		session.update(applicationmodel);
		session.beginTransaction().commit();

		
		final String status=result;
		final Integer smsType=3;
		final Integer emailType=3;
		
		final String smsTemp="";final String application_ID = applicationBean.getAppId();
		Thread notify = new Thread(new Runnable() {
			@Override
			public void run() {
				SMSBuilder obj = new SMSBuilder();
				obj.getSmsTemplate(application_ID,smsType,smsTemp);
				obj.getStatus(application_ID, status);
			}
		}, "notify");
		notify.start();
		

		session.close();
		return "Success";
	}
	
	@Override
	public String isMcDicision(ApplicationBean applicationBean) {
		// TODO Auto-generated method stub
		// MC Approved
		String res=null;
		 String result=null;
		String resMsg=null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Application applicationmodel = (Application) session.get(
				Application.class, applicationBean.getAppId());
		//applicationmodel.setUserId((EmployeeDetails) session.get(EmployeeDetails.class, Integer.parseInt(applicationBean.getUserId())));
		if(applicationBean.getIsMcDicision().equals("1")){
			//applicationmodel.setSmsId((SmsTemp)session.get(SmsTemp.class, 3));// afterInspection.ftl
			applicationmodel.setEeStatus((MasterStatus) session.get(MasterStatus.class, 3));// 3
			applicationmodel.setMcStatus((MasterStatus) session.get(MasterStatus.class, 3));// 2 Approved
			applicationmodel.setSmsId((SmsTemp)session.get(SmsTemp.class, 7));//mcapproved.ftl
			applicationmodel.setInsStatusId(3);
			applicationmodel.setGstAmount(Integer.parseInt(applicationBean.getGstAmount().split("\\.")[0]));
			applicationmodel.setTotalAmount(Integer.parseInt(applicationBean.getTotalAmount()));
			applicationmodel.setFixedFinalFee(Integer.parseInt(applicationBean.getEstimationCost()));
			res="App%20No%20"+applicationBean.getAppId()+"%20Water%20Connection%20Approved%20Successfully.";
			result= "MC%20APPROVED";
		 resMsg="Management%20Committee%20has%20approved%20the%20estimate%20cost.%20Please%20pay%20the%20estimated%20fee";
		}
		if(applicationBean.getIsMcDicision().equals("2")){
			
			applicationmodel.setEeStatus((MasterStatus) session.get(MasterStatus.class, 3));// 3
			applicationmodel.setMcStatus((MasterStatus) session.get(MasterStatus.class, 5));// 2 Rejected
			applicationmodel.setSmsId((SmsTemp)session.get(SmsTemp.class, 8));//mcrejected.ftl
			applicationmodel.setInsStatusId(5);
			res="App%20No%20"+applicationBean.getAppId()+"%20Water%20Connection%20Rejected.";
		resMsg= "MC%20REJECTED";
			
		}
		
		
		//applicationmodel.setRemarks(applicationBean.getRemarks());
		// remarks addeds later
		session.update(applicationmodel);
		session.beginTransaction().commit();

		
		final String status=result;
		final Integer smsType=3;
		final Integer emailType=3;
		final String sts=resMsg;
		final String smsTemp=res;final String application_ID = applicationBean.getAppId();
		Thread notify = new Thread(new Runnable() {
			@Override
			public void run() {
				SMSBuilder obj = new SMSBuilder();
				obj.getSmsTemplate(application_ID,smsType,smsTemp);
				obj.getStatus(application_ID, sts);
			}
		}, "notify");
		notify.start();
		

		session.close();
		return "Success";
	}
	
	
	
	@Override
	public String sendInitialPaymentCost(ApplicationBean applicationBean) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Application applicationmodel = (Application) session.get(
				Application.class, applicationBean.getAppId());
		applicationmodel.setUserId((EmployeeDetails) session.get(EmployeeDetails.class, Integer.parseInt(applicationBean.getUserId())));
		
		applicationmodel.setInsStatusId(1);
		applicationmodel.setSmsId((SmsTemp)session.get(SmsTemp.class, 3));// inspection date
		
		applicationmodel.setCeStatus((MasterStatus) session.get(MasterStatus.class, 3));// 3 Approved.
		applicationmodel.setEeStatus((MasterStatus) session.get(MasterStatus.class, 2));// 2 Pending
		
		applicationmodel.setInitialPayment(applicationBean.getInitialPaymentCost());
		 /* Payment payment=new Payment();
	       payment.setAppId((Application) session.get(Application.class, applicationBean.getAppId()));
	       payment.setTrsNumber(Integer.parseInt(applicationBean.getInitialPaymentCost()));
	       payment.setCreateTs(new Date());
	       session.save(payment);
	*/
		
		session.update(applicationmodel);
		session.beginTransaction().commit();
		
		
		final Integer smsType=3;
		final Integer emailType=3;
		final String status="EE%20Fixed%20INITIAL%20PAYMENT%20COST";
		final String smsTemp="";final String application_ID = applicationBean.getAppId();
		Thread notify = new Thread(new Runnable() {
			@Override
			public void run() {
				SMSBuilder obj = new SMSBuilder();
				obj.getSmsTemplate(application_ID,smsType,smsTemp);
				obj.getStatus(application_ID, status);
			}
		}, "notify");
		notify.start();
		

		session.close();
		return "Success";
	}
	
	
	
	
	@Override
	public String sendBeforeInspectionDate(ApplicationBean applicationBean) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Application applicationmodel = (Application) session.get(
				Application.class, applicationBean.getAppId());
		applicationmodel.setUserId((EmployeeDetails) session.get(EmployeeDetails.class, Integer.parseInt(applicationBean.getUserId())));
		applicationmodel.setInsStatusId(2);
		applicationmodel.setSmsId((SmsTemp)session.get(SmsTemp.class, 2));//send inspection date
		applicationmodel.setRemarks(applicationBean.getRemarks());
		session.update(applicationmodel);
		session.beginTransaction().commit();
				
		final Integer smsType=3;
		final Integer emailType=3;
		final String smsTemp="WaterInspectionDate"+applicationBean.getInspectionDate();
		final String status="EE%20SCHEDULED%20INSPECTION%20DATE";
		final String application_ID = applicationBean.getAppId();
		Thread notify = new Thread(new Runnable() {
			@Override
			public void run() {
				SMSBuilder obj = new SMSBuilder();
				obj.getSmsTemplate(application_ID,smsType,smsTemp);
				obj.getStatus(application_ID, status);
			}
		}, "notify");
		notify.start();
		

		session.close();
		return "Success";
	}
	
	
	
	@Override
	public List<Object[]> getceDashboardCount() {

		Session session = sessionFactory.openSession();
		StringBuffer sqlQreyString = new StringBuffer();

		List<Object[]> dashBoardCount = new ArrayList<Object[]>();
		//sqlQreyString.append(Constant.Get_dashboardCount);
		sqlQreyString.append(Constant.Get_CEDashboardCount);
		
		
		SQLQuery query = session.createSQLQuery(sqlQreyString.toString());
		dashBoardCount = query.list();
		return dashBoardCount;

	}
	
	
	@Override
	public List<Object[]> getPaymentDashboardCount() {

		Session session = sessionFactory.openSession();
		StringBuffer sqlQreyString = new StringBuffer();

		List<Object[]> dashBoardCount = new ArrayList<Object[]>();
		//sqlQreyString.append(Constant.Get_dashboardCount);
		sqlQreyString.append(Constant.Get_PaymentDashboardCount);
		
		
		SQLQuery query = session.createSQLQuery(sqlQreyString.toString());
		dashBoardCount = query.list();
		return dashBoardCount;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DDPaymentFormBean> registeredApplication() {
		// TODO Auto-generated method stub

		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<DDPaymentFormBean> appDetails = new ArrayList<DDPaymentFormBean>();
		try {
			

			Criteria cr = session.createCriteria(CompanyDtl.class,"companyDtl")
					.createAlias("companyDtl.categoryType", "categoryType")
					.createAlias("companyDtl.office","office",JoinType.LEFT.ordinal())
					.setProjection(Projections.projectionList()
					.add(Projections.property("companyDtl.appId"),"appId")
					.add(Projections.property("companyDtl.contactPersonName"),"contactPersonName")
					.add(Projections.property("companyDtl.legCompName"),"legCompName")
					.add(Projections.property("companyDtl.createTs"),"createTs")
					.add(Projections.property("companyDtl.cdoorNo"),"cdoorNo")
					.add(Projections.property("companyDtl.cplotNo"),"cplotNo")
					.add(Projections.property("companyDtl.cstreetName"),"cstreetName")
					.add(Projections.property("companyDtl.clocation"),"clocation")
					.add(Projections.property("companyDtl.cpinCode"),"cpinCode")
					.add(Projections.property("companyDtl.salutation"),"salutation")
					.add(Projections.property("companyDtl.mobileNum"),"mobileNum")
					.add(Projections.property("companyDtl.landLineNo"),"landLineNo")
					.add(Projections.property("companyDtl.emailAddr"),"emailAddr")
					.add(Projections.property("categoryType.categoryName"),"categoryType")
					.add(Projections.property("office.officeName"),"officeName")
					.add(Projections.property("companyDtl.addrPremSought"),"addrPremSought")
					.add(Projections.property("companyDtl.doorNo"),"doorNo")
					.add(Projections.property("companyDtl.plotNo"),"plotNo")
					.add(Projections.property("companyDtl.streetName"),"streetName")
					.add(Projections.property("companyDtl.location"),"location")
					.add(Projections.property("companyDtl.district"),"district")
					.add(Projections.property("companyDtl.taluk"),"taluk")
					.add(Projections.property("companyDtl.village"),"village")
					.add(Projections.property("companyDtl.pinCode"),"pinCode")
					.add(Projections.property("companyDtl.surveyFieldNo"),"surveyFieldNo")
					.add(Projections.property("companyDtl.isNewConnection"),"isNewConnection")
					.add(Projections.property("companyDtl.reqMld"),"reqMld")
					.add(Projections.property("companyDtl.gstAmount"),"gstAmount")
					.add(Projections.property("companyDtl.totalAmount"),"totalAmount")
					.add(Projections.property("companyDtl.intrPlumStatus"),"intrPlumStatus")
					.add(Projections.property("companyDtl.workType"),"workType")
				           
				           )
					.add(Restrictions.eq("companyDtl.active", 1));
					cr.setResultTransformer(Transformers.aliasToBean(DDPaymentFormBean.class));
					
			
			appDetails=cr.list();
			
			
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return appDetails;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<DDPaymentFormBean> approvedApplication() {
		// TODO Auto-generated method stub

		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<DDPaymentFormBean> appDetails = new ArrayList<DDPaymentFormBean>();
		try {
			

			Criteria cr = session.createCriteria(CompanyDtl.class,"companyDtl")
					.createAlias("companyDtl.categoryType", "categoryType")
					.createAlias("companyDtl.office","office",JoinType.LEFT.ordinal())
					.setProjection(Projections.projectionList()
					.add(Projections.property("companyDtl.appId"),"appId")
					.add(Projections.property("companyDtl.contactPersonName"),"contactPersonName")
					.add(Projections.property("companyDtl.legCompName"),"legCompName")
					.add(Projections.property("companyDtl.createTs"),"createTs")
					.add(Projections.property("companyDtl.cdoorNo"),"cdoorNo")
					.add(Projections.property("companyDtl.cplotNo"),"cplotNo")
					.add(Projections.property("companyDtl.cstreetName"),"cstreetName")
					.add(Projections.property("companyDtl.clocation"),"clocation")
					.add(Projections.property("companyDtl.cpinCode"),"cpinCode")
					.add(Projections.property("companyDtl.salutation"),"salutation")
					.add(Projections.property("companyDtl.mobileNum"),"mobileNum")
					.add(Projections.property("companyDtl.landLineNo"),"landLineNo")
					.add(Projections.property("companyDtl.emailAddr"),"emailAddr")
					.add(Projections.property("categoryType.categoryName"),"categoryType")
					.add(Projections.property("office.officeName"),"officeName")
					.add(Projections.property("companyDtl.addrPremSought"),"addrPremSought")
					.add(Projections.property("companyDtl.doorNo"),"doorNo")
					.add(Projections.property("companyDtl.plotNo"),"plotNo")
					.add(Projections.property("companyDtl.streetName"),"streetName")
					.add(Projections.property("companyDtl.location"),"location")
					.add(Projections.property("companyDtl.district"),"district")
					.add(Projections.property("companyDtl.taluk"),"taluk")
					.add(Projections.property("companyDtl.village"),"village")
					.add(Projections.property("companyDtl.pinCode"),"pinCode")
					.add(Projections.property("companyDtl.surveyFieldNo"),"surveyFieldNo")
					.add(Projections.property("companyDtl.isNewConnection"),"isNewConnection")
					.add(Projections.property("companyDtl.reqMld"),"reqMld")
					.add(Projections.property("companyDtl.gstAmount"),"gstAmount")
					.add(Projections.property("companyDtl.totalAmount"),"totalAmount")
					.add(Projections.property("companyDtl.intrPlumStatus"),"intrPlumStatus")
					.add(Projections.property("companyDtl.workType"),"workType")
					.add(Projections.property("companyDtl.managementComments"),"managementComments")
				           
				           )
					.add(Restrictions.eq("companyDtl.active", 2));
					cr.setResultTransformer(Transformers.aliasToBean(DDPaymentFormBean.class));
					
			
			appDetails=cr.list();
			
			
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return appDetails;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<DDPaymentFormBean> rejectedApplication() {
		// TODO Auto-generated method stub

		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<DDPaymentFormBean> appDetails = new ArrayList<DDPaymentFormBean>();
		try {
			

			Criteria cr = session.createCriteria(CompanyDtl.class,"companyDtl")
					.createAlias("companyDtl.categoryType", "categoryType")
					.createAlias("companyDtl.office","office",JoinType.LEFT.ordinal())
					.setProjection(Projections.projectionList()
					.add(Projections.property("companyDtl.appId"),"appId")
					.add(Projections.property("companyDtl.contactPersonName"),"contactPersonName")
					.add(Projections.property("companyDtl.legCompName"),"legCompName")
					.add(Projections.property("companyDtl.createTs"),"createTs")
					.add(Projections.property("companyDtl.cdoorNo"),"cdoorNo")
					.add(Projections.property("companyDtl.cplotNo"),"cplotNo")
					.add(Projections.property("companyDtl.cstreetName"),"cstreetName")
					.add(Projections.property("companyDtl.clocation"),"clocation")
					.add(Projections.property("companyDtl.cpinCode"),"cpinCode")
					.add(Projections.property("companyDtl.salutation"),"salutation")
					.add(Projections.property("companyDtl.mobileNum"),"mobileNum")
					.add(Projections.property("companyDtl.landLineNo"),"landLineNo")
					.add(Projections.property("companyDtl.emailAddr"),"emailAddr")
					.add(Projections.property("categoryType.categoryName"),"categoryType")
					.add(Projections.property("office.officeName"),"officeName")
					.add(Projections.property("companyDtl.addrPremSought"),"addrPremSought")
					.add(Projections.property("companyDtl.doorNo"),"doorNo")
					.add(Projections.property("companyDtl.plotNo"),"plotNo")
					.add(Projections.property("companyDtl.streetName"),"streetName")
					.add(Projections.property("companyDtl.location"),"location")
					.add(Projections.property("companyDtl.district"),"district")
					.add(Projections.property("companyDtl.taluk"),"taluk")
					.add(Projections.property("companyDtl.village"),"village")
					.add(Projections.property("companyDtl.pinCode"),"pinCode")
					.add(Projections.property("companyDtl.surveyFieldNo"),"surveyFieldNo")
					.add(Projections.property("companyDtl.isNewConnection"),"isNewConnection")
					.add(Projections.property("companyDtl.reqMld"),"reqMld")
					.add(Projections.property("companyDtl.gstAmount"),"gstAmount")
					.add(Projections.property("companyDtl.totalAmount"),"totalAmount")
					.add(Projections.property("companyDtl.intrPlumStatus"),"intrPlumStatus")
					.add(Projections.property("companyDtl.workType"),"workType")
					.add(Projections.property("companyDtl.managementComments"),"managementComments")       
				           )
			 .add(Restrictions.eq("companyDtl.active", 0));
					cr.setResultTransformer(Transformers.aliasToBean(DDPaymentFormBean.class));
					
			
			appDetails=cr.list();
			
			
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return appDetails;
	}
	


	
	
	
	
	@Override
	public List<DDPaymentFormBean> paymentPendingList() {
		// TODO Auto-generated method stub

		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<DDPaymentFormBean> appDetails = new ArrayList<DDPaymentFormBean>();
		try {
			

			Criteria cr = session.createCriteria(CompanyPaymentDtl.class,"companyPaymentDtl")
					.createCriteria("companyPaymentDtl.appId","companyDtl")
					.setProjection(Projections.projectionList()
							 .add(Projections.property("companyPaymentDtl.companyPaymentDtlID"),"companyPaymentDtlID")
				            .add(Projections.property("companyPaymentDtl.paymentType"),"paymentType")
				            .add(Projections.property("companyPaymentDtl.paymentAmount"),"paymentAmount")  
				            
				            .add(Projections.property("companyPaymentDtl.managementComments"),"managementComments")
				            .add(Projections.property("companyPaymentDtl.paymentStatusFlag"),"paymentStatusFlag")
				            .add(Projections.property("companyPaymentDtl.ddNo"),"ddNo")
				            .add(Projections.property("companyPaymentDtl.ddDate"),"ddDate")
				            .add(Projections.property("companyPaymentDtl.ddBankName"),"ddBankName")
				            .add(Projections.property("companyPaymentDtl.createTs"),"createTs")				            
				            .add(Projections.property("companyDtl.legCompName"),"legCompName") 
				            .add(Projections.property("companyDtl.contactPersonName"),"contactPersonName") 
				            .add(Projections.property("companyDtl.appId"),"appId") 
				           
				           )
					 .add(Restrictions.eq("companyDtl.active", 1)).add(Restrictions.eq("companyPaymentDtl.paymentStatusFlag",'N'));
							 cr.setResultTransformer(Transformers.aliasToBean(DDPaymentFormBean.class));
					
			
			appDetails=cr.list();
			
			
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return appDetails;
	}
	
	@Override
	public List<DDPaymentFormBean> paymentRejectedList() {
		// TODO Auto-generated method stub

		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<DDPaymentFormBean> appDetails = new ArrayList<DDPaymentFormBean>();
		try {
			

			Criteria cr = session.createCriteria(CompanyPaymentDtl.class,"companyPaymentDtl")
					.createCriteria("companyPaymentDtl.appId","CompanyDtl")
					.setProjection(Projections.projectionList()
							 .add(Projections.property("companyPaymentDtl.companyPaymentDtlID"),"companyPaymentDtlID")
				            .add(Projections.property("companyPaymentDtl.paymentType"),"paymentType")
				            .add(Projections.property("companyPaymentDtl.paymentAmount"),"paymentAmount")  
				            
				            .add(Projections.property("companyPaymentDtl.managementComments"),"managementComments")
				            .add(Projections.property("companyPaymentDtl.paymentStatusFlag"),"paymentStatusFlag")
				            .add(Projections.property("companyPaymentDtl.ddNo"),"ddNo")
				            
				            .add(Projections.property("companyPaymentDtl.ddDate"),"ddDate")
				            .add(Projections.property("companyPaymentDtl.ddBankName"),"ddBankName")
				            .add(Projections.property("companyPaymentDtl.createTs"),"createTs")				            
				            .add(Projections.property("CompanyDtl.legCompName"),"legCompName") 
				            .add(Projections.property("CompanyDtl.contactPersonName"),"contactPersonName") 
				            .add(Projections.property("CompanyDtl.appId"),"appId") 
				           
				           )
					 .add(Restrictions.eq("CompanyDtl.active", 1)).add(Restrictions.eq("companyPaymentDtl.paymentStatusFlag",'R'));
							 cr.setResultTransformer(Transformers.aliasToBean(DDPaymentFormBean.class));
					
			
			appDetails=cr.list();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return appDetails;
	}

	
	

	@Override
	public List<DDPaymentFormBean> paymentApprovedList() {
		// TODO Auto-generated method stub

		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<DDPaymentFormBean> appDetails = new ArrayList<DDPaymentFormBean>();
		try {
			

			Criteria cr = session.createCriteria(CompanyPaymentDtl.class,"companyPaymentDtl")
					.createCriteria("companyPaymentDtl.appId","CompanyDtl")
					.setProjection(Projections.projectionList()
							 .add(Projections.property("companyPaymentDtl.companyPaymentDtlID"),"companyPaymentDtlID")
				            .add(Projections.property("companyPaymentDtl.paymentType"),"paymentType")
				            .add(Projections.property("companyPaymentDtl.paymentAmount"),"paymentAmount")  
				            
				            .add(Projections.property("companyPaymentDtl.managementComments"),"managementComments")
				            .add(Projections.property("companyPaymentDtl.paymentStatusFlag"),"paymentStatusFlag")
				            .add(Projections.property("companyPaymentDtl.ddNo"),"ddNo")
				            
				            .add(Projections.property("companyPaymentDtl.ddDate"),"ddDate")
				            .add(Projections.property("companyPaymentDtl.ddBankName"),"ddBankName")
				            .add(Projections.property("companyPaymentDtl.createTs"),"createTs")				            
				            .add(Projections.property("CompanyDtl.legCompName"),"legCompName") 
				            .add(Projections.property("CompanyDtl.contactPersonName"),"contactPersonName") 
				            .add(Projections.property("CompanyDtl.appId"),"appId") 
				           
				           )
					 .add(Restrictions.eq("CompanyDtl.active", 1)).add(Restrictions.eq("companyPaymentDtl.paymentStatusFlag",'A'));
							 cr.setResultTransformer(Transformers.aliasToBean(DDPaymentFormBean.class));
					
			
			appDetails=cr.list();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return appDetails;
	}

	@Override
	public List<DDPaymentFormBean> ddPaymentViewAllList() {
		// TODO Auto-generated method stub

		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<DDPaymentFormBean> appDetails = new ArrayList<DDPaymentFormBean>();
		try {
			

			Criteria cr = session.createCriteria(CompanyPaymentDtl.class,"companyPaymentDtl")
					.createCriteria("companyPaymentDtl.appId","CompanyDtl")
					.setProjection(Projections.projectionList()
							 .add(Projections.property("companyPaymentDtl.companyPaymentDtlID"),"companyPaymentDtlID")
				            .add(Projections.property("companyPaymentDtl.paymentType"),"paymentType")
				            .add(Projections.property("companyPaymentDtl.paymentAmount"),"paymentAmount")  
				            
				            .add(Projections.property("companyPaymentDtl.managementComments"),"managementComments")
				            .add(Projections.property("companyPaymentDtl.paymentStatusFlag"),"paymentStatusFlag")
				            .add(Projections.property("companyPaymentDtl.ddNo"),"ddNo")
				            
				            .add(Projections.property("companyPaymentDtl.ddDate"),"ddDate")
				            .add(Projections.property("companyPaymentDtl.ddBankName"),"ddBankName")
				            .add(Projections.property("companyPaymentDtl.createTs"),"createTs")				            
				            .add(Projections.property("CompanyDtl.legCompName"),"legCompName") 
				            .add(Projections.property("CompanyDtl.contactPersonName"),"contactPersonName") 
				            .add(Projections.property("CompanyDtl.appId"),"appId") 
				           
				           )
					 .add(Restrictions.eq("CompanyDtl.active", 1));
							 cr.setResultTransformer(Transformers.aliasToBean(DDPaymentFormBean.class));
					
			
			appDetails=cr.list();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return appDetails;
	}

	@Override
	public DDPaymentFormBean paymentViewForm(DDPaymentFormBean ddPaymentFormBean) {
		// TODO Auto-generated method stub

		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		DDPaymentFormBean appDetails = new DDPaymentFormBean();
		try {
			

			Criteria cr = session.createCriteria(CompanyPaymentDtl.class,"companyPaymentDtl")
					.createCriteria("companyPaymentDtl.appId","CompanyDtl")
					.setProjection(Projections.projectionList()
							 .add(Projections.property("companyPaymentDtl.companyPaymentDtlID"),"companyPaymentDtlID")
				            .add(Projections.property("companyPaymentDtl.paymentType"),"paymentType")
				            .add(Projections.property("companyPaymentDtl.paymentAmount"),"paymentAmount")  
				            
				            .add(Projections.property("companyPaymentDtl.managementComments"),"managementComments")
				            .add(Projections.property("companyPaymentDtl.paymentStatusFlag"),"paymentStatusFlag")
				            .add(Projections.property("companyPaymentDtl.ddNo"),"ddNo")
				            
				            .add(Projections.property("companyPaymentDtl.ddDate"),"ddDate")
				            .add(Projections.property("companyPaymentDtl.ddBankName"),"ddBankName")
				            .add(Projections.property("companyPaymentDtl.createTs"),"createTs")				            
				            .add(Projections.property("CompanyDtl.legCompName"),"legCompName") 
				            .add(Projections.property("CompanyDtl.contactPersonName"),"contactPersonName") 
				            .add(Projections.property("CompanyDtl.appId"),"appId") 
				           
				           )
					 .add(Restrictions.eq("CompanyDtl.active", 1)).add(Restrictions.eq("CompanyDtl.appId", ddPaymentFormBean.getAppId()));
							 cr.setResultTransformer(Transformers.aliasToBean(DDPaymentFormBean.class));
					
			
			appDetails=(DDPaymentFormBean)cr.uniqueResult();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return appDetails;
	}

	
	
	
	@Override
	public String paymentApproved(ApplicationBean applicationBean) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Application applicationmodel = (Application) session.get(
				Application.class, applicationBean.getAppId());
		applicationmodel.setUserId((EmployeeDetails) session.get(EmployeeDetails.class, Integer.parseInt(applicationBean.getUserId())));
		applicationmodel.setCeStatus((MasterStatus) session.get(MasterStatus.class, 3));// 3
		applicationmodel.setEeStatus((MasterStatus) session.get(MasterStatus.class, 2));// 2
		applicationmodel.setSmsId((SmsTemp)session.get(SmsTemp.class, 2));
		applicationmodel.setRemarks(applicationBean.getRemarks());
		
		// remarks addeds later
		session.update(applicationmodel);
		session.beginTransaction().commit();
		
		
		final Integer smsType=2;
		final Integer emailType=2;
		
		final String smsTemp="";final String application_ID = applicationBean.getAppId();
		Thread notify = new Thread(new Runnable() {
			@Override
			public void run() {
				SMSBuilder obj = new SMSBuilder();
				obj.getSmsTemplate(application_ID,smsType,smsTemp);
			}
		}, "notify");
		notify.start();
		

		session.close();
		return "Success";
	}
	
	

	
	@Override
	public List<Object[]> geteeDashboardCount() {

		Session session = sessionFactory.openSession();
		StringBuffer sqlQreyString = new StringBuffer();

		List<Object[]> dashBoardCount = new ArrayList<Object[]>();
		//sqlQreyString.append(Constant.Get_dashboardCount);
		sqlQreyString.append(Constant.Get_EEDashboardCount);
		
		
		SQLQuery query = session.createSQLQuery(sqlQreyString.toString());
		dashBoardCount = query.list();
		return dashBoardCount;

	}
	@Override
	public List<Object[]> getmcDashboardCount() {

		Session session = sessionFactory.openSession();
		StringBuffer sqlQreyString = new StringBuffer();

		List<Object[]> dashBoardCount = new ArrayList<Object[]>();

		sqlQreyString.append(Constant.Get_MCDashboardCount);

		SQLQuery query = session.createSQLQuery(sqlQreyString.toString());
		dashBoardCount = query.list();
		return dashBoardCount;

	}
	
	@Override
	public List<Object[]> getPublicDashboardCount(
			ApplicationBean applicationBean) {
		Session session = sessionFactory.openSession();
		StringBuffer sqlQreyString = new StringBuffer();

		List<Object[]> dashBoardCount = new ArrayList<Object[]>();

		sqlQreyString.append(Constant.Get_CEDashboardCount);

		SQLQuery query = session.createSQLQuery(sqlQreyString.toString());
		dashBoardCount = query.list();
		return dashBoardCount;

	}
	@Override
	public List<Application> listEePendingApplicationDtls() {
		// TODO Auto-generated method stub
		return null;
	}
public String addNewUser(EmployeeFormBean employeeFormBean ){
	Session session = sessionFactory.openSession();
	Transaction tx =  session.beginTransaction();
	EmployeeDetails employeeDetails = new EmployeeDetails();
	employeeDetails.setUserRole((MasterRole)session.get(MasterRole.class,Integer.parseInt(employeeFormBean.getRoleId())));
	employeeDetails.setUserOffice((MasterOffice)session.get(MasterOffice.class,Integer.parseInt(employeeFormBean.getOfficeId())));
	employeeDetails.setLoginUserName(employeeFormBean.getUsername());
	employeeDetails.setLoginPassword(employeeFormBean.getPassword());
	employeeDetails.setEmailAddr(employeeFormBean.getEmail());
	employeeDetails.setPhoneNum(Long.parseLong(employeeFormBean.getMobile()));
	employeeDetails.setUserFirstName(employeeFormBean.getName());
	employeeDetails.setStatusFlag('Y');
	employeeDetails.setUpdateTs(new Date());
	employeeDetails.setCreateTs(new Date());
	employeeDetails.setUpdateUserId("Administrator");
	employeeDetails.setCreateUserId("Administrator");
	employeeDetails.setUserLastName(" ");
	employeeDetails.setZoneId((MasterZone)session.get(MasterZone.class,1));
	session.save(employeeDetails);
	tx.commit();
		return "User Added Successfully";
	}
public String editUser(EmployeeFormBean employeeFormBean ){
	Session session = sessionFactory.openSession();
	Transaction tx =  session.beginTransaction();
	EmployeeDetails employeeDetails = (EmployeeDetails)session.get(EmployeeDetails.class,Integer.parseInt(employeeFormBean.getUserId()));
	employeeDetails.setUserId(Integer.parseInt(employeeFormBean.getUserId()));
	employeeDetails.setUserRole((MasterRole)session.get(MasterRole.class,Integer.parseInt(employeeFormBean.getRoleId())));
	employeeDetails.setUserOffice((MasterOffice)session.get(MasterOffice.class,Integer.parseInt(employeeFormBean.getOfficeId())));
	employeeDetails.setLoginUserName(employeeFormBean.getUsername());
	employeeDetails.setLoginPassword(employeeFormBean.getPassword());
	employeeDetails.setEmailAddr(employeeFormBean.getEmail());
	employeeDetails.setPhoneNum(Long.parseLong(employeeFormBean.getMobile()));
	employeeDetails.setUserFirstName(employeeFormBean.getName());
	employeeDetails.setUpdateTs(new Date());
	session.update(employeeDetails);
	tx.commit();
		return "User Updated Successfully";
	}
	
	public String deleteUser(EmployeeFormBean employeeFormBean ){
		
		String[] userArray = employeeFormBean.getUserId().split(",");
		
		for(int i=0;i<userArray.length;i++){
			Session session = sessionFactory.openSession();
			Transaction tx =  session.beginTransaction();
			int userid=Integer.parseInt(userArray[i]);
		EmployeeDetails employeeDetails = (EmployeeDetails)session.get(EmployeeDetails.class,userid);
		session.delete(employeeDetails);
		tx.commit();
		}
			return "User deleted Successfully";
		}
	public List<EmployeeDetails> getUserDtl(){
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(EmployeeDetails.class,"app");
			return cr.list();
		}
	
	

public String addCategory(CategoryFormBean categoryFormBean ){
	Session session = sessionFactory.openSession();
	Transaction tx =  session.beginTransaction();
	MasterCategory masterCategory = new MasterCategory();
	masterCategory.setCategoryName(categoryFormBean.getCategoryName());
	masterCategory.setCategoryDesc(categoryFormBean.getCategoryDesc());
	masterCategory.setUpdateTs(new Date());
	masterCategory.setCreateTs(new Date());
	masterCategory.setUpdateUserId("Administrator");
	masterCategory.setCreateUserId("Administrator");
	session.save(masterCategory);
	tx.commit();
		return "Category Added Successfully";
	}
public String editCategory(CategoryFormBean categoryFormBean ){
	Session session = sessionFactory.openSession();
	Transaction tx =  session.beginTransaction();
	MasterCategory masterCategory = (MasterCategory)session.get(MasterCategory.class,Integer.parseInt(categoryFormBean.getCategoryId()));
	masterCategory.setCategoryName(categoryFormBean.getCategoryName());
	masterCategory.setCategoryDesc(categoryFormBean.getCategoryDesc());
	
	masterCategory.setUpdateTs(new Date());
	session.update(masterCategory);
	tx.commit();
		return "Category Updated Successfully";
	}
	
	public String deleteCategory(CategoryFormBean categoryFormBean ){
		
		String[] categoryArray = categoryFormBean.getCategoryId().split(",");
		
		for(int i=0;i<categoryArray.length;i++){
			Session session = sessionFactory.openSession();
			Transaction tx =  session.beginTransaction();
			int userid=Integer.parseInt(categoryArray[i]);
			MasterCategory masterCategory = (MasterCategory)session.get(MasterCategory.class,userid);
		session.delete(masterCategory);
		tx.commit();
		}
			return "Category deleted Successfully";
		}
	public List<MasterCategory> getCategoryDtl(){
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(MasterCategory.class,"app");
			return cr.list();
		}
	

public String addOffice(OfficeFormBean officeFormBean ){
	Session session = sessionFactory.openSession();
	Transaction tx =  session.beginTransaction();
	MasterOffice masterOffice = new MasterOffice();
	masterOffice.setOfficeName(officeFormBean.getOfficeName());
	masterOffice.setOfficeDesc(officeFormBean.getOfficeDesc());
	masterOffice.setUpdateTs(new Date());
	masterOffice.setCreateTs(new Date());
	masterOffice.setUpdateUserId("Administrator");
	masterOffice.setCreateUserId("Administrator");
	session.save(masterOffice);
	tx.commit();
		return "Office Added Successfully";
	}
public String editOffice(OfficeFormBean officeFormBean ){
	Session session = sessionFactory.openSession();
	Transaction tx =  session.beginTransaction();
	MasterOffice masterOffice = (MasterOffice)session.get(MasterOffice.class,Integer.parseInt(officeFormBean.getOfficeId()));
	masterOffice.setOfficeName(officeFormBean.getOfficeName());
	masterOffice.setOfficeDesc(officeFormBean.getOfficeDesc());
	
	masterOffice.setUpdateTs(new Date());
	session.update(masterOffice);
	tx.commit();
		return "Office Updated Successfully";
	}
	
	public String deleteOffice(OfficeFormBean officeFormBean ){
		
		String[] officeArray = officeFormBean.getOfficeId().split(",");
		
		for(int i=0;i<officeArray.length;i++){
			Session session = sessionFactory.openSession();
			Transaction tx =  session.beginTransaction();
			int userid=Integer.parseInt(officeArray[i]);
			MasterOffice masterOffice = (MasterOffice)session.get(MasterOffice.class,userid);
		session.delete(masterOffice);
		tx.commit();
		}
			return "Office deleted Successfully";
		}
	public List<MasterOffice> getOfficeDtl(){
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(MasterOffice.class,"app");
			return cr.list();
		}

	
	

public String addPaymentType(PaymentFormBean paymentTypeFormBean ){
	Session session = sessionFactory.openSession();
	Transaction tx =  session.beginTransaction();
	MasterPaymentType masterPaymentType = new MasterPaymentType();
	masterPaymentType.setPaymentType(paymentTypeFormBean.getPaymentType());
	masterPaymentType.setPaymentTypeDesc(paymentTypeFormBean.getPaymentTypeDesc());
	masterPaymentType.setUpdateTs(new Date());
	masterPaymentType.setCreateTs(new Date());
	masterPaymentType.setUpdateUserId("Administrator");
	masterPaymentType.setCreateUserId("Administrator");
	session.save(masterPaymentType);
	tx.commit();
		return "PaymentType Added Successfully";
	}
public String editPaymentType(PaymentFormBean paymentTypeFormBean ){
	Session session = sessionFactory.openSession();
	Transaction tx =  session.beginTransaction();
	MasterPaymentType masterPaymentType = (MasterPaymentType)session.get(MasterPaymentType.class,Integer.parseInt(paymentTypeFormBean.getPaymentId()));
	masterPaymentType.setPaymentType(paymentTypeFormBean.getPaymentType());
	masterPaymentType.setPaymentTypeDesc(paymentTypeFormBean.getPaymentTypeDesc());
	
	masterPaymentType.setUpdateTs(new Date());
	session.update(masterPaymentType);
	tx.commit();
		return "PaymentType Updated Successfully";
	}
	
	public String deletePaymentType(PaymentFormBean paymentTypeFormBean ){
		
		String[] paymentTypeArray = paymentTypeFormBean.getPaymentId().split(",");
		
		for(int i=0;i<paymentTypeArray.length;i++){
			Session session = sessionFactory.openSession();
			Transaction tx =  session.beginTransaction();
			int userid=Integer.parseInt(paymentTypeArray[i]);
			MasterPaymentType masterPaymentType = (MasterPaymentType)session.get(MasterPaymentType.class,userid);
		session.delete(masterPaymentType);
		tx.commit();
		}
			return "PaymentType deleted Successfully";
		}
	public List<MasterPaymentType> getPaymentTypeDtl(){
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(MasterPaymentType.class,"app");
			return cr.list();
		}


public String addPayment(PaymentFormBean paymentFormBean ){
	Session session = sessionFactory.openSession();
	Transaction tx =  session.beginTransaction();
	MasterPayment masterPayment = new MasterPayment();
	masterPayment.setPaymentType((MasterPaymentType)session.get(MasterPaymentType.class,Integer.parseInt(paymentFormBean.getPaymentType())));
	masterPayment.setPaymentAmount(paymentFormBean.getPaymentAmount());
	masterPayment.setPaymentDesc(paymentFormBean.getPaymentDesc());
	masterPayment.setGstAmount(paymentFormBean.getGstAmount());
	masterPayment.setGstPercent(paymentFormBean.getGstPercent());
	masterPayment.setTotalAmount(paymentFormBean.getTotalAmount());
	masterPayment.setUpdateTs(new Date());
	masterPayment.setCreateTs(new Date());
	masterPayment.setUpdateUserId("Administrator");
	masterPayment.setCreateUserId("Administrator");
	session.save(masterPayment);
	tx.commit();
		return "Payment Added Successfully";
	}
public String editPayment(PaymentFormBean paymentFormBean ){
	Session session = sessionFactory.openSession();
	Transaction tx =  session.beginTransaction();
	MasterPayment masterPayment = (MasterPayment)session.get(MasterPayment.class,Integer.parseInt(paymentFormBean.getPaymentId()));
	masterPayment.setPaymentType((MasterPaymentType)session.get(MasterPaymentType.class,Integer.parseInt(paymentFormBean.getPaymentType())));
	masterPayment.setPaymentAmount(paymentFormBean.getPaymentAmount());
	masterPayment.setPaymentDesc(paymentFormBean.getPaymentDesc());
	masterPayment.setGstAmount(paymentFormBean.getGstAmount());
	masterPayment.setGstPercent(paymentFormBean.getGstPercent());
	masterPayment.setTotalAmount(paymentFormBean.getTotalAmount());
	
	masterPayment.setUpdateTs(new Date());
	session.update(masterPayment);
	tx.commit();
		return "Payment Updated Successfully";
	}
	
	public String deletePayment(PaymentFormBean paymentFormBean ){
		
		String[] paymentArray = paymentFormBean.getPaymentId().split(",");
		
		for(int i=0;i<paymentArray.length;i++){
			Session session = sessionFactory.openSession();
			Transaction tx =  session.beginTransaction();
			int userid=Integer.parseInt(paymentArray[i]);
			MasterPayment masterPayment = (MasterPayment)session.get(MasterPayment.class,userid);
		session.delete(masterPayment);
		tx.commit();
		}
			return "Payment deleted Successfully";
		}
	public List<MasterPayment> getPaymentDtl(){
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(MasterPayment.class,"app");
			return cr.list();
		}



	
	
	
	
	
	
	
public String addConnectionType(ConnectionFormBean connectionFormBean ){
	Session session = sessionFactory.openSession();
	Transaction tx =  session.beginTransaction();
	MasterReconnection masterReconnection = new MasterReconnection();
	masterReconnection.setConnectionType(connectionFormBean.getConnectionName());
	masterReconnection.setReConnDes(connectionFormBean.getConnectionDesc());
	masterReconnection.setUpdateTs(new Date());
	masterReconnection.setCreateTs(new Date());
	masterReconnection.setUpdateUserId("Administrator");
	masterReconnection.setCreateUserId("Administrator");
	session.save(masterReconnection);
	tx.commit();
		return "Connection Type Added Successfully";
	}
public String editConnectionType(ConnectionFormBean connectionFormBean ){
	Session session = sessionFactory.openSession();
	Transaction tx =  session.beginTransaction();
	MasterReconnection masterReconnection = (MasterReconnection)session.get(MasterReconnection.class,Integer.parseInt(connectionFormBean.getConnectionId()));
	masterReconnection.setConnectionType(connectionFormBean.getConnectionName());
	masterReconnection.setReConnDes(connectionFormBean.getConnectionDesc());
	
	masterReconnection.setUpdateTs(new Date());
	session.update(masterReconnection);
	tx.commit();
		return "Connection Type Updated Successfully";
	}
	
	public String deleteConnectionType(ConnectionFormBean connectionFormBean ){
		
		String[] connectionArray = connectionFormBean.getConnectionId().split(",");
		
		for(int i=0;i<connectionArray.length;i++){
			Session session = sessionFactory.openSession();
			Transaction tx =  session.beginTransaction();
			int connectionid=Integer.parseInt(connectionArray[i]);
		MasterReconnection masterReconnection = (MasterReconnection)session.get(MasterReconnection.class,connectionid);
		session.delete(masterReconnection);
		tx.commit();
		}
			return "Connection Type deleted Successfully";
		}
	public List<MasterReconnection> getConnectionTypeDtl(){
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(MasterReconnection.class,"app");
			return cr.list();
		}
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

public String addZone(ZoneDivisionFormBean zoneDivisionFormBean ){
	Session session1 = sessionFactory.openSession();
	Transaction tx1 =  session1.beginTransaction();
	MasterZone masterZone = new MasterZone();
	masterZone.setZoneDesc(zoneDivisionFormBean.getZoneName());
	masterZone.setStatusFlag('Y');
	masterZone.setUpdateTs(new Date());
	masterZone.setCreateTs(new Date());
	masterZone.setUpdateUserId("Administrator");
	masterZone.setCreateUserId("Administrator");
	session1.save(masterZone);
	tx1.commit();
	
	
	String[] divisionArray = zoneDivisionFormBean.getDivisionNo().split(",");
	String[] divisionAddrArray = zoneDivisionFormBean.getDivisionAddr().split("##");
	String[] divisionPhoneArray = zoneDivisionFormBean.getDivisionPhone().split(",");
	String[] divisionMobileArray = zoneDivisionFormBean.getDivisionMobile().split(",");
	
	for(int i=0;i<divisionArray.length;i++){
	
	Session session2 = sessionFactory.openSession();
	Transaction tx2 =  session2.beginTransaction();
	
	MasterDivision masterDivision = new MasterDivision();
	masterDivision.setCmwssbZoneNum((MasterZone)session2.get(MasterZone.class,masterZone.getZoneId()));
	masterDivision.setDivDesc(divisionArray[i]);
	
	masterDivision.setDivAddr(divisionAddrArray[i]);
	masterDivision.setDivPhone(divisionPhoneArray[i]);
	masterDivision.setDivMobile(divisionMobileArray[i]);
	masterDivision.setUpdateTs(new Date());
	masterDivision.setCreateTs(new Date());
	masterDivision.setUpdateUserId("Administrator");
	masterDivision.setCreateUserId("Administrator");
	
	session2.save(masterDivision);
	tx2.commit();
	}
		return "Zone Division Added Successfully";
	}
	
	
	
	

public String addDivision(ZoneDivisionFormBean zoneDivisionFormBean ){
	
	String[] divisionArray = zoneDivisionFormBean.getDivisionNo().split(",");
	String[] divisionAddrArray = zoneDivisionFormBean.getDivisionAddr().split("##");
	String[] divisionPhoneArray = zoneDivisionFormBean.getDivisionPhone().split(",");
	String[] divisionMobileArray = zoneDivisionFormBean.getDivisionMobile().split(",");
	
	for(int i=0;i<divisionArray.length;i++){
	
	Session session2 = sessionFactory.openSession();
	Transaction tx2 =  session2.beginTransaction();
	
	MasterDivision masterDivision = new MasterDivision();
	masterDivision.setCmwssbZoneNum((MasterZone)session2.get(MasterZone.class,Integer.parseInt(zoneDivisionFormBean.getZoneDivisionId())));
	masterDivision.setDivDesc(divisionArray[i]);
	
	masterDivision.setDivAddr(divisionAddrArray[i]);
	masterDivision.setDivPhone(divisionPhoneArray[i]);
	masterDivision.setDivMobile(divisionMobileArray[i]);
	masterDivision.setUpdateTs(new Date());
	masterDivision.setCreateTs(new Date());
	masterDivision.setUpdateUserId("Administrator");
	masterDivision.setCreateUserId("Administrator");
	
	session2.save(masterDivision);
	tx2.commit();
	}
		return "Division Added Successfully";
	}
public String editDivision(ZoneDivisionFormBean zoneDivisionFormBean ){
	Session session2 = sessionFactory.openSession();
	Transaction tx2 =  session2.beginTransaction();
	
	MasterDivision masterDivision = (MasterDivision)session2.get(MasterDivision.class,Integer.parseInt(zoneDivisionFormBean.getZoneDivisionId()));
	masterDivision.setDivDesc(zoneDivisionFormBean.getDivisionNo());
	masterDivision.setDivAddr(zoneDivisionFormBean.getDivisionAddr());
	masterDivision.setDivPhone(zoneDivisionFormBean.getDivisionPhone());
	masterDivision.setDivMobile(zoneDivisionFormBean.getDivisionMobile());
	masterDivision.setUpdateTs(new Date());
	masterDivision.setUpdateUserId("Administrator");
	session2.update(masterDivision);
	tx2.commit();
		return "Zone Division Updated Successfully";
	}
	
	public String deleteDivision(ZoneDivisionFormBean zoneDivisionFormBean ){
		
		String[] divisionArray = zoneDivisionFormBean.getZoneDivisionId().split(",");
		
		for(int i=0;i<divisionArray.length;i++){
			Session session = sessionFactory.openSession();
			Transaction tx =  session.beginTransaction();
			int divisionid=Integer.parseInt(divisionArray[i]);
			MasterDivision masterDivision = (MasterDivision)session.get(MasterDivision.class,divisionid);
		session.delete(masterDivision);
		tx.commit();
		}
			return "Zone Division deleted Successfully";
		}
	public List<ZoneDivisionFormBean> getDivisionDtl(){
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(MasterDivision.class,"app")
				.createCriteria("app.cmwssbZoneNum","zone")
				.setProjection(Projections.projectionList()
			            .add(Projections.property("zone.zoneDesc"),"zoneName")
			            .add(Projections.property("app.divDesc"),"divisionNo")  
			            
			            .add(Projections.property("app.divAddr"),"divisionAddr")
			            .add(Projections.property("app.divPhone"),"divisionPhone")
			            .add(Projections.property("app.divMobile"),"divisionMobile")
			            
			            .add(Projections.property("zone.zoneId"),"zoneId")  
			            .add(Projections.property("app.divId"),"divisionId"))
			
		.setResultTransformer(Transformers.aliasToBean(ZoneDivisionFormBean.class));
			return cr.list();
		}
	
	
	
	
	
	
	
	

public String addDistrict(DistrictFormBean categoryFormBean ){
	Session session = sessionFactory.openSession();
	Transaction tx =  session.beginTransaction();
	MasterDistrict masterDistrict = new MasterDistrict();
	masterDistrict.setDistrictName(categoryFormBean.getDistrictName());
	masterDistrict.setUpdateTs(new Date());
	masterDistrict.setCreateTs(new Date());
	masterDistrict.setUpdateUserId("Administrator");
	masterDistrict.setCreateUserId("Administrator");
	session.save(masterDistrict);
	tx.commit();
		return "District Added Successfully";
	}
public String editDistrict(DistrictFormBean categoryFormBean ){
	Session session = sessionFactory.openSession();
	Transaction tx =  session.beginTransaction();
	MasterDistrict masterDistrict = (MasterDistrict)session.get(MasterDistrict.class,Integer.parseInt(categoryFormBean.getDistrictId()));
	masterDistrict.setDistrictName(categoryFormBean.getDistrictName());
	
	masterDistrict.setUpdateTs(new Date());
	session.update(masterDistrict);
	tx.commit();
		return "District Updated Successfully";
	}
	
	public String deleteDistrict(DistrictFormBean categoryFormBean ){
		Session session = sessionFactory.openSession();
		try{
		String[] categoryArray = categoryFormBean.getDistrictId().split(",");
			for (int i = 0; i < categoryArray.length; i++) {

				Transaction tx = session.beginTransaction();
				int userid = Integer.parseInt(categoryArray[i]);
				MasterDistrict masterDistrict = (MasterDistrict) session.get(MasterDistrict.class, userid);
				session.delete(masterDistrict);

				tx.commit();
			}
		}
		catch(Exception e){
			return "District can't be deleted, First delete Village and Taluk then district";
		}
		finally {
			session.close();
		}
			return "District deleted Successfully";
		}
	public List<MasterDistrict> getDistrictDtl(){
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(MasterDistrict.class,"app");
			return cr.list();
		}
	

	
	
	
	
	
	
	

public String addTaluk(DistrictTalukFormBean districtTalukFormBean ){
	
	String[] talukArray = districtTalukFormBean.getTalukName().split(",");
	
	for(int i=0;i<talukArray.length;i++){
	
	Session session2 = sessionFactory.openSession();
	Transaction tx2 =  session2.beginTransaction();
	
	MasterTaluk masterTaluk = new MasterTaluk();
	masterTaluk.setDistrictId((MasterDistrict)session2.get(MasterDistrict.class,Integer.parseInt(districtTalukFormBean.getDistrictTalukId())));
	masterTaluk.setTalukName(talukArray[i]);
	masterTaluk.setUpdateTs(new Date());
	masterTaluk.setCreateTs(new Date());
	masterTaluk.setUpdateUserId("Administrator");
	masterTaluk.setCreateUserId("Administrator");
	
	session2.save(masterTaluk);
	tx2.commit();
	}
		return "Taluk Added Successfully";
	}
public String editTaluk(DistrictTalukFormBean districtTalukFormBean ){
	Session session2 = sessionFactory.openSession();
	Transaction tx2 =  session2.beginTransaction();
	
	MasterTaluk masterTaluk = (MasterTaluk)session2.get(MasterTaluk.class,Integer.parseInt(districtTalukFormBean.getDistrictTalukId()));
	masterTaluk.setTalukName(districtTalukFormBean.getTalukName());
	masterTaluk.setUpdateTs(new Date());
	masterTaluk.setUpdateUserId("Administrator");
	session2.update(masterTaluk);
	tx2.commit();
		return "Taluk Updated Successfully";
	}
	
	public String deleteTaluk(DistrictTalukFormBean districtTalukFormBean ){
		Session session = sessionFactory.openSession();
		try{
		String[] talukArray = districtTalukFormBean.getDistrictTalukId().split(",");
		
			for (int i = 0; i < talukArray.length; i++) {

				Transaction tx = session.beginTransaction();
				int talukid = Integer.parseInt(talukArray[i]);
				MasterTaluk masterTaluk = (MasterTaluk) session.get(MasterTaluk.class, talukid);
				session.delete(masterTaluk);

				tx.commit();
			}
		
		}catch(Exception e){
			return "Taluk can't be deleted, First delete Village then Taluk";
		}
		finally {
			session.close();
		}
			return "Taluk deleted Successfully";
		}
	public List<DistrictTalukFormBean> getTalukDtl(){
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(MasterTaluk.class,"app")
				.createCriteria("app.districtId","district")
				.setProjection(Projections.projectionList()
			            .add(Projections.property("district.districtName"),"districtName")
			            .add(Projections.property("app.talukName"),"talukName")  
			            
			            .add(Projections.property("district.districtId"),"districtId")  
			            .add(Projections.property("app.talukId"),"talukId"))
			
		.setResultTransformer(Transformers.aliasToBean(DistrictTalukFormBean.class));
			return cr.list();
		}
	
	

	
	
	
	
	
	

public String addVillage(TalukVillageFormBean talukVillageFormBean ){
	
	String[] villageArray = talukVillageFormBean.getVillageName().split(",");
	
	for(int i=0;i<villageArray.length;i++){
	
	Session session2 = sessionFactory.openSession();
	Transaction tx2 =  session2.beginTransaction();
	
	MasterVillage masterVillage = new MasterVillage();
	masterVillage.setTalukId((MasterTaluk)session2.get(MasterTaluk.class,Integer.parseInt(talukVillageFormBean.getTalukVillageId())));
	masterVillage.setVillageName(villageArray[i]);

	masterVillage.setUpdateTs(new Date());
	masterVillage.setCreateTs(new Date());
	masterVillage.setUpdateUserId("Administrator");
	masterVillage.setCreateUserId("Administrator");
	
	session2.save(masterVillage);
	tx2.commit();
	}
		return "Village Added Successfully";
	}
public String editVillage(TalukVillageFormBean talukVillageFormBean ){
	Session session2 = sessionFactory.openSession();
	Transaction tx2 =  session2.beginTransaction();
	
	MasterVillage masterVillage = (MasterVillage)session2.get(MasterVillage.class,Integer.parseInt(talukVillageFormBean.getTalukVillageId()));
	masterVillage.setVillageName(talukVillageFormBean.getVillageName());
	masterVillage.setUpdateTs(new Date());
	masterVillage.setUpdateUserId("Administrator");
	session2.update(masterVillage);
	tx2.commit();
		return "Village Updated Successfully";
	}
	
	public String deleteVillage(TalukVillageFormBean talukVillageFormBean ){
		Session session = sessionFactory.openSession();
		try{
		String[] villageArray = talukVillageFormBean.getTalukVillageId().split(",");
		
			for (int i = 0; i < villageArray.length; i++) {

				Transaction tx = session.beginTransaction();
				int villageid = Integer.parseInt(villageArray[i]);
				MasterVillage masterVillage = (MasterVillage) session.get(MasterVillage.class, villageid);
				session.delete(masterVillage);
				tx.commit();
			}
		}
		catch(Exception e){
			return "Village isn't deleted";
		}
		finally{
			session.close();
		}
			return "Village deleted Successfully";
		}
	public List<TalukVillageFormBean> getVillageDtl(){
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(MasterVillage.class,"app")
				.createCriteria("app.talukId","taluk")
				.setProjection(Projections.projectionList()
			            .add(Projections.property("taluk.talukName"),"talukName")
			            .add(Projections.property("app.villageName"),"villageName")  
			            
			            
			            .add(Projections.property("taluk.talukId"),"talukId")  
			            .add(Projections.property("app.villageId"),"villageId"))
			
		.setResultTransformer(Transformers.aliasToBean(TalukVillageFormBean.class));
			return cr.list();
		}
	
	

	
	
	
	// Maha new method
	
	@Override
	public List<CompanyDtl> listBeforeInspection(CompanyDtlBean companyDtlBean) {
		// TODO Auto-generated method stub

		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<CompanyDtl> companyDtls = new ArrayList<CompanyDtl>();
		try {
		
			
			
			Criteria cr = session.createCriteria(CompanyDtl.class,"app")
					.createCriteria("app.office","office")
					.createCriteria("app.eeStatus","status")
			         .add(Restrictions.eq("status.statusId", 1))
			         .add(Restrictions.eq("office.officeId", Integer.parseInt(companyDtlBean.getOffice())))
					.add(Restrictions.eq("app.statusFlag", 'Y')).add(Restrictions.eq("app.insStatusId",1 ));
			
			companyDtls=cr.list();
			
			

		
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return companyDtls;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}