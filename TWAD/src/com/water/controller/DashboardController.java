package com.water.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
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
import com.water.bean.ApplicationBean;
import com.water.bean.CategoryFormBean;
import com.water.bean.CompanyDtlBean;
import com.water.bean.ComplaintBean;
import com.water.bean.ConnectionFormBean;
import com.water.bean.DDPaymentFormBean;
import com.water.bean.DashboardBean;
import com.water.bean.DashboardBeanList;
import com.water.bean.DashboardCountBean;
import com.water.bean.DistrictFormBean;
import com.water.bean.DistrictTalukFormBean;
import com.water.bean.EmployeeFormBean;
import com.water.bean.OfficeFormBean;
import com.water.bean.OracleDbBean;
import com.water.bean.PaymentFormBean;
import com.water.bean.TalukVillageFormBean;
import com.water.bean.ZoneConstants;
import com.water.bean.ZoneDivisionFormBean;

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

		List<DDPaymentFormBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			DDPaymentFormBean applicationBean = gson.fromJson(
					jsonArray.getString(i), DDPaymentFormBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("mcViewapp", "list", model);
	}

	

	@RequestMapping(value = "/eeBeforeInspold", method = RequestMethod.GET)
	public ModelAndView mcBeforeInspold(
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

		List<CompanyDtlBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			CompanyDtlBean applicationBean = gson.fromJson(
					jsonArray.getString(i), CompanyDtlBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("eeafterInsp", "list", model);
	}
	@RequestMapping(value = "/eeEstimate", method = RequestMethod.GET)
	public ModelAndView eeAEstimate(
			@ModelAttribute("dashboardForm") ComplaintBean complaintBean)
			throws JsonSyntaxException, JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "listEstimate", HttpMethod.POST,
				entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<CompanyDtlBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			CompanyDtlBean applicationBean = gson.fromJson(
					jsonArray.getString(i), CompanyDtlBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("eeEstimate", "list", model);
	}
	@RequestMapping(value = "/eeMOU", method = RequestMethod.GET)
	public ModelAndView eeAMOU(
			@ModelAttribute("dashboardForm") ComplaintBean complaintBean)
			throws JsonSyntaxException, JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "listMOU", HttpMethod.POST,
				entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<CompanyDtlBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			CompanyDtlBean applicationBean = gson.fromJson(
					jsonArray.getString(i), CompanyDtlBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("eeMOU", "list", model);
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

		/*ApplicationBean applicationBean = new ApplicationBean();*/
		CompanyDtlBean applicationBean=new CompanyDtlBean();
		//ApplicationBean applicationBean = new ApplicationBean();
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

	
	@RequestMapping(value = "/paymentDashboard", method = RequestMethod.GET)
	public ModelAndView paymentDashboard() {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "getPaymentDashboardCount", HttpMethod.POST,
				entity, String.class);

		gson = new Gson();
		DashboardCountBean dashboard = gson.fromJson(out.getBody().toString(),
				DashboardCountBean.class);

		model.put("count", dashboard);
		model.put("categoryCount", publicdashboard());
		return new ModelAndView("ddPaymentDashboard", "list", model);
	}


	@RequestMapping(value = "/registeredApplication", method = RequestMethod.GET)
	public ModelAndView registeredApplication()
			throws JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);
		
		
		ResponseEntity<String> out1 = restTemplate.exchange(
				WaterDashboardService + "getOfficeDtl",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray1 = new JSONArray(out1.getBody().toString());

		gson = new Gson();

		List<OfficeFormBean> officeFormBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray1.length(); i++) {
			OfficeFormBean officeFormBean = gson.fromJson(
					jsonArray1.getString(i), OfficeFormBean.class);
			officeFormBeanList.add(officeFormBean);
		}

		
		
		model.put("officeDtl", officeFormBeanList);
		
		

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "registeredApplication",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<DDPaymentFormBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			DDPaymentFormBean applicationBean = gson.fromJson(
					jsonArray.getString(i), DDPaymentFormBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		return new ModelAndView("registeredApplication", "list", model);
	}
	

	

	@RequestMapping(value = "/approvedApplication", method = RequestMethod.GET)
	public ModelAndView approvedApplication()
			throws JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "approvedApplication",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<DDPaymentFormBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			DDPaymentFormBean applicationBean = gson.fromJson(
					jsonArray.getString(i), DDPaymentFormBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		return new ModelAndView("approvedApplication", "list", model);
	}
	

	

	@RequestMapping(value = "/rejectedApplication", method = RequestMethod.GET)
	public ModelAndView rejectedApplication()
			throws JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "rejectedApplication",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<DDPaymentFormBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			DDPaymentFormBean applicationBean = gson.fromJson(
					jsonArray.getString(i), DDPaymentFormBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		return new ModelAndView("rejectedApplication", "list", model);
	}
	


	@RequestMapping(value = "/paymentPendingList", method = RequestMethod.GET)
	public ModelAndView paymentPendingList()
			throws JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);
		
		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "paymentPendingList",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<DDPaymentFormBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			DDPaymentFormBean applicationBean = gson.fromJson(
					jsonArray.getString(i), DDPaymentFormBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("ddPaymentPending", "list", model);
	}
	
	

	@RequestMapping(value = "/registeredApplicationApproved", method = RequestMethod.POST)
	@ResponseBody
	public String registeredApplicationApproved(DDPaymentFormBean dDPaymentFormBean)
			throws JSONException {


		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(dDPaymentFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "registeredApplicationApproved",

				HttpMethod.POST, entity, String.class);

		return out.getBody().toString();

	}

	@RequestMapping(value = "/registeredApplicationRejected", method = RequestMethod.POST)
	@ResponseBody
	public String registeredApplicationRejected(DDPaymentFormBean dDPaymentFormBean)
			throws JSONException {


		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(dDPaymentFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "registeredApplicationApproved",

				HttpMethod.POST, entity, String.class);

		return out.getBody().toString();

	}
	

	
	
	@RequestMapping(value = "/ddPaymentApproved", method = RequestMethod.POST)
	@ResponseBody
	public String ddPaymentApproved(DDPaymentFormBean dDPaymentFormBean)
			throws JSONException {


		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(dDPaymentFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "ddPaymentApproved",

				HttpMethod.POST, entity, String.class);

		return out.getBody().toString();

	}

	@RequestMapping(value = "/ddPaymentRejected", method = RequestMethod.POST)
	@ResponseBody
	public String ddPaymentRejected(DDPaymentFormBean dDPaymentFormBean)
			throws JSONException {


		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(dDPaymentFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "ddPaymentRejected",

				HttpMethod.POST, entity, String.class);

		return out.getBody().toString();

	}
	
	
	@RequestMapping(value = "/paymentRejectedList", method = RequestMethod.GET)
	public ModelAndView paymentRejectedList() throws JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "paymentRejectedList",

				HttpMethod.GET, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<DDPaymentFormBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			DDPaymentFormBean applicationBean = gson.fromJson(
					jsonArray.getString(i), DDPaymentFormBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("ddPaymentRejected", "list", model);
	}
	


	@RequestMapping(value = "/paymentApprovedList", method = RequestMethod.GET)
	public ModelAndView paymentApprovedList() throws JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "paymentApprovedList",

				HttpMethod.GET, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<DDPaymentFormBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			DDPaymentFormBean applicationBean = gson.fromJson(
					jsonArray.getString(i), DDPaymentFormBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("ddPaymentApproved", "list", model);
	}
	


		
		@RequestMapping(value = "/paymentViewAll", method = RequestMethod.GET)
	public ModelAndView paymentViewAll(
			@ModelAttribute("dashboardForm") ComplaintBean complaintBean)
			throws JSONException {

			Map<String, Object> model = new HashMap<String, Object>();

			RestTemplate restTemplate = new RestTemplate();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<?> entity = new HttpEntity(headers);

			ResponseEntity<String> out = restTemplate.exchange(
					WaterDashboardService + "ddPaymentViewAllList",

					HttpMethod.GET, entity, String.class);

			JSONArray jsonArray = new JSONArray(out.getBody().toString());

			gson = new Gson();

			List<DDPaymentFormBean> applicationBeanList = new ArrayList<>();

			for (int i = 0; i < jsonArray.length(); i++) {
				DDPaymentFormBean applicationBean = gson.fromJson(
						jsonArray.getString(i), DDPaymentFormBean.class);
				applicationBeanList.add(applicationBean);
			}

			model.put("appBean", applicationBeanList);
			// model.put("categoryCount", publicdashboard());
			return new ModelAndView("ddPaymentViewAll", "list", model);
		}
		
		@RequestMapping(value = "/paymentViewForm", method = RequestMethod.GET)
		public ModelAndView paymentViewForm(@RequestParam String appId) {
			DDPaymentFormBean ddPaymentFormBean = new DDPaymentFormBean();
			ddPaymentFormBean.setAppId(appId);
			//applicationBean.setUserId(session.getAttribute("LoginID").toString());
			Map<String, Object> model = new HashMap<String, Object>();

			RestTemplate restTemplate = new RestTemplate();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<?> entity = new HttpEntity(ddPaymentFormBean, headers);

			ResponseEntity<String> out = restTemplate.exchange(
					WaterDashboardService + "paymentViewForm", HttpMethod.POST, entity,
					String.class);

			// ApplicationBean appBean = out.getBody();
			gson = new Gson();
			ddPaymentFormBean = gson.fromJson(out.getBody().toString(),
					DDPaymentFormBean.class);

			model.put("application", ddPaymentFormBean);
			// model.put("categoryCount", publicdashboard());
			return new ModelAndView("ddPaymentViewForm", "list", model);
		}
		
		

	
	@RequestMapping(value = "/configrationManagement", method = RequestMethod.GET)
	public ModelAndView configrationManagement() throws JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);
		
		

		ResponseEntity<String> out1 = restTemplate.exchange(
				WaterDashboardService + "getOfficeDtl",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray1 = new JSONArray(out1.getBody().toString());

		gson = new Gson();

		List<OfficeFormBean> officeFormBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray1.length(); i++) {
			OfficeFormBean officeFormBean = gson.fromJson(
					jsonArray1.getString(i), OfficeFormBean.class);
			officeFormBeanList.add(officeFormBean);
		}

		
		
		model.put("officeDtl", officeFormBeanList);
		

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
	
	
	@RequestMapping(value = "/officeLocation", method = RequestMethod.GET)
	public ModelAndView officeLocation() throws JsonSyntaxException, JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);
		
		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "getOfficeDtl",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<OfficeFormBean> officeFormBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			OfficeFormBean officeFormBean = gson.fromJson(
					jsonArray.getString(i), OfficeFormBean.class);
			officeFormBeanList.add(officeFormBean);
		}

		
		
		model.put("officeDtl", officeFormBeanList);
		
		
		
		
		
		
		

		return new ModelAndView("officeLocation", "list", model);
	}

	
	@RequestMapping(value = "/paymentType", method = RequestMethod.GET)
	public ModelAndView paymentType() throws JsonSyntaxException, JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);
				
		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "getPaymentTypeDtl",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<PaymentFormBean> paymentFormBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			PaymentFormBean paymentFormBean = gson.fromJson(
					jsonArray.getString(i), PaymentFormBean.class);
			paymentFormBeanList.add(paymentFormBean);
		}

		
		
		model.put("paymentTypeDtl", paymentFormBeanList);
		
		
		
		
		
		
		

		return new ModelAndView("paymentType", "list", model);
	}
	

	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	public ModelAndView payment() throws JsonSyntaxException, JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);
		

		ResponseEntity<String> out1 = restTemplate.exchange(
				WaterDashboardService + "getPaymentTypeDtl",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray1 = new JSONArray(out1.getBody().toString());

		gson = new Gson();

		List<PaymentFormBean> paymenttypeFormBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray1.length(); i++) {
			PaymentFormBean paymentTypeFormBean = gson.fromJson(
					jsonArray1.getString(i), PaymentFormBean.class);
			paymenttypeFormBeanList.add(paymentTypeFormBean);
		}

		
		
		model.put("paymentTypeDtl", paymenttypeFormBeanList);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "getPaymentDtl",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<PaymentFormBean> paymentFormBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			PaymentFormBean paymentFormBean = gson.fromJson(
					jsonArray.getString(i), PaymentFormBean.class);
			paymentFormBeanList.add(paymentFormBean);
		}

		
		
		model.put("paymentDtl", paymentFormBeanList);
		
		
		
		
		
		
		

		return new ModelAndView("payment", "list", model);
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
	
	@RequestMapping(value = "/districtManagement", method = RequestMethod.GET)
	public ModelAndView districtManagement() throws JsonSyntaxException, JSONException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "getDistrictDtl",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<DistrictFormBean> districtFormBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			DistrictFormBean districtFormBean = gson.fromJson(
					jsonArray.getString(i), DistrictFormBean.class);
			districtFormBeanList.add(districtFormBean);
		}

		
		
		model.put("districtDtl", districtFormBeanList);

		return new ModelAndView("districtManagement", "list", model);
	}


	@RequestMapping(value = "/districtTalukManagement", method = RequestMethod.GET)
	public ModelAndView districtTalukManagement() throws JSONException, IOException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);

		ResponseEntity<String> out1 = restTemplate.exchange(
				WaterDashboardService + "getDistrictDtl",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray1 = new JSONArray(out1.getBody().toString());

		Gson gson1 = new Gson();


		Map<String,String> districtMap = new LinkedHashMap<>();
		for (int i = 0; i < jsonArray1.length(); i++) {
			DistrictFormBean districtFormBean = gson1.fromJson(
					jsonArray1.getString(i), DistrictFormBean.class);
			if(!districtMap.containsKey(String.valueOf(districtFormBean.getDistrictId()))){
				districtMap.put(String.valueOf(districtFormBean.getDistrictId()), districtFormBean.getDistrictName());
				}
		}
		
		
		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "getTalukDtl",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<DistrictTalukFormBean> districtTalukFormBeanList = new ArrayList<>();

		
		for (int i = 0; i < jsonArray.length(); i++) {
			DistrictTalukFormBean districtTalukFormBean = gson.fromJson(
					jsonArray.getString(i), DistrictTalukFormBean.class);
			districtTalukFormBeanList.add(districtTalukFormBean);
		}

		updateDistrictTalukFile(districtTalukFormBeanList);
		
		model.put("districtTalukDtl", districtTalukFormBeanList);
		model.put("districtMap", districtMap);

		return new ModelAndView("districtTalukManagement", "list", model);
	}
	
	@RequestMapping(value = "/talukVillageManagement", method = RequestMethod.GET)
	public ModelAndView talukVillageManagement() throws JSONException, IOException {

		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(headers);
		
		
		ResponseEntity<String> out1 = restTemplate.exchange(
				WaterDashboardService + "getTalukDtl",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray1 = new JSONArray(out1.getBody().toString());

		Gson gson1 = new Gson();


		Map<String,String> talukMap = new LinkedHashMap<>();
		for (int i = 0; i < jsonArray1.length(); i++) {
			DistrictTalukFormBean districtTalukFormBean = gson1.fromJson(
					jsonArray1.getString(i), DistrictTalukFormBean.class);
			if(!talukMap.containsKey(String.valueOf(districtTalukFormBean.getTalukId()))){
				talukMap.put(String.valueOf(districtTalukFormBean.getTalukId()), districtTalukFormBean.getTalukName());
				}
		}
		
		
		
		
		
		
		
		
		

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "getVillageDtl",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<TalukVillageFormBean> talukVillageFormBeanList = new ArrayList<>();

		
		for (int i = 0; i < jsonArray.length(); i++) {
			TalukVillageFormBean talukVillageFormBean = gson.fromJson(
					jsonArray.getString(i), TalukVillageFormBean.class);
			
			
			
			talukVillageFormBeanList.add(talukVillageFormBean);
		}

		updateTalukVillageFile(talukVillageFormBeanList);
		
		model.put("talukVillageDtl", talukVillageFormBeanList);
		model.put("talukMap", talukMap);

		return new ModelAndView("talukVillageManagement", "list", model);
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
	
	
	
	
	
	
	
	public void updateDistrictTalukFile(List<DistrictTalukFormBean> districtTalukFormBeanList)
			throws IOException, JSONException {

		String path = this.getClass().getClassLoader().getResource("").getPath();
		String fullPath = URLDecoder.decode(path, "UTF-8");
		String pathArr[] = fullPath.split("WEB-INF/");

		try {

			FileWriter fw = new FileWriter(pathArr[0] + "library/test1.json");
			BufferedWriter bw = new BufferedWriter(fw);

			Map<String, List<Map<Integer,String>>> districtTalukMap = new HashMap<String, List<Map<Integer,String>>>();
			List<Map<Integer,String>> talukList = null;
			Map<Integer,String> talukMap = null;
			for (DistrictTalukFormBean districtTalukFormBean : districtTalukFormBeanList) {

				String key = String.valueOf(districtTalukFormBean.getDistrictId());

				if (districtTalukMap.containsKey(key)) {
					talukList = districtTalukMap.get(key);
					talukMap = new HashMap<Integer,String>();
					talukMap.put(districtTalukFormBean.getTalukId(),districtTalukFormBean.getTalukName());
					talukList.add(talukMap);

				} else {
					talukList = new ArrayList<Map<Integer,String>>();
					talukMap = new HashMap<Integer,String>();
					talukMap.put(districtTalukFormBean.getTalukId(),districtTalukFormBean.getTalukName());
					talukList.add(talukMap);
					districtTalukMap.put(key, talukList);

				}

			}
			Gson gson = new Gson(); 
			String json = gson.toJson(districtTalukMap); 
			//System.out.println(json);
			
			bw.write(json);
			bw.close();
			fw.close();

			File newFile = new File(pathArr[0] + "library/test1.json");
			File oldFile = new File(pathArr[0] + "library/DistrictTaluk.json");
			if (oldFile.exists()) {
				oldFile.delete();
			}
			newFile.renameTo(oldFile);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}
	
	

	public void updateTalukVillageFile(List<TalukVillageFormBean> talukVillageFormBeanList)
			throws IOException, JSONException {

		String path = this.getClass().getClassLoader().getResource("").getPath();
		String fullPath = URLDecoder.decode(path, "UTF-8");
		String pathArr[] = fullPath.split("WEB-INF/");

		try {

			FileWriter fw = new FileWriter(pathArr[0] + "library/test2.json");
			BufferedWriter bw = new BufferedWriter(fw);

			Map<String, List<Map<Integer,String>>> talukVillageMap = new HashMap<String, List<Map<Integer,String>>>();
			List<Map<Integer,String>> villageList = null;
			Map<Integer,String> villageMap = null;
			for (TalukVillageFormBean talukVillageFormBean : talukVillageFormBeanList) {

				String key = String.valueOf(talukVillageFormBean.getTalukId());

				if (talukVillageMap.containsKey(key)) {
					villageList = talukVillageMap.get(key);
					villageMap = new HashMap<Integer,String>();
					villageMap.put(talukVillageFormBean.getVillageId(),talukVillageFormBean.getVillageName());
					villageList.add(villageMap);

				} else {
					villageList = new ArrayList<Map<Integer,String>>();
					villageMap = new HashMap<Integer,String>();
					villageMap.put(talukVillageFormBean.getVillageId(),talukVillageFormBean.getVillageName());
					villageList.add(villageMap);
					talukVillageMap.put(key, villageList);

				}

			}
			Gson gson = new Gson(); 
			String json = gson.toJson(talukVillageMap); 
			//System.out.println(json);
			
			bw.write(json);
			bw.close();
			fw.close();

			File newFile = new File(pathArr[0] + "library/test2.json");
			File oldFile = new File(pathArr[0] + "library/TalukVillage.json");
			if (oldFile.exists()) {
				oldFile.delete();
			}
			newFile.renameTo(oldFile);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
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
	
	

	@RequestMapping(value = "/addOffice", method = RequestMethod.POST)
	@ResponseBody
	public String addOffice(OfficeFormBean officeFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(officeFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "addOffice", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}
	

	@RequestMapping(value = "/editOffice", method = RequestMethod.POST)
	@ResponseBody
	public String editOffice(OfficeFormBean officeFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(officeFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "editOffice", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}

	@RequestMapping(value = "/deleteOffice", method = RequestMethod.POST)
	@ResponseBody
	public String deleteOffice(OfficeFormBean officeFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(officeFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "deleteOffice", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}
	


	@RequestMapping(value = "/addPaymentType", method = RequestMethod.POST)
	@ResponseBody
	public String addPaymentType(PaymentFormBean paymentTypeFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(paymentTypeFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "addPaymentType", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}
	

	@RequestMapping(value = "/editPaymentType", method = RequestMethod.POST)
	@ResponseBody
	public String editPaymentType(PaymentFormBean paymentTypeFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(paymentTypeFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "editPaymentType", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}

	@RequestMapping(value = "/deletePaymentType", method = RequestMethod.POST)
	@ResponseBody
	public String deletePaymentType(PaymentFormBean paymentTypeFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(paymentTypeFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "deletePaymentType", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}
	

	@RequestMapping(value = "/addPayment", method = RequestMethod.POST)
	@ResponseBody
	public String addPayment(PaymentFormBean paymentFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(paymentFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "addPayment", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}
	

	@RequestMapping(value = "/editPayment", method = RequestMethod.POST)
	@ResponseBody
	public String editPayment(PaymentFormBean paymentFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(paymentFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "editPayment", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}

	@RequestMapping(value = "/deletePayment", method = RequestMethod.POST)
	@ResponseBody
	public String deletePayment(PaymentFormBean paymentFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(paymentFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "deletePayment", HttpMethod.POST,
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

	
	

	@RequestMapping(value = "/addDistrict", method = RequestMethod.POST)
	@ResponseBody
	public String addDistrict(DistrictFormBean districtFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(districtFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "addDistrict", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}
	

	@RequestMapping(value = "/editDistrict", method = RequestMethod.POST)
	@ResponseBody
	public String editDistrict(DistrictFormBean districtFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(districtFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "editDistrict", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}

	@RequestMapping(value = "/deleteDistrict", method = RequestMethod.POST)
	@ResponseBody
	public String deleteDistrict(DistrictFormBean districtFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(districtFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "deleteDistrict", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}
	
	
	

	@RequestMapping(value = "/addTaluk", method = RequestMethod.POST)
	@ResponseBody
	public String addTaluk(DistrictTalukFormBean districtTalukFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(districtTalukFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "addTaluk", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}
	

	@RequestMapping(value = "/editTaluk", method = RequestMethod.POST)
	@ResponseBody
	public String editTaluk(DistrictTalukFormBean districtTalukFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(districtTalukFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "editTaluk", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}

	@RequestMapping(value = "/deleteTaluk", method = RequestMethod.POST)
	@ResponseBody
	public String deleteTaluk(DistrictTalukFormBean districtTalukFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(districtTalukFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "deleteTaluk", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}


	
	
	
	
	
	
	
	
	
	

	@RequestMapping(value = "/addVillage", method = RequestMethod.POST)
	@ResponseBody
	public String addVillage(TalukVillageFormBean VillageVillageFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(VillageVillageFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "addVillage", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}
	

	@RequestMapping(value = "/editVillage", method = RequestMethod.POST)
	@ResponseBody
	public String editVillage(TalukVillageFormBean VillageVillageFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(VillageVillageFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "editVillage", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}

	@RequestMapping(value = "/deleteVillage", method = RequestMethod.POST)
	@ResponseBody
	public String deleteVillage(TalukVillageFormBean talukVillageFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(talukVillageFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "deleteVillage", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}


	
	//New method by Mahalingam
	
	@RequestMapping(value = "/eePendingApplication", method = RequestMethod.GET)
	public ModelAndView eePendingApplication(
			@ModelAttribute("dashboardForm") ComplaintBean complaintBean,HttpSession session)
			throws JSONException {

		CompanyDtlBean companyDtlBean = new CompanyDtlBean();
		if(null != session.getAttribute("OfficeId")){
		  companyDtlBean.setOffice(session.getAttribute("OfficeId").toString());
		}
		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();
        
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(companyDtlBean,headers);

		

		ResponseEntity<String> out1 = restTemplate.exchange(
				WaterDashboardService + "getPaymentTypeDtl",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray1 = new JSONArray(out1.getBody().toString());

		gson = new Gson();

		List<PaymentFormBean> paymenttypeFormBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray1.length(); i++) {
			PaymentFormBean paymentTypeFormBean = gson.fromJson(
					jsonArray1.getString(i), PaymentFormBean.class);
			paymenttypeFormBeanList.add(paymentTypeFormBean);
		}

		model.put("paymentTypeDtl", paymenttypeFormBeanList);

		
		
		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "eePendingApplication",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<CompanyDtlBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			CompanyDtlBean applicationBean = gson.fromJson(
					jsonArray.getString(i), CompanyDtlBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("eePendingApplication", "list", model);
	}
	
	@RequestMapping(value = "/eeAddPayment", method = RequestMethod.POST)
	@ResponseBody
	public String eeAddPayment(PaymentFormBean paymentFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(paymentFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "eeAddPayment", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}
	
	

	

	@RequestMapping(value = "/eePaymentPending", method = RequestMethod.GET)
	public ModelAndView eePaymentPending(HttpSession session)
			throws JSONException {

		CompanyDtlBean companyDtlBean = new CompanyDtlBean();
		if(null != session.getAttribute("OfficeId")){
		  companyDtlBean.setOffice(session.getAttribute("OfficeId").toString());
		}
		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(companyDtlBean,headers);
		
		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "eePaymentPending",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<DDPaymentFormBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			DDPaymentFormBean applicationBean = gson.fromJson(
					jsonArray.getString(i), DDPaymentFormBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("eePaymentPending", "list", model);
	}
	
	@RequestMapping(value = "/eePaymentPendingApproved", method = RequestMethod.POST)
	@ResponseBody
	public String eePaymentPendingApproved(PaymentFormBean paymentFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(paymentFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "eePaymentPendingApproved", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}
	

	@RequestMapping(value = "/eePaymentCompleted", method = RequestMethod.GET)
	public ModelAndView eePaymentCompleted(HttpSession session)
			throws JSONException {
		CompanyDtlBean companyDtlBean = new CompanyDtlBean();
		if(null != session.getAttribute("OfficeId")){
		  companyDtlBean.setOffice(session.getAttribute("OfficeId").toString());
		}
		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(companyDtlBean,headers);
		
		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "eePaymentCompleted",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<DDPaymentFormBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			DDPaymentFormBean applicationBean = gson.fromJson(
					jsonArray.getString(i), DDPaymentFormBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("eePaymentCompleted", "list", model);
	}
	
	
	@RequestMapping(value = "/eePaymentCompletedApproved", method = RequestMethod.POST)
	@ResponseBody
	public String eePaymentCompletedApproved(PaymentFormBean paymentFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(paymentFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "eePaymentCompletedApproved", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}
	

	@RequestMapping(value = "/eeInspectedApplication", method = RequestMethod.GET)
	public ModelAndView eeInspectedApplication(HttpSession session)
			throws JSONException {

		
		CompanyDtlBean companyDtlBean = new CompanyDtlBean();
		if(null != session.getAttribute("OfficeId")){
		  companyDtlBean.setOffice(session.getAttribute("OfficeId").toString());
		}
		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(companyDtlBean,headers);
		
		


		ResponseEntity<String> out1 = restTemplate.exchange(
				WaterDashboardService + "getPaymentTypeDtl",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray1 = new JSONArray(out1.getBody().toString());

		gson = new Gson();

		List<PaymentFormBean> paymenttypeFormBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray1.length(); i++) {
			PaymentFormBean paymentTypeFormBean = gson.fromJson(
					jsonArray1.getString(i), PaymentFormBean.class);
			paymenttypeFormBeanList.add(paymentTypeFormBean);
		}

		model.put("paymentTypeDtl", paymenttypeFormBeanList);


		
		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "eeInspectedApplication",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<DDPaymentFormBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			DDPaymentFormBean applicationBean = gson.fromJson(
					jsonArray.getString(i), DDPaymentFormBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("eeInspectedApplication", "list", model);
	}
	
	
	@RequestMapping(value = "/eeAddFullPayment", method = RequestMethod.POST)
	@ResponseBody
	public String eeAddFullPayment(PaymentFormBean paymentFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(paymentFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "eeAddFullPayment", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}
	

	@RequestMapping(value = "/eeMCApproved", method = RequestMethod.GET)
	public ModelAndView eeMCApproved(HttpSession session)
			throws JSONException {

		
		CompanyDtlBean companyDtlBean = new CompanyDtlBean();
		if(null != session.getAttribute("OfficeId")){
		  companyDtlBean.setOffice(session.getAttribute("OfficeId").toString());
		}
		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(companyDtlBean,headers);
		
		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "eeMCApproved",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<DDPaymentFormBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			DDPaymentFormBean applicationBean = gson.fromJson(
					jsonArray.getString(i), DDPaymentFormBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("eeMCApproved", "list", model);
	}
	
	@RequestMapping(value = "/eeFullPaymentApproved", method = RequestMethod.POST)
	@ResponseBody
	public String eeFullPaymentApproved(PaymentFormBean paymentFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(paymentFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "eeFullPaymentApproved", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}
	
	@RequestMapping(value = "/eeMCApprovedBtn", method = RequestMethod.POST)
	@ResponseBody
	public String eeMCApprovedBtn(PaymentFormBean paymentFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(paymentFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "eeMCApprovedBtn", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}
	
	
	
	@RequestMapping(value = "/mcApprovePayment", method = RequestMethod.POST)
	@ResponseBody
	public String mcApprovePayment(PaymentFormBean paymentFormBean) {

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(paymentFormBean,headers);

		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "mcApprovePayment", HttpMethod.POST,
				entity, String.class);

		String res = out.getBody();
		return res;
	}
	

	@RequestMapping(value = "/eeFullPaymentCompleted", method = RequestMethod.GET)
	public ModelAndView eeFullPaymentCompleted(HttpSession session)
			throws JSONException {

		
		CompanyDtlBean companyDtlBean = new CompanyDtlBean();
		if(null != session.getAttribute("OfficeId")){
		  companyDtlBean.setOffice(session.getAttribute("OfficeId").toString());
		}
		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(companyDtlBean,headers);
		
		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "eeFullPaymentCompleted",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<DDPaymentFormBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			DDPaymentFormBean applicationBean = gson.fromJson(
					jsonArray.getString(i), DDPaymentFormBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("eeFullPaymentCompleted", "list", model);
	}
	

	@RequestMapping(value = "/eeExecution", method = RequestMethod.GET)
	public ModelAndView eeExecution(HttpSession session)
			throws JSONException {

		
		CompanyDtlBean companyDtlBean = new CompanyDtlBean();
		if(null != session.getAttribute("OfficeId")){
		  companyDtlBean.setOffice(session.getAttribute("OfficeId").toString());
		}
		Map<String, Object> model = new HashMap<String, Object>();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<?> entity = new HttpEntity(companyDtlBean,headers);
		
		ResponseEntity<String> out = restTemplate.exchange(
				WaterDashboardService + "eeExecution",
				HttpMethod.POST, entity, String.class);

		JSONArray jsonArray = new JSONArray(out.getBody().toString());

		gson = new Gson();

		List<DDPaymentFormBean> applicationBeanList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			DDPaymentFormBean applicationBean = gson.fromJson(
					jsonArray.getString(i), DDPaymentFormBean.class);
			applicationBeanList.add(applicationBean);
		}

		model.put("appBean", applicationBeanList);
		// model.put("categoryCount", publicdashboard());
		return new ModelAndView("eeExecution", "list", model);
	}
	
	
	
	
	
	
	
}
