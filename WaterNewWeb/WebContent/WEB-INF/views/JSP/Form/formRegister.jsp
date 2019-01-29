<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

  
<!DOCTYPE html>
<html>
<head>
<link href="library/css/style.css" rel="stylesheet" />
<!-- <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> -->

<link rel="stylesheet"
	href="library/assets/plugins/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="library/assets/css/main.css" />
<link rel="stylesheet" href="assets/css/theme.css" />
<link rel="stylesheet" href="assets/css/MoneAdmin.css" />
<link rel="stylesheet"
	href="assets/plugins/Font-Awesome/css/font-awesome.css" />
<link rel="stylesheet"
	href="assets/plugins/validationengine/css/validationEngine.jquery.css" />


<script src="library/js/jquery-1.7.2.min.js"></script>
<script src="library/js/jquery-ui-1.8.21.custom.min.js"></script>
<script src='library/js/jquery.dataTables.min.js'></script>
<script src='library/js/chosen.jquery.js'></script>
<script src='library/js/jquery.validate.js'></script>
<script src='library/js/dataTables.fixedColumns.js'></script>
<script src='library/js/jquery-ui-timepicker-addon.js'></script>
<script src='JS/common.js'></script>


<link href="library/css/jquery-ui-1.8.21.custom.css" rel="stylesheet">
<body style="margin: 0px; padding: 0px"
	background="library/img/border_bg.jpg">

	<style>
/*  #mydiv {
    margin:auto;
 	position:absolute;
    left: 50%;
    width:1200px;
    height:300px;
     margin-left: -600px;
    border: 0px;
}

 #myTable {
    margin:auto;
 	position:absolute;
    left: 50%;
    width:1200px;
    height:400px;
     margin-left: -600px;
    border: 0px;
}
 */
div[role="dialog"] {
	border-radius: 15px;
}

.loginDialog {
	display: none;
	width: 400px;
	height: 200px;
	padding: 0px !important;
	overflow: hidden !important;
}
/*
.open{
display:block;
} */
/* 
.dropdown, .dropup {
    position: relative;
} */
input[type="button"] {
	border-radius: 10px;
	background-color: #2DAAE1;
	font-weight: bold;
	cursor: pointer;
	padding: 5px 5px 5px 5px;
	margin-left: 150px;
	width: 100px;
	color: white;
	margin-top: 30px;
}

.ui-dialog>.ui-widget-header {
	background: blue;
	display: none;
}

.ui-dialog {
	padding: 0px;
	overflow: hidden;
}

.dropdown-inline {
	display: inline-block;
	position: relative;
	font-family: Lato;
	font-size: 13px;
	margin-left: 10px;
	padding-bottom: 10px;
}

.dropdown-slide {
	/*  display: inline-block; */
	position: relative;
	font-family: Lato;
	font-size: 13px;
	/*  margin-left: 10px; */
	padding-bottom: 10px;
}

* {
	border-collapse: collapse;
}

.dropdown-menu {
	position: absolute;
	top: 100%;
	left: 0;
	z-index: 1000;
	display: none;
	float: left;
	min-width: 160px;
	padding: 5px 0;
	margin: 2px 0 0;
	font-size: 14px;
	text-align: left;
	list-style: none;
	background-color: #fff;
	-webkit-background-clip: padding-box;
	background-clip: padding-box;
	border: 1px solid #ccc;
	border: 1px solid rgba(0, 0, 0, .15);
	border-radius: 4px;
	-webkit-box-shadow: 0 6px 12px rgba(0, 0, 0, .175);
	box-shadow: 0 6px 12px rgba(0, 0, 0, .175);
}

.dropdown-menu>li>a {
	display: block;
	padding: 3px 20px;
	clear: both;
	font-weight: 400;
	line-height: 1.42857143;
	color: #333;
	white-space: nowrap;
	font-size: 13px;
}

.ftrmenu {
	font-size: 18px;
}

.caret {
	display: inline-block;
	width: 0;
	height: 0;
	margin-left: 2px;
	vertical-align: middle;
	border-top: 4px dashed;
	border-top: 4px solid\9;
	border-right: 4px solid transparent;
	border-left: 4px solid transparent;
}

.bg_heading {
	text-align: left;
	font-family: Lato;
	font-size: 20px;
	text-transform: uppercase;
	color: white;
	background-color: #2DAAE1;
	border-radius: 15px 0px 0px 0px;
	margin: 0px;
}

input[type="text"],input[type="password"] ,input[type="file"]{
	width: 330px;
	height: 40px;
	margin-left: 15px;
	padding: 5px;
}

select.lessWidth {
	width: 100px;
	height: 40px;
	margin-left: 15px;
	padding: 5px;
}

select.moreWidth {
	width: 210px;
	height: 40px;
	margin-left: 15px;
	padding: 5px;
}

select.classCategory {
	width: 332px;
	height: 40px;
	margin-left: 15px;
	padding: 5px;
}

.dropdown-menu .divider {
	height: 1px;
	margin: 9px 0;
	overflow: hidden;
	background-color: #e5e5e5;
}

.error {
	color: red;
}
</style>
<script type="text/javascript">


	$(document).ready(function(){
		
		document.getElementById("isNewConnectionId").style.visibility = "hidden";
	var hcafid = 	$('#HCafId').val();
	
	if(hcafid=='null' ){
		
		$('#mobileNumId').removeAttr('readonly');
		$('#landLineNoId').removeAttr('readonly');
		
	}
	else{
		
		$('#mobileNumId').prop('readonly','true');
		$('#landLineNoId').prop('readonly','true');
	}
		

		
		var legCompName = $('#H1').val();
		if(legCompName!=null && legCompName!="null" ){
			$('#legCompNameId').val(legCompName);
			$('#contactPersonNameId').val($('#H3').val());
			$('#mobileNumId').val($('#H4').val());
			$('#landLineNoId').val($('#H5').val());
			$('#emailAddrId').val($('#H6').val());
			
			var correspondenceAddr = $('#H2').val();
			if(correspondenceAddr !=null && correspondenceAddr !="null"){
				var cAddrArray = correspondenceAddr.split(",");
				$('#cdoorNoId').val(cAddrArray[0]);
				$('#cplotNoId').val(cAddrArray[1]);
				$('#cstreetNameId').val(cAddrArray[2]);
				$('#clocationId').val(cAddrArray[3]);
				$('#cpinCodeId').val(cAddrArray[4]);
			}
			var siteAddr = $('#H7').val();
            if(siteAddr !=null && siteAddr !="null"){
            	var addrArray = siteAddr.split(",");
            	$('#doorNoId').val(addrArray[0]);
				$('#plotNoId').val(addrArray[1]);
				$('#streetNameId').val(addrArray[2]);
				$('#locationId').val(addrArray[3]);
				$('#pinCodeId').val(addrArray[4]);
			}
			
		}

		
		var registeredDataLocal = JSON.parse(localStorage.getItem('registeredData'));
		
		//$("#mobileNumId").prop('readonly', true);
		//$("#landLineNoId").prop('readonly', true);
			 if(registeredDataLocal !=null){
				$("#legCompNameId").val(registeredDataLocal.legCompName);
				$("#cdoorNoId").val(registeredDataLocal.cdoorNo);
				$("#cplotNoId").val(registeredDataLocal.cplotNo);
				$("#cstreetNameId").val(registeredDataLocal.cstreetName);
				$("#clocationId").val(registeredDataLocal.clocation); 
				$("#cpinCodeId").val(registeredDataLocal.cpinCode);
				
				
				
				$('#salutationId option[value="'+registeredDataLocal.salutation+'"]').attr('selected', 'selected');
				$("#contactPersonNameId").val(registeredDataLocal.contactPersonName);
				$("#mobileNumId").val(registeredDataLocal.mobileNum);
				$("#landLineNoId").val(registeredDataLocal.landLineNo);
				$("#emailAddrId").val(registeredDataLocal.emailAddr.replace('%40','@'));
				$("#webAddressId").val(registeredDataLocal.webAddress);
				
				$('#categoryTypeId option[value="'+registeredDataLocal.categoryType+'"]').attr('selected', 'selected');
				$("#doorNoId").val(registeredDataLocal.doorNo);
				$("#plotNoId").val(registeredDataLocal.plotNo);
				$("#streetNameId").val(registeredDataLocal.streetName);
				$("#locationId").val(registeredDataLocal.location); 
				$("#pinCodeId").val(registeredDataLocal.pinCode);
				$('#isNewConnectionId option[value="'+registeredDataLocal.isNewConnection+'"]').attr('selected', 'selected');
				$('#isExistConnectionForAlterationId option[value="'+registeredDataLocal.isExistConnectionForAlteration+'"]').attr('selected', 'selected');
				
				if (registeredDataLocal.isNewConnection == 1 && registeredDataLocal.isExistConnectionForAlteration == 1) {
					$("#isReconnectionForServiceId").attr('disabled', 'disabled');
					$("#reconnectionTypeId").attr('disabled', 'disabled');
				} else {
					$("#isReconnectionForServiceId").removeAttr('disabled');
					$("#reconnectionTypeId").removeAttr('disabled');
					$('#isReconnectionForServiceId option[value="'+registeredDataLocal.isReconnectionForService+'"]').attr('selected', 'selected');
				}
				
				if(registeredDataLocal.isReconnectionForService==1){
				 $('#reconnectionTypeId option[value="'+registeredDataLocal.reconnectionType+'"]').attr('selected', 'selected');
				}
				else{
					$("#reconnectionTypeId").attr('disabled', 'disabled');
				}
				$("#reqMldId").val(registeredDataLocal.reqMld);
				$("#ipf").val(registeredDataLocal.ipf);
				$("#gstAmount").val(registeredDataLocal.gstAmount);
				$("#totalAmount").val(registeredDataLocal.totalAmount);
				$('#cmwssbZoneNumId option[value="'+registeredDataLocal.cmwssbZoneNum+'"]').attr('selected', 'selected');
				
				
				$
				.ajax({
					type : "GET",
					url : "/WaterNew/library/ZoneDivision.json",
					success : function(response) {
						var divValue = response[registeredDataLocal.cmwssbZoneNum];
						var option = '<option value="">--Select--</option>';
						for (var i = 0; i < divValue.length; i++) {

							option = option
									+ '<option value="'+divValue[i]+'">'
									+ divValue[i]
									+ '</option>';
						}
						$('#divId').find('option')
								.remove();
						$('#divId').append(option);
						$('#divId option[value="'+registeredDataLocal.divId+'"]').attr('selected', 'selected');

					}
				});
				
				
			
				$('#billNumId2').val(registeredDataLocal.billNo2);
				$('#billNumId1').val(registeredDataLocal.billNo1);
				$('#annAssmtValId').val(registeredDataLocal.annAssmtVal);
				
				
				
				$('#intrPlumStatusId option[value="'+registeredDataLocal.intrPlumStatus+'"]').attr('selected', 'selected');
				$('#watSevPropId option[value="'+registeredDataLocal.watSevProp+'"]').attr('selected', 'selected');
				$('#workTypeId option[value="'+registeredDataLocal.workType+'"]').attr('selected', 'selected');
				
				} 
		
		
		
		
		$('#reqMldId').blur(function(){
			
			//var whichPayment = $('input[name="paymentMode"]:checked').val();
			var reqMldId = $('#reqMldId').val();
	
			if(reqMldId!=""){
			$.ajax({
				type:"POST",
				url:"getIpfAmount.do",
				data:{'reqMldId':reqMldId},
				success:function(response){
					
					var gst=(response*18)/100;
					var res=response;
					//var g1=parseInt(gst);
					var g1=parseInt(gst);
					var t1=parseInt(res);
					var total=g1+t1;
					
					$('#ipf').val(response);
					$('#gstAmount').val(g1);
					$('#totalAmount').val(total);
					
				}
				
			})
			}
			
		});
		
		
	});
	</script>
	<script type="text/javascript">
	
	
	
	
	
	
	
	
		$('.dropdown-inline, .dropdown-slide').hover(function() {
			$(this).find('ul').css({
				'display' : 'block'
			});
		}, function() {
			$(this).find('ul').css({
				'display' : 'none'
			});
		});

		$(function() {

			$('#cmwssbZoneNumId')
					.change(
							function() {
								var key = $(this).val();

								$
										.ajax({
											type : "GET",
											url : "/WaterNew/library/ZoneDivision.json",
											success : function(response) {
												var divValue = response[key];
												var option = '<option value="">--Select--</option>';
												for (var i = 0; i < divValue.length; i++) {

													option = option
															+ '<option value="'+divValue[i]+'">'
															+ divValue[i]
															+ '</option>';
												}
												$('#divId').find('option')
														.remove();
												$('#divId').append(option);

											}
										});
							});
			
	$('#isNewConnectionId').change(function() {
		if ($('#isNewConnectionId').val() == 1 ) {
			
			$("#isExistConnectionForAlterationId").attr('disabled', 'disabled');
			$("#isReconnectionForServiceId").attr('disabled', 'disabled');
			$("#reconnectionTypeId").attr('disabled', 'disabled');
		} else {
			$("#isExistConnectionForAlterationId").removeAttr('disabled');
			$("#isReconnectionForServiceId").removeAttr('disabled');
			$("#reconnectionTypeId").removeAttr('disabled');
		}
	});

			$('#isReconnectionForServiceId').change(function() {
				if ($(this).val() == 0) {
					$("#reconnectionTypeId").attr('disabled', 'disabled');
				} else {
					$("#reconnectionTypeId").removeAttr('disabled');
				}
			});
			

			$('#registrationbtnId')
					.click(
							function() {

								 $(".uploadClass").each(function(){
									 $('.error').remove();
							         if(this.files[0] != undefined)
								        if(this.files[0].size>5000000) {    // 5 * 1000 * 1000 bytes
				        	        	   $(this).after('<span class="error"> File Should not be more than 5 MB</span>');
							               $(this).focus();
									       return false;
								        }
								        
								 }
								);
								
								if (validateForm()) {
									if (confirm("Are you sure want to Register ?")) {
										$('#loading_image').show();
										var dataString = $('#formId')
												.serialize();
										 var form = $('#formId')[0];
										var paymentMode='initial';
						        	        var formdata = new FormData(form);
						        	        var isUpload=false;
						        	        
						        	        $(".uploadClass").each(function(){
						        	        	if(this !=undefined && $(this).val()!=""){
						        	        		isUpload=true;
						        	        	}
						        	        })  ;    
						        	        

						        	        $("#LoadingImage").show();

										$.ajax({
													type : "POST",
													url : "/WaterNew/companyRegister.do",
													data : dataString,
													async : false,
													
													success : function(response) {
														if(isUpload){
									        	        $.ajax({
									        	            type: "POST",
									        	            enctype: 'multipart/form-data',
									        	            url: "uploadMultipleFile.do",
									        	            data: formdata,
									        	            processData: false,
									        	            contentType: false,
									        	            cache: false,
									        	            timeout: 600000,
									        	            async : false,
									        	           
									        	            success: function (data) {
									        	            	 $("#LoadingImage").hide();
									        	            	 window.location.href="saveOnlinePaymentsDetails.do?paymentMode=initial&appId="+response+"&initialPayment="+$('#totalAmount').val();
									        	            	//window.location.href="saveOnlinePaymentsDetails.do?paymentMode=initial&appId="+response+"&initialPayment="+$('#ipf').val();
									        	            
									        	            },
									        	        });
													}
													else{
														 $("#LoadingImage").hide();
														 window.location.href="saveOnlinePaymentsDetails.do?paymentMode=initial&appId="+response+"&initialPayment="+$('#totalAmount').val();
														//window.location.href="saveOnlinePaymentsDetails.do?paymentMode=initial&appId="+response+"&initialPayment="+$('#ipf').val();
													}
														
														
														
													}
												});
									}
								}
							});

		});

		function validateForm() {
			var numberReg = /^[0-9]+$/;
			var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
			var legCompName = $("#legCompNameId");
			var correspondenceAddr = $("#correspondenceAddrId");
			var salutation = $('#salutationId');
			var contactPersonName = $("#contactPersonNameId");
			var mobileNum = $("#mobileNumId");
			var emailAddr = $("#emailAddrId");
			var categoryType = $("#categoryTypeId");
			//var addrPremSought  =	$("#addrPremSoughtId");
			var isExistConnectionForAlteration = $("#isExistConnectionForAlterationId");
			var isReconnectionForService = $("#isReconnectionForServiceId");
			var reqMld = $("#reqMldId");
			var workType = $("#workTypeId");
			var paymentType = $("#paymentTypeId");
		
			var pinCode = $("#pinCodeId");
			var isNewConnection = $("#isNewConnectionId");
			var cpinCode = $("#cpinCodeId");

			var inputVal = new Array(legCompName, cpinCode, salutation,
					contactPersonName, mobileNum, emailAddr, categoryType,
					pinCode, isNewConnection,
					reqMld, workType);

			$('.error').hide();
			flag = true;
			for (i = 0; i < inputVal.length; i++) {
				if (inputVal[i].val() == "") {
					inputVal[i]
							.after('<span class="error"> This field is required. </span>');
					inputVal[i].focus();
					flag = false;
				} else if (inputVal[i].attr('id') == 'emailAddrId'
						&& !emailReg.test(inputVal[i].val())) {
					inputVal[i]
							.after('<span class="error"> Please enter correct Email Id </span>');
					inputVal[i].focus();
					flag = false;
				} else if (inputVal[i].attr('id') == 'mobileNumId'
						&& inputVal[i].val().length != 10) {
					inputVal[i]
							.after('<span class="error"> Please enter correct mobile No. </span>');
					inputVal[i].focus();
					flag = false;
				}
				
				/*  else if (inputVal[i].attr('id') == 'reqMldId'
						&& !/^[1-9][0-9]+\.[0-9]{2}$/.test(inputVal[i].val())) {
					inputVal[i]
							.after('<span class="error"> Please enter required MLD in Number, With two digit after decimal   </span>');
					inputVal[i].focus();
					flag = false; */
					
					 else if (inputVal[i].attr('id') == 'reqMldId'
							&& !/^[1-9][0-9]+$/.test(inputVal[i].val())) {
						inputVal[i]
								.after('<span class="error"> Please enter required KLD in Number   </span>');
						inputVal[i].focus();
						flag = false;
				} else if (inputVal[i].attr('id') == 'contactPersonNameId'
						&& !/^[a-zA-Z\s]+$/.test(inputVal[i].val())) {
					inputVal[i]
							.after('<span class="error"> Please enter Alphabet and Space </span>');
					inputVal[i].focus();
					flag = false;
				} else if ((inputVal[i].attr('id') == 'pinCodeId' || inputVal[i]
						.attr('id') == 'cpinCodeId')
						&& !/^\d{6}$/.test(inputVal[i].val())) {
					inputVal[i]
							.after('<span class="error"> Please enter 6 digit PinCode </span>');
					inputVal[i].focus();
					flag = false;
				} 
				
				else if((inputVal[i].attr('id') == 'isNewConnectionId' || inputVal[i].attr('id') == 'isExistConnectionForAlterationId')
					&& inputVal[i].val() == 0){
					if($('#isReconnectionForServiceId').val()==''){
					$('.error').hide();
					$('#isReconnectionForServiceId').after('<span class="error">This field is required </span>');
					$('#isExistConnectionForAlterationId').after('<span class="error">This field is required </span>');
					$('#isReconnectionForServiceId').focus();
				flag = false;
				}
				}
					 
				

			}

		 if ($('#isReconnectionForServiceId').val() == 1) {
			if ($('#reconnectionTypeId').val() == "") {
				$('#reconnectionTypeId')
						.after(
								'<span class="error"> Please select reconnection Type </span>');
				$('#reconnectionTypeId').focus();
				flag = false;
			}
			if ($('#divId').val() == "") {
				$('#divId')
						.after(
								'<span class="error"> Please select division Number</span>');
				$('#divId').focus();
				flag = false;
			}
			if ($('#billNumId1').val() == ""
					&& $('#billNumId2').val() == "") {
				$('#billNumId1')
						.after(
								'<span class="error"> Please enter Bill Number </span>');
				$('#billNumId1').focus();
				flag = false;
			}
		}
				
			/* if ($('#billNumId1').val() != ''
					&& !/^\d{3}$/.test($('#billNumId1').val())) {
				$('#billNumId1')
						.after(
								'<span class="error"> Please enter Bill Number contains 3 Digit </span>');
				$('#billNumId1').focus();
				flag = false;
			} */
			/* if ($('#billNumId2').val() != ''
					&& !/^\d{5}$/.test($('#billNumId2').val())) {
				$('#billNumId2')
						.after(
								'<span class="error"> Please enter Bill Number contains 5 Digit </span>');
				$('#billNumId2').focus();
				flag = false;
			} */
			/* if ($('#annAssmtValId').val() != ''
					&& !/^[0-9]+$/.test($('#annAssmtValId').val())) {
				$('#annAssmtValId')
						.after(
								'<span class="error"> Please enter Annual Assessment Value Max 10 digit allowed</span>');
				$('#annAssmtValId').focus();
				flag = false;
			} */ 
			return flag;
		}
	</script>


	<table id="mydiv" align="center">

		<tr>
			<td valign="middle" style="height: 155px; width: 100%;">
				<table>
					<tbody>
						<tr>
							<td><img src="library/img/Logo_Tamil_Nadu.jpg" width="150px"
								height="150px" style="margin-left: 50px;"></td>
							<td><img src="library/img/middleImage.png" width="800px"
								height="75px"></td>
							<td><img src="library/img/pic6_2.jpg" width="150px"
								height="150px" style="margin-right: 50px;"></td>
							<td></td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>



		<tr>



			<td colspan="2" height="25px">

				<div style="padding: 0px; width: 100%;">
					<div
						style="background-image: url(library/img/border_bg.jpg); height: 7px; background-repeat: repeat-x;">
						&nbsp;</div>
				</div>
			</td>
		</tr>
	</table>
	<form id="formId" method="post" enctype="multipart/form-data">
	<input type="hidden" name="appId" value='<%=request.getParameter("applicationRef")%>'>
	<input type="hidden" id="HCafId" name="cafId" value='<%=request.getParameter("caf")%>'/>
	<input type="hidden"  id="H1" value='<%=request.getParameter("legCompName")%>'/>
	<input type="hidden"  id="H2" value='<%=request.getParameter("correspondenceAddr")%>'/>
	<input type="hidden"  id="H3" value='<%=request.getParameter("contactPersonName")%>'/>
	<input type="hidden"  id="H4" value='<%=request.getParameter("mobileNum")%>'/>
	<input type="hidden"  id="H5" value='<%=request.getParameter("landLineNo")%>'/> 
	<input type="hidden"  id="H6" value='<%=request.getParameter("emailAddr")%>'/> 
	<input type="hidden"  id="H7" value='<%=request.getParameter("siteAddr")%>'/>
		<table id="myTable" width="100%">
			<tr>
				<td colspan="2" align="center" valign="top" height="50px"
					style="font-weight: bold; font-size: 17px;"><font
						color="blue">Registration for new industrial water connection </font></td>
				<td></td>
			</tr>

<tr><td>
<div id="LoadingImage" style="display: none">
  <img src="library/img/loaderTn.gif" />
</div>
</td></tr>



			<tr>
				<td align="right" width="48%"><b>Legal Name of Company:</b><span
					style="color: red;">*</span></td>
				<td><input placeholder="Ex: ABC Company" type="text"
					id="legCompNameId" name="legCompName" /></td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td colspan="1" align="right"><b>Address for
						Correspondence:</b><span style="color: red;">*</span></td>
				<td><input placeholder="Ex: DoorNo" type="text" id="cdoorNoId"
					name="cdoorNo" /></td>
			</tr>
			<tr>
				<td align="right"></td>
				<td><input placeholder="Ex: Plot No" type="text" id="cplotNoId"
					name="cplotNo" /></td>
			</tr>
			<tr>
				<td align="right"></td>
				<td><input placeholder="Ex: Street Name" type="text"
					id="cstreetNameId" name="cstreetName" /></td>
			</tr>
			<tr>
				<td align="right"></td>
				<td><input placeholder="Ex: Location" type="text"
					id="clocationId" name="clocation" /></td>
			</tr>
			<tr>
				<td align="right"></td>
				<td><input placeholder="Ex: PinCode" type="text" onkeypress='return event.charCode >= 48 && event.charCode <= 57'
					id="cpinCodeId" name="cpinCode" maxlength="6" /></td>
			</tr>
			<tr>
				<td align="right"><b>Name of contact person:</b><span
					style="color: red;">*</span></td>
				<td><select class="classCategory" id="salutationId"
					name="salutation" style="width: 65px;">
						<option value="">---</option>
						<option value="Mr">Mr</option>
						<option value="Mrs">Mrs</option>
				</select> <input placeholder="Ex: sachin tendulkar" type="text"
					id="contactPersonNameId" name="contactPersonName"
					style="width: 246px;" /></td>
			</tr>
			<tr>
				<td align="right"><b>Mobile Number:</b><span
					style="color: red;">*</span></td>
				<td><input placeholder="Ex: 1234567891" type="text"  onkeypress='return event.charCode >= 48 && event.charCode <= 57'
					id="mobileNumId" name="mobileNum" maxlength="10" /></td>
			</tr>
			<tr>
				<td align="right"><b>Land line number:</b></td>
				<td><input placeholder="Ex: 1234567891" type="text"  onkeypress='return event.charCode >= 48 && event.charCode <= 57'
					id="landLineNoId" name="landLineNo" maxlength="15" /></td>
			</tr>
			<tr>
				<td align="right"><b>Email Id:</b><span style="color: red;">*</span></td>
				<td><input placeholder="Ex: abc@xyz.cd" type="text"
					id="emailAddrId" name="emailAddr" /></td>
			</tr>
			<tr>
				<td align="right"><b>Survey Field No:</b></td>
				<td><input placeholder="Survey Field No" type="text"
					id="surveyFieldNoId" name="surveyFieldNo" /></td>
			</tr>

			<tr>
				<td align="right"><b>Type of category:</b><span
					style="color: red;">*</span></td>
				<td style="position: relative;"><select class="classCategory"
					id="categoryTypeId" name="categoryType" style="margin-right: 10px;">
						<option value="">--Select--</option>
						 <c:forEach items="${list.categoryDtl}" var="app" varStatus="count" >
						  <option value="${app.getCategoryId()}">${app.getCategoryName()}</option>
						 </c:forEach>
						
					
				</select><a href="library/TypeOfCategory.pdf" download><img src="library/img/pdf-image.jpg" width="35px" height="40px"
					style="position: absolute; cursor: pointer;"></a></td>	
					
					
			</tr>


			<tr>
				<td align="right"><b>Site Address:</b><span style="color: red;">*</span></td>
				<td><input placeholder="Ex: DoorNo" type="text" id="doorNoId"
					name="doorNo" /></td>
			</tr>
			<tr>
				<td align="right"></td>
				<td><input placeholder="Ex: Plot No" type="text" id="plotNoId"
					name="plotNo" /></td>
			</tr>
			<tr>
				<td align="right"></td>
				<td><input placeholder="Ex: Street Name" type="text"
					id="streetNameId" name="streetName" /></td>
			</tr>
			<tr>
				<td align="right"></td>
				<td><input placeholder="Ex: Location" type="text"
					id="locationId" name="location" /></td>
			</tr>
			<tr>
				<td align="right"></td>
				<td><input placeholder="Ex: PinCode" type="text" id="pinCodeId" onkeypress='return event.charCode >= 48 && event.charCode <= 57'
					name="pinCode" maxlength="6" /></td>
			</tr>




			 <tr>
				<!-- <td align="right"><b>Is this a new connection?</b> <span
					style="color: red;">*</span></td> -->
				<td><select  class="lessWidth" id="isNewConnectionId"
					name="isNewConnection">
						 <!-- <option value="">--Select--</option>  -->
						<option value="1" selected>Yes</option>
						<!--  <option value="0">No</option>  -->
				</select></td>
			</tr> 

		<%-- 	<tr>
				<td align="right"><b>Is this an alteration to an existing water/ sewer connection?</b> <span style="color: red;">*</span></td>
				<td><select class="lessWidth"
					id="isExistConnectionForAlterationId"
					name="isExistConnectionForAlteration">
						<option value="">--Select--</option>
						<option value="1">Yes</option>
						<option value="0">No</option>
				</select></td>
			</tr>

			<tr>
				<td align="right"><b>Is this a reconnection of service for an existing water/ sewer connection?</b><span style="color: red;">*</span></td>
				<td><select class="lessWidth" id="isReconnectionForServiceId"
					name="isReconnectionForService">

						<option value="">--Select--</option>
						<option value="1">Yes</option>
						<option value="0">No</option>
				</select> <select class="moreWidth" id="reconnectionTypeId"
					name="reconnectionType">
						<option value="">--Select--</option>
						 <c:forEach items="${list.connectionTypeDtl}" var="app" varStatus="count" >
						 <option value="${app.getConnectionId()}">${app.getConnectionName()}</option>
						 </c:forEach>
			
				</select></td>
			</tr>
 --%>

			<tr>
				<td align="right"><b>Requirement of water in KLD :</b><span style="color: red;">*</span></td>
				<td><input placeholder="Ex: 12345" type="text" id="reqMldId" onkeypress='return event.charCode >= 48 && event.charCode <= 57' onkeypress="gst()" 
					 name="reqMld" style="margin-right: 5px; width: 158px;"
					maxlength="5" /> Cost Rs<input placeholder="Auto Genearated" type="text" readonly
					id="ipf" name="ipf"
					style="margin-left: 5px; width: 158px;" maxlength="3" /></td>
			</tr>
			<tr>
				<td align="right"><b>GST Amount<font style="color: rgb(128,128,128);font-size: 12px;">(GST @ 18 % )</font>:</b><span style="color: red;">*</span></td>
				<td><input placeholder="GST @ 18 % " type="text" id="gstAmount" readonly
					name="gstAmount" /></td>
			</tr>
			<tr>
				<td align="right"><b>Total Amount:</b><span style="color: red;">*</span></td>
				<td><input placeholder="Ex: Cost * GST 18 %" type="text" id="totalAmount" readonly
					name="totalAmount" /></td>
			</tr>
			<%--   <tr>
				<td align="right"><b>CMWSSB Area Number/ Chennai
						Corporation Zone Number:</b></td>
				<td style="position: relative;"><select class="classCategory"
					id="cmwssbZoneNumId" name="cmwssbZoneNum"
					style="margin-right: 10px;">
						<option value="">--Select--</option>


						<c:forEach items="${list.zoneDivisionDtl}" var="app"
							varStatus="status">

							<c:set var="floorAlreadyExists" value="${false}" />
							<c:if test="${(status.index ) > 0}">
								<c:forEach var="previousRoom" items="${list.zoneDivisionDtl}"
									begin="0" end="${status.index - 1}" varStatus="inner">
									<c:if
										test="${app.getZoneId().equals(previousRoom.getZoneId())}">
										<c:set var="floorAlreadyExists" value="${true}" />
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${not floorAlreadyExists}">
								<option value="${app.getZoneId()}">${app.getZoneName()}</option>
							</c:if>

                        </c:forEach>
				</select></td>
			</tr>  --%>
		 	<!-- <tr>
				<td align="right"><b>Division No./ Depot No:</b></td>
				<td style="position: relative;"><select class="lessWidth"
					id="divId" name="divId" style="margin-right: 10px;">
						<option value="">--Select--</option>

				</select><a href="library/ZoneDivisionRelationship.pdf" download
					title="Click here to download Division Details"
					style="font-weight: bold;">Help</a></td>
			</tr> --> 
			<!--  <tr>
				<td align="right"><b>Bill No. provided by Corporation/ Local Authority:</b></td>
				<td><input placeholder="Ex: 12345" type="hidden" id="billNumId2"
					name="billNo2" value="0123456789" style="margin-right: 5px; width: 158px;"
					maxlength="5" /><input type="hidden" 
					id="billNumId1" name="billNo1" value="123"
					style="margin-left: 5px; width: 158px;" maxlength="3" /></td>
			</tr>   -->
			<!-- <tr>
				<td align="right"><b>Annual Assessment value of property
						fixed by Chennai Corporation/ Local authority:</b></td>
				<td><input type="hidden" placeholder="Ex: 1234567890"  value="0123456789"
					id="annAssmtValId" name="annAssmtVal" maxlength="10" /></td>
			</tr> -->



			 <tr>
				<td align="right"><b>Has the internal plumbing work been completed?:</b></td>
				<td><select class="lessWidth" id="intrPlumStatusId"
					name="intrPlumStatus">
						<option value=""><b>--Select--</b></option>
						<option value="1">Yes</option>
						<option value="0">No</option>
				</select></td>
			</tr> 

			<!-- <tr>
				<td align="right"><b>Are water/ sewer lines available near the property?:</b></td>
				<td><select class="lessWidth" id="watSevPropId"
					name="watSevProp">
						<option value=""><b>--Select--</b></option>
						<option value="1">Yes</option>
						<option value="0">No</option>
				</select></td>
			</tr> -->
			<tr>
				<td align="right"><b>Work Type:</b><span style="color: red;">*</span></td>
				<td><select class="classCategory" id="workTypeId"
					name="workType">
						<option value="">--Select--</option>
						<option value="0">Treated (Chloronated)</option>
						<!-- <option value="1">Raw Water</option> -->
						<option value="1">Secondary treated water</option>
				</select><img src="library/img/pdf-image.jpg" width="35px" height="40px"
					style="position: absolute; cursor: pointer;"></td>
			</tr>
			
	
			<tr>
				<td align="right"><b>Site plan <font style="color: rgb(128,128,128);font-size: 12px;">(Scale not less than 1:400,.dwg file, 5 MB)</font>:</b></td>
				<td><input type="file" class="uploadClass" name="file" accept=".dwg"/></td>
			</tr>
			<tr>
				<td align="right"><b>Sump drawing specifying height of sump<font style="color: rgb(128,128,128);font-size: 12px;">(.dwg file, 5 MB)</font>:</b></td>
				<td><input type="file" class="uploadClass" name="file" accept=".dwg"/></td>
			</tr>
			<tr>
				<td align="right"><b> Ownership proof <font style="color: rgb(128,128,128);font-size: 12px;">(sale deed/ lease deed/ rental deed)</font> self-attested by the applicant <font style="color: rgb(128,128,128);font-size: 12px;">( PDF file, 5 MB)</font>:</b></td>
				<td><input type="file" class="uploadClass" name="file" accept=".pdf"/></td> </td>
			</tr>
		
		
			<tr>
				<td style="font-weight: bold; color: blue;" align="center"
					colspan="2" height="50px"><font style="color: rgb(128,128,128)"><h5>( Note: Payment  
					option is available in Next Page)</h5></font></td>
			</tr>
			<tr>
				<td align="center" valign="middle" colspan="2" height="70px;"><input
					type="button" id="registrationbtnId" name="industrialistSubmitBtn"
					value="Submit" /></td>
				<td></td>

			</tr>
		</table>
	</form>



	<form style="display: hidden" action="uploadDocument.jsp"
		method="GET" id="hiddenForm">
		<input type="hidden" id="applicationRef" name="applicationRef"
			value="" /> <input type="hidden" id="siteAddr" name="siteAddr"
			value="" /> <input type="hidden" id="companyName" name="companyName"
			value="" /> <input type="hidden" id="mld" name="mld" value="" /> <input
			type="hidden" id="zone" name="zone" value="" /> <input type="hidden"
			id="division" name="division" value="" /> <input type="hidden"
			id="bill1" name="bill1" value="" /> <input type="hidden" id="bill2"
			name="bill2" value="" /> <input type="hidden" id="workTyp"
			name="workTyp" value="" /> <input type="hidden" id="mobNo"
			name="mobNo" value="" />

	</form>


</body>
</html>
