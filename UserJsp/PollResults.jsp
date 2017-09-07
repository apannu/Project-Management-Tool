<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Cisco Project Management Portal - Poll Results</title>
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
        <form action="./UserServlet?action=AddPollReplyServlet" method="post" name="pollResultsForm">
	        <fieldset>
	        	<legend class="text7">Poll Details</legend>
						<table>
							<c:forEach items="${pollsList}" var="poll">
								<tr>
									<td><label class="text11">Poll Topic:</label></td>
									<td>${poll.pollTopic}</td>
								</tr>
								<input type="hidden" value="${poll.pollTopic}" name="pollTopic">
								<tr>
									<td><label class="text11">Poll Description:</label></td>
									<td>${poll.pollDescription}</td>
								</tr>	
								<tr>
									<td><label class="text11">Poll End Date:</label></td>
									<td>${poll.pollEndDate}</td>
								</tr>
								<tr>
									<td><label class="text11">Created By:</label></td>
									<td><a href="./UserServlet?action=ViewUserDetailsServlet&username=${poll.createUserId}">${poll.createUserId}</a></td>
								</tr>
								<tr>
									<td><label class="text11">Created Date:</label></td>
									<td>${poll.createDate}</td>
								</tr>
								<tr>
									<td><label class="text11">Last Modified By:</label></td>
									<td><a href="./UserServlet?action=ViewUserDetailsServlet&username=${poll.modifyUserId}">${poll.modifyUserId}</a></td>
								</tr>
								<tr>
									<td><label class="text11">Last Modified Date:</label></td>
									<td>${poll.modifydate}</td>
								</tr>
							</c:forEach>
						</table>
						<br/>
			</fieldset>	
			<br/>
			<fieldset>
				<legend class="text7">Poll Reply List</legend>
				<br/>
				<c:if test="${not empty pollReplyList}">
					<table id="customers">
						<tr>
							<th class="textTableHeader">Username</th>
							<th class="textTableHeader">Reply</th>
							<th class="textTableHeader">Reply Date</th>
						</tr>
						<c:forEach items="${pollReplyList}" var="poll">
						<tr>
								<td>${poll.username}</td>
								<td>${poll.pollReplyText}</td>
								<td>${poll.pollReplyDate}</td>
						</tr>
						</c:forEach>
					</table>
				</c:if>	
				<c:if test="${empty pollReplyList}">No reply List for the poll</c:if>
				<br/>
			</fieldset>
			<br/>
			<c:forEach items="${pollsList}" var="poll"><c:if test="${poll.pollStatus=='OnGoing'}">
			<fieldset>
	        	<legend class="text7">Post your reply</legend>
						<table>
								<tr>
									<td><label class="text11">Reply Text:</label></td>
									<td><input type="text" name="pollReply"></td>
								</tr>
								<tr>
									<td><label class="text11">Reply Date:</label></td>
									<td><input type="text" name="pollReplyDate"></td>
								</tr>	
						</table>
						<br/>
						<input type="submit" value="Add Reply">${pollReplyMessage}
			</fieldset>	
			</c:if></c:forEach>
			</form>
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
