<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Cisco Project Management Portal</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="./style.css" type="text/css" />
<link rel="stylesheet" href="./css/styles.css" type="text/css" />
<link rel="stylesheet" href="./css/jquery-tool.css" type="text/css" />
<link rel="stylesheet" href="./css/loginstyle.css" type="text/css"/>


<script type="text/javascript" src="./js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="./js/jquery.tools.min.js"></script>
<script type="text/javascript" src="./js/main.js"></script>

<script type="text/javascript" src="./js/cufon-yui.js"></script>
<script type="text/javascript" src="./js/arial.js"></script>
<script type="text/javascript" src="./js/cuf_run.js"></script>

</head>
<body>
	<!-- START PAGE SOURCE -->
	<div class="main">
		<%@ include file="./CiscoLogo.jsp"%>
		<div class="content">

			<section class="container">
			<div class="login">
				
				<center><p>You have been logged out! </p>
			
				<p>To login again, <a href="../LoginPage.jsp">Please click here</a>.
				</p></center>
			</div>
			</section>

		</div>
		<div class="clr"></div>
		<%@ include file="./Footer.jsp"%>

	</div>
	<!-- END PAGE SOURCE -->
</body>
</html>
