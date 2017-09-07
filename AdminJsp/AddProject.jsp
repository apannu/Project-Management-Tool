<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Cisco Project Management Portal - Add Project</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<link rel="stylesheet" href="./CommonJsp/style.css" type="text/css" />
<link rel="stylesheet" href="./CommonJsp/css/styles.css" type="text/css" />
<link rel="stylesheet" href="./CommonJsp/css/jquery-tool.css" type="text/css" />
<link rel="stylesheet" type="text/css" media="all" href="jsdatepick-calendar/jsDatePick_ltr.min.css" />

<script type="text/javascript" src="./CommonJsp/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="./CommonJsp/js/jquery.tools.min.js"></script>
<script type="text/javascript" src="./CommonJsp/js/main.js"></script>
<script type="text/javascript" src="jsdatepick-calendar/jsDatePick.min.1.3.js"></script>
<script type="text/javascript" src="./CommonJsp/js/cufon-yui.js"></script>
<script type="text/javascript" src="./CommonJsp/js/arial.js"></script>
<script type="text/javascript" src="./CommonJsp/js/cuf_run.js"></script>
<script type="text/javascript" src="validations/externalJavascript.js"></script>
<script type="text/javascript" src="validations/DateCalender.js"></script>
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
        <fieldset>
	<legend class="text7">Add Project</legend>
	<b>Field names ending with * are mandatory</b>
			<br/>
			<br/>
			<form action="./UserServlet?action=AddProjectServlet" method="post" name="addProjectForm" id="addProjectForm">	
				<table>
					<tr>
						<td><label class="text11">Project Code:</label></td>
						<td><input type="text" name="projectCode" id="projectCode1"></td>
					</tr>
					<tr>
						<td><label class="text11">Project Name:</label>
						<td><input type="text" name="projectName" id="projectName"></td>
					</tr>	
					<tr>
						<td><label class="text11">Project Objective:</label>
						<td><input type="text" name="projectObjective" id="projectObjective" ></td>
					</tr>	
					<tr>
						<td><label class="text11">Project Manager:</label>
						<td>
								<select name="projectManager">
									<c:forEach items="${projectManagerList}" var="user">
										<option value="${user.username}">${user.username}</option>
									</c:forEach>							
								</select>
						</td>
					</tr>	
					<tr>
						<td><label class="text11">Completion Time:</label>
						<td><input type="text" name="completionTime" id="completionTime"></td>
					</tr>
					<tr>
						<td><label class="text11">Number of modules:</label>
						<td><input type="text" name="modules" id="modules"></td>
					</tr>
					<tr>
						<td><label class="text11">Location:</label>
						<td>
							<select name="location">
							<c:forEach items="${locationNamesList}" var="user">
							<option value="${user.locationName}">${user.locationName}</option>
							</c:forEach>							
							</select>
						</td>
					</tr>	
					<tr>
						<td><label class="text11">Start Date:</label>
						<td><input type="text" name="startDate" id="startDate" onfocus="calender(startDate.name);"></td>
					</tr>
					<tr>
						<td><label class="text11">End Date:</label>
						<td><input type="text" name="endDate" id="endDate" onfocus="calender(endDate.name);"></td>
					</tr>
					<tr>
						<td><label class="text11">Number of Technical Resources:</label></td>
						<td><input type="text" name="noOfTechnicalResources" id="noOfTechnicalResources"></td>
					</tr>
					<tr>
						<td><label class="text11">Number of Non-Technical Resources:</label>
						<td><input type="text" name="noOfNonTechnicalResources" id="noOfNonTechnicalResources"></td>
					</tr>	
					</table>
					<br/>
						${addProjectMessage}
						<input type="submit" id="submit" value="Add" class="button fright" onclick="return addProjectValidations();">
			</form>
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
