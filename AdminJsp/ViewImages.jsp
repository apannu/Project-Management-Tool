<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Cisco Project Management Portal - View Images</title>
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
<script type="text/javascript" src="validations/externalJavascript.js"></script>

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
       <fieldset>
								<legend class="text7">Images List</legend>
								<br />
								<form name="displayImagesForm" method="post" id="displayImagesForm">
									<div class="top-search">
									</div>
									<c:if test="${empty imagesList}">No Images Found</c:if>
										<c:if test="${not empty imagesList}">
										<table id="customers">
											<tr>
											
												<th class="textTableHeader">Image Name</th>
												<th class="textTableHeader">Uploaded By</th>
												<th class="textTableHeader">Uploaded Date</th>
												<th class="textTableHeader">Image Description</th>
												<th class="textTableHeader">Image Size(in KB)</th>
												<th></th>
												<th></th>
											</tr>
											<c:forEach items="${imagesList}" var="images">
												<tr>
													<td>${images.fileName}</td>
													<td><a href="./UserServlet?action=ViewUserDetailsServlet&userType=admin&username=${images.createUserId}">${images.createUserId}</a></td>
													<td>${images.createDate}</td>
													<td>${images.description}</td>
													<td>${images.fileSize}</td>
													<td><a href="./UserServlet?action=DownloadImage&imageName=${images.fileName}">Download</a></td>
													<td><a href="./UserServlet?action=DeleteImage&ImageId=${images.imageID}">Delete</a></td>
												</tr>
											</c:forEach>	
											</table>
										</c:if>
								<br />
							</form>
							</fieldset>
        </div>
      </div>
    <%@ include file="AdminSidebar.jsp" %>
  </div>
  
  <div class="clr"></div>
  <%@ include file="../CommonJsp/Footer.jsp"%>
  
</div>
</div>
<!-- END PAGE SOURCE -->
</body>
</html>
