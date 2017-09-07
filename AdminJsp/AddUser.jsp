<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Cisco Project Management Portal - Add User</title>
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
		<%@ include file="AdminHeader.jsp"%>
		<p align="right" style="padding-right:13%; color:gray; font-size: 15px;"> Welcome <c:out value="${username}"></c:out> | <a href="./UserServlet?action=Logout">Log Out</a></p>
  <div class="content">
    <div class="content_resize">
      <div class="mainbar">
        <div class="article">
        <fieldset><legend class="text7">Add User</legend>
        <b>Field names ending with * are mandatory</b>
        <br/>
			<br/>
			<form action="./UserServlet?action=AddUserServlet" method="post" name="addUserForm" id="addUserForm">	
				<table>
					<tr>
						<td><label class="text11">Employee ID</label><label class="text12">*</label></td>
						<td><input type="text" name="employeeId" id="employeeId" class="text11" value="${param.employeeId}">*EmployeeID's length should be between 4 and 6 numbers</td>
					</tr>
					<tr>
						<td><label class="text11">Capgemini Username:</label><label class="text12">*</label></td><c:if test="${errorMessage!=null}"><c:out value="Enter a different username"></c:out></c:if>
						<td><input type="text" name="capgeminiUsername" id="capgeminiUsername" class="text11" value="${param.capgeminiUsername}">*Username cannot be more than 20 characters</td>
					</tr>	
					<tr>
						<td><label class="text11">User Type:</label><label class="text12">*</label></td>
						<td>
							<select name="userType">
								<option value="${param.userType}" selected>${param.userType}</option>
								<option value="Admin">Admin</option>
								<option value="Non-Admin">Non-Admin</option>
							</select>
						</td>
					</tr>
					<tr>
						<td><label class="text11">First Name:</label><label class="text12">*</label></td>
						<td><input type="text" name="firstName" id="firstName" class="text11" value="${param.firstName}"></td>
					</tr>
					<tr>
						<td><label class="text11">Last Name:</label><label class="text12">*</label></td>
						<td><input type="text" name="lastName" id="lastName" class="text11" value="${param.lastName}"></td>
					</tr>
					<tr>
						<td><label class="text11">Capgemini Email-Id:</label><label class="text12">*</label></td>
						<td><input type="text" name="capgeminiEmailId" class="text11" id="capgeminiEmailId" class="required email" value="${param.capgeminiEmailId}"></td>
					</tr>	
					<tr>
						<td><label class="text11">Primary Contact No:</label><label class="text12">*</label></td>
						<td><input type="text" name="primaryContactNo" class="text11" id="primaryContactNo" value="${param.primaryContactNo}"></td>
					</tr>
					<tr>
						<td><label class="text11">Emergency Contact No:</label></td>
						<td><input type="text" name="emergencyContactNo" class="text11" id="emergencyContactNo" value="${param.emergencyContactNo}"></td>
					</tr>
					<tr>
						<td><label class="text11">Cisco DOJ:</label><label class="text12">*</label></td>
						<td><input type="text" name="ciscoDOJ" id="ciscoDOJ" value="${param.ciscoDOJ}" onfocus="calender(ciscoDOJ.name)"></td>
						
					</tr>
					<tr>
						<td><label class="text11">Designation:</label><label class="text12">*</label></td>
						<td>
							<c:if test="${param.designation==null}">
								<select name="designation">
									<c:forEach items="${designationList}" var="listDesignation">
										<option value="${listDesignation}">${listDesignation}</option>
									</c:forEach>
								</select>
							</c:if>
							
							<c:if test="${param.designation!=null}">
								<select name="designation">
									<option value="${param.designation}" selected>${param.designation}</option>
									<c:forEach items="${designationList}" var="listDesignation">
										<c:if test="${param.designation!=listDesignation}">
											<option value="${listDesignation}">${listDesignation}</option>
										</c:if>
									</c:forEach>
								</select>
							</c:if>
							&nbsp&nbsp&nbsp&nbsp&nbsp<input type="submit" value="ManagerList" OnClick="managerList(designation.value);">
						</td>
					</tr>
					<tr>
						<td><label class="text11">CG Manager:</label><label class="text12">*</label></td>
						<td>
								<c:if test="${empty managerList}">
								<c:out value="${hierarchyMessage}"></c:out>
								</c:if>
								<c:if test="${not empty managerList}">
									<select name="cgManagerUsername">
										<c:forEach items="${managerList}" var="cgManager">
											<option value="${cgManager}">${cgManager}</option>
										</c:forEach>
									</select>
								</c:if>
						</td>
					</tr>
					<tr>
						<td><label class="text11">Delivery Manager</label><label class="text12">*</label></td>
						<td><input type="radio" name="deliveryManager" value="Yes">Yes&nbsp&nbsp&nbsp&nbsp&nbsp<input type="radio" name="deliveryManager" value="No" checked="checked">No</td>
					</tr>
					<tr>
						<td><label class="text11">Cisco Manager:</label><label class="text12">*</label></td>
						<td>
							<select name="cgManagerUsername">
								<option value="Frautz">Frautz</option>
							</select>
						</td>
					</tr>
					<tr>
						<td><label class="text11">Total Experience:</label><label class="text12">*</label></td>
						<td><input type="text" name="experience" class="text11" id="experience" value="${param.experience}"></td>
					</tr>
					<tr>
						<td><label class="text11">Service Line:</label><label class="text12">*</label></td>
						<td><input type="text" name="serviceLine" class="text11" id="serviceLine" value="${param.serviceLine}"></td>
					</tr>
					<tr>
						<td><label class="text11">Primary Skill:</label><label class="text12">*</label></td>
						<td>
							<c:if test="${param.primarySkill==null}">
								<select name="primarySkill">
									<option value="Select your Skill">Select your Skill</option>
									<c:forEach items="${skillList}" var="Skill">
										<c:if test="${not empty Skill}">
										<option value="${Skill}">${Skill}</option>
										</c:if>
									</c:forEach>
								</select>
							</c:if>
							
							<c:if test="${param.primarySkill!=null}">
								<select name="primarySkill">
									<option value="${param.primarySkill}" selected>${param.primarySkill}</option>
									<c:forEach items="${skillList}" var="Skill">
										<c:if test="${param.primarySkill!=Skill}">
											<option value="${Skill}">${Skill}</option>
										</c:if>
									</c:forEach>
								</select>
							</c:if>
						</td>
					</tr>
					<tr>
						<td><label class="text11">Secondary Skill 1:</label></td>
						<td>
							<c:if test="${param.secondarySkill1==null}">
								<select name="secondarySkill1">
									<option value="Select your Skill">Select your Skill</option>
									<c:forEach items="${skillList}" var="Skill">
										<c:if test="${not empty Skill}">
										<option value="${Skill}">${Skill}</option>
										</c:if>
									</c:forEach>
								</select>
							</c:if>
							
							<c:if test="${param.secondarySkill1!=null}">
							<select name="secondarySkill1">
								<option value="${param.secondarySkill1}" selected>${param.secondarySkill1}</option>
								<c:forEach items="${skillList}" var="Skill">
									<c:if test="${param.secondarySkill1!=Skill}">
										<option value="${Skill}">${Skill}</option>
									</c:if>
								</c:forEach>
							</select>
							</c:if>
						</td>
					</tr>
					<tr>
						<td><label class="text11">Secondary  Skill 2:</label></td>
						<td>
							<c:if test="${param.secondarySkill2==null}">
								<select name="secondarySkill2">
									<option value="Select your Skill">Select your Skill</option>
									<c:forEach items="${skillList}" var="Skill">
										<c:if test="${not empty Skill}">
										<option value="${Skill}">${Skill}</option>
										</c:if>
									</c:forEach>
								</select>
							</c:if>
							
							<c:if test="${param.secondarySkill2!=null}">
								<select name="secondarySkill2">
									<option value="${param.secondarySkill2}" selected>${param.secondarySkill2}</option>
									<c:forEach items="${skillList}" var="Skill">
										<c:if test="${param.secondarySkill2!=Skill}">
											<option value="${Skill}">${Skill}</option>
										</c:if>
									</c:forEach>
								</select>
							</c:if>
						</td>
					</tr>
					</table>
					<br/>
						<input type="submit" value="Add" onclick="return addUserValidations();">
						<input type="reset" value="Reset">
						${addUserMessage}
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
