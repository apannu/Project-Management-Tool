<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Cisco Project Management Portal</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link href="./style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="./css/styles.css" type="text/css" />
<link rel="stylesheet" href="./css/jquery-tool.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="./css/loginstyle.css" />


<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/jquery.tools.min.js"></script>
<script type="text/javascript" src="js/main.js"></script>

<script type="text/javascript" src="js/cufon-yui.js"></script>
<script type="text/javascript" src="js/arial.js"></script>
<script type="text/javascript" src="js/cuf_run.js"></script>

</head>
<body>
	<div class="main">
  <%@ include file="CiscoLogo.jsp" %>
  <%@ include file="../UserJsp/UserHeader.jsp" %>
  <p align="right" style="padding-right:13%; color:gray; font-size: 15px;"> Welcome <c:out value="${username}"></c:out> | <a href="../LoginPage.jsp">Log Out</a></p>
  <div class="content">
    <div class="content_resize">
      <div class="mainbar">
        <div class="article">
        <fieldset>
	<legend class="text7">Reset Password</legend></center>
			<br/>
			<center><c:out value="${message}"></c:out></center>
			<br/>
			<form id="resetPassword_form" class="resetPassword_form" action="./UserServlet?action=ResetPassword" method="post" name="resetPassword_form">	
				<table>
					<tr>
						<td><label for="oldPassword" class="text11">Enter your current password:</label></td>
						<td><input type="password" name="oldPassword" id="oldPassword"/></td>
					</tr>
					<tr>
						<td><label for="newPassword" class="text11">Enter new password:</label></td>
						<td><input type="password" name="newPassword" id="newPassword"/></td>
					</tr>	
					<tr>
						<td><label for="confirmPassword" class="text11">Confirm new password:</label></td>
						<td><input type="password" name="confirmPassword" id="confirmPassword"/></td>
					</tr>	
					
					</table>
					<br/>
						<input type="submit" id="submit" value="Submit" class="button fright"/>
						<input type="reset" id="reset" value="Reset" class="button fright"/>
			</form>
			</fieldset>
        </div>
      </div>
    <%@ include file="../UserJsp/UserSidebar.jsp" %>
  </div>
  
  <div class="clr"></div>
  <%@ include file="Footer.jsp" %>
  
</div>
</div>
</body>
</html>
