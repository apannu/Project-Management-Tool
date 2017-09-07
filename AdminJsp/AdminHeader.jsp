<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="header">
    <div class="header_resize">
      <div class="menu_nav">
        <ul>
          <li><a href="./UserServlet?action=ViewUserJspAdmin">Home</a></li>
          <li><a href="">My Profile</a></li>
          <li><a href="./UserServlet?action=ResetPasswordJsp&userType=admin">Reset Password</a></li>
          <li><a href="./UserServlet?action=DisplayImagesJsp&userType=admin">Gallery</a></li>
          <li><a href="./UserServlet?action=AdminRightsJsp">Grant/Revoke Admin Rights</a></li>
        </ul>
      </div>
      <div class="clr"></div>
    </div>
  </div>
</body>
</html>