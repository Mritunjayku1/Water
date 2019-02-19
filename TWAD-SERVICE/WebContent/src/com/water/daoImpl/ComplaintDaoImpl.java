package com.water.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.water.bean.ComplaintBean;
import com.water.bean.DocumentBean;
import com.water.dao.ComplaintDao;
import com.water.model.ComplaintDetails;
import com.water.model.Documents;
import com.water.model.MasterFieldCode;
import com.water.util.Common;
import com.water.util.Constant;
import com.water.util.HibernateUtil;
import com.water.util.SMSBuilder;
import com.google.gson.Gson;

/**
 * @author Mahalingam
 * 
 * 
 * 
 *         Created by Mahalingam (Freelancer) On 02-June-2017
 * 
 * 
 * 
 */
public class ComplaintDaoImpl implements ComplaintDao {

	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ytu.dao.ComplaintDao#registerCompliant(com.ytu.bean.ComplaintBean)
	 */
	@Override
	public ComplaintDetails registerCompliant(ComplaintBean complaintBean) {

		Session session = sessionFactory.openSession();
		String complaintID = "";

		ComplaintDetails complaintDetails = null;
		System.out.println(new Gson().toJson(complaintBean));
		try {

			if (complaintBean.getComplaintEscalated() == null
					|| "".equals(complaintBean.getComplaintEscalated())) {
				complaintBean.setComplaintEscalated(false);
			} else {
				complaintBean.setComplaintEscalated(true);
			}

			if (complaintBean.getComplaintID() != null
					&& complaintBean.getComplaintID() > 0) {

				session.beginTransaction();
				complaintDetails = new ComplaintDetails();

				if ((complaintBean.getComplaintEscalated() && null != complaintBean
						.getComplaintEscalated())
						&& (complaintBean.getComplaintStatus() != null && (complaintBean
								.getComplaintStatus() == 2 || complaintBean
								.getComplaintStatus() == 5))) {

					System.out
							.println("Re-Assigned Queue >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.");

					complaintDetails = new ComplaintDetails();
//					SQLQuery query = session
//							.createSQLQuery(Constant.FieldOfficer_Escalation_Update);
					SQLQuery query = session
							.createSQLQuery(Constant.FieldOfficer_Escalation_Select);
					
					query.setParameter(0, 2);
					query.setParameter(1, 1);
					query.setParameter(2,
							complaintBean.getComplaintEscalationLevel());
					query.setParameter(3, complaintBean.getAssignedOfficerID());
					query.setParameter(4, new java.util.Date());
					query.setParameter(5, complaintBean.getComplaintID());

					query.executeUpdate();
					session.beginTransaction().commit();
					complaintDetails.setComplaintID(complaintBean
							.getComplaintID());
				}

				else if (complaintBean.getComplaintStatus() != null
						&& complaintBean.getComplaintStatus() == 5
						&& complaintBean.getAssignedOfficerID() != null) {

					complaintDetails = new ComplaintDetails();
					SQLQuery query = session
							.createSQLQuery(Constant.FieldOfficer_Acknowledge);
					query.setParameter(0, 5);
					query.setParameter(1, new java.util.Date());
					query.setParameter(2, complaintBean.getComplaintID());
					query.setParameter(3, complaintBean.getAssignedOfficerID());
					int resultCnt = query.executeUpdate();
					session.beginTransaction().commit();
					if (resultCnt > 0) {
						complaintDetails.setComplaintID(complaintBean
								.getComplaintID());
					}
				}

				else if (complaintBean.getComplaintStatus() != null
						&& complaintBean.getAssignedOfficerID() != null
						&& complaintBean.getComplaintStatus() == 6) {

					complaintDetails = new ComplaintDetails();
					SQLQuery query = session
							.createSQLQuery(Constant.FieldOfficer_ComplaintClose);
					query.setParameter(0, new java.util.Date());
					query.setParameter(1,
							complaintBean.getAssignedOfficerComments());
					query.setParameter(2, 6);
					query.setParameter(3, complaintBean.getAssignedOfficerID());
					query.setParameter(4, complaintBean.getComplaintID());
					query.setParameter(5, complaintBean.getAssignedOfficerID());
					int resultCnt = query.executeUpdate();
					session.beginTransaction().commit();
					if (resultCnt > 0) {
						complaintDetails.setComplaintID(complaintBean
								.getComplaintID());
					}
				}

				else if (complaintBean.getComplaintStatus() != null
						&& complaintBean.getComplaintStatus() == 7) {

					complaintDetails = new ComplaintDetails();

					SQLQuery query = session
							.createSQLQuery(Constant.Agent_ComplaintClose);
					query.setParameter(0,
							complaintBean.getAssignedAgentComments());
					query.setParameter(1, 7);
					query.setParameter(2, new java.util.Date());

					query.setParameter(3, false);
					if (complaintBean.getPublicCanView().equals("1"))
						query.setParameter(3, true);

					query.setParameter(4, complaintBean.getComplaintID());
					query.executeUpdate();
					session.beginTransaction().commit();
					complaintDetails.setComplaintID(complaintBean
							.getComplaintID());

					System.out.println("SMSBuilder Calling on complaint close "
							+ complaintBean.getComplaintID());

					final Integer application_ID = complaintBean.getComplaintID();
					Thread notify = new Thread(new Runnable() {
						@Override
						public void run() {
							SMSBuilder obj = new SMSBuilder();
							obj.getSmsTemplate(application_ID,1);
						}
					}, "notify");
					notify.start();

				}

				else if (complaintBean.getIsReject() != null
						&& complaintBean.getIsReject().equals("Y")
						&& complaintBean.getComplaintStatus() == 4) {
					complaintDetails = new ComplaintDetails();
					SQLQuery query = session
							.createSQLQuery(Constant.Reject_Complaint);
					query.setParameter(0, new java.util.Date());
					query.setParameter(1, 4);
					query.setParameter(2,
							complaintBean.getAssignedAgentComments());
					query.setParameter(3, complaintBean.getRejectedReason());
					query.setParameter(4,
							complaintBean.getComplaintCategoryID());
					query.setParameter(5,
							complaintBean.getComplaintViolationTypeID());

					query.setParameter(6,
							complaintBean.getComplaintRegisteredBy());

					query.setParameter(7,
							complaintBean.getComplaintSubmitterName());

					query.setParameter(8,
							complaintBean.getComplaintSubmitterAddress());

					query.setParameter(9,
							complaintBean.getComplaintSubmitterEmailID());

					query.setParameter(10, complaintBean.getComplaintID());

					query.executeUpdate();
					session.beginTransaction().commit();
					complaintDetails.setComplaintID(complaintBean
							.getComplaintID());

					System.out
							.println("SMSBuilder Calling on complaint Reject "
									+ complaintBean.getComplaintID());
					final Integer application_ID = complaintBean.getComplaintID();
					Thread notify = new Thread(new Runnable() {
						@Override
						public void run() {
							SMSBuilder obj = new SMSBuilder();
							obj.getSmsTemplate(application_ID,1);
						}
					}, "notify");
					notify.start();

				} else if (complaintBean.getIsReject() != null
						&& complaintBean.getComplaintStatus() == 2) {

					complaintDetails = new ComplaintDetails();
					SQLQuery query = session
							.createSQLQuery(Constant.Update_Complaint);
					query.setParameter(0, new java.util.Date());
					query.setParameter(1, complaintBean.getAssignedAgentID());
					query.setParameter(2, complaintBean.getAssignedOfficerID());
					query.setParameter(3, complaintBean.getComplaintContent());
					query.setParameter(4, complaintBean.getComplaintPriority());
					query.setParameter(5,
							complaintBean.getComplaintRegisteredBy());
					query.setParameter(6, 2);
					query.setParameter(7,
							complaintBean.getComplaintSubmitterAddress());
					query.setParameter(8,
							complaintBean.getComplaintSubmitterEmailID());
					query.setParameter(9,
							complaintBean.getComplaintSubmitterMobileNo());
					query.setParameter(10,
							complaintBean.getComplaintSubmitterName());
					query.setParameter(11,
							complaintBean.getComplaintViolationTypeID());
					query.setParameter(12, new java.util.Date());
					query.setParameter(13, new java.util.Date());
					query.setParameter(14,
							complaintBean.getComplaintCategoryID());
					query.setParameter(15, complaintBean.getComplaintID());
					query.executeUpdate();
					session.beginTransaction().commit();
					complaintDetails.setComplaintID(complaintBean
							.getComplaintID());
					System.out.println("SMSBuilder Calling Assign Complaint"
							+ complaintDetails.getComplaintID());

					// Assign Notification sms to FO and Citizen
					final Integer application_ID = complaintDetails
							.getComplaintID();
					Thread notify = new Thread(new Runnable() {
						@Override
						public void run() {
							SMSBuilder obj = new SMSBuilder();
							obj.getSmsTemplate(application_ID,1);
						}
					}, "notify");
					notify.start();

				}

				else if (complaintBean.getComplaintStatus() != null
						&& complaintBean.getComplaintStatus() == 25) {

					complaintDetails = new ComplaintDetails();
					SQLQuery query = session
							.createSQLQuery(Constant.Update_ReAssign);
					query.setParameter(0, new java.util.Date());
					query.setParameter(1, complaintBean.getAssignedAgentID());
					query.setParameter(2, complaintBean.getAssignedOfficerID());
					query.setParameter(3, complaintBean.getComplaintContent());
					query.setParameter(4, complaintBean.getComplaintPriority());
					query.setParameter(5, new java.util.Date());
					query.setParameter(6, 2);
					query.setParameter(7, complaintBean.getComplaintID());
					query.executeUpdate();
					session.beginTransaction().commit();

					System.out
							.println("SMSBuilder Calling Re-Assign Complaint : "
									+ complaintBean.getComplaintID());

					// Assign Notification sms to FO and Citizen
					final Integer application_ID = complaintDetails
							.getComplaintID();
					Thread notify = new Thread(new Runnable() {
						@Override
						public void run() {
							SMSBuilder obj = new SMSBuilder();
							obj.getSmsTemplate(application_ID,1);
						}
					}, "notify");
					notify.start();

				}

			} else if (complaintBean.getComplaintContent() != null
					&& (complaintBean.getComplaintSource() == 1 || complaintBean
							.getComplaintSource() == 3)
					&& (complaintBean.getComplaintSubmitterMobileNo() != null || complaintBean
							.getComplaintSubmitterEmailID() != null)
					&& complaintBean.getComplaintStatus() != null
					&& complaintBean.getComplaintStatus() == 8) {

				System.out.println("---------------- feedbak source--------"
						+ complaintBean.getComplaintContent());

				// Feed back from user Y/N via SMS and Email
				String complaintContent = complaintBean.getComplaintContent();
				String SMSText[] = complaintContent.split(" ");
				// CMP 11-0266895 Y
				if (SMSText[0].equalsIgnoreCase("cmp")) {

					session.beginTransaction();

					complaintDetails = new ComplaintDetails();
					complaintDetails = (ComplaintDetails) session
							.createCriteria(ComplaintDetails.class)
							.add(Restrictions.eq("complaintNumber", SMSText[1]))
							.setMaxResults(1).uniqueResult();

					complaintDetails.setComplaintID(complaintDetails
							.getComplaintID());
					complaintDetails.setCustomerFeedBack(SMSText[2]);

					session.update(complaintDetails);
					session.beginTransaction().commit();

				}

			} else {

				complaintDetails = new ComplaintDetails();
				complaintDetails.setComplaintSubmitterName(complaintBean
						.getComplaintSubmitterName());
				complaintDetails.setComplaintSubmitterMobileNo(complaintBean
						.getComplaintSubmitterMobileNo());
				complaintDetails.setComplaintRegisteredBy(complaintBean
						.getComplaintRegisteredBy());
				complaintDetails.setComplaintSubmitterEmailID(complaintBean
						.getComplaintSubmitterEmailID());
				complaintDetails.setComplaintContent(complaintBean
						.getComplaintContent());
				if (complaintBean.getComplaintRegisteredTime() != null)
					complaintDetails.setComplaintRegisteredTime(Common
							.convertDate(complaintBean
									.getComplaintRegisteredTime()));
				else
					complaintDetails
							.setComplaintRegisteredTime(new java.util.Date());

				complaintDetails.setCreatedDate(new java.util.Date());
				complaintDetails.setComplaintStatus(1);

				MasterFieldCode MFD = new MasterFieldCode();
				MFD.setFieldCodeID(complaintBean.getComplaintSource());
				complaintDetails.setMasterFieldCodeSource(MFD);

				if (complaintBean.getLocationID() == null)
					complaintBean.setLocationID(11);

				System.out.println("complaintBean.getLocationID() : "
						+ complaintBean.getLocationID());
				complaintDetails.setComplaintNumber(complaintBean
						.getLocationID() + "-" + Common.getComplaintNo(7));

				complaintDetails.setComplaintSubmitterAddress(complaintBean
						.getComplaintSubmitterAddress());

				complaintDetails.setLocationID(complaintBean.getLocationID());
				complaintDetails.setLocation(complaintBean.getLocation());

				complaintDetails.setComplaintViolationTypeID(complaintBean
						.getComplaintViolationTypeID());

				complaintDetails.setComplaintCategoryID(complaintBean
						.getComplaintCategoryID());

				complaintDetails.setComplaintCreatedBy(complaintBean
						.getAssignedAgentID());

				complaintDetails.setPublicCanView(true);

				session.beginTransaction();
				session.save(complaintDetails);
				session.beginTransaction().commit();
				complaintID = complaintDetails.getComplaintNumber();

				// sms call
				System.out.println("SMSBuilder Calling Register Complaint"
						+ complaintDetails.getComplaintID());

				final Integer application_ID = complaintDetails.getComplaintID();
				Thread notify = new Thread(new Runnable() {
					@Override
					public void run() {
						SMSBuilder obj = new SMSBuilder();
						obj.getSmsTemplate(application_ID,1);
					}
				}, "notify");
				notify.start();

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return complaintDetails;
	}

	public static void main(String[] args) {
		new ComplaintDaoImpl().listCompliant(new ComplaintBean());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ytu.dao.ComplaintDao#getCompliantDtls(com.ytu.bean.ComplaintBean)
	 */
	@Override
	public ComplaintDetails getCompliantDtls(ComplaintBean complaintBean) {
		// TODO Auto-generated method stub

		ComplaintDetails complaintDetails = new ComplaintDetails();
		Session session = sessionFactory.openSession();
		try {
			Criteria cr = session.createCriteria(ComplaintDetails.class);
			cr.add(Restrictions.eq("complaintID",
					complaintBean.getComplaintID()));
			complaintDetails = (ComplaintDetails) cr.setMaxResults(1)
					.uniqueResult();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return complaintDetails;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ytu.dao.ComplaintDao#listCompliant(com.ytu.bean.ComplaintBean)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> listCompliant(ComplaintBean complaintBean) {
		Session session = sessionFactory.openSession();
		List<Object[]> complaintDetails = new ArrayList<Object[]>();

		System.out.println(new Gson().toJson(complaintBean));
		try {

			StringBuffer complaintDtlsQuery = new StringBuffer();
			complaintDtlsQuery.append(Constant.Get_ComplaintDtls);
			SQLQuery query;

			boolean isMobile = false;
			boolean isSource = false;
			boolean isComplaintNo = false;
			boolean isStatus = false;
			boolean isCategory = false;
			boolean isEscalated = false;
			boolean isComplaintID = false;

			if (complaintBean.getAccessTypeID() != null
					&& complaintBean.getAccessTypeID() == 2) {

				if (complaintBean.getComplaintSubmitterMobileNo() != null
						&& !"".equals(complaintBean
								.getComplaintSubmitterMobileNo())) {

					complaintDtlsQuery
							.append(" AND ComplaintSubmitterMobileNo=:complaintSubmitterMobileNo");

					isMobile = true;

				}

				if (complaintBean.getComplaintSource() != null
						&& !"".equals(complaintBean.getComplaintSource())) {

					complaintDtlsQuery
							.append(" AND ComplaintSource=:complaintSource");

					isSource = true;

				}

				if (complaintBean.getComplaintNumber() != null
						&& !"".equals(complaintBean.getComplaintNumber())) {

					complaintDtlsQuery
							.append(" AND ComplaintNumber=:complaintNumber");
					isComplaintNo = true;

				}

				if (complaintBean.getComplaintID() != null
						&& !"".equals(complaintBean.getComplaintID())) {

					complaintDtlsQuery.append(" AND ComplaintID=:complaintID");

					isComplaintID = true;

				}

				if (complaintBean.getComplaintStatus() != null
						&& complaintBean.getComplaintStatus() != 0
						&& complaintBean.getComplaintStatus() != 10) {

					complaintDtlsQuery
							.append(" AND ComplaintStatus=:complaintStatus");

					isStatus = true;

				}

				if (complaintBean.getComplaintStatus() != null
						&& complaintBean.getComplaintStatus() != 0
						&& complaintBean.getComplaintStatus() == 10) {
					isEscalated = true;
				}

				if (complaintBean.getComplaintCategoryID() != null) {

					if (complaintBean.getComplaintCategoryID() > 0) {
						complaintDtlsQuery
								.append(" AND ComplaintCategoryID=:complaintCategoryID  AND ComplaintStatus=7 and PublicCanView=1 ");
						isCategory = true;
					} else {
						complaintDtlsQuery
								.append(" AND ComplaintStatus=7 and PublicCanView=1 ");
					}

					if (complaintBean.getCompliantClosedDate() != null
							&& !"".equals(complaintBean
									.getCompliantClosedDate())) {
						complaintDtlsQuery
								.append("  AND convert(date,ComplaintRejectCloseTime)='"
										+ Common.convertDateFormat(complaintBean
												.getCompliantClosedDate())
										+ "'");

					}

				}

				if (isEscalated)
					complaintDtlsQuery
							.append(" AND ComplaintStatus=2 and ComplaintEscalated=1 ");

				complaintDtlsQuery.append(" Order by CD.CreatedDate desc ");

				query = session.createSQLQuery(complaintDtlsQuery.toString());

				if (isMobile)
					query.setParameter("complaintSubmitterMobileNo",
							complaintBean.getComplaintSubmitterMobileNo());

				if (isSource)
					query.setParameter("complaintSource",
							complaintBean.getComplaintSource());

				if (isComplaintNo)
					query.setParameter("complaintNumber",
							complaintBean.getComplaintNumber());
				if (isComplaintID)
					query.setParameter("complaintID",
							complaintBean.getComplaintID());

				if (isStatus)
					query.setParameter("complaintStatus",
							complaintBean.getComplaintStatus());

				if (isCategory)
					query.setParameter("complaintCategoryID",
							complaintBean.getComplaintCategoryID());

				complaintDetails = query.list();

			} else if (complaintBean.getAccessTypeID() != null
					&& complaintBean.getAccessTypeID() == 1) {

				complaintDtlsQuery.append(" AND ComplaintStatus in (2,5) ");

				boolean isOfficer = false;
				if (complaintBean.getAssignedOfficerID() != null
						&& complaintBean.getAssignedOfficerID() != 0) {

					complaintDtlsQuery
							.append(" AND AssignedOfficerID= :assignedOfficerID");

					complaintDtlsQuery
							.append(" Order by ComplaintPriority, CD.CreatedDate asc ");

					isOfficer = true;
				}

				query = session.createSQLQuery(complaintDtlsQuery.toString());

				if (isOfficer)
					query.setParameter("assignedOfficerID",
							complaintBean.getAssignedOfficerID());

				complaintDetails = query.list();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return complaintDetails;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ytu.dao.ComplaintDao#getDashboardCount()
	 */
	@Override
	public List<Object[]> getDashboardCount() {

		Session session = sessionFactory.openSession();
		StringBuffer sqlQreyString = new StringBuffer();

		List<Object[]> dashBoardCount = new ArrayList<Object[]>();
		//sqlQreyString.append(Constant.Get_dashboardCount);
		sqlQreyString.append(Constant.Get_dashboardCountcha);
		
		SQLQuery query = session.createSQLQuery(sqlQreyString.toString());
		dashBoardCount = query.list();
		return dashBoardCount;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ytu.dao.ComplaintDao#insertAudioInfo(java.lang.String)
	 */
	@Override
	public Boolean insertAudioInfo(DocumentBean documentBean) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Documents documents = new Documents();
			documents.setComplaintID(documentBean.getComplaintID());
			documents.setDocumentPath(documentBean.getDocumentPath());
			documents.setDocumentOwner(documentBean.getDocumentOwner());
			documents.setIsActive(true);
			documents.setCreatedDate(new java.util.Date());
			session.save(documents);
			session.beginTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ytu.dao.ComplaintDao#searchCompliant(com.ytu.bean.ComplaintBean)
	 */
	@Override
	public List<ComplaintDetails> searchCompliant(ComplaintBean complaintBean) {
		Session session = sessionFactory.openSession();
		List<ComplaintDetails> complaintDetails = new ArrayList<ComplaintDetails>();

		System.out.println(new Gson().toJson(complaintBean));
		try {
			Criteria cr = session.createCriteria(ComplaintDetails.class);

			if (complaintBean.getAccessTypeID() != null
					&& complaintBean.getAccessTypeID() == 1) {

				if (complaintBean.getComplaintSubmitterMobileNo() != null
						&& complaintBean.getComplaintNumber() != null) {
					cr.add(Restrictions.eq("complaintSubmitterMobileNo",
							complaintBean.getComplaintSubmitterMobileNo()));

					cr.add(Restrictions.eq("complaintNumber",
							complaintBean.getComplaintNumber()));
					complaintDetails = (List<ComplaintDetails>) cr.list();

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return complaintDetails;
	}

	public List<Object[]> listEscalatedComplaints() {
		Session session = sessionFactory.openSession();
		StringBuffer sqlQreyString = new StringBuffer();

		List<Object[]> listcompliants = new ArrayList<Object[]>();
		sqlQreyString.append(Constant.FieldOfficer_Escalation_Select);
		SQLQuery query = session.createSQLQuery(sqlQreyString.toString());
		// query.setParameter("complaintID", compliantId);
		listcompliants = query.list();
		return listcompliants;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.water.dao.ComplaintDao#uploadDocumentInfo(com.water.bean.ComplaintBean)
	 */
	@Override
	public ComplaintDetails updateDocumentInfo(ComplaintBean complaintBean) {

		Session session = sessionFactory.openSession();
		try {

			session.beginTransaction();

			Documents documents = new Documents();
			documents.setComplaintID(complaintBean.getComplaintID());
			documents.setDocumentPath(complaintBean.getAttachementPath());
			documents.setDocumentOwner(complaintBean.getAttachementOwner());
			documents.setIsActive(true);
			documents.setCreatedDate(new java.util.Date());

			session.save(documents);
			session.beginTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Object[]> getPublicDashboardCount(ComplaintBean complaintBean) {

		Session session = sessionFactory.openSession();
		StringBuffer sqlQreyString = new StringBuffer();

		List<Object[]> publicDashBoardCount = new ArrayList<Object[]>();
		sqlQreyString.append(Constant.Get_publicdashboardCount);

		if (complaintBean.getAccessTypeID() != null
				&& complaintBean.getAccessTypeID() == 2) {
			sqlQreyString
					.append("  AND ComplaintStatus=7 and PublicCanView=1  ");
		}
		if (complaintBean.getCompliantClosedDate() != null
				&& !"".equals(complaintBean.getCompliantClosedDate())) {

			sqlQreyString
					.append(" AND convert(date,ComplaintRejectCloseTime)='"
							+ Common.convertDateFormat(complaintBean
									.getCompliantClosedDate()) + "'");

			System.out.println(Common.convertDateFormat(complaintBean
					.getCompliantClosedDate()));
		}
		sqlQreyString.append(Constant.Get_publicdashboardCount_1);

		SQLQuery query = session.createSQLQuery(sqlQreyString.toString());

		publicDashBoardCount = query.list();
		return publicDashBoardCount;

	}
}
