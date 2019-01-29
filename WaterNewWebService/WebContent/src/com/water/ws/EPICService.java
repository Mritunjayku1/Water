/**
 * 
 */
package com.water.ws;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.water.dao.EPICDao;
import com.water.daoImpl.EPICDaoImpl;
import com.google.gson.Gson;

/**
 * @author Mahalingam
 * 
 *         Created on 02-06-2017 for Utility service, It contains dropdownlist
 *         services:
 * 
 * 
 * 
 */
@Path("EPICService")
public class EPICService {

	private static Logger logger = Logger.getLogger(UtilService.class);

	@POST
	@Path("getEPICDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public String epicSearch(@QueryParam("EPIC") String EPIC,
			@QueryParam("channelID") Integer channelID,
			@QueryParam("from") String from) {
		EPICDao dao = new EPICDaoImpl();
		return new Gson().toJson(dao.getEPICDetails(EPIC, channelID, from));

	}

}
