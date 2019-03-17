<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="library/assets/plugins/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="library/assets/css/main.css" />
<link rel="stylesheet" href="library/assets/css/theme.css" />
<link rel="stylesheet" href="library/assets/css/MoneAdmin.css" />
<link rel="stylesheet"
	href="library/assets/plugins/Font-Awesome/css/font-awesome.css" />

<!--END GLOBAL STYLES -->

<!-- PAGE LEVEL STYLES -->
<link href="library/assets/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet" />

<style>
.bg_heading {
	text-align: left;
    font-size: 20px;
    color: white;
    margin-top: -32px;
    margin-left: 195px;
    font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}
#menu3 {
	background: #E05400;
	background: -webkit-linear-gradient(top, #FFFFFF 3%, #E05400 30%);
}

input[type="search"] {
	height: 30px !important;
}

 input[type="file"] {
    width: 330px;
    height: 35px;
    padding-top:9px;
    }

input[type="text"] {
	width: 300px;
    height: 35px;
    padding: 5px;
}

#paymentTable input[type="button"] {
	width: 100px;
    height: 30px;
}

select {
	width: 300px;
    height: 35px;
    padding: 5px;
}

#successMessage {
	margin-left: 450px;
	z-index: 20000;
	margin-top: 20px;
	position: absolute;
	color: green;
}

.error {
	color: red;
}
</style>
<script src='JS/complaints/searchComplaints.js'></script>

<script type="text/javascript">

function validateAddForm() {
	return true;
	  	var numberReg = /^[0-9]*$/;

	  	var paymentTypeId = $("#paymentTypeId");
	  	var paymentAmount = $("#paymentAmountId");
	  	var gstPercent = $("#gstPercentId");
	  	
	  	

	  	var inputVal = new Array(paymentTypeId,paymentAmount,gstPercent);

	  	$('.error').hide();
	  	flag = true;
	  	for (i = 0; i < inputVal.length; i++) {
	  		if (inputVal[i].val() == "") {
	  			inputVal[i]
	  					.after('<br/><span class="error"> This field is required. </span>');
	  			inputVal[i].focus();
	  			flag = false;
	  		} 
	  		
	  		else if (inputVal[i].attr('id') == 'paymentAmountId' &&  !numberReg.test(inputVal[i].val())) {
	  			inputVal[i].after('<br/><span class="error"> Please enter Amount in Numeric only. </span>');
	  			inputVal[i].focus();
	  			flag = false;
	  		}
	  		else if (inputVal[i].attr('id') == 'gstPercentId' &&  !numberReg.test(inputVal[i].val())) {
	  			inputVal[i].after('<br/><span class="error"> Please enter GST Percent in Numeric only. </span>');
	  			inputVal[i].focus();
	  			flag = false;
	  		}
	  		
	  		
	  	}
	  	return flag;
	  }


	$(function() {
		var appId="";
		$('input[name="approveBtn"]').click(function(){
			appId = $(this).attr("id");
			$('#appId').val(appId);
			$(".ui-dialog-content").dialog("close");
			$( "#addDialog" ).dialog({ 'width':'600px','modal':'true'});
		}); 
		
		$('.closeBtn,.imgClose').click(function(){
			$(".ui-dialog-content").dialog("close");
			
		}); 


		$('#paymentSaveBtnId').click(function(){
			if(validateAddForm()){
				
				$("#paymentSaveBtnId").prop("disabled", true);
	   	        var form = $('#uploadFormId')[0];
	   	        var data = new FormData(form);

	   	        $.ajax({
	   	            type: "POST",
	   	            enctype: 'multipart/form-data',
	   	            async:false,
	   	            url: "uploadMultipleFileByAdmin.do",
	   	            data: data,
	   	            processData: false,
	   	            contentType: false,
	   	            cache: false,
	   	            timeout: 600000,
	   	            success: function (response) {
	   	         	alert(response);
	   	         	window.location.reload();

	   	            },
	   	        });
			}
		});

		
	});
</script>

<div id="addDialog" style="display: none;">
<form id="uploadFormId" method="POST" enctype="multipart/form-data">
<input type="hidden" name="appId" id="appId" value=''/>
<img class="imgClose" src="library/img/Close_SMS.png"
			style="width: 40px; border-width: 0px; float: right; margin-top: -11px; margin-right: -5px; cursor: pointer;">
		<h2 class="bg_heading">Upload Document</h2>
		<table id="paymentTable" style="margin-left: 30px;margin-top: 20px;">
		
<tr><td><span><b>Upload Document:</b></span></td><td><input type="file" name="file" accept=".doc,.docx,.dwg,.pdf,.txt,.xlsx,.xls"></td></tr>

			<tr><td colspan="2" align="center" height="70px">	<input type="button" value="Save" id="paymentSaveBtnId"/> <input type="button" value="Close"  class="closeBtn"/></td></tr>
		
		

		</table>
		
		</form>		
		</div>
		



<table class='table-bordered table table-striped display'
	style='width: 100%; font-size: 28px;'>

	<tr>
		<td colspan='8'
			style='text-align: center; background-color: #FCFCF4; font-size: 17px; height: 10px; color: #800000; font-weight: bold;'>
			Full Payment Completed</td>
	</tr>
</table>

<!--PAGE CONTENT -->
<div id="content" style="margin-left: 0px !important">
	<div id="successMessage"></div>
	<div class="inner">
		<div class="row"></div>




		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">

					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">

								<thead>
									<tr>
										<!--  <th style="color:black !important"></th> -->
										<th style="color: black !important"><b>App Ref#</b></th>
										<th style="color: black !important"><b> Name of
												Company</b></th>
										<th style="color: black !important"><b>Category Type</b></th>

										<th style="color: black !important"><b>Correspondence
												Address</b></th>
										
										
										<th style="color: black !important"></th>
									</tr>
								</thead>
								<tbody>

									<c:forEach items="${list.appBean}" var="app">



										<tr class="odd gradeX">
											
											<td><a href="EEViewForm.do?appId=${app.getAppId()}" style="color: rgb(128,128,128)">${app.getAppId()}</a></td>
											<td>${app.getLegCompName()}</td>
											<td>${app.getCategoryType()}</td>
                                         
											<td class="center">${app.getCdoorNo()}
												${app.getCplotNo()} ${app.getCstreetName()}
												${app.getClocation()} ${app.getCpinCode()}</td>
										<td class="center"></td> 
											
											<td class="center"><input type="button"
												name="approveBtn" id="${app.getAppId()}"
												value="Send SMS/ Email" /></td>
										</tr>

									</c:forEach>
								</tbody>
							</table>
						</div>

					</div>
				</div>
			</div>
		</div>


	</div>




</div>
<!--END PAGE CONTENT -->
<c:choose>
	<c:when test="${!empty list.results}">

		<table
			class='jDataTable table-bordered table tabel-striped bootstrap-datatable display'
			id='tblSearchcomplaintTable'>
			<thead>
				<tr>
					<th><spring:message code="label.complaintNumber" /></th>
					<th><spring:message code="label.channel" /></th>
					<th style='min-width: 150px;'><spring:message
							code="label.recievedDateTime" /></th>
					<th><spring:message code="label.content" /></th>
					<th><spring:message code="label.status" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list.results}" var="results">
					<tr>
						<td>
							<form method='post' id="${results.getComplaintID()}"
								action="editComplaint.do">
								<input type='hidden' name="complaintID"
									value="${results.getComplaintID()}" /> <a
									href="javascript:fnSubmitForm('<c:out value="${results.getComplaintID()}"/>');">${results.getComplaintNumber()}</a>
							</form>
						</td>

						<td>${results.getComplaintSourceName()}</td>
						<td>${results.getCreatedDate()}</td>
						<td>${results.getComplaintContent()}</td>
						<td>${results.getComplaintStatusName()}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:when>
	<c:otherwise>

		<div id='alertBox' style='display: none;'>
			<h3>No Records found</h3>
		</div>
		<%
			if (request.getParameter("isSumbitted") != null
							&& "Y".equals(request.getParameter("isSumbitted"))) {
		%>
		<script>
			$('#alertBox').show();
			$("#alertBox").dialog({
				resizable : false,
				height : 115,
				width : "30%",
				modal : true,
				position : 'center',
				title : "Information",
				closeOnEscape : false,
				dialogClass : "noclose",
				buttons : {
					"Ok" : function() {
						$(this).dialog("close");
					}
				}
			});
		</script>
		<%
			}
		%>
	</c:otherwise>
</c:choose>


<!-- PAGE LEVEL SCRIPTS -->
<script src="assets/plugins/dataTables/jquery.dataTables.js"></script>
<script src="assets/plugins/dataTables/dataTables.bootstrap.js"></script>
<script>
	$(document).ready(function() {
		$('#dataTables-example').dataTable();
	});
</script>
<!-- END PAGE LEVEL SCRIPTS -->