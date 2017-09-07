<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Cisco Project Management Portal - Delete Project</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="./CommonJsp/style.css" type="text/css" />
<link rel="stylesheet" href="./CommonJsp/css/styles.css" type="text/css" />
<link rel="stylesheet" href="./CommonJsp/css/jquery-tool.css"
	type="text/css" />

<script type="text/javascript" src="./CommonJsp/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="./CommonJsp/js/jquery.tools.min.js"></script>
<script type="text/javascript" src="./CommonJsp/js/main.js"></script>

<script type="text/javascript" src="./CommonJsp/js/cufon-yui.js"></script>
<script type="text/javascript" src="./CommonJsp/js/arial.js"></script>
<script type="text/javascript" src="./CommonJsp/js/cuf_run.js"></script>
<script type="text/javascript" src="validations/externalJavascript.js"></script>
</head>
<body>
	<!-- START PAGE SOURCE -->
	<div class="main">
		<%@ include file="../CommonJsp/CiscoLogo.jsp"%>
		<%@ include file="AdminHeader.jsp"%>
		<p align="right" style="padding-right: 13%; color: gray; font-size: 15px;">Welcome
			<c:out value="${username}"></c:out> |
			 <a href="./UserServlet?action=Logout">Log Out</a>
			 </p>
		<div class="content">
			<div class="content_resize">
				<div class="mainbar">
					<div class="article">
						<fieldset>
							<legend class="text7">Delete Projects</legend>
							<br />
							<form name="userForm" method="post" id="searchform"
								action="./UserServlet?action=DeleteProjectSearch">
								<div class="top-search">
									<div>
										<input type="text" value="Enter the Project Name"
											name="projectNameSearch" id="s"/>&nbsp;&nbsp;&nbsp;<input
											type="submit" value="Search">
									</div>
									<div>
										<a
											href="./UserServlet?action=ViewCompletedProjectJsp">Display
											Completed Projects</a>
									</div>
								</div>
								<br /> ${message1}
								<table id="customers">
									<tr>
										<th></th>
										<th class="textTableHeader">Project Code</th>
										<th class="textTableHeader">Project Manager</th>
										<th class="textTableHeader">Start Date</th>
										<th class="textTableHeader">End Date</th>

										<c:out value="${errorMessage}"></c:out>
										<c:out value="${errorMessage1}"></c:out>
									</tr>
									<c:if test="${empty projectLists}">
										<c:forEach items="${allProjectsList}" var="project">
											<tr>
												<td><input type="checkbox" name="projectName"
													value="${project.projectName}">
												</td>
												<td>${project.projectName}(${project.project_code})</td>
												<td>${project.projectManager}</td>
												<td>${project.startDate}</td>
												<td>${project.endDate}</td>
											</tr>
										</c:forEach>
									</c:if>

									<c:if test="${empty allProjectsList}">
										<c:forEach items="${projectLists}" var="project">
											<tr>
												<td><input type="checkbox" name="projectName"
													value="${project.projectName}">
												</td>
												<td>${project.projectName}(${project.project_code})</td>
												<td>${project.projectManager}</td>
												<td>${project.startDate}</td>
												<td>${project.endDate}</td>
											</tr>
										</c:forEach>
									</c:if>
								</table>
								<%--For displaying Previous link except for the 1st page --%>
								<table align="right">
									<tr>
										<c:if test="${currentPage != 1}">

											<td><a
												href="./UserServlet?action=DeleteProjectJsp&page=${currentPage - 1}">Previous</a>
											</td>
										</c:if>

										<%--For displaying Page numbers. The when condition does not display a link for the current page--%>

										<td>
											<table border="1" cellpadding="5" cellspacing="5">
												<tr>
													<c:forEach begin="1" end="${noOfPages}" var="i">
														<c:choose>
															<c:when test="${currentPage eq i}">
																<td>${i}</td>
															</c:when>
															<c:otherwise>
																<td><a
																	href="./UserServlet?action=DeleteProjectJsp&page=${i}">${i}</a>
																</td>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</tr>
											</table></td>

										<%--For displaying Next link --%>

										<c:if test="${currentPage lt noOfPages}">
											<td><a
												href="./UserServlet?action=DeleteProjectJsp&page=${currentPage + 1}">Next</a>
											</td>
										</c:if>
									</tr>

								</table>
								<br /> <input type="submit" value="Delete" onclick="deleteProject();" />
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
