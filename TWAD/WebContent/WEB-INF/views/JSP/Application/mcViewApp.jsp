-<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <link rel="stylesheet" href="library/assets/plugins/bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" href="library/assets/css/main.css" />
    <link rel="stylesheet" href="library/assets/css/theme.css" />
    <link rel="stylesheet" href="library/assets/css/MoneAdmin.css" />
    <link rel="stylesheet" href="library/assets/plugins/Font-Awesome/css/font-awesome.css" />
    <!--END GLOBAL STYLES -->

    <!-- PAGE LEVEL STYLES -->
    <link href="library/assets/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
<style>

/* Full-width input fields */
input[type=text],input[type=password] {
	width: 20%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

/* Set a style for all buttons */
button {
	background-color: #0BB09F;
	/* color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 15%; */
	width: auto;
	padding: 10px 18px;
}

button:hover {
	opacity: 0.8;
}

/* Extra styles for the cancel button */
.cancelbtn {
	width: auto;
	padding: 10px 18px;
	background-color: #f44336;
	left: 50%;
	height: 50%;
	top: 50%;
	right: 50%;
}
.uploadbtn {
	width: auto;
	padding: 10px 18px;
	background-color: #DAB304;
	left: 50%;
	height: 50%;
	top: 50%;
	right: 50%;
}

/* Center the image and position the close button */
.imgcontainer {
	text-align: center;
	margin: 24px 0 12px 0;
	position: relative;
}

.imgcontainer1 {
	text-align: center;
	margin: 24px 0 12px 0;
	position: relative;
}

img.avatar {
	width: 40%;
	border-radius: 50%;
}

.container {
	padding: 16px;
}

span.psw {
	float: right;
	padding-top: 16px;
}

/* The Modal (background) */
.modal {
	display: none;
	position: fixed;
	z-index: 1;
	left: 50%;
	top: 50%;
	height: 38%;
	overflow: auto;
	background-color: white;
	width: 469px !important;
}

.modal td{
padding-top:15px;
padding-left:10px;

}
select,input[type="text"]{
width:150px;
}

/* Modal Content/Box */
.modal-content {
	background-color: #fefefe;
	margin: 5% auto 15% auto;
	/* 5% from the top, 15% from the bottom and centered */
	border: 0px solid #888;
	width: 80%; /* Could be more or less, depending on screen size */
}



/* The Close Button (x) */
.popupclose {
	position: absolute;
	right: 10px;
	top: 3px !important;
	color: red;
	font-size: 35px;
	font-weight: bold;
}

.popupclose:hover,.popupclose:focus {
	color: red;
	cursor: pointer;
}

/* Add Zoom Animation */
.animate {
	-webkit-animation: animatezoom 0.6s;
	animation: animatezoom 0.6s
}

@
-webkit-keyframes animatezoom {
	from {-webkit-transform: scale(0)
}

to {
	-webkit-transform: scale(1)
}

}
@
keyframes animatezoom {
	from {transform: scale(0)
}

to {
	transform: scale(1)
}

}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
	span.psw {
		display: block;
		float: none;
	}
	.cancelbtn {
		width: 30%;
	}
}

#menu3 {
	background: #E05400;
	background: -webkit-linear-gradient(top, #FFFFFF 3%, #E05400 30%);
}

input[type="search"] {
	height: 30px !important;
}

input[type="text"] {
	height: 25px !important;
}

#successMessage {
	margin-left: 450px;
	z-index: 20000;
	margin-top: 20px;
	position: absolute;
	color: green;
}
.row-fluid,.span12{
width:100% !important;
}
/* div[role="dialog"]{
border-radius:15px; 
}
.loginDialog{
	display:none;
	width:400px;
	height:200px;
	padding:0px !important;
	overflow: hidden !important;
} */

 div[role="dialog"]{
/* border-radius:15px;  */
left:410px !important;
}
</style>
<style>

#menu3{
	background: #E05400;
	background: -webkit-linear-gradient(top, #FFFFFF 3%, #E05400 30%);
}
input[type="search"]{
height:30px !important;
}
input[type="text"]{
height:25px !important;
}

#successMessage{
	margin-left: 450px;
    z-index: 20000;
    margin-top: 20px;
    position: absolute;
    color: green;
    }
    
    
    
    
</style>
<script src='JS/complaints/searchComplaints.js'></script>


<script type="text/javascript">

$(function(){
	$('.paymentClass').click(function(){
		if(confirm("Are you sure want to Submit ?")){
			 $.ajax({
				type:"POST",
				url:"mcApprovePayment.do",
				data:{'appRef':id},
				success:function(response){
					alert(response);
					window.location.reload();
					
				}
			});
			}
		
	});
	
	
});

	function EERejected(id) {
		//alert(id);
		$('#appIdRef').val(id);
		
		$('#id01').css({
			'display' : 'block'
		});
		
		$('#appId').val(id);
	
		
	}
	
	function gst(){
		//alert("2222");
		var estAmt = document.getElementById('finalfee').value;
		var est=parseInt(estAmt);
		
		var gst=parseInt(est*18)/100;
		var total=parseInt(est+gst);
		$("#gst").val(gst);
		$("#totalAmount").val(total);
	}
	
	function eeReject(appRef){
		var estimationCost = document.getElementById('txtestpayment').value;
		if (estimationCost == null || estimationCost == '') {
			alert("Please enter the  remarks !");
			return false;
		}
		
		
		
			if(confirm("Are you sure want to Reject?")){
		 $.ajax({
			type : "POST",
			url : "eeRejcted.do",
			
			data : {
				'appRef' : $('#appIdRef').val(),
				'remarks':estimationCost
			},
			success : function(response) {
			//	$('#successMessage').text(response);
				alert(response);
				window.location.reload();

			}
		});
		} 
	}
	


	var modal = document.getElementById('id01');

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = "none";
		}
	}
	var modalr = document.getElementById('id02');

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
		if (event.target == modalr) {
			modalr.style.display = "none";
		}
	}
</script>

<table class='table-bordered table table-striped display'
	style='width: 100%; font-size: 28px;'>

	<tr>
		<td colspan='8'
			style='text-align: center; background-color: #FCFCF4; font-size: 17px; height: 10px; color: #800000; font-weight: bold;'>
			Pending Applications</td>
	</tr>
	</table>



                       <!--PAGE CONTENT -->
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
                                            <th style="color:black !important"><b>Category Type</b></th>
                                           
                                          
                                            <th style="color:black !important"><b>Correspondence Address</b></th>
                                          
                                          
                                              <th style="color:black !important"><b>Payment Amount</b></th>
                                               <th style="color:black !important"><b>GST Percent</b></th>
                                                 <th style="color:black !important"><b>GST Amount</b></th>
                                                   <th style="color:black !important"><b>Total Amount</b></th>
                                              
                                             
                                            <th style="color:black !important"></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    
                                    
          							  <c:forEach items="${list.appBean}" var="app" >
          
          									 
          									 
          								<tr class="odd gradeX">
          								
          							<td>	<a href="MCViewForm.do?appId=${app.getAppId()}" style="color: rgb(128,128,128)">${app.getAppId()}</a></td>
                                            <td>${app.getLegCompName()}</td>
                                        
                                             <td>${app.getCategoryType()}</td>
                                           
                                           <td class="center">${app.getCdoorNo()} ${app.getCplotNo()} ${app.getCstreetName()} ${app.getClocation()} ${app.getCpinCode()}</td>
                                         
                                         <td class="center">${app.getPaymentAmount()}</td>
                                         <td class="center">${app.getGstPercent()}</td>
                                         <td class="center">${app.getGstAmount()}</td>
                                         <td class="center">${app.getTotalAmount()}</td>
                                         
                                             
											<td><button onclick="getPaymentDetails(this.id)"
													class="paymentClass" id="${app.getAppId()}"
													style="width: auto;">Accepted</button>
													
													<button onclick="EERejected(this.id)"
													class="cancelbtn" id="${app.getAppId()}"
													style="width: auto;">Rejected</button></td>
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
       
     
												
												
												
												
												
												
												
												
											

			<div class="imgcontainer">

<input type="hidden" id="appIdRef" value=""/>

													<table id="id01" class="modal" style="display: none;">


														<tr height="30px"
															 color: white; font-weight: bold;">
															<td>
																 <span
																onclick="document.getElementById('id01').style.display='none'"
																class="popupclose" title="Close Modal">&times;</span>
															</td>
														</tr>


														<tr>
															<td><label><b>Remarks </b></label></td>
															<td><input type="textarea" width="100%" height="30px" name="txtestpayment"
																id="txtestpayment" placeholder="EX: Comments">
																
																</td>
														</tr>
														
														
														<tr>


															<td align="center" colspan="2">
																
																<button onclick="eeReject()"
																	class="paymentClassRej" id="${app.getAppId()}"
																	style="width: auto;">Submit</button>

															</td>
														</tr>
													</table>

												</div>
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
				<c:forEach items="${list.results}" var="results">
					<tr>
						<td>
							<form method='post' id="${results.getComplaintID()}" action="editComplaint.do">
								<input type='hidden' name="complaintID" value="${results.getComplaintID()}" /> <a
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
         $(document).ready(function () {
             $('#dataTables-example').dataTable();
         });
    </script>
     <!-- END PAGE LEVEL SCRIPTS -->