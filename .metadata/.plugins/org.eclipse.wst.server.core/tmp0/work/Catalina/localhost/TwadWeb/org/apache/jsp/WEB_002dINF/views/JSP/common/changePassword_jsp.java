/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.67
 * Generated at: 2019-02-16 18:32:17 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.JSP.common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class changePassword_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!--\r\n");
      out.write("\r\n");
      out.write("\tEC- User Login screen.\r\n");
      out.write("\tCreated by Mahalingam\r\n");
      out.write("\r\n");
      out.write(" -->\r\n");
      out.write(" \r\n");
      out.write("<!DOCTYPE html> \r\n");
      out.write("\r\n");
      out.write("<HTML lang=\"en\">\r\n");
      out.write("<HEAD>\r\n");
      out.write("<META charset=\"utf-8\">\r\n");
      out.write("<META http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("<meta name=\"viewport\"\r\n");
      out.write("\tcontent=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\" />\r\n");
      out.write("<link rel=\"shortcut icon\" href=\"library/img/EC_logo.jpg\" >\r\n");
      out.write("<TITLE>E-Water | Login</TITLE>\r\n");
      out.write("<!-- Bootstrap -->\r\n");
      out.write("<LINK href=\"library/login/bootstrap.min.css\" rel=\"stylesheet\"\r\n");
      out.write("\ttype=\"text/css\">\r\n");
      out.write("<LINK href=\"library/login/jquery-ui.min.css\" rel=\"stylesheet\"\r\n");
      out.write("\ttype=\"text/css\">\r\n");
      out.write("<LINK href=\"library/login/scree.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("\r\n");
      out.write("<link href=\"library/css/jquery-ui-1.8.21.custom.css\" rel=\"stylesheet\">\r\n");
      out.write("<script src=\"library/js/jquery-1.7.2.min.js\"></script>\r\n");
      out.write("<script src=\"library/js/jquery-ui-1.8.21.custom.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("$(document).ready(function(){\r\n");
      out.write("\t   $('input[type=\"password\"]').bind(\"cut copy paste\",function(e) {\r\n");
      out.write("\t      e.preventDefault();\r\n");
      out.write("\t   });\r\n");
      out.write("\t   \r\n");
      out.write("\t   \r\n");
      out.write("\t  \r\n");
      out.write("  \t\t \r\n");
      out.write("  \t\t$('#changePasswordId').click(function() {\r\n");
      out.write("\t\t\tif(validateChangePasswordForm()){\r\n");
      out.write("\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\ttype : \"POST\",\r\n");
      out.write("\t\t\t\turl : \"managementChangePassword.do\",\r\n");
      out.write("\t\t\t\tdata : {\r\n");
      out.write("\t\t\t\t\t'userName' : $('#changeUNameId').val(),\r\n");
      out.write("\t\t\t\t\t'userEmailId' : $('#changeUEmailId').val(),\r\n");
      out.write("\t\t\t\t\t'oldPassword' : $('#oldPasswordId').val(),\r\n");
      out.write("\t\t\t\t\t'newPassword' : $('#newPasswordId').val()\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\tsuccess : function(response) {\r\n");
      out.write("\t\t\t\t\talert(response);\r\n");
      out.write("\t\t\t\t\twindow.location.reload();\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t});\r\n");
      out.write("\t   \r\n");
      out.write("\t   \r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\tfunction validateChangePasswordForm(){\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tvar emailReg = /^([\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4})?$/;\r\n");
      out.write("\t\tvar passwordRegex = /^(?=.{8,})(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$/;\r\n");
      out.write("\t\tvar emailId = $('#changeUEmailId');\r\n");
      out.write("\t\tvar oldPasswordId = $('#oldPasswordId');\r\n");
      out.write("\t\tvar newPasswordId = $('#newPasswordId');\r\n");
      out.write("\t\tvar confirmPasswordId = $('#confirmPasswordId');\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tvar inputVal = new Array(emailId,oldPasswordId,newPasswordId,confirmPasswordId);\r\n");
      out.write("\r\n");
      out.write("\t\t$('.error').hide();\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tflag = true;\r\n");
      out.write("\t\tfor (i = 0; i < inputVal.length; i++) {\r\n");
      out.write("\t\t\tvar value = inputVal[i].val().replace(/ /g, \"\");\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tif (value == \"\") {\r\n");
      out.write("\t\t\t\tinputVal[i]\r\n");
      out.write("\t\t\t\t\t\t.after('<br/><span class=\"error\"> This field is required. </span>');\r\n");
      out.write("\t\t\t\tinputVal[i].focus();\r\n");
      out.write("\t\t\t\tflag = false;\r\n");
      out.write("\t\t\t} else if (inputVal[i].attr('id') == 'changeUEmailId'\r\n");
      out.write("\t\t\t\t\t&& !emailReg.test(value)) {\r\n");
      out.write("\t\t\t\tinputVal[i]\r\n");
      out.write("\t\t\t\t\t\t.after('<br/><span class=\"error\"> Please enter correct Email Id </span>');\r\n");
      out.write("\t\t\t\tinputVal[i].focus();\r\n");
      out.write("\t\t\t\tflag = false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\telse if (inputVal[i].attr('id') == 'newPasswordId'\r\n");
      out.write("\t\t\t\t&& !passwordRegex.test(inputVal[i].val())) {\r\n");
      out.write("\t\t\tinputVal[i]\r\n");
      out.write("\t\t\t\t\t.after('<br/><span class=\"error\"> Please should contains minimum 8 character,<br/> atleast 1 caps, 1 small, 1 number and 1 special character </span>');\r\n");
      out.write("\t\t\tinputVal[i].focus();\r\n");
      out.write("\t\t\tflag = false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\telse if (inputVal[i].attr('id') == 'confirmPasswordId'\r\n");
      out.write("\t\t\t\t&& inputVal[i].val() != $('#newPasswordId').val()) {\r\n");
      out.write("\t\t\tinputVal[i]\r\n");
      out.write("\t\t\t\t\t.after('<br/><span class=\"error\"> Confirm Password should match with New Password</span>');\r\n");
      out.write("\t\t\tinputVal[i].focus();\r\n");
      out.write("\t\t\tflag = false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\treturn flag;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("</script>\r\n");
      out.write("<STYLE type=\"text/css\">\r\n");
      out.write("\r\n");
      out.write(".error {\r\n");
      out.write("\tcolor: red;\r\n");
      out.write("}\r\n");
      out.write("input.error {\r\n");
      out.write("\tborder: 1px solid red;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("label.error {\r\n");
      out.write("\tcolor: red;\r\n");
      out.write("\tfont-weight: normal;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("html {\r\n");
      out.write("\theight: 100%\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("::-moz-sWater {\r\n");
      out.write("\tbackground: #1589FF;\r\n");
      out.write("\tcolor: #fff;\r\n");
      out.write("\ttext-shadow: none;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("::sWater {\r\n");
      out.write("\tbackground: #1589FF;\r\n");
      out.write("\tcolor: #fff;\r\n");
      out.write("\ttext-shadow: none;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("body {\r\n");
      out.write("/* \tbackground-image: url('library/img/EC_banner.jpg'); */\r\n");
      out.write("\t-webkit-background-size: cover;\r\n");
      out.write("\t-moz-background-size: cover;\r\n");
      out.write("\t-o-background-size: cover;\r\n");
      out.write("\tbackground-size: cover;\r\n");
      out.write("\tbackground-repeat: no-repeat;\r\n");
      out.write("/* \tbackground:#1589FF;  */\r\n");
      out.write("\t/* background-size : 100%; */\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".login {\r\n");
      out.write("\tbackground: #eceeee;\r\n");
      out.write("\tborder: 1px solid #42464b;\r\n");
      out.write("\tborder-radius: 6px;\r\n");
      out.write("\theight: 257px;\r\n");
      out.write("\tmargin: 10%;\r\n");
      out.write("\twidth: 298px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".stretch {\r\n");
      out.write("\twidth: 100%;\r\n");
      out.write("\theight: 100%;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".login h1 {\r\n");
      out.write("\tbackground-image: linear-gradient(top, #f1f3f3, #d4dae0);\r\n");
      out.write("\tborder-bottom: 1px solid #a6abaf;\r\n");
      out.write("\tborder-radius: 6px 6px 0 0;\r\n");
      out.write("\tbox-sizing: border-box;\r\n");
      out.write("\tcolor: #727678;\r\n");
      out.write("\tdisplay: block;\r\n");
      out.write("\theight: 43px;\r\n");
      out.write("\tfont: 600 14px/1 'Open Sans', sans-serif;\r\n");
      out.write("\tpadding-top: 14px;\r\n");
      out.write("\tmargin: 0;\r\n");
      out.write("\ttext-align: center;\r\n");
      out.write("\ttext-shadow: 0 -1px 0 rgba(0, 0, 0, 0.2), 0 1px 0 #fff;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("input[type=\"password\"], input[type=\"text\"] {\r\n");
      out.write("\tbackground: url('http://i.minus.com/ibhqW9Buanohx2.png') center left\r\n");
      out.write("\t\tno-repeat, linear-gradient(top, #d6d7d7, #dee0e0);\r\n");
      out.write("\tborder: 1px solid #a1a3a3;\r\n");
      out.write("\tborder-radius: 4px;\r\n");
      out.write("\tbox-shadow: 0 1px #fff;\r\n");
      out.write("\tbox-sizing: border-box;\r\n");
      out.write("\tcolor: #696969;\r\n");
      out.write("\theight: 39px;\r\n");
      out.write("\tmargin: 0px; /*10px 0 0 20px; */\r\n");
      out.write("\tpadding-left: 2px 2px;\r\n");
      out.write("\ttransition: box-shadow 0.3s;\r\n");
      out.write("\twidth: 240px;\r\n");
      out.write("\tmargin-top: 20px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("input[type=\"password\"]:focus, input[type=\"text\"]:focus {\r\n");
      out.write("\tbox-shadow: 0 0 4px 1px rgba(55, 166, 155, 0.3);\r\n");
      out.write("\toutline: 0;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".show-password {\r\n");
      out.write("\tdisplay: block;\r\n");
      out.write("\theight: 16px;\r\n");
      out.write("\tmargin: 26px 0 0 28px;\r\n");
      out.write("\twidth: 87px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("input[type=\"checkbox\"] {\r\n");
      out.write("\tcursor: pointer;\r\n");
      out.write("\theight: 16px;\r\n");
      out.write("\topacity: 0;\r\n");
      out.write("\tposition: relative;\r\n");
      out.write("\twidth: 64px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("input[type=\"checkbox\"]:checked {\r\n");
      out.write("\tleft: 29px;\r\n");
      out.write("\twidth: 58px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".toggle {\r\n");
      out.write("\tbackground: url(http://i.minus.com/ibitS19pe8PVX6.png) no-repeat;\r\n");
      out.write("\tdisplay: block;\r\n");
      out.write("\theight: 16px;\r\n");
      out.write("\tmargin-top: -20px;\r\n");
      out.write("\twidth: 87px;\r\n");
      out.write("\tz-index: -1;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("input[type=\"checkbox\"]:checked+.toggle {\r\n");
      out.write("\tbackground-position: 0 -16px\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".change {\r\n");
      out.write("\tcolor: #7f7f7f;\r\n");
      out.write("\tdisplay: inline-block;\r\n");
      out.write("\tfloat: right;\r\n");
      out.write("\tfont: 12px/1 sans-serif;\r\n");
      out.write("\tleft: -19px;\r\n");
      out.write("\tposition: relative;\r\n");
      out.write("\ttext-decoration: none;\r\n");
      out.write("\ttop: 5px;\r\n");
      out.write("\ttransition: color .4s;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".change:hover {\r\n");
      out.write("\tcolor: #3b3b3b\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("input[type=\"submit\"],input[type=\"button\"] {\r\n");
      out.write("\twidth: 240px;\r\n");
      out.write("\theight: 35px;\r\n");
      out.write("\tdisplay: block;\r\n");
      out.write("\tfont-family: Arial, \"Helvetica\", sans-serif;\r\n");
      out.write("\tfont-size: 16px;\r\n");
      out.write("\tfont-weight: bold;\r\n");
      out.write("\tcolor: #fff;\r\n");
      out.write("\ttext-decoration: none;\r\n");
      out.write("\ttext-transform: uppercase;\r\n");
      out.write("\ttext-align: center;\r\n");
      out.write("\ttext-shadow: 1px 0px 0px black;\r\n");
      out.write("\tpadding-top: 6px;\r\n");
      out.write("\tmargin: 1%;\r\n");
      out.write("\tposition: relative;\r\n");
      out.write("\tcursor: pointer;\r\n");
      out.write("\tborder: none;\r\n");
      out.write("\tbackground-color: #1589FF;\r\n");
      out.write("\tbackground-image: linear-gradient(top, #3db0a6, #3111);\r\n");
      out.write("\tborder-top-left-radius: 5px;\r\n");
      out.write("\tborder-top-right-radius: 5px;\r\n");
      out.write("\tborder-bottom-right-radius: 5px;\r\n");
      out.write("\tborder-bottom-left-radius: 5px;\r\n");
      out.write("\tbox-shadow: inset 0px 1px 0px #2ab7ec, 0px 3px 0px 0px #497a78, 0px 6px\r\n");
      out.write("\t\t5px white;\r\n");
      out.write("\t\tmargin-bottom: 15px;\r\n");
      out.write("\t\tmargin-top: 20px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".shadow {\r\n");
      out.write("\tbackground: #000;\r\n");
      out.write("\tborder-radius: 12px 12px 4px 4px;\r\n");
      out.write("\tbox-shadow: 0 0 20px 10px #000;\r\n");
      out.write("\theight: 12px;\r\n");
      out.write("\tmargin: 30px auto;\r\n");
      out.write("\topacity: 0.2;\r\n");
      out.write("\twidth: 270px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("input[type=\"submit\"]:active,input[type=\"button\"]:active {\r\n");
      out.write("\ttop: 3px;\r\n");
      out.write("\tbox-shadow: inset 0px 1px 0px #2ab7ec, 0px 2px 0px 0px #31524d, 0px 5px\r\n");
      out.write("\t\t3px #999;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("div.wrap {\r\n");
      out.write("\tborder: 1px solid #1589FF;\r\n");
      out.write("\theight: 290px;\r\n");
      out.write("\twidth: 290px;\r\n");
      out.write("\tmargin: 12%;\r\n");
      out.write("\tborder-radius: 4%;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("div.inner {\r\n");
      out.write(" \tbackground: white;\r\n");
      out.write("\theight: 278px;\r\n");
      out.write("\twidth: 278px;\r\n");
      out.write("\tborder-radius: 2%;\r\n");
      out.write("\tmargin: auto;\r\n");
      out.write("\tmargin-top: 5px;\r\n");
      out.write("\tborder: 6px solid #1589FF; \r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("label {\r\n");
      out.write("\tfloat: left;\r\n");
      out.write("\twidth: 10em;\r\n");
      out.write("\tpadding-left: 50px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("div[role=\"dialog\"] {\r\n");
      out.write("\tborder-radius: 15px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".changeDialog {\r\n");
      out.write("\tdisplay: none;\r\n");
      out.write("\twidth: 400px;\r\n");
      out.write("\tpadding: 0px !important;\r\n");
      out.write("\toverflow: hidden !important;\r\n");
      out.write("}\r\n");
      out.write(".ui-dialog>.ui-widget-header {\r\n");
      out.write("\tbackground: blue;\r\n");
      out.write("\tdisplay: none;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".ui-dialog {\r\n");
      out.write("\tpadding: 0px;\r\n");
      out.write("\toverflow: hidden;\r\n");
      out.write("}\r\n");
      out.write(".bg_heading {\r\n");
      out.write("\ttext-align: center;\r\n");
      out.write("\tfont-family: Lato;\r\n");
      out.write("\tfont-size: 20px;\r\n");
      out.write("\ttext-transform: uppercase;\r\n");
      out.write("\tcolor: white;\r\n");
      out.write("\tbackground-color: #2DAAE1;\r\n");
      out.write("\tborder-radius: 15px 0px 0px 0px;\r\n");
      out.write("\tmargin: 0px;\r\n");
      out.write("\theight: 45px;\r\n");
      out.write("\tpadding-top: 10px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</STYLE>\r\n");
      out.write("</HEAD>\r\n");
      out.write("\r\n");
      out.write("<BODY> \r\n");
      out.write("\t\t <Label style=\"text-align: center; font-family: alice; width: 100%; color: #1589FF; font-size: 39px; padding: 10px;border-bottom: 2px solid #1589FF;\">\r\n");
      out.write("\t\t<img src='library/img/EC_logo.jpg' width=\"70px\" height=\"70px\"><font color=\"blue\">  New Water Connections</font> \t\r\n");
      out.write("\t\t\t<br><span style='font-size:17px;font-weight: normal;'></span></Label> \r\n");
      out.write("\t<!-- <DIV class=\"container\" align=\"center\">  -->\r\n");
      out.write("             <table width=\"100%\"><tr><td align=\"center\" width=\"100%\">\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<table  height=\"400px\">\r\n");
      out.write("\t\t\t<tr  height=\"20px\"><td></td></tr>\r\n");
      out.write("\t\t\t<tr height=\"50px\"><td style=\"text-align: center; background-color: #FCFCF4; font-size: 25px; height: 10px; color: #800000; font-weight: bold;\">Change Password</td></tr>\r\n");
      out.write("\t\t\t<tr  height=\"20px\"><td></td></tr>\r\n");
      out.write("\t\t\t<tr><td align=\"center\" width=\"600px\">\r\n");
      out.write("\t\t\t\t<input type=\"text\" name=\"userName\" id=\"changeUNameId\" placeholder=\"Enter UserName\" title=\"Enter UserName\" style=\"padding-left: 10px;\" />\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t\t<tr><td  align=\"center\" width=\"600px\">\r\n");
      out.write("\t\t\t\t<input type=\"text\" name=\"userEmailId\" id=\"changeUEmailId\" placeholder=\"Enter EmailId\" title=\"Enter EmailId\" style=\"padding-left: 10px;\"/>\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<tr><td  align=\"center\" width=\"600px\">\r\n");
      out.write("\t\t\t\t<input type=\"Password\" name=\"oldPassword\" id=\"oldPasswordId\" placeholder=\"Enter Old Password\" title=\"Enter Old Password\" style=\"padding-left: 10px;\"/>\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t\t<tr><td align=\"center\" width=\"600px\">\r\n");
      out.write("\t\t\t\t<input type=\"Password\" name=\"newPassword\" id=\"newPasswordId\" placeholder=\"Enter New Password\" title=\"Enter New Password\" style=\"padding-left: 10px;\"/>\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<tr><td align=\"center\" width=\"600px\">\r\n");
      out.write("\t\t\t\t<input type=\"Password\" name=\"confirmPasswordId\" id=\"confirmPasswordId\" placeholder=\"Enter Confirm Password\" title=\"Enter Confirm Password\" style=\"padding-left: 10px;\"/>\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<tr><td align=\"center\" width=\"600px\">\r\n");
      out.write("\t\t\t\t<input type=\"button\" id=\"changePasswordId\" Value=\"Submit\" />\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t</td></tr></table>\r\n");
      out.write("\t\t<!-- </DIV> -->\r\n");
      out.write("\r\n");
      out.write("</BODY>\r\n");
      out.write("<footer\r\n");
      out.write("\tstyle='text-align: center; position: fixed; bottom: 0; width: 100%;border-top:2px solid #1589FF;'>\r\n");
      out.write("\t<table\r\n");
      out.write("\t\tstyle='text-align: center; vertical-align: middle; width: 100%;   font-size: 12px; color: #1589FF; height: 20px;'>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td>Powered by: <a style='color: #1589FF;'\r\n");
      out.write("\t\t\t\thref=\"###\" target=\"_blank\">TamilNadu </a> &copy; 2019\r\n");
      out.write("\t\t\t</td> \r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("</footer>\r\n");
      out.write("</HTML>\r\n");
      out.write("\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
