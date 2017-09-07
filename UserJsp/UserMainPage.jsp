<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Cisco Project Management Portal-User Main Page</title>
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

						 <fieldset><legend class="text7">Personal Details</legend>
					<form action="./UserServlet?action=UpdateUserJsp" method="post" name="updateUserDetailsForm">	
						<table>
							<tr>
								<c:forEach items="${list1}" var="list">
									<td><label for="employeeId" class="text11">Employee Id:</label></td>
									<td class="text5">${list.employeeId}</td>
								</c:forEach>
							</tr>
							<tr>
								<c:forEach items="${list1}" var="list">
									<td><label for="capgeminiUserName" class="text11">Capgemini User Name:</label>
									<td class="text5">${list.username}</td>
								</c:forEach>
							</tr>	
							<tr>
								<c:forEach items="${list1}" var="list">
									<td><label for="ciscoUserName" class="text11">Cisco User Name:</label>
									<c:if test="${list.ciscoUsername!=null}"><td class="text5">${list.ciscoUsername}</td></c:if>
									<c:if test="${list.ciscoUsername==null}"><td>No User Name available</td></c:if>
								</c:forEach>
							</tr>
							<tr>
								<c:forEach items="${list1}" var="list">	
									<td><label for="firstName" class="text11">Name:</label>
									<td class="text5">${list.firstName} ${list.lastName}</td>
								</c:forEach>	
							</tr>
							<tr>
								<c:forEach items="${list1}" var="list">	
									<td><label for="designation" class="text11">Designation:</label>
									<td class="text5">${list.designation}</td>
								</c:forEach>
							</tr>
							<tr>
								<c:forEach items="${list2}" var="list">	
									<td><label for="locationName" class="text11">Location:</label>
									<td class="text5">${list.locationName}</td>
								</c:forEach>
							</tr>
							<tr>
								<c:forEach items="${list1}" var="list">	
									<td><label for="ciscoEmailId" class="text11">Cisco Email ID:</label></td>
									<td class="text5">${list.ciscoEmailId}</td>
								</c:forEach>
							</tr>
							<tr>
								<c:forEach items="${list1}" var="list">	
									<td><label for="capgeminiEmailId" class="text11">Capgemini Email ID:</label>
									<td class="text5">${list.capgeminiEmailId}</td>
								</c:forEach>
							</tr>	
							<tr>
								<c:forEach items="${list1}" var="list">	
									<td><label for="primaryContactNo" class="text11">Primary Contact No:</label>
									<td class="text5">${list.primaryContactNumber}</td>
								</c:forEach>
							<tr>
								<c:forEach items="${list1}" var="list">	
									<td><label for="emergencyContactNo" class="text11">Emergency Contact No:</label></td>
									<td class="text5">${list.emergencyContactNumber}</td>
								</c:forEach>
							</tr>
							<tr>
								<c:forEach items="${list1}" var="list">	
									<td><label for="ciscoDOJ" class="text11">Cisco DOJ:</label>
									<td class="text5">${list.ciscoDOJ}</td>
								</c:forEach>	
							</tr>	
							<tr>
								<c:forEach items="${list1}" var="list">	
									<td><label for="primarySkill" class="text11">Primary Skill:</label>
									<td class="text5">${list.primarySkill}</td>
								</c:forEach>
							</tr>
							<tr>
								<c:forEach items="${list1}" var="list">	
									<td><label for="secondarySkill1" class="text11">Secondary Skill 1:</label>
									<td class="text5"><c:if test="${empty list.secondarySkill_01}">No Skill</c:if>${list.secondarySkill_01}</td>
								</c:forEach>
							</tr>
							<tr>
								<c:forEach items="${list1}" var="list">	
									<td><label for="secondarySkill2" class="text11">Secondary Skill 2:</label>
									<td class="text5"><c:if test="${empty list.secondarySkill_02}">No Skill</c:if>${list.secondarySkill_02}</td>
								</c:forEach>
							</tr>
							<tr>
							<td><label class="text11">N+1:</label></td>
								<c:forEach items="${list1}" var="list1">
								<c:forEach items="${list4}" var="list4">
									<c:if test="${list1.capgeminiManagerName==list1.username}">
										<td class="text5">Not Available</td>
									</c:if>
									<c:if test="${list1.capgeminiManagerName!=list1.username}">
										<td class="text5"><a href="./UserServlet?action=ViewUserDetailsServlet&userType=admin&username=${list1.capgeminiManagerName}">${list4.firstName} ${list4.lastName}(${list1.capgeminiManagerName})</a></td>
									</c:if>
								</c:forEach>
								</c:forEach>
							</tr>	
							<tr>
							<td><label class="text11">N+2:</label></td>
								<c:forEach items="${list4}" var="list4">
								<c:forEach items="${list6}" var="list6">
									<c:if test="${list4.managerName2==list4.username}">
										<td class="text5">Not Available</td>
									</c:if>
									<c:if test="${list4.managerName2!=list4.username}">
										<td class="text5"><a href="./UserServlet?action=ViewUserDetailsServlet&userType=admin&username=${list4.managerName2}">${list6.firstName} ${list6.lastName}(${list4.managerName2})</a></td>
									</c:if>
								</c:forEach>
								</c:forEach>
							</tr>	
									
							<tr>
								<c:forEach items="${list1}" var="list">
									<td><label class="text11">Created By:</label>
									<td class="text5"><a href="./UserServlet?action=ViewUserDetailsServlet&userType=admin&username=${list.createUserId}">${list.createUserId}</a></td>
								</c:forEach>
							</tr>
							<tr>
								<c:forEach items="${list1}" var="list">
									<td><label class="text11">Created Date:</label>
									<td class="text5">${list.createDate}</td>
								</c:forEach>
							</tr>
							<tr>
								<c:forEach items="${list1}" var="list">
									<td><label class="text11">Last Modified By:</label>
									<td class="text5"><a href="./UserServlet?action=ViewUserDetailsServlet&userType=admin&username=${list.modifyUserId}">${list.modifyUserId}</a></td>
								</c:forEach>
							</tr>
							<tr>
								<c:forEach items="${list1}" var="list">
									<td><label class="text11">Last Modified Date:</label>
									<td class="text5">${list.modifydate}</td>
								</c:forEach>
					</tr>
						</table>
						
						<br/>
						<input type="submit" value="Edit" name="submit"/>
					</form>
					
			</fieldset>	
			<br/>
			<fieldset>
				<legend class="text7">Project Information</legend>
				<br/>
				<table id="customers">
					<tr>
						<th class="textTableHeader">Project Name</th>
						<th class="textTableHeader">Project Manager</th>
						<th class="textTableHeader">Start Date</th>
						<th class="textTableHeader">End Date</th>
						<th class="textTableHeader">User Role</th>
						<c:forEach items="${list1}" var="list1"><c:if test="${list1.isBillable=='N'}">No Tagging available for ${list1.username}</c:if></c:forEach>
						
					</tr>
					<tr>
					
					<c:forEach items="${list2}" var="list2">
						<c:forEach items="${list3}" var="list3">	
								<td><a href="./UserServlet?action=ViewProjectDetailsServlet&userType=admin&projectName=${list3.projectName}">${list3.projectName}(${list2.project_code})</a></td>
								<td><a href="./UserServlet?action=ViewUserDetailsServlet&userType=admin&username=${list3.projectManager}">${list3.firstName} ${list3.lastName}(${list3.projectManager})</a></td>
						</c:forEach>
					</c:forEach>
					<c:forEach items="${list1}" var="list1">	
					<c:forEach items="${list2}" var="list2">	
						<c:forEach items="${list7}" var="list7">	
							<td>${list2.startDate}</td>
							<td>${list2.endDate}</td>
							<td>${list7.projectRoleName}</td>
						</c:forEach>
					</c:forEach>
					</c:forEach>
					</tr>
				</table>
				<br/>
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
