package com.water.ws;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.FileUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.water.bean.ComplaintBean;
import com.water.bean.DashboardBean;
import com.water.bean.DocumentBean;
import com.water.dao.ComplaintDao;
import com.water.daoImpl.ComplaintDaoImpl;
import com.water.model.ComplaintDetails;
import com.water.model.Documents;
import com.water.util.Common;
import com.water.util.HibernateUtil;
import com.google.gson.Gson;
import com.sun.jersey.core.header.ContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;

/**
 * @author Mahalingam Created on 02-June-2017 for DashboardService :
 * 
 */
@Path("DashboardService")
public class DashboardService {

	ComplaintDao complaintDao;
	Gson gson;

	ResourceBundle rb = ResourceBundle.getBundle("resources/constant");

	String EC_Folder = rb.getString("EC_Folder");

	@POST
	@Path("registerCompliant")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String registerCompliant(ComplaintBean complaintBean) {
		gson = new Gson();
		complaintDao = new ComplaintDaoImpl();
		ComplaintDetails complaintDetails = new ComplaintDetails();

		String complaintContent = complaintBean.getComplaintContent();
		if (complaintContent != null) {
			String SMSText[] = complaintContent.split(" ");
			// CMP 11-0266895 Y
			// complaintBean.setComplaintStatus(1);
			if (SMSText[0].equalsIgnoreCase("cmp")
					&& (SMSText[2].equalsIgnoreCase("y") || SMSText[2]
							.equalsIgnoreCase("n"))) {
				complaintBean.setComplaintStatus(8);
			}
		}
		System.out.println("--- : " + gson.toJson(complaintBean));
		complaintDetails = complaintDao.registerCompliant(complaintBean);
		complaintDetails.setCreatedDate(null);
		return gson.toJson(complaintDetails);

	}

	@POST
	@Path("updateDocumentInfo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateDocumentInfo(ComplaintBean complaintBean) {
		gson = new Gson();
		complaintDao = new ComplaintDaoImpl();
		ComplaintDetails complaintDetails = new ComplaintDetails();
		complaintDetails = complaintDao.updateDocumentInfo(complaintBean);
		return gson.toJson(complaintDetails);
	}

	@POST
	@Path("/listCompliant")
	@Produces(MediaType.APPLICATION_JSON)
	public String listCompliant(ComplaintBean complaintBean) {
		gson = new Gson();
		complaintDao = new ComplaintDaoImpl();

		ComplaintBean CBean;

		List<ComplaintBean> complaintBeanList = new ArrayList<ComplaintBean>();

		for (Object[] complaintDetails : complaintDao
				.listCompliant(complaintBean)) {

			CBean = new ComplaintBean();
			CBean.setComplaintID(Integer.parseInt(String
					.valueOf(complaintDetails[0])));
			CBean.setComplaintNumber(String.valueOf(complaintDetails[1]));

			CBean.setComplaintSubmitterName(String.valueOf(complaintDetails[2]));

			CBean.setComplaintSubmitterMobileNo(String
					.valueOf(complaintDetails[3]));
			CBean.setComplaintSubmitterEmailID(String
					.valueOf(complaintDetails[4]));
			CBean.setComplaintContent(String.valueOf(complaintDetails[5]));

			CBean.setComplaintRegisteredTime(complaintDetails[6] != null ? Common
					.convertDateFormat(String.valueOf(complaintDetails[6]))
					: null);
			// Common.convertDate(String
			// .valueOf(complaintDetails[6])));
			CBean.setComplaintRegisteredBy(complaintDetails[15] != null ? Integer
					.parseInt(String.valueOf(complaintDetails[15])) : null);

			CBean.setComplaintStatus(complaintDetails[7] != null ? Integer
					.parseInt(String.valueOf(complaintDetails[7])) : null);

			CBean.setComplaintStatusName(complaintDetails[8] != null ? String
					.valueOf(complaintDetails[8]) : null);

			CBean.setComplaintSourceName(String.valueOf(complaintDetails[10]));

			CBean.setComplaintSource(complaintDetails[9] != null ? Integer
					.parseInt(String.valueOf(complaintDetails[9])) : null);

			CBean.setComplaintPriority(complaintDetails[11] != null ? Integer
					.parseInt(String.valueOf(complaintDetails[11])) : null);

			CBean.setAssignedAgentID(complaintDetails[13] != null ? Integer
					.parseInt(String.valueOf(complaintDetails[13])) : null);

			CBean.setAssignedAgentName(complaintDetails[14] != null ? String
					.valueOf(complaintDetails[14]) : null);

			// CBean.setComplaintantType(complaintDetails.getComplaintantType());
			CBean.setAgentAssignedTime(complaintDetails[17] != null ? Common
					.convertDBDate(String.valueOf(complaintDetails[17])) : null);

			CBean.setAssignedOfficerID(complaintDetails[18] != null ? Integer
					.parseInt(String.valueOf(complaintDetails[18])) : null);

			CBean.setAssignedOfficerName(complaintDetails[19] != null ? String
					.valueOf(complaintDetails[19]) : null);

			CBean.setOfficerAssignedTime(complaintDetails[20] != null ? Common
					.convertDBDate(String.valueOf(complaintDetails[20])) : null);

			CBean.setComplaintResolvedTime(complaintDetails[21] != null ? Common
					.convertDBDate(String.valueOf(complaintDetails[21])) : null);

			CBean.setComplaintRejectCloseTime(complaintDetails[22] != null ? Common
					.convertDBDate(String.valueOf(complaintDetails[22])) : null);

			CBean.setComplaintCreatedBy(complaintDetails[23] != null ? Integer
					.parseInt(String.valueOf(complaintDetails[23])) : null);

			CBean.setComplaintClosedBy(complaintDetails[24] != null ? Integer
					.parseInt(String.valueOf(complaintDetails[24])) : null);

			CBean.setComplaintClosedName(complaintDetails[25] != null ? String
					.valueOf(complaintDetails[25]) : null);

			CBean.setCreatedDate(complaintDetails[26] != null ? Common
					.convertDBDate(String.valueOf(complaintDetails[26])) : null);

			CBean.setModifiedDate(complaintDetails[27] != null ? Common
					.convertDate(String.valueOf(complaintDetails[27])) : null);

			CBean.setComplaintSubmitterAddress(String
					.valueOf(complaintDetails[28]));

			CBean.setAssignedAgentComments(null != complaintDetails[33] ? String
					.valueOf(complaintDetails[33]) : null);

			CBean.setAssignedOfficerComments(String
					.valueOf(complaintDetails[34]));

			CBean.setComplaintViolationTypeID(null != complaintDetails[31] ? Integer
					.parseInt(String.valueOf(complaintDetails[31])) : null);

			CBean.setComplaintCategoryID(complaintDetails[29] != null ? Integer
					.parseInt(String.valueOf(complaintDetails[29])) : null);

			CBean.setComplaintCategoryName(complaintDetails[30] != null ? String
					.valueOf(complaintDetails[30]) : "");

			CBean.setRejectedReason(complaintDetails[35] != null ? Integer
					.parseInt(String.valueOf(complaintDetails[35])) : null);

			CBean.setComplaintEscalated(complaintDetails[37] != null ? Boolean
					.parseBoolean(String.valueOf(complaintDetails[37])) : null);

			CBean.setComplaintEscalationLevel(complaintDetails[38] != null ? Integer
					.parseInt(String.valueOf(complaintDetails[38])) : null);

			CBean.setLocationID(complaintDetails[39] != null ? Integer
					.parseInt(String.valueOf(complaintDetails[39])) : null);

			CBean.setRoleName(complaintDetails[42] != null ? String
					.valueOf(complaintDetails[42]) : null);

			complaintBeanList.add(CBean);
		}

		return gson.toJson(complaintBeanList);
	}

	@POST
	@Path("/searchCompliant")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchCompliant(ComplaintBean complaintBean) {

		gson = new Gson();
		complaintDao = new ComplaintDaoImpl();

		ComplaintBean CBean = new ComplaintBean();

		for (ComplaintDetails complaintDetails : complaintDao
				.searchCompliant(complaintBean)) {

			CBean.setComplaintID(complaintDetails.getComplaintID());
			CBean.setComplaintNumber(complaintDetails.getComplaintNumber());
			CBean.setComplaintSubmitterName(complaintDetails
					.getComplaintSubmitterName());
			CBean.setComplaintSubmitterMobileNo(complaintDetails
					.getComplaintSubmitterMobileNo());
			CBean.setComplaintSubmitterEmailID(complaintDetails
					.getComplaintSubmitterEmailID());
			CBean.setComplaintContent(complaintDetails.getComplaintContent());
			CBean.setComplaintRegisteredTime(Common
					.convertDate(complaintDetails.getComplaintRegisteredTime()));
			CBean.setComplaintRegisteredBy(complaintDetails
					.getComplaintRegisteredBy());
			CBean.setComplaintStatus(complaintDetails.getComplaintStatus());
			CBean.setComplaintSourceName(complaintDetails
					.getMasterFieldCodeSource().getDerivedValue());
			CBean.setComplaintSource(complaintDetails
					.getMasterFieldCodeSource().getFieldCodeID());

			CBean.setComplaintPriority(complaintDetails.getComplaintPriority());
			CBean.setAssignedAgentID(complaintDetails.getAssignedAgentID());
			// CBean.setComplaintantType(complaintDetails.getComplaintantType());
			CBean.setAgentAssignedTime(complaintDetails.getAgentAssignedTime());
			CBean.setAssignedOfficerID(complaintDetails.getAssignedOfficerID());
			CBean.setOfficerAssignedTime(complaintDetails
					.getOfficerAssignedTime());
			CBean.setComplaintResolvedTime(complaintDetails
					.getComplaintResolvedTime());
			CBean.setComplaintRejectCloseTime(complaintDetails
					.getComplaintRejectCloseTime());
			CBean.setComplaintCreatedBy(complaintDetails
					.getComplaintCreatedBy());
			CBean.setComplaintClosedBy(complaintDetails.getComplaintClosedBy());
			CBean.setCreatedDate(complaintDetails.getCreatedDate());
			CBean.setModifiedDate(complaintDetails.getModifiedDate());
			CBean.setComplaintSubmitterAddress(complaintDetails
					.getComplaintSubmitterAddress());
			CBean.setComplaintViolationTypeID(complaintDetails
					.getComplaintViolationTypeID());

			CBean.setComplaintCategoryID(complaintDetails
					.getComplaintCategoryID());

			// CBean.setComplaintCategoryName(complaintDetails
			// .getComplaintCategoryName());

			CBean.setAssignedAgentComments(complaintDetails
					.getAssignedAgentComments());
			CBean.setAssignedOfficerComments(complaintDetails
					.getAssignedOfficerComments());

			CBean.setRejectedReason(complaintDetails.getRejectedReason());

			CBean.setComplaintEscalated(complaintDetails
					.getComplaintEscalated());
			CBean.setComplaintEscalationLevel(complaintDetails
					.getComplaintEscalationLevel());
			CBean.setLocationID(complaintDetails.getLocationID());

		}

		return gson.toJson(CBean);

	}

	@POST
	@Path("/getCompliantDtls")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCompliantDtls(ComplaintBean complaintBean) {
		gson = new Gson();
		complaintDao = new ComplaintDaoImpl();

		ComplaintBean CBean;

		CBean = new ComplaintBean();
		ComplaintDetails complaintDetails = complaintDao
				.getCompliantDtls(complaintBean);

		CBean.setComplaintID(complaintDetails.getComplaintID());
		CBean.setComplaintNumber(complaintDetails.getComplaintNumber());
		CBean.setComplaintSubmitterName(complaintDetails
				.getComplaintSubmitterName());
		CBean.setComplaintSubmitterMobileNo(complaintDetails
				.getComplaintSubmitterMobileNo());
		CBean.setComplaintSubmitterEmailID(complaintDetails
				.getComplaintSubmitterEmailID());
		CBean.setComplaintContent(complaintDetails.getComplaintContent());
		CBean.setComplaintRegisteredTime(Common.convertDate(complaintDetails
				.getComplaintRegisteredTime()));
		CBean.setComplaintRegisteredBy(complaintDetails
				.getComplaintRegisteredBy());

		CBean.setComplaintViolationTypeID(complaintDetails
				.getComplaintViolationTypeID());
		CBean.setComplaintStatus(complaintDetails.getComplaintStatus());
		CBean.setComplaintSourceName(complaintDetails
				.getMasterFieldCodeSource().getDerivedValue());
		CBean.setComplaintSource(complaintDetails.getMasterFieldCodeSource()
				.getFieldCodeID());

		CBean.setComplaintPriority(complaintDetails.getComplaintPriority());
		CBean.setAssignedAgentID(complaintDetails.getAssignedAgentID());
		CBean.setComplaintantType(complaintDetails.getComplaintRegisteredBy());
		CBean.setAgentAssignedTime(complaintDetails.getAgentAssignedTime());
		CBean.setAssignedOfficerID(complaintDetails.getAssignedOfficerID());
		CBean.setOfficerAssignedTime(complaintDetails.getOfficerAssignedTime());
		CBean.setComplaintResolvedTime(complaintDetails
				.getComplaintResolvedTime());
		CBean.setComplaintRejectCloseTime(complaintDetails
				.getComplaintRejectCloseTime());
		CBean.setComplaintCreatedBy(complaintDetails.getComplaintCreatedBy());
		CBean.setComplaintClosedBy(complaintDetails.getComplaintClosedBy());
		CBean.setCreatedDate(complaintDetails.getCreatedDate());
		CBean.setModifiedDate(complaintDetails.getModifiedDate());
		CBean.setComplaintSubmitterAddress(complaintDetails
				.getComplaintSubmitterAddress());
		CBean.setComplaintViolationTypeID(complaintDetails
				.getComplaintViolationTypeID());

		CBean.setComplaintCategoryID(complaintDetails.getComplaintCategoryID());

		// CBean.setComplaintCategoryName(complaintDetails
		// .getMasterFieldCodecomplaintCategory().getDerivedValue());

		CBean.setAssignedAgentComments(complaintDetails
				.getAssignedAgentComments());
		CBean.setAssignedOfficerComments(complaintDetails
				.getAssignedOfficerComments());

		CBean.setRejectedReason(complaintDetails.getRejectedReason());

		CBean.setComplaintEscalated(complaintDetails.getComplaintEscalated());
		CBean.setComplaintEscalationLevel(complaintDetails
				.getComplaintEscalationLevel());
		CBean.setLocationID(complaintDetails.getLocationID());

		CBean.setPublicCanView(complaintDetails.getPublicCanView() != null ? String
				.valueOf(complaintDetails.getPublicCanView()) : null);

		return gson.toJson(CBean);
	}

	@POST
	@Path("/getDashboardCount")
	@Produces(MediaType.APPLICATION_JSON)
	public String getDashboardCount() {

		complaintDao = new ComplaintDaoImpl();
		gson = new Gson();

		DashboardBean dashboardBean = new DashboardBean();

		for (Object[] rowData : complaintDao.getDashboardCount()) {

			dashboardBean.setTotalComplaints(Integer.parseInt(rowData[0]
					.toString()));

			dashboardBean.setNewComplaints(Integer.parseInt(rowData[1]
					.toString()));
			dashboardBean.setAssignedComplaints(Integer.parseInt(rowData[2]
					.toString()));

			dashboardBean.setAcknowledgeCount(Integer.parseInt(rowData[3]
					.toString()));

			dashboardBean.setClosedByFieldOfficer(Integer.parseInt(rowData[4]
					.toString()));

			dashboardBean.setRelovedCount(Integer.parseInt(rowData[5]
					.toString()));

			dashboardBean.setRejectedCount(Integer.parseInt(rowData[6]
					.toString()));

			dashboardBean.setSMSChannelCount(Integer.parseInt(rowData[7]
					.toString()));
			dashboardBean.setIVRChannelCount(Integer.parseInt(rowData[8]
					.toString()));
			dashboardBean.setEmailChannelCount(Integer.parseInt(rowData[9]
					.toString()));
			dashboardBean.setMobileAppChannelCount(Integer.parseInt(rowData[10]
					.toString()));
			dashboardBean.setWalkinCount(Integer.parseInt(rowData[11]
					.toString()));
			dashboardBean.setTotalChannelCount(Integer.parseInt(rowData[12]
					.toString()));

			dashboardBean.setYesCount(Integer.parseInt(rowData[13].toString()));

			dashboardBean.setNoCount(Integer.parseInt(rowData[14].toString()));

			dashboardBean.setNACount(Integer.parseInt(rowData[15].toString()));
			dashboardBean.setEscalationCount(Integer.parseInt(rowData[16]
					.toString()));
			dashboardBean.setTappalCount(Integer.parseInt(rowData[17]
					.toString()));

		}
		return gson.toJson(dashboardBean);
	}

	@SuppressWarnings("resource")
	@POST
	@Path("/fileUpload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public String fileUpload(FormDataMultiPart multipartdata) {
		InputStream inputstream = null;
		OutputStream outputstream = null;
		Session session = null;
		Transaction transaction = null;
		String result = null;

		try {
			if (multipartdata != null) {

				Integer userId = Integer.parseInt(multipartdata.getField(
						"userId").getValue());

				String complaintNumber = multipartdata.getField(
						"complaintNumber").getValue();

				Integer complaintID = Integer.parseInt(multipartdata.getField(
						"complaint_No").getValue());

				FormDataBodyPart bodypartdata = multipartdata
						.getField("fileObject");
				ContentDisposition contentdisposition = bodypartdata
						.getContentDisposition();

				InputStream uploadedInputStream = bodypartdata
						.getValueAs(InputStream.class);
				try {

					if (!new File(EC_Folder + complaintNumber + "//").exists())
						new File(EC_Folder + complaintNumber).mkdirs();

					OutputStream outpuStream = new FileOutputStream(new File(
							EC_Folder + complaintNumber + "//"
									+ contentdisposition.getFileName()));

					int read = 0;

					byte[] bytes = new byte[1024];

					outpuStream = new FileOutputStream(new File(EC_Folder
							+ complaintNumber + "//"
							+ contentdisposition.getFileName()));

					while ((read = uploadedInputStream.read(bytes)) != -1) {
						outpuStream.write(bytes, 0, read);
					}
					outpuStream.flush();
					outpuStream.close();

				} catch (IOException e) {
					e.printStackTrace();

				}

				// FileUtils.copyFileToDirectory(bodypartdata,
				// directoryPath);
				Long filesize = Long.valueOf(multipartdata.getField("filesize")
						.getValue());

				if (filesize == null) {
					return result = "Folder size has exceeded";
				} else {
					String filepath = EC_Folder + complaintNumber + "//"
							+ contentdisposition.getFileName();

					session = HibernateUtil.getSessionFactory().openSession();
					transaction = session.beginTransaction();
					transaction.begin();
					Integer complaintId = (Integer) session
							.createCriteria(ComplaintDetails.class)
							.add(Restrictions.eq("complaintID", complaintID))
							.setProjection(Projections.property("complaintID"))
							.setMaxResults(1).uniqueResult();
					System.out.println("ComplainId : " + complaintId);

					if (complaintId != null) {
						inputstream = bodypartdata
								.getValueAs(InputStream.class);
						outputstream = new FileOutputStream(filepath);
						int read = 0;
						byte[] bytes = new byte[2048];
						while ((read = inputstream.read(bytes)) != -1) {
							outputstream.write(bytes, 0, read);
						}
						outputstream.flush();

						Documents document = new Documents();
						if (userId > 0)
							document.setDocumentOwner(userId);
						else
							document.setDocumentOwner(0);

						document.setComplaintID(complaintId);
						document.setCreatedDate(new Date());
						document.setDocumentPath(complaintNumber + "//"
								+ contentdisposition.getFileName());
						document.setIsActive(true);
						session.save(document);
						result = "success";
					} else {
						result = "error";
					}

					transaction.commit();
				}

			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null)
				transaction.rollback();
			return null;
		} finally {
			if (inputstream != null) {
				try {
					inputstream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputstream != null) {
				try {
					outputstream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (session != null) {
				session.flush();
				session.close();
			}
		}
	}

	@GET
	@Path("registerIVRCompliant")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String registerIVRCompliant(@QueryParam("mobileNo") String mobileNo,
			@QueryParam("filepath") String filepath) {

		ComplaintBean complaintBean = new ComplaintBean();
		ComplaintDetails complaintDetails = new ComplaintDetails();
		complaintDao = new ComplaintDaoImpl();
		gson = new Gson();
		try {
			if (mobileNo != null && filepath != null) {

				complaintBean.setComplaintSubmitterMobileNo(mobileNo);
				complaintBean.setIVR_filePath(filepath);
				complaintBean.setComplaintSource(2);
				complaintBean.setLocationID(11);
				complaintDetails = complaintDao
						.registerCompliant(complaintBean);
				complaintDetails.setCreatedDate(null);

				// moveFile(mobileNo, filepath);

				String complaintNo = complaintDetails.getComplaintNumber();

				if (complaintNo != null) {

					File sourceFile = new File(filepath);

					String destFilePath = EC_Folder + mobileNo;

					if (!new File(destFilePath).exists()) {
						new File(destFilePath).mkdirs();
					}

					FileUtils
							.copyFile(
									new File(EC_Folder + sourceFile.getName()),
									new File(destFilePath + "//"
											+ sourceFile.getName()));

					DocumentBean documentBean = new DocumentBean();
					documentBean.setComplaintID(complaintDetails
							.getComplaintID());
					documentBean.setDocumentPath(mobileNo + "//"
							+ sourceFile.getName());
					documentBean.setDocumentOwner(0);
					documentBean.setIsActive(true);
					documentBean.setCreatedDate(new java.util.Date());

					complaintDao.insertAudioInfo(documentBean);

					new File(EC_Folder + sourceFile.getName()).delete();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return gson.toJson(complaintDetails);

	}

	@GET
	@Path("registerSMSCompliant")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String registerSMSCompliant(
			@QueryParam("complaintSource") Integer complaintSource,
			@QueryParam("complaintSubmitterMobileNo") String complaintSubmitterMobileNo,
			@QueryParam("complaintContent") String complaintContent) {

		ComplaintBean complaintBean = new ComplaintBean();
		complaintBean.setLocationID(11);

		System.out.println(complaintSource + "" + complaintSubmitterMobileNo
				+ "" + complaintContent);

		if (complaintSource != null && (complaintSubmitterMobileNo != null)
				&& complaintContent != null) {

			System.out.println(complaintContent);
			String SMSText[] = complaintContent.split(" ");
			// CMP 11-0266895 Y

			if (SMSText[0].equalsIgnoreCase("cmp")
					&& (SMSText[2].equalsIgnoreCase("y") || SMSText[2]
							.equalsIgnoreCase("n"))) {

				complaintBean.setComplaintStatus(8);

				complaintBean.setComplaintSource(complaintSource);

				complaintBean
						.setComplaintSubmitterMobileNo(complaintSubmitterMobileNo);
				complaintBean.setComplaintContent(complaintContent);

				complaintBean.setComplaintSource(1);
				gson = new Gson();
				complaintDao = new ComplaintDaoImpl();

				// complaintDao.registerCompliant(complaintBean)
				// .getComplaintNumber());
				if (complaintDao.registerCompliant(complaintBean)
						.getComplaintNumber() != null)
					return "Thank you for the feedback!";
				else
					return "Failed, Please try again later!";
			} else {

				complaintBean.setComplaintStatus(1);
				complaintBean.setComplaintSource(complaintSource);
				complaintBean
						.setComplaintSubmitterMobileNo(complaintSubmitterMobileNo);
				complaintBean.setComplaintContent(complaintContent);

				complaintBean.setComplaintSource(1);
				// complaintBean.setComplaintRegisteredTime(complaintRegisteredTime););

				gson = new Gson();
				complaintDao = new ComplaintDaoImpl();
				return gson.toJson("complaintNumber : "
						+ complaintDao.registerCompliant(complaintBean)
								.getComplaintNumber());
			}
		} else {
			return "Successfully updated, Thanks!";
		}

	}

	@POST
	@Path("/getPublicDashboardCount")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPublicDashboardCount(ComplaintBean complaintBean) {

		complaintDao = new ComplaintDaoImpl();
		gson = new Gson();

		DashboardBean dashboardBean;

		List<DashboardBean> dashboardBeanList = new ArrayList<DashboardBean>();

		for (Object[] rowData : complaintDao
				.getPublicDashboardCount(complaintBean)) {
			dashboardBean = new DashboardBean();
			dashboardBean
					.setCategoryID(Integer.parseInt(rowData[0].toString()));
			dashboardBean.setCategoryName(rowData[1].toString());
			dashboardBean.setCategoryCount(Integer.parseInt(rowData[2]
					.toString()));
			dashboardBean.setIcon((rowData[3].toString()));
			dashboardBean.setBgColor((rowData[4].toString()));
			dashboardBeanList.add(dashboardBean);
		}
		return gson.toJson(dashboardBeanList);
	}

	
}
