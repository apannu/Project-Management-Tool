package com.capgemini.cisco.portal.service;

import java.util.List;
import java.util.Map;

import com.capgemini.cisco.portal.dto.ResourceDTO;
import com.capgemini.cisco.portal.dto.UserDTO;

public interface UserManagementInterface 
{
	public boolean addUser(UserDTO userDTO) throws Exception;
	
	
	public List<UserDTO> viewActiveUsers() throws Exception;
	
	public List<String> getManagerList(int accessLevel) throws Exception;
	
	int getUserAccessLevel(String designation) throws Exception;
	
	public List<String> getSkills() throws Exception;
	
	public Map<Integer, List<UserDTO>> viewUserDetails(String username) throws Exception;
	
	public boolean tagUserToProject(UserDTO userDTO) throws Exception;
	
	public List<UserDTO> getUntaggedUsers() throws Exception;
	
	public List<UserDTO> getProjectCode() throws Exception;
	
	public List<UserDTO> getUserRole() throws Exception;
	
	public List<String> getVPandADNames() throws Exception;
	
	public void deleteUsers(String[] username) throws Exception;
	
	public List<String> viewSkills() throws Exception;
	
	public String loginAuthentication(String username, String password) throws Exception;
	
	public String getPassword(String username, String emailId) throws Exception;
	
	public int resetPassword(String oldPassword, String newPassword, String username) throws Exception;

	List<UserDTO> viewAllUsers(String isActive);


	List<ResourceDTO> pollSearch(String pollTopic);

	List<ResourceDTO> pollDetails(String pollTopic);

	List<ResourceDTO> displayPollReply(String pollTopic);

	boolean addPollReply(ResourceDTO resourceDTO);

	List<String> getManagerList(String designation);

	boolean ifManagerTagged(int projectCode);

	boolean checkManager(String username);

	boolean createPoll(ResourceDTO resourceDTO);

	List<UserDTO> viewAllUsers(String isActive, int offset, int noOfRecords);
	
	public boolean updateUserDetails(UserDTO userDTO);
	
	public int getNoOfRecordsSkills();

	boolean grantAdminRights(String username);

	boolean revokeAdminRights(String username);

	List<UserDTO> getAdminUsers();

	List<UserDTO> getnonAdminUsers();

	List<UserDTO> getUsersUnderManager(String pollTopic);

	List<ResourceDTO> viewPolls(String pollStatus, int offset, int noOfRecords);

	List<ResourceDTO> viewPolls(String pollStatus);

	List<UserDTO> viewUsers(String username, String searchType);

	Map<Integer, List<UserDTO>> viewUsersByProject(String projectName);


	boolean untagUser(String username);
}
