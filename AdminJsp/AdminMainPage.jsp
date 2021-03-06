<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Cisco Project Management Portal - Admin Main Page</title>
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


</head>
<body>
<input type="submit" value="click">
	<!-- START PAGE SOURCE -->
	<div class="main">
		<%@ include file="../CommonJsp/CiscoLogo.jsp"%>
		<%@ include file="AdminHeader.jsp"%>
		<p align="right" style="padding-right:13%; color:gray; font-size: 15px;"> Welcome <c:out value="${username}"></c:out> | <a href="./UserServlet?action=Logout">Log Out</a></p>

		<div class="content">
			<div class="content_resize">
				<div class="mainbar">
					<div class="article">
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
										<div>
											&nbsp&nbsp&nbsp&nbsp&nbsp<a href="./UserServlet?action=ViewUnactiveUsersJsp">Display Unactive Users</a>
										</div>
									</div>
								<br />
								${addUserMessage}
									<table id="customers">
										<tr>
											<th></th>
											<th class="textTableHeader">Capgemini Username</th>
											<th class="textTableHeader">Cisco Username</th>
											<th class="textTableHeader">Name</th>
											<th class="textTableHeader">Contact Number</th>
											<th class="textTableHeader">Project Code</th>
											<th class="textTableHeader">CG Manager Name</th>
											
											<c:out value="${errorMessage}"></c:out>
											<c:out value="${errorMessage1}"></c:out>
										</tr>
											<c:if test="${empty allUsersList}">
													<c:forEach items="${usersList1}" var="user1">
														<tr>
															<td><input type="radio" name="username" value="${user1.username}"></td>
															<td>${user1.username}</td>
															<td>
																<c:if test="${user1.ciscoUsername!=user1.username}">${user1.ciscoUsername}</c:if>
																<c:if test="${user1.ciscoUsername==user1.username}">No User Name available</c:if>
															</td>
															<td>${user1.firstName} ${user1.lastName}</td>
															<td>${user1.primaryContactNumber}</td>
															<td>
																<c:if test="${user1.isBillable=='N'}">No Tagging available</c:if>
																<c:if test="${user1.isBillable=='Y'}">${user1.project_code}</c:if>
															</td>
															<td>${user1.capgeminiManagerName}</td>
														</tr>
													</c:forEach>	
													<c:forEach items="${usersList}" var="user">
														<tr>
															<td><input type="radio" name="username" value="${user.username}"></td>
															<td>${user.username}</td>
															<td>
																<c:if test="${user.ciscoUsername!=user.username}">${user.ciscoUsername}</c:if>
																<c:if test="${user.ciscoUsername==user.username}">No User Name available</c:if>
															</td>
															<td>${user.firstName} ${user.lastName}</td>
															<td>${user.primaryContactNumber}</td>
															<td>
																<c:if test="${user.isBillable=='N'}">No Tagging available</c:if>
																<c:if test="${user.isBillable=='Y'}">${user.project_code}</c:if>
															</td>
															<td>${user.capgeminiManagerName}</td>
														</tr>
													</c:forEach>
											</c:if>
										
											<c:if test="${empty usersList}">
												<c:forEach items="${allUsersList}" var="user">
													<tr>
														<c:set var="code" value="${user.project_code}"></c:set>
														<td><input type="radio" name="username" value="${user.username}"></td>
														<td>${user.username}</td>
														<td>
															<c:if test="${user.ciscoUsername!=user.username}">${user.ciscoUsername}</c:if>
															<c:if test="${user.ciscoUsername==user.username}">No User Name available</c:if>
														</td>
														<td>${user.firstName} ${user.lastName}</td>
														<td>${user.primaryContactNumber}</td>
														<td>
															<c:if test="${user.isBillable=='N'}">No Tagging available</c:if>
															<c:if test="${user.isBillable=='Y'}">${user.project_code}</c:if>
														</td>
														<td>${user.capgeminiManagerName}</td>
													</tr>
											</c:forEach>
											</c:if>
									</table>
									<br />
							<input type="submit" value="View Details">
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
