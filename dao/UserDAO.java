package com.capgemini.cisco.portal.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.capgemini.cisco.portal.dto.ProjectDTO;
import com.capgemini.cisco.portal.dto.ResourceDTO;
import com.capgemini.cisco.portal.dto.UserDTO;
import com.capgemini.cisco.portal.utility.DBConnection;
import com.capgemini.cisco.portal.utility.FetchDesignationUtil;
import com.capgemini.cisco.portal.utility.FetchLocationUtil;
import com.capgemini.cisco.portal.utility.FetchSkillUtil;

public class UserDAO {

	private Connection connection;
	

	public UserDAO() {
		try {

			connection = DBConnection.getDBConnection();

		} catch (Exception e) {
			System.out.println("Error in Database Connection");
			e.printStackTrace();
		}
	}

	// for adding the users in AddUser.jsp
	public boolean addUser(UserDTO userDTO) {
		boolean flagAddUser = false;
		PreparedStatement preparedStatementInsert = null;
		PreparedStatement preparedStatementUpdate = null;
		String addUserDetails = "insert into user_details values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String addUserSkills = "insert into user_skillset_details values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			connection.setAutoCommit(false);
			preparedStatementInsert = connection
					.prepareStatement(addUserDetails);
			preparedStatementInsert.setString(1, userDTO.getUsername());
			preparedStatementInsert.setInt(2, userDTO.getEmployeeId());
			preparedStatementInsert.setString(3, userDTO.getUsername());
			preparedStatementInsert.setString(4, userDTO.getUserType());
			preparedStatementInsert.setString(5, userDTO.getFirstName());
			preparedStatementInsert.setString(6, userDTO.getLastName());
			preparedStatementInsert.setString(7, userDTO.getCapgeminiEmailId());
			preparedStatementInsert.setString(8,
					userDTO.getPrimaryContactNumber());
			preparedStatementInsert.setString(9,
					userDTO.getEmergencyContactNumber());
			preparedStatementInsert.setDate(10, userDTO.getCiscoDOJ());
			preparedStatementInsert.setInt(11,
					getDesignationID(userDTO.getDesignation()));
			preparedStatementInsert.setInt(12,
					getUserAccessLevel(userDTO.getDesignation()));
			preparedStatementInsert.setString(13, userDTO.getIsBillable());
			preparedStatementInsert.setString(14, userDTO.getIsActive());
			preparedStatementInsert.setString(15,
					userDTO.getCapgeminiManagerName());
			preparedStatementInsert
					.setString(16, userDTO.getCiscoManagerName());
			preparedStatementInsert.setString(17, userDTO.getCreateUserId());
			preparedStatementInsert.setDate(18, userDTO.getCreateDate());
			preparedStatementInsert.setString(19, userDTO.getModifyUserId());
			preparedStatementInsert.setDate(20, userDTO.getModifydate());
			preparedStatementInsert.setString(21, userDTO.getCiscoUsername());
			preparedStatementInsert.setString(22,
					userDTO.getIs_delivery_manager());
			preparedStatementInsert.executeUpdate();
			// for inserting in skillstable
			preparedStatementUpdate = connection
					.prepareStatement(addUserSkills);
			preparedStatementUpdate.setString(1, userDTO.getUsername());
			preparedStatementUpdate.setString(2, userDTO.getServiceLine());
			preparedStatementUpdate.setInt(3,
					getSkillID(userDTO.getPrimarySkill()));
			preparedStatementUpdate.setInt(4,
					getSkillID(userDTO.getSecondarySkill_01()));
			preparedStatementUpdate.setInt(5,
					getSkillID(userDTO.getSecondarySkill_02()));
			preparedStatementUpdate.setString(6, userDTO.getCreateUserId());
			preparedStatementUpdate.setDate(7, userDTO.getCreateDate());
			preparedStatementUpdate.setString(8, userDTO.getModifyUserId());
			preparedStatementUpdate.setDate(9, userDTO.getModifydate());
			preparedStatementUpdate.setInt(10, userDTO.getTotalExperience());
			preparedStatementUpdate.executeUpdate();
			connection.commit();
			flagAddUser = true;
		} catch (Exception e) {
			System.out.println("Problem in adding the user: " + e.getMessage());
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("error4: " + e.getMessage());
			}
		} finally {
			try {
				preparedStatementInsert.close();
				preparedStatementUpdate.close();
			} catch (SQLException e) {
				System.out.println("Problem in closing the connection");
			}
		}
		return flagAddUser;
	}
	//for untagging user

	public boolean untagUser(String userName) throws Exception 

	{

	boolean flagUntagUser = false;

	PreparedStatement preparedStatement = null;

	try 

	{

	connection.setAutoCommit(false);

	String untagUserQuery = "update user_details set is_billable='N' where username=?";

	preparedStatement = connection.prepareStatement(untagUserQuery);

	preparedStatement.setString(1, userName);

	preparedStatement.executeUpdate();


	String untagUserQuery2 = "update user_project_details set is_presently_tagged = 'N' and cisco_email_id = '' where username=?";

	preparedStatement = connection.prepareStatement(untagUserQuery2);

	preparedStatement.setString(1, userName);

	preparedStatement.executeUpdate();


	connection.commit();

	flagUntagUser = true;

	} 

	catch (Exception e) {

	System.out.println("Problem in untagging the user: " + e);

	try{

	connection.rollback();

	}

	catch(Exception e1)

	{


	}

	} finally {

	preparedStatement.close();

	}

	return flagUntagUser;

	}



	// for checking if username already exists in AddUser.jsp
	public boolean ifUserExists(String userName) throws Exception {
		boolean flagUserExists = false;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			String selectUserName = "select username from User_Details where username=?";
			preparedStatement = connection.prepareStatement(selectUserName);
			preparedStatement.setString(1, userName);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String dbUserName = resultSet.getString("username");
				if (userName.equalsIgnoreCase(dbUserName)) {
					flagUserExists = true;
					// throw new PortalException("The User "
					// +username+" already exists");
				}
			}
		} catch (Exception e) {
			System.out.println("error6: " + e);
		} finally {
			resultSet.close();
			preparedStatement.close();
		}
		return flagUserExists;
	}

	public List<String> fetchSkill() {
		Statement statement = null;
		ResultSet resultSet = null;
		List<String> skillList = new ArrayList<String>();
		try {
			String selectSkills = "select skill_name from skillset_details";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(selectSkills);
			while (resultSet.next()) {
				skillList.add(resultSet.getString(1));
			}
		} catch (Exception e) {
			System.out.println("Problem in displaying skills name");
		}
		return skillList;
	}

	public int getDesignationID(String designationName) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int designationID = 0;
		try {
			String selectDesignationID = "select Designation_ID from Designation_Details where Designation_Name = ?";
			preparedStatement = connection
					.prepareStatement(selectDesignationID);
			preparedStatement.setString(1, designationName);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				designationID = resultSet.getInt("Designation_ID");
			}
		} catch (Exception e) {
			System.out.println("error7: " + e.getMessage());
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				System.out.println("error in closing the connection");
			}
		}
		return designationID;
	}

	public int getUserAccessLevel(String designationName) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int userAccessLevel = 0;
		try {
			String selectAccessLevel = "select access_level from Designation_Details where Designation_Name = ?";
			preparedStatement = connection.prepareStatement(selectAccessLevel);
			preparedStatement.setString(1, designationName);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				userAccessLevel = resultSet.getInt("access_level");
			}
		} catch (Exception e) {
			System.out.println("error8: " + e.getMessage());
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				System.out.println();
			}

		}
		return userAccessLevel;
	}

	public List<String> getManagerList(int accessLevel) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<String> list = new ArrayList<String>();
		try {
			String selectManagerQuery = "select username from user_details where access_level > ?";
			preparedStatement = connection.prepareStatement(selectManagerQuery);
			preparedStatement.setInt(1, accessLevel);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String managerName = resultSet.getString("username");
				list.add(managerName);
			}
		} catch (Exception e) {
			System.out.println("error8: " + e.getMessage());
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				System.out.println("error8345: " + e.getMessage());
			}

		}
		return list;
	}

	// for displaying users by name in ViewUsers.jsp
	public List<UserDTO> getUsersListByUsername(String username) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<UserDTO> userList = new ArrayList<UserDTO>();
		try {
			String usersQuery = "select a.username,a.cisco_username,a.first_name,a.last_name,a.primary_contact_number,a.cg_manager_username,a.is_billable,b.project_code from user_details a left join user_project_details b on a.username=b.username where a.username like ? and a.is_active='Y' and b.is_presently_tagged = 'Y'";
			preparedStatement = connection.prepareStatement(usersQuery);
			preparedStatement.setString(1, username + "%");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				UserDTO userDTO = new UserDTO();
				userDTO.setUsername(resultSet.getString("username"));
				userDTO.setCiscoUsername(resultSet.getString("cisco_username"));
				userDTO.setFirstName(resultSet.getString("first_name"));
				userDTO.setLastName(resultSet.getString("last_name"));
				userDTO.setPrimaryContactNumber(resultSet
						.getString("primary_contact_number"));
				userDTO.setCapgeminiManagerName(resultSet
						.getString("cg_manager_username"));
				userDTO.setIsBillable(resultSet.getString("is_billable"));
				userDTO.setProject_code(resultSet.getInt("project_code"));
				userList.add(userDTO);
			}
		} catch (Exception e) {
			System.out.println("error121" + e);
		} finally {
			try {
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {
				System.out.println("Error in closing the connection" + e);
			}
		}
		return userList;
	}

	// for displaying users by project in ViewUsers.jsp
	public Map<Integer, List<UserDTO>> getUsersListByProjectName(
			String projectName) {
		Map<Integer, List<UserDTO>> hashmap = new HashMap<Integer, List<UserDTO>>();
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		ResultSet resultSet1 = null;
		ResultSet resultSet2 = null;
		int projectCode = 0;
		List<UserDTO> userList = new ArrayList<UserDTO>();
		List<UserDTO> userList1 = new ArrayList<UserDTO>();
		// String getProjectNameQuery =
		// "select project_code,project_manager from project_details where project_name like ? and status = 'On Going'";
		String managersQuery = "select a.username,a.cisco_username,a.first_name,a.last_name,a.primary_contact_number,a.cg_manager_username,a.is_billable,b.project_code from user_details a left join project_details b on a.username=b.project_manager where b.project_manager in (select project_manager from project_details where project_name like ? and b.status='On Going') ";
		try {
			connection.setAutoCommit(false);
			preparedStatement1 = connection.prepareStatement(managersQuery);
			preparedStatement1.setString(1, projectName + "%");

			resultSet1 = preparedStatement1.executeQuery();
			if (resultSet1.next()) {
				projectCode = resultSet1.getInt("project_code");
				// managerName = resultSet1.getString("project_manager");

				UserDTO userDTO = new UserDTO();
				userDTO.setUsername(resultSet1.getString("username"));
				userDTO.setCiscoUsername(resultSet1.getString("cisco_username"));
				userDTO.setFirstName(resultSet1.getString("first_name"));
				userDTO.setLastName(resultSet1.getString("last_name"));
				userDTO.setPrimaryContactNumber(resultSet1
						.getString("primary_contact_number"));
				userDTO.setCapgeminiManagerName(resultSet1
						.getString("cg_manager_username"));
				userDTO.setIsBillable(resultSet1.getString("is_billable"));
				userDTO.setProject_code(resultSet1.getInt("project_code"));
				userList.add(userDTO);
				hashmap.put(new Integer(1), userList);
				userDTO = null;
			}

			String usersQuery = "select a.username,a.cisco_username,a.first_name,a.last_name,a.primary_contact_number,a.cg_manager_username,a.is_billable,b.project_code from user_details a left join user_project_details b on a.username=b.username where a.username in (select username from user_project_details where project_code = ? and a.is_active='Y')";
			preparedStatement2 = connection.prepareStatement(usersQuery);
			preparedStatement2.setInt(1, projectCode);
			resultSet2 = preparedStatement2.executeQuery();
			while (resultSet2.next()) {
				UserDTO userDTO = new UserDTO();
				userDTO.setUsername(resultSet2.getString("username"));
				userDTO.setCiscoUsername(resultSet2.getString("cisco_username"));
				userDTO.setFirstName(resultSet2.getString("first_name"));
				userDTO.setLastName(resultSet2.getString("last_name"));
				userDTO.setPrimaryContactNumber(resultSet2
						.getString("primary_contact_number"));
				userDTO.setCapgeminiManagerName(resultSet2
						.getString("cg_manager_username"));
				userDTO.setIsBillable(resultSet2.getString("is_billable"));
				userDTO.setProject_code(resultSet2.getInt("project_code"));
				userList1.add(userDTO);
				hashmap.put(new Integer(2), userList1);
				userDTO = null;
			}
			connection.commit();
			for (int i = 0; i < userList.size(); i++) {
				System.out.println("userlist1: "
						+ userList.get(i).getUsername());
			}

			for (int i = 0; i < userList1.size(); i++) {
				System.out.println("userlist2: "
						+ userList1.get(i).getUsername());
			}
		} catch (Exception e) {
			System.out.println("error128" + e);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("error in rollback");
			}
		} finally {
			try {
				preparedStatement1.close();
				preparedStatement2.close();
				resultSet1.close();
				resultSet2.close();
			} catch (SQLException e) {
				System.out.println("Error in closing the connection" + e);
			}
		}
		return hashmap;
	}

	public List<UserDTO> getUsersListByManagerName(String username) {
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		ResultSet resultSet1 = null;
		ResultSet resultSet2 = null;
		int accessLevel = 0;
		List<UserDTO> userList = new ArrayList<UserDTO>();
		try {
			connection.setAutoCommit(false);
			String managerLevelQuery = "select access_level from designation_details where designation_name = 'P5'";
			preparedStatement2 = connection.prepareStatement(managerLevelQuery);
			resultSet2 = preparedStatement2.executeQuery();
			while (resultSet2.next()) {
				accessLevel = resultSet2.getInt("access_level");
			}

			String usersQuery = "select a.username,a.first_name,a.last_name,a.primary_contact_number,a.cg_manager_username,a.is_billable,b.project_code from user_details a left join user_project_details b on a.username=b.username where a.username in (select username from user_details where username like ? and access_level > ? and a.is_active='Y' and b.is_presently_tagged='Y') ";
			preparedStatement1 = connection.prepareStatement(usersQuery);
			preparedStatement1.setString(1, username + "%");
			preparedStatement1.setInt(2, accessLevel);
			resultSet1 = preparedStatement1.executeQuery();
			while (resultSet1.next()) {
				UserDTO userDTO = new UserDTO();
				userDTO.setUsername(resultSet1.getString("username"));
				userDTO.setFirstName(resultSet1.getString("first_name"));
				userDTO.setLastName(resultSet1.getString("last_name"));
				userDTO.setPrimaryContactNumber(resultSet1
						.getString("primary_contact_number"));
				userDTO.setCapgeminiManagerName(resultSet1
						.getString("cg_manager_username"));
				userDTO.setIsBillable(resultSet1.getString("is_billable"));
				userDTO.setProject_code(resultSet1.getInt("project_code"));
				userList.add(userDTO);
			}
			connection.commit();
		} catch (Exception e) {
			System.out.println("error128" + e);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("error in rollback");
			}
		} finally {
			try {
				preparedStatement1.close();
				resultSet1.close();
			} catch (SQLException e) {
				System.out.println("Error in closing the connection" + e);
			}
		}
		return userList;
	}

	public int getSkillID(String skillName) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int skillID = 0;
		try {
			String selectSkillID = "select skill_id from Skillset_details where skill_name = ?";
			preparedStatement = connection.prepareStatement(selectSkillID);
			preparedStatement.setString(1, skillName);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				skillID = resultSet.getInt("skill_id");
			}
		} catch (Exception e) {
			System.out.println("error9: " + e.getMessage());
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				System.out.println("error in closing the connection");
			}

		}
		return skillID;
	}

	// displaying the user details in ViewUserDetails.jsp
	public Map<Integer, List<UserDTO>> getUserDetails(String username) {
		Map<Integer, List<UserDTO>> hashmap = new HashMap<Integer, List<UserDTO>>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int projectCode = 0;
		int roleID = 0;
		String managerName1 = "";
		String managerName2 = "";
		try {
			String userDetailsQuery = "select a.employee_id,a.username,a.cisco_username,a.first_name,a.last_name,a.designation_id,a.cisco_doj,a.cg_email_id,a.primary_contact_number,a.emergency_contact_number,a.cg_manager_username,a.is_billable,a.created_username,a.created_date,a.last_modified_username,a.last_modified_date,b.primary_skill_id,b.secondary_skill_01_id,b.secondary_skill_02_id from user_details a left join user_skillset_Details b on a.username = b.username where a.username = ?";
			preparedStatement = connection.prepareStatement(userDetailsQuery);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				UserDTO userDTO = new UserDTO();
				userDTO.setEmployeeId(resultSet.getInt("employee_id"));
				userDTO.setUsername(resultSet.getString("username"));
				userDTO.setFirstName(resultSet.getString("first_name"));
				userDTO.setLastName(resultSet.getString("last_name"));
				userDTO.setDesignation(FetchDesignationUtil
						.getDesignationName(resultSet.getInt("designation_id")));
				userDTO.setCapgeminiEmailId(resultSet.getString("cg_email_id"));
				userDTO.setPrimaryContactNumber(resultSet
						.getString("primary_contact_number"));
				userDTO.setEmergencyContactNumber(resultSet
						.getString("emergency_contact_number"));
				userDTO.setCiscoDOJ(resultSet.getDate("cisco_doj"));
				userDTO.setCapgeminiManagerName(resultSet
						.getString("cg_manager_username"));
				userDTO.setCiscoUsername(resultSet.getString("cisco_username"));
				userDTO.setIsBillable(resultSet.getString("is_billable"));
				userDTO.setCreateUserId(resultSet.getString("created_username"));
				userDTO.setCreateDate(resultSet.getDate("created_date"));
				userDTO.setModifyUserId(resultSet
						.getString("last_modified_username"));
				userDTO.setModifydate(resultSet.getDate("last_modified_date"));
				userDTO.setPrimarySkill(FetchSkillUtil.getSkillName(resultSet
						.getInt("primary_skill_id")));
				userDTO.setSecondarySkill_01(FetchSkillUtil
						.getSkillName(resultSet.getInt("secondary_skill_01_id")));
				userDTO.setSecondarySkill_02(FetchSkillUtil
						.getSkillName(resultSet.getInt("secondary_skill_02_id")));
				managerName1 = resultSet.getString("cg_manager_username");
				System.out.println(managerName1);
				List<UserDTO> list1 = new ArrayList<UserDTO>();
				list1.add(userDTO);
				hashmap.put(new Integer(1), list1);
			}
			System.out.println("manager is: " + managerName1);
			String projectDetailsQuery = "select role_id,project_code,tag_start_date,tag_end_date,cisco_email_id,location_id from user_project_details where username = ? and is_presently_tagged = 'Y'";
			preparedStatement = connection
					.prepareStatement(projectDetailsQuery);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				UserDTO userDTO = new UserDTO();
				projectCode = resultSet.getInt("project_code");
				roleID = resultSet.getInt("role_id");
				userDTO.setProject_code(resultSet.getInt("project_code"));
				userDTO.setStartDate(resultSet.getDate("tag_start_date"));
				userDTO.setEndDate(resultSet.getDate("tag_end_date"));
				userDTO.setCiscoEmailId(resultSet.getString("cisco_email_id"));
				userDTO.setLocationName(FetchLocationUtil
						.getLocationName(resultSet.getInt("location_id")));
				List<UserDTO> list2 = new ArrayList<UserDTO>();
				list2.add(userDTO);
				hashmap.put(new Integer(2), list2);
			}

			String projectNameQuery = "select a.first_name,a.last_name,b.project_name,b.project_manager from user_details a left join project_details b on a.username=b.project_manager where b.project_code = ?";
			preparedStatement = connection.prepareStatement(projectNameQuery);
			preparedStatement.setInt(1, projectCode);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				UserDTO userDTO = new UserDTO();
				userDTO.setProjectManager(resultSet
						.getString("project_manager"));
				userDTO.setProjectName(resultSet.getString("project_name"));
				userDTO.setFirstName(resultSet.getString("first_name"));
				userDTO.setLastName(resultSet.getString("last_name"));
				List<UserDTO> list3 = new ArrayList<UserDTO>();
				list3.add(userDTO);
				hashmap.put(new Integer(3), list3);
			}
			// for generating
			String managerName2Query = "select username,first_name,last_name,cg_manager_username from user_details where username = ?";
			preparedStatement = connection.prepareStatement(managerName2Query);
			preparedStatement.setString(1, managerName1);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				UserDTO userDTO = new UserDTO();
				userDTO.setUsername(resultSet.getString("username"));
				userDTO.setFirstName(resultSet.getString("first_name"));
				userDTO.setLastName(resultSet.getString("last_name"));
				userDTO.setManagerName2(resultSet
						.getString("cg_manager_username"));
				managerName2 = resultSet.getString("cg_manager_username");
				System.out.println("manager1 is: " + managerName1);
				List<UserDTO> list4 = new ArrayList<UserDTO>();
				list4.add(userDTO);
				hashmap.put(new Integer(4), list4);
			}

			String manager2NameQuery = "select first_name,last_name from user_details where username = ?";
			preparedStatement = connection.prepareStatement(manager2NameQuery);
			preparedStatement.setString(1, managerName2);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				UserDTO userDTO = new UserDTO();
				userDTO.setFirstName(resultSet.getString("first_name"));
				userDTO.setLastName(resultSet.getString("last_name"));
				List<UserDTO> list6 = new ArrayList<UserDTO>();
				list6.add(userDTO);
				hashmap.put(new Integer(6), list6);
			}

			String roleNameQuery = "select role_name from project_role_details where role_id = ?";
			preparedStatement = connection.prepareStatement(roleNameQuery);
			preparedStatement.setInt(1, roleID);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {

				UserDTO userDTO = new UserDTO();
				userDTO.setProjectRoleName(resultSet.getString("role_name"));
				List<UserDTO> list7 = new ArrayList<UserDTO>();
				list7.add(userDTO);
				hashmap.put(new Integer(7), list7);
			}
		} catch (Exception e) {
			System.out.println("error845: " + e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				System.out.println("error in closing the connection");
			}

		}
		return hashmap;
	}

	public Map<Integer, String> getProjectName(int projectCode) {
		Map<Integer, String> hashmap = new HashMap<Integer, String>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String projectName = "";
		String projectManager = "";
		try {
			String projectDetailQuery = "select project_name,project_manager from project_details where project_code = ?";
			preparedStatement = connection.prepareStatement(projectDetailQuery);
			preparedStatement.setInt(1, projectCode);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				projectName = resultSet.getString("project_name");
				projectManager = resultSet.getString("project_manager");
			}
			hashmap.put(new Integer(1), projectName);
			hashmap.put(new Integer(2), projectManager);
		} catch (Exception e) {
			System.out.println("error128" + e);
		} finally {
			try {
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {
				System.out.println("Error in closing the connection" + e);
			}
		}
		return hashmap;
	}

	// for tagging the user to a project in TagUser.jsp
	public boolean tagUserToProject(UserDTO userDTO) {
		boolean flagAddProject = false;
		PreparedStatement preparedStatement = null;
		String updateBillableQuery = "update user_details set is_billable='Y',cisco_username=? where username=?";
		String tagUserQuery = "insert into user_project_details values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			connection.setAutoCommit(false);

			preparedStatement = connection
					.prepareStatement(updateBillableQuery);
			preparedStatement.setString(1, userDTO.getCiscoUsername());
			preparedStatement.setString(2, userDTO.getUsername());
			preparedStatement.executeUpdate();

			preparedStatement = connection.prepareStatement(tagUserQuery);
			preparedStatement.setString(1, userDTO.getUsername());
			preparedStatement.setInt(2, userDTO.getProject_code());
			preparedStatement.setInt(3, userDTO.getBillHours());
			preparedStatement.setDate(4, userDTO.getStartDate());
			preparedStatement.setDate(5, userDTO.getEndDate());
			preparedStatement.setString(6, userDTO.getIsPresentlyTagged());
			preparedStatement.setString(7, userDTO.getIsOnShore());
			preparedStatement.setInt(8, userDTO.getProjectRoleID());
			preparedStatement.setInt(9, userDTO.getLocationID());
			preparedStatement.setString(10, userDTO.getCreateUserId());
			preparedStatement.setDate(11, userDTO.getStartDate());
			preparedStatement.setString(12, userDTO.getModifyUserId());
			preparedStatement.setDate(13, userDTO.getModifydate());
			preparedStatement.setString(14, userDTO.getCiscoUsername());
			preparedStatement.setString(15, userDTO.getCiscoEmailId());
			preparedStatement.executeUpdate();

			connection.commit();
			flagAddProject = true;
		} catch (Exception e) {
			System.out.println("Problem in tagging the user to project: " + e);
			try {
				connection.rollback();
			} catch (Exception e1) {
				System.out.println("problem in rollback" + e1);
			}
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				System.out.println("Problem in closing the connection");
			}
		}
		return flagAddProject;
	}

	public boolean ifUserTagged(String username) {
		boolean flagUserTagged = false;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			String checkTaggingQuery = "select is_presently_tagged from user_project_details where username=?";
			preparedStatement = connection.prepareStatement(checkTaggingQuery);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String isBillable = resultSet.getString("is_presently_tagged");
				if (isBillable.equalsIgnoreCase("y")) {
					flagUserTagged = true;
					// throw new PortalException("The User "
					// +username+" already exists");
				}
			}
		} catch (Exception e) {
			System.out.println("error69: " + e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				System.out.println("error79: " + e);
			}
		}
		return flagUserTagged;
	}

	public List<String> getVPandADNames() {
		List<String> list = new ArrayList<String>();
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		String VPName = "";
		String ADName = "";
		try {
			String getADName = "select first_name,last_name from user_details where designation_id in (select designation_id from designation_details where designation_name=?)";
			preparedStatement = connection.prepareStatement(getADName);
			preparedStatement.setString(1, "Associate Director");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				ADName = resultSet.getString("first_name") + " "
						+ resultSet.getString("last_name");
			}
			list.add(ADName);
			String getVPName = "select first_name,last_name from user_details where designation_id in (select designation_id from designation_details where designation_name=?)";
			preparedStatement = connection.prepareStatement(getVPName);
			preparedStatement.setString(1, "Vice President");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				VPName = resultSet.getString("first_name") + " "
						+ resultSet.getString("last_name");
			}
			list.add(VPName);
		} catch (Exception e) {
			System.out.println("error6129: " + e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				System.out.println("error723239: " + e);
			}
		}
		return list;
	}

	public List<UserDTO> viewActiveUsers() {

		ResultSet resultSet = null;
		Statement statement = null;
		List<UserDTO> usersList = new ArrayList<UserDTO>();
		try {
			String selectUsersQuery = "select a.username,a.first_name,a.last_name,a.primary_contact_number,a.cg_manager_username,a.is_billable,b.project_code from user_details a left join user_project_details b on a.username=b.username where a.is_active='Y'";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(selectUsersQuery);

			while (resultSet.next()) {

				UserDTO userDTO = new UserDTO();
				userDTO.setUsername(resultSet.getString("username"));
				userDTO.setFirstName(resultSet.getString("first_name"));
				userDTO.setLastName(resultSet.getString("last_name"));
				userDTO.setPrimaryContactNumber(resultSet
						.getString("primary_contact_number"));
				userDTO.setProject_code(resultSet.getInt("project_code"));
				userDTO.setCapgeminiManagerName(resultSet
						.getString("cg_manager_username"));
				userDTO.setIsBillable(resultSet.getString("is_billable"));
				usersList.add(userDTO);
			}
		} catch (Exception e) {
			System.out.println("error61: " + e);
		} finally {
			try {
				resultSet.close();
				statement.close();
			} catch (SQLException e) {
				System.out.println("error in closing connections: " + e);
			}
		}
		return usersList;
	}

	public void deleteUsers(String[] username) {

		PreparedStatement ps = null;
		int i = 0;
		String queryString = "";
		int len = username.length;

		for (i = 1; i <= len; i++) {
			queryString = queryString + "?";
			if (i <= (len - 1)) {
				queryString = queryString + ",";
			}
		}
		String delete = "update user_details set is_active = 'N' where username in ("
				+ queryString + ")";

		try {
			ps = connection.prepareStatement(delete);
			for (int j = 0; j < len; j++) {
				ps.setString(j + 1, username[j]);
			}
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				System.out.println("Problem in closing the connection");
			}
		}

	}

	public List<String> viewSkills() {

		ResultSet resultSet = null;
		Statement statement = null;
		String skillname = null;

		List<String> skillList = new ArrayList<String>();
		try {
			String selectskills = "select skill_name from skillset_details";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(selectskills);

			while (resultSet.next()) {

				skillname = (resultSet.getString("skill_name"));
				skillList.add(skillname);

			}
		} catch (Exception e) {
			System.out.println("error61: " + e);
		} finally {
			try {
				resultSet.close();
				statement.close();
			} catch (SQLException e) {
				System.out.println("error in closing conenctions: " + e);
			}
		}
		return skillList;

	}

	public String loginAuthentication(String username, String password) {

		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		String usertype = "";

		try {
			String checkUser = "select username,password,usertype from user_details where username = ? and password = ?";
			preparedStatement = connection.prepareStatement(checkUser);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				usertype = resultSet.getString("usertype");
			} else {
				usertype = "No User found";
			}
		} catch (Exception e) {
			System.out.println("login error: " + e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				System.out.println("error71: " + e);
			}
		}
		return usertype;
	}

	// public List<String> loginAuthentication(String username, String password)
	// {
	//
	// ResultSet rs = null;
	// PreparedStatement ps = null;
	//
	// List<String> user = new ArrayList<String>();
	// String usertype = null;
	// boolean userExists = false;
	// String message = null;
	//
	// try {
	//
	// String checkUser =
	// "select usertype from user_details where username = ? and password = ?";
	//
	// ps = connection.prepareStatement(checkUser);
	// ps.setString(1, username);
	// ps.setString(2, password);
	// rs = ps.executeQuery();
	//
	// userExists = rs.next();
	//
	// if (userExists) {
	// usertype = rs.getString("usertype");
	// message = "Welcome ";
	// } else {
	// message = "The user is not registered!";
	// }
	// user.add(usertype);
	// user.add(message);
	// } catch (Exception e) {
	//
	// System.out.println("error71: " + e);
	// } finally {
	// try {
	// rs.close();
	// ps.close();
	// } catch (SQLException e) {
	// System.out.println("error71: " + e);
	// }
	// }
	// return user;
	// }

	public String getPassword(String username, String emailId) {

		ResultSet rs = null;
		PreparedStatement ps = null;

		String password = null;

		try {
			String passwordQuery = "select password from user_details where username = ? and cg_email_id = ? and is_active='Y'";
			ps = connection.prepareStatement(passwordQuery);
			ps.setString(1, username);
			ps.setString(2, emailId);
			rs = ps.executeQuery();

			while (rs.next()) {
				password = rs.getString("password");
			}

		} catch (Exception e) {

			System.out.println("error71: " + e);
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				System.out.println("error71: " + e);
			}
		}
		return password;
	}

	public int resetPassword(String oldPassword, String newPassword,
			String username) {

		PreparedStatement ps = null;

		int rowsaffected = 0;

		try {
			String changePasswordQuery = "update user_details set password=? where username=? and password=?";
			ps = connection.prepareStatement(changePasswordQuery);
			ps.setString(1, newPassword);
			ps.setString(2, username);
			ps.setString(3, oldPassword);
			rowsaffected = ps.executeUpdate();

		} catch (Exception e) {

			System.out.println("error71: " + e);
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				System.out.println("error71: " + e);
			}
		}
		return rowsaffected;
	}

	// for displaying the all users in ViewUsers.jsp
	public List<UserDTO> viewAllUsers(String isActive) {
		ResultSet resultSet1 = null;
		PreparedStatement preparedStatement1 = null;
		List<UserDTO> usersList = new ArrayList<UserDTO>();
		try {
			String selectUsersQuery = "select username,cisco_username,first_name,last_name,primary_contact_number,cg_manager_username,is_billable from user_details where is_active=?";
			preparedStatement1 = connection.prepareStatement(selectUsersQuery);
			preparedStatement1.setString(1, isActive);
			resultSet1 = preparedStatement1.executeQuery();
			while (resultSet1.next()) {
				UserDTO userDTO = new UserDTO();
				userDTO.setUsername(resultSet1.getString("username"));
				userDTO.setCiscoUsername(resultSet1.getString("cisco_username"));
				userDTO.setFirstName(resultSet1.getString("first_name"));
				userDTO.setLastName(resultSet1.getString("last_name"));
				userDTO.setPrimaryContactNumber(resultSet1
						.getString("primary_contact_number"));
				userDTO.setCapgeminiManagerName(resultSet1
						.getString("cg_manager_username"));
				userDTO.setIsBillable(resultSet1.getString("is_billable"));

				String selectProjectCodeQuery = "select project_code,is_presently_tagged from user_project_details where username = ? and is_presently_tagged = 'Y'";
				PreparedStatement preparedStatement2 = connection
						.prepareStatement(selectProjectCodeQuery);
				preparedStatement2.setString(1,
						resultSet1.getString("username"));
				ResultSet resultSet2 = preparedStatement2.executeQuery();
				if (resultSet2.next()) {
					userDTO.setProject_code(resultSet2.getInt("project_code"));
					userDTO.setIsPresentlyTagged(resultSet2
							.getString("is_presently_tagged"));
				}
				usersList.add(userDTO);
			}
		} catch (Exception e) {
			System.out.println("cannot display all users: " + e.getMessage());
		} finally {
			try {
				resultSet1.close();
				preparedStatement1.close();
			} catch (SQLException e) {
				System.out.println("error in closing conenctions: " + e);
			}
		}
		return usersList;
	}

	// for displaying list of polls
	public List<ResourceDTO> viewPolls(String pollStatus) {
		List<ResourceDTO> allPollsList = new ArrayList<ResourceDTO>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		java.sql.Date endDatePoll = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		System.out.println(dateFormat.format(cal.getTime()));
		try {
			String checkEndPollQuery = "select poll_end_date from poll_details where status=?";
			preparedStatement = connection.prepareStatement(checkEndPollQuery);
			preparedStatement.setString(1, pollStatus);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				endDatePoll = resultSet.getDate("poll_end_date");
				System.out.println(resultSet.getDate("poll_end_date"));
				if (endDatePoll.equals(dateFormat.format(cal.getTime()))) {
					System.out.println("yes date is matched");
				} else
					System.out.println("no");
			}

			String pollListQuery = "select poll_topic,poll_end_date,status,created_username,created_date from poll_details where status=?";
			preparedStatement = connection.prepareStatement(pollListQuery);
			preparedStatement.setString(1, pollStatus);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ResourceDTO resourceDTO = new ResourceDTO();
				resourceDTO.setPollTopic(resultSet.getString("poll_topic"));
				resourceDTO.setPollEndDate(resultSet.getDate("poll_end_date"));
				resourceDTO.setPollStatus(resultSet.getString("status"));
				resourceDTO.setCreateUserId(resultSet
						.getString("created_username"));
				resourceDTO.setCreateDate(resultSet.getDate("created_date"));
				allPollsList.add(resourceDTO);
			}
		} catch (Exception e) {
			System.out.println("error in displaying list of polls: " + e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				System.out.println("error in closing the connection");
			}

		}
		return allPollsList;
	}

	// for searching any poll
	public List<ResourceDTO> pollSearch(String pollTopic) {
		List<ResourceDTO> pollsList = new ArrayList<ResourceDTO>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String pollListQuery = "select poll_topic,poll_description,poll_end_date,status,created_username,created_date,last_modified_username,last_modified_date from poll_details where poll_topic like ? and status='OnGoing'";
			preparedStatement = connection.prepareStatement(pollListQuery);
			preparedStatement.setString(1, pollTopic + "%");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ResourceDTO resourceDTO = new ResourceDTO();
				resourceDTO.setPollTopic(resultSet.getString("poll_topic"));
				resourceDTO.setPollDescription(resultSet
						.getString("poll_description"));
				resourceDTO.setPollEndDate(resultSet.getDate("poll_end_date"));
				resourceDTO.setPollStatus(resultSet.getString("status"));
				resourceDTO.setCreateUserId(resultSet
						.getString("created_username"));
				resourceDTO.setCreateDate(resultSet.getDate("created_date"));
				resourceDTO.setModifyUserId(resultSet
						.getString("last_modified_username"));
				resourceDTO.setModifydate(resultSet
						.getDate("last_modified_date"));
				pollsList.add(resourceDTO);
			}
		}

		catch (Exception e) {
			System.out.println("error in searching a poll: " + e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				System.out.println("error in closing the connection");
			}

		}
		return pollsList;
	}

	public List<ResourceDTO> pollDetails(String pollTopic) {
		List<ResourceDTO> pollsList = new ArrayList<ResourceDTO>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int yesRowCount = 0;
		int noRowCount = 0;

		try {
			String yesRowCountQuery = "select poll_id from user_poll_details where poll_reply_text =? and poll_id in (select poll_id from poll_details where poll_topic=?)";
			preparedStatement = connection.prepareStatement(yesRowCountQuery);
			preparedStatement.setString(1, "yes");
			preparedStatement.setString(2, pollTopic);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				yesRowCount++;
				System.out.println("yes row count is: " + yesRowCount);
			}

			String noRowCountQuery = "select poll_id from user_poll_details where poll_reply_text =? and poll_id in (select poll_id from poll_details where poll_topic=?)";
			preparedStatement = connection.prepareStatement(noRowCountQuery);
			preparedStatement.setString(1, "no");
			preparedStatement.setString(2, pollTopic);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				noRowCount++;
				System.out.println("no row count is: " + noRowCount);
			}

			String pollListQuery = "select poll_topic,poll_description,poll_end_date,poll_type,status,created_username,created_date,last_modified_username,last_modified_date from poll_details where poll_topic = ?";
			preparedStatement = connection.prepareStatement(pollListQuery);
			preparedStatement.setString(1, pollTopic);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ResourceDTO resourceDTO = new ResourceDTO();
				resourceDTO.setPollTopic(resultSet.getString("poll_topic"));
				resourceDTO.setPollDescription(resultSet
						.getString("poll_description"));
				resourceDTO.setPollEndDate(resultSet.getDate("poll_end_date"));
				resourceDTO.setPollStatus(resultSet.getString("status"));
				resourceDTO.setPollType(resultSet.getString("poll_type"));
				resourceDTO.setCreateUserId(resultSet
						.getString("created_username"));
				resourceDTO.setCreateDate(resultSet.getDate("created_date"));
				resourceDTO.setModifyUserId(resultSet
						.getString("last_modified_username"));
				resourceDTO.setModifydate(resultSet
						.getDate("last_modified_date"));
				resourceDTO.setYesRowCount(yesRowCount);
				resourceDTO.setNoRowCount(noRowCount);
				pollsList.add(resourceDTO);
			}
		}

		catch (Exception e) {
			System.out.println("error in details of a poll: " + e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				System.out.println("error in closing the connection");
			}
		}
		return pollsList;
	}

	// for displaying poll replies
	public List<ResourceDTO> displayPollReply(String pollTopic) {
		List<ResourceDTO> pollReplyList = new ArrayList<ResourceDTO>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String pollReplyQuery = "select username,poll_reply_text,poll_reply_date from user_poll_details where poll_id in (select poll_id from poll_details where poll_topic = ?)";
			preparedStatement = connection.prepareStatement(pollReplyQuery);
			preparedStatement.setString(1, pollTopic);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ResourceDTO resourceDTO = new ResourceDTO();
				resourceDTO.setUsername(resultSet.getString("username"));
				resourceDTO.setPollReplyText(resultSet
						.getString("poll_reply_text"));
				resourceDTO.setPollReplyDate(resultSet
						.getDate("poll_reply_date"));
				pollReplyList.add(resourceDTO);
			}
		}

		catch (Exception e) {
			System.out.println("error in dispalying replies of a poll: " + e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				System.out.println("error in closing the connection");
			}

		}
		return pollReplyList;
	}

	// for adding the poll reply
	public boolean addPollReply(ResourceDTO resourceDTO) {
		boolean flagReplyPoll = false;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int pollID = 0;
		String getPollIdQuery = "select poll_id from poll_details where poll_topic = ?";
		String createPollQuery = "insert into user_poll_details(poll_id,username,poll_reply_text,poll_reply_date,created_username,created_date,last_modified_username,last_modified_date) values (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(getPollIdQuery);
			preparedStatement.setString(1, resourceDTO.getPollTopic());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				pollID = resultSet.getInt("poll_id");
			}

			preparedStatement = connection.prepareStatement(createPollQuery);
			preparedStatement.setInt(1, pollID);
			preparedStatement.setString(2, resourceDTO.getUsername());
			preparedStatement.setString(3, resourceDTO.getPollReplyText());
			preparedStatement.setDate(4, resourceDTO.getPollReplyDate());
			preparedStatement.setString(5, resourceDTO.getCreateUserId());
			preparedStatement.setDate(6, resourceDTO.getCreateDate());
			preparedStatement.setString(7, resourceDTO.getModifyUserId());
			preparedStatement.setDate(8, resourceDTO.getModifydate());
			preparedStatement.executeUpdate();
			flagReplyPoll = true;
			connection.commit();
		} catch (Exception e) {
			System.out.println("Problem in adding a reply to the poll: " + e);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				System.out.println("Problem in closing the connection");
			}
		}
		return flagReplyPoll;
	}

	// for displaying manager names in AddUser.jsp according to access level
	public List<String> getManagerList(String designationName) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<String> list = new ArrayList<String>();
		try {
			if (designationName.equals("P1") || designationName.equals("P2")
					|| designationName.equals("P3")
					|| designationName.equals("P4")
					|| designationName.equals("P5")) {
				String selectManagerQuery = "select username from user_details where access_level >= (select access_level from Designation_Details where Designation_Name = 'M1')";
				preparedStatement = connection
						.prepareStatement(selectManagerQuery);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					String managerName = resultSet.getString("username");
					list.add(managerName);
				}
			} else {
				String selectManagerQuery = "select username from user_details where access_level > (select access_level from Designation_Details where Designation_Name = ?)";
				preparedStatement = connection
						.prepareStatement(selectManagerQuery);
				preparedStatement.setString(1, designationName);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					String managerName = resultSet.getString("username");
					list.add(managerName);
				}

			}
		} catch (Exception e) {
			System.out.println("error8: " + e.getMessage());
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				System.out.println("error8345: " + e.getMessage());
			}

		}
		return list;
	}

	// for checking that a manager cannot be added to a project in which a
	// manager is already tagged
	public boolean ifManagerTagged(int projectCode) {
		boolean flagManagerExists = false;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String checkManagerTagging = "select * from user_project_details where is_presently_tagged = 'Y' and project_code = ? and role_id in (select role_id from project_role_details where role_name = 'Manager')";
			preparedStatement = connection
					.prepareStatement(checkManagerTagging);
			preparedStatement.setInt(1, projectCode);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				flagManagerExists = true;
				System.out.println("Manager Tagged");
			}
		} catch (Exception e) {
			System.out.println("error in checking tagging of manager: "
					+ e.getMessage());
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				System.out.println("error in closing the connection");
			}

		}
		return flagManagerExists;
	}

	// for checking if the employee has designation manager
	public boolean checkManager(String username) {
		boolean flagManagerExists = false;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			String checkManagerQuery = "select username,access_level from user_details where designation_id in (select designation_id from designation_details where access_level between 6 and 10)";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(checkManagerQuery);
			while (resultSet.next()) {
				if ((resultSet.getString("username").equals(username))) {
					System.out.println("Username is: " + username);
					System.out.println(resultSet.getString("access_level"));
					System.out.println("User is a manager");
					flagManagerExists = true;
				}
			}
		} catch (Exception e) {
			System.out
					.println("error in checking user designation as manager: "
							+ e.getMessage());
		} finally {
			try {
				resultSet.close();
				statement.close();
			} catch (SQLException e) {
				System.out.println("error in closing the connection");
			}

		}
		return flagManagerExists;
	}

	// for creating poll
	public boolean createPoll(ResourceDTO resourceDTO) {
		boolean flagCreatePoll = false;
		PreparedStatement preparedStatement = null;
		String createPollQuery = "insert into poll_details(poll_topic,poll_description,poll_end_date,status,created_username,created_date,last_modified_username,last_modified_date,poll_type) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			preparedStatement = connection.prepareStatement(createPollQuery);
			preparedStatement.setString(1, resourceDTO.getPollTopic());
			preparedStatement.setString(2, resourceDTO.getPollDescription());
			preparedStatement.setDate(3, resourceDTO.getPollEndDate());
			preparedStatement.setString(4, resourceDTO.getPollStatus());
			preparedStatement.setString(5, resourceDTO.getCreateUserId());
			preparedStatement.setDate(6, resourceDTO.getCreateDate());
			preparedStatement.setString(7, resourceDTO.getModifyUserId());
			preparedStatement.setDate(8, resourceDTO.getModifydate());
			preparedStatement.setString(9, resourceDTO.getPollType());
			preparedStatement.executeUpdate();
			flagCreatePoll = true;
		} catch (Exception e) {
			System.out.println("Problem in creating the poll: " + e);
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				System.out.println("Problem in closing the connection");
			}
		}
		return flagCreatePoll;
	}

	// Vanitaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa

	// for displaying the all users in ViewUsers.jsp
	public List<UserDTO> viewAllUsers(String isActive, int offset,
			int noOfRecords) {
		ResultSet resultSet1 = null;
		PreparedStatement preparedStatement1 = null;
		List<UserDTO> usersList = new ArrayList<UserDTO>();
		try {
			String selectUsersQuery = "select SQL_CALC_FOUND_ROWS username,cisco_username,first_name,last_name,primary_contact_number,cg_manager_username,is_billable from user_details where is_active=? limit ?,?";
			preparedStatement1 = connection.prepareStatement(selectUsersQuery);
			preparedStatement1.setString(1, isActive);
			preparedStatement1.setInt(2, offset);
			preparedStatement1.setInt(3, noOfRecords);
			resultSet1 = preparedStatement1.executeQuery();
			while (resultSet1.next()) {
				UserDTO userDTO = new UserDTO();
				userDTO.setUsername(resultSet1.getString("username"));
				userDTO.setCiscoUsername(resultSet1.getString("cisco_username"));
				userDTO.setFirstName(resultSet1.getString("first_name"));
				userDTO.setLastName(resultSet1.getString("last_name"));
				userDTO.setPrimaryContactNumber(resultSet1
						.getString("primary_contact_number"));
				userDTO.setCapgeminiManagerName(resultSet1
						.getString("cg_manager_username"));
				userDTO.setIsBillable(resultSet1.getString("is_billable"));

				String selectProjectCodeQuery = "select project_code,is_presently_tagged from user_project_details where username = ? and is_presently_tagged = 'Y'";
				PreparedStatement preparedStatement2 = connection
						.prepareStatement(selectProjectCodeQuery);
				preparedStatement2.setString(1,
						resultSet1.getString("username"));
				ResultSet resultSet2 = preparedStatement2.executeQuery();
				if (resultSet2.next()) {
					userDTO.setProject_code(resultSet2.getInt("project_code"));
					userDTO.setIsPresentlyTagged(resultSet2
							.getString("is_presently_tagged"));
				}
				usersList.add(userDTO);
			}
		} catch (Exception e) {
			System.out.println("Cannot display all users: " + e.getMessage());
		} finally {
			try {
				resultSet1.close();
				preparedStatement1.close();
			} catch (SQLException e) {
				System.out.println("Error in closing connections: " + e);
			}
		}
		return usersList;
	}

	public boolean grantAdminRights(String username) {
		boolean flagGrantAdminRights = false;
		PreparedStatement preparedStatement = null;
		String updateUserType = "update user_details set usertype='Admin' where username=?";
		try {
			preparedStatement = connection.prepareStatement(updateUserType);
			preparedStatement.setString(1, username);
			preparedStatement.executeUpdate();
			flagGrantAdminRights = true;
		} catch (Exception e) {
			System.out
					.println("Problem in granting the user the admin rights: "
							+ e);
			flagGrantAdminRights = false;
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				System.out.println("Problem in closing the connection");
			}
		}
		return flagGrantAdminRights;
	}

	public boolean revokeAdminRights(String username) {
		boolean flagGrantAdminRights = false;
		PreparedStatement preparedStatement = null;
		String updateUserType = "update user_details set usertype='Non-Admin' where username=?";
		try {
			preparedStatement = connection.prepareStatement(updateUserType);
			preparedStatement.setString(1, username);
			preparedStatement.executeUpdate();
			flagGrantAdminRights = true;
		} catch (Exception e) {
			System.out
					.println("Problem in revoking the user from admin rights: "
							+ e);
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				System.out.println("Problem in closing the connection");
			}
		}
		return flagGrantAdminRights;
	}

	public List<UserDTO> getAdminUsers() {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		List<UserDTO> adminUsersList = new ArrayList<UserDTO>();
		try {
			String selectAdminUsers = "select username from user_details where is_active='Y' and usertype='Admin'";
			preparedStatement = connection.prepareStatement(selectAdminUsers);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				UserDTO userDTO = new UserDTO();
				userDTO.setUsername(resultSet.getString("username"));
				adminUsersList.add(userDTO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adminUsersList;
	}

	public List<UserDTO> getnonAdminUsers() {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		List<UserDTO> nonAdminUsersList = new ArrayList<UserDTO>();
		try {
			String selectnonAdminUsers = "select username from user_details where is_active='Y' and usertype='Non-Admin'";
			preparedStatement = connection
					.prepareStatement(selectnonAdminUsers);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				UserDTO userDTO = new UserDTO();
				userDTO.setUsername(resultSet.getString("username"));
				nonAdminUsersList.add(userDTO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nonAdminUsersList;
	}

	// Manager can update the profile of the users under them
	public List<UserDTO> getUsersUnderManager(String managerName) {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		List<UserDTO> usersList = new ArrayList<UserDTO>();
		try {
			String selectUsers = "select username from user_details where is_active='Y' and cg_manager_username=?";
			preparedStatement = connection.prepareStatement(selectUsers);
			preparedStatement.setString(1, managerName);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				UserDTO userDTO = new UserDTO();
				userDTO.setUsername(resultSet.getString("username"));
				usersList.add(userDTO);
				System.out.println("users under: "
						+ resultSet.getString("username"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersList;
	}

	// To fetch number of records for displaying users list using pagination
	public int getNoOfRecords() {

		ResultSet resultSet1 = null;
		PreparedStatement preparedStatement1 = null;
		int noOfRecords = 0;

		try {
			String selectCountQuery = "select count(*) from user_details where is_active=?";
			preparedStatement1 = connection.prepareStatement(selectCountQuery);
			preparedStatement1.setString(1, "Y");
			resultSet1 = preparedStatement1.executeQuery();
			if (resultSet1.next())
				noOfRecords = resultSet1.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noOfRecords;
	}

	// To fetch number of records for displaying users list using pagination
	// after search -- Search By Username
	public int getNoOfRecordsSearchByUsername(String username) {

		ResultSet resultSet1 = null;
		PreparedStatement preparedStatement1 = null;
		int noOfRecords = 0;

		try {
			String selectCountQuery = "select count(*) from user_details where username like ? and is_active='Y'";
			preparedStatement1 = connection.prepareStatement(selectCountQuery);
			preparedStatement1.setString(1, username);
			resultSet1 = preparedStatement1.executeQuery();
			if (resultSet1.next())
				noOfRecords = resultSet1.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noOfRecords;
	}

	// To fetch number of records for displaying users list using pagination
	// after search -- Search By Manager Name
	public int getNoOfRecordsSearchByManagerName(String username) {

		ResultSet resultSet1 = null;
		PreparedStatement preparedStatement1 = null;
		int noOfRecords = 0;
		int accessLevel = 0;

		try {

			String managerLevelQuery = "select access_level from designation_details where designation_name = 'P5'";
			preparedStatement1 = connection.prepareStatement(managerLevelQuery);
			resultSet1 = preparedStatement1.executeQuery();
			while (resultSet1.next()) {
				accessLevel = resultSet1.getInt("access_level");
			}

			String selectCountQuery = "select count(*) from user_details where username in (select username from user_details where username like ? and access_level > ? and a.is_active='Y' and b.is_presently_tagged='Y') ";
			preparedStatement1 = connection.prepareStatement(selectCountQuery);
			preparedStatement1.setString(1, username);
			preparedStatement1.setInt(2, accessLevel);
			resultSet1 = preparedStatement1.executeQuery();
			if (resultSet1.next())
				noOfRecords = resultSet1.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noOfRecords;
	}

	// To fetch number of records for displaying users list using pagination
	// after search -- Search By Project Name
	public int getNoOfRecordsSearchByProjectName(String projectName) {

		ResultSet resultSet1 = null;
		PreparedStatement preparedStatement1 = null;
		int noOfRecords = 0;

		try {
			String selectCountQuery = "select count(*) from user_details where project_manager in (select project_manager from project_details where project_name like ? and b.status='On Going') ";
			preparedStatement1 = connection.prepareStatement(selectCountQuery);
			preparedStatement1.setString(1, projectName);
			resultSet1 = preparedStatement1.executeQuery();
			if (resultSet1.next())
				noOfRecords = resultSet1.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noOfRecords;
	}

	// Updating user details by admin
	public boolean updateUserDetailsAdmin(UserDTO userDTO) {
		PreparedStatement preparedStatement = null;
		boolean flagUpdateUser = false;
		try {

			connection.setAutoCommit(false);
			String userUpdateQuery = "update user_details set first_name=?,last_name=?,designation_id=?,cg_email_id=?,primary_contact_number=?,emergency_contact_number=?,last_modified_username=?,last_modified_date=? where username=?";
			preparedStatement = connection.prepareStatement(userUpdateQuery);

			preparedStatement.setString(1, userDTO.getFirstName());
			preparedStatement.setString(2, userDTO.getLastName());
			System.out.println(userDTO.getDesignation());
			System.out.println("Designation"
					+ FetchDesignationUtil.getDesignationID(userDTO
							.getDesignation()));
			System.out.println("inside");
			preparedStatement.setInt(3, FetchDesignationUtil
					.getDesignationID(userDTO.getDesignation()));
			preparedStatement.setString(4, userDTO.getCapgeminiEmailId());
			preparedStatement.setString(5, userDTO.getPrimaryContactNumber());
			preparedStatement.setString(6, userDTO.getEmergencyContactNumber());
			preparedStatement.setString(7, userDTO.getModifyUserId());
			preparedStatement.setDate(8, userDTO.getModifydate());
			preparedStatement.setString(9, userDTO.getUsername());
			preparedStatement.executeUpdate();

			String userSkillUpdateQuery = "update user_skillset_details set primary_skill_id=?,secondary_skill_01_id=?,secondary_skill_02_id=? where username=?";
			preparedStatement = connection
					.prepareStatement(userSkillUpdateQuery);
			preparedStatement.setInt(1, getSkillID(userDTO.getPrimarySkill()));
			preparedStatement.setInt(2,
					getSkillID(userDTO.getSecondarySkill_01()));
			preparedStatement.setInt(3,
					getSkillID(userDTO.getSecondarySkill_02()));
			preparedStatement.setString(4, userDTO.getUsername());
			preparedStatement.executeUpdate();

			String userCiscoIDQuery = "update user_project_details set cisco_email_id=? where username=?";
			preparedStatement = connection.prepareStatement(userCiscoIDQuery);
			preparedStatement.setString(1, userDTO.getCiscoEmailId());
			preparedStatement.setString(2, userDTO.getUsername());
			preparedStatement.executeUpdate();
			connection.commit();
			flagUpdateUser = true;

		} catch (Exception e) {
			System.out.println("Error in editing user details by admin"
					+ e.toString());
			flagUpdateUser = false;
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				preparedStatement.close();

			} catch (SQLException e) {
				System.out.println("Error in closing the connection" + e);
			}
		}
		return flagUpdateUser;

	}

	// update user details by user
	public boolean updateUserDetails(UserDTO userDTO) {
		PreparedStatement preparedStatement = null;
		boolean flagUpdateUser = false;
		try {
			String userUpdateQuery = "update user_details set first_name=?,last_name=?,primary_contact_number=?,emergency_contact_number=?,last_modified_username=?,last_modified_date=? where username=?";
			preparedStatement = connection.prepareStatement(userUpdateQuery);

			preparedStatement.setString(1, userDTO.getFirstName());
			preparedStatement.setString(2, userDTO.getLastName());
			preparedStatement.setString(3, userDTO.getPrimaryContactNumber());
			preparedStatement.setString(4, userDTO.getEmergencyContactNumber());
			preparedStatement.setString(5, userDTO.getModifyUserId());
			preparedStatement.setDate(6, userDTO.getModifydate());
			preparedStatement.setString(7, userDTO.getUsername());
			preparedStatement.executeUpdate();
			flagUpdateUser = true;

		} catch (Exception e) {
			System.out.println("Error in editing user details by user"
					+ e.toString());
			flagUpdateUser = false;
		} finally {
			try {
				preparedStatement.close();

			} catch (SQLException e) {
				System.out.println("Error in closing the connection" + e);
			}
		}
		return flagUpdateUser;

	}

	// To fetch number of skills in the skillset_details table
	public int getNoOfRecordsSkills() {

		ResultSet resultSet1 = null;
		PreparedStatement preparedStatement1 = null;
		int noOfRecords = 0;

		try {
			String selectCountQuery = "select count(*) from skillset_details";
			preparedStatement1 = connection.prepareStatement(selectCountQuery);
			resultSet1 = preparedStatement1.executeQuery();
			if (resultSet1.next())
				noOfRecords = resultSet1.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noOfRecords;
	}

	public List<ProjectDTO> viewLocation() {

		ResultSet resultSet = null;
		Statement statement = null;
		

		List<ProjectDTO> locationList = new ArrayList<ProjectDTO>();
		try {
			String selectLocation = "select location_name,address from location_details";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(selectLocation);

			while (resultSet.next()) {
				ProjectDTO projectDTO=new ProjectDTO();
				projectDTO.setLocationName(resultSet.getString("location_name"));
				projectDTO.setAddress(resultSet.getString("address"));
				locationList.add(projectDTO);
			}
		} catch (Exception e) {
			System.out.println("error61: " + e);
		} finally {
			try {
				resultSet.close();
				statement.close();
			} catch (SQLException e) {
				System.out.println("error in closing conenctions: " + e);
			}
		}
		return locationList;
	}

	public List<String> viewDesignation() {

		ResultSet resultSet = null;
		Statement statement = null;
		String designationName = null;

		List<String> designationList = new ArrayList<String>();
		try {
			String selectDesignation = "select designation_name from location_details";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(selectDesignation);

			while (resultSet.next()) {
				designationName = resultSet.getString("designation_name");
				designationList.add(designationName);
			}
		} catch (Exception e) {
			System.out.println("error61: " + e);
		} finally {
			try {
				resultSet.close();
				statement.close();
			} catch (SQLException e) {
				System.out.println("error in closing conenctions: " + e);
			}
		}
		return designationList;
	}

	public List<UserDTO> viewCiscoManagers() {
		ResultSet resultSet = null;
		Statement statement = null;

		List<UserDTO> ciscoManagerList = new ArrayList<UserDTO>();
		try {
			String selectCiscoManager = "select username,first_name,last_name from cisco_manager_details";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(selectCiscoManager);

			while (resultSet.next()) {

				UserDTO userDTO = new UserDTO();
				userDTO.setUsername(resultSet.getString("username"));
				userDTO.setFirstName(resultSet.getString("first_name"));
				userDTO.setLastName(resultSet.getString("last_name"));

				ciscoManagerList.add(userDTO);
			}
		} catch (Exception e) {
			System.out.println("error61: " + e);
		} finally {
			try {
				resultSet.close();
				statement.close();
			} catch (SQLException e) {
				System.out.println("Error in closing connections: " + e);
			}
		}
		return ciscoManagerList;

	}

	// for displaying list of polls
	public List<ResourceDTO> viewPolls(String pollStatus, int offset,
			int noOfRecords) {
		List<ResourceDTO> allPollsList = new ArrayList<ResourceDTO>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		java.sql.Date endDatePoll = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		System.out.println(dateFormat.format(cal.getTime()));
		try {
			String checkEndPollQuery = "select poll_end_date from poll_details where status=?";
			preparedStatement = connection.prepareStatement(checkEndPollQuery);
			preparedStatement.setString(1, pollStatus);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				endDatePoll = resultSet.getDate("poll_end_date");
				System.out.println(resultSet.getDate("poll_end_date"));
				if (endDatePoll.equals(dateFormat.format(cal.getTime()))) {
					System.out.println("yes date is matched");
				} else
					System.out.println("no");
			}

			String pollListQuery = "select poll_id,poll_topic,poll_end_date,status,created_username,created_date from poll_details where status=? limit ?,?";
			preparedStatement = connection.prepareStatement(pollListQuery);
			preparedStatement.setString(1, pollStatus);
			preparedStatement.setInt(2, offset);
			preparedStatement.setInt(3, noOfRecords);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ResourceDTO resourceDTO = new ResourceDTO();
				resourceDTO.setPollID(resultSet.getInt("poll_id"));
				resourceDTO.setPollTopic(resultSet.getString("poll_topic"));
				resourceDTO.setPollEndDate(resultSet.getDate("poll_end_date"));
				resourceDTO.setPollStatus(resultSet.getString("status"));
				resourceDTO.setCreateUserId(resultSet
						.getString("created_username"));
				resourceDTO.setCreateDate(resultSet.getDate("created_date"));
				allPollsList.add(resourceDTO);
			}
		} catch (Exception e) {
			System.out.println("error in displaying list of polls: " + e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				System.out.println("error in closing the connection");
			}

		}
		return allPollsList;
	}

	// To fetch number of polls for displaying project list using pagination
	public int getNoOfPollRecords(String status) {

		ResultSet resultSet1 = null;
		PreparedStatement preparedStatement1 = null;
		int noOfRecords = 0;

		try {
			String selectCountQuery = "select count(*) from poll_details where status=?";
			preparedStatement1 = connection.prepareStatement(selectCountQuery);
			preparedStatement1.setString(1, status);
			resultSet1 = preparedStatement1.executeQuery();
			if (resultSet1.next())
				noOfRecords = resultSet1.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noOfRecords;
	}

	public int getNoOfProjectRecords(String status) {
		ResultSet resultSet1 = null;
		PreparedStatement preparedStatement1 = null;
		int noOfRecords = 0;

		try {
			String selectCountQuery = "select count(*) from project_details where status=?";
			preparedStatement1 = connection.prepareStatement(selectCountQuery);
			preparedStatement1.setString(1, status);
			resultSet1 = preparedStatement1.executeQuery();
			if (resultSet1.next())
				noOfRecords = resultSet1.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noOfRecords;
	}

	public int addSkill(String skillName, String username) {
		PreparedStatement preparedStatementInsert = null;
		int rowsaffected = 0;

		String addSkill = "insert into skillset_details(skill_name,created_username,created_date,last_modified_username,last_modified_date) values (?,?,?,?,?)";
		try {

			preparedStatementInsert = connection.prepareStatement(addSkill);
			preparedStatementInsert.setString(1, skillName);
			preparedStatementInsert.setString(2, username);
			java.util.Date createdDate = new java.util.Date();
			java.sql.Date currentDate1 = new Date(createdDate.getTime()); 
			preparedStatementInsert.setDate(3, currentDate1);
			preparedStatementInsert.setString(4, username);
			java.util.Date modifiedDate = new java.util.Date();
			java.sql.Date currentDate2 = new Date(modifiedDate.getTime()); 
			preparedStatementInsert.setDate(5, currentDate2);
			rowsaffected = preparedStatementInsert.executeUpdate();

		} catch (Exception e) {
			System.out.println("Problem in adding skill: " + e.getMessage());

		} finally {
			try {
				preparedStatementInsert.close();

			} catch (SQLException e) {
				System.out.println("Problem in closing the connection");
			}
		}
		return rowsaffected;
	}

	public int addLocation(String locationName, String address, String username) {
		PreparedStatement preparedStatementInsert = null;
		int rowsaffected = 0;

		String addLocation = "insert into location_details(location_name,address,created_username,created_date,last_modified_username,last_modified_date) values (?,?,?,?,?,?)";
		try {

			preparedStatementInsert = connection.prepareStatement(addLocation);
			preparedStatementInsert.setString(1, locationName);
			preparedStatementInsert.setString(2, address);
			preparedStatementInsert.setString(3, username);
			java.util.Date createdDate = new java.util.Date();
			java.sql.Date currentDate1 = new Date(createdDate.getTime()); 
			preparedStatementInsert.setDate(4, currentDate1);
			preparedStatementInsert.setString(5, username);
			java.util.Date modifiedDate = new java.util.Date();
			java.sql.Date currentDate2 = new Date(modifiedDate.getTime()); 
			preparedStatementInsert.setDate(6, currentDate2);
			rowsaffected = preparedStatementInsert.executeUpdate();

		} catch (Exception e) {
			System.out.println("Problem in adding Location: " + e.getMessage());

		} finally {
			try {
				preparedStatementInsert.close();

			} catch (SQLException e) {
				System.out.println("Problem in closing the connection");
			}
		}
		return rowsaffected;
	}

	public int addCiscoManager(String managerUsername, String firstName,
			String lastName, String username) {
		PreparedStatement preparedStatementInsert = null;
		int rowsaffected = 0;

		String addLocation = "insert into cisco_manager_details(username,first_name,last_name,created_username,created_date,last_modified_username,last_modified_date) values (?,?,?,?,?,?,?)";
		try {

			preparedStatementInsert = connection.prepareStatement(addLocation);
			preparedStatementInsert.setString(1, managerUsername);
			preparedStatementInsert.setString(2, firstName);
			preparedStatementInsert.setString(3, lastName);
			preparedStatementInsert.setString(4, username);
			java.util.Date createdDate = new java.util.Date();
			java.sql.Date currentDate1 = new Date(createdDate.getTime()); 
			preparedStatementInsert.setDate(5, currentDate1);
			preparedStatementInsert.setString(6, username);
			java.util.Date modifiedDate = new java.util.Date();
			java.sql.Date currentDate2 = new Date(modifiedDate.getTime()); 
			preparedStatementInsert.setDate(7, currentDate2);
			rowsaffected = preparedStatementInsert.executeUpdate();

		} catch (Exception e) {
			System.out.println("Problem in adding Cisco Manager: " + e.getMessage());

		} finally {
			try {
				preparedStatementInsert.close();

			} catch (SQLException e) {
				System.out.println("Problem in closing the connection");
			}
		}
		return rowsaffected;
	}

	public int activateUser(String username) {
		
		int rowsaffected=0;
		PreparedStatement preparedStatement = null;
		try {
			String activateUserName = "update user_details set is_active=? where username=?";
			preparedStatement = connection.prepareStatement(activateUserName);
			preparedStatement.setString(1, "Y");
			preparedStatement.setString(2, username);
			rowsaffected=preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("error6: " + e);
		} finally {
			
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return rowsaffected;
	}

	public int deactivatePoll(int pollId) {
		int rowsaffected=0;
		PreparedStatement preparedStatement = null;
		try {
			String activateUserName = "update poll_details set status=? where poll_id=?";
			preparedStatement = connection.prepareStatement(activateUserName);
			preparedStatement.setString(1, "Completed");
			preparedStatement.setInt(2, pollId);
			rowsaffected=preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("error6: " + e);
		} finally {
			
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return rowsaffected;
	}

}
