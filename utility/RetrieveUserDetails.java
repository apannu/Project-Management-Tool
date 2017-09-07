package com.capgemini.cisco.portal.utility;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.capgemini.cisco.portal.dto.UserDTO;
import com.capgemini.cisco.portal.service.UserManagementModule;


public class RetrieveUserDetails {

	public static void getUserDetails(String username, HttpServletRequest request, HttpServletResponse response) {
		Map<Integer, List<UserDTO>> hashmap = null;
		UserManagementModule userModel=new UserManagementModule();
		hashmap = userModel.viewUserDetails(username);
		List<String> designationList = DesignationNamesUtil
				.getDesignationNames();
		List<String> skillList = userModel.getSkills();

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
		request.setAttribute("designationList", designationList);
		request.setAttribute("skillList", skillList);
	}

}
