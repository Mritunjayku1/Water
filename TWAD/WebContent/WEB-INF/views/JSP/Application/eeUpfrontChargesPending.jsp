<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

  
    <!--END GLOBAL STYLES -->

    <!-- PAGE LEVEL STYLES -->
    <link href="library/assets/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
    <link rel="stylesheet" href="library/assets/plugins/bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" href="library/assets/css/main.css" />
    <link rel="stylesheet" href="library/assets/css/theme.css" />
    <link rel="stylesheet" href="library/assets/css/MoneAdmin.css" />
    <link rel="stylesheet" href="library/assets/plugins/Font-Awesome/css/font-awesome.css" />
	    <link rel="stylesheet" href="library/assets/plugins/validationengine/css/validationEngine.jquery.css" />

<style>
.bg_heading {
	text-align: left;
    font-size: 20px;
    color: white;
    margin-top: -32px;
    margin-left: 195px;
    font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}
/* #menu3{
	background: #E05400;
	background: -webkit-linear-gradient(top, #FFFFFF 3%, #E05400 30%);
} */
input[type="search"]{
height:30px !important;
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
		var companyPaymentDtlID="";
		$('input[name="approveBtn"]').click(function(){
			var appIdArray = $(this).attr("id").split("_");
			appId =  appIdArray[1];
			companyPaymentDtlID=appIdArray[2];

			var ddNo=$('#ddNo_'+appId).text();
			if(ddNo == null || ddNo=='')
			{
			alert("Applicant not submitted DD !")
			return false;
			}
			$('#appId').val(appId);
			$(".ui-dialog-content").dialog("close");
			$( "#addDialog" ).dialog({ 'width':'600px','modal':'true'});
			$('#paymentTypeId option[value="1"]').attr('disabled',true);
			$('#paymentTypeId option[value="2"]').attr('disabled',true);
			$('#paymentTypeId option[value="3"]').attr('selected',true);
			$('#paymentAmountId').focus();
			
			$.ajax({
				type : "GET",
				url : "getUpfrontCharges.do",
				data : {"appId":appId},
				async : false,
	            success : function(response) {
	            	response = JSON.parse(response);
					$('#gstPercentId').val(response.gstPercent);
					
				}
				});
			
		}); 
		
		$('.closeBtn,.imgClose').click(function(){
			$(".ui-dialog-content").dialog("close");
			
		}); 
		
		
		$('#paymentAmountId').blur(function(){
			var paymentAmount = $(this).val();
			var gstAmount = $('#gstAmountId').val();
			var gstPercent = $('#gstPercentId').val();
			if(paymentAmount!="" && gstAmount==0 && gstPercent != "" && gstPercent != 0){
				var gstAmount = paymentAmount*gstPercent/100;
				$('#gstAmountId').val(gstAmount);
			}
			else{
				$('#gstAmountId').val(0);
			}
			if(paymentAmount!="" && !Number.isNaN(paymentAmount) && !Number.isNaN(gstAmount)){
				$('#totalAmountId').val(parseInt(paymentAmount)+parseInt(gstAmount));
			}
			else{
				$('#totalAmountId').val(0);
			}
		});

		$('#gstPercentId').blur(function(){
			var paymentAmount = $('#paymentAmountId').val();
			var gstPercent = $('#gstPercentId').val();
			
			if(paymentAmount!="" && !Number.isNaN(paymentAmount) && gstPercent != "" && !Number.isNaN(gstPercent)){
				var gstAmount = paymentAmount*gstPercent/100;
				$('#gstAmountId').val(gstAmount);
				$('#totalAmountId').val(parseInt(paymentAmount)+parseInt(gstAmount));
			}
			else{
				$('#gstAmountId').val(0);
				$('#totalAmountId').val(0);
			}
		});


		$('#paymentSaveBtnId').click(function(){
			if(validateAddForm()){
				
				$("#paymentSaveBtnId").prop("disabled", true);
	   			// localStorage.setItem('isFileUploaded', true);
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
	   	               // $("#btnSubmit").prop("disabled", false);

	   	            },
	   	        });
	   		 
	   	    
			$.ajax({
				type:"POST",
				url:"eeAddFullPayment.do",
				async:false,
				data:{
					
					'appId':appId,
					'paymentType':$('#paymentTypeId').val(),
					'paymentAmount':$('#paymentAmountId').val(),
					'gstPercent':$('#gstPercentId').val(),
					'gstAmount':$('#gstAmountId').val(),
					'totalAmount':$('#totalAmountId').val(),
					'paymentDesc':$('#paymentDescId').val(),
					'companyPaymentDtlID':companyPaymentDtlID,
					'inspectedDate':$('.inspectionDate').val()
					
				},
				success:function(response){
					alert(response);
					
					window.location.reload();
				}
			});
			}
		});

		
		
		
		
		/* $('input[name="approveBtn"]')
				.click(
						function() {
							var appRef = $(this).attr('id');
							var inspectionDate = $(this).closest('tr').find(
									'td:nth-child(7)').find(
									'input[type="text"]').val();
							if (inspectionDate == null || inspectionDate == '') {
								alert("Please select  inspection Date !")
								return false;
							}
							if (confirm("Are you sure want to Submit ?")) {
								$.ajax({
									type : "POST",
									url : "eeSendInspectionDate.do",
									data : {
										'appRef' : appRef,
										'inspectionDate' : inspectionDate
									},
									success : function(response) {
										//$('#successMessage').text(response);
										alert(response);
										window.location.reload();

									}
								});
							}

						}); */
		$(".inspectionDate").datepicker({
			dateFormat : 'dd-mm-yy',
			changeMonth : true,
			changeYear : true,
			//maxDate : new Date(),
			showOn : "button",
			minDate : 0,
			maxDate : "+2Y",
			buttonImageOnly : true,
		//	buttonText : "Select date",
			buttonImage : "library/img/datepicker.png",
			showAnim : "slideDown",
		});

	});
</script>

<div id="addDialog" style="display: none;">
<form id="uploadFormId" method="POST" enctype="multipart/form-data">
<input type="hidden" name="appId" id="appId" value=''/>
<img class="imgClose" src="library/img/Close_SMS.png"
			style="width: 40px; border-width: 0px; float: right; margin-top: -11px; margin-right: -5px; cursor: pointer;">
		<h2 class="bg_heading">Add Payment Details</h2>
		<table id="paymentTable" style="margin-left: 30px;margin-top: 20px;">
		<tr><td><span><b>Payment Type:</b></span><span style="color: red;">*</span></td><td>
				<select  id="paymentTypeId">
				 <option value="">--Select Payment Type--</option>
                                    <c:forEach items="${list.paymentTypeDtl}" var="app" varStatus="count">
                                        <option value="${app.getPaymentId()}">${app.getPaymentType()}</option>
                                    </c:forEach>
                </select></td></tr>
				
<tr><td><span><b>Payment Amount:</b></span><span style="color: red;">*</span></td><td>

				<input placeholder="Ex: 100" type="text" id="paymentAmountId" name="paymentAmount" style=""/></td></tr>

<tr><td><span><b>GST %:</b></span><span style="color: red;">*</span></td><td>
				<input placeholder="Ex: 10" type="text" id="gstPercentId" name="gstPercent" style="" value="" readonly="readonly" style="background-color: lightgrey;"/></td></tr>

<tr><td><span><b>GST Amount:</b></span></td><td>
				<input placeholder="Ex: 12" type="text" id="gstAmountId" name="gstAmount" readonly="readonly" value="0" style="background-color: lightgrey;"/></td></tr>

<tr><td><span><b>Total Amount:</b></span></td><td>
				<input placeholder="Ex: 123" type="text" id="totalAmountId" name="totalAmount" readonly="readonly" value="0" style="background-color: lightgrey;"/></td></tr>
<tr><td><span><b>Inspected Date:</b></span></td><td>
				<input placeholder="Ex: DD-MM-YYYY" type="text" class="inspectionDate" readonly="readonly"  style="background-color: lightgrey;"/></td></tr>


<tr><td colspan="2"><hr style="margin: 0px;width: 95%;"/></td></tr>

<tr><td><span><b>DPR report:</b></span></td><td><input type="file" name="file" accept=".doc,.docx,.dwg,.pdf,.txt,.xlsx,.xls"></td></tr>

<tr><td colspan="2"><hr  style="margin-top: 5px;width: 95%;"/></td></tr>

<tr><td><span><b>Comments:</b></span></td><td>
				<input placeholder="Ex: ABC" type="text" id="paymentDescId" name="paymentDesc" style=""/></td></tr>
			<tr><td colspan="2" align="center" height="70px">	<input type="button" value="Save" id="paymentSaveBtnId"/> <input type="button" value="Close"  class="closeBtn"/></td></tr>
		
		

		</table>
		
		</form>		
		</div>
		

<table class='table-bordered table table-striped display'
	style='width: 100%; font-size: 28px;'>
<tr>
		<td colspan='8'
			style='text-align: center; background-color: #FCFCF4; font-size: 17px; height: 10px; color: #800000; font-weight: bold;'>
			Pending Upfront Charges</td>
	</tr>
</table>

        <div id="content" style="margin-left: 0px !important">
<div id="successMessage"></div>
            <div class="inner">
                <div class="row">
                    
                </div>

               


                <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                           <!--  <th style="color:black !important"></th> -->
                                            <th style="color:black !important"><b>App Ref#</b></th>
                                            <th style="color:black !important"><b> Name of Company</b></th>
                                             <th style="color:black !important"><b> Name of Person</b></th>
                                            <th style="color:black !important"><b>Total Payment Amount</b></th>
                                            <th style="color:black !important"><b>DD NO</b></th>
                                             <th style="color:black !important"><b>DD Date</b></th>
                                            <th style="color:black !important"><b>DD Bank Name</b></th>
                                            <th style="color:black !important"><b>Payment Status</b></th>
                                             <th style="color:black !important"><b>Registered Date</b></th>
                                            
                                              <th></th>
                                        
                                        </tr>
                                    </thead>
                                    <tbody>
                                    
                                     <c:forEach items="${list.appBean}" var="app" >
          
          							
          									 
          								<tr class="odd gradeX">
          							<td > <a href="EEViewForm.do?appId=${app.getAppId()}" style="color: rgb(128,128,128)">${app.getAppId()}</a></td>
                                            <td>${app.getLegCompName()}</td>
                                            
                                             <td>${app.getContactPersonName()}</td>
                                               <td>${app.getPaymentAmount()}</td>
                                                <td id="ddNo_${app.getAppId()}">${app.getDdNo()}</td>
                                                 <td>${app.getDdDate()}</td>
                                                  <td>${app.getDdBankName()}</td>
                                                   <td>${app.getPaymentStatusDisplay()}</td>
                                           
                                             <td class="center">${app.getCreateTs()}</td>
                                             <%--  <td class="center"><textarea id="managementComments_${app.getAppId()}" name="managementComments" style="width:100%;height:100%;"></textarea></td> --%>
                                             <td class="center">
											<input type="button"
												name="approveBtn" id="approved_${app.getAppId()}_${app.getCompanyPaymentDtlID()}"
												value="Add Payment..." /></td>
                                           
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
					<th style='min-width:150px;'><spring:message code="label.recievedDateTime" /></th>
					<th><spring:message code="label.content" /></th>
					<th><spring:message code="label.status" /></th>
				</tr>
			</thead>
			<tbody>
				
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
    <script src="library/assets/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="library/assets/plugins/dataTables/dataTables.bootstrap.js"></script>
     <script>
         $(document).ready(function () {
             $('#dataTables-example').dataTable();
         });
    </script>
     <!-- END PAGE LEVEL SCRIPTS -->