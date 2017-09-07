<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
<li><a href="addUser.html">Add User</a> </li>
<li><a href="deleteUser.html">Delete User</a> </li>
<li><a  href="updateUser.html">Update User Details</a> </li>
<li><a  href="grantOrRevokeAdminRights.html">Grant / Revoke Admin Rights</a></li>
<li><a  href="viewUsers.html">View Users</a> </li>
<li><a href="addUser.html">Add User</a> </li>
</ul>

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
<center><p class="text7">My Profile<br /><img src="img/06.png" alt="" /></p></center>
		<div id="userDetails">
		<section class="grid col-three-quarters mq2-col-two-thirds mq3-col-full">
<fieldset><legend class="text7">Personal Details</legend></center>
				<form action="" method="post" name="ViewUserDetails_form">	
				<table>
					<tr>
					<c:forEach items="${list1}" var="list">
						<td><label for="employeeId" class="text11">Employee Id:</label></td>
						<td class="text5">${list.employeeId}</td>
					</c:forEach>
					</tr>
					<tr>
					<c:forEach items="${list1}" var="list">
						<td><label for="ciscoUserName" class="text11">Cisco Username:</label>
						<td class="text5">${list.username}</td>
					</c:forEach>
					</tr>	
					<tr>
					<c:forEach items="${list1}" var="list">	
						<td><label for="firstName" class="text11">First Name:</label>
						<td class="text5">${list.firstName}</td>
					</c:forEach>	
					</tr>
					<tr>
					<c:forEach items="${list1}" var="list">	
						<td><label for="lastName" class="text11">Last Name:</label>
						<td class="text5">${list.lastName}</td>
					</c:forEach>
					</tr>
					<tr>
					<c:forEach items="${list1}" var="list">	
						<td><label for="designation" class="text11">Designation:</label>
						<td class="text5">${list.designation}</td>
					</c:forEach>
					</tr>
					<tr>
					<c:forEach items="${list1}" var="list">	
						<td><label for="ciscoEmailId" class="text11">Cisco Email-Id:</label></td>
						<td class="text5">${list.ciscoEmailId}</td>
					</c:forEach>
					</tr>
					<tr>
					<c:forEach items="${list1}" var="list">	
						<td><label for="capgeminiEmailId" class="text11">Capgemini Email-Id:</label>
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
					<c:forEach items="${list2}" var="list">	
						<td><label for="skillSet" class="text11">SkillSet:</label>
						<td class="text5">${list.primarySkill},${list.secondarySkill_01},${list.secondarySkill_02}</td>
						</c:forEach>
					</tr>
					</table>
					<br/>
			</form>
			</fieldset>	
		</section>
		<br/>
		<section id ="UserList" class="grid col-three-quarters mq2-col-two-thirds mq3-col-full">
			<fieldset>
				<legend class="text7">Project Information</legend>
				<br/>
				<table id="customers">
					<tr>
						<th class="textTableHeader">Project Code</th>
						<th class="textTableHeader">Start Date</th>
						<th class="textTableHeader">End Date</th>
						<th class="textTableHeader">Project Name</th>
						<th class="textTableHeader">Project Manager</th>
						<c:out value="${errorMessage}"></c:out>
					</tr>
					<tr>
					<c:forEach items="${list3}" var="list">	
						<td>${list.project_code}</td>
						<td>${list.startDate}</td>
						<td>${list.endDate}</td>
					</c:forEach>
					<c:forEach items="${list4}" var="list">	
						<td>${list.projectName}</td>
						<td>${list.projectManager}</td>
					</c:forEach>
					</tr>
				</table>
				<br/>
			</fieldset>
		</section>	
		</div>
</div>
</div>
</div></div></div></div>

</div>
</body>
</html>
