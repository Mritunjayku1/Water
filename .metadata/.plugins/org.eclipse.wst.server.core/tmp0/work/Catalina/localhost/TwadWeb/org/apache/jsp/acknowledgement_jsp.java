/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.67
 * Generated at: 2019-02-16 20:35:41 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class acknowledgement_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\r\n");
      out.write("\"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write(" <link href=\"library/css/style.css\" rel=\"stylesheet\"/> \r\n");
      out.write(" <link rel=\"stylesheet\"\r\n");
      out.write("\thref=\"library/assets/plugins/bootstrap/css/bootstrap.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"library/assets/css/main.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"library/assets/css/theme.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"library/assets/css/MoneAdmin.css\" />\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("\thref=\"library/assets/plugins/Font-Awesome/css/font-awesome.css\" />\r\n");
      out.write("<link href=\"library/assets/plugins/dataTables/dataTables.bootstrap.css\"\r\n");
      out.write("\trel=\"stylesheet\" />\r\n");
      out.write("\r\n");
      out.write("  \r\n");
      out.write(" <link href=\"library/css/jquery-ui-1.8.21.custom.css\" rel=\"stylesheet\">\r\n");
      out.write(" <script src=\"library/js/jquery-1.7.2.min.js\"></script>\r\n");
      out.write("<script src=\"library/js/jquery-ui-1.8.21.custom.min.js\"></script>\r\n");
      out.write("<script src='library/js/chosen.jquery.js'></script>\r\n");
      out.write("<script src='library/js/html2canvas.js'></script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write(" <style type=\"text/css\">\r\n");
      out.write(" td span{\r\n");
      out.write(" padding-left: 10px;\r\n");
      out.write(" }\r\n");
      out.write(" body{\r\n");
      out.write(" font-size: 14px;\r\n");
      out.write(" }\r\n");
      out.write(" input[type=\"button\"],input[type=\"submit\"] {\r\n");
      out.write("    border-radius: 10px;\r\n");
      out.write("    background-color: #2DAAE1;\r\n");
      out.write("    font-weight: bold;\r\n");
      out.write("    cursor: pointer;\r\n");
      out.write("    padding: 5px 5px 5px 5px;\r\n");
      out.write("    width: 200px;\r\n");
      out.write("    color: white;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("div[role=\"dialog\"]{\r\n");
      out.write("border-radius:15px; \r\n");
      out.write("}\r\n");
      out.write(".loginDialog{\r\n");
      out.write("\tdisplay:none;\r\n");
      out.write("\twidth:400px;\r\n");
      out.write("\theight:200px;\r\n");
      out.write("\tpadding:0px !important;\r\n");
      out.write("\toverflow: hidden !important;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("#ackTable tr:nth-child(even) {background: white;height:40px;}\r\n");
      out.write("#ackTable tr:nth-child(odd) {background:#EDEDED ;height:40px;}\r\n");
      out.write("\r\n");
      out.write("#ackTable td{\r\n");
      out.write("padding-left:10px;\r\n");
      out.write("font-size: 17px;\r\n");
      out.write("font-family: \"Helvetica Neue\", Helvetica, Arial, sans-serif;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(" </style>\r\n");
      out.write("</head>\r\n");
      out.write("<body style=\"margin:0px;padding:0px\">\r\n");
      out.write("<div id=\"ackDiv\">\r\n");
      out.write("<table id=\"mydiv\" align=\"center\">\r\n");
      out.write("\r\n");
      out.write("<tr><td valign=\"middle\" align=\"center\" style=\"height:160px;width: 100%;\"> \r\n");
      out.write("<table><tr><td>\r\n");
      out.write(" <img src=\"library/img/twad_logo.gif\" width=\"110px\" height=\"106px\"></td><td>\r\n");
      out.write("    <img src=\"library/img/Acknowlegement.png\" width=\"800px\" height=\"75px\">\r\n");
      out.write("    </td></tr></table>\r\n");
      out.write("  </td></tr>\r\n");
      out.write("  \r\n");
      out.write(" \r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<td colspan=\"2\" height=\"25px\">\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<div style=\"padding: 0px; width: 100%;\">\r\n");
      out.write("                <div style=\"background-image: url(library/img/border_bg.jpg); height: 7px; background-repeat: repeat-x;\">\r\n");
      out.write("                    &nbsp;</div>\r\n");
      out.write("            </div>\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("<table id=\"statusHeader\" align=\"center\" class=\"table-bordered table table-striped display\" style=\"width: 970px;\">\r\n");
      out.write("\r\n");
      out.write("\t<tbody><tr>\r\n");
      out.write("\t\t<td style=\"text-align: center; background-color: #FCFCF4; font-size: 25px; height: 10px; color: #800000; font-weight: bold;\">Acknowledgement</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t\r\n");
      out.write("</tbody></table>\r\n");
      out.write("<table width=\"35%\" id=\"ackTable\" align=\"center\" style=\"font-size: 20px;\">\r\n");
      out.write("\r\n");
      out.write("<tr><td align=\"left\" width=\"50%\"><b> Name of the Applicant</b></td><td  width=\"50%\">:<span>");
      out.print(request.getParameter("applicantName"));
      out.write("</span></td></tr>\r\n");
      out.write("<tr><td align=\"left\"><b>Application Reference No</b></td><td>:<span>");
      out.print(request.getParameter("applicationRef"));
      out.write("</span></td></tr>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<table width=\"40%\" align=\"center\" style=\"font-size: 15px;margin-top: 10px;\">\r\n");
      out.write("\r\n");
      out.write("<tr height=\"70px\" style=\"font-weight: bold;font-size: 17px;\"><td colspan=\"2\" align=\"center\">Note: Please note it Application Reference No for Future Reference.</td></tr>\r\n");
      out.write("<tr height=\"70px\" style=\"font-weight: bold;\"><td align=\"center\"><input type=\"button\" id=\"load\" value=\"Download\"/></td>\r\n");
      out.write("<td><input type=\"button\"\r\n");
      out.write("\t\t\t\tid=\"printbtnId\" onclick=\"javascript:window.print();\"\r\n");
      out.write("\t\t\t\tname=\"industrialistSubmitBtn\" value=\"Print\" /></td></tr>\r\n");
      out.write("<tr><td align=\"center\"><a href=\"/TwadWeb/index.do\"><b>Back to Home Page</b> </a></td></tr>\r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<form action=\"/TwadWeb/downloadImage.do\" id=\"imageFormId\" method=\"post\">\r\n");
      out.write("\r\n");
      out.write("<input type=\"hidden\" id=\"imageString\" name=\"imageString\" value=\"\"/>\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t<div class=\"loginDialog\" id=\"uploadDialog\">\r\n");
      out.write("<img class=\"imgClose\" src=\"library/img/Close_SMS.png\" style=\"width:40px;border-width:0px;float: right; margin-top: -6px; margin-right: -5px; cursor: pointer;\">\r\n");
      out.write("\t\t<table width=\"100%\" align=\"center\"><tr><td align=\"center\">\t\r\n");
      out.write("\t<form id=\"uploadFormId\" method=\"POST\" enctype=\"multipart/form-data\">\r\n");
      out.write("\t<span id=\"successMessage\" style=\"color:green;\"></span>\r\n");
      out.write("\t<span id=\"errorMessage\" style=\"color:red;\"></span>\r\n");
      out.write("\t\t<br/><br/>\r\n");
      out.write("\t\t<div id=\"clonedDiv\">\r\n");
      out.write("\t\t<div id=\"fileCloneId\" >\r\n");
      out.write("\t\t<input type=\"file\" name=\"file\"><img src=\"library/img/add.JPG\" width=\"25px\" height=\"25px\" class=\"addFileId\"/><br /> \r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<input type=\"hidden\" name=\"appId\" value='");
      out.print(request.getParameter("applicationRef"));
      out.write("'><br /> \r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<input type=\"submit\" id=\"btnSubmit\" value=\"Upload\">\r\n");
      out.write("\t</form>\r\n");
      out.write("\t</td></tr></table>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("      <script>\r\n");
      out.write("          $(function(){\r\n");
      out.write("        \t var count=0;\r\n");
      out.write("        \t $('#clonedDiv').on('click','.addFileId',function(){\r\n");
      out.write("        \t\t  var cloned = $('#fileCloneId').clone();\r\n");
      out.write("        \t\t  cloned.find('img.deleteFileId').remove();\r\n");
      out.write("        \t\t  cloned.attr('id','fileCloneId_'+count++);\r\n");
      out.write("        \t\t  cloned.css({'margin-left': '25px'});\r\n");
      out.write("          \t\t cloned.find('.addFileId').after('<img src=\"library/img/delete.JPG\" width=\"25px\" height=\"25px\" class=\"deleteFileId\"/>');\r\n");
      out.write("        \t\t $('#clonedDiv').append(cloned);\r\n");
      out.write("        \t\t \r\n");
      out.write("        \t\t \r\n");
      out.write("        \t  });\r\n");
      out.write("        \t $('#clonedDiv').on('click','.deleteFileId',function(){\r\n");
      out.write("        \t\t  $(this).parent('div').remove();  \r\n");
      out.write("        \t\t});\r\n");
      out.write("        \t  \r\n");
      out.write("        \t  $(\"#btnSubmit\").click(function (event) {\r\n");
      out.write("        \t\t  event.preventDefault();\r\n");
      out.write("         \t\t var flag = true;\r\n");
      out.write("         \t\t  $('input[name=file]').each(function(){\r\n");
      out.write("         \t\t\t \r\n");
      out.write("         \t\t\t  if($(this).val().length==0){\r\n");
      out.write("         \t\t\t\t $('#errorMessage').text(\"Add File or remove the extra file Upload\");\r\n");
      out.write("         \t\t\t\t  flag = false;\r\n");
      out.write("         \t\t\t  }\r\n");
      out.write("         \t\t\t\r\n");
      out.write("         \t\t  });\r\n");
      out.write("         \t\t  \r\n");
      out.write("        \t\t  if(flag){\r\n");
      out.write("        \t       \r\n");
      out.write("        \t        var form = $('#uploadFormId')[0];\r\n");
      out.write("        \t        var data = new FormData(form);\r\n");
      out.write("        \t        $(\"#btnSubmit\").prop(\"disabled\", true);\r\n");
      out.write("\r\n");
      out.write("        \t        $.ajax({\r\n");
      out.write("        \t            type: \"POST\",\r\n");
      out.write("        \t            enctype: 'multipart/form-data',\r\n");
      out.write("        \t            url: \"uploadMultipleFile.do\",\r\n");
      out.write("        \t            data: data,\r\n");
      out.write("        \t            processData: false,\r\n");
      out.write("        \t            contentType: false,\r\n");
      out.write("        \t            cache: false,\r\n");
      out.write("        \t            timeout: 600000,\r\n");
      out.write("        \t            success: function (data) {\r\n");
      out.write("        \t            \t$('#successMessage').text(data);\r\n");
      out.write("        \t                $(\"#btnSubmit\").prop(\"disabled\", false);\r\n");
      out.write("\r\n");
      out.write("        \t            },\r\n");
      out.write("        \t        });\r\n");
      out.write("        \t\t  }\r\n");
      out.write("        \t  });\r\n");
      out.write("        \t\r\n");
      out.write("           $('#load').click(function(){ //calling this function when Save button pressed\r\n");
      out.write("              html2canvas($('#ackDiv'), {//give the div id whose image you want in my case this is #cont\r\n");
      out.write("              onrendered: function (canvas) {\r\n");
      out.write("              var base64_string = canvas.toDataURL(\"image/png\",1.0);//here set the image extension and now image data is in var img that will send by our ajax call to our api or server site page\r\n");
      out.write("\r\n");
      out.write("             $('#imageString').val(base64_string);\r\n");
      out.write("              $('#imageFormId').submit();\r\n");
      out.write("              \r\n");
      out.write("             \r\n");
      out.write("              }\r\n");
      out.write("              });\r\n");
      out.write("          });\r\n");
      out.write("         \r\n");
      out.write("           $('.loginDialog ').parent('div').css({'width':'500px','top':'500px'});\r\n");
      out.write("   \t\t$('.imgClose').click(function(){\r\n");
      out.write("   \t\t\t$(this).parents('div.loginDialog').dialog('close');\r\n");
      out.write("   \t\t});\r\n");
      out.write("   \t $('#uploadbtn').click(function(){\r\n");
      out.write("   \t\t\t$(\".ui-dialog-content\").dialog(\"close\");\r\n");
      out.write("   \t\t\t$( \"#uploadDialog\" ).dialog({ 'width':'500px','modal':'true'});\r\n");
      out.write("   \t\t}); \r\n");
      out.write("   \t  \r\n");
      out.write("           \r\n");
      out.write("        });\r\n");
      out.write("      </script> \r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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