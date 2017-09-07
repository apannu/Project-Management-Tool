package com.capgemini.cisco.portal.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.capgemini.cisco.portal.dto.ProjectDTO;
import com.capgemini.cisco.portal.dto.ResourceDTO;
import com.capgemini.cisco.portal.dto.UserDTO;
import com.capgemini.cisco.portal.utility.DBConnection;
import com.capgemini.cisco.portal.utility.FetchLocationIDUtil;

public class ProjectDAO {
	private Connection connection = null;

	public ProjectDAO() {
		connection = DBConnection.getDBConnection();
	}

	// for adding the project in AddProject.jsp
	public boolean addProject(ProjectDTO projectDTO) {
		boolean flagAddProject = false;
		PreparedStatement preparedStatement = null;
		String addProjectQuery = "insert into project_details values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(addProjectQuery);
			preparedStatement.setInt(1, projectDTO.getProject_code());
			preparedStatement.setString(2, projectDTO.getProjectName());
			preparedStatement.setString(3, projectDTO.getProjectObjective());
			preparedStatement.setString(4, projectDTO.getProjectManager());
			preparedStatement.setInt(5, projectDTO.getTimeOfCompletion());
			preparedStatement.setInt(6, projectDTO.getNoOfModules());
			preparedStatement.setInt(7, projectDTO.getLocationID());
			preparedStatement.setDate(8, projectDTO.getStartDate());
			preparedStatement.setDate(9, projectDTO.getEndDate());
			preparedStatement
					.setInt(10, projectDTO.getNoOfTechnicalResources());
			preparedStatement.setInt(11,
					projectDTO.getNoOfNonTechnicalResources());
			preparedStatement.setString(12, projectDTO.getStatus());
			preparedStatement.setString(13, projectDTO.getCreateUserId());
			preparedStatement.setDate(14, projectDTO.getCreateDate());
			preparedStatement.setString(15, projectDTO.getModifyUserId());
			preparedStatement.setDate(16, projectDTO.getModifydate());
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

	// Delete Documents
	public boolean deleteDocuments(String fileName) {
		boolean flagDeleteDocument = false;
		PreparedStatement ps = null;
		try {
			String deleteProject = "delete from document_details where filename=?";
			ps = connection.prepareStatement(deleteProject);
			ps.setString(1, fileName);
			ps.executeUpdate();
			flagDeleteDocument = true;
		} catch (SQLException e) {
			e.printStackTrace();
			flagDeleteDocument = false;
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				System.out.println("Problem in closing the connection");
			}
		}
		return flagDeleteDocument;
	}

	public int updateProjectDetails(ProjectDTO projectDTO) {
		PreparedStatement preparedStatement = null;
		int rowsaffected = 0;
		try {
			String projectUpdateQuery = "update project_details set project_name=?,objectives=?,project_manager=?,time_of_completion=?,number_of_modules=?,location_id=?,number_of_technical_resources=?,number_of_non_technical_resources=?,status=?,last_modified_date=?,last_modified_username=? where project_code=?";
			preparedStatement = connection.prepareStatement(projectUpdateQuery);
			preparedStatement.setString(1, projectDTO.getProjectName());
			preparedStatement.setString(2, projectDTO.getProjectObjective());
			preparedStatement.setString(3, projectDTO.getProjectManager());
			preparedStatement.setInt(4, projectDTO.getTimeOfCompletion());
			preparedStatement.setInt(5, projectDTO.getNoOfModules());
			System.out.println("inside");
			preparedStatement.setInt(6, FetchLocationIDUtil
					.getLocationID(projectDTO.getLocationName()));
			System.out.println("Location"
					+ FetchLocationIDUtil.getLocationID(projectDTO
							.getLocationName()));
			preparedStatement.setInt(7, projectDTO.getNoOfTechnicalResources());
			preparedStatement.setInt(8,
					projectDTO.getNoOfNonTechnicalResources());
			preparedStatement.setString(9, projectDTO.getStatus());
			preparedStatement.setDate(10, projectDTO.getModifydate());
			preparedStatement.setString(11, projectDTO.getModifyUserId());
			preparedStatement.setInt(12, projectDTO.getProject_code());
			rowsaffected = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out
					.println("Error in updating project details by admin" + e);
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rowsaffected;
	}

	// for downloading the file
	public boolean downloadFile(String fileName, HttpServletResponse response) {
		boolean flagDownload = false;
		try {
			String fileDownloadQuery = "select content from document_details where filename = ?";
			PreparedStatement preparedStatement = connection
					.prepareStatement(fileDownloadQuery);
			preparedStatement.setString(1, fileName);
			ResultSet resultset = preparedStatement.executeQuery();
			if (resultset.next()) {
				response.setContentType("application/octet");
				response.setHeader("Content-Disposition",
						"attachment;filename=" + fileName);
				Blob blobColumn = resultset.getBlob("content");
				BufferedInputStream bufferedInputStream = new BufferedInputStream(
						blobColumn.getBinaryStream());
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				int fileData = -1;
				while ((fileData = bufferedInputStream.read()) != -1) {
					byteArrayOutputStream.write(fileData);
				}
				bufferedInputStream.close();
				byteArrayOutputStream.close();
				response.setContentLength(byteArrayOutputStream.size());
				response.getOutputStream().write(
						byteArrayOutputStream.toByteArray());
				response.getOutputStream().flush();
				response.setHeader("Cache-Control", "no-cache");
				resultset.close();
				preparedStatement.close();
				connection.close();
				flagDownload = true;
			}
		} catch (Exception e) {
			System.out.println("Error in downloading: " + e);
		}
		return flagDownload;
	}
	
	// for downloading image
	public boolean downloadImage(String imageName, HttpServletResponse response) {
		boolean flagDownlaod = false;
		try {
			
			String fileDownloadQuery = "select image_content from image_details where image_name = ?";
			PreparedStatement preparedStatement = connection
					.prepareStatement(fileDownloadQuery);
			preparedStatement.setString(1, imageName);
			ResultSet resultset = preparedStatement.executeQuery();
			if (resultset.next()) {
				response.setContentType("application/octet");
				response.setHeader("Content-Disposition",
						"attachment;filename=" + imageName);
				Blob blobColumn = resultset.getBlob("image_content");
				BufferedInputStream bufferedInputStream = new BufferedInputStream(
						blobColumn.getBinaryStream());
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				int fileData = -1;
				while ((fileData = bufferedInputStream.read()) != -1) {
					byteArrayOutputStream.write(fileData);
				}
				bufferedInputStream.close();
				byteArrayOutputStream.close();
				response.setContentLength(byteArrayOutputStream.size());
				response.getOutputStream().write(
						byteArrayOutputStream.toByteArray());
				response.getOutputStream().flush();
				response.setHeader("Cache-Control", "no-cache");
				resultset.close();
				preparedStatement.close();
				connection.close();
				flagDownlaod = true;
			}
		} catch (Exception e) {
			System.out.println("Error in downloading: " + e);
		}
		return flagDownlaod;
	}

	public boolean ifProjectExists(String projectName) {
		boolean flagProjectExists = false;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			String selectProjectName = "select project_name from project_details where project_name=?";
			preparedStatement = connection.prepareStatement(selectProjectName);
			preparedStatement.setString(1, projectName);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String dbProjectName = resultSet.getString("project_name");
				if (projectName.equalsIgnoreCase(dbProjectName)) {
					flagProjectExists = true;
					// throw new PortalException("The User "
					// +projectName+" already exists");
				}
			}
		} catch (Exception e) {
			System.out.println("error6: " + e);
		} finally {
			try {
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {

			}
		}
		return flagProjectExists;
	}

	// get the manager names in AddProject.jsp
	public List<UserDTO> getProjectManagerNames() {
		List<UserDTO> deliveryManagerList = new ArrayList<UserDTO>();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			String deliveryManagerNamesQuery = "select username from user_details where designation_id in(select designation_id from designation_details where designation_name='M1' or designation_name='M2' or designation_name='M3' or designation_name='M4' or designation_name='M5')";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(deliveryManagerNamesQuery);
			while (resultSet.next()) {
				UserDTO userDTO = new UserDTO();
				userDTO.setUsername(resultSet.getString("username"));
				deliveryManagerList.add(userDTO);
			}
		} catch (Exception e) {
			System.out.println("error20" + e);
		} finally {
			try {
				resultSet.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return deliveryManagerList;
	}

	// for displaying locations in AddProject.jsp and TagUser.jsp
	public List<ProjectDTO> getLocationNames() {
		List<ProjectDTO> locationNamesList = new ArrayList<ProjectDTO>();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			String deliveryManagerNamesQuery = "select location_name from location_details";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(deliveryManagerNamesQuery);
			while (resultSet.next()) {
				ProjectDTO projectDTO = new ProjectDTO();
				projectDTO
						.setLocationName(resultSet.getString("location_name"));
				locationNamesList.add(projectDTO);
			}
		} catch (Exception e) {
			System.out.println("error21" + e);
		} finally {
			try {
				resultSet.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return locationNamesList;
	}

	// displays untagged users including managers in TagUser.jsp
	public List<UserDTO> getUntaggedUsers() {
		List<UserDTO> untaggedUsersList = new ArrayList<UserDTO>();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			String untaggedUsersListQuery = "select username from user_details where is_billable='N' and access_level between 1 and 6 and is_active='Y'";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(untaggedUsersListQuery);
			while (resultSet.next()) {
				UserDTO userDTO = new UserDTO();
				userDTO.setUsername(resultSet.getString("username"));
				untaggedUsersList.add(userDTO);
			}
		} catch (Exception e) {
			System.out.println("error25" + e);
		} finally {
			try {
				resultSet.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return untaggedUsersList;
	}

	public List<UserDTO> getProjectCode() {
		List<UserDTO> projectCodeList = new ArrayList<UserDTO>();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			String untaggedUsersListQuery = "select project_code from project_details where status='On Going'";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(untaggedUsersListQuery);
			while (resultSet.next()) {
				UserDTO userDTO = new UserDTO();
				userDTO.setProject_code(resultSet.getInt("project_code"));
				projectCodeList.add(userDTO);
			}
		} catch (Exception e) {
			System.out.println("error65" + e);
		} finally {
			try {
				resultSet.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return projectCodeList;
	}

	public List<UserDTO> getUserRole() {
		List<UserDTO> UserRoleList = new ArrayList<UserDTO>();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			String untaggedUsersListQuery = "select role_name from project_role_details";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(untaggedUsersListQuery);
			while (resultSet.next()) {
				UserDTO userDTO = new UserDTO();
				userDTO.setProjectRoleName(resultSet.getString("role_name"));
				UserRoleList.add(userDTO);
			}
		} catch (Exception e) {
			System.out.println("error65" + e);
		} finally {
			try {
				resultSet.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return UserRoleList;
	}

	// for displaying particular project in ViewProjects.jsp
	public List<ProjectDTO> viewProject(String projectName) {
		List<ProjectDTO> list = new ArrayList<ProjectDTO>();
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			String selectProjectQuery = "select project_code,project_name,project_manager,start_date,end_date from project_details where project_name like ?";
			preparedStatement = connection.prepareStatement(selectProjectQuery);
			preparedStatement.setString(1, projectName + "%");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ProjectDTO projectDTO = new ProjectDTO();
				projectDTO.setProject_code(resultSet.getInt("project_code"));
				projectDTO.setProjectName(resultSet.getString("project_name"));
				projectDTO.setProjectManager(resultSet
						.getString("project_manager"));
				projectDTO.setStartDate(resultSet.getDate("start_date"));
				projectDTO.setEndDate(resultSet.getDate("end_date"));
				list.add(projectDTO);
			}
		} catch (Exception e) {
			System.out.println("error6: " + e);
		} finally {
			try {
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {

			}
		}
		return list;
	}

	// for getting the Project details in
	public Map<Integer, List<ProjectDTO>> getProjectDetails(String projectName) {
		Map<Integer, List<ProjectDTO>> hashmap = new HashMap<Integer, List<ProjectDTO>>();
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		ResultSet resultSet = null;
		ResultSet resultSet1 = null;
		ResultSet resultSet2 = null;
		List<ProjectDTO> projectDetailsList = new ArrayList<ProjectDTO>();
		List<ProjectDTO> userDetailsList = new ArrayList<ProjectDTO>();
		List<ProjectDTO> managerDetailsList = new ArrayList<ProjectDTO>();
		List<ProjectDTO> locationList = new ArrayList<ProjectDTO>();

		int locationID = 0;
		int projectCode = 0;
		int roleID = 0;
		String projectNameDB = "";
		String projectManager = "";
		try {
			System.out.println(projectName);
			// for displaying general project details
			String projectDetailsQuery = "select project_code,project_name,objectives,project_manager,time_of_completion,number_of_modules,location_id,start_date,end_date,number_of_technical_resources,number_of_non_technical_resources,status,created_username,created_date,last_modified_username,last_modified_date from project_details where project_name = ?";
			preparedStatement = connection
					.prepareStatement(projectDetailsQuery);
			preparedStatement.setString(1, projectName);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				ProjectDTO projectDTO = new ProjectDTO();
				projectDTO.setProject_code(resultSet.getInt("project_code"));
				projectCode = resultSet.getInt("project_code");
				projectNameDB = resultSet.getString("project_name");
				projectDTO.setProjectName(resultSet.getString("project_name"));
				projectDTO.setProjectObjective(resultSet
						.getString("objectives"));

				projectManager = resultSet.getString("project_manager");

				projectDTO.setProjectManager(resultSet
						.getString("project_manager"));
				projectDTO.setTimeOfCompletion(resultSet
						.getInt("time_of_completion"));
				projectDTO
						.setNoOfModules(resultSet.getInt("number_of_modules"));

				locationID = resultSet.getInt("location_id");

				projectDTO.setStartDate(resultSet.getDate("start_date"));
				projectDTO.setEndDate(resultSet.getDate("end_date"));
				projectDTO.setNoOfTechnicalResources(resultSet
						.getInt("number_of_technical_resources"));
				projectDTO.setNoOfNonTechnicalResources(resultSet
						.getInt("number_of_non_technical_resources"));
				projectDTO.setStatus(resultSet.getString("status"));
				projectDTO.setCreateUserId(resultSet
						.getString("created_username"));
				projectDTO.setCreateDate(resultSet.getDate("created_date"));
				projectDTO.setModifyUserId(resultSet
						.getString("last_modified_username"));
				projectDTO.setModifydate(resultSet
						.getDate("last_modified_date"));
				projectDetailsList.add(projectDTO);
			}
			hashmap.put(new Integer(1), projectDetailsList);
			resultSet = null;
			// for retreiving the location of project
			String locationNameQuery = "select location_name from location_details where location_id = ?";
			preparedStatement = connection.prepareStatement(locationNameQuery);
			preparedStatement.setInt(1, locationID);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				ProjectDTO projectDTO = new ProjectDTO();
				projectDTO
						.setLocationName(resultSet.getString("location_name"));
				locationList.add(projectDTO);
			}
			hashmap.put(new Integer(2), locationList);
			resultSet = null;
			// for getting tagged users in project
			String usersQuery = "select username,tag_start_date,tag_end_date,role_id from user_project_details where project_code = ? and is_presently_tagged = 'Y' and role_id not in (select role_id from project_role_details where role_name ='Manager')";
			preparedStatement = connection.prepareStatement(usersQuery);
			preparedStatement.setInt(1, projectCode);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ProjectDTO projectDTO = new ProjectDTO();
				projectDTO.setUsername(resultSet.getString("username"));
				String username = resultSet.getString("username");
				projectDTO.setStartDate(resultSet.getDate("tag_start_date"));
				projectDTO.setEndDate(resultSet.getDate("tag_end_date"));
				roleID = resultSet.getInt("role_id");

				String roleNameQuery = "select role_name from project_role_details where role_id = ?";
				preparedStatement1 = connection.prepareStatement(roleNameQuery);
				preparedStatement1.setInt(1, roleID);
				resultSet1 = preparedStatement1.executeQuery();
				if (resultSet1.next()) {
					projectDTO.setProjectRoleName(resultSet1
							.getString("role_name"));
				}

				String userNamesQuery = "select first_name,last_name from user_details where username = ?";
				preparedStatement2 = connection
						.prepareStatement(userNamesQuery);
				preparedStatement2.setString(1, username);
				resultSet2 = preparedStatement2.executeQuery();
				if (resultSet2.next()) {
					projectDTO.setFirstName(resultSet2.getString("first_name"));
					projectDTO.setLastName(resultSet2.getString("last_name"));
				}
				userDetailsList.add(projectDTO);
			}
			hashmap.put(new Integer(3), userDetailsList);
			resultSet = null;
			// for getting Project Manager
			String managerQuery = "select project_manager,start_date,end_date from project_details where project_manager = ?";
			preparedStatement = connection.prepareStatement(managerQuery);
			preparedStatement.setString(1, projectManager);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				ProjectDTO projectDTO = new ProjectDTO();
				projectDTO.setUsername(resultSet.getString("project_manager"));
				projectDTO.setStartDate(resultSet.getDate("start_date"));
				projectDTO.setEndDate(resultSet.getDate("end_date"));
				projectDTO.setProjectRoleName("Manager");

				String userNamesQuery = "select first_name,last_name from user_details where username = ?";
				preparedStatement = connection.prepareStatement(userNamesQuery);
				preparedStatement.setString(1, projectManager);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					projectDTO.setFirstName(resultSet.getString("first_name"));
					projectDTO.setLastName(resultSet.getString("last_name"));
				}

				String managerUntagQuery = "select username from user_project_details where username = ? and project_code in (select project_code from project_details where project_name = ?) and is_presently_tagged = 'Y'";
				preparedStatement = connection
						.prepareStatement(managerUntagQuery);
				preparedStatement.setString(1, projectManager);
				preparedStatement.setString(2, projectName);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					projectDTO.setShowManagerUntag("yes");
					System.out.println("tagging for manager");
				}
				managerDetailsList.add(projectDTO);
			}
			hashmap.put(new Integer(4), managerDetailsList);
		} catch (Exception e) {
			System.out.println("error in project details" + e.getMessage());
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return hashmap;
	}

	// for displaying a list of files
	public List<ResourceDTO> getFileList(String projectName) {
		List<ResourceDTO> projectDetailsList = new ArrayList<ResourceDTO>();
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			String getFilesQuery = "select filename,uploaded_date,document_group,created_username,document_size from document_details where project_code in (select project_code from project_details where project_name = ?)";
			preparedStatement = connection.prepareStatement(getFilesQuery);
			preparedStatement.setString(1, projectName);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ResourceDTO resourceDTO = new ResourceDTO();
				resourceDTO.setFileName(resultSet.getString("filename"));
				resourceDTO.setUploadedDate(resultSet.getDate("uploaded_date"));
				resourceDTO.setDocumentGroup(resultSet
						.getString("document_group"));
				resourceDTO.setCreateUserId(resultSet
						.getString("created_username"));
				float fileSize = resultSet.getFloat("document_size") / 1024;
				resourceDTO.setFileSize(fileSize);
				projectDetailsList.add(resourceDTO);
			}
		} catch (Exception e) {
			System.out.println("Error in displaying list of files: " + e);
		}
		return projectDetailsList;
	}

	// for uploading the document
	public boolean uploadFile(ResourceDTO resourceDTO) {
		boolean flagFileUpload = false;
		try {
			System.out.println("hey");
			System.out.println("filename is: " + resourceDTO.getFileName());
			String fileUploadQuery = "insert into document_details(project_code,filename,document_type,uploaded_date,document_group,content,access_level,created_username,created_date,last_modified_username,last_modified_date,document_size) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection
					.prepareStatement(fileUploadQuery);
			preparedStatement.setInt(1, resourceDTO.getProject_code());
			preparedStatement.setString(2, resourceDTO.getFileName());
			preparedStatement.setString(3, resourceDTO.getDocumentType());
			preparedStatement.setDate(4, resourceDTO.getUploadedDate());
			preparedStatement.setString(5, resourceDTO.getDocumentGroup());
			preparedStatement.setBinaryStream(6, new ByteArrayInputStream(
					resourceDTO.getFileBytes()),
					resourceDTO.getFileBytes().length);
			// preparedStatement.setBinaryStream(6, resourceDTO.getContent());
			// preparedStatement.setBlob(6, resourceDTO.getContent());
			preparedStatement.setString(7, "naveen");
			preparedStatement.setString(8, resourceDTO.getCreateUserId());
			preparedStatement.setDate(9, resourceDTO.getCreateDate());
			preparedStatement.setString(10, resourceDTO.getModifyUserId());
			preparedStatement.setDate(11, resourceDTO.getModifydate());
			preparedStatement.setFloat(12, resourceDTO.getFileSize());
			preparedStatement.executeUpdate();
			flagFileUpload = true;
		} catch (Exception e) {
			System.out.println("Error in uploading inside ProjectDAO: " + e);
			flagFileUpload = false;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Error in closing the connection: " + e);
			}
		}
		return flagFileUpload;
	}
	
	// for uploading the document
		public boolean uploadImage(ResourceDTO resourceDTO) {
			boolean flagFileUpload = false;
			try {
				System.out.println("hey");
				System.out.println("filename is: " + resourceDTO.getFileName());
				String fileUploadQuery = "insert into image_details(image_name,image_description,image_content,image_size,created_username,created_date,last_modified_username,last_modified_date) values (?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStatement = connection
						.prepareStatement(fileUploadQuery);
				preparedStatement.setString(1, resourceDTO.getFileName());
				preparedStatement.setString(2, resourceDTO.getDescription());
				preparedStatement.setBinaryStream(3, new ByteArrayInputStream(resourceDTO.getFileBytes()),resourceDTO.getFileBytes().length);
				preparedStatement.setFloat(4, resourceDTO.getFileSize());
				preparedStatement.setString(5, resourceDTO.getCreateUserId());
				preparedStatement.setDate(6, resourceDTO.getCreateDate());
				preparedStatement.setString(7, resourceDTO.getModifyUserId());
				preparedStatement.setDate(8, resourceDTO.getModifydate());
				preparedStatement.executeUpdate();
				flagFileUpload = true;
			} catch (Exception e) {
				System.out.println("Error in uploading image inside ProjectDAO: " + e);
				flagFileUpload = false;
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println("Error in closing the connection: " + e);
				}
			}
			return flagFileUpload;
		}

	// for displaying a list of images
	public List<ResourceDTO> getImagesList() {
		List<ResourceDTO> imagesList = new ArrayList<ResourceDTO>();
		ResultSet resultSet = null;
		Statement statement = null;
		try {
			String getImagesQuery = "select image_id,image_name,image_description,image_size,created_username,created_date from image_details";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(getImagesQuery);
			while (resultSet.next()) {
				ResourceDTO resourceDTO = new ResourceDTO();
				resourceDTO.setImageID(resultSet.getInt("image_id"));
				resourceDTO.setFileName(resultSet.getString("image_name"));
				resourceDTO.setDescription(resultSet
						.getString("image_description"));
				resourceDTO.setCreateUserId(resultSet
						.getString("created_username"));
				resourceDTO.setCreateDate(resultSet.getDate("created_date"));
				float fileSize = resultSet.getFloat("image_size") / 1024;
				resourceDTO.setFileSize(fileSize);

				System.out.println("image_name");
				imagesList.add(resourceDTO);
			}
		} catch (Exception e) {
			System.out.println("Error in displaying list of images: " + e);
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return imagesList;
	}

	// To fetch number of records for displaying project list using pagination
	public int getNoOfProjectRecords(String status) {

		ResultSet resultSet1 = null;
		PreparedStatement preparedStatement1 = null;
		int noOfRecords = 0;

		try {
			String selectCountQuery = "select count(*) from project_details where status=?";
			preparedStatement1 = connection.prepareStatement(selectCountQuery);
			preparedStatement1.setString(1, "On Going");
			resultSet1 = preparedStatement1.executeQuery();
			if (resultSet1.next())
				noOfRecords = resultSet1.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noOfRecords;
	}

	// for displaying the projects in ViewProjects.jsp
	public List<ProjectDTO> viewAllProjects(String projectStatus, int offset,
			int noOfRecords) {
		List<ProjectDTO> list = new ArrayList<ProjectDTO>();
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			String selectProjectsQuery = "select project_code,project_name,project_manager,start_date,end_date from project_details where status = ? limit ?,?";
			preparedStatement = connection
					.prepareStatement(selectProjectsQuery);
			preparedStatement.setString(1, projectStatus);
			preparedStatement.setInt(2, offset);
			preparedStatement.setInt(3, noOfRecords);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ProjectDTO projectDTO = new ProjectDTO();
				projectDTO.setProject_code(resultSet.getInt("project_code"));
				projectDTO.setProjectName(resultSet.getString("project_name"));
				projectDTO.setProjectManager(resultSet
						.getString("project_manager"));
				projectDTO.setStartDate(resultSet.getDate("start_date"));
				projectDTO.setEndDate(resultSet.getDate("end_date"));
				list.add(projectDTO);
			}
		} catch (Exception e) {
			System.out.println("error6: " + e);
		} finally {
			try {
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {

			}
		}
		return list;
	}

	public void deleteUsers(String[] projectName) {
		PreparedStatement ps = null;

		int i = 0;

		String queryString = "";

		int len = projectName.length;

		for (i = 1; i <= len; i++) {

			queryString = queryString + "?";

			if (i <= (len - 1)) {

				queryString = queryString + ",";

			}

		}

		String deleteProject = "update project_details set status='Completed' where project_name in ("

				+ queryString + ")";

		try {

			ps = connection.prepareStatement(deleteProject);

			for (int j = 0; j < len; j++) {

				ps.setString(j + 1, projectName[j]);

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

	public int getNoOfProjectRecordsName(String projectName) {

		ResultSet resultSet1 = null;
		PreparedStatement preparedStatement1 = null;
		int noOfRecords = 0;

		try {
			String selectCountQuery = "select count(*) from project_details where project_name like ?";
			preparedStatement1 = connection.prepareStatement(selectCountQuery);
			preparedStatement1.setString(1, projectName + "%");
			resultSet1 = preparedStatement1.executeQuery();
			if (resultSet1.next())
				noOfRecords = resultSet1.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noOfRecords;
	}

	// Delete Images

	public void deleteImages(int imageId) {

		PreparedStatement ps = null;
		try {
			String deleteImage = "delete from image_details where image_id=?";
			ps = connection.prepareStatement(deleteImage);
			ps.setInt(1, imageId);
			ps.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {

			try {
				ps.close();
			} catch (SQLException e) {
				System.out.println("Problem in closing the connection");
			}
		}
	}

}
