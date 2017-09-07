<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Cisco Project Management Portal - Grant/Revoke Admin Rights</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<link rel="stylesheet" href="./CommonJsp/style.css" type="text/css" />
<link rel="stylesheet" href="./CommonJsp/css/styles.css" type="text/css" />
<link rel="stylesheet" href="./CommonJsp/css/jquery-tool.css" type="text/css" />

<script type="text/javascript" src="./CommonJsp/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="./CommonJsp/js/jquery.tools.min.js"></script>
<script type="text/javascript" src="./CommonJsp/js/main.js"></script>
<script type="text/javascript" src="./CommonJsp/js/cufon-yui.js"></script>
<script type="text/javascript" src="./CommonJsp/js/arial.js"></script>
<script type="text/javascript" src="./CommonJsp/js/cuf_run.js"></script>
<script type="text/javascript" src="validations/externalJavascript.js"></script>

</head>
<body>
<!-- START PAGE SOURCE -->
<div class="main">
  <%@ include file="../CommonJsp/CiscoLogo.jsp"%>
		<%@ include file="AdminHeader.jsp"%>
		<p align="right" style="padding-right:13%; color:gray; font-size: 15px;"> Welcome <c:out value="${username}"></c:out> | <a href="./UserServlet?action=Logout">Log Out</a></p>
  <div class="content">
    <div class="content_resize">
      <div class="mainbar">
        <div class="article">
        <fieldset><legend class="text7">Grant Admin Rights</legend>
			<br/>
			<form  method="post" action="./UserServlet?action=AdminRightsServlet&action1=grant" name="grantAdminRightsForm">
				<table>
					<tr>
						<td><label class="text11">Non-Admin Users:</label></td>
						<td>
							<select name="nonAdminUsers">
								<c:forEach items="${nonAdminUsersList}" var="user">
									<option value="${user.username}">${user.username}</option>
								</c:forEach>
							</select>
							&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type="submit" value="Grant">
						</td>
					</tr>
					</table>
					<br/>
					</form>
			</fieldset>
			<br/>
			<fieldset><legend class="text7">Revoke Admin Rights</legend>
			<br/>
			<form  method="post" action="./UserServlet?action=AdminRightsServlet&action1=revoke" name="revokeAdminRightsForm">
				<table>
					<tr>
						<td><label class="text11">Admin Users:</label></td>
						<td>
							<select name="adminUsers">
								<c:forEach items="${adminUsersList}" var="user">
									<option value="${user.username}">${user.username}</option>
								</c:forEach>
							</select>
							&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type="submit" value="Revoke">
						</td>
					</tr>
					</table>
					<br/>
					</form>
			</fieldset>
			<br/>
			<fieldset><legend class="text7"></legend>
			<b>${adminRightsMessage}</b>
			</fieldset>
        </div>
      </div>
    <%@ include file="AdminSidebar.jsp" %>
  </div>
  
  <div class="clr"></div>
  <%@ include file="../CommonJsp/Footer.jsp"%>
  
</div>
</div>
<!-- END PAGE SOURCE -->
</body>
</html>
