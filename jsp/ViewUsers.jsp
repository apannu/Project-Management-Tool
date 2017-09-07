<%@page import="java.util.List"%>
<%@page import="com.capgemini.cisco.portal.dto.ProjectDTO"%>
<%@page import="com.capgemini.cisco.portal.dto.UserDTO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/text.css" />
	
<script type="text/javascript">
	function emptyUsernameTextbox() {
		document.getElementsByName("userField")[0].value = "";
	}
	function emptyReporteeTextbox() {
		document.getElementsByName("userField1")[0].value = "";
	}
	function viewUserDetails(){
		document.forms[0].action="./UserServlet?action=ViewUserDetailsServlet";
	}
</script>

</head>
<body>
	<div class="main">
		<div class="center">
			<div class="left">
				<div class="logo"></div>
				<div class="service">
					<div class="service2">
						<p class="text1">Services list</p>
					</div>
					<div class="service1">
						<p class="text8">User Management
						<ul>
							<li><a href="addUser.html">Add User</a></li>
							<li><a href="deleteUser.html">Delete User</a></li>
							<li><a href="updateUser.html">Update User Details</a></li>
							<li><a href="grantOrRevokeAdminRights.html">Grant/Revoke Admin Rights</a></li>
							<li><a href="./UserServlet?action=ViewUserJsp">View Users</a>
							</li>
							<li><a href="./UserServlet?action=HierarchyServlet">Hierarchy</a> </li>
						</ul>
						<p class="text8">Project Management
						<ul>
							<li><a href="addProject.html">Add Project</a></li>
							<li><a href="viewProjects.html">View Projects</a></li>
							<li><a href="deleteProject.html">Delete Project</a></li>
						</ul>
					</div>
				</div>

				<div class="latest">
					<!--p class="text4">Latest news</p>-->
					<p class="textLatest">
						<a href="viewUsers.html"><img src="img/03.png" alt="" />Users</a><br />
						<br /> <span class="text3">2 Users Added last week<br />
						</span><br />
					</p>
					<p class="textLatest">
						<a href="viewProjects.html"><img src="img/03.png" alt="" />Projects</a><br />
						<br /> <span class="text3">1 Project removed last week<br />
						</span><br />
					</p>
					<p class="text6">
						<a href="">Create New Poll &gt;</a>
					</p>
				</div>
				<div class="clear"></div>
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
					</ul>
				</div>
				<div class="white">
					<br />
					<div class="slider"></div>
					<div class="content">
						<div class="compnews">
							<section id="UserList"
								class="grid col-three-quarters mq2-col-two-thirds mq3-col-full">
							<fieldset>
								<legend class="text7">Cisco Users Information</legend>
								<br />
								<form name="userForm" method="post" id="searchform" action="./UserServlet?action=ViewUserServlet">
									<div class="top-search">
										<div>
											<select name="searchType" id="s" style="width: 250px;">
												<option value="Search by User Name">Search by User Name</option>
												<option value="Search by CG Manager Name">Search by CG Manager Name</option>
												<option value="Search by Project Name">Search by Project Name</option>
											</select>
											<input type="text" value="Enter the username" name="userField" id="s" onfocus="emptyUsernameTextbox()" />&nbsp;&nbsp;&nbsp;<input type="submit" value="Search user">
										</div>
									</div>
								<br />
									<table id="customers">
										<tr>
											<th></th>
											<th class="textTableHeader">Cisco Username</th>
											<th class="textTableHeader">Name</th>
											<th class="textTableHeader">Contact Number</th>
											<th class="textTableHeader">Project Code</th>
											<th class="textTableHeader">CG Manager Name</th>
											<th class="textTableHeader">Billable</th>
											
											<c:out value="${errorMessage}"></c:out>
											<c:out value="${errorMessage1}"></c:out>
											</tr>
											<c:if test="${empty allUsersList}">
											<c:forEach items="${usersList}" var="user">
											<tr>
											<td><input type="radio" name="employeeId" id="employeeId" value="${user.username}"></td>
											<td><c:out value="${user.username}"></c:out></td>
											<td><c:out value="${user.firstName} ${user.lastName}"></c:out></td>
											<td><c:out value="${user.primaryContactNumber}"></c:out></td>
											<td>
											<c:if test="${user.project_code!=0}">
											<c:out value="${user.project_code}"></c:out>
											</c:if>
											<c:if test="${user.project_code==0}">
											<c:out value="No Tagging available"></c:out>
											</c:if>
											</td>
											<td><c:out value="${user.capgeminiManagerName}"></c:out></td>
											<td><c:out value="${user.isBillable}"></c:out></td>
										</tr>
										</c:forEach>
										</c:if>
										
										<c:if test="${empty usersList}">
										<c:forEach items="${allUsersList}" var="user">
											<tr>
												<c:set var="code" value="${user.project_code}"></c:set>
												<td><input type="radio" name="employeeId" id="employeeId" value="${user.username}"></td>
												<td><c:out value="${user.username}"></c:out></td>
												<td><c:out value="${user.firstName} ${user.lastName}"></c:out></td>
												<td><c:out value="${user.primaryContactNumber}"></c:out></td>
												<td>
												<c:if test="${user.project_code!=0}">
													<c:out value="${user.project_code}"></c:out>
												</c:if>
												<c:if test="${user.project_code==0}">
													<c:out value="No Tagging available"></c:out>
												</c:if>
												</td>
												<td><c:out value="${user.capgeminiManagerName}"></c:out></td>
												<td><c:out value="${user.isBillable}"></c:out></td>
												</tr>
												</c:forEach>
										</c:if>
									</table>
									<br />
							<input type="submit" value="View User Details" onClick="viewUserDetails();">
							</form>
							</fieldset>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>
