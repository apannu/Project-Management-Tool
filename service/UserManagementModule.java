package com.capgemini.cisco.portal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.capgemini.cisco.portal.dao.ProjectDAO;
import com.capgemini.cisco.portal.dao.UserDAO;
import com.capgemini.cisco.portal.dto.ProjectDTO;
import com.capgemini.cisco.portal.dto.ResourceDTO;
import com.capgemini.cisco.portal.dto.UserDTO;

public class UserManagementModule implements UserManagementInterface {

	UserDAO userDAO = new UserDAO();
	ProjectDAO projectDAO = new ProjectDAO();
	List<UserDTO> userDTOList = new ArrayList<UserDTO>();
	Map<Integer, List<UserDTO>> hashmap = new HashMap<Integer, List<UserDTO>>();
	List<ResourceDTO> pollsList = new ArrayList<ResourceDTO>();
	List<String> list = null;

	@Override
	public boolean addUser(UserDTO userDTO) throws Exception {
		boolean flagUserExist = userDAO.ifUserExists(userDTO.getUsername());
		if (!flagUserExist)
			userDAO.addUser(userDTO);
		return flagUserExist;
	}

	@Override
	public List<ResourceDTO> pollSearch(String pollTopic) {
		pollsList = userDAO.pollSearch(pollTopic);
		return pollsList;
	}

	@Override
	public List<UserDTO> getUsersUnderManager(String managerName) {
		userDTOList = userDAO.getUsersUnderManager(managerName);
		return userDTOList;
	}

	@Override
	public boolean grantAdminRights(String username) {
		boolean flagAdminRights = userDAO.grantAdminRights(username);
		return flagAdminRights;
	}
	@Override

	public boolean untagUser(String username) {

	boolean falgUntagUser = false;

	try {

	falgUntagUser = userDAO.untagUser(username);

	} catch (Exception e) {

	// TODO Auto-generated catch block

	e.printStackTrace();

	}

	return falgUntagUser;

	}


	@Override
	public List<UserDTO> getAdminUsers() {
		List<UserDTO> adminUsersList = userDAO.getAdminUsers();
		return adminUsersList;
	}

	@Override
	public List<UserDTO> getnonAdminUsers() {
		List<UserDTO> nonAdminUsersList = userDAO.getnonAdminUsers();
		return nonAdminUsersList;
	}

	@Override
	public boolean revokeAdminRights(String username) {
		boolean flagAdminRights = userDAO.revokeAdminRights(username);
		return flagAdminRights;
	}

	@Override
	public boolean ifManagerTagged(int projectCode) {
		boolean flagManagerExists = userDAO.ifManagerTagged(projectCode);
		return flagManagerExists;
	}

	@Override
	public List<String> getManagerList(int accessLevel) {

		List<String> list = userDAO.getManagerList(accessLevel);
		return list;
	}

	@Override
	public int getUserAccessLevel(String designation) {

		int accessLevel = userDAO.getUserAccessLevel(designation);
		return accessLevel;
	}

	@Override
	public List<String> getSkills() {

		List<String> skillList = userDAO.fetchSkill();
		return skillList;
	}

	@Override
	public boolean checkManager(String username) {
		boolean flagManagerExists = userDAO.checkManager(username);
		return flagManagerExists;
	}

	@Override
	public boolean addPollReply(ResourceDTO resourceDTO) {
		boolean flagPollReply = userDAO.addPollReply(resourceDTO);
		return flagPollReply;
	}

	@Override
	public List<UserDTO> viewUsers(String username, String searchType) {
		if (searchType.equals("Search by User Name"))
			userDTOList = userDAO.getUsersListByUsername(username);
		else if (searchType.equals("Search by CG Manager Name"))
			userDTOList = userDAO.getUsersListByManagerName(username);
		return userDTOList;
	}

	@Override
	public boolean createPoll(ResourceDTO resourceDTO) {
		boolean flagCreatePoll = userDAO.createPoll(resourceDTO);
		return flagCreatePoll;
	}

	@Override
	public List<UserDTO> viewActiveUsers() {

		List<UserDTO> usersList = userDAO.viewActiveUsers();
		return usersList;
	}

	@Override
	public Map<Integer, List<UserDTO>> viewUserDetails(String username) {

		Map<Integer, List<UserDTO>> hashmap = userDAO.getUserDetails(username);
		return hashmap;
	}

	@Override
	public boolean tagUserToProject(UserDTO userDTO) {
		boolean flagTagUser = userDAO.tagUserToProject(userDTO);
		return flagTagUser;
	}

	@Override
	public List<UserDTO> getUntaggedUsers() {
		userDTOList = projectDAO.getUntaggedUsers();
		return userDTOList;
	}

	@Override
	public List<UserDTO> getProjectCode() {
		userDTOList = projectDAO.getProjectCode();
		return userDTOList;
	}

	@Override
	public List<UserDTO> getUserRole() {
		userDTOList = projectDAO.getUserRole();
		return userDTOList;
	}

	@Override
	public List<String> getVPandADNames() {

		List<String> list = userDAO.getVPandADNames();
		return list;
	}

	@Override
	public void deleteUsers(String[] username) {

		userDAO.deleteUsers(username);
	}

	public List<String> viewSkills() {

		List<String> skillList = new ArrayList<String>();
		skillList = userDAO.viewSkills();
		return skillList;
	}

	public String loginAuthentication(String username, String password) {

		String userType = "";
		userType = userDAO.loginAuthentication(username, password);
		return userType;

	}

	public String getPassword(String username, String emailId) {

		String password = null;
		password = userDAO.getPassword(username, emailId);
		return password;
	}

	public int resetPassword(String oldPassword, String newPassword,
			String username) {

		int rowsaffected = 0;
		rowsaffected = userDAO
				.resetPassword(oldPassword, newPassword, username);
		return rowsaffected;
	}

	@Override
	public List<UserDTO> viewAllUsers(String isActive) {
		userDTOList = userDAO.viewAllUsers(isActive);
		return userDTOList;
	}

	@Override
	public Map<Integer, List<UserDTO>> viewUsersByProject(String projectName) {
		hashmap = userDAO.getUsersListByProjectName(projectName);
		return hashmap;
	}

	@Override
	public List<ResourceDTO> viewPolls(String pollStatus) {
		pollsList = userDAO.viewPolls(pollStatus);
		return pollsList;
	}

	@Override
	public List<ResourceDTO> pollDetails(String pollTopic) {
		pollsList = userDAO.pollDetails(pollTopic);
		return pollsList;
	}

	@Override
	public List<ResourceDTO> displayPollReply(String pollTopic) {
		pollsList = userDAO.displayPollReply(pollTopic);
		return pollsList;
	}

	@Override
	public List<String> getManagerList(String designation) {
		list = userDAO.getManagerList(designation);
		return list;
	}

	@Override
	public List<UserDTO> viewAllUsers(String isActive, int offset,
			int noOfRecords) {
		userDTOList = userDAO.viewAllUsers(isActive, offset, noOfRecords);
		return userDTOList;
	}

	public int getNoOfRecords() {

		int noOfRecords = userDAO.getNoOfRecords();
		return noOfRecords;
	}

	public int getNoOfRecordsByUsername(String username) {

		int noOfRecords = userDAO.getNoOfRecordsSearchByUsername(username);
		return noOfRecords;
	}

	public int getNoOfRecordsByUserByManagerName(String username) {

		int noOfRecords = userDAO.getNoOfRecordsSearchByManagerName(username);
		return noOfRecords;
	}
	
	public int getNoOfRecordsByUserByProjectName(String projectName) {

		int noOfRecords = userDAO.getNoOfRecordsSearchByProjectName(projectName);
		return noOfRecords;
	}
	
	public boolean updateUserDetailsAdmin(UserDTO userDTO) {

		boolean flagUpdateUser = userDAO.updateUserDetailsAdmin(userDTO);
		return flagUpdateUser;
	}

	public int getNoOfRecordsSkills() {

		int noOfRecords = userDAO.getNoOfRecordsSkills();
		return noOfRecords;
	}

	public List<ProjectDTO> viewLocation() {

		List<ProjectDTO> locationList = new ArrayList<ProjectDTO>();
		locationList = userDAO.viewLocation();
		return locationList;
	}

	public List<String> viewDesignation() {

		List<String> designationList = new ArrayList<String>();
		designationList = userDAO.viewDesignation();
		return designationList;
	}

	public List<UserDTO> viewCiscoManagers() {
		List<UserDTO> ciscoManagerList = new ArrayList<UserDTO>();
		ciscoManagerList = userDAO.viewCiscoManagers();
		return ciscoManagerList;
	}

	public int getNoOfPollRecords(String status) {

		int noOfRecords = userDAO.getNoOfPollRecords(status);
		return noOfRecords;
	}

	@Override
	public List<ResourceDTO> viewPolls(String pollStatus, int offset,
			int noOfRecords) {
		pollsList = userDAO.viewPolls(pollStatus, offset, noOfRecords);
		return pollsList;
	}

	public int getNoOfProjectRecords(String status) {
		int noOfRecords = userDAO.getNoOfProjectRecords(status);
		return noOfRecords;
	}

	
	
	@Override
	public boolean updateUserDetails(UserDTO userDTO) 
	{
		boolean flagUpdateUser = userDAO.updateUserDetails(userDTO);
		return flagUpdateUser;
	}

	public int addSkill(String skillName, String username) {
		
		int rowsaffected=userDAO.addSkill(skillName,username);
		return rowsaffected;
	}

	public int addLocation(String locationName, String address, String username) {
		int rowsaffected=userDAO.addLocation(locationName,address,username);
		return rowsaffected;
	}

	public int addCiscoManager(String managerUsername, String firstName,
			String lastName, String username) {
		int rowsaffected=userDAO.addCiscoManager(managerUsername,firstName,lastName,username);
		return rowsaffected;
	}

	public int activateUser(String username) {
		
		int rowsaffected=userDAO.activateUser(username);
		return rowsaffected;
	}

	public int deactivatePoll(int pollId) {
		int rowsaffected=userDAO.deactivatePoll(pollId);
		return rowsaffected;
	}



	

}
