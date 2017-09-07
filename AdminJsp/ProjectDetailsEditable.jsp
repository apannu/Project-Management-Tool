<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Cisco Project Management Portal - Edit Project Details</title>
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
<script type="text/javascript" src="validations/DateCalender.js"></script>

</head>
<body>
	<!-- START PAGE SOURCE -->
	<div class="main">
		<%@ include file="../CommonJsp/CiscoLogo.jsp"%>
		<%@ include file="AdminHeader.jsp"%>
		<div class="content">
			<div class="content_resize">
				<div class="mainbar">
					<div class="article">
						<fieldset>
							<legend class="text7">Edit Project Details</legend>
							<form action="./UserServlet?action=UpdateProjectAdmin" method="post" name="EditProjectDetailsForm">
								<table>
									<c:forEach items="${projectDetailsList}" var="project">
										<tr>
											<td><label class="text11">Project Code:</label></td>
											<td class="text5">${project.project_code}</td>
											<td><input type="hidden" name="projectCode" value="${project.project_code}"></td>
										</tr>
										<tr>
											<td><label class="text11">Project Name:</label>
											<td class="text5"><input type="text" name="projectName"
												value="${project.projectName}">
											</td>
										</tr>
										<tr>
											<td><label class="text11">Objectives:</label>
											<td class="text5"><input type="text"
												name="projectObjective" id="projectObjective"
												value="${project.projectObjective}">
											</td>
										</tr>
										<tr>
											<td><label class="text11">Project Manager:</label></td>
											<td class="text5"><select name="projectManager">
													<option value="${project.projectManager}" selected>${project.projectManager}</option>
													<c:forEach items="${projectManagerList}" var="user">
														<c:if test="${project.projectManager!=user.username}">
															<option value="${user.username}">${user.username}</option>
														</c:if>
													</c:forEach>
											</select>
											</td>
										</tr>
										<tr>
											<td><label class="text11">Time of Completion:</label>
											</td>
											<td class="text5"><input type="text"
												name="completionTime" id="completionTime"
												value="${project.timeOfCompletion}">
											</td>
										</tr>
										<tr>
											<td><label class="text11">Number of modules:</label>
											<td class="text5"><input type="text" name="modules"
												id="modules" value="${project.noOfModules}">
											</td>
										</tr>
										<tr>
											<c:forEach items="${locationList}" var="project1">
												<td><label class="text11">Location:</label>
												</td>
												<td class="text5"><select name="locationName">

														<option value="${project1.locationName}" selected>${project1.locationName}</option>
											
											<c:forEach items="${locationNamesList}" var="user">
												<c:if test="${project1.locationName!=user.locationName}">
													<option value="${user.locationName}">${user.locationName}</option>
												</c:if>
											</c:forEach>
											</select>
											</td>
											</c:forEach>
										</tr>
										<tr>
											<td><label class="text11">Start Date:</label>
											</td>
											<td class="text5">${project.startDate}</td>
										</tr>
										<tr>
											<td><label class="text11">Technical Resources:</label>
											<td class="text5"><input type="text"
												name="noOfTechnicalResources" id="noOfTechnicalResources"
												value="${project.noOfTechnicalResources}">
											</td>
										</tr>
										<tr>
											<td><label class="text11">Non Technical
													Resources:</label>
											<td class="text5"><input type="text"
												name="noOfNonTechnicalResources"
												id="noOfNonTechnicalResources"
												value="${project.noOfNonTechnicalResources}">
											</td>
										</tr>
										<tr>
											<td><label class="text11">Status:</label>
											<td class="text5"><select name="projectStatus">
													<option value="${project.status}">${project.status}</option>
													<option value="Completed">Completed</option>
											</select></td>
										</tr>
									</c:forEach>
								</table>
								<br />
								<input type="submit" value="Update">
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
