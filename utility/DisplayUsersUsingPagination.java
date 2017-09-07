package com.capgemini.cisco.portal.utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capgemini.cisco.portal.dto.UserDTO;
import com.capgemini.cisco.portal.service.UserManagementModule;

public class DisplayUsersUsingPagination {
	
	static List<UserDTO> allUsersList=new ArrayList<UserDTO>();
	static UserManagementModule userModel=new UserManagementModule();
	static String errorMessage = "";
	
	public static void displayUsersPagination(HttpServletRequest request, HttpServletResponse response){
		int page = 1;
		int recordsPerPage = 7;
		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));
		allUsersList = userModel.viewAllUsers("Y", (page - 1)
				* recordsPerPage, recordsPerPage);
		if (allUsersList == null) {
			errorMessage = "No Users";
		}
		int noOfRecords = userModel.getNoOfRecords();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0
				/ recordsPerPage);
		request.setAttribute("allUsersList", allUsersList);
		request.setAttribute("errorMessage", errorMessage);
		request.setAttribute("noOfPages", noOfPages);
		request.setAttribute("currentPage", page);
	}

}
