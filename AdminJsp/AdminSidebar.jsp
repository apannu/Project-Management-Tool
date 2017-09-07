<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cisco Project Management Portal</title>
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
							<span class="label" style="background-image: url(./CommonJsp/images/user.png);">User Management</span>
							<span class="arrow down"></span>
						</div>
						<ul class="menu" style="display:none;">
							<li><a href="./UserServlet?action=AddUserJsp">Add New User</a></li>
							<li><a href="./UserServlet?action=ViewUserJsp&userType=admin">View / Edit User Details</a></li>
							<li><a href="./UserServlet?action=DeleteUserJsp">Delete Users</a></li>
							<li><a href="./UserServlet?action=TagUserJsp">Tag Users to Projects</a></li>
							<li><a href="./UserServlet?action=ActivateUserJsp">Activate Deleted Users</a></li>
						</ul>
					</li>
					<li>
						<div class="header">
							<span class="label" style="background-image: url(./CommonJsp/images/messages.png);">Project Management</span>
							<span class="arrow down"></span>
						</div>
						<ul class="menu" style="display:none">
							<li><a href="./UserServlet?action=AddProjectJsp">Add New Project</a></li>
							<li><a href="./UserServlet?action=ViewProjectJsp&userType=admin&status=On Going">View / Edit Project Details</a></li>
							<li><a href="./UserServlet?action=DeleteProjectJsp">Delete Project</a></li>
						</ul>
					</li>
					<li>
						<div class="header">
							<span class="label" style="background-image: url(./CommonJsp/images/messages.png);">Poll Management</span>
							<span class="arrow down"></span>
						</div>
						<ul class="menu" style="display:none">
							<li><a href="./UserServlet?action=CreatePollJsp">Create New Poll</a></li>
							<li><a href="./UserServlet?action=ViewPollsServlet&userType=admin&status=OnGoing">View Active Polls</a></li>
							<li><a href="./UserServlet?action=DeactivatePollJsp&status=OnGoing">End Poll</a></li>
						</ul>
					</li>
					<li>
						<div class="header">
							<span class="label" style="background-image: url(./CommonJsp/images/messages.png);">Document Management</span>
							<span class="arrow down"></span>
						</div>
						<ul class="menu" style="display:none">
							<li><a href="./UserServlet?action=FileUploadJsp&userType=admin">Upload Documents</a></li>
						</ul>
					</li>
					<li>
						<div class="header">
							<span class="label" style="background-image: url(./CommonJsp/images/messages.png);">Miscellaneous</span>
							<span class="arrow down"></span>
						</div>
						<ul class="menu" style="display:none">
							<li><a href="./UserServlet?action=AddLocationJsp">Add New Location</a></li>
							<li><a href="./UserServlet?action=AddSkillJsp">Add New Skill</a></li>
							<li><a href="./UserServlet?action=AddCiscoManagerJsp">Add Cisco Manager</a></li>
							<li><a href="./UserServlet?action=ImageUploadJsp&userType=admin">Upload Images</a></li>
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