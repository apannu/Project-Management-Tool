package com.capgemini.cisco.portal.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.capgemini.cisco.portal.dto.ProjectDTO;
import com.capgemini.cisco.portal.dto.ResourceDTO;
import com.capgemini.cisco.portal.dto.UserDTO;
import com.capgemini.cisco.portal.service.ProjectManagementModule;
import com.capgemini.cisco.portal.service.UserManagementModule;
import com.capgemini.cisco.portal.utility.DesignationNamesUtil;
import com.capgemini.cisco.portal.utility.FetchLocationUtil;
import com.capgemini.cisco.portal.utility.FetchProjectRoleIDUtil;
import com.capgemini.cisco.portal.utility.GetSqlDate;

public class AdminServlet extends HttpServlet {
	/*private static final long serialVersionUID = 1L;
	HttpSession session;
	Date sessionCreatedDate;
	Date sessionLastAccessedDate;

	public AdminServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		String a = "";
		UserManagementModule userModel = new UserManagementModule();
		ProjectManagementModule projectModel = new ProjectManagementModule();


		if (action.equals("HierarchyServlet")) {

			List<String> list = userModel.getVPandADNames();
			String ADName = list.get(0);
			String VPName = list.get(1);
			request.setAttribute("VPName", VPName);
			request.setAttribute("ADName", ADName);
			request.getRequestDispatcher("/jsp/Hierarchy.jsp").forward(request,
					response);
		}

		if (action.equalsIgnoreCase("DeleteUserJsp")) {

			String errorMessage = "";
			List<UserDTO> allUsersList = userModel.viewActiveUsers();
			if (allUsersList == null) {
				errorMessage = "No Users";
			}
			request.setAttribute("allUsersList", allUsersList);
			request.setAttribute("errorMessage", errorMessage);
			request.getRequestDispatcher("/jsp1/DeleteUser.jsp").forward(
					request, response);

		}

		if (action.equals("DeleteUserSearchResult")) {

			String errorMessage1 = "";
			List<UserDTO> usersList = new ArrayList<UserDTO>();
			String username = request.getParameter("username");
			usersList = userModel.viewUsers(username, "Search by User Name");
			if (usersList.isEmpty()) {
				errorMessage1 = "No Match found";
			}
			System.out.println("empty list: " + errorMessage1);
			request.setAttribute("errorMessage1", errorMessage1);
			request.setAttribute("usersList", usersList);
			request.getRequestDispatcher("/jsp1/DeleteUser.jsp").forward(
					request, response);

		}

		if (action.equals("DeleteUser")) {

			String[] username = request.getParameterValues("usercheck");
			for (int i = 0; i < username.length; i++) {
				System.out.println(username[i]);
			}
			String message1 = "";
			try {
				userModel.deleteUsers(username);
				message1 = "The user(s) have been succesfully deleted!";
				request.setAttribute("message1", message1);
				String errorMessage = "";
				List<UserDTO> allUsersList = userModel.viewActiveUsers();
				if (allUsersList == null) {
					errorMessage = "No Users";
				}
				request.setAttribute("allUsersList", allUsersList);
				request.setAttribute("errorMessage", errorMessage);
				request.getRequestDispatcher("/jsp1/DeleteUser.jsp").forward(
						request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (action.equalsIgnoreCase("UpdateUserJsp")) {

			String errorMessage = "";
			List<UserDTO> allUsersList = userModel.viewActiveUsers();
			if (allUsersList == null) {
				errorMessage = "No Users";
			}
			request.setAttribute("allUsersList", allUsersList);
			request.setAttribute("errorMessage", errorMessage);
			request.getRequestDispatcher("/jsp1/UpdateUser.jsp").forward(
					request, response);

		}

		if (action.equals("UpdateUserSearchResult")) {

			String errorMessage1 = "";
			List<UserDTO> usersList = new ArrayList<UserDTO>();
			String username = request.getParameter("username");
			usersList = userModel.viewUsers(username, "Search by User Name");
			if (usersList.isEmpty()) {
				errorMessage1 = "No Match found";
			}
			System.out.println("empty list: " + errorMessage1);
			request.setAttribute("errorMessage1", errorMessage1);
			request.setAttribute("usersList", usersList);
			request.getRequestDispatcher("/jsp1/UserEditPage.jsp").forward(
					request, response);

		}

		if (action.equals("UpdateUser")) {

			String[] username = request.getParameterValues("usercheck");
			for (int i = 0; i < username.length; i++) {
				System.out.println(username[i]);
			}
			String message1 = "";
			try {
				userModel.deleteUsers(username);
				message1 = "The user(s) have been succesfully deleted!";
				request.setAttribute("message1", message1);
				String errorMessage = "";
				List<UserDTO> allUsersList = userModel.viewActiveUsers();
				if (allUsersList == null) {
					errorMessage = "No Users";
				}
				request.setAttribute("allUsersList", allUsersList);
				request.setAttribute("errorMessage", errorMessage);
				request.getRequestDispatcher("/jsp1/UpdateUser.jsp").forward(
						request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (action.equals("ViewUserDetailsServletUpdate")) {

			Map<Integer, List<UserDTO>> hashmap = null;
			if (request.getParameter("action1") != null)
				hashmap = userModel.viewUserDetails(request
						.getParameter("action1"));
			else
				hashmap = userModel.viewUserDetails(request
						.getParameter("username"));
			List<UserDTO> list1 = (List<UserDTO>) hashmap.get(1);
			List<UserDTO> list2 = (List<UserDTO>) hashmap.get(2);
			List<UserDTO> list3 = (List<UserDTO>) hashmap.get(3);
			List<UserDTO> list4 = (List<UserDTO>) hashmap.get(4);
			List<UserDTO> list5 = (List<UserDTO>) hashmap.get(5);
			List<UserDTO> list6 = (List<UserDTO>) hashmap.get(6);
			request.setAttribute("list1", list1);
			request.setAttribute("list2", list2);
			request.setAttribute("list3", list3);
			request.setAttribute("list4", list4);
			request.setAttribute("list5", list5);
			request.setAttribute("list6", list6);

			request.getRequestDispatcher("/jsp1/ViewUserDetails.jsp").forward(
					request, response);
		}

		if (action.equals("ViewUserDetailsServletUpdate")) {

			Map<Integer, List<UserDTO>> hashmap = null;
			if (request.getParameter("action1") != null)
				hashmap = userModel.viewUserDetails(request
						.getParameter("action1"));
			else
				hashmap = userModel.viewUserDetails(request
						.getParameter("username"));
			List<UserDTO> list1 = (List<UserDTO>) hashmap.get(1);
			List<UserDTO> list2 = (List<UserDTO>) hashmap.get(2);
			List<UserDTO> list3 = (List<UserDTO>) hashmap.get(3);
			List<UserDTO> list4 = (List<UserDTO>) hashmap.get(4);
			List<UserDTO> list5 = (List<UserDTO>) hashmap.get(5);
			List<UserDTO> list6 = (List<UserDTO>) hashmap.get(6);
			request.setAttribute("list1", list1);
			request.setAttribute("list2", list2);
			request.setAttribute("list3", list3);
			request.setAttribute("list4", list4);
			request.setAttribute("list5", list5);
			request.setAttribute("list6", list6);

			request.getRequestDispatcher("/jsp1/ViewUserDetails.jsp").forward(
					request, response);
		}

		if (action.equals("AddSkillJsp")) {

			List<String> skillList = userModel.viewSkills();
			request.setAttribute("skillList", skillList);
			request.getRequestDispatcher("/jsp1/AddSkill.jsp").forward(request,
					response);
		}

		// User enters his username and password and clicks on Login
		if (action.equals("LoginAuthentication")) {

			List<String> user = new ArrayList<String>();
			System.out.println("Servlet Execution");
			String dispatch_user = "./UserServlet?action=ViewMyProfile";
			String dispatch_admin = "./UserServlet?action=ViewUserJsp&userType=admin";

			String username = request.getParameter("username");
			String password = request.getParameter("password");

			//user = userModel.loginAuthentication(username, password);

			session = request.getSession(true);
			System.out.println(session.isNew());
			System.out.println(session.getId());
			System.out.println(session.getMaxInactiveInterval());

			session.setAttribute("username", username);
			session.setAttribute("valid", "yes");

			String usertype = user.get(0);
			String message = user.get(1);
			System.out.println(usertype);
			request.setAttribute("usertype", usertype);
			request.setAttribute("message", message);
			if (usertype.equals("Admin")) {
				request.getRequestDispatcher(dispatch_admin)
						.forward(request, response);
			} else if (usertype.equals("Non-Admin")) {
				request.getRequestDispatcher(dispatch_user).forward(request,
						response);
			}
		}

		// User clicks on Forgot Password link
		if (action.equalsIgnoreCase("ForgotPassword")) {

			String password = null;
			String username = request.getParameter("username");
			String emailId = request.getParameter("capgeminiEmailId");
			password = userModel.getPassword(username, emailId);
			if (password != null) {
				request.setAttribute("message", "Your Password is ");
				request.setAttribute("password", password);
			}
			request.getRequestDispatcher("/CommonJsp/ForgotPassword.jsp")
					.forward(request, response);

		}


		// User clicks on Reset Password link
		if (action.equalsIgnoreCase("ResetPassword")) {

			int rowsaffected = 0;
			String oldPassword = request.getParameter("oldPassword");
			String newPassword = request.getParameter("newPassword");
			String username = (String) session.getAttribute("username");
			System.out.println(username);
			rowsaffected = userModel.resetPassword(oldPassword, newPassword,
					username);
			if (rowsaffected > 0) {
				request.setAttribute("message",
						"Your password has been changed!");
			}
			if(request.getParameter("userType").equals("admin"))
			{
				request.getRequestDispatcher("/AdminJsp/ResetPassword.jsp").forward(request, response);
			}
			if(request.getParameter("userType").equals("nonAdmin"))
			{
				request.getRequestDispatcher("/UserJsp/ResetPassword.jsp").forward(request, response);
			}

		}
		
		if (action.equalsIgnoreCase("ResetPasswordJsp"))
		{
			if(request.getParameter("userType").equals("admin"))
			{
				request.getRequestDispatcher("/AdminJsp/ResetPassword.jsp").forward(request, response);
			}
			if(request.getParameter("userType").equals("nonAdmin"))
			{
				request.getRequestDispatcher("/UserJsp/ResetPassword.jsp").forward(request, response);
			}
			
		}
		
		// User clicks on Log Out
		if (action.equalsIgnoreCase("Logout")) {

			HttpSession session = request.getSession(true);
			Enumeration attributeNames = session.getAttributeNames(); // get all attribute names associated with session			
			while (attributeNames.hasMoreElements()) // destroy session
			{
				String name = (String) attributeNames.nextElement();
				String value = session.getAttribute(name).toString();
				session.removeAttribute(name);
				System.out.println(name + "=" + value + " has been cleared");
				response.sendRedirect("./CommonJsp/Logout.jsp");
				return;
			}
		}
		
		if (action.equalsIgnoreCase("ActivateUser")) {

			Map<Integer, List<UserDTO>> hashmap = null;
			HttpSession session = request.getSession(false);
			String username=(String)session.getAttribute("username");
			System.out.println("Username is "+username);
			hashmap = userModel.viewUserDetails(username);
			List<UserDTO> list1 = (List<UserDTO>) hashmap.get(1);
			List<UserDTO> list2 = (List<UserDTO>) hashmap.get(2);
			List<UserDTO> list3 = (List<UserDTO>) hashmap.get(3);
			List<UserDTO> list4 = (List<UserDTO>) hashmap.get(4);
			List<UserDTO> list5 = (List<UserDTO>) hashmap.get(5);
			List<UserDTO> list6 = (List<UserDTO>) hashmap.get(6);
			request.setAttribute("list1", list1);
			request.setAttribute("list2", list2);
			request.setAttribute("list3", list3);
			request.setAttribute("list4", list4);
			request.setAttribute("list5", list5);
			request.setAttribute("list6", list6);

			request.getRequestDispatcher("/AdminJsp/ActivateUser.jsp").forward(
					request, response);
		}
		
		//
		Anirudh
		if (action.equalsIgnoreCase("ViewUserJsp"))
		{
			String errorMessage = ""; 
			UserManagementModule userManagementModule = new UserManagementModule();
			List<UserDTO> allUsersList = userManagementModule.viewAllUsers("Y");
			if(allUsersList==null)
			{
				errorMessage = "No Users";
			}
			request.setAttribute("allUsersList", allUsersList);
			request.setAttribute("errorMessage", errorMessage);
			if(request.getParameter("userType").equals("admin"))
			{
				request.getRequestDispatcher("/AdminJsp/ViewUsers.jsp").forward(request, response);
			}
			if(request.getParameter("userType").equals("nonAdmin"))
			{
				request.getRequestDispatcher("/UserJsp/ViewUsers.jsp").forward(request, response);
			}
		}
		
		if (action.equalsIgnoreCase("ViewMyProfile")) {

			 Map<Integer,List<UserDTO>> hashmap = null;	
			 UserManagementModule userManagementModule = new UserManagementModule();	
			 hashmap = userManagementModule.viewUserDetails(request.getParameter("username"));	
			List<UserDTO> list1 = (List<UserDTO>)hashmap.get(1);	
			List<UserDTO> list2 = (List<UserDTO>)hashmap.get(2);	
			List<UserDTO> list3 = (List<UserDTO>)hashmap.get(3);	
			 List<UserDTO> list4 = (List<UserDTO>)hashmap.get(4);	
			List<UserDTO> list6 = (List<UserDTO>)hashmap.get(6);	
			List<UserDTO> list7 = (List<UserDTO>)hashmap.get(7);	
			request.setAttribute("list1", list1);	
			request.setAttribute("list2", list2);	
			request.setAttribute("list3", list3);	
			request.setAttribute("list4", list4);	
			request.setAttribute("list6", list6);	
			request.setAttribute("list7", list7);	
				request.getRequestDispatcher("/UserJsp/UserMainPage.jsp").forward(
						request, response);
			}
		
		
		if (action.equalsIgnoreCase("ViewUserDetailsServlet")) 
		{
			 Map<Integer,List<UserDTO>> hashmap = null;
			 UserManagementModule userManagementModule = new UserManagementModule();
			 hashmap = userManagementModule.viewUserDetails(request.getParameter("username"));
			 List<UserDTO> list1 = (List<UserDTO>)hashmap.get(1);
			 List<UserDTO> list2 = (List<UserDTO>)hashmap.get(2);
			 List<UserDTO> list3 = (List<UserDTO>)hashmap.get(3);
			 List<UserDTO> list4 = (List<UserDTO>)hashmap.get(4);
			 List<UserDTO> list6 = (List<UserDTO>)hashmap.get(6);
			 List<UserDTO> list7 = (List<UserDTO>)hashmap.get(7);
			 request.setAttribute("list1", list1);
			 request.setAttribute("list2", list2);
			 request.setAttribute("list3", list3);
			 request.setAttribute("list4", list4);
			 request.setAttribute("list6", list6);
			 request.setAttribute("list7", list7);
			 if(request.getParameter("userType").equals("admin"))
			 {
				 request.getRequestDispatcher("/AdminJsp/ViewUserDetails.jsp").forward(request, response);
			 }
			 if(request.getParameter("userType").equals("nonAdmin"))
			 {
				 request.getRequestDispatcher("/UserJsp/ViewUserDetails.jsp").forward(request, response);
			 }
		 }
		
		if (action.equalsIgnoreCase("ViewUserServlet")) 
		{
			UserManagementModule userManagementmodule = new UserManagementModule();
			String errorMessage1 = "";
			List<UserDTO> usersList = new ArrayList<UserDTO>();
			if (request.getParameter("searchType").equals("Search by User Name")) 
			{
				usersList = userManagementmodule.viewUsers(request.getParameter("userField"),"Search by User Name");
				if(usersList.isEmpty())
				{
					errorMessage1 = "No Match found";
				}
			}
			else if (request.getParameter("searchType").equals("Search by CG Manager Name")) 
			{
				usersList = userManagementmodule.viewUsers(request.getParameter("userField"),"Search by CG Manager Name");
				if(usersList.isEmpty())
				{
					errorMessage1 = "No Match found";
				}
			} 
			else if (request.getParameter("searchType").equals("Search by Project Name")) 
			{
				Map<Integer, List<UserDTO>> hashmap = userManagementmodule.viewUsersByProject(request.getParameter("userField"));
				usersList = (List<UserDTO>)hashmap.get(1);
				List<UserDTO> usersList1 = (List<UserDTO>)hashmap.get(2);
				if(usersList==null)
				{
					errorMessage1 = "No Match found";
				}
				request.setAttribute("usersList1", usersList1);
			}
			request.setAttribute("errorMessage1", errorMessage1);
			request.setAttribute("usersList", usersList);
			if(request.getParameter("userType").equals("admin"))
			 {
				request.getRequestDispatcher("/AdminJsp/ViewUsers.jsp").forward(request, response);
			 }
			 if(request.getParameter("userType").equals("nonAdmin"))
			 {
				 request.getRequestDispatcher("/UserJsp/ViewUsers.jsp").forward(request, response);
			 }
		} 
		
		if (request.getParameter("action").equals("ViewInactiveUsersJsp"))
		{
			String errorMessage = ""; 
			UserManagementModule userManagementModule = new UserManagementModule();
			List<UserDTO> allUsersList = userManagementModule.viewAllUsers("N");
			if(allUsersList==null)
			{
				errorMessage = "No Users";
			}
			request.setAttribute("allUsersList", allUsersList);
			request.setAttribute("errorMessage", errorMessage);
			request.getRequestDispatcher("/AdminJsp/ViewUsers.jsp").forward(request, response);
		}
		
		if (action.equalsIgnoreCase("ViewProjectJsp"))
		{
			String projectMessage = ""; 
			ProjectManagementModule projectManagementModule = new ProjectManagementModule();
			List<ProjectDTO> allProjectsList = projectManagementModule.viewAllProjects("On Going");
			if(allProjectsList==null)
			{
				projectMessage = "No Projects";
			}
			request.setAttribute("allProjectsList", allProjectsList);
			request.setAttribute("projectMessage", projectMessage);
			 if(request.getParameter("userType").equals("admin"))
			 {
				 request.getRequestDispatcher("/AdminJsp/ViewProjects.jsp").forward(request, response);
			 }
			 if(request.getParameter("userType").equals("nonAdmin"))
			 {
				 request.getRequestDispatcher("/UserJsp/ViewProjects.jsp").forward(request, response);
			 }
		}
		
		if (action.equalsIgnoreCase("ViewProjectServlet"))
		{
			String projectMessage = "";
			ProjectManagementModule projectManagementmodule = new ProjectManagementModule();
			List<ProjectDTO> projectLists = projectManagementmodule.viewProject(request.getParameter("projectNameValue"));
			if(projectLists==null)
			{
				projectMessage = "No Project found";
			}
			request.setAttribute("projectMessage", projectMessage);
			request.setAttribute("projectLists", projectLists);
			 if(request.getParameter("userType").equals("admin"))
			 {
				 request.getRequestDispatcher("/AdminJsp/ViewProjects.jsp").forward(request, response);
			 }
			 if(request.getParameter("userType").equals("nonAdmin"))
			 {
				 request.getRequestDispatcher("/UserJsp/ViewProjects.jsp").forward(request, response);
			 }
		}
		
		if (action.equalsIgnoreCase("ViewProjectDetailsServlet")) 
		 {
			 ProjectManagementModule projectManagementModule = new ProjectManagementModule();
			 Map<Integer, List<ProjectDTO>> hashmap = projectManagementModule.getProjectDetails(request.getParameter("projectName"));
			 List<ProjectDTO> projectDetailsList = (List<ProjectDTO>)hashmap.get(1);
			 List<ProjectDTO> locationList = (List<ProjectDTO>)hashmap.get(2);
			 List<ProjectDTO> userDetailsList = (List<ProjectDTO>)hashmap.get(3);
			 List<ProjectDTO> managerDetailsList = (List<ProjectDTO>)hashmap.get(4);
			 List<ResourceDTO> projectFilesList = projectManagementModule.fileList(request.getParameter("projectName"));
			 request.setAttribute("projectFilesList", projectFilesList);
			 request.setAttribute("projectDetailsList", projectDetailsList);
			 request.setAttribute("locationList", locationList);
			 request.setAttribute("userDetailsList", userDetailsList);
			 request.setAttribute("managerDetailsList", managerDetailsList);
			 if(request.getParameter("userType").equals("admin"))
			 {
				 request.getRequestDispatcher("/AdminJsp/ViewProjectDetails.jsp").forward(request, response);
			 }
			 if(request.getParameter("userType").equals("nonAdmin"))
			 {
				 request.getRequestDispatcher("/UserJsp/ViewProjectDetails.jsp").forward(request, response);
			 }
		 }
		
		 if (action.equalsIgnoreCase("FileUploadJsp")) 
		 {
			 UserManagementModule userManagementModule = new UserManagementModule();
			 List<UserDTO> projectCodeList = userManagementModule.getProjectCode();
			 request.setAttribute("projectCodeList", projectCodeList);
			 if(request.getParameter("userType").equals("admin"))
			 {
				 request.getRequestDispatcher("/AdminJsp/FileDetails.jsp").forward(request, response);
			 }
			 if(request.getParameter("userType").equals("nonAdmin"))
			 {
				 request.getRequestDispatcher("/UserJsp/FileDetails.jsp").forward(request, response);
			 }
		 }
		 
		 if (action.equalsIgnoreCase("GetFileDetails")) 
		 {
			 HttpSession session = request.getSession(false);
			 session.setAttribute("projectCode", request.getParameter("projectCode"));
			 session.setAttribute("fileName", request.getParameter("fileName"));
			 session.setAttribute("uploadDate", request.getParameter("uploadDate"));
			 session.setAttribute("fileGroup", request.getParameter("fileGroup"));
			 if(request.getParameter("userType").equals("admin"))
			 {
				 request.getRequestDispatcher("/AdminJsp/FileUpload.jsp").forward(request, response);
			 }
			 if(request.getParameter("userType").equals("nonAdmin"))
			 {
				 request.getRequestDispatcher("/UserJsp/FileUpload.jsp").forward(request, response);
			 }
		 }
		 
		 if (action.equalsIgnoreCase("FileUploadServlet")) 
		 {
			HttpSession session = request.getSession(false);
			ProjectManagementModule projectManagementModule = new ProjectManagementModule();
			String projectCode = (String)session.getAttribute("projectCode");
			String fileName = (String)session.getAttribute("fileName");
			String uploadDate = (String)session.getAttribute("uploadDate");
			String fileGroup = (String)session.getAttribute("fileGroup");
			boolean flagFileUpload = projectManagementModule.uploadFile(request,projectCode,fileName,uploadDate,fileGroup);
			if(!flagFileUpload)
			request.setAttribute("fileUploadMessage", "The File "+(String)session.getAttribute("fileName")+" could not be uploaded");
			else
			request.setAttribute("fileUploadMessage", "The File "+(String)session.getAttribute("fileName")+" uploaded succesfully");
			 if(request.getParameter("userType").equals("admin"))
			 {
				 request.getRequestDispatcher("./UserServlet?action=FileUploadJsp&userType=admin").forward(request, response);
			 }
			 if(request.getParameter("userType").equals("nonAdmin"))
			 {
				 request.getRequestDispatcher("./UserServlet?action=FileUploadJsp&userType=nonAdmin").forward(request, response);
			 }
		 }
		 
		 if (action.equalsIgnoreCase("ViewPollsServlet")) 
		 {
			 UserManagementModule userManagementModule = new UserManagementModule();
			 List<ResourceDTO> allPollsList = userManagementModule.viewPolls(request.getParameter("status"));
			 if(allPollsList.isEmpty())
			 {
				 request.setAttribute("emptyallPollsList", "No "+request.getParameter("status")+" Polls found");
			 }
			 request.setAttribute("allPollsList", allPollsList);
			 if(request.getParameter("userType").equals("admin"))
			 {
				 request.getRequestDispatcher("/AdminJsp/ViewPolls.jsp").forward(request, response);
			 }
			 if(request.getParameter("userType").equals("nonAdmin"))
			 {
				 request.getRequestDispatcher("/UserJsp/ViewPolls.jsp").forward(request, response);
			 }
		 }
		 
		 if (action.equalsIgnoreCase("PollSearchServlet")) 
		 {
			 UserManagementModule userManagementModule = new UserManagementModule();
			 List<ResourceDTO> pollsList = userManagementModule.pollSearch(request.getParameter("pollTopic"));
			 if(pollsList.isEmpty())
			 {
				 request.setAttribute("emptypollsList", "No search for "+request.getParameter("pollTopic"));
			 }
			 request.setAttribute("pollsList", pollsList);
			 if(request.getParameter("userType").equals("admin"))
			 {
				 request.getRequestDispatcher("/AdminJsp/ViewPolls.jsp").forward(request, response);
			 }
			 if(request.getParameter("userType").equals("nonAdmin"))
			 {
				 request.getRequestDispatcher("/UserJsp/ViewPolls.jsp").forward(request, response);
			 }
		 }
		 
		 if (action.equalsIgnoreCase("PollResultsServlet"))
		 {
			 UserManagementModule userManagementModule = new UserManagementModule();
			 List<ResourceDTO> pollsList = userManagementModule.pollDetails(request.getParameter("pollTopic"));
			 List<ResourceDTO> pollReplyList = userManagementModule.displayPollReply(request.getParameter("pollTopic"));
			 request.setAttribute("pollsList", pollsList);
			 request.setAttribute("pollReplyList", pollReplyList);
			 if(request.getParameter("userType").equals("admin"))
			 {
				 request.getRequestDispatcher("/AdminJsp/PollResults.jsp").forward(request, response);
			 }
			 if(request.getParameter("userType").equals("nonAdmin"))
			 {
				 request.getRequestDispatcher("/UserJsp/PollResults.jsp").forward(request, response);
			 }
		 }
		 
		 if (action.equalsIgnoreCase("AddPollReplyServlet"))
		 {
			 UserManagementModule userManagementModule = new UserManagementModule();
			 ResourceDTO resourceDTO = new ResourceDTO();
			 resourceDTO.setPollTopic(request.getParameter("pollTopic"));
			 //to be retreived from session
			 resourceDTO.setUsername("abhishek");
			 resourceDTO.setPollReplyText(request.getParameter("pollReply"));
			 resourceDTO.setPollReplyDate(GetSqlDate.getDate(request.getParameter("pollReplyDate")));
			 //to be retreived by session
			 resourceDTO.setCreateUserId("naveen");
			 resourceDTO.setCreateDate(GetSqlDate.getDate(request.getParameter("pollReplyDate")));
			//to be retreived by session
			 resourceDTO.setModifyUserId("naveen");
			 resourceDTO.setModifydate(GetSqlDate.getDate(request.getParameter("pollReplyDate")));
			 boolean flagPollReply = userManagementModule.addPollReply(resourceDTO);
			 if(!flagPollReply)
			 request.setAttribute("pollReplyMessage", "Reply not added in the poll");
			 else
			 request.setAttribute("pollReplyMessage", "Reply added succesfully in the poll");
			 request.getRequestDispatcher("./UserServlet?action=PollResultsServlet&pollTopic="+request.getParameter("pollTopic")).forward(request, response);
		 }
		 
		 if (action.equalsIgnoreCase("AddUserJsp")) 
			{
				UserManagementModule userManagementModule = new UserManagementModule();
				List<String> designationList = DesignationNamesUtil.getDesignationNames();
				List<String> skillList = userManagementModule.getSkills();
				request.setAttribute("hierarchyMessage", "Select a manager");
				request.setAttribute("designationList", designationList);
				request.setAttribute("skillList", skillList);
				request.getRequestDispatcher("/AdminJsp/AddUser.jsp").forward(request, response);
			}
		 
		 if (request.getParameter("action").equals("DisplayManagerNamesServlet")) 
			{
				String designation = request.getParameter("designation");
				String hierarchyMessage ="";
				UserManagementModule userManagementModule = new UserManagementModule();
				List<String> managerList = userManagementModule.getManagerList(designation);
				if(managerList.isEmpty())
				{
					hierarchyMessage = "Not Available";
					request.setAttribute("hierarchyMessage", hierarchyMessage);
				}
				List<String> designationList = DesignationNamesUtil.getDesignationNames();
				List<String> skillList = userManagementModule.getSkills();
				request.setAttribute("designationList", designationList);
				request.setAttribute("managerList", managerList);
				request.setAttribute("skillList", skillList);
				request.getRequestDispatcher("/AdminJsp/AddUser.jsp").forward(request, response);
			}
		 
		 if (request.getParameter("action").equals("AddUserServlet")) 
			{
				UserDTO userDTO = new UserDTO();
				boolean flagAddUser = false;
				userDTO.setEmployeeId(Integer.parseInt(request.getParameter("employeeId")));
				userDTO.setUsername(request.getParameter("capgeminiUsername"));
				userDTO.setPassword(request.getParameter("capgeminiUsername"));
				userDTO.setUserType(request.getParameter("userType"));
				userDTO.setFirstName(request.getParameter("firstName"));
				userDTO.setLastName(request.getParameter("lastName"));
				userDTO.setCiscoEmailId(request.getParameter("ciscoEmailId"));
				userDTO.setCapgeminiEmailId(request.getParameter("ciscoEmailId"));
				userDTO.setPrimaryContactNumber(request.getParameter("primaryContactNo"));
				userDTO.setEmergencyContactNumber(request.getParameter("emergencyContactNo"));
				userDTO.setCiscoDOJ(GetSqlDate.getDate(request.getParameter("ciscoDOJ")));
				userDTO.setDesignation(request.getParameter("designation"));
				userDTO.setIsBillable("N");
				userDTO.setIsActive("Y");
				if(request.getParameter("designation").equals("AD"))
				{
					userDTO.setCapgeminiManagerName(request.getParameter("capgeminiUsername"));
				}
				else
				{
					userDTO.setCapgeminiManagerName(request.getParameter("cgManagerUsername"));
				}
				userDTO.setCiscoManagerName(request.getParameter("ciscoManagerUsername"));
				userDTO.setTotalExperience(Integer.parseInt(request.getParameter("experience")));
				userDTO.setServiceLine(request.getParameter("serviceLine"));
				userDTO.setPrimarySkill(request.getParameter("primarySkill"));
				if(request.getParameter("secondarySkill1").equals("Select your Skill"))
				{
					userDTO.setSecondarySkill_01("");
				}
				else if(request.getParameter("secondarySkill2").equals("Select your Skill"))
				{
					userDTO.setSecondarySkill_02("");
				}
				else
				{
					userDTO.setSecondarySkill_01(request.getParameter("secondarySkill1"));
					userDTO.setSecondarySkill_02(request.getParameter("secondarySkill2"));
				}
				userDTO.setCreateUserId(request.getParameter("capgeminiUsername"));
				userDTO.setCreateDate(GetSqlDate.getDate(request.getParameter("ciscoDOJ")));
				userDTO.setModifyUserId(request.getParameter("capgeminiUsername"));
				userDTO.setModifydate(GetSqlDate.getDate(request.getParameter("ciscoDOJ")));
				userDTO.setCiscoUsername(request.getParameter("capgeminiUsername"));
				userDTO.setIs_delivery_manager(request.getParameter("deliveryManager"));
				
				UserManagementModule userManagementModule = new UserManagementModule();
				try 
				{
					flagAddUser = userManagementModule.addUser(userDTO);
				}
				catch (Exception e) 
				{
					System.out.println("error2 in : " + e.getMessage());
				}
				if (flagAddUser)
				{
					request.setAttribute("addUserMessage", "User "+ request.getParameter("capgeminiUsername")+ " already exists!");
					request.getRequestDispatcher("./UserServlet?action=AddUserJsp").forward(request, response);
				}
				else
				{
					request.setAttribute("addUserMessage", "User "+ request.getParameter("capgeminiUsername")+ " added succesfully");
					request.getRequestDispatcher("./UserServlet?action=ViewUserJspAdmin").forward(request, response);
				}
			} 
		 
		 if (request.getParameter("action").equals("TagUserJsp")) 
		 {
			 	UserManagementModule userManagementModule = new UserManagementModule();
				ProjectManagementModule projectManagementModule = new ProjectManagementModule();
				List<UserDTO> untaggedUsersList = userManagementModule.getUntaggedUsers();
				List<UserDTO> projectCodeList = userManagementModule.getProjectCode();
				List<UserDTO> UserRoleList = userManagementModule.getUserRole();
				List<ProjectDTO> locationNamesList = projectManagementModule.fillLocationNames();
				request.setAttribute("untaggedUsersList", untaggedUsersList);
				request.setAttribute("projectCodeList", projectCodeList);
				request.setAttribute("UserRoleList", UserRoleList);
				request.setAttribute("locationNamesList", locationNamesList);
				request.getRequestDispatcher("/AdminJsp/TagUser.jsp").forward(request,response);
		 } 
			
		 if (request.getParameter("action").equals("TagUserServlet")) 
		 {
			 	boolean flagIfManagerTagged = false;	
			 	boolean flagCheckManager = false;
			 	boolean flagTagUser = false;
			 	String tagUserStatus = "";
			 	UserDTO userDTO = new UserDTO();
				userDTO.setUsername(request.getParameter("capgeminiUsername"));
				userDTO.setProject_code(Integer.parseInt(request.getParameter("projectCode")));
				userDTO.setBillHours(Integer.parseInt(request.getParameter("billHours")));
				userDTO.setStartDate(GetSqlDate.getDate(request.getParameter("startDate")));
				userDTO.setEndDate(GetSqlDate.getDate(request.getParameter("endDate")));
				userDTO.setIsPresentlyTagged("Y");
				if (request.getParameter("region").equals("On Shore")) 
				{
					userDTO.setIsOnShore("Y");
				}
				else 
				{
					userDTO.setIsOnShore("N");
				}
				userDTO.setProjectRoleID(FetchProjectRoleIDUtil.getProjectRoleID(request.getParameter("userRole")));
				userDTO.setLocationID(FetchLocationUtil.getLocationID(request.getParameter("location")));
				userDTO.setCreateUserId("naveen");
				userDTO.setCreateDate(GetSqlDate.getDate(request.getParameter("startDate")));
				userDTO.setModifyUserId("naveen");
				userDTO.setModifydate(GetSqlDate.getDate(request.getParameter("startDate")));
				userDTO.setCiscoUsername(request.getParameter("ciscoUsername"));
				UserManagementModule userManagementModule = new UserManagementModule();
				if(request.getParameter("userRole").equals("Manager"))
				{
					flagIfManagerTagged = userManagementModule.ifManagerTagged(Integer.parseInt(request.getParameter("projectCode")));
					flagCheckManager = userManagementModule.checkManager(request.getParameter("capgeminiUsername"));
					if(flagIfManagerTagged)
					tagUserStatus = "Manager is already for Project Code: "+request.getParameter("projectCode");
					if(!flagCheckManager)
					tagUserStatus = request.getParameter("capgeminiUsername")+" is not a manager by designation";
					request.setAttribute("tagUserStatus", tagUserStatus);
					request.getRequestDispatcher("./UserServlet?action=TagUserJsp").forward(request, response);
				}
				else
				{
					flagTagUser = userManagementModule.tagUserToProject(userDTO);
					if(flagTagUser)
					tagUserStatus = "User "+request.getParameter("capgeminiUsername")+" has been tagged successfully for project code "+request.getParameter("projectCode");
					request.setAttribute("tagUserStatus", tagUserStatus);
					request.getRequestDispatcher("./UserServlet?action=TagUserJsp").forward(request, response);
				}
		 }
		 
		 if (request.getParameter("action").equals("ViewCompletedProjectJsp"))
			{
				String projectMessage = ""; 
				ProjectManagementModule projectManagementModule = new ProjectManagementModule();
				List<ProjectDTO> allProjectsList = projectManagementModule.viewAllProjects("Completed");
				if(allProjectsList==null)
				{
					projectMessage = "No Projects";
				}
				request.setAttribute("allProjectsList", allProjectsList);
				request.setAttribute("projectMessage", projectMessage);
				request.getRequestDispatcher("/AdminJsp/ViewProjects.jsp").forward(request, response);
			}
		 
		 if (action.equalsIgnoreCase("AddProjectJsp")) 
		 {
			ProjectManagementModule projectManagementModule = new ProjectManagementModule();
			List<UserDTO> projectManagerList = projectManagementModule.fillManagerNames();
			List<ProjectDTO> locationNamesList = projectManagementModule.fillLocationNames();
			request.setAttribute("projectManagerList", projectManagerList);
			request.setAttribute("locationNamesList", locationNamesList);
			request.getRequestDispatcher("/AdminJsp/AddProject.jsp").forward(request, response);
		 } 
		 
		 if (action.equalsIgnoreCase("AddProjectServlet")) 
		 {
			 	boolean flagAddProject = false;
			 	//for adding the project
				ProjectDTO projectDTO = new ProjectDTO();
				projectDTO.setProject_code(Integer.parseInt(request.getParameter("projectCode")));
				projectDTO.setProjectName(request.getParameter("projectName"));
				projectDTO.setProjectObjective(request.getParameter("projectObjective"));
				projectDTO.setProjectManager(request.getParameter("projectManager"));
				projectDTO.setTimeOfCompletion(Integer.parseInt(request.getParameter("completionTime")));
				projectDTO.setNoOfModules(Integer.parseInt(request.getParameter("modules")));
				projectDTO.setLocationID(FetchLocationUtil.getLocationID(request.getParameter("location")));
				projectDTO.setStartDate(GetSqlDate.getDate(request.getParameter("startDate")));
				projectDTO.setEndDate(GetSqlDate.getDate(request.getParameter("endDate")));
				projectDTO.setNoOfTechnicalResources(Integer.parseInt(request.getParameter("noOfTechnicalResources")));
				projectDTO.setNoOfNonTechnicalResources(Integer.parseInt(request.getParameter("noOfNonTechnicalResources")));
				projectDTO.setStatus("On Going");
				// name would be retreived from session id.
				projectDTO.setCreateUserId("naveen");
				projectDTO.setCreateDate(GetSqlDate.getDate(request.getParameter("startDate")));
				projectDTO.setModifyUserId("naveen");
				projectDTO.setModifydate(GetSqlDate.getDate(request.getParameter("startDate")));
				
				ProjectManagementModule projectManagementModule = new ProjectManagementModule();
				try 
				{
					flagAddProject = projectManagementModule.addProject(projectDTO);
					if(flagAddProject)
					{
						request.setAttribute("addProjectMessage", "Project "+request.getParameter("projectName")+" already exists");
						request.getRequestDispatcher("./UserServlet?action=AddProjectJsp").forward(request, response);
					}
					else
					{
						request.setAttribute("addProjectMessage", "Project "+request.getParameter("projectName")+" added succesfully");
						request.getRequestDispatcher("./UserServlet?action=ViewProjectJsp").forward(request, response);
					}
				} 
				catch (Exception e) 
				{
					System.out.println("error27 in : " + e.getMessage());
				}
		 }
		 
		 if (action.equalsIgnoreCase("CreatePollServlet")) 
		 {
			ResourceDTO resourceDTO = new ResourceDTO();
			resourceDTO.setPollTopic(request.getParameter("pollTopic"));
			resourceDTO.setPollDescription(request.getParameter("description"));
			resourceDTO.setPollType(request.getParameter("pollType"));
			resourceDTO.setPollEndDate(GetSqlDate.getDate(request.getParameter("endDate")));
			resourceDTO.setPollStatus("OnGoing");
			resourceDTO.setCreateUserId("naveen");
			resourceDTO.setCreateDate(GetSqlDate.getDate(request.getParameter("startDate")));
			resourceDTO.setModifyUserId("naveen");
			resourceDTO.setModifydate(GetSqlDate.getDate(request.getParameter("startDate")));
			UserManagementModule userManagementModule = new UserManagementModule();
			boolean flagCreatePoll = userManagementModule.createPoll(resourceDTO);
			if(!flagCreatePoll)
			{
				request.setAttribute("createPollMessage", "Poll "+request.getParameter("pollTopic")+" not created succesfully");
				request.getRequestDispatcher("/AdminJsp/CreatePoll.jsp").forward(request, response);
			}
			else
			{
				request.setAttribute("createPollMessage", "Poll "+request.getParameter("pollTopic")+" created succesfully");
				request.getRequestDispatcher("./UserServlet?action=ViewPollsServlet&status=OnGoing").forward(request, response);
			}
		 }
		 
		 if (action.equalsIgnoreCase("CreatePollJsp"))
		 {
			 request.getRequestDispatcher("/AdminJsp/CreatePoll.jsp").forward(request, response);
		 }
		 
	}*/

}
