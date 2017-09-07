<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Cisco Project Management Portal - View Polls</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link href="./CommonJsp/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="./CommonJsp/css/styles.css" type="text/css" />
<link rel="stylesheet" href="./CommonJsp/css/jquery-tool.css" type="text/css" />

<script type="text/javascript" src="./CommonJsp/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="./CommonJsp/js/jquery.tools.min.js"></script>
<script type="text/javascript" src="./CommonJsp/js/main.js"></script>

<script type="text/javascript" src="./CommonJsp/js/cufon-yui.js"></script>
<script type="text/javascript" src="./CommonJsp/js/arial.js"></script>
<script type="text/javascript" src="./CommonJsp/js/cuf_run.js"></script>
<script type="text/javascript" src="validations/externalJavascript.js"></script>
	
<script type="text/javascript">
	function emptyUsernameTextbox() 
	{
		document.getElementsByName("pollTopic")[0].value = "";
	}
</script>
</head>
<body>
<!-- START PAGE SOURCE -->
<div class="main">
  <%@ include file="../CommonJsp/CiscoLogo.jsp"%>
		<%@ include file="UserHeader.jsp"%>
		<p align="right" style="padding-right:13%; color:gray; font-size: 15px;"> Welcome <c:out value="${username}"></c:out> | <a href="./UserServlet?action=Logout">Log Out</a></p>
  <div class="content">
    <div class="content_resize">
      <div class="mainbar">
        <div class="article">
       <fieldset>
								<legend class="text7">View Polls</legend>
								<br />
								<form name="pollForm" method="post" action="./UserServlet?action=PollSearchServlet&userType=admin&userType=nonAdmin">
									<div class="top-search">
										<div>
											<input type="text" value="Enter the Poll Topic" name="pollTopic" id="s" onfocus="emptyUsernameTextbox()" />&nbsp;&nbsp;&nbsp;<input type="submit" value="Search Poll" onclick="return viewPollsValidations();">
										</div>
									</div>
								<br />
								${createPollMessage}
								${emptyallPollsList}
								${emptypollsList}
								<c:if test="${not empty allPollsList}">
									<table id="customers">
										<tr>
											<th class="textTableHeader">Poll Topic</th>
											<th class="textTableHeader">Poll Status</th>
											<th class="textTableHeader">Created By</th>
											<th class="textTableHeader">Poll Start Date</th>
											<th class="textTableHeader">Poll End Date</th>
										</tr>
										
										<c:forEach items="${allPollsList}" var="poll">
											<tr>
												<td><a href="./UserServlet?action=PollResultsServlet&userType=nonAdmin&pollTopic=${poll.pollTopic}">${poll.pollTopic}</a></td>
												<td>${poll.pollStatus}</td>
												<td><a href="./UserServlet?action=ViewUserDetailsServlet&userType=nonAdmin&username=${poll.createUserId}">${poll.createUserId}</a></td>
												<td>${poll.createDate}</td>
												<td>${poll.pollEndDate}</td>
											</tr>
										</c:forEach>
									</table>
								</c:if>
								
								<c:if test="${not empty pollsList}">
									<table id="customers">
										<tr>
											<th class="textTableHeader">Poll Topic</th>
											<th class="textTableHeader">Poll Status</th>
											<th class="textTableHeader">Created By</th>
											<th class="textTableHeader">Poll Start Dae</th>
											<th class="textTableHeader">Poll End Date</th>
										</tr>
										
										<c:forEach items="${pollsList}" var="poll">
											<tr>
												<td><a href="./UserServlet?action=PollResultsServlet&userType=nonAdmin&pollTopic=${poll.pollTopic}">${poll.pollTopic}</a></td>
												<td>${poll.pollStatus}</td>
												<td><a href="./UserServlet?action=ViewUserDetailsServlet&userType=nonAdmin&username=${poll.createUserId}">${poll.createUserId}</a></td>
												<td>${poll.createDate}</td>
												<td>${poll.pollEndDate}</td>
											</tr>
										</c:forEach>
									</table>
								</c:if>
									<br />
							</form>
							</fieldset>
        </div>
      </div>
   <%@ include file="UserSidebar.jsp"%>
  </div>
  
  <div class="clr"></div>
   <%@ include file="../CommonJsp/Footer.jsp"%>
  
</div>
</div>
<!-- END PAGE SOURCE -->
</body>
</html>
