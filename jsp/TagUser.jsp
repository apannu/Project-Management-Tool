<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
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
<li><a href="addUser.html">Add User</a> </li>
<li><a href="deleteUser.html">Delete User</a> </li>
<li><a  href="updateUser.html">Update User Details</a> </li>
<li><a  href="grantOrRevokeAdminRights.html">Grant / Revoke Admin Rights</a></li>
<li><a  href="viewUsers.html">View Users</a></li>
<li><a  href="./UserServlet?action=TagUserJsp">Tag User to Project</a></li></ul>
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
<c:if test="${param.projectManager!=null}">
<c:set var="projectStatus" value="The Project had been added succesfully.Please tag the Manager in the project"></c:set>
<c:out value="${projectStatus}"></c:out>
</c:if>
<section class="grid col-three-quarters mq2-col-two-thirds mq3-col-full">
<fieldset><legend class="text7">Tag User to Project</legend></center>
			<br/>
			<form action="./UserServlet?action=TagUserServlet" method="post" name="addUser_form">	
				<table>
					<tr>
						<td><label for="username" class="text11">Username:</label></td>
						<td>
							<c:if test="${param.projectManager==null}">
							<select name="username">
							<c:forEach items="${untaggedUsersList}" var="user">
							<option value="${user.username}">${user.username}</option>
							</c:forEach>
							</select>
							</c:if>
							
							<c:if test="${param.projectManager!=null}">
							<input type="text" name="username" value="${param.projectManager}" readonly>
							</c:if>
						</td>
					</tr>
					<tr>
						<td><label for="projectCode1" class="text11">Project Code:</label>
						<td>
							<c:if test="${param.projectCode==null}">
							<select name="projectCode1">
							<c:forEach items="${projectCodeList}" var="project">
							<option value="${project.project_code}">${project.project_code}</option>
							</c:forEach>
							</select>
							</c:if>
							
							<c:if test="${param.projectCode!=null}">
							<input type="text" name="projectCode1" value="${param.projectCode}" readonly>
							</c:if>
						</td>
					</tr>	
					<tr>
						<td><label for="billHours" class="text11">Billing hours:</label>
						<td><input type="text" name="billHours" id="billHours"></td>
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
						<td><label for="region" class="text11">Region:</label></td>
						<td><select name="region">
							<option value="On Shore">On Shore</option>
							<option value="Off Shore">Off Shore</option>
							</select>
						</td>
					</tr>
					<tr>
						<td><label for="userRole" class="text11">User Role:</label>
						<td>
							<c:if test="${param.projectManager==null}">
							<select name="userRole">
							<c:forEach items="${UserRoleList}" var="userRole">
							<option value="${userRole.projectRoleName}">${userRole.projectRoleName}</option>
							</c:forEach>
							</select>
							</c:if>
							
							<c:if test="${param.projectManager!=null}">
							<input type="text" name="userRole" value="Manager" readonly>
							</c:if>
						</td>
					</tr>	
					<tr>
						<td><label for="locationName1" class="text11">Location:</label>
						<td>
							<c:if test="${param.location==null}">
							<select name="locationName1">
							<c:forEach items="${locationNamesList}" var="location">
							<option value="${location.locationName}">${location.locationName}</option>
							</c:forEach>
							</select>
							</c:if>
							
							<c:if test="${param.location!=null}">
							<input type="text" name="locationName1" value="${param.location}" readonly>
							</c:if>
						</td>
					</tr>
					</table>
					<br/>
					<input type="submit" value="Tag User">
			</form>
			</fieldset>
		</section>	
</div>
</div>
</div></div></div></div>
</div>
</body>
</html>
