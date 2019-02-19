package com.water.daoImpl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.water.dao.EPICDao;
import com.water.model.EPICDetails;
import com.water.model.EPICLog;
import com.water.util.HibernateUtil;

/**
 * @author Mahalingam
 * 
 * 
 * 
 *         Created by Mahalingam (Freelancer) On 2e-June-2017
 * 
 *         This class implements EPICDao operations
 * 
 */
public class EPICDaoImpl implements EPICDao {

	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.water.dao.EPICDao#getEPICDetails(java.lang.String)
	 */
	@Override
	public EPICDetails getEPICDetails(String EPIC, Integer channelID,
			String from) {
		EPICDetails epciDetails = new EPICDetails();
		Session session = sessionFactory.openSession();
		try {
			Criteria cr = session.createCriteria(EPICDetails.class);
			cr.add(Restrictions.eq("epicNumber", EPIC));
			epciDetails = (EPICDetails) cr.setMaxResults(1).uniqueResult();
			logs(EPIC, channelID, from);

			if (epciDetails == null) {
				epciDetails = new EPICDetails();
				epciDetails.setEpicNumber("invalid");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return epciDetails;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.water.dao.EPICDao#logs(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Boolean logs(String EPIC, Integer channelID, String from) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		EPICLog log = new EPICLog();
		log.setEpicNumber(EPIC);
		log.setFrom(from);
		log.setChannelID(channelID);
		log.setCreatedDate(new java.util.Date());
		session.save(log);
		session.beginTransaction().commit();
		return true;
	}
}
