<%@page import="com.capgemini.cisco.portal.dao.ProjectDAO"%>
<%@page import="com.capgemini.cisco.portal.dto.UserDTO"%>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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
<li><a href="addUser.html">Add User</a></li>
<li><a href="deleteUser.html">Delete User</a> </li>
<li><a  href="updateUser.html">Update User Details</a></li>
<li><a  href="grantOrRevokeAdminRights.html">Grant / Revoke Admin Rights</a></li>
<li><a  href="viewUsers.html">View Users</a></li></ul>
<p class="text8">Project Management<ul>
<li><a 	href="./UserServlet?action=AddProjectJsp">Add Project</a></li>
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
<fieldset>
	<legend class="text7">Add Project</legend></center>
			<br/>
			<form id="addUser_form" class="addUser_form" action="./UserServlet?action=TagUserJsp" method="post" name="addUser_form">	
				<table>
					<tr>
						<td><label for="projectCode" class="text11">Project Code:</label></td>
						<td><input type="text" name="projectCode" id="projectCode"></td>
					</tr>
					<tr>
						<td><label for="projectName" class="text11">Project Name:</label>
						<td><input type="text" name="projectName" id="projectName"></td>
					</tr>	
					<tr>
						<td><label for="projectObjective" class="text11">Project Objective:</label>
						<td><input type="text" name="projectObjective" id="projectObjective"></td>
					</tr>	
					<tr>
						<td><label for="projectManager" class="text11">Project Manager:</label>
						<td>
							<c:if test="${empty projectManagerList}">
							<c:set var="managerList" value="All managers are tagged"></c:set>
							<c:out value="${managerList}"></c:out>
							</c:if>
							<c:if test="${not empty projectManagerList}">
							<select name="projectManager">
							<c:forEach items="${projectManagerList}" var="user">
							<option value="${user.username}">${user.username}</option>
							</c:forEach>							
							</select>
							</c:if>
						</td>
					</tr>	
					<tr>
						<td><label for="completionTime" class="text11">Completion Time:</label>
						<td><input type="text" name="completionTime" id="completionTime"></td>
					</tr>
					<tr>
						<td><label for="modules" class="text11">Number of modules:</label>
						<td><input type="text" name="modules" id="modules"></td>
					</tr>
					<tr>
						<td><label for="location" class="text11">Location:</label>
						<td>
							<select name="location">
							<c:forEach items="${locationNamesList}" var="user">
							<option value="${user.locationName}">${user.locationName}</option>
							</c:forEach>							
							</select>
						</td>
					</tr>	
					<tr>
						<td><label for="startDate" class="text11">Start Date:</label>
						<td><input type="text" name="startDate" id="startDate"></td>
					</tr>
					<tr>
						<td><label for="endDate" class="text11">End Date:</label>
						<td><input type="text" name="endDate" id="endDate"></td>
					</tr>
					<tr>
						<td><label for="noOfTechnicalResources" class="text11">Number of Technical Resources:</label></td>
						<td><input type="text" name="noOfTechnicalResources" id="noOfTechnicalResources"></td>
					</tr>
					<tr>
						<td><label for="noOfNonTechnicalResources" class="text11">Number of Non-Technical Resources:</label>
						<td><input type="text" name="noOfNonTechnicalResources" id="noOfNonTechnicalResources"></td>
					</tr>	
					</table>
					<br/>
						<input type="submit" id="submit" value="Add" class="button fright">
			</form>
			</fieldset>
		</section>	
</div>
</div>
</div></div></div></div>

</div>
</body>
</html>
