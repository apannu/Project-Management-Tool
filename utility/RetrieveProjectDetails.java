package com.capgemini.cisco.portal.utility;


import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capgemini.cisco.portal.dto.ProjectDTO;
import com.capgemini.cisco.portal.dto.ResourceDTO;
import com.capgemini.cisco.portal.service.ProjectManagementModule;


public class RetrieveProjectDetails {
	
	static ProjectManagementModule projectModel = new ProjectManagementModule();

	public static void getProjectDetails(String projectName, HttpServletRequest request, HttpServletResponse response) {
		Map<Integer, List<ProjectDTO>> hashmap = projectModel
				.getProjectDetails(projectName);
		List<ProjectDTO> projectDetailsList = (List<ProjectDTO>) hashmap
				.get(1);
		List<ProjectDTO> locationList = (List<ProjectDTO>) hashmap.get(2);
		List<ProjectDTO> userDetailsList = (List<ProjectDTO>) hashmap
				.get(3);
		List<ProjectDTO> managerDetailsList = (List<ProjectDTO>) hashmap
				.get(4);
		List<ResourceDTO> projectFilesList = projectModel
				.fileList(request.getParameter("projectName"));
		request.setAttribute("projectFilesList", projectFilesList);
		request.setAttribute("projectDetailsList", projectDetailsList);
		request.setAttribute("locationList", locationList);
		request.setAttribute("userDetailsList", userDetailsList);
		request.setAttribute("managerDetailsList", managerDetailsList);
	}

}
