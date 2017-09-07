package com.capgemini.cisco.portal.controller;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
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
import com.capgemini.cisco.portal.utility.CheckUserTagging;
import com.capgemini.cisco.portal.utility.DesignationNamesUtil;
import com.capgemini.cisco.portal.utility.DisplayPollUsingPagination;
import com.capgemini.cisco.portal.utility.DisplayProjectsUsingPagination;
import com.capgemini.cisco.portal.utility.DisplayUsersUsingPagination;
import com.capgemini.cisco.portal.utility.FetchLocationUtil;
import com.capgemini.cisco.portal.utility.FetchProjectRoleIDUtil;
import com.capgemini.cisco.portal.utility.GetSqlDate;
import com.capgemini.cisco.portal.utility.RetrieveProjectDetails;
import com.capgemini.cisco.portal.utility.RetrieveUserDetails;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
	Date sessionCreatedDate;
	Date sessionLastAccessedDate;

	public UserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		UserManagementModule userModel = new UserManagementModule();
		ProjectManagementModule projectModel = new ProjectManagementModule();
		UserDTO userDTO = new UserDTO();
		System.out.println(action);
		if (action.equals("HierarchyServlet")) {

			List<String> list = userModel.getVPandADNames();
			String ADName = list.get(0);
			String VPName = list.get(1);
			request.setAttribute("VPName", VPName);
			request.setAttribute("ADName", ADName);
			request.getRequestDispatcher("/jsp/Hierarchy.jsp").forward(request,
					response);
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

		// To redirect to the Add Skill JSP Page
		if (action.equals("AddSkillJsp")) {

			List<String> skillList = userModel.viewSkills();
			request.setAttribute("skillList", skillList);
			request.getRequestDispatcher("AdminJsp/AddSkill.jsp").forward(
					request, response);
		}

		// To add a new skill
		if (action.equals("AddSkillServlet")) {

			String skillName = request.getParameter("skillName");
			String message = "";
			String username = (String) session.getAttribute("username");
			int rowsaffected = userModel.addSkill(skillName, username);
			if (rowsaffected > 0) {
				message = "The skill has been successfully added to the list!";
			} else {
				message = "The skill could not be added to the list";
			}
			request.setAttribute("message", message);
			List<String> skillList = userModel.viewSkills();
			request.setAttribute("skillList", skillList);
			request.getRequestDispatcher("AdminJsp/AddSkill.jsp").forward(
					request, response);
		}

		// User enters his username and password and clicks on Login
		if (action.equalsIgnoreCase("LoginAuthentication")) {
			String userType = "";
			String dispatch_user = "./UserServlet?action=ViewMyProfile";
			String dispatch_admin = "./UserServlet?action=ViewUserJsp&userType=admin";
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			userType = userModel.loginAuthentication(username, password);
			if (userType.equals("No User found")) {
				request.setAttribute("loginMessage",
						"Username and password do not match");
				request.getRequestDispatcher("./LoginPage.jsp").forward(
						request, response);
			} else {
				session = request.getSession(true);
				System.out.println(session.isNew());
				System.out.println(session.getId());
				System.out.println(session.getMaxInactiveInterval());

				session.setAttribute("username", username);
				session.setAttribute("valid", "yes");
				System.out.println("usertype is: " + userType);
				request.setAttribute("usertype", userType);
				if (userType.equals("Admin")) {
					request.getRequestDispatcher(dispatch_admin).forward(
							request, response);
				} else if (userType.equals("Non-Admin")) {
					request.getRequestDispatcher(dispatch_user).forward(
							request, response);
				}
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
				// "Your password has been changed!");
			}
			if (request.getParameter("userType").equals("admin")) {
				request.getRequestDispatcher("/AdminJsp/ResetPassword.jsp")
						.forward(request, response);
			}
			if (request.getParameter("userType").equals("nonAdmin")) {
				request.getRequestDispatcher("/UserJsp/ResetPassword.jsp")
						.forward(request, response);
			}

		}

		if (action.equalsIgnoreCase("ResetPasswordJsp")) {
			if (request.getParameter("userType").equals("admin")) {
				request.getRequestDispatcher("/AdminJsp/ResetPassword.jsp")
						.forward(request, response);
			}
			if (request.getParameter("userType").equals("nonAdmin")) {
				request.getRequestDispatcher("/UserJsp/ResetPassword.jsp")
						.forward(request, response);
			}

		}

		// User clicks on Log Out
		if (action.equalsIgnoreCase("Logout")) {

			System.out.println("logout");
			HttpSession session = request.getSession(true);
			Enumeration attributeNames = session.getAttributeNames(); // get all
																		// attribute
																		// names
																		// associated
																		// with
																		// session
			while (attributeNames.hasMoreElements()) // destroy session
			{
				String name = (String) attributeNames.nextElement();
				String value = session.getAttribute(name).toString();
				session.removeAttribute(name);
				System.out.println(name + "=" + value + " has been cleared");
			}
			session.invalidate();
			response.sendRedirect("./CommonJsp/Logout.jsp");
			return;
		}

		// To redirect to the Activate User Page
		if (action.equals("ActivateUserJsp")) {

			List<UserDTO> userList = userModel.viewAllUsers("N");
			String message = null;
			if (userList == null) {
				message = "No Inactive Users";
			}
			request.setAttribute("message", message);
			request.setAttribute("userList", userList);
			request.getRequestDispatcher("AdminJsp/ActivateUser.jsp").forward(
					request, response);
		}

		// Activate User
		if (action.equalsIgnoreCase("ActivateUserServlet")) {

			String username = request.getParameter("username");
			System.out.println("Activate user" + username);
			int rowsaffected = userModel.activateUser(username);
			String activeMessage = null;
			if (rowsaffected > 0) {
				activeMessage = "The user " + username + " is now active!";
			}
			List<UserDTO> userList = userModel.viewAllUsers("N");
			String message = null;
			if (userList == null) {
				message = "No Inactive Users";
			}
			request.setAttribute("message", message);
			request.setAttribute("userList", userList);
			request.setAttribute("activeMessage", activeMessage);

			request.getRequestDispatcher("/AdminJsp/ActivateUser.jsp").forward(
					request, response);
		}

		/* Anirudh */
		// View All users
		if (action.equalsIgnoreCase("ViewUserJsp")) {
			String userType = request.getParameter("userType");
			DisplayUsersUsingPagination.displayUsersPagination(request,
					response);
			if (userType.equals("admin")) {
				request.getRequestDispatcher("/AdminJsp/ViewUsers.jsp")
						.forward(request, response);
			} else if (userType.equals("nonAdmin")) {
				request.getRequestDispatcher("/UserJsp/ViewUsers.jsp").forward(

				request, response);

			}

		}

		if (action.equalsIgnoreCase("ViewMyProfile")) {

			Map<Integer, List<UserDTO>> hashmap = null;
			UserManagementModule userManagementModule = new UserManagementModule();
			hashmap = userManagementModule.viewUserDetails(request
					.getParameter("username"));
			List<UserDTO> list1 = (List<UserDTO>) hashmap.get(1);
			List<UserDTO> list2 = (List<UserDTO>) hashmap.get(2);
			List<UserDTO> list3 = (List<UserDTO>) hashmap.get(3);
			List<UserDTO> list4 = (List<UserDTO>) hashmap.get(4);
			List<UserDTO> list6 = (List<UserDTO>) hashmap.get(6);
			List<UserDTO> list7 = (List<UserDTO>) hashmap.get(7);
			session.setAttribute("list1", list1);
			session.setAttribute("list2", list2);
			session.setAttribute("list3", list3);
			session.setAttribute("list4", list4);
			session.setAttribute("list6", list6);
			session.setAttribute("list7", list7);
			request.getRequestDispatcher("/UserJsp/UserMainPage.jsp").forward(
					request, response);
		}

		if (action.equalsIgnoreCase("ViewUserDetailsServlet")) {
			Map<Integer, List<UserDTO>> hashmap = null;
			UserManagementModule userManagementModule = new UserManagementModule();
			hashmap = userManagementModule.viewUserDetails(request
					.getParameter("username"));
			System.out.println("username is:"
					+ request.getParameter("username"));
			List<UserDTO> usersList = userManagementModule
					.getUsersUnderManager(request.getParameter("managerName"));
			List<UserDTO> list1 = (List<UserDTO>) hashmap.get(1);
			List<UserDTO> list2 = (List<UserDTO>) hashmap.get(2);
			List<UserDTO> list3 = (List<UserDTO>) hashmap.get(3);
			List<UserDTO> list4 = (List<UserDTO>) hashmap.get(4);
			List<UserDTO> list6 = (List<UserDTO>) hashmap.get(6);
			List<UserDTO> list7 = (List<UserDTO>) hashmap.get(7);
			request.setAttribute("list1", list1);
			request.setAttribute("list2", list2);
			request.setAttribute("list3", list3);
			request.setAttribute("list4", list4);
			request.setAttribute("list6", list6);
			request.setAttribute("list7", list7);
			request.setAttribute("usersList", usersList);

			if (request.getParameter("userType").equals("admin")) {
				request.getRequestDispatcher("/AdminJsp/ViewUserDetails.jsp")
						.forward(request, response);
			}
			if (request.getParameter("userType").equals("nonAdmin")) {
				request.getRequestDispatcher("/UserJsp/ViewUserDetails.jsp")
						.forward(request, response);
			}
		}

		if (action.equalsIgnoreCase("ViewUserServlet")) {

			String errorMessage1 = "";

			List<UserDTO> usersList = new ArrayList<UserDTO>();
			if (request.getParameter("searchType")
					.equals("Search by User Name")) {
				usersList = userModel.viewUsers(
						request.getParameter("usernameField"),
						"Search by User Name");
				if (usersList.isEmpty()) {
					errorMessage1 = "No Match found";

				}

			} else if (request.getParameter("searchType").equals(
					"Search by CG Manager Name")) {
				usersList = userModel.viewUsers(
						request.getParameter("usernameField"),
						"Search by CG Manager Name");
				if (usersList.isEmpty()) {
					errorMessage1 = "No Match found";

				}

			} else if (request.getParameter("searchType").equals(
					"Search by Project Name")) {
				Map<Integer, List<UserDTO>> hashmap = userModel
						.viewUsersByProject(request
								.getParameter("usernameField"));
				usersList = (List<UserDTO>) hashmap.get(1);
				List<UserDTO> usersList1 = (List<UserDTO>) hashmap.get(2);
				if (usersList == null) {
					errorMessage1 = "No Match found";

				}

				request.setAttribute("usersList1", usersList1);
			}

			request.setAttribute("errorMessage1", errorMessage1);
			request.setAttribute("usersList", usersList);

			if (request.getParameter("userType").equals("admin")) {
				request.getRequestDispatcher("/AdminJsp/ViewUsers.jsp")
						.forward(request, response);
			}
			if (request.getParameter("userType").equals("nonAdmin")) {
				request.getRequestDispatcher("/UserJsp/ViewUsers.jsp").forward(
						request, response);
			}
		}

		if (request.getParameter("action").equals("ViewInactiveUsersJsp")) {
			String errorMessage = "";
			UserManagementModule userManagementModule = new UserManagementModule();
			List<UserDTO> allUsersList = userManagementModule.viewAllUsers("N");
			if (allUsersList == null) {
				errorMessage = "No Users";
			}
			request.setAttribute("allUsersList", allUsersList);
			request.setAttribute("errorMessage", errorMessage);
			request.getRequestDispatcher("/AdminJsp/ViewUsers.jsp").forward(
					request, response);
		}

		// View all projects
		if (action.equalsIgnoreCase("ViewProjectJsp")) {
			String status = request.getParameter("status");
			DisplayProjectsUsingPagination.displayProjectsPagination(request,
					response, status);
			if (request.getParameter("userType").equals("admin")) {
				request.getRequestDispatcher("/AdminJsp/ViewProjects.jsp")
						.forward(request, response);
			} else if (request.getParameter("userType").equals("nonAdmin")) {
				request.getRequestDispatcher("/UserJsp/ViewProjects.jsp")
						.forward(request, response);
			}
		}
		// For searching a project
		if (action.equalsIgnoreCase("ViewProjectServlet")) {
			String projectMessage = "";
			ProjectManagementModule projectManagementmodule = new ProjectManagementModule();
			List<ProjectDTO> projectLists = projectManagementmodule
					.viewProject(request.getParameter("projectNameValue"));
			if (projectLists == null) {
				projectMessage = "No Project found";
			}
			request.setAttribute("projectMessage", projectMessage);
			request.setAttribute("projectLists", projectLists);
			if (request.getParameter("userType").equals("admin")) {
				request.getRequestDispatcher("/AdminJsp/ViewProjects.jsp")
						.forward(request, response);
			}
			if (request.getParameter("userType").equals("nonAdmin")) {
				request.getRequestDispatcher("/UserJsp/ViewProjects.jsp")
						.forward(request, response);
			}
		}

		// Viewing a particular project's details }
		if (action.equalsIgnoreCase("ViewProjectDetailsServlet")) {

			String projectName = request.getParameter("projectName");
			RetrieveProjectDetails.getProjectDetails(projectName, request,
					response);
			if (request.getParameter("userType").equals("admin")) {
				request.getRequestDispatcher("/AdminJsp/ViewProjectDetails.jsp")
						.forward(request, response);
			}
			if (request.getParameter("userType").equals("nonAdmin")) {
				request.getRequestDispatcher("/UserJsp/ViewProjectDetails.jsp")
						.forward(request, response);
			}
		}
		// for displaying FileDetails.jsp along with the Project Codes
		if (action.equalsIgnoreCase("FileUploadJsp")) {
			UserManagementModule userManagementModule = new UserManagementModule();
			List<UserDTO> projectCodeList = userManagementModule
					.getProjectCode();
			request.setAttribute("projectCodeList", projectCodeList);
			if (request.getParameter("userType").equals("admin")) {
				request.getRequestDispatcher("/AdminJsp/FileDetails.jsp")
						.forward(request, response);
			}
			if (request.getParameter("userType").equals("nonAdmin")) {
				request.getRequestDispatcher("/UserJsp/FileDetails.jsp")
						.forward(request, response);
			}
		}
		
		if (action.equalsIgnoreCase("ImageUploadJsp")) {
			if (request.getParameter("userType").equals("admin")) {
				request.getRequestDispatcher("/AdminJsp/ImageDetails.jsp")
						.forward(request, response);
			}
			if (request.getParameter("userType").equals("nonAdmin")) {
				request.getRequestDispatcher("/UserJsp/ImageDetails.jsp")
						.forward(request, response);
			}
		}
		// for passing the File Details from FileDetails.jsp to FileUpload.jsp
		if (action.equalsIgnoreCase("GetFileDetails")) {
			HttpSession session = request.getSession(false);
			session.setAttribute("projectCode",
					request.getParameter("projectCode"));
			session.setAttribute("uploadDate",
					request.getParameter("uploadDate"));
			session.setAttribute("fileGroup", request.getParameter("fileGroup"));
			if (request.getParameter("userType").equals("admin")) {
				request.getRequestDispatcher("/AdminJsp/FileUpload.jsp")
						.forward(request, response);
			}
			if (request.getParameter("userType").equals("nonAdmin")) {
				request.getRequestDispatcher("/UserJsp/FileUpload.jsp")
						.forward(request, response);
			}
		}
		//for passing the image details
		if (action.equalsIgnoreCase("GetImageDetails")) {
			HttpSession session = request.getSession(false);
			session.setAttribute("description", request.getParameter("description"));
			session.setAttribute("uploadDate",request.getParameter("uploadDate"));
			if (request.getParameter("userType").equals("admin")) {
				request.getRequestDispatcher("/AdminJsp/UploadImages.jsp")
						.forward(request, response);
			}
			if (request.getParameter("userType").equals("nonAdmin")) {
				request.getRequestDispatcher("/UserJsp/UploadImages.jsp")
						.forward(request, response);
			}
		}
		
		// for uploading the image
				if (action.equalsIgnoreCase("ImageUploadServlet")) {
					HttpSession session = request.getSession(false);
					ProjectManagementModule projectManagementModule = new ProjectManagementModule();
					String description = (String) session.getAttribute("description");
					String uploadDate = (String) session.getAttribute("uploadDate");
					String username = (String)session.getAttribute("username");
					boolean flagFileUpload = projectManagementModule.uploadImage(
							request, description, uploadDate, username);
					if (!flagFileUpload)
						request.setAttribute("fileUploadMessage",
								"The File could not be uploaded");
					else
						request.setAttribute("fileUploadMessage",
								"The File uploaded succesfully");
					if (request.getParameter("userType").equals("admin")) {
						request.getRequestDispatcher(
								"./UserServlet?action=ImageUploadJsp&userType=admin")
								.forward(request, response);
					}
					if (request.getParameter("userType").equals("nonAdmin")) {
						request.getRequestDispatcher(
								"./UserServlet?action=ImageUploadJsp&userType=nonAdmin")
								.forward(request, response);
					}
				}
		// for uploading the file
		if (action.equalsIgnoreCase("FileUploadServlet")) {
			HttpSession session = request.getSession(false);
			ProjectManagementModule projectManagementModule = new ProjectManagementModule();
			String projectCode = (String) session.getAttribute("projectCode");
			System.out.println("project code: "
					+ session.getAttribute("projectCode"));
			String uploadDate = (String) session.getAttribute("uploadDate");
			String fileGroup = (String) session.getAttribute("fileGroup");
			String username = (String)session.getAttribute("username");
			boolean flagFileUpload = projectManagementModule.uploadFile(
					request, projectCode, uploadDate, fileGroup, username);
			if (!flagFileUpload)
				request.setAttribute("fileUploadMessage",
						"The File could not be uploaded");
			else
				request.setAttribute("fileUploadMessage",
						"The File uploaded succesfully");
			if (request.getParameter("userType").equals("admin")) {
				request.getRequestDispatcher(
						"./UserServlet?action=FileUploadJsp&userType=admin")
						.forward(request, response);
			}
			if (request.getParameter("userType").equals("nonAdmin")) {
				request.getRequestDispatcher(
						"./UserServlet?action=FileUploadJsp&userType=nonAdmin")
						.forward(request, response);
			}
		}

		// View all polls
		if (action.equalsIgnoreCase("ViewPollsServlet")) {

			String status = request.getParameter("status");
			DisplayPollUsingPagination.displayPollsPagination(request,
					response, status);
			if (request.getParameter("userType").equals("admin")) {
				request.getRequestDispatcher("/AdminJsp/ViewPolls.jsp")
						.forward(request, response);
			}
			if (request.getParameter("userType").equals("nonAdmin")) {
				request.getRequestDispatcher("/UserJsp/ViewPolls.jsp").forward(
						request, response);
			}
		}
		// for searching any specific poll
		if (action.equalsIgnoreCase("PollSearchServlet")) {
			UserManagementModule userManagementModule = new UserManagementModule();
			List<ResourceDTO> pollsList = userManagementModule
					.pollSearch(request.getParameter("pollTopic"));
			if (pollsList.isEmpty()) {
				request.setAttribute("emptypollsList", "No search for "
						+ request.getParameter("pollTopic"));
			}
			request.setAttribute("pollsList", pollsList);
			if (request.getParameter("userType").equals("admin")) {
				request.getRequestDispatcher("/AdminJsp/ViewPolls.jsp")
						.forward(request, response);
			}
			if (request.getParameter("userType").equals("nonAdmin")) {
				request.getRequestDispatcher("/UserJsp/ViewPolls.jsp").forward(
						request, response);
			}
		}
		// shows the reply page of the poll
		if (action.equalsIgnoreCase("PollResultsServlet")) {
			UserManagementModule userManagementModule = new UserManagementModule();
			List<ResourceDTO> pollsList = userManagementModule
					.pollDetails(request.getParameter("pollTopic"));
			List<ResourceDTO> pollReplyList = userManagementModule
					.displayPollReply(request.getParameter("pollTopic"));
			request.setAttribute("pollsList", pollsList);
			request.setAttribute("pollReplyList", pollReplyList);
			if (request.getParameter("userType").equals("admin")) {
				request.getRequestDispatcher("/AdminJsp/PollResults.jsp")
						.forward(request, response);
			}
			if (request.getParameter("userType").equals("nonAdmin")) {
				request.getRequestDispatcher("/UserJsp/PollResults.jsp")
						.forward(request, response);
			}
		}
		// To add the reply in the poll
		if (action.equalsIgnoreCase("AddPollReplyServlet")) {
			UserManagementModule userManagementModule = new UserManagementModule();
			ResourceDTO resourceDTO = new ResourceDTO();
			resourceDTO.setPollTopic(request.getParameter("pollTopic"));
			// to be retreived from session
			resourceDTO.setUsername((String)session.getAttribute("username"));
			resourceDTO.setPollReplyText(request.getParameter("pollReply"));
			resourceDTO.setPollReplyDate(GetSqlDate.getDate(request
					.getParameter("pollReplyDate")));
			// to be retreived by session
			resourceDTO.setCreateUserId((String)session.getAttribute("username"));
			resourceDTO.setCreateDate(GetSqlDate.getDate(request
					.getParameter("pollReplyDate")));
			// to be retreived by session
			resourceDTO.setModifyUserId((String)session.getAttribute("username"));
			resourceDTO.setModifydate(GetSqlDate.getDate(request
					.getParameter("pollReplyDate")));
			boolean flagPollReply = userManagementModule
					.addPollReply(resourceDTO);
			if (!flagPollReply)
				request.setAttribute("pollReplyMessage",
						"Reply not added in the poll");
			else
				request.setAttribute("pollReplyMessage",
						"Reply added succesfully in the poll");
			request.getRequestDispatcher(
					"./UserServlet?action=PollResultsServlet&pollTopic="
							+ request.getParameter("pollTopic")).forward(
					request, response);
		}

		if (action.equalsIgnoreCase("AddUserJsp")) {
			UserManagementModule userManagementModule = new UserManagementModule();
			List<String> designationList = DesignationNamesUtil
					.getDesignationNames();
			List<String> skillList = userManagementModule.getSkills();
			request.setAttribute("hierarchyMessage", "Select a manager");
			request.setAttribute("designationList", designationList);
			request.setAttribute("skillList", skillList);
			request.getRequestDispatcher("/AdminJsp/AddUser.jsp").forward(
					request, response);
		}

		if (request.getParameter("action").equals("DisplayManagerNamesServlet")) {
			String designation = request.getParameter("designation");
			String hierarchyMessage = "";
			UserManagementModule userManagementModule = new UserManagementModule();
			List<String> managerList = userManagementModule
					.getManagerList(designation);
			if (managerList.isEmpty()) {
				hierarchyMessage = "Not Available";
				request.setAttribute("hierarchyMessage", hierarchyMessage);
			}
			List<String> designationList = DesignationNamesUtil
					.getDesignationNames();
			List<String> skillList = userManagementModule.getSkills();
			request.setAttribute("designationList", designationList);
			request.setAttribute("managerList", managerList);
			request.setAttribute("skillList", skillList);
			request.getRequestDispatcher("/AdminJsp/AddUser.jsp").forward(
					request, response);
		}

		if (request.getParameter("action").equals("AddUserServlet")) {

			boolean flagAddUser = false;
			userDTO.setEmployeeId(Integer.parseInt(request
					.getParameter("employeeId")));
			userDTO.setUsername(request.getParameter("capgeminiUsername"));
			userDTO.setPassword(request.getParameter("capgeminiUsername"));
			userDTO.setUserType(request.getParameter("userType"));
			userDTO.setFirstName(request.getParameter("firstName"));
			userDTO.setLastName(request.getParameter("lastName"));
			userDTO.setCapgeminiEmailId(request
					.getParameter("capgeminiEmailId"));
			userDTO.setPrimaryContactNumber(request
					.getParameter("primaryContactNo"));
			if (request.getParameter("emergencyContactNo").equals("")) {
				userDTO.setEmergencyContactNumber(request.getParameter(""));
			} else {
				userDTO.setEmergencyContactNumber(request
						.getParameter("emergencyContactNo"));
			}
			userDTO.setCiscoDOJ(GetSqlDate.getDate(request
					.getParameter("ciscoDOJ")));
			userDTO.setDesignation(request.getParameter("designation"));
			userDTO.setIsBillable("N");
			userDTO.setIsActive("Y");
			if (request.getParameter("designation").equals("AD")) {
				userDTO.setCapgeminiManagerName(request
						.getParameter("capgeminiUsername"));
			} else {
				userDTO.setCapgeminiManagerName(request
						.getParameter("cgManagerUsername"));
			}
			userDTO.setCiscoManagerName(request
					.getParameter("ciscoManagerUsername"));
			userDTO.setTotalExperience(Integer.parseInt(request
					.getParameter("experience")));
			userDTO.setServiceLine(request.getParameter("serviceLine"));
			userDTO.setPrimarySkill(request.getParameter("primarySkill"));
			if (request.getParameter("secondarySkill1").equals(
					"Select your Skill")) {
				userDTO.setSecondarySkill_01("");
			} else if (request.getParameter("secondarySkill2").equals(
					"Select your Skill")) {
				userDTO.setSecondarySkill_02("");
			} else {
				userDTO.setSecondarySkill_01(request
						.getParameter("secondarySkill1"));
				userDTO.setSecondarySkill_02(request
						.getParameter("secondarySkill2"));
			}
			userDTO.setCreateUserId((String)session.getAttribute("username"));
			userDTO.setCreateDate(GetSqlDate.getDate(request
					.getParameter("ciscoDOJ")));
			userDTO.setModifyUserId((String)session.getAttribute("username"));
			userDTO.setModifydate(GetSqlDate.getDate(request
					.getParameter("ciscoDOJ")));
			userDTO.setCiscoUsername(request.getParameter("capgeminiUsername"));
			userDTO.setIs_delivery_manager(request
					.getParameter("deliveryManager"));

			UserManagementModule userManagementModule = new UserManagementModule();
			try {
				flagAddUser = userManagementModule.addUser(userDTO);
			} catch (Exception e) {
				System.out.println("error2 in : " + e.getMessage());
			}
			if (flagAddUser) {
				request.setAttribute("addUserMessage",
						"User " + request.getParameter("capgeminiUsername")
								+ " already exists!");
				request.getRequestDispatcher("./UserServlet?action=AddUserJsp")
						.forward(request, response);
			} else {
				request.setAttribute("addUserMessage",
						"User " + request.getParameter("capgeminiUsername")
								+ " added succesfully");
				request.getRequestDispatcher(
						"./UserServlet?action=ViewUserJsp&userType=admin")
						.forward(request, response);
			}
		}
		// for displaying untagged users list and project codes
		if (action.equalsIgnoreCase("TagUserJsp")) {
			UserManagementModule userManagementModule = new UserManagementModule();
			ProjectManagementModule projectManagementModule = new ProjectManagementModule();
			List<UserDTO> untaggedUsersList = userManagementModule
					.getUntaggedUsers();
			List<UserDTO> projectCodeList = userManagementModule
					.getProjectCode();
			List<UserDTO> UserRoleList = userManagementModule.getUserRole();
			List<ProjectDTO> locationNamesList = projectManagementModule
					.fillLocationNames();
			request.setAttribute("untaggedUsersList", untaggedUsersList);
			request.setAttribute("projectCodeList", projectCodeList);
			request.setAttribute("UserRoleList", UserRoleList);
			request.setAttribute("locationNamesList", locationNamesList);
			request.getRequestDispatcher("/AdminJsp/TagUser.jsp").forward(
					request, response);
		}
		// for tagging the user
		if (action.equalsIgnoreCase("TagUserServlet")) {
			boolean flagIfManagerTagged = false;
			boolean flagCheckManager = false;
			boolean flagTagUser = false;
			String tagUserStatus = "";

			userDTO.setUsername(request.getParameter("capgeminiUsername"));
			userDTO.setCiscoUsername(request.getParameter("ciscoUsername"));
			userDTO.setCiscoEmailId(request.getParameter("ciscoEmailId"));
			userDTO.setProject_code(Integer.parseInt(request
					.getParameter("projectCode")));
			userDTO.setBillHours(Integer.parseInt(request
					.getParameter("billHours")));
			userDTO.setStartDate(GetSqlDate.getDate(request
					.getParameter("startDate")));
			userDTO.setEndDate(GetSqlDate.getDate(request
					.getParameter("endDate")));
			userDTO.setIsPresentlyTagged("Y");
			if (request.getParameter("region").equals("On Shore")) {
				userDTO.setIsOnShore("Y");
			} else {
				userDTO.setIsOnShore("N");
			}
			userDTO.setProjectRoleID(FetchProjectRoleIDUtil
					.getProjectRoleID(request.getParameter("userRole")));
			userDTO.setLocationID(FetchLocationUtil.getLocationID(request
					.getParameter("location")));
			userDTO.setCreateUserId((String)session.getAttribute("username"));
			userDTO.setCreateDate(GetSqlDate.getDate(request
					.getParameter("startDate")));
			userDTO.setModifyUserId((String)session.getAttribute("username"));
			userDTO.setModifydate(GetSqlDate.getDate(request
					.getParameter("startDate")));
			userDTO.setCiscoUsername(request.getParameter("ciscoUsername"));
			UserManagementModule userManagementModule = new UserManagementModule();
			if (request.getParameter("userRole").equals("Manager")) {
				flagIfManagerTagged = userManagementModule
						.ifManagerTagged(Integer.parseInt(request
								.getParameter("projectCode")));
				flagCheckManager = userManagementModule.checkManager(request
						.getParameter("capgeminiUsername"));
				if (flagIfManagerTagged)
					tagUserStatus = "Manager is already for Project Code: "
							+ request.getParameter("projectCode");
				if (!flagCheckManager)
					tagUserStatus = request.getParameter("capgeminiUsername")
							+ " is not a manager by designation";
				request.setAttribute("tagUserStatus", tagUserStatus);
				request.getRequestDispatcher("./UserServlet?action=TagUserJsp")
						.forward(request, response);
			} else {
				flagTagUser = userManagementModule.tagUserToProject(userDTO);
				if (flagTagUser)
					tagUserStatus = "User "
							+ request.getParameter("capgeminiUsername")
							+ " has been tagged successfully for project code "
							+ request.getParameter("projectCode");
				request.setAttribute("tagUserStatus", tagUserStatus);
				request.getRequestDispatcher("./UserServlet?action=TagUserJsp")
						.forward(request, response);
			}
		}

		// View completed projects
		if (action.equalsIgnoreCase("ViewCompletedProjectJsp")) {
			String projectMessage = "";
			String status = request.getParameter("status");
			DisplayProjectsUsingPagination.displayProjectsPagination(request,
					response, status);
			request.setAttribute("projectMessage", projectMessage);
			request.getRequestDispatcher("/AdminJsp/ViewProjects.jsp").forward(
					request, response);
		}
		// for displaying the manager names in AddProject.jsp
		if (action.equalsIgnoreCase("AddProjectJsp")) {
			ProjectManagementModule projectManagementModule = new ProjectManagementModule();
			List<UserDTO> projectManagerList = projectManagementModule
					.fillManagerNames();
			List<ProjectDTO> locationNamesList = projectManagementModule
					.fillLocationNames();
			request.setAttribute("projectManagerList", projectManagerList);
			request.setAttribute("locationNamesList", locationNamesList);
			request.getRequestDispatcher("/AdminJsp/AddProject.jsp").forward(
					request, response);
		}

		if (action.equalsIgnoreCase("AddProjectServlet")) {
			boolean flagAddProject = false;
			// for adding the project
			ProjectDTO projectDTO = new ProjectDTO();
			projectDTO.setProject_code(Integer.parseInt(request
					.getParameter("projectCode")));
			projectDTO.setProjectName(request.getParameter("projectName"));
			projectDTO.setProjectObjective(request
					.getParameter("projectObjective"));
			projectDTO
					.setProjectManager(request.getParameter("projectManager"));
			projectDTO.setTimeOfCompletion(Integer.parseInt(request
					.getParameter("completionTime")));
			projectDTO.setNoOfModules(Integer.parseInt(request
					.getParameter("modules")));
			projectDTO.setLocationID(FetchLocationUtil.getLocationID(request
					.getParameter("location")));
			projectDTO.setStartDate(GetSqlDate.getDate(request
					.getParameter("startDate")));
			projectDTO.setEndDate(GetSqlDate.getDate(request
					.getParameter("endDate")));
			projectDTO.setNoOfTechnicalResources(Integer.parseInt(request
					.getParameter("noOfTechnicalResources")));
			projectDTO.setNoOfNonTechnicalResources(Integer.parseInt(request
					.getParameter("noOfNonTechnicalResources")));
			projectDTO.setStatus("On Going");
			// name would be retreived from session id.
			projectDTO.setCreateUserId((String)session.getAttribute("username"));
			projectDTO.setCreateDate(GetSqlDate.getDate(request
					.getParameter("startDate")));
			projectDTO.setModifyUserId((String)session.getAttribute("username"));
			projectDTO.setModifydate(GetSqlDate.getDate(request
					.getParameter("startDate")));

			try {
				flagAddProject = projectModel.addProject(projectDTO);
				if (flagAddProject) {
					request.setAttribute("addProjectMessage", "Project "
							+ request.getParameter("projectName")
							+ " already exists");
					request.getRequestDispatcher(
							"./UserServlet?action=AddProjectJsp").forward(
							request, response);
				} else {
					request.setAttribute("addProjectMessage", "Project "
							+ request.getParameter("projectName")
							+ " added succesfully");
					request.getRequestDispatcher(
							"./UserServlet?action=ViewProjectJsp&userType=admin")
							.forward(request, response);
				}
			} catch (Exception e) {
				System.out.println("error27 in : " + e.getMessage());
			}
		}

		if (action.equalsIgnoreCase("CreatePollServlet")) {
			ResourceDTO resourceDTO = new ResourceDTO();
			resourceDTO.setPollTopic(request.getParameter("pollTopic"));
			resourceDTO.setPollDescription(request.getParameter("description"));
			resourceDTO.setPollType(request.getParameter("pollType"));
			resourceDTO.setPollEndDate(GetSqlDate.getDate(request
					.getParameter("endDate")));
			resourceDTO.setPollStatus("OnGoing");
			resourceDTO.setCreateUserId((String)session.getAttribute("username"));
			resourceDTO.setCreateDate(GetSqlDate.getDate(request
					.getParameter("startDate")));
			resourceDTO.setModifyUserId((String)session.getAttribute("username"));
			resourceDTO.setModifydate(GetSqlDate.getDate(request
					.getParameter("startDate")));
			UserManagementModule userManagementModule = new UserManagementModule();
			boolean flagCreatePoll = userManagementModule
					.createPoll(resourceDTO);
			if (!flagCreatePoll) {
				request.setAttribute("createPollMessage",
						"Poll " + request.getParameter("pollTopic")
								+ " not created succesfully");
				request.getRequestDispatcher("/AdminJsp/CreatePoll.jsp")
						.forward(request, response);
			} else {
				request.setAttribute("createPollMessage",
						"Poll " + request.getParameter("pollTopic")
								+ " created succesfully");
				request.getRequestDispatcher(
						"./UserServlet?action=ViewPollsServlet&userType=admin&status=OnGoing")
						.forward(request, response);
			}
		}

		if (action.equalsIgnoreCase("CreatePollJsp")) {
			request.getRequestDispatcher("/AdminJsp/CreatePoll.jsp").forward(
					request, response);
		}

		// Vanitaaaaaaaaaa
		// Admin clicks on Delete Users link

		if (action.equalsIgnoreCase("DeleteUserJsp")) {

			String errorMessage = "";
			DisplayUsersUsingPagination.displayUsersPagination(request,
					response);
			request.getRequestDispatcher("/AdminJsp/DeleteUser.jsp").forward(
					request, response);

		}
		if (action.equalsIgnoreCase("DeleteUserSearch")) {

			String errorMessage1 = "";
			List<UserDTO> usersList = new ArrayList<UserDTO>();
			if (request.getParameter("searchType")
					.equals("Search by User Name")) {
				usersList = userModel.viewUsers(
						request.getParameter("usernameField"),
						"Search by User Name");
				if (usersList.isEmpty()) {
					errorMessage1 = "No Match found";

				}

			} else if (request.getParameter("searchType").equals(
					"Search by CG Manager Name")) {
				usersList = userModel.viewUsers(
						request.getParameter("usernameField"),
						"Search by CG Manager Name");
				if (usersList.isEmpty()) {
					errorMessage1 = "No Match found";
				}

			} else if (request.getParameter("searchType").equals(
					"Search by Project Name")) {
				Map<Integer, List<UserDTO>> hashmap = userModel
						.viewUsersByProject(request
								.getParameter("usernameField"));
				usersList = (List<UserDTO>) hashmap.get(1);
				List<UserDTO> usersList1 = (List<UserDTO>) hashmap.get(2);
				if (usersList == null) {
					errorMessage1 = "No Match found";

				}

				request.setAttribute("usersList1", usersList1);
			}
			request.setAttribute("errorMessage1", errorMessage1);
			request.setAttribute("usersList", usersList);

			if (request.getParameter("userType").equals("admin")) {
				request.getRequestDispatcher("/AdminJsp/DeleteUser.jsp")
						.forward(request, response);
			}
			if (request.getParameter("userType").equals("nonAdmin")) {
				request.getRequestDispatcher("/UserJsp/DeleteUser.jsp")
						.forward(request, response);
			}
		}

		// for granting or revoking admin rights
		if (action.equalsIgnoreCase("AdminRightsServlet")) {
			System.out.println(request.getParameter("nonAdminUsers"));
			UserManagementModule userManagementModule = new UserManagementModule();
			if (request.getParameter("action1").equals("grant")) {
				boolean flagAdminRights = userManagementModule
						.grantAdminRights(request.getParameter("nonAdminUsers"));
				if (!flagAdminRights)
					request.setAttribute(
							"adminRightsMessage",
							"Problem in granting "
									+ request.getParameter("nonAdminUsers")
									+ " the admin rights");
				else
					request.setAttribute(
							"adminRightsMessage",
							request.getParameter("nonAdminUsers")
									+ " has been granted the admin rights succesfully");
			}
			if (request.getParameter("action1").equals("revoke")) {
				boolean flagAdminRights = userManagementModule
						.revokeAdminRights(request.getParameter("adminUsers"));
				if (!flagAdminRights)
					request.setAttribute(
							"adminRightsMessage",
							"Problem in revoking admin rights from "
									+ request.getParameter("adminUsers"));
				else
					request.setAttribute(
							"adminRightsMessage",
							"Admin rights had been revoked from "
									+ request.getParameter("adminUsers"));
			}
			request.getRequestDispatcher("./UserServlet?action=AdminRightsJsp")
					.forward(request, response);
		}
		if (action.equalsIgnoreCase("AdminRightsJsp")) {
			UserManagementModule userManagementModule = new UserManagementModule();
			List<UserDTO> adminUsersList = userManagementModule.getAdminUsers();
			List<UserDTO> nonAdminUsersList = userManagementModule
					.getnonAdminUsers();
			request.setAttribute("adminUsersList", adminUsersList);
			request.setAttribute("nonAdminUsersList", nonAdminUsersList);
			request.getRequestDispatcher("/AdminJsp/AdminRights.jsp").forward(
					request, response);
		}

		// Admin selects the users to be deleted and the users have to be
		// deleted from the database
		if (action.equals("DeleteUser")) {

			String loggedInErrorMessage = null;
			String loggedinusername = null;
			String errorMessage = null;
			String is_billable = null;
			String billableMessage = null;

			List<UserDTO> allUsersList = new ArrayList<UserDTO>();
			String[] username = request.getParameterValues("username");
			loggedinusername = (String) session.getAttribute("username");
			for (int i = 0; i < username.length; i++) {

				System.out.println(username[i]);
				is_billable = CheckUserTagging.checkTagging(username[i]);
				if (loggedinusername.equals(username[i])) {

					DisplayUsersUsingPagination.displayUsersPagination(request,
							response);
					loggedInErrorMessage = "The logged in user '" + username[i]
							+ "' cannot be deleted!";
					request.setAttribute("loggedErrorMessage1",
							loggedInErrorMessage);
					request.getRequestDispatcher("/AdminJsp/DeleteUser.jsp")
							.forward(request, response);
					return;
				}

				else if (is_billable.equals("Y")) {

					DisplayUsersUsingPagination.displayUsersPagination(request,
							response);
					billableMessage = "Please untag the user '" + username[i]
							+ "' before deleting!";
					request.setAttribute("billableMessage", billableMessage);
					request.getRequestDispatcher("/AdminJsp/DeleteUser.jsp")
							.forward(request, response);
					return;
				}

			}
			String message1 = "";
			try {
				userModel.deleteUsers(username);
				message1 = "The user(s) have been succesfully deleted!";
				request.setAttribute("message1", message1);
				DisplayUsersUsingPagination.displayUsersPagination(request,
						response);
				request.setAttribute("deleteUserMessage", message1);
				request.getRequestDispatcher("/AdminJsp/DeleteUser.jsp")
						.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		// for downloading file
		if (action.equals("DownloadFile")) {
			ProjectManagementModule projectManagementModule = new ProjectManagementModule();
			boolean flagDownload = projectManagementModule.downloadFile(
					request.getParameter("fileName"), response);
		}
		// for downloading image
		if (action.equals("DownloadImage")) {
			ProjectManagementModule projectManagementModule = new ProjectManagementModule();
			projectManagementModule.downloadImage(
					request.getParameter("imageName"), response);
		}

		// Admin clicks on Edit details
		if (action.equalsIgnoreCase("UpdateUserJspAdmin")) {

			String username = request.getParameter("username");
			RetrieveUserDetails.getUserDetails(username, request, response);
			request.getRequestDispatcher("/AdminJsp/UserDetailsEditable.jsp")
					.forward(request, response);

		}
		// User clicks on Edit Details
		if (action.equalsIgnoreCase("UpdateUserJsp")) {

			String username = request.getParameter("username");
			RetrieveUserDetails.getUserDetails(username, request, response);
			request.getRequestDispatcher("/UserJsp/UserDetailsEditable.jsp")
					.forward(request, response);
		}
		// for editing user details by admin
		if (action.equalsIgnoreCase("UpdateUserAdmin")) {
			// System.out.println(request.getParameter("designation"));
			boolean flagUpdateUser = false;
			String username = request.getParameter("capgeminiUserName");
			userDTO.setUsername(username);
			userDTO.setFirstName(request.getParameter("firstName"));
			userDTO.setLastName(request.getParameter("lastName"));
			userDTO.setDesignation(request.getParameter("designation"));
			userDTO.setCiscoEmailId(request.getParameter("ciscoEmailId"));
			userDTO.setCapgeminiEmailId(request
					.getParameter("capgeminiEmailId"));
			userDTO.setPrimaryContactNumber(request
					.getParameter("primaryContactNo"));
			if (request.getParameter("emergencyContactNo").equals("")) {
				userDTO.setEmergencyContactNumber(request.getParameter(""));
			} else {
				userDTO.setEmergencyContactNumber(request
						.getParameter("emergencyContactNo"));
				userDTO.setPrimarySkill(request.getParameter("primarySkill"));
				if (request.getParameter("secondarySkill1").equals(
						"Select your Skill")) {
					userDTO.setSecondarySkill_01("");
					userDTO.setSecondarySkill_02(request
							.getParameter("secondarySkill2"));
				} else if (request.getParameter("secondarySkill2").equals(
						"Select your Skill")) {
					userDTO.setSecondarySkill_02("");
					userDTO.setSecondarySkill_01(request
							.getParameter("secondarySkill1"));
				} else {
					userDTO.setSecondarySkill_01(request
							.getParameter("secondarySkill1"));
					userDTO.setSecondarySkill_02(request
							.getParameter("secondarySkill2"));
				}
				java.util.Date date = new java.util.Date();
				// java.sql.Date currentDate = new
				// java.sql.Date(date.getTime());
				Format formatter = new SimpleDateFormat("MM-dd-yyyy");
				String s = formatter.format(date);
				// System.out.println("date in servlet is: "+s);
				userDTO.setModifydate(GetSqlDate.getDate(s));

				String username2 = (String) session.getAttribute("username");
				System.out.println("session is: " + username2);
				userDTO.setModifyUserId((String)session.getAttribute("username"));
				flagUpdateUser = userModel.updateUserDetailsAdmin(userDTO);
				if (!flagUpdateUser) {
					request.setAttribute("message",
							"Unable to update user details");
				} else {
					request.setAttribute("message",
							"User details have been successfully updated!");
				}
				RetrieveUserDetails.getUserDetails(username, request, response);

				request.getRequestDispatcher("/AdminJsp/ViewUserDetails.jsp")
						.forward(request, response);
			}
		}
		// for editing user details by user
		if (action.equalsIgnoreCase("UpdateUser")) {
			// System.out.println(request.getParameter("designation"));
			boolean flagUpdateUser = false;
			String username = request.getParameter("capgeminiUserName");
			userDTO.setUsername(username);
			userDTO.setFirstName(request.getParameter("firstName"));
			userDTO.setLastName(request.getParameter("lastName"));
			userDTO.setPrimaryContactNumber(request
					.getParameter("primaryContactNo"));
			if (request.getParameter("emergencyContactNo").equals(""))
				userDTO.setEmergencyContactNumber(request.getParameter(""));
			else
				userDTO.setEmergencyContactNumber(request
						.getParameter("emergencyContactNo"));
			java.util.Date date = new java.util.Date();
			Format formatter = new SimpleDateFormat("MM-dd-yyyy");
			String s = formatter.format(date);
			userDTO.setModifydate(GetSqlDate.getDate(s));
			String username2 = (String) session.getAttribute("username");
			System.out.println("session is: " + username2);
			userDTO.setModifyUserId((String)session.getAttribute("username"));
			flagUpdateUser = userModel.updateUserDetails(userDTO);
			if (!flagUpdateUser)
				request.setAttribute("message", "Unable to update user details");
			else
				request.setAttribute("message",
						"User details have been successfully updated!");
			RetrieveUserDetails.getUserDetails(username, request, response);
			request.getRequestDispatcher("/UserJsp/ViewUserDetails.jsp")
					.forward(request, response);
		}

		// To redirect to the Add Location JSP Page
		if (action.equals("AddLocationJsp")) {

			List<ProjectDTO> locationList = userModel.viewLocation();
			request.setAttribute("locationList", locationList);
			request.getRequestDispatcher("AdminJsp/AddLocation.jsp").forward(
					request, response);
		}

		// To add a new skill
		if (action.equals("AddLocationServlet")) {

			String locationName = request.getParameter("locationName");
			String address = request.getParameter("address");
			String message = "";
			String username = (String) session.getAttribute("username");
			int rowsaffected = userModel.addLocation(locationName, address,
					username);
			if (rowsaffected > 0) {
				message = "The location has been successfully added to the list!";
			} else {
				message = "The location could not be added to the list";
			}
			request.setAttribute("message", message);
			List<ProjectDTO> locationList = userModel.viewLocation();
			request.setAttribute("locationList", locationList);
			request.getRequestDispatcher("AdminJsp/AddLocation.jsp").forward(
					request, response);
		}

		// To redirect to the Add Location JSP Page
		if (action.equals("AddCiscoManagerJsp")) {

			List<UserDTO> managerList = userModel.viewCiscoManagers();
			request.setAttribute("managerList", managerList);
			request.getRequestDispatcher("AdminJsp/AddCiscoManager.jsp")
					.forward(request, response);
		}

		// To add a new skill
		if (action.equals("AddCiscoManagerServlet")) {

			String managerUsername = request.getParameter("username");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String message = "";
			String username = (String) session.getAttribute("username");
			int rowsaffected = userModel.addCiscoManager(managerUsername,
					firstName, lastName, username);
			if (rowsaffected > 0) {
				message = "The Manager has been successfully added to the list!";
			} else {
				message = "The Manager could not be added to the list";
			}
			request.setAttribute("message", message);
			List<UserDTO> managerList = userModel.viewCiscoManagers();
			request.setAttribute("managerList", managerList);
			request.getRequestDispatcher("AdminJsp/AddCiscoManager.jsp")
					.forward(request, response);
		}

		// Admin clicks on Edit Project details
		if (action.equals("UpdateProjectDetails")) {
			String projectName = request.getParameter("projectName");
			RetrieveProjectDetails.getProjectDetails(projectName, request,
					response);
			List<UserDTO> projectManagerList = projectModel.fillManagerNames();
			List<ProjectDTO> locationNamesList = projectModel
					.fillLocationNames();
			request.setAttribute("projectManagerList", projectManagerList);
			request.setAttribute("locationNamesList", locationNamesList);
			request.getRequestDispatcher("UserJsp/ProjectDetailsEditable.jsp")
					.forward(request, response);
		}

		if (action.equals("UpdateProjectDetailsAdmin")) {
			String projectName = request.getParameter("projectName");
			RetrieveProjectDetails.getProjectDetails(projectName, request,
					response);
			List<UserDTO> projectManagerList = projectModel.fillManagerNames();
			List<ProjectDTO> locationNamesList = projectModel
					.fillLocationNames();
			request.setAttribute("projectManagerList", projectManagerList);
			request.setAttribute("locationNamesList", locationNamesList);
			request.getRequestDispatcher("AdminJsp/ProjectDetailsEditable.jsp")
					.forward(request, response);
		}

		// User's edited details updated in the database

		if (action.equalsIgnoreCase("UpdateProjectAdmin")) {
			int rowsaffected = 0;
			int projectCode = Integer.parseInt(request
					.getParameter("projectCode"));
			String projectName = request.getParameter("projectName");
			ProjectManagementModule projectManagementModule = new ProjectManagementModule();
			ProjectDTO projectDTO = new ProjectDTO();
			projectDTO.setProject_code(projectCode);
			projectDTO.setProjectName(projectName);
			projectDTO.setProjectObjective(request
					.getParameter("projectObjective"));
			projectDTO
					.setProjectManager(request.getParameter("projectManager"));
			projectDTO.setTimeOfCompletion((Integer.parseInt(request
					.getParameter("completionTime"))));
			projectDTO.setNoOfModules((Integer.parseInt(request
					.getParameter("modules"))));
			projectDTO.setLocationName(request.getParameter("locationName"));
			projectDTO.setNoOfTechnicalResources((Integer.parseInt(request
					.getParameter("noOfTechnicalResources"))));
			projectDTO.setNoOfNonTechnicalResources((Integer.parseInt(request
					.getParameter("noOfNonTechnicalResources"))));
			projectDTO.setStatus(request.getParameter("projectStatus"));

			// java.util.Date date = new java.util.Date();
			// java.sql.Date currentDate = new
			// java.sql.Date(date.getTime());
			// projectDTO.setModifydate(currentDate);

			java.util.Date date = new java.util.Date();
			Format formatter = new SimpleDateFormat("MM-dd-yyyy");
			String s = formatter.format(date);
			projectDTO.setModifydate(GetSqlDate.getDate(s));
			System.out.println("session is: "
					+ (String) session.getAttribute("username"));
			projectDTO.setModifyUserId((String) session
					.getAttribute("username"));
			rowsaffected = projectManagementModule
					.updateProjectDetails(projectDTO);
			if (rowsaffected == 0) {
				request.setAttribute("message",
						"Unable to update Project details");
			}
			RetrieveProjectDetails.getProjectDetails(projectName, request,
					response);
			request.setAttribute("message",
					"Project details have been successfully updated!");
			request.getRequestDispatcher("/AdminJsp/ViewProjectDetails.jsp")
					.forward(request, response);
		}

		if (action.equalsIgnoreCase("UpdateProject")) {
			int rowsaffected = 0;
			int projectCode = Integer.parseInt(request
					.getParameter("projectCode"));
			String projectName = request.getParameter("projectName");
			ProjectManagementModule projectManagementModule = new ProjectManagementModule();
			ProjectDTO projectDTO = new ProjectDTO();
			projectDTO.setProject_code(projectCode);
			projectDTO.setProjectName(projectName);
			projectDTO.setProjectObjective(request
					.getParameter("projectObjective"));
			projectDTO
					.setProjectManager(request.getParameter("projectManager"));
			projectDTO.setTimeOfCompletion((Integer.parseInt(request
					.getParameter("completionTime"))));
			projectDTO.setNoOfModules((Integer.parseInt(request
					.getParameter("modules"))));
			projectDTO.setLocationName(request.getParameter("locationName"));
			projectDTO.setNoOfTechnicalResources((Integer.parseInt(request
					.getParameter("noOfTechnicalResources"))));
			projectDTO.setNoOfNonTechnicalResources((Integer.parseInt(request
					.getParameter("noOfNonTechnicalResources"))));
			projectDTO.setStatus(request.getParameter("projectStatus"));

			// java.util.Date date = new java.util.Date();
			// java.sql.Date currentDate = new
			// java.sql.Date(date.getTime());
			// projectDTO.setModifydate(currentDate);

			java.util.Date date = new java.util.Date();
			Format formatter = new SimpleDateFormat("MM-dd-yyyy");
			String s = formatter.format(date);
			projectDTO.setModifydate(GetSqlDate.getDate(s));
			System.out.println("session is: "
					+ (String) session.getAttribute("username"));
			projectDTO.setModifyUserId((String) session
					.getAttribute("username"));
			rowsaffected = projectManagementModule
					.updateProjectDetails(projectDTO);
			if (rowsaffected == 0) {
				request.setAttribute("message",
						"Unable to update Project details");
			}
			RetrieveProjectDetails.getProjectDetails(projectName, request,
					response);
			request.setAttribute("message",
					"Project details have been successfully updated!");
			request.getRequestDispatcher("/UserJsp/ViewProjectDetails.jsp")
					.forward(request, response);
		}

		// Deleting documents related to projects
		if (action.equals("DeleteDocument")) {
			String fileName = request.getParameter("fileName");
			String projectName = request.getParameter("projectName");
			String message1 = "";
			try {
				boolean flagDeleteDocument = projectModel
						.deleteDocument(fileName);
				if (flagDeleteDocument)
					message1 = "The document has been succesfully deleted!";
				else
					message1 = "Please try again";
				request.setAttribute("message1", message1);
				RetrieveProjectDetails.getProjectDetails(projectName, request,
						response);
				request.getRequestDispatcher("/AdminJsp/ViewProjectDetails.jsp")
						.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Redirecting to Deactivate Poll page
		if (action.equals("DeactivatePollJsp")) {

			String status = request.getParameter("status");
			DisplayPollUsingPagination.displayPollsPagination(request,
					response, status);

			request.getRequestDispatcher("/AdminJsp/DeactivatePolls.jsp")
					.forward(request, response);

		}

		// Ending a Poll
		if (action.equals("DeactivatePollServlet")) {

			int pollId = Integer.parseInt(request.getParameter("PollId"));
			int rowsaffected = userModel.deactivatePoll(pollId);
			String message = null;
			if (rowsaffected > 0) {
				message = "The Poll has been ended!";
			} else {
				message = "Unable to end the Poll";
			}
			request.setAttribute("message", message);
			String status = "OnGoing";
			DisplayPollUsingPagination.displayPollsPagination(request,
					response, status);
			request.getRequestDispatcher("/AdminJsp/DeactivatePolls.jsp")
					.forward(request, response);

		}

		// Searching a Poll for delete
		if (action.equals("DeactivatePollSearch")) {

			String pollName = request.getParameter("pollTopic");

			request.getRequestDispatcher("/AdminJsp/DeactivatePolls.jsp")
					.forward(request, response);

		}

		// Admin clicks on Delete Project link

		if (action.equalsIgnoreCase("DeleteProjectJsp")) {

			String status = "On Going";
			DisplayProjectsUsingPagination.displayProjectsPagination(request,
					response, status);
			request.getRequestDispatcher("/AdminJsp/DeleteProject.jsp")
					.forward(request, response);
		}

		// Admin searches for a particular project

		if (action.equalsIgnoreCase("DeleteProjectSearch")) {

			int page = 1;
			int recordsPerPage = 5;
			int noOfRecords = 0;
			int noOfPages = 0;
			if (request.getParameter("page") != null)
				page = Integer.parseInt(request.getParameter("page"));
			String errorMessage1 = "";
			List<ProjectDTO> projectLists = projectModel.viewProject(request
					.getParameter("projectNameSearch"));
			if (projectLists == null) {
				errorMessage1 = "No Match found";
			}
			noOfRecords = projectModel.getNoOfProjectRecordsName(request
					.getParameter("projectNameSearch"));
			noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", page);
			request.setAttribute("projectLists", projectLists);
			request.setAttribute("errorMessage1", errorMessage1);
			request.getRequestDispatcher("/AdminJsp/DeleteProject.jsp")
					.forward(request, response);
		}

		// Admin selects the users to be deleted and the users have to be
		// deleted from the database

		if (action.equals("DeleteProject")) {
			String[] projectName = request.getParameterValues("projectName");
			for (int i = 0; i < projectName.length; i++) {
				System.out.println(projectName[i]);

			}
			String message1 = "";
			try {
				projectModel.deleteProjects(projectName);
				message1 = "The project(s) have been succesfully deleted!";
				request.setAttribute("message1", message1);
				String status = "On Going";
				DisplayProjectsUsingPagination.displayProjectsPagination(
						request, response, status);
				request.getRequestDispatcher("/AdminJsp/DeleteProject.jsp")
						.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();

			}

		}

		// Display Images

		if (request.getParameter("action").equals("DisplayImagesJsp")) {
			System.out.println("action");
			System.out.println("action1");
			List<ResourceDTO> imagesList = projectModel.displayImages();
			request.setAttribute("imagesList", imagesList);
			if (request.getParameter("userType").equals("admin")) {
				request.getRequestDispatcher("/AdminJsp/ViewImages.jsp")
						.forward(request, response);
			}
			if (request.getParameter("userType").equals("nonAdmin")) {
				request.getRequestDispatcher("/UserJsp/ViewImages.jsp")
						.forward(request, response);
			}
		}
		//for untagging users
		if (action.equalsIgnoreCase("UnTagUserServlet")) {

			UserManagementModule userManagementModule = new UserManagementModule();

			boolean flagUntagUser = userManagementModule.untagUser(request.getParameter("username"));

			if(flagUntagUser)

			request.setAttribute("untagMessage", "User untagged succesfully");

			else

			request.setAttribute("untagMessage", "Problem in untagging");

			request.getRequestDispatcher("./UserServlet?action=ViewUserJsp&userType=admin").forward(request, response);

			}



		// Delete Images

		if (action.equals("DeleteImage")) {
			int imageId = Integer.parseInt(request.getParameter("ImageId"));
			String message1 = "";

			projectModel.deleteImage(imageId);
			message1 = "The Image has been succesfully deleted!";
			request.setAttribute("message1", message1);
			List<ResourceDTO> imagesList = projectModel.displayImages();
			request.setAttribute("imagesList", imagesList);
			request.getRequestDispatcher("/AdminJsp/ViewImages.jsp").forward(
					request, response);

		}

	}
}
