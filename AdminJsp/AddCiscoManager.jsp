<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Cisco Project Management Portal - Add Cisco Manager</title>
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
<script type="text/javascript">
	function showAddManagerForm() {
		document.getElementById("addManager").style.visibility = "visible";
	}
	function hideAddManagerForm() {
		document.getElementById("addManager").style.visibility = "hidden";
	}
</script>
</head>
<body onload="hideAddManagerForm()">
	<!-- START PAGE SOURCE -->
	<div class="main">
		<%@ include file="../CommonJsp/CiscoLogo.jsp"%>
		<%@ include file="AdminHeader.jsp"%>
		<p align="right"
			style="padding-right: 13%; color: gray; font-size: 15px;">
			Welcome
			<c:out value="${username}"></c:out>
			| <a href="./UserServlet?action=Logout">Log Out</a>
		</p>
		<div class="content">
			<div class="content_resize">
				<div class="mainbar">
					<div class="article">
						<fieldset>
							<legend class="text7">Available Cisco Managers</legend>
							<br />
							<p align="center">${message}</p>
							<form id="addManager_form" action="" method="post"
								name="addManager_form">
								<table id="customers">
									<tr>
										<th class="textTableHeader"></th>
										<th class="textTableHeader">Username</th>
										<th class="textTableHeader">Manager Name</th>
									</tr>

									<c:forEach items="${managerList}" var="manager">
										<c:set var="count" value="${count + 1}" scope="page" />
										<tr>
											<td>${count}</td>
											<td>${manager.username}</td>
											<td>${manager.firstName} ${manager.lastName}</td>
										
										</tr>
									</c:forEach>
								</table>

								<br /> <input type="button" value="Add New Manager"
									class="button fright" onclick="showAddManagerForm()">
							</form>
						</fieldset>
						<br/>
						
						<fieldset name="addManager" id="addManager">
							<legend class="text7">Add New Manager</legend>
							<br />
							<form id="addManager_form2" action="./UserServlet?action=AddCiscoManagerServlet" method="post" name="addManager_form2">
								<table id="customers">
									<tr>
										<th class="textTableHeader"></th>
										<th class="textTableHeader">Manager Information</th>
									</tr>
									<tr>
										<td>Username</td>
										<td><input type="text" name="username"></td>
									</tr>
									
									<tr>
										<td>Manager First Name</td>
										<td><input type="text" name="firstName"></td>
									</tr>
									<tr>
										<td>Manager Last Name</td>
										<td><input type="text" name="lastName"></td>
									</tr>
									
								</table>

								<br /> <input type="submit" id="submit" value="Submit"
									class="button fright">
							</form>
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
