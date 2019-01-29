package com.water.controller;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.GroupLayout.Alignment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.water.bean.AppFormBean;
import com.water.bean.ApplicationBean;
import com.water.bean.CategoryFormBean;
import com.water.bean.CmwWaterConnBean;
import com.water.bean.ComplaintBean;
import com.water.bean.ConnectionFormBean;
import com.water.bean.DashboardBean;
import com.water.bean.DashboardBeanList;
import com.water.bean.DashboardCountBean;
import com.water.bean.EmployeeFormBean;
import com.water.bean.OracleDbBean;
import com.water.bean.ZoneConstants;
import com.water.bean.ZoneDivisionFormBean;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
public class DashboardController {

	// getting webservice URL from property file in context
	@Value("${WaterEmployeeService}")
	String WaterEmployeeService;

	// getting webservice URL from property file in context
	@Value("${WaterUtilService}")
	String WaterUtilService;

	// getting webservice URL from property file in context
	@Value("${WaterDashboardService}")
	String WaterDashboardService;

	@Value("${err.LOGIN_ERR_MSG}")
	String LOGIN_ERR_MSG;

	@Value("${EC_Folder}")
	String EC_Folder;

	Gson gson;

	@RequestMapping(value = "/ceViewApp", method = RequestMethod.GET)
	public ModelAndView ceViewApp(
			@ModelAttribute("dashboardForm") ComplaintBean complaintBean)
			throws JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "listCePendingApplication",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<ApplicationBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			ApplicationBean applicationBean = gson.fromJson(
					jsonArray.getString(i), ApplicationBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("ceViewApp", "list", model);
	}

	@RequestMapping(value = "/ceApproved", method = RequestMethod.GET)
	public ModelAndView ceApproved(
			@ModelAttribute("dashboardForm") ComplaintBean complaintBean)
			throws JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "listCeApprovedApplication",

				HttpMethod.GET, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<ApplicationBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			ApplicationBean applicationBean = gson.fromJson(
					jsonArray.getString(i), ApplicationBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("ceApproved", "list", model);
	}
	


		
		@RequestMapping(value = "/ceViewAll", method = RequestMethod.GET)
	public ModelAndView ceViewAll(
			@ModelAttribute("dashboardForm") ComplaintBean complaintBean)
			throws JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "listViewAllApplication",

				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<ApplicationBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			ApplicationBean applicationBean = gson.fromJson(
					jsonArray.getString(i), ApplicationBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("ceViewAll", "list", model);
	}
		
		@RequestMapping(value = "/mcViewAll", method = RequestMethod.GET)
		public ModelAndView mcViewAll(
				@ModelAttribute("dashboardForm") ComplaintBean complaintBean)
				throws JSONException {

			Map<String, Object> model = new HashMap<String, Object>();

			RestTemplate restTemplate = new RestTemplate();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<?> entity = new HttpEntity(headers);

			ResponseEntity<String> out = restTemplate.exchange(
					WaterDashboardService + "listViewAllApplication",

					HttpMethod.POST, entity, String.class);

			JSONArray jsonArray = new JSONArray(out.getBody().toString());

			gson = new Gson();

			List<ApplicationBean> applicationBeanList = new ArrayList<>();

			for (int i = 0; i < jsonArray.length(); i++) {
				ApplicationBean applicationBean = gson.fromJson(
						jsonArray.getString(i), ApplicationBean.class);
				applicationBeanList.add(applicationBean);
			}

			model.put("appBean", applicationBeanList);
			// model.put("categoryCount", publicdashboard());
			return new ModelAndView("mcViewAll", "list", model);
		}
		@RequestMapping(value = "/eeViewAll", method = RequestMethod.GET)
	public ModelAndView eeViewAll(
			@ModelAttribute("dashboardForm") ComplaintBean complaintBean)
			throws JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "listViewAllApplication",

				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<ApplicationBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			ApplicationBean applicationBean = gson.fromJson(
					jsonArray.getString(i), ApplicationBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("eeViewAll", "list", model);
	}
	@RequestMapping(value = "/eeApproved", method = RequestMethod.GET)
	public ModelAndView eeApproved(
			@ModelAttribute("dashboardForm") ComplaintBean complaintBean)
			throws JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "listEeApprovedApplication",

				HttpMethod.GET, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<ApplicationBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			ApplicationBean applicationBean = gson.fromJson(
					jsonArray.getString(i), ApplicationBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("eeApproved", "list", model);
	}
	@RequestMapping(value = "/mcapproved", method = RequestMethod.GET)
	public ModelAndView mcApproved(
			@ModelAttribute("dashboardForm") ComplaintBean complaintBean)
			throws JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "listMcApprovedApplication",

				HttpMethod.GET, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<ApplicationBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			ApplicationBean applicationBean = gson.fromJson(
					jsonArray.getString(i), ApplicationBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("mcapproved", "list", model);
	}

	@RequestMapping(value = "/eeViewApp", method = RequestMethod.GET)
	public ModelAndView eeViewApp(
			@ModelAttribute("dashboardForm") ComplaintBean complaintBean)
			throws JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "listEePendingApplication",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<ApplicationBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			ApplicationBean applicationBean = gson.fromJson(
					jsonArray.getString(i), ApplicationBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("eeViewApp", "list", model);
	}

	@RequestMapping(value = "/mcViewApp", method = RequestMethod.GET)
	public ModelAndView mcViewApp(
			@ModelAttribute("dashboardForm") ComplaintBean complaintBean)
			throws JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "listMcPendingApplication",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<ApplicationBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			ApplicationBean applicationBean = gson.fromJson(
					jsonArray.getString(i), ApplicationBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("mcViewapp", "list", model);
	}

	

	@RequestMapping(value = "/eeBeforeInsp", method = RequestMethod.GET)
	public ModelAndView mcBeforeInsp(
			@ModelAttribute("dashboardForm") ComplaintBean complaintBean)
			throws JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "listBeforeInspection",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<ApplicationBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			ApplicationBean applicationBean = gson.fromJson(
					jsonArray.getString(i), ApplicationBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("eebeforeInsp", "list", model);
	}
	
	@RequestMapping(value = "/eeProsFeePendingApp", method = RequestMethod.GET)
	public ModelAndView eeProsFeePendingApp(
			@ModelAttribute("dashboardForm") ComplaintBean complaintBean)
			throws JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "listProsFeePendingPayment",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<ApplicationBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			ApplicationBean applicationBean = gson.fromJson(
					jsonArray.getString(i), ApplicationBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("eeProsFeePendinApp", "list", model);
	}
	
	@RequestMapping(value = "/eeConPayPendingApp", method = RequestMethod.GET)
	public ModelAndView eeConPayPendingApp(
			@ModelAttribute("dashboardForm") ComplaintBean complaintBean)
			throws JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "listeeConPendingPayment",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<ApplicationBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			ApplicationBean applicationBean = gson.fromJson(
					jsonArray.getString(i), ApplicationBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("eeConPendinApp", "list", model);
	}

	@RequestMapping(value = "/eeConPaidApp", method = RequestMethod.GET)
	public ModelAndView eeConPaidApp(
			@ModelAttribute("dashboardForm") ComplaintBean complaintBean)
			throws JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "listConPaidDtls",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<ApplicationBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			ApplicationBean applicationBean = gson.fromJson(
					jsonArray.getString(i), ApplicationBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("eeConPaidApp", "list", model);
	}

	@RequestMapping(value = "/eeAfterInsp", method = RequestMethod.GET)
	public ModelAndView mcAfterInsp(
			@ModelAttribute("dashboardForm") ComplaintBean complaintBean)
			throws JsonSyntaxException, JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "listAfterInspection", HttpMethod.POST,
				entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<ApplicationBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			ApplicationBean applicationBean = gson.fromJson(
					jsonArray.getString(i), ApplicationBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("eeafterInsp", "list", model);
	}

	@RequestMapping(value = "/ceStatus", method = RequestMethod.GET)
	public ModelAndView cestatus(
			@ModelAttribute("dashboardForm") ComplaintBean complaintBean) {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "getDashboardCount", HttpMethod.POST,
				entity, String.class);

		gson = new Gson();
		DashboardBean dashboard = gson.fromJson(out.getBody().toString(),
				DashboardBean.class);

		model.put("count", dashboard);
		model.put("categoryCount", publicdashboard());
		return new ModelAndView("cestatus", "list", model);
	}

	@RequestMapping(value = "/eeStatus", method = RequestMethod.GET)
	public ModelAndView eestatus(
			@ModelAttribute("dashboardForm") ComplaintBean complaintBean) {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "getDashboardCount", HttpMethod.POST,
				entity, String.class);

		gson = new Gson();
		DashboardBean dashboard = gson.fromJson(out.getBody().toString(),
				DashboardBean.class);

		model.put("count", dashboard);
		model.put("categoryCount", publicdashboard());
		return new ModelAndView("eestatus", "list", model);
	}

	@RequestMapping(value = "/trackStatus", method = RequestMethod.GET)
	public ModelAndView trackStatus(
			@ModelAttribute("dashboardForm") ComplaintBean complaintBean)
			throws JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		/*ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "listTrackApplication",
				HttpMethod.POST, entity, String.class);*/
		
		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "listMcPendingApplication",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<ApplicationBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			ApplicationBean applicationBean = gson.fromJson(
					jsonArray.getString(i), ApplicationBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		
		return new ModelAndView("trackstatus", "list", model);

	}

	
	@RequestMapping(value = "/ceApprove", method = RequestMethod.POST)
	@ResponseBody
	public String ceApprove(@RequestParam String appRef,
			@RequestParam String remarks, HttpSession session)
			throws JSONException {

		ApplicationBean applicationBean = new ApplicationBean();
		applicationBean.setAppId(appRef);
		applicationBean.setRemarks(remarks);
		applicationBean.setUserId(session.getAttribute("LoginID").toString());
		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(applicationBean, headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "CeApproved", HttpMethod.POST, entity,
				String.class);

		gson = new Gson();
		String responseMsg = gson.fromJson(out.getBody().toString(),
				String.class);

		model.put("responseMsg", appRef + " Forward Successfully");
		// model.put("categoryCount", publicdashboard());
		return "Application # " + appRef
				+ " has been Forward Successfully !!";
		// return new ModelAndView("exemptionReport");

	}
	
	
	
	@RequestMapping(value = "/finalOfflinePaymnet", method = RequestMethod.POST)
	@ResponseBody
	public String finalOfflinePaymnet(@RequestParam String appRef,
			@RequestParam String inspectionDate, HttpSession session)
			throws JSONException {

		ApplicationBean applicationBean = new ApplicationBean();
		applicationBean.setAppId(appRef);
	//	applicationBean.setInspectionDate("07-08-2017"); // date put it hard
															// code value
		applicationBean.setInspectionDate(inspectionDate);
		applicationBean.setUserId(session.getAttribute("LoginID").toString());
		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(applicationBean, headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "SendInspectionDate", HttpMethod.POST,
				entity, String.class);

		gson = new Gson();
		String responseMsg = gson.fromJson(out.getBody().toString(),
				String.class);

		model.put("responseMsg", appRef + " Approved Successfully");
		// model.put("categoryCount", publicdashboard());
		return "Inspection Date   " + inspectionDate
				+ "   Send Successfully to  " + appRef
				+ "  Registered Mobile / Email !!";
		// return new ModelAndView("exemptionReport");

	}


	@RequestMapping(value = "/eeSendInspectionDate", method = RequestMethod.POST)
	@ResponseBody
	public String eeSendInspectionDate(@RequestParam String appRef,
			@RequestParam String inspectionDate, HttpSession session)
			throws JSONException {

		ApplicationBean applicationBean = new ApplicationBean();
		applicationBean.setAppId(appRef);
		//applicationBean.setInspectionDate("07-08-2017"); // date put it hard
															// code value
		applicationBean.setInspectionDate(inspectionDate);

		applicationBean.setUserId(session.getAttribute("LoginID").toString());
		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(applicationBean, headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "SendInspectionDate", HttpMethod.POST,
				entity, String.class);

		gson = new Gson();
		String responseMsg = gson.fromJson(out.getBody().toString(),
				String.class);

		model.put("responseMsg", appRef + " Approved Successfully");
		// model.put("categoryCount", publicdashboard());
		return "Inspection Date   " + inspectionDate
				+ "   Send Successfully to  " + appRef
				+ "  Registered Mobile / Email !!";
		// return new ModelAndView("exemptionReport");

	}

	@RequestMapping(value = "/isMcDicision", method = RequestMethod.POST)
	@ResponseBody
	public String mcDicision(@RequestParam String appRef,
			@RequestParam String isMcDicision,String estAmount,String gst, String finalfee, HttpSession session)
			throws JSONException {

		ApplicationBean applicationBean = new ApplicationBean();
		applicationBean.setAppId(appRef);
		applicationBean.setIsMcDicision(isMcDicision);
		applicationBean.setTotalAmount(estAmount);
		applicationBean.setGstAmount(gst);
		
		applicationBean.setEstimationCost(finalfee);
	

		applicationBean.setUserId(session.getAttribute("LoginID").toString());
		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(applicationBean, headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "isMcDicision", HttpMethod.POST,
				entity, String.class);

		gson = new Gson();
		String responseMsg = gson.fromJson(out.getBody().toString(),
				String.class);

		model.put("responseMsg", appRef + " Approved Successfully");
		// model.put("categoryCount", publicdashboard());
		return "Application #  " + appRef + "  Approved "
				+ " Successfully ";
		// return new ModelAndView("exemptionReport");

	}

	@RequestMapping(value = "/isMcTrckDicision", method = RequestMethod.POST)
	@ResponseBody
	public String isMcTrckDicision(@RequestParam String appRef,
			@RequestParam String isMcDicision, HttpSession session)
			throws JSONException {

		ApplicationBean applicationBean = new ApplicationBean();
		applicationBean.setAppId(appRef);
		applicationBean.setIsMcDicision(isMcDicision);

		applicationBean.setUserId(session.getAttribute("LoginID").toString());
		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(applicationBean, headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "isMcTrckDicision", HttpMethod.POST,
				entity, String.class);

		gson = new Gson();
		String responseMsg = gson.fromJson(out.getBody().toString(),
				String.class);

		model.put("responseMsg", appRef + " Approved Successfully");
		// model.put("categoryCount", publicdashboard());
		return "Application #  " + appRef + "  " + isMcDicision
				+ " Successfully ";
		// return new ModelAndView("exemptionReport");

	}

	@RequestMapping(value = "/eeSendInitialPatment", method = RequestMethod.POST)
	@ResponseBody
	public String eeSendInitialPatment(@RequestParam String appRef,
			@RequestParam String initialPaymentCost, HttpSession session)
			throws JSONException {

		ApplicationBean applicationBean = new ApplicationBean();
		applicationBean.setAppId(appRef);
		applicationBean.setInitialPaymentCost(initialPaymentCost);

		applicationBean.setUserId(session.getAttribute("LoginID").toString());
		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(applicationBean, headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "SendinitialPaymentCost",
				HttpMethod.POST, entity, String.class);

		gson = new Gson();
		String responseMsg = gson.fromJson(out.getBody().toString(),
				String.class);

		model.put("responseMsg", appRef + " Approved Successfully");
		// model.put("categoryCount", publicdashboard());
		return " For this "+ appRef+ " Processing  Cost Rs " + initialPaymentCost
				+ "  , Send Successfully to  " 
				+ "  Registered Mobile / Email !!";
		// return new ModelAndView("exemptionReport");

	}

	@RequestMapping(value = "/CEViewForm", method = RequestMethod.GET)
	public ModelAndView CEViewForm(@RequestParam String appId,
			HttpSession session) {
		ApplicationBean applicationBean = new ApplicationBean();
		applicationBean.setAppId(appId);
		// applicationBean.setRemarks(remarks);
		applicationBean.setUserId(session.getAttribute("LoginID").toString());
		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(applicationBean, headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "getviewApp", HttpMethod.POST, entity,
				String.class);

		// ApplicationBean appBean = out.getBody();
		gson = new Gson();
		applicationBean = gson.fromJson(out.getBody().toString(),
				ApplicationBean.class);

		model.put("application", applicationBean);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("CEViewForm", "list", model);
	}
	
	
	
	
	
	@RequestMapping(value = "/SendOracleDB", method = RequestMethod.POST)
	@ResponseBody
		public String SendOracleDB(@RequestParam String appRef,String commissiningdate,String completionDate, String estimationCost, String initialFee,String gst,String totalAmount,
				HttpSession session) {
			OracleDbBean oracleDbBean = new OracleDbBean();

			
			oracleDbBean.setAppId(appRef);
			oracleDbBean.setCompletionDate(completionDate);
			oracleDbBean.setCommissionDate(commissiningdate);
			oracleDbBean.setEstimationCost(estimationCost);
			oracleDbBean.setInitialPaymentCost(initialFee);
			oracleDbBean.setGstAmount(gst);
			oracleDbBean.setTotalAmount(totalAmount);
			
			
			/*CmwWaterConnBean cmwBean=new CmwWaterConnBean();
			cmwBean.setCHALLAN_NO(appRef);*/
			
			oracleDbBean.setUserId(session.getAttribute("LoginID").toString());
			Map<String, Object> model = new HashMap<String, Object>();

			RestTemplate restTemplate = new RestTemplate();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<?> entity = new HttpEntity(oracleDbBean, headers);

			ResponseEntity<String> out = restTemplate.exchange(
					WaterDashboardService + "SendOracleDb", HttpMethod.POST, entity,
					String.class);

			String  response = out.getBody();
			
			return response;
		}
	@RequestMapping(value = "/SendEstimationCost", method = RequestMethod.POST)
@ResponseBody
	public String SendEstimationCost(@RequestParam String appRef,String isnearest,String isnewpipeline,String isproposal,String estimationCost,String gst,String totalAmount,String tentativeDate, 
			HttpSession session) {
		ApplicationBean applicationBean = new ApplicationBean();
		applicationBean.setAppId(appRef);
		applicationBean.setIsnearest(isnearest);
		applicationBean.setIsnewpipeline(isnewpipeline);
		applicationBean.setIsproposal(isproposal);
		applicationBean.setEstimationCost(estimationCost);
		applicationBean.setPaymentAmount(estimationCost);
		applicationBean.setGstAmount(gst);
		applicationBean.setTotalAmount(totalAmount);
		applicationBean.setTentativeDate(tentativeDate);
		// applicationBean.setRemarks(remarks);
		applicationBean.setUserId(session.getAttribute("LoginID").toString());
		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(applicationBean, headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "SendEstimationCost", HttpMethod.POST, entity,
				String.class);

		String  response = out.getBody();
		
		return response;
	}
	@RequestMapping(value = "/eeWidthdraw", method = RequestMethod.POST)
	public ModelAndView eeWidthdraw(@RequestParam String appRef,
			HttpSession session) {
		ApplicationBean applicationBean = new ApplicationBean();
		applicationBean.setAppId(appRef);
		// applicationBean.setRemarks(remarks);
		applicationBean.setUserId(session.getAttribute("LoginID").toString());
		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(applicationBean, headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "eeWidthdraw", HttpMethod.POST, entity,
				String.class);

		// ApplicationBean appBean = out.getBody();
		gson = new Gson();
		applicationBean = gson.fromJson(out.getBody().toString(),
				ApplicationBean.class);

		model.put("application", applicationBean);
		return new ModelAndView("trackstatus", "list", model);
	}
	
	@RequestMapping(value = "/eeRejcted", method = RequestMethod.POST)
	@ResponseBody
	public String eeRejcted(@RequestParam String appRef,String remarks,
			HttpSession session) {
		ApplicationBean applicationBean = new ApplicationBean();
		applicationBean.setAppId(appRef);
		applicationBean.setRemarks(remarks);
		// applicationBean.setRemarks(remarks);
		applicationBean.setUserId(session.getAttribute("LoginID").toString());
		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(applicationBean, headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "eeRejcted", HttpMethod.POST, entity,
				String.class);

		// ApplicationBean appBean = out.getBody();
		gson = new Gson();
		/*applicationBean = gson.fromJson(out.getBody().toString(),
				ApplicationBean.class);*/

		//model.put("application", applicationBean);
		// model.put("categoryCount", publicdashboard());
		//model.put("responseMsg", appRef + " Deleted Successfully");
		return appRef + " Deleted Successfully";
	}
	@RequestMapping(value = "/eeApprove", method = RequestMethod.POST)
	public ModelAndView eeApprove(@RequestParam String appRef,
			HttpSession session) {
		ApplicationBean applicationBean = new ApplicationBean();
		applicationBean.setAppId(appRef);
		// applicationBean.setRemarks(remarks);
		applicationBean.setUserId(session.getAttribute("LoginID").toString());
		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(applicationBean, headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "getviewApp", HttpMethod.POST, entity,
				String.class);

		// ApplicationBean appBean = out.getBody();
		gson = new Gson();
		applicationBean = gson.fromJson(out.getBody().toString(),
				ApplicationBean.class);

		model.put("application", applicationBean);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("EEApprove", "list", model);
	}

	@RequestMapping(value = "/EEViewForm", method = RequestMethod.GET)
	public ModelAndView EEViewForm(@RequestParam String appId,
			HttpSession session) {
		ApplicationBean applicationBean = new ApplicationBean();
		applicationBean.setAppId(appId);
		// applicationBean.setRemarks(remarks);
		applicationBean.setUserId(session.getAttribute("LoginID").toString());
		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(applicationBean, headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "getviewApp", HttpMethod.POST, entity,
				String.class);

		// ApplicationBean appBean = out.getBody();
		gson = new Gson();
		applicationBean = gson.fromJson(out.getBody().toString(),
				ApplicationBean.class);

		model.put("application", applicationBean);
		File folder = new File("c:/EC/"+appId);
		File[] listOfFiles = folder.listFiles();
		List<String> fileList = new ArrayList<String>();
		
		    for (int i = 0;listOfFiles!=null &&  i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	  fileList.add(listOfFiles[i].getName());
		      }
		    }
		    
		    File folderAdmin = new File("c:/EC/"+appId+"/Admin");
			File[] listOfFilesByAdmin = folderAdmin.listFiles();
			List<String> uploadedFilesByAdmin = new ArrayList<String>();
		    
		    for (int i = 0;listOfFilesByAdmin!=null &&  i < listOfFilesByAdmin.length; i++) {
			      if (listOfFilesByAdmin[i].isFile()) {
			    	  uploadedFilesByAdmin.add(listOfFilesByAdmin[i].getName());
			      }
			    }
		
		    model.put("uploadedFiles", fileList);
		    model.put("uploadedFilesByAdmin", uploadedFilesByAdmin);
		    
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("EEViewForm", "list", model);
	}

	@RequestMapping(value = "/MCViewForm", method = RequestMethod.GET)
	public ModelAndView MCViewForm(@RequestParam String appId,
			HttpSession session) {
		ApplicationBean applicationBean = new ApplicationBean();
		applicationBean.setAppId(appId);
		// applicationBean.setRemarks(remarks);
		applicationBean.setUserId(session.getAttribute("LoginID").toString());
		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(applicationBean, headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "getviewApp", HttpMethod.POST, entity,
				String.class);

		// ApplicationBean appBean = out.getBody();
		gson = new Gson();
		applicationBean = gson.fromJson(out.getBody().toString(),
				ApplicationBean.class);

		//model.put("application", applicationBean);
		
		model.put("application", applicationBean);
		File folder = new File("c:/EC/"+appId);
		File[] listOfFiles = folder.listFiles();
		List<String> fileList = new ArrayList<String>();
		
		    for (int i = 0;listOfFiles!=null &&  i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	  fileList.add(listOfFiles[i].getName());
		      }
		    }
		    
		    File folderAdmin = new File("c:/EC/"+appId+"/Admin");
			File[] listOfFilesByAdmin = folderAdmin.listFiles();
			List<String> uploadedFilesByAdmin = new ArrayList<String>();
		    
		    for (int i = 0;listOfFilesByAdmin!=null &&  i < listOfFilesByAdmin.length; i++) {
			      if (listOfFilesByAdmin[i].isFile()) {
			    	  uploadedFilesByAdmin.add(listOfFilesByAdmin[i].getName());
			      }
			    }
		
		    model.put("uploadedFiles", fileList);
		    model.put("uploadedFilesByAdmin", uploadedFilesByAdmin);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("MCViewForm", "list", model);
	}

	@RequestMapping(value = "/eePayment", method = RequestMethod.GET)
	public ModelAndView eePayment(
			@ModelAttribute("dashboardForm") ComplaintBean complaintBean)
			throws JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "getCePendingApplication",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<ApplicationBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			ApplicationBean applicationBean = gson.fromJson(
					jsonArray.getString(i), ApplicationBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		// model.put("categoryCount", publicdashboard());
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("eePayment", "list", model);
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(
			@ModelAttribute("dashboardForm") ComplaintBean complaintBean) {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "getDashboardCount", HttpMethod.POST,
				entity, String.class);

		gson = new Gson();
		DashboardBean dashboard = gson.fromJson(out.getBody().toString(),
				DashboardBean.class);

		model.put("count", dashboard);
		model.put("categoryCount", publicdashboard());
		return new ModelAndView("dashboard", "list", model);
	}

	@RequestMapping(value = "/ceDashboard", method = RequestMethod.GET)
	public ModelAndView cedashboard(
			@ModelAttribute("dashboardForm") ApplicationBean applicationBean) {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "getceDashboardCount", HttpMethod.POST,
				entity, String.class);

		gson = new Gson();
		DashboardCountBean dashboard = gson.fromJson(out.getBody().toString(),
				DashboardCountBean.class);

		model.put("count", dashboard);
		model.put("categoryCount", publicdashboard());
		return new ModelAndView("ceDashboard", "list", model);
	}


	@RequestMapping(value = "/configrationManagement", method = RequestMethod.GET)
	public ModelAndView configrationManagement() throws JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "getUserDtl",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<EmployeeFormBean> employeeFormBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			EmployeeFormBean employeeFormBean = gson.fromJson(
					jsonArray.getString(i), EmployeeFormBean.class);
			employeeFormBeanList.add(employeeFormBean);
		}

		
		
		model.put("userDtl", employeeFormBeanList);
		return new ModelAndView("configrationManagement", "list", model);
	}
	
	@RequestMapping(value = "/categoryManagement", method = RequestMethod.GET)
	public ModelAndView categoryManagement() throws JsonSyntaxException, JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "getCategoryDtl",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<CategoryFormBean> categoryFormBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			CategoryFormBean categoryFormBean = gson.fromJson(
					jsonArray.getString(i), CategoryFormBean.class);
			categoryFormBeanList.add(categoryFormBean);
		}

		
		
		model.put("categoryDtl", categoryFormBeanList);

		return new ModelAndView("categoryManagement", "list", model);
	}
	
	@RequestMapping(value = "/reConnectionTypeManagement", method = RequestMethod.GET)
	public ModelAndView reConnectionTypeManagement() throws JSONException {


		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "getConnectionTypeDtl",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<ConnectionFormBean> connectionFormBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			ConnectionFormBean connectionFormBean = gson.fromJson(
					jsonArray.getString(i), ConnectionFormBean.class);
			connectionFormBeanList.add(connectionFormBean);
		}

		
		
		model.put("connectionTypeDtl", connectionFormBeanList);

		return new ModelAndView("reConnectionTypeManagement", "list", model);
	}
	
	@RequestMapping(value = "/zoneDivisionManagement", method = RequestMethod.GET)
	public ModelAndView zoneDivisionManagement() throws JSONException, IOException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "getDivisionDtl",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<ZoneDivisionFormBean> zoneDivisionFormBeanList = new ArrayList<>();

		Map<String,String> zoneMap = new LinkedHashMap<>();
		for (int i = 0; i < jsonArray.length(); i++) {
			ZoneDivisionFormBean zoneDivisionFormBean = gson.fromJson(
					jsonArray.getString(i), ZoneDivisionFormBean.class);
			
			if(!zoneMap.containsKey(String.valueOf(zoneDivisionFormBean.getZoneId()))){
			zoneMap.put(String.valueOf(zoneDivisionFormBean.getZoneId()), zoneDivisionFormBean.getZoneName());
			}
			
			
			zoneDivisionFormBeanList.add(zoneDivisionFormBean);
		}

		updateZoneDivisionFile(zoneDivisionFormBeanList);
		
		model.put("zoneDivisionDtl", zoneDivisionFormBeanList);
		model.put("zoneMap", zoneMap);

		return new ModelAndView("zoneDivisionManagement", "list", model);
	}
	
	public void updateZoneDivisionFile(List<ZoneDivisionFormBean> zoneDivisionFormBeanList) throws IOException, JSONException{

		String path = this.getClass().getClassLoader().getResource("").getPath();
		String fullPath = URLDecoder.decode(path, "UTF-8");
		String pathArr[] = fullPath.split("WEB-INF/");
		
		Font redFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.NORMAL, new CMYKColor(0, 1f, 1f, 0));
		Font blackFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL, new CMYKColor(0, 0, 0, 255));
		Font blueFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, Font.BOLD, new CMYKColor(1f, 0.498f, 0, 0));
		Font greyFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.NORMAL, new CMYKColor(0, 0, 0, 255));
		Font blackHeaderFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD, new CMYKColor(0, 0, 0, 255));
		
		
		Document document = new Document();
	      try
	      {
	         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pathArr[0]+"library/HelloWorld.pdf"));
	         document.open();
	         
	         document.add(new Paragraph("Area Offices:",redFont));
	         PdfPTable zoneTable = new PdfPTable(1);
	         PdfPCell zoneCell=null;
	         
	         PdfPTable headerTable = new PdfPTable(1);
	         headerTable.setWidthPercentage(100);
	         PdfPCell headerCell=new PdfPCell(new Paragraph("Depot Offices and Mobile no's",blueFont));
	         headerCell.setBorder(Rectangle.NO_BORDER);
	         headerCell.setPadding(10);
	         headerTable.addCell(headerCell);
	         PdfPTable zoneDivisionTable = new PdfPTable(5);
	         zoneDivisionTable.setWidthPercentage(100);
	        zoneDivisionTable.setWidths(new int[]{9,7,44,20,20});
	         PdfPCell zoneDivisionCell=null;
	         
	        
		FileWriter fw = new FileWriter(pathArr[0]+"library/test.json");
		BufferedWriter bw = new BufferedWriter(fw);
		
		Map<String,List<Integer>> zoneDivisionMap = new HashMap<>();
		List<Integer> li = null;
		int count = 0;
		for(ZoneDivisionFormBean zoneDivisionFormBean:zoneDivisionFormBeanList){
		
			String key = String.valueOf(zoneDivisionFormBean.getZoneId());
			
			if(zoneDivisionMap.containsKey(key)){
				li = zoneDivisionMap.get(key);
				li.add(Integer.parseInt(zoneDivisionFormBean.getDivisionNo()));
				 String areaName = ZoneConstants.getZoneAreaName(zoneDivisionFormBean.getZoneName().split("-")[0].trim());
				  zoneDivisionCell = new PdfPCell(new Paragraph(areaName,greyFont));
				  zoneDivisionCell.setMinimumHeight(30f);
			         zoneDivisionCell.setVerticalAlignment(Element.ALIGN_TOP);
			         if(count%2==0){
			             zoneDivisionCell.setBackgroundColor(new BaseColor(245,245,245));
			         }
			         zoneDivisionTable.addCell(zoneDivisionCell);
			         zoneDivisionCell = new PdfPCell(new Paragraph(zoneDivisionFormBean.getDivisionNo(),greyFont));
			         zoneDivisionCell.setMinimumHeight(30f);
			         zoneDivisionCell.setVerticalAlignment(Element.ALIGN_TOP);
			         if(count%2==0){
			             zoneDivisionCell.setBackgroundColor(new BaseColor(245,245,245));
			         }
			         zoneDivisionTable.addCell(zoneDivisionCell);
			         zoneDivisionCell = new PdfPCell(new Paragraph(zoneDivisionFormBean.getDivisionAddr(),greyFont));
			         zoneDivisionCell.setMinimumHeight(30f);
			         zoneDivisionCell.setVerticalAlignment(Element.ALIGN_TOP);
			         if(count%2==0){
			             zoneDivisionCell.setBackgroundColor(new BaseColor(245,245,245));
			         }
			         zoneDivisionTable.addCell(zoneDivisionCell);
			         zoneDivisionCell = new PdfPCell(new Paragraph(zoneDivisionFormBean.getDivisionPhone(),greyFont));
			         zoneDivisionCell.setMinimumHeight(30f);
			         zoneDivisionCell.setVerticalAlignment(Element.ALIGN_TOP);
			         if(count%2==0){
			             zoneDivisionCell.setBackgroundColor(new BaseColor(245,245,245));
			         }
			         zoneDivisionTable.addCell(zoneDivisionCell);
			         zoneDivisionCell = new PdfPCell(new Paragraph(zoneDivisionFormBean.getDivisionMobile(),greyFont));
			         zoneDivisionCell.setMinimumHeight(30f);
			         zoneDivisionCell.setVerticalAlignment(Element.ALIGN_TOP);
			         if(count%2==0){
			             zoneDivisionCell.setBackgroundColor(new BaseColor(245,245,245));
			         }
			         zoneDivisionTable.addCell(zoneDivisionCell);
			         count++;
				
			}
			else{
				
				count=0;
				 li = new ArrayList<Integer>(); 
				 li.add(Integer.parseInt(zoneDivisionFormBean.getDivisionNo()));
				zoneDivisionMap.put(key, li);
				 zoneCell = new PdfPCell(new Paragraph(zoneDivisionFormBean.getZoneName(),blackFont));
				 zoneCell.setBorder(Rectangle.NO_BORDER);
				 zoneCell.setPadding(3);
		         zoneTable.addCell(zoneCell);
		         zoneTable.setWidthPercentage(75);
		         zoneTable.setHorizontalAlignment(Element.ALIGN_CENTER);
		         
		         
		          zoneDivisionCell = new PdfPCell(new Paragraph("Area No.",blackHeaderFont));
		          zoneDivisionCell.setMinimumHeight(40f);
		          zoneDivisionCell.setVerticalAlignment(Element.ALIGN_TOP);
		          zoneDivisionCell.setBackgroundColor(new BaseColor(245,245,245));
		          zoneDivisionTable.addCell(zoneDivisionCell);
		          zoneDivisionCell = new PdfPCell(new Paragraph("Depot No.",blackHeaderFont));
		         
		          zoneDivisionCell.setVerticalAlignment(Element.ALIGN_TOP);
		          zoneDivisionCell.setBackgroundColor(new BaseColor(245,245,245));
		          zoneDivisionTable.addCell(zoneDivisionCell);
		          zoneDivisionCell = new PdfPCell(new Paragraph("Office Address",blackHeaderFont));
		         
		          zoneDivisionCell.setVerticalAlignment(Element.ALIGN_TOP);
		          zoneDivisionCell.setBackgroundColor(new BaseColor(245,245,245));
		          zoneDivisionTable.addCell(zoneDivisionCell);
		          zoneDivisionCell = new PdfPCell(new Paragraph("Phone No.",blackHeaderFont));
		         
		          zoneDivisionCell.setVerticalAlignment(Element.ALIGN_TOP);
		          zoneDivisionCell.setBackgroundColor(new BaseColor(245,245,245));
		          zoneDivisionTable.addCell(zoneDivisionCell);
		          zoneDivisionCell = new PdfPCell(new Paragraph("Mobile No.",blackHeaderFont));
		         
		          zoneDivisionCell.setVerticalAlignment(Element.ALIGN_TOP);
		          zoneDivisionCell.setBackgroundColor(new BaseColor(245,245,245));
		          zoneDivisionTable.addCell(zoneDivisionCell);
		         
		          String areaName = ZoneConstants.getZoneAreaName(zoneDivisionFormBean.getZoneName().split("-")[0].trim());
		         zoneDivisionCell = new PdfPCell(new Paragraph(areaName,greyFont));
		         zoneDivisionCell.setMinimumHeight(30f);
		         zoneDivisionCell.setNoWrap(false);
		         zoneDivisionCell.setVerticalAlignment(Element.ALIGN_TOP);
		         zoneDivisionTable.addCell(zoneDivisionCell);
		         zoneDivisionCell = new PdfPCell(new Paragraph(zoneDivisionFormBean.getDivisionNo(),greyFont));
		       
		         zoneDivisionCell.setVerticalAlignment(Element.ALIGN_TOP);
		         zoneDivisionTable.addCell(zoneDivisionCell);
		         zoneDivisionCell = new PdfPCell(new Paragraph(zoneDivisionFormBean.getDivisionAddr(),greyFont));
		         
		         zoneDivisionCell.setVerticalAlignment(Element.ALIGN_TOP);
		         zoneDivisionTable.addCell(zoneDivisionCell);
		         zoneDivisionCell = new PdfPCell(new Paragraph(zoneDivisionFormBean.getDivisionPhone(),greyFont));
		         
		         zoneDivisionCell.setVerticalAlignment(Element.ALIGN_TOP);
		         zoneDivisionTable.addCell(zoneDivisionCell);
		         zoneDivisionCell = new PdfPCell(new Paragraph(zoneDivisionFormBean.getDivisionMobile(),greyFont));
		         
		         zoneDivisionCell.setVerticalAlignment(Element.ALIGN_TOP);
		         zoneDivisionTable.addCell(zoneDivisionCell);
		         
			}
			
		
		
		}
		
		Iterator itr = zoneDivisionMap.entrySet().iterator();
		String str = "";
		String str1 ="{";
		while (itr.hasNext()) {
			Map.Entry<String, List<Integer>>   mapEntry =  (Map.Entry<String, List<Integer>>)itr.next();
			List<Integer> divisionNo = mapEntry.getValue();
			Collections.sort(divisionNo);
		    str = str + ",\n\""+mapEntry.getKey()+"\":"+divisionNo;
		  
		}
		 bw.write(str1+str.substring(1)+"\n");
		   bw.write("}");
		
		bw.close();
		fw.close();
		
		
		
		
		File newFile = new File(pathArr[0]+"library/test.json");
		File oldFile = new File(pathArr[0]+"library/ZoneDivision.json");
		if(oldFile.exists()){
			oldFile.delete();
		}
		newFile.renameTo(oldFile);
		
		
		 document.add(zoneTable);
		 document.add(headerTable);
		 document.add(zoneDivisionTable);
         document.close();
         writer.close();
         
         
         File newZoneFile = new File(pathArr[0]+"library/HelloWorld.pdf");
 		File oldZoneFile = new File(pathArr[0]+"library/ZoneDivisionRelationship.pdf");
 		if(oldZoneFile.exists()){
 			oldZoneFile.delete();
 		}
 		newZoneFile.renameTo(oldZoneFile);
         
         
      } catch (DocumentException e)
      {
         e.printStackTrace();
      } catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
		
	}
	
	
	
	
	
	
	@RequestMapping(value = "/eeDashboard", method = RequestMethod.GET)
	public ModelAndView eedashboard(
			@ModelAttribute("dashboardForm") ComplaintBean complaintBean) {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "geteeDashboardCount", HttpMethod.POST,
				entity, String.class);

		gson = new Gson();
		DashboardCountBean dashboard = gson.fromJson(out.getBody().toString(),
				DashboardCountBean.class);

		model.put("count", dashboard);
		model.put("categoryCount", publicdashboard());
		return new ModelAndView("eedashboard", "list", model);
	}

	@RequestMapping(value = "/mcdashboard", method = RequestMethod.GET)
	public ModelAndView mcdashboard(
			@ModelAttribute("dashboardForm") ComplaintBean complaintBean) {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "getmcDashboardCount", HttpMethod.POST,
				entity, String.class);

		gson = new Gson();
		DashboardCountBean dashboard = gson.fromJson(out.getBody().toString(),
				DashboardCountBean.class);

		model.put("count", dashboard);
		model.put("categoryCount", publicdashboard());
		return new ModelAndView("mcdashboard", "list", model);
	}

	public DashboardBeanList publicdashboard() {

		// ComplaintBean complaintBean = new ComplaintBean();
		ApplicationBean applicationBean = new ApplicationBean();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(applicationBean, headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "getPublicDashboardCount",
				HttpMethod.POST, entity, String.class);
		gson = new Gson();

		DashboardBeanList dashboardBeanList = gson.fromJson(out.getBody()
				.toString(), DashboardBeanList.class);

		return dashboardBeanList;
	}
	
	@RequestMapping(value = "/downloadFiles", method = RequestMethod.GET)
	public void downloadFiles(@RequestParam String fileName,@RequestParam String appId,HttpServletResponse response)
			 throws MalformedURLException, IOException {
			 try {
				 
				 response.setContentType("*/*");
					String headerKey = "Content-Disposition";
					String headerValue = String.format("attachment; filename=\"%s\"",fileName);
					response.setHeader(headerKey, headerValue);
					ServletOutputStream out;
					out = response.getOutputStream();
					FileInputStream fin = new FileInputStream("c:/EC/"+appId +"/"+ fileName);

					BufferedInputStream bin = new BufferedInputStream(fin);
					BufferedOutputStream bout = new BufferedOutputStream(out);
					int ch = 0;
					;
					while ((ch = bin.read()) != -1) {
						bout.write(ch);
					}

					bin.close();
					fin.close();
					bout.close();
					out.close();
				 
			 }
			 catch(Exception e){
				 
			 }

}
	

	@RequestMapping(value = "/addNewUser", method = RequestMethod.POST)
	@ResponseBody
	public String addNewUser(EmployeeFormBean employeeFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(employeeFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "addNewUser", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}
	

	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	@ResponseBody
	public String editUser(EmployeeFormBean employeeFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(employeeFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "editUser", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	@ResponseBody
	public String deleteUser(EmployeeFormBean employeeFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(employeeFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "deleteUser", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}
	
	

	@RequestMapping(value = "/addCategory", method = RequestMethod.POST)
	@ResponseBody
	public String addCategory(CategoryFormBean categoryFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(categoryFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "addCategory", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}
	

	@RequestMapping(value = "/editCategory", method = RequestMethod.POST)
	@ResponseBody
	public String editCategory(CategoryFormBean categoryFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(categoryFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "editCategory", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}

	@RequestMapping(value = "/deleteCategory", method = RequestMethod.POST)
	@ResponseBody
	public String deleteCategory(CategoryFormBean categoryFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(categoryFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "deleteCategory", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}
	
	

	@RequestMapping(value = "/addConnectionType", method = RequestMethod.POST)
	@ResponseBody
	public String addConnectionType(ConnectionFormBean connectionFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(connectionFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "addConnectionType", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}
	

	@RequestMapping(value = "/editConnectionType", method = RequestMethod.POST)
	@ResponseBody
	public String editConnectionType(ConnectionFormBean connectionFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(connectionFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "editConnectionType", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}

	@RequestMapping(value = "/deleteConnectionType", method = RequestMethod.POST)
	@ResponseBody
	public String deleteConnectionType(ConnectionFormBean connectionFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(connectionFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "deleteConnectionType", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}
	
	
	@RequestMapping(value = "/addZone", method = RequestMethod.POST)
	@ResponseBody
	public String addZone(ZoneDivisionFormBean zoneDivisionFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(zoneDivisionFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "addZone", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}
	
	

	@RequestMapping(value = "/addDivision", method = RequestMethod.POST)
	@ResponseBody
	public String addDivision(ZoneDivisionFormBean zoneDivisionFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(zoneDivisionFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "addDivision", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}
	

	@RequestMapping(value = "/editDivision", method = RequestMethod.POST)
	@ResponseBody
	public String editDivision(ZoneDivisionFormBean zoneDivisionFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(zoneDivisionFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "editDivision", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}

	@RequestMapping(value = "/deleteDivision", method = RequestMethod.POST)
	@ResponseBody
	public String deleteDivision(ZoneDivisionFormBean zoneDivisionFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(zoneDivisionFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "deleteDivision", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
