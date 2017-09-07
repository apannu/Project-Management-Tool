<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Cisco Project Management Portal</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="./CommonJsp/style.css" type="text/css" />
<link rel="stylesheet" href="./CommonJsp/css/styles.css" type="text/css" />
<link rel="stylesheet" href="./CommonJsp/css/jquery-tool.css" type="text/css" />
<link rel="stylesheet" href="./CommonJsp/css/loginstyle.css" type="text/css"/>


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
		<%@ include file="/CommonJsp/CiscoLogo.jsp"%>
		<div class="content">
			<section class="container">
			<div class="login">
				<h1>Login to the Portal</h1>
				<form method="post" action="./UserServlet?action=LoginAuthentication" name="loginForm">
					<p> Username
						<input type="text" name="username" value=""
							placeholder="Username" />
					</p>
					<p> Password
						<input type="password" name="password" value=""
							placeholder="Password" />
					</p>
					<p class="remember_me">
						<label> <input type="checkbox" name="remember_me"
							id="remember_me" /> Remember me on this computer </label>
					</p>
					<p>
						<input type="submit" name="commit" value="Login" />
						<input type="reset" name="reset" value="Reset" />
					</p>
					<p><b>${loginMessage}</b></p>
				</form>
			</div>

			<div class="login-help">
				<p>Forgot your password? <a href="./CommonJsp/ForgotPassword.jsp">Click here to reset
						it</a>.
				</p>
			</div>
			</section>

		</div>
		<div class="clr"></div>
		<%@ include file="/CommonJsp/Footer.jsp"%>

	</div>
	<!-- END PAGE SOURCE -->
</body>
</html>
