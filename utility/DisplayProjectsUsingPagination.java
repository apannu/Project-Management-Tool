package com.capgemini.cisco.portal.utility;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.capgemini.cisco.portal.dto.ProjectDTO;
import com.capgemini.cisco.portal.service.ProjectManagementModule;
import com.capgemini.cisco.portal.service.UserManagementModule;

public class DisplayProjectsUsingPagination {
	
	static UserManagementModule userModel=new UserManagementModule();
	static String projectMessage = "";
	static ProjectManagementModule projectModel=new ProjectManagementModule();
	
	public static void displayProjectsPagination(HttpServletRequest request, HttpServletResponse response, String status){
		int page = 1;
		int recordsPerPage = 5;
		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));
		List<ProjectDTO> allProjectsList = projectModel
				.viewAllProjects(status, (page - 1)
				* recordsPerPage, recordsPerPage);
		if (allProjectsList == null) {
			projectMessage = "No Projects to display";
		}
		int noOfRecords = userModel.getNoOfProjectRecords(status);
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0
				/ recordsPerPage);
		request.setAttribute("allProjectsList", allProjectsList);
		request.setAttribute("projectMessage", projectMessage);
		request.setAttribute("noOfPages", noOfPages);
		request.setAttribute("currentPage", page);
	}

}
