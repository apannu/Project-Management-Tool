<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <div class="sidebar">
       
	   <div id="content">
	   <ul id="expmenu-freebie">
			<li>
				<!-- Start Freebie -->
				<ul class="expmenu">
					<li>
						<div class="header">
							<span class="label" style="background-image: url(./CommonJsp/images/user.png);">Users</span>
							<span class="arrow down"></span>
						</div>
						<ul class="menu" style="display:none;">
							<li><a href="./UserServlet?action=ViewUserJsp&userType=nonAdmin">Search Users</a></li>
						</ul>
					</li>
					<li>
						<div class="header">
							<span class="label" style="background-image: url(./CommonJsp/images/messages.png);">Projects</span>
							<span class="arrow down"></span>
						</div>
						<ul class="menu" style="display:none">
							<li><a href="./UserServlet?action=ViewProjectJsp&userType=nonAdmin&status=On Going">Search Projects</a></li>

						</ul>
					</li>
					<li>
						<div class="header">
							<span class="label" style="background-image: url(./CommonJsp/images/messages.png);">Documents</span>
							<span class="arrow down"></span>
						</div>
						<ul class="menu" style="display:none">
							<li><a href="./UserServlet?action=FileUploadJsp&userType=nonAdmin">Upload Documents</a></li>
							<li><a href="./UserServlet?action=ImageUploadJsp&userType=nonAdmin">Upload Images</a></li>
						</ul>
					</li>
					<li>
						<div class="header">
							<span class="label" style="background-image: url(./CommonJsp/images/messages.png);">Poll Summary</span>
							<span class="arrow down"></span>
						</div>
						<ul class="menu" style="display:none">
							<li><a href="./UserServlet?action=ViewPollsServlet&userType=nonAdmin&status=OnGoing">View Active Polls</a></li>
						</ul>
					</li>
				</ul>
				<!-- End Freebie -->
			</li>
		</ul>
		
		
      </div>
      <div class="clr"></div>
    </div>
</body>
</html>