<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http//www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <link href="library/css/style.css" rel="stylesheet"/> 

 <link rel="stylesheet" href="library/assets/plugins/bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" href="library/assets/css/main.css" />
    <link rel="stylesheet" href="assets/css/theme.css" />
    <link rel="stylesheet" href="assets/css/MoneAdmin.css" />
    <link rel="stylesheet" href="assets/plugins/Font-Awesome/css/font-awesome.css" />
	    <link rel="stylesheet" href="assets/plugins/validationengine/css/validationEngine.jquery.css" />
 
 
<link href="library/css/jquery-ui-1.8.21.custom.css" rel="stylesheet">



<style>

tr:nth-child(even) {background: white;height:40px;}
tr:nth-child(odd) {background:#EDEDED ;height:40px;}


input[type="button"] {
    border-radius 10px;
    background-color #2DAAE1;
    font-weight bold;
    cursor pointer;
    padding 5px 5px 5px 5px;
    margin-left150px; 
    width 100px;
    color white;
    margin-top 30px;
}

#myTable td{
padding-left:10px;
}

#myTable td span{
margin-left:10px;
}
</style>
</head>
<body>

<table class="table-bordered table table-striped display" style="width: 100%; font-size: 28px;">

	<tbody><tr>
		<td style="text-align: center; background-color: #FCFCF4; font-size: 17px; height: 10px; color: #800000; font-weight: bold;">Application Details</td>
	</tr>
	
</tbody></table>

	<table id="myTable" width="90%" align="center" style="height: 450px;background-color:#FCFCF4; ">
		<tr>
			<td width="25%"><b>Application #</b></td>
			<td width="25%">:<span id="legCompNameId">${list.application.getAppId()}</span></td>
			<td width="25%"><b>Registered</b></td>
			<td width="25%">:<span id="dateId">
					</span></td>
		</tr>
		

		<tr>
			<td width="25%"><b>Legal Name of Company</b></td>
			<td width="25%">:<span id="legCompNameId">${list.application.getLegCompName()}</span></td>
			<td width="25%"><b>Name of contact person</b></td>
			<td width="25%">:<span id="contactPersonNameId">${list.application.getSalutation()}
					${list.application.getContactPersonName()}</span></td>
		</tr>
		
		<tr>
			<td><b>Mobile Number</b></td>
			<td>:<span id="mobileNumId">${list.application.getMobileNum()}</span></td>
			<td><b>Land line number</b></td>
			<td>:<span id="landlineNumId">${list.application.getLandLineNo()}</span></td>
		</tr>
		<tr>
			<td><b>Email Id</b></td>
			<td>:<span id="emailAddrId">${list.application.getEmailAddr()}</span></td>
			<td><b>Survey Field No</b></td>
			<td>:<span id="surveyFieldNo">${list.application.getSurveyFieldNo()}</span></td>
		</tr>
		
          <tr>
			<td><b>Address for Correspondence</b></td>
			<td>:<span id="correspondenceAddrId">${list.application.getCdoorNo()}
					${list.application.getCplotNo()}
					${list.application.getCstreetName()}
					${list.application.getClocation()}
					${list.application.getCpinCode()}</span></td>
			<td><b>Site Address</b></td>
			<td>:<span id="addrPremSoughtId">${list.application.getDoorNo()}
					${list.application.getPlotNo()} ${list.application.getStreetName()}
					${list.application.getLocation()} ${list.application.getPinCode()}</span></td>
		</tr>
		
		<tr>
			
			<td><b>District</b></td>
			<td>:<span id="reqMldId">${list.application.getDistrict()}</span></td>
			
			<td><b>Taluk</b></td>
			<td>:<span class="lessWidth"
				id="isExistConnectionForAlterationId">${list.application.getTaluk()}</span></td>
		</tr>
		
		
		<tr>
			
			<td><b>Village</b></td>
			<td>:<span id="reqMldId">${list.application.getVillage()}</span></td>
			
			<td><b>Type of category</b></td>
			<td style="">:<span class="classCategory" id="categoryTypeId">${list.application.getCategoryType()}</span></td>
		</tr>
		
		
		
		
		<tr>

			<td><b>Is this New Connection?</b></td>
			<td>:<span class="lessWidth" id="isNewConnectionId">${list.application.getIsNewConnectionDisplay()}</span></td>
			<td><b>Upfront Charges</b></td>
			<td>: <span class="lessWidth" id="isNewConnectionId">${list.application.getUpfrontCharges()}</span></td>
			
		</tr>

		<tr>
			
			<td><b>Requirement of water in MLD (Million Litres per day)</b></td>
			<td>:<span id="reqMldId">${list.application.getReqMld()}</span></td>
			
			<td><b>Total Amount (Application Fee + GST Amount)</b></td>
			<td>:<span class="lessWidth"
				id="isExistConnectionForAlterationId">${list.application.getTotalAmount()}</span></td>
		</tr>
		
		



		<tr>
			<td><b>Application Fee</b></td>
			<td>:<span class="lessWidth" id="intrPlumStatusId">${list.application.getApplicationFee()}</span></td>

			<td><b>GST Amount</b></td>
			<td>:<span class="lessWidth" id="watSevPropId">${list.application.getGstAmount()}</span></td>
		</tr>
		<tr>
			<td><b>Work Type</b></td>
			<td>:<span class="lessWidth" id="workTypeId">${list.application.getWorkTypeDisplay()}</span></td>
			<td><b>Application Status</b></td>
			<td>:<span class="lessWidth" id="status">${list.application.getManagementComments()}</span></td>
			
		</tr>
				<tr>
			<td colspan="4" align="center"><input type="button" style="margin-right: 10px;"
				id="printbtnId" onclick="javascript:window.print();"
				name="industrialistSubmitBtn" value="Print" /><input type="button"  onClick="window.history.go(-1)" value="Back"/></td>
		</tr>

	</table>


</body>
			</html>