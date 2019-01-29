<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <script src="library/js/jquery-1.7.2.min.js"></script>
  <link rel="stylesheet" href="library/assets/plugins/bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" href="library/assets/css/main.css" />
    <link rel="stylesheet" href="library/assets/css/theme.css" />
    <link rel="stylesheet" href="library/assets/plugins/Font-Awesome/css/font-awesome.css" />
    <!--END GLOBAL STYLES -->

    <!-- PAGE LEVEL STYLES -->
    <link href="library/assets/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
 
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


<style>

#myTable td{
padding-left:10px;
}

#myTable td span{
margin-left:10px;
}
.error{
color:red;

}
input[type="button"] {
    border-radius: 10px;
    background-color: #2DAAE1;
    font-weight: bold;
    cursor: pointer;
    padding: 5px 5px 5px 5px;
    width: 100px;
    color: white;
    margin-top: 30px;
}

#initialId tr:nth-child(even) {background: white;height:40px;}
#initialId tr:nth-child(odd) {background:#EDEDED ;height:40px;}


.tdPadding{
padding-left:305px;
}

</style>
<script type="text/javascript">

$(document).ready(function(){
	
//	$('input[name="paymentMode"]').change(function(){
		var whichPayment = 'final';
		var appId = $('#appId').val();
		if(appId!=""){
		$.ajax({
			type:"POST",
			url:"getPaymentAmount.do",
			data:{'whichPayment':whichPayment,'appId':$('#appId').val()},
			success:function(response){
				$('#paymentId').val(response);
			}
			
		});
		}
		else{
			//alert("Please Enter Application Ref");
		}
//	}
	
	
	$('#appId').blur(function(){
	
		var whichPayment = 'final';
		var appId = $('#appId').val();
		
		if(appId!=""){
		$.ajax({
			type:"POST",
			url:"getPaymentAmount.do",
			data:{'whichPayment':whichPayment,'appId':$('#appId').val()},
			success:function(response){
			
				var originalcost=parseInt(response/1.18);
				var gst=parseInt(response-originalcost);
				$('#gstId').val(gst);
				$('#paymentId').val(response);
				
				
				
			}
			
		});
		}
		
	});
	$('#appId').blur(function(){
		
		var whichPayment = 'final';
		var appId = $('#appId').val();
		
		if(appId!=""){
		$.ajax({
			type:"POST",
			url:"getGstAmount.do",
			data:{'whichPayment':whichPayment,'appId':$('#appId').val()},
			success:function(response){
				$('#gstId').val(response);
				
			}
			
		});
		}
		
	});

	
	$(".ddDateClass").datepicker({
		dateFormat : 'dd-mm-yy',
		changeMonth : true,
		changeYear : true,
		maxDate : new Date(),
		showOn : "button",
		buttonImageOnly : true,
		buttonText : "Select date",
		buttonImage : "library/img/datepicker.png",
		showAnim : "slideDown",
	});
 
	
	function validateForm(){
	 
	 var numberReg =  /^[0-9]+$/;
	 var dateReg =  /^\d{2}-\d{2}-\d{4}$/;
	    var appId  =	$("#appId");
	    var paymentId  =	$("#paymentId");
	   /*  var ddNoId =	$("#ddNoId");
	    var ddDateId  = $("#ddDateId");
	    var bankNameId  = $("#bankNameId");
	    var bankBranchId  = $("#bankBranchId"); */
	    var flag = true;
	
//if($('input:radio[name=paymentType]:checked').val()=='0'){
	    var inputVal = new Array(appId, paymentId);
		

	     $('.error').hide();
		for(var i=0;i<inputVal.length;i++){
	        if(inputVal[i].val() == ""){
	        	inputVal[i].after('<span class="error"> This field is required. </span>');
	        	inputVal[i].focus();
	        	flag = false;
	        }
	       /*  else if(inputVal[i].attr('id')=='ddNoId' && !numberReg.test(inputVal[i].val())){
	        	inputVal[i].after('<span class="error"> Please enter DD Number </span>');
	        	inputVal[i].focus();
	        	flag = false;
	        }
	        else if(inputVal[i].attr('id')=='ddDateId' && !dateReg.test(inputVal[i].val())){
	        	inputVal[i].after('<span class="error"> Please enter date in dd-mm-yyyy format </span>');
	        	inputVal[i].focus();
	        	flag = false;
	        } */
		
	}
//}


		return flag;
 }
	$('#submitPaymentId').click(function(){
		if(validateForm())
		{
			if(confirm("Are you sure want to Submit ?")){
			$('#loading_image').show();
			var dataString = $('#formId').serialize();
			
		    	window.location.href="saveOnlinePaymentsDetails.do?paymentMode=final"+"&appId="+$('#appId').val()+"&initialPayment="+$('#paymentId').val();
		    	//window.location.href="saveOnlinePaymentsDetails.do?paymentMode=initial&appId="+response+"&initialPayment="+$('#ipf').val();
		  
			}
		}
		});
	
	$('input[type=radio][name=paymentType]').change(function(){
		if($('input:radio[name=paymentType]:checked').val()=='1'){
			$('#ddNoId').attr('disabled','disabled');
			$('#ddDateId').attr('disabled','disabled');
			$('#bankNameId').attr('disabled','disabled');
			$('#bankBranchId').attr('disabled','disabled');
		}
		else{
			$('#ddNoId').removeAttr('disabled');
			$('#ddDateId').removeAttr('disabled');
			$('#bankNameId').removeAttr('disabled');
			$('#bankBranchId').removeAttr('disabled');
		}
	});

	});
	


</script>

</head>
<body>


<table align="center" style="width: 1200px; font-size: 28px;">


<tr><td valign="middle" style="height:155px;width: 100%;"> 
    <table><tbody><tr><td>
    <img src="library/img/Logo_Tamil_Nadu.jpg" width="150px" height="150px" style="margin-left: 50px;"></td><td>
    <img src="library/img/middleImage.png" width="800px" height="75px">  </td><td>
    <img src="library/img/pic6_2.jpg" width="150px" height="150px" style="margin-right:50px;"></td><td>
        </td></tr></tbody></table>
  </td></tr>
  <tr>
			<td height="25px">
			
			<div style="padding: 0px; width: 100%;">
                <div style="background-image: url(library/img/border_bg.jpg); height: 7px; background-repeat: repeat-x;">
                    &nbsp;</div>
            </div>
			</td>
			</tr>
  
  
			
	</table>
	<img src="library/img/loader.gif" style="display: none;" id="loading_image">
	
<table id="statusHeader" align="center" class="table-bordered table table-striped display" style="width: 970px;">

	<tbody><tr>
		<td style="text-align: center; background-color: #FCFCF4; font-size: 25px; height: 10px; color: #800000; font-weight: bold;">Payment</td>
	</tr>
	
</tbody></table>
	<form id="formId" style="width:970px;margin-left: 195px;">
	<table id="initialId" width="100%" align="center" style="margin-left: 0px;">		
	
<!-- <tr><td class="tdPadding" width="50%"> <b></b>&nbsp;<input type="radio" name="paymentMode" id="initialPaymentId" value="initial" checked/></td><td><b></b>&nbsp;&nbsp;<input type="radio" name="paymentMode" id="finalPaymentId" value="final"/> </td></tr>	 -->	
<tr><td class="tdPadding" width="50%"><b>Enter Application Ref:</b><span style="color:red;">*</span></td><td><input type="text" placeholder ="Enter Application Ref" id="appId" name="appId" value=""/></td></tr>
 <tr><td class="tdPadding" width="50%"><b>GST Amount(GST @ 18 %):</b><span style="color:red;">*</span></td><td><input type="text" readonly placeholder ="Auto populate" title="Please enter Application Ref to Auto Populate Amount" id="gstId" name="initialPayment" style="background-color: lightgrey;"/></td></tr>

 <tr><td class="tdPadding" width="50%"><b>Tota Paymount Amount:</b><span style="color: rgb(128,128,128);font-size: 12px;"></span></td><td><input type="text" readonly placeholder ="Auto populate" title="Please enter Application Ref to Auto Populate Amount" id="paymentId" name="initialPayment" style="background-color: lightgrey;"/></td></tr>

<tr><td colspan="2" align="center" style="height: 50px;">
<input type="button" id="submitPaymentId"   value="Submit"/> <a href="/WaterNew/index.do">Back to Home Page </a>

</td></tr></tbody>

			</table>
			
		</form>
			</body>
			</html>