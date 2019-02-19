package com.water.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;

import sun.security.ssl.Debug;

import com.water.bean.LoginBean;
import com.water.dao.LoginDao;
import com.water.model.EmployeeDetails;
import com.water.model.Login;
import com.water.util.HibernateUtil;

/**
 * @author Mahalingam
 * 
 *         4.1 YTU Login Module
 * 
 *         Created by Mahalingam (Freelancer) On 25-Apr-2017
 * 
 *         This class implements LoginDAO operations, authenticateUSer and
 *         password change(Mobile)
 * 
 */
public class LoginDaoImpl implements LoginDao {

	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ytu.dao.LoginDao#authenticateUser(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public EmployeeDetails authenticateUser(LoginBean loginBean) {

		Session session = sessionFactory.openSession();
		EmployeeDetails loginList = null;
		try {

			Criteria cr = session.createCriteria(EmployeeDetails.class);
			System.out.println(loginBean.getAccessTypeID());

			if (loginBean.getAccessTypeID() != null
					&& loginBean.getLoginName() != null
					&& loginBean.getLoginPassword() != null) {

				List<Integer> accessType = new ArrayList<Integer>();
				
				accessType.add(2);


				cr.add(Restrictions.eq("loginUserName", loginBean.getLoginName())).add(
						Restrictions.eq("loginPassword", loginBean.getLoginPassword()));
				
			

			}
			cr.add(Restrictions.eq("statusFlag", 'Y'));
			

			loginList = (EmployeeDetails) cr.uniqueResult();

		} catch (Exception e) {
			Debug.println("Solution: Please check the Login characters", "");
			e.printStackTrace();
		} finally {
			//session.close();
		}
		return loginList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ytu.dao.LoginDao#changePassword(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public Boolean changePassword(String userID, String oldPassword,
			String newPassword, String accessId) {
		Session session = sessionFactory.openSession();

		Login loginList = null;
		boolean isChanged = false;

		try {
			if (accessId.equals("2")) {
				Criteria cr = session.createCriteria(Login.class);

				cr.add(Restrictions.eq("loginName", userID))
						.add(Restrictions
								.sqlRestriction(
										"loginPassword = ? collate Latin1_General_CS_AS_KS_WS",
										oldPassword, new StringType()));

				cr.setMaxResults(1);
				loginList = (Login) cr.uniqueResult();
			} else if (accessId.equals("1")) {
				Criteria cr = session.createCriteria(Login.class);

				cr.add(Restrictions.eq("loginName", userID))
						.add(Restrictions
								.sqlRestriction(
										"loginPassword = ? collate Latin1_General_CS_AS_KS_WS",
										oldPassword, new StringType()));

				cr.setMaxResults(1);
				loginList = (Login) cr.uniqueResult();
			}
			session.beginTransaction();

			if (loginList != null) {
				loginList.setLoginPassword(newPassword);
				loginList.setModifiedDate(new java.util.Date());
				session.update(loginList);
				session.beginTransaction().commit();
				isChanged = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			isChanged = false;
		} finally {
			session.close();
		}
		return isChanged;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ytu.dao.LoginDao#forgetPassword(java.lang.String)
	 */
	@Override
	public String forgetPassword(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

}
