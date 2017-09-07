<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Cisco Project Management Portal - Edit User Details</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link href="./CommonJsp/style.css" rel="stylesheet" type="text/css" />
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
<script type="text/javascript" src="validations/DateCalender.js"></script>

</head>
<body>
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
							<legend class="text7">Edit User Details</legend>
							<form action="./UserServlet?action=UpdateUserAdmin" method="post"
								name="EditUserDetailsForm">
								<table>
									<tr>
										<c:forEach items="${list1}" var="list">
											<td><label for="capgeminiUserName" class="text11">Capgemini
													User Name:</label>
											<td class="text5">${list.username}</td>
											<td><input type="hidden" name="capgeminiUserName" value="${list.username}"></td>
										</c:forEach>
										
									</tr>
									<tr>
										<c:forEach items="${list1}" var="list">
											<td><label for="firstName" class="text11">First
													Name:</label>
											<td class="text5"><input type="text" name="firstName"
												id="firstName" class="text11" value="${list.firstName}"></td>
										</c:forEach>
									</tr>
									<tr>
										<c:forEach items="${list1}" var="list">
											<td><label for="firstName" class="text11">Last
													Name:</label>
											<td class="text5"><input type="text" name="lastName"
												id="lastName" class="text11" value="${list.lastName}"></td>
										</c:forEach>
									</tr>
									<tr>
										<c:forEach items="${list1}" var="list">
											<td class="text11"><label for="designation"
												class="text11">Designation:</label></td>
											<td class="text5">
											<select name="designation">
													<option value="${list.designation}" selected>${list.designation}</option>
													<c:forEach items="${designationList}" var="listDesignation">
														<c:if test="${list.designation!=listDesignation}">
															<option value="${listDesignation}">${listDesignation}</option>
														</c:if>
													</c:forEach>
											</select></td>
										</c:forEach>
									</tr>
									<tr>
										<c:forEach items="${list2}" var="list">
											<td><label for="ciscoEmailId" class="text11">Cisco
													Email ID:</label></td>
											<td class="text5"><input type="text" name="ciscoEmailId"
												id="ciscoEmailId" class="text11"
												value="${list.ciscoEmailId}"></td>
										</c:forEach>
									</tr>
									<tr>
										<c:forEach items="${list1}" var="list">
											<td><label for="capgeminiEmailId" class="text11">Capgemini
													Email ID:</label>
											<td class="text5"><input type="text"
												name="capgeminiEmailId" class="text11" id="capgeminiEmailId"
												value="${list.capgeminiEmailId}"></td>
										</c:forEach>
									</tr>
									<tr>
										<c:forEach items="${list1}" var="list">
											<td><label for="primaryContactNo" class="text11">Primary
													Contact No:</label>
											<td class="text5"><input type="text"
												name="primaryContactNo" class="text11" id="primaryContactNo"
												value="${list.primaryContactNumber}"></td>
										</c:forEach>
									<tr>
										<c:forEach items="${list1}" var="list">
											<td><label for="emergencyContactNo" class="text11">Emergency
													Contact No:</label></td>
											<td class="text5"><input type="text"
												name="emergencyContactNo" class="text11"
												id="emergencyContactNo"
												value="${list.emergencyContactNumber}"></td>
										</c:forEach>
									</tr>
									<tr>
										<c:forEach items="${list1}" var="list">
											<td><label for="primarySkill" class="text11">Primary
													Skill:</label>
											<td class="text5"><select name="primarySkill">
													<option value="${list.primarySkill}" selected>${list.primarySkill}</option>
													<c:forEach items="${skillList}" var="listSkills">
														<c:if test="${list.primarySkill!=listSkills}">
															<option value="${listSkills}">${listSkills}</option>
														</c:if>
													</c:forEach>
											</select></td>
										</c:forEach>

									</tr>
									<tr>
										<c:forEach items="${list1}" var="list">
											<td><label for="secondarySkill1" class="text11">Secondary
													Skill 1:</label>
											<td class="text5"><select name="secondarySkill1">
													<option value="${list.secondarySkill_01}" selected>${list.secondarySkill_01}</option>
													<c:forEach items="${skillList}" var="listSkills">
														<c:if test="${list.secondarySkill_01!=listSkills}">
															<option value="${listSkills}">${listSkills}</option>
														</c:if>
													</c:forEach>
											</select></td>
										</c:forEach>
									</tr>
									<tr>
										<c:forEach items="${list1}" var="list">
											<td><label for="secondarySkill2" class="text11">Secondary
													Skill 2:</label>
											<td class="text5"><select name="secondarySkill2">
													<option value="${list.secondarySkill_02}" selected>${list.secondarySkill_02}</option>
													<c:forEach items="${skillList}" var="listSkills">
														<c:if test="${list.secondarySkill_02!=listSkills}">
															<option value="${listSkills}">${listSkills}</option>
														</c:if>
													</c:forEach>
											</select></td>
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
													<td class="text5">${list4.firstName}
															${list4.lastName}(${list1.capgeminiManagerName})</td>
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
													<td class="text5">${list6.firstName}
															${list6.lastName}(${list4.managerName2})</td>
												</c:if>
											</c:forEach>
										</c:forEach>
									</tr>
								</table><br>
								<input type="submit" value="Update" name="Submit" /><br />
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
