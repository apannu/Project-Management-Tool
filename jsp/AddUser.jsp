<%@page import="com.capgemini.cisco.portal.utility.DesignationNamesUtil"%>
<%@page import="com.capgemini.cisco.portal.dao.UserDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.text.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="com.capgemini.cisco.portal.utility.DesignationNamesUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript">
	function managerList(designation)
	{
		document.forms[0].action="./UserServlet?action=DisplayManagerNamesServlet&designation="+designation;
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/text.css" />
    </head>

<body>
<div class="main">
<div class="center">
<div class="left">
<div class="logo"></div>
<div class="service">
<div class="service2"><p class="text1">Services list</p></div><div class="service1">
<p class="text8">User Management<ul>
<li><a href="/DBInit">Add User</a> </li>
<li><a href="deleteUser.html">Delete User</a> </li>
<li><a  href="updateUser.html">Update User Details</a> </li>
<li><a  href="grantOrRevokeAdminRights.html">Grant / Revoke Admin Rights</a></li>
<li><a  href="viewUsers.html">View Users</a> </li></ul>
<p class="text8">Project Management<ul>
<li><a href="addProject.html">Add Project</a></li>
<li><a  href="viewProjects.html">View Projects</a></li>
<li><a  href="deleteProject.html">Delete Project</a></li></ul>
</div>
</div>

<div class="latest">
<!--p class="text4">Latest news</p>-->
<p class="textLatest"><a href="viewUsers.html"><img src="img/03.png" alt="" />Users</a><br /><br />
<span class="text3">2 Users Added last week<br /></span><br /></p>
<p class="textLatest"><a href="viewProjects.html"><img src="img/03.png" alt="" />Projects</a><br /><br />
<span class="text3">1 Project removed last week<br /></span><br /></p>
<p class="text6"><a href="">Create New Poll &gt;</a> </p>
</div><div class="clear"></div>
</div>

<div class="right">
<div class="nav">
<ul>
<li><a href="index.html">Home</a></li>
<li><a href="myProfile.html">My Profile</a></li>
<li><a href="poll.html">Poll Summary</a></li>
<li><a href="UserSkillset.html">SkillSet</a></li>
<li><a href="#">Quick Links</a></li>
<li><a href="contact.html">Contacts</a></li>
<p class="logout"><a href="#">LogOut</a></p>
<li><a href="changePassword.html">Change Password</a></li>
<li><a href="documents.html">Documents</a></li>
<li><a href="pictures.html">Picture Gallery</a></li>
</ul></div>
<div class="white">
<br />
<div class="slider"></div>
<div class="content">
<div class="compnews">
<section class="grid col-three-quarters mq2-col-two-thirds mq3-col-full">
<fieldset><legend class="text7">Add User</legend></center>
			<br/>
			<form id="addUser_form" class="addUser_form" action="./UserServlet?action=AddUserServlet" method="post" name="addUser_form">	
			*Please add the employees in a hierarchy.For e.g-Vice President-Director-Associate Director-Manager-etc
				<table>
					<tr>
						<td><label for="employeeId" class="text11">Employee Id:</label></td>
						<td><input type="text" name="employeeId" id="employeeId" value="${param.employeeId}"></td>
					</tr>
					<tr>
						<td><label for="ciscoUserName" class="text11">Cisco Username:</label></td><c:if test="${errorMessage!=null}"><c:out value="Enter a different username"></c:out></c:if>
						<td><input type="text" name="ciscoUserName" id="ciscoUserName" value="${param.ciscoUserName}"></td>
					</tr>	
					<tr>
						<td><label for="userType" class="text11">User Type:</label></td>
						<td>
							<select name="userType">
							<option value="${param.userType}" selected>${param.userType}</option>
							<option value="Admin">Admin</option>
							<option value="Non-Admin">Non-Admin</option>
							</select>
						</td>
					</tr>
					<tr>
						<td><label for="firstName" class="text11">First Name:</label></td>
						<td><input type="text" name="firstName" id="firstName" value="${param.firstName}"></td>
					</tr>
					<tr>
						<td><label for="lastName" class="text11">Last Name:</label></td>
						<td><input type="text" name="lastName" id="lastName" value="${param.lastName}"></td>
					</tr>
					<tr>
						<td><label for="ciscoEmailId" class="text11">Cisco Email-Id:</label></td>
						<td><input type="text" name="ciscoEmailId" id="ciscoEmailId" value="${param.ciscoEmailId}"></td>
					</tr>
					<tr>
						<td><label for="capgeminiEmailId" class="text11">Capgemini Email-Id:</label></td>
						<td><input type="text" name="capgeminiEmailId" id="capgeminiEmailId"value="${param.capgeminiEmailId}"></td>
					</tr>	
					<tr>
						<td><label for="primaryContactNo" class="text11">Primary Contact No:</label></td>
						<td><input type="text" name="primaryContactNo" id="primaryContactNo" value="${param.primaryContactNo}"></td>
					</tr>
					<tr>
						<td><label for="emergencyContactNo" class="text11">Emergency Contact No:</label></td>
						<td><input type="text" name="emergencyContactNo" id="emergencyContactNo" value="${param.emergencyContactNo}"></td>
					</tr>
					<tr>
						<td><label for="ciscoDOJ" class="text11">Cisco DOJ:</label></td>
						<td><input type="text" name="ciscoDOJ" id="ciscoDOJ" value="${param.ciscoDOJ}"></td>
						<td>*Enter in format date(dd)-month(MM)-year(yyyy).For example 16-07-2012</td>
					</tr>
					<tr>
						<td><label for="designation" class="text11">Designation:</label></td>
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
						</td>
					</tr>
					<tr>
						<td><label for="cgManagerUsername" class="text11">CG Manager Username:</label>
						<td>
								<c:if test="${not empty managerList}">
									<select name="cgManagerUsername">
									<c:forEach items="${managerList}" var="cgManager">
									<option value="${cgManager}">${cgManager}</option>
									</c:forEach>
									</select>
								</c:if>
								<c:out value="${hierarchyMessage}"></c:out>
								<input type="submit" value="Generate ManagerList" OnClick="managerList(designation.value);">
						</td>
					</tr>
					<tr>
						<td><label for="ciscoManagerUsername" class="text11">Cisco Manager Username:</label></td>
						<td>
							<select name="cgManagerUsername">
							<option value=""></option>
							</select>
						</td>
					</tr>
					<tr>
						<td><label for="experience" class="text11">Total Experience:</label></td>
						<td><input type="text" name="experience" id="experience" value="${param.experience}"></td>
					</tr>
					<tr>
						<td><label for="serviceLine" class="text11">Service Line:</label></td>
						<td><input type="text" name="serviceLine" id="serviceLine" value="${param.serviceLine}"></td>
					</tr>
					<tr>
						<td><label for="primarySkill" class="text11">Primary Skill:</label></td>
						<td>
							<c:if test="${param.primarySkill==null}">
							<select name="primarySkill">
							<c:forEach items="${skillList}" var="Skill">
							<option value="${Skill}">${Skill}</option>
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
						<td><label for="secondarySkill1" class="text11">Secondary Skill 1:</label></td>
						<td>
							<c:if test="${param.secondarySkill1==null}">
							<select name="secondarySkill1">
							<c:forEach items="${skillList}" var="Skill">
							<option value="${Skill}">${Skill}</option>
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
						<td><label for="secondarySkill2" class="text11">Secondary  Skill 2:</label></td>
						<td>
							<c:if test="${param.secondarySkill2==null}">
							<select name="secondarySkill2">
							<c:forEach items="${skillList}" var="Skill">
							<option value="${Skill}">${Skill}</option>
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
						<input type="submit" value="Add">
						<c:if test="${errorMessage!=null}">
						<c:out value="${errorMessage}"></c:out>
						</c:if>
			</form>
			</fieldset>
		</section>	
</div>
</div>
</div></div></div></div>

</div>
</body>
</html>

</body>
</html>