<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Cisco Project Management Portal - Activate Users</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="./CommonJsp/style.css" type="text/css" />
<link rel="stylesheet" href="./CommonJsp/css/styles.css" type="text/css" />
<link rel="stylesheet" href="./CommonJsp/css/jquery-tool.css"
	type="text/css" />

<script type="text/javascript" src="./CommonJsp/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="./CommonJsp/js/jquery.tools.min.js"></script>
<script type="text/javascript" src="./CommonJsp/js/main.js"></script>

<script type="text/javascript" src="./CommonJsp/js/cufon-yui.js"></script>
<script type="text/javascript" src="./CommonJsp/js/arial.js"></script>
<script type="text/javascript" src="./CommonJsp/js/cuf_run.js"></script>
</head>
<body>
	<!-- START PAGE SOURCE -->
	<div class="main">
		<%@ include file="../CommonJsp/CiscoLogo.jsp"%>
		<%@ include file="../AdminJsp/AdminHeader.jsp"%>
		<p align="right" style="padding-right:13%; color:gray; font-size: 15px;"> Welcome <c:out value="${username}"></c:out> | <a href="./UserServlet?action=Logout">Log Out</a></p>
		<div class="content">
			<div class="content_resize">
				<div class="mainbar">
					<div class="article">
						
						
							
							
					
					
						<fieldset>
						<p align="center">${activeMessage}</p>
							<legend class="text7">Activate User</legend>
							<br />
							<table id="customers">
								<tr>
									<th class="textTableHeader">Username</th>
									<th class="textTableHeader">Cisco Username</th>
									<th class="textTableHeader">Name</th>
									<th class="textTableHeader">Capgemini Manager Name</th>
									<th class="textTableHeader"></th>
								</tr>
								
									<c:forEach items="${userList}" var="user">
									
										<c:set var="count" value="${count + 1}" scope="page" />
										<tr>
											<td>${user.username}</td>
											<td>${user.ciscoUsername}</td>
											<td>${user.firstName} ${user.lastName}</td>
											<td>${user.capgeminiManagerName}</td>
											<td><a href="./UserServlet?action=ActivateUserServlet&username=${user.username}">Activate</a></td>
										</tr>
									</c:forEach>
						
								
							</table>
							<br />
						</fieldset>
					</div>
				</div>
				<%@ include file="AdminSidebar.jsp"%>
			</div>

			<div class="clr"></div>
			<%@ include file="../CommonJsp/Footer.jsp"%>

		</div>
	</div>
	<!-- END PAGE SOURCE -->
</body>
</html>
