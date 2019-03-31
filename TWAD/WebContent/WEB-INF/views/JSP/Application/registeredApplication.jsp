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
	$('input[name="approvedBtn"]').click(function(){
		var approvedAppRef = $(this).attr('id');
		var appId = approvedAppRef.split("_");
		var managementComments=$('#managementComments_'+appId[1]).val();
		var officeId= $("#officeSearch_"+appId[1]).attr('item_id');
		if(officeId == null || officeId=='')
		{
		alert("Please select Office Name !")
		return false;
		}
		if(managementComments == null || managementComments=='')
		{
		alert("Please enter the Comments !")
		return false;
		}
		if(confirm("Are you sure want to Approve ? ")){
		$.ajax({
			type:"POST",
			url:"registeredApplicationApproved.do",
			data:{'appId':appId[1],'managementComments':managementComments,'officeName':officeId},
			success:function(response){
				alert(response);
				window.location.reload();
				
			}
		});
		}
		
	});
	$('input[name="rejectedBtn"]').click(function(){
		var approvedAppRef = $(this).attr('id');
		var appId = approvedAppRef.split("_");
		var managementComments=$('#managementComments_'+appId[1]).val();
		if(managementComments == null || managementComments=='')
		{
		alert("Please enter the Comments !")
		return false;
		}
		if(confirm("Are you sure want to Reject ? ")){
		$.ajax({
			type:"POST",
			url:"registeredApplicationRejected.do",
			data:{'appId':appId[1],'managementComments':managementComments},
			success:function(response){
				alert(response);
				window.location.reload();
				
			}
		});
		}
		
	});
	   var acList = "";
	   
	   var searchId = "";
	   $('.officeSearchClass').focus(function(){
		   var searchIdArr = $(this).attr("id").split("_");
	    	searchId = searchIdArr[1];
	    	
	   });
	  
	                     
	                      $('.officeSearchClass').autocomplete({
	                    	    source: function (request, response) {
	                    	    	  $.ajax({
	                  					type: "GET",
	                  					async:false,
	                  					url: "library/Office.json",
	                  					success: function (
	                  						response) {
	                  						acList=response;
	                  					}
	                  	    	  
	                  	    	  });
	                    	        var matches = $.map(acList, function (acItem) {
	                    	        	var searchTerm = request.term.replace(/%/g,".*");
	                    	        	var patt = new RegExp("^"+searchTerm.toLowerCase()+".*$","ig");
	                    	        	  if(patt.test(acItem.value.toLowerCase())){
	                    	                return acItem;
	                    	            }
	                    	        });
	                    	        response(matches);
	                    	    },
	                      
	                      select: function (event, ui) {
	                          $("#officeSearch_"+searchId).attr('item_id',ui.item.id); // save selected id to hidden input
	                      }
	                      
	                    	});
	                      
	                      
});
</script>
<table class='table-bordered table table-striped display'
	style='width: 100%; font-size: 28px;'>
<tr>
		<td colspan='8'
			style='text-align: center; background-color: #FCFCF4; font-size: 17px; height: 10px; color: #800000; font-weight: bold;'>
			Pending Applications</td>
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
                                              <th style="color:black !important"><b> District</b></th>
                                                <th style="color:black !important"><b>Taluk</b></th>
                                                  <th style="color:black !important"><b> Village</b></th>
                                          
                                             <th style="color:black !important"><b>Registered Date</b></th>
                                              <th style="color:black !important"><b>Office Name</b></th>
                                             <th style="color:black !important"><b>Management Comments</b></th>
                                              <th></th>
                                        
                                        </tr>
                                    </thead>
                                    <tbody>
                                    
                                     <c:forEach items="${list.appBean}" var="app" >
          
          									 
          									 
          								<tr class="odd gradeX">
          								
          							<td > <a href="paymentViewForm.do?appId=${app.getAppId()}" style="color: rgb(128,128,128)">${app.getAppId()}</a></td>
                                            <td>${app.getLegCompName()}</td>
                                             <td>${app.getDistrict()}</td>
                                              <td>${app.getTaluk()}</td>
                                               <td>${app.getVillage()}</td>
                                           
                                             <td class="center">${app.getCreateTs()}</td>
                                              <td class="center">
                                              
                                              <div class="ui-widget">
                                                <input class="officeSearchClass" id="officeSearch_${app.getAppId()}" title="To get all Office Name type % only" />
                                               </div>
 
                                              
                                             <%--  <select style="width: 200px;" id="officeId_${app.getAppId()}">
				                           <option value="">--Select Office Name--</option>
                                    <c:forEach items="${list.officeDtl}" var="office" varStatus="count">
                                        <option value="${office.getOfficeId()}">${office.getOfficeName()}</option>
                                    </c:forEach>

				
				</select>
                                              --%> 
                                              </td>
                                             
                                              <td class="center"><textarea id="managementComments_${app.getAppId()}" name="managementComments" style="width:100%;height:100%;"></textarea></td>
                                              <td class="center">
                                              <input type="button" class="paymentClass" id="approved_${app.getAppId()}" name="approvedBtn" style="width: auto;" value="Approved"/>
											  <input type="button" class="cancelbtn" id="rejected_${app.getAppId()}" name="rejectedBtn" style="width: auto;" value="Rejected"/>
											  </td>
                                           
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