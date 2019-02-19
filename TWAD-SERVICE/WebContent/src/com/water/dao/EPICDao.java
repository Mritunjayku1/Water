package com.water.dao;

import com.water.model.EPICDetails;

/**
 * @author Mahalingam
 * 
 *         ComplaintDao.java
 * 
 *         Created by Mahalingam (Freelancer) On 24-June-2017
 * 
 *         This Interface is used for EPIC
 * 
 */
public interface EPICDao {

	public EPICDetails getEPICDetails(String EPIC, Integer channelID,
			String from);

	public Boolean logs(String EPIC, Integer channelID, String from);
}
