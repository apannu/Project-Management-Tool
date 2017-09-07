<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Cisco Project Management Portal - View Project Details</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link href="./CommonJsp/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="./CommonJsp/css/styles.css" type="text/css" />
<link rel="stylesheet" href="./CommonJsp/css/jquery-tool.css" type="text/css" />

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
		<%@ include file="UserHeader.jsp"%>
		<p align="right" style="padding-right:13%; color:gray; font-size: 15px;"> Welcome <c:out value="${username}"></c:out> | <a href="./UserServlet?action=Logout">Log Out</a></p>
  <div class="content">
    <div class="content_resize">
      <div class="mainbar">
        <div class="article">
	        <fieldset><legend class="text7">Project Details</legend>
					<form action="./UserServlet?action=UpdateProjectDetails" method="post" name="ViewUserDetailsForm">	
						<table>
							<c:forEach items="${projectDetailsList}" var="project">
							<tr>
									<td><label class="text11">Project Code:</label></td>
									<td class="text5">${project.project_code}</td>
							</tr>
							<tr>
									<td><label class="text11">Project Name:</label>
									<td class="text5">${project.projectName}</td>
							</tr>	
							<tr>
									<td><label class="text11">Objectives:</label>
									<td class="text5">${project.projectObjective}</td>
							</tr>
							<tr>
									<td><label class="text11">Project Manager:</label>
									<td class="text5">${project.projectManager}</td>
							</tr>
							<tr>
									<td><label class="text11">Time of Completion:</label></td>
									<td class="text5">${project.timeOfCompletion}</td>
							</tr>
							<tr>
									<td><label class="text11">Number of modules:</label>
									<td class="text5">${project.noOfModules}</td>
							</tr>	
							<tr>
									
									<td><label class="text11">Location:</label>
									<c:forEach items="${locationList}" var="project1">
										<td class="text5">${project1.locationName}</td>
									</c:forEach>
							<tr>
									<td><label class="text11">Start Date:</label></td>
									<td class="text5">${project.startDate}</td>
							</tr>
							<tr>
									<td><label class="text11">End Date:</label>
									<td class="text5">${project.endDate}</td>
							</tr>	
							<tr>
									<td><label class="text11">Technical Resources:</label>
									<td class="text5">${project.noOfTechnicalResources}</td>
							</tr>
							<tr>
									<td><label class="text11">Non Technical Resources:</label>
									<td class="text5">${project.noOfNonTechnicalResources}</td>
							</tr>	
							<tr>
									<td><label class="text11">Status:</label>
									<td class="text5">${project.status}</td>
							</tr>	
							<tr>
									<td><label class="text11">Created By:</label>
									<td class="text5"><a href="./UserServlet?action=ViewUserDetailsServlet&userType=nonAdmin&username=${project.createUserId}">${project.createUserId}</a></td>
							</tr>
							<tr>
									<td><label class="text11">Created Date:</label>
									<td class="text5">${project.createDate}</td>
							</tr>
							<tr>
									<td><label class="text11">Last Modified By:</label>
									<td class="text5"><a href="./UserServlet?action=ViewUserDetailsServlet&userType=nonAdmin&username=${project.modifyUserId}">${project.modifyUserId}</a></td>
							</tr>
							<tr>
									<td><label class="text11">Last Modified Date:</label>
									<td class="text5">${project.modifydate}</td>
							</tr>
							</c:forEach>
						</table>
						<br/>
							<c:forEach items="${projectDetailsList}" var="project">
								<c:if test="${project.projectManager==username}">
									<input type="submit" value="Edit" >
								</c:if>
							</c:forEach>
						<br/>
					</form>
			</fieldset>	
			<br/>
			<fieldset>
				<legend class="text7">Users Tagged</legend>
				<br/>
				<table id="customers">
				<c:if test="${empty managerDetailsList}">No Users Tagged</c:if>
				<c:if test="${not empty managerDetailsList}">
					<tr>
						<th class="textTableHeader">Username</th>
						<th class="textTableHeader">Start Date</th>
						<th class="textTableHeader">End Date</th>
						<th class="textTableHeader">User Role</th>
					</tr>
					<c:forEach items="${managerDetailsList}" var="manager">	
						<tr>
								<td><a href="./UserServlet?action=ViewUserDetailsServlet&userType=nonAdmin&username=${manager.username}">${manager.firstName} ${manager.lastName}(${manager.username})</a></td>
								<td>${manager.startDate}</td>
								<td>${manager.endDate}</td>
								<td>${manager.projectRoleName}</td>
		 				</tr>
					</c:forEach>
					<c:forEach items="${userDetailsList}" var="user">	
						<tr>
								<td><a href="./UserServlet?action=ViewUserDetailsServlet&userType=nonAdmin&username=${user.username}">${user.firstName} ${user.lastName}(${user.username})</a></td>
								<td>${user.startDate}</td>
								<td>${user.endDate}</td>
								<td>${user.projectRoleName}</td>
		 				</tr>
	 				</c:forEach>
	 			</c:if>
				</table>
				<br>
				</fieldset>
				<br/>
				<fieldset>
				<legend class="text7">Related Files</legend>
				<br/>
				<table id="customers">
										<c:if test="${empty projectFilesList}">No Files</c:if>
										<c:if test="${not empty projectFilesList}">
										<tr>
											<th class="textTableHeader">File Name</th>
											<th class="textTableHeader">Uploaded By</th>
											<th class="textTableHeader">Uploaded Date</th>
											<th class="textTableHeader">File Group</th>
											<th class="textTableHeader">File Size(in KB)</th>
											<th></th>
										</tr>
											
											<c:forEach items="${projectFilesList}" var="file">
												<tr>
													<td>${file.fileName}</td>
													<td>${file.createUserId}</td>
													<td>${file.uploadedDate}</td>
													<td>${file.documentGroup}</td>
													<td>${file.fileSize}</td>
													<td><a href="./UserServlet?action=DownloadFile&fileName=${file.fileName}">Download</a></td>
												</tr>
											</c:forEach>
										</c:if>
				</table>
				</fieldset>
				<br/>
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
