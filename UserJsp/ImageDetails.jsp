<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Cisco Project Management Portal - Image Details</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="./CommonJsp/style.css" type="text/css" />
<link rel="stylesheet" href="./CommonJsp/css/styles.css" type="text/css" />
<link rel="stylesheet" href="./CommonJsp/css/jquery-tool.css" type="text/css" />
<link rel="stylesheet" type="text/css" media="all" href="jsdatepick-calendar/jsDatePick_ltr.min.css" />

<script type="text/javascript" src="./CommonJsp/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="./CommonJsp/js/jquery.tools.min.js"></script>
<script type="text/javascript" src="./CommonJsp/js/main.js"></script>

<script type="text/javascript" src="./CommonJsp/js/cufon-yui.js"></script>
<script type="text/javascript" src="./CommonJsp/js/arial.js"></script>
<script type="text/javascript" src="./CommonJsp/js/cuf_run.js"></script>
<script type="text/javascript" src="jsdatepick-calendar/jsDatePick.min.1.3.js"></script>
<script type="text/javascript" src="validations/externalJavascript.js"></script>
<script type="text/javascript" src="validations/DateCalender.js"></script>
</head>
<body>
<!-- START PAGE SOURCE -->
<div class="main">
 <%@ include file="../CommonJsp/CiscoLogo.jsp"%>
		<%@ include file="UserHeader.jsp"%>
		<p align="right" style="padding-right:13%; color:gray; font-size: 15px;"> Welcome <c:out value="${username}"></c:out> | <a href="./UserServlet?action=Logout">Log Out</a></p>
  <div class="content">
    <div class="content_resize">
      <div class="mainbar">
        <div class="article">
        <fieldset>
	<legend class="text7">Document Details:</legend>
			<br/>
			<form action="UserServlet?action=GetImageDetails&userType=nonAdmin" method="post" name="fileDetailsForm">	
				<table>
					<tr>
						<td><label class="text11">Description:</label>
						<td><input type="text" name="description"></td>
					</tr>
					<tr>
						<td><label class="text11">Upload Date:</label>
						<td><input type="text" name="uploadDate" id="uploadDate" onfocus="calender(uploadDate.name)"></td>
					</tr>
				</table>
					<br/>
					<input type="submit" value="Select File" class="button fright">${fileUploadMessage}
			</form>
			</fieldset>
        </div>
      </div>
    <%@ include file="UserSidebar.jsp"%>
  </div>
  
  <div class="clr"></div>
  <%@ include file="../CommonJsp/Footer.jsp"%>
  
</div>
</div>
<!-- END PAGE SOURCE -->
</body>
</html>
