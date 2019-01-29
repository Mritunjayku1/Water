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

a{
cursor:pointer;
}
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

.bg_heading {
	text-align: left;
    font-size: 20px;
    color: white;
    margin-top: -32px;
    margin-left: 135px;
    font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}


.error {
	color: red;
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
var divisionlist = "";
var zoneNameList = "";
$('#dataTables-example input:checkbox').each(function(){
	zoneNameList =  zoneNameList +","+$(this).closest('tr').find('td:nth-child(3)').text();
});

$('#dataTables-example input:checkbox').each(function(){
	 divisionlist =  divisionlist +","+$(this).closest('tr').find('td:nth-child(4)').text();
});

function validateAddZoneForm() {
	
	var zoneName = $("#zoneNameId");
	var divisionNo = $("#clonedDiv .divisionNo");
	var divisionAddr = $("#clonedDiv .divisionAddr");
	var divisionMobile = $("#clonedDiv .divisionMobile");
	
	

	var inputVal = new Array(zoneName,divisionNo,divisionAddr,divisionMobile);

	$('.error').hide();
	flag = true;
	var reg = /^[0-9]+$/;
	for (i = 0; i < inputVal.length; i++) {
	
		 $(inputVal[i]).each(function(){
			 if ($(this).val() == "") {
					$(this)
							.after('<span class="error"> This field is required. </span>');
					$(this).focus();
					flag = false;
				} 
			 else  if ($(this).attr('id') == 'zoneNameId' && $(this).val().length > 1) {
					if(zoneNameList.indexOf($(this).val())>-1){
						  $(this).after('<span class="error"> Please enter unique Zone Name. </span>');
						  $(this).focus();
						  flag = false;
						}
					}
			 else  if ($(this).attr('class') == 'divisionNo' && (!reg.test($(this).val()) || divisionlist.indexOf(","+$(this).val()+",")>-1)) {
			
						  $(this).after('<span class="error"> Please enter unique Division No. and should be Numeric </span>');
						  $(this).focus();
						  flag = false;
					}
			
			 else if ($(this).attr('class') == 'divisionMobile' && !/^[0-9]{10}$/.test($(this).val())) {
				$(this).after('<span class="error"> Please enter correct mobile No. </span>');
				$(this).focus();
				flag = false;
			}
					
		 });
	
		
	}
	return flag;
}

function validateAddDivisionForm() {
	
	var zoneName = $("#divisionZoneId");
	var divisionNo = $("#divisionClonedDiv .divisionNo");
	var divisionAddr = $("#divisionClonedDiv .divisionAddr");
	var divisionMobile = $("#divisionClonedDiv .divisionMobile");
	
	

	var inputVal = new Array(zoneName,divisionNo,divisionAddr,divisionMobile);

	$('.error').hide();
	flag = true;
	var reg = /^[0-9]+$/;
	for (i = 0; i < inputVal.length; i++) {
	
		 $(inputVal[i]).each(function(){
			 if ($(this).val() == "") {
					$(this)
							.after('<span class="error"> This field is required. </span>');
					$(this).focus();
					flag = false;
				} 
			 else  if ($(this).attr('class') == 'divisionNo' && (!reg.test($(this).val()) || divisionlist.indexOf(","+$(this).val()+",")>-1)) {
			
						  $(this).after('<span class="error"> Please enter unique Division No. and should be Numeric </span>');
						  $(this).focus();
						  flag = false;
					}
			
			 else if ($(this).attr('class') == 'divisionMobile' && !/^[0-9]{10}$/.test($(this).val())) {
				$(this).after('<span class="error"> Please enter correct mobile No. </span>');
				$(this).focus();
				flag = false;
			}
					
		 });
	
		
	}
	return flag;
}



function validateEditForm() {
	return true;
	
	var editDivisionNo = $("#editDivisionNoId");
	var editDivisionAddr = $("#editDivisionAddrId");
	var editDivisionMobile = $("#editDivisionMobileId");
	
	

	var inputVal = new Array(editDivisionNo,editDivisionAddr,editDivisionMobile);

	$('.error').hide();
	flag = true;
	for (i = 0; i < inputVal.length; i++) {
		if (inputVal[i].val() == "") {
			inputVal[i]
					.after('<span class="error"> This field is required. </span>');
			inputVal[i].focus();
			flag = false;
		} 
		
		 else  if (inputVal[i].attr('id') == 'editDivisionNoId' && (!reg.test(inputVal[i].val()) || divisionlist.indexOf(","+inputVal[i].val()+",")>-1)) {
				
			  inputVal[i].after('<span class="error"> Please enter unique Division No. and should be Numeric </span>');
			  inputVal[i].focus();
			  flag = false;
		}

else if (inputVal[i].attr('id') == 'editDivisionMobileId' && !/^[0-9]{10}$/.test(inputVal[i].val())) {
	inputVal[i].after('<span class="error"> Please enter correct mobile No. </span>');
	inputVal[i].focus();
	flag = false;
}
		
	}
	return flag;
}





$("#all").click(function(){
    $('#dataTables-example input:checkbox').not(this).prop('checked', this.checked);
});

$('#dataTables-example input:checkbox').click(function(){
var numberOfChecked = $('#dataTables-example input:checkbox:checked').length;

var numberOfCheckbox = $('#dataTables-example input:checkbox').length;
if(numberOfChecked==(numberOfCheckbox-1)){
	 $('#all').prop('checked', this.checked);
}
});

$('#addZone').click(function(){
		$(".ui-dialog-content").dialog("close");
		$( "#addDialog" ).dialog({ 'width':'500px','modal':'true'});
	}); 

$('#addDivision').click(function(){
	$(".ui-dialog-content").dialog("close");
	$( "#addDivisionDialog" ).dialog({ 'width':'500px','modal':'true'});
}); 
	

	
	
$('#editDivision').click(function(){
	$(".ui-dialog-content").dialog("close");
	var numberOfChecked = $('#dataTables-example td input:checkbox:checked').length;
	if(numberOfChecked==1){
	$( "#editDialog" ).dialog({ 'width':'500px','modal':'true'});
	
	$('#dataTables-example td input:checkbox:checked').each(function(){
		$('#editDivisionId').val($(this).closest('tr').find('td:nth-child(1)').find('input[type="checkbox"]').attr('id').trim());
		$('#editZoneNameId').val($(this).closest('tr').find('td:nth-child(3)').text());
		$('#editDivisionNoId').val($(this).closest('tr').find('td:nth-child(4)').text());
		
		$('#editDivisionAddrId').val($(this).closest('tr').find('td:nth-child(5)').text());
		$('#editDivisionPhoneId').val($(this).closest('tr').find('td:nth-child(6)').text());
		$('#editDivisionMobileId').val($(this).closest('tr').find('td:nth-child(7)').text());
		
		zoneNameList = zoneNameList.replace($(this).closest('tr').find('td:nth-child(3)').text().trim(),"");
		divisionlist = divisionlist.replace($(this).closest('tr').find('td:nth-child(4)').text().trim(),"");
		
	});
	}
	else{
		alert("Please select only one Division to Edit");
	}
	
	
	
}); 
$('.closeBtn,.imgClose').click(function(){
	$(".ui-dialog-content").dialog("close");
	
}); 


$('#divisionSaveBtnId').click(function(){
	var zoneDivisionIds = "";
	var zoneDivisionAddrs = "";
	var zoneDivisionPhones = "";
	var zoneDivisionMobiles = "";
	 $('#clonedDiv .divisionNo').each(function(){
		 zoneDivisionIds = zoneDivisionIds +"," + $(this).val();
	 });
	 
	 $('#clonedDiv .divisionAddr').each(function(){
		 zoneDivisionAddrs = zoneDivisionAddrs +"##" + $(this).val();
	 });
	 
	 $('#clonedDiv .divisionPhone').each(function(){
		 zoneDivisionPhones = zoneDivisionPhones +"," + $(this).val();
	 });
	 
	 $('#clonedDiv .divisionMobile').each(function(){
		 zoneDivisionMobiles = zoneDivisionMobiles +"," + $(this).val();
	 });
	 
	if(validateAddZoneForm()){
	$.ajax({
		type:"POST",
		url:"addZone.do",
		data:{
			'zoneName':$('#zoneNameId').val(),
			'divisionNo':zoneDivisionIds.substring(1),
			'divisionAddr':zoneDivisionAddrs.substring(2),
			'divisionPhone':zoneDivisionPhones.substring(1),
			'divisionMobile':zoneDivisionMobiles.substring(1)
			
		},
		success:function(response){
			alert(response);
			
			window.location.reload();
		}
	});
	}
});


$('#divSaveBtnId').click(function(){
	var zoneDivisionIds = "";
	var zoneDivisionAddrs = "";
	var zoneDivisionPhones = "";
	var zoneDivisionMobiles = "";
	 $('#divisionClonedDiv .divisionNo').each(function(){
		 zoneDivisionIds = zoneDivisionIds +"," + $(this).val();
	 });
	 
	 $('#divisionClonedDiv .divisionAddr').each(function(){
		 zoneDivisionAddrs = zoneDivisionAddrs +"##" + $(this).val();
	 });
	 
	 $('#divisionClonedDiv .divisionPhone').each(function(){
		 zoneDivisionPhones = zoneDivisionPhones +"," + $(this).val();
	 });
	 
	 $('#divisionClonedDiv .divisionMobile').each(function(){
		 zoneDivisionMobiles = zoneDivisionMobiles +"," + $(this).val();
	 });
	if(validateAddDivisionForm()){
	$.ajax({
		type:"POST",
		url:"addDivision.do",
		data:{
			'zoneDivisionId':$('#divisionZoneId option:selected').val(),
			'divisionNo':zoneDivisionIds.substring(1),
			'divisionAddr':zoneDivisionAddrs.substring(2),
			'divisionPhone':zoneDivisionPhones.substring(1),
			'divisionMobile':zoneDivisionMobiles.substring(1)
			
		},
		success:function(response){
			alert(response);
			
			window.location.reload();
		}
	});
	}
});





$('#editDivisionBtn').click(function(){
	if(validateEditForm()){
	$.ajax({
		type:"POST",
		url:"editDivision.do",
		data:{
			'zoneDivisionId':$('#editDivisionId').val(),
			'zoneName':$('#editZoneNameId').val(),
			'divisionNo':$('#editDivisionNoId').val(),
			'divisionAddr':$('#editDivisionAddrId').val(),
			'divisionPhone':$('#editDivisionPhoneId').val(),
			'divisionMobile':$('#editDivisionMobileId').val()
			
		
		},
		success:function(response){
			alert(response);
			window.location.reload();
			
			
		}
	}); 
	}
	
});

$('#deleteDivision').click(function(){
	var divisionList = "";
	var zoneNameList = "";
	$('#dataTables-example input:checkbox:checked').each(function(){
		if($(this).closest('tr').find('td:nth-child(1)').find('input[type="checkbox"]').attr('id')!=undefined){
		  divisionList = divisionList +","+$(this).closest('tr').find('td:nth-child(1)').find('input[type="checkbox"]').attr('id').trim();
		  zoneNameList = zoneNameList +","+$(this).closest('tr').find('td:nth-child(3)').text().trim();
		}
	});
	if(confirm("Are you sure want to Delete these Division "+zoneNameList.substring(1)+" ? ")){
	$.ajax({
		type:"POST",
		url:"deleteDivision.do",
		data:{
			'zoneDivisionId':divisionList.substring(1)},
		success:function(response){
			alert(response);
			window.location.reload();
			
			
		}
	});
	}
});
});




</script>




<table class='table-bordered table table-striped display'
	style='width: 100%; font-size: 28px;'>
<tr>
		<td colspan='8'>
			<a href="#" style="padding: 10px;background-color: #78C948;color:white;" id="addZone">Add New Zone</a>
			<a href="#" style="padding: 10px;background-color: darkolivegreen;color:white;" id="addDivision">Add New Division</a>
			<a style="padding: 10px;background-color: #FEBA17;color:white;" id="editDivision">Edit</a>
			<a style="padding: 10px;background-color: #EE3B41;color:white;" id="deleteDivision">Delete</a>
			
			</td>
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
                                            <th style="color:black !important"><input type="checkbox" id="all" style="width:15px;"/></th>
                                            <th style="color:black !important"><b> #</b></th>
                                            <th style="color:black !important"><b>Zone Name</b></th>
                                            <th style="color:black !important"><b>Division No</b></th>
                                             <th style="color:black !important"><b>Address</b></th>
                                              <th style="color:black !important"><b>Phone</b></th>
                                               <th style="color:black !important"><b>Mobile</b></th>
                                           
                                        
                                        </tr>
                                    </thead>
                                    <tbody>
                                   
                                    
                                    
                                     <c:forEach items="${list.zoneDivisionDtl}" var="app" varStatus="count" >
          
                                               <tr class="odd gradeX">
          								
          							
                                            <td><input type="checkbox" id="${app.getDivisionId()}" style="width:15px;"/></td>
                                            <td>${count.count}</td>
                                             <td>${app.getZoneName()}</td>
                                             <td>${app.getDivisionNo()}</td>
                                             
                                             <td>${app.getDivisionAddr()}</td>
                                             <td>${app.getDivisionPhone()}</td>
                                             <td>${app.getDivisionMobile()}</td>
                                           
                                           
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

<div id="addDialog" style="display: none;">

<img class="imgClose" src="library/img/Close_SMS.png"
			style="width: 40px; border-width: 0px; float: right; margin-top: -43px; margin-right: -5px; cursor: pointer;">
		<h2 class="bg_heading">Add New Zone</h2>


<span><b>Enter Zone Name:</b></span><span style="color: red;">*</span>
				<input placeholder="Ex: ABC" type="text" id="zoneNameId" name="zoneName" /><br/>
				
				
				<div id="clonedDiv">
		<div id="fileCloneId" style="border: 1px solid lightgrey;padding:6px;">
						
<span><b>Enter Division No./ Depot No:</b></span><span style="color: red;">*</span>
				<input placeholder="Ex: ABC" type="text" id="divisionNoId_1" class="divisionNo" style="margin-left: 5px;width:118px;"/><img src="library/img/add.JPG" width="25px" height="25px" class="addFileId" title="Add New Division Details"/><br /> 
		
		
				<span><b>Enter Address:</b></span><span style="color: red;">*</span>
				<input placeholder="Ex: ABC" type="text" id="divisionAddrId_1" class="divisionAddr" style="margin-left: 5px;width:220px;"/><br/>
				
				<span><b>Enter Phone:</b></span>
				<input placeholder="Ex: ABC" type="text" id="divisionPhoneId_1" class="divisionPhone" style="margin-left: 16px;width:222px;"/><br/>
				
				<span><b>Enter Mobile:</b></span><span style="color: red;">*</span>
				<input placeholder="Ex: ABC" type="text" id="divisionMobileId_1" class="divisionMobile" maxlength="10"  style="margin-left: 14px;width:222px;"/><br/>
				
		
		
		</div>
		</div>
	<input type="button" value="Save" id="divisionSaveBtnId"/> <input type="button" value="Close"  class="closeBtn"/>

		</div>
		
		
		

<div id="addDivisionDialog" style="display: none;">

<img class="imgClose" src="library/img/Close_SMS.png"
			style="width: 40px; border-width: 0px; float: right; margin-top: -43px; margin-right: -5px; cursor: pointer;">
		<h2 class="bg_heading">Add New Division</h2>


<span><b>Select Zone Name:</b></span><span style="color: red;">*</span>
<select id="divisionZoneId" name="divisionZoneName">
<option value="">---</option>
<c:forEach var="var" items="${list.zoneMap}">
<option value="${var.key}">${var.value}</option>
</c:forEach>


</select><br/>
				
				
				<div id="divisionClonedDiv">
		<div id="divisionFileCloneId" style="border: 1px solid lightgrey;padding:6px;">
						
<span><b>Enter Division No./ Depot No:</b></span><span style="color: red;">*</span>
				<input placeholder="Ex: ABC" type="text" id="divNoId_1" class="divisionNo" style="margin-left: 5px;width:118px;"/><img src="library/img/add.JPG" width="25px" height="25px" class="addFileId" title="Add New Division Details"/><br /> 
		
		
				<span><b>Enter Address:</b></span><span style="color: red;">*</span>
				<input placeholder="Ex: ABC" type="text" id="divAddrId_1" class="divisionAddr" style="margin-left: 5px;width:220px;"/><br/>
				
				<span><b>Enter Phone:</b></span>
				<input placeholder="Ex: ABC" type="text" id="divPhoneId_1" class="divisionPhone" style="margin-left: 16px;width:222px;"/><br/>
				
				<span><b>Enter Mobile:</b></span><span style="color: red;">*</span>
				<input placeholder="Ex: ABC" type="text" id="divMobileId_1" class="divisionMobile"  maxlength="10" style="margin-left: 14px;width:222px;"/><br/>
				
		
		
		</div>
		</div>
	<input type="button" value="Save" id="divSaveBtnId"/> <input type="button" value="Close"  class="closeBtn"/>

		</div>







		
		
		<div id="editDialog" style="display: none;">


<img class="imgClose" src="library/img/Close_SMS.png"
			style="width: 40px; border-width: 0px; float: right; margin-top: -43px; margin-right: -5px; cursor: pointer;">
		<h2 class="bg_heading">Edit Division</h2>
		
				<input type="hidden" id="editDivisionId"/>
                 <span><b>Zone Name:</b></span>
				<input placeholder="Ex: ABC" type="text" id="editZoneNameId" name="editZoneName" readonly="readonly"/><br/>
                <span><b>Enter Division No./ Depot No:</b></span><span style="color: red;">*</span>
				<input placeholder="Ex: ABC" type="text" id="editDivisionNoId" name="editDivisionNo" style="margin-left: 5px;width:118px;"/><br/>
				
				<span><b>Enter Address:</b></span><span style="color: red;">*</span>
				<input placeholder="Ex: ABC" type="text" id="editDivisionAddrId" name="editDivisionAddr" style="margin-left: 5px;width:227px;"/><br/>
				
				<span><b>Enter Phone:</b></span>
				<input placeholder="Ex: ABC" type="text" id="editDivisionPhoneId" name="editDivisionPhone" style="margin-left: 16px;width:228px;"/><br/>
				
				<span><b>Enter Mobile:</b></span><span style="color: red;">*</span>
				<input placeholder="Ex: ABC" type="text" id="editDivisionMobileId" name="editDivisionMobile"  maxlength="10" style="margin-left: 14px;width:228px;"/><br/>
				
				
				
				
				
				<input type="button" value="Update" id="editDivisionBtn"/> <input type="button" value="Close" class="closeBtn"/>
				
		</div>
	
	<script src="assets/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="assets/plugins/dataTables/dataTables.bootstrap.js"></script>
     <script>
         $(document).ready(function () {
             $('#dataTables-example').dataTable();
             
             
             var idCount=2;
        	 $('#clonedDiv').on('click','.addFileId',function(){
        		  var cloned = $('#fileCloneId').clone();
        		  cloned.find('img.deleteFileId').remove();
        		  cloned.attr('id','fileCloneId_'+idCount);
        		  cloned.find('.divisionNo').attr('id','divisionNoId_'+idCount);
        		  
        		  cloned.find('.divisionAddr').attr('id','divisionAddrId_'+idCount);
        		  cloned.find('.divisionPhone').attr('id','divisionPhoneId_'+idCount);
        		  cloned.find('.divisionMobile').attr('id','divisionMobileId_'+idCount);
        		  
          		 cloned.find('.addFileId').after('<img src="library/img/delete.JPG" width="25px" height="25px" class="deleteFileId" title="Remove Division Details"/>');
        		 $('#clonedDiv').append(cloned);
        		 idCount++;
        		 
        	  });
        	 $('#clonedDiv').on('click','.deleteFileId',function(){
        		  $(this).parent('div').remove();  
        		});
        	  
        	 
        	  var idDivCount=2;
         	 $('#divisionClonedDiv').on('click','.addFileId',function(){
         		  var cloned = $('#divisionFileCloneId').clone();
         		  cloned.find('img.deleteFileId').remove();
         		  cloned.attr('id','fileCloneId_'+idCount);
         		  cloned.find('.divisionNo').attr('id','divNoId_'+idCount);
         		  
         		  cloned.find('.divisionAddr').attr('id','divAddrId_'+idCount);
         		  cloned.find('.divisionPhone').attr('id','divPhoneId_'+idCount);
         		  cloned.find('.divisionMobile').attr('id','divMobileId_'+idCount);
         		  
           		 cloned.find('.addFileId').after('<img src="library/img/delete.JPG" width="25px" height="25px" class="deleteFileId" title="Remove Division Details"/>');
         		 $('#divisionClonedDiv').append(cloned);
         		idDivCount++;
         		 
         	  });
         	 $('#divisionClonedDiv').on('click','.deleteFileId',function(){
         		  $(this).parent('div').remove();  
         		});
         	  
        	 
        	 
        	 
        	 
        	 
        	 
             
         });
    </script>

