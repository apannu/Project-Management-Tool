package com.capgemini.cisco.portal.utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

public class DisplayPollUsingPagination {
	
	static UserManagementModule userModel=new UserManagementModule();
	static String pollMessage = "";
	static List<ResourceDTO> allPollsList=new ArrayList<ResourceDTO>();
	
	public static void displayPollsPagination(HttpServletRequest request, HttpServletResponse response, String status){
		int page = 1;
		int recordsPerPage = 4;
		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));
		allPollsList= userModel
				.viewPolls(status, (page - 1)
				* recordsPerPage, recordsPerPage);
		if (allPollsList == null) {
			pollMessage = "No Polls to display";
		}
		int noOfRecords = userModel.getNoOfPollRecords(status);
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0
				/ recordsPerPage);
		request.setAttribute("allPollsList", allPollsList);
		request.setAttribute("pollMessage", pollMessage);
		request.setAttribute("noOfPages", noOfPages);
		request.setAttribute("currentPage", page);
	}

}
