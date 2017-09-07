package com.capgemini.cisco.portal.service;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.capgemini.cisco.portal.dao.ProjectDAO;
import com.capgemini.cisco.portal.dto.ProjectDTO;
import com.capgemini.cisco.portal.dto.ResourceDTO;
import com.capgemini.cisco.portal.dto.UserDTO;
import com.capgemini.cisco.portal.utility.GetSqlDate;

public class ProjectManagementModule implements ProjectManagementInterface {
	ProjectDAO projectDAO = new ProjectDAO();
	ResourceDTO resourceDTO = new ResourceDTO();

	@Override
	public boolean addProject(ProjectDTO projectDTO) throws Exception {
		boolean flagProjectExist = projectDAO.ifProjectExists(projectDTO
				.getProjectName());
		if (!flagProjectExist)
			projectDAO.addProject(projectDTO);
		return flagProjectExist;
	}

	@Override
	public List<UserDTO> fillManagerNames() {
		List<UserDTO> projectManagerList = projectDAO.getProjectManagerNames();
		return projectManagerList;
	}

	public boolean deleteDocument(String fileName) {
		boolean flagDeleteDocument = projectDAO.deleteDocuments(fileName);
		return flagDeleteDocument;
	}

	@Override
	public List<ProjectDTO> fillLocationNames() {
		List<ProjectDTO> locationNamesList = projectDAO.getLocationNames();
		return locationNamesList;
	}

	public int updateProjectDetails(ProjectDTO projectDTO) {
		int noOfRowsAffected = 0;
		noOfRowsAffected = projectDAO.updateProjectDetails(projectDTO);
		return noOfRowsAffected;
	}

	@Override
	public boolean downloadFile(String fileName, HttpServletResponse response) {
		boolean flagDownload = projectDAO.downloadFile(fileName, response);
		return flagDownload;
	}

	@Override
	public boolean downloadImage(String imageName, HttpServletResponse response) {
		boolean flagDownload = projectDAO.downloadImage(imageName, response);
		return flagDownload;
	}

	@Override
	public List<ProjectDTO> viewProject(String projectName) {
		List<ProjectDTO> list = projectDAO.viewProject(projectName);
		return list;
	}

	@Override
	public Map<Integer, List<ProjectDTO>> getProjectDetails(String projectName) {
		Map<Integer, List<ProjectDTO>> hashmap = projectDAO
				.getProjectDetails(projectName);
		return hashmap;
	}

	@Override
	public List<ResourceDTO> fileList(String projectName) {
		List<ResourceDTO> projectDetailsList = projectDAO
				.getFileList(projectName);
		return projectDetailsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean uploadFile(HttpServletRequest request, String projectCode,
			String uploadDate, String fileGroup, String username) {
		InputStream filecontent = null;
		byte[] fileBytes = new byte[] {};
		float fileSize = 0;
		boolean flagFileUpload = false;
		String filename = "";
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iter = items.iterator();
				if (iter.hasNext()) {
					FileItem fileItem = (FileItem) iter.next();
					System.out.println(fileItem);
					filecontent = fileItem.getInputStream();
					fileSize = fileItem.getSize();
					System.out.println(fileSize);
					fileBytes = fileItem.get();
					String fileName = fileItem.getName();
					System.out.println(fileName);
					if (fileName != null) {
						filename = FilenameUtils.getName(fileName);
						System.out.println(filename);
					}
					resourceDTO.setProject_code(Integer.parseInt(projectCode));
					resourceDTO.setFileName(filename);
					resourceDTO.setDocumentType("Y");
					resourceDTO.setUploadedDate(GetSqlDate.getDate(uploadDate));
					resourceDTO.setDocumentGroup(fileGroup);
					resourceDTO.setContent(filecontent);
					resourceDTO.setFileBytes(fileBytes);
					resourceDTO.setCreateUserId(username);
					resourceDTO.setCreateDate(GetSqlDate.getDate(uploadDate));
					resourceDTO.setModifyUserId(username);
					resourceDTO.setModifydate(GetSqlDate.getDate(uploadDate));
					resourceDTO.setFileSize(fileSize);
					flagFileUpload = projectDAO.uploadFile(resourceDTO);
				}

			} catch (Exception e) {
				System.out.println("error in uploading file in project:" + e);
			}
		}
		return flagFileUpload;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean uploadImage(HttpServletRequest request, String description, String uploadDate, String username) 
	{
		InputStream filecontent = null;
		byte[] fileBytes = new byte[] {};
		float fileSize = 0;
		boolean flagFileUpload = false;
		String filename = "";
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iter = items.iterator();
				if (iter.hasNext()) {
					FileItem fileItem = (FileItem) iter.next();
					System.out.println(fileItem);
					filecontent = fileItem.getInputStream();
					fileSize = fileItem.getSize();
					System.out.println(fileSize);
					fileBytes = fileItem.get();
					String fileName = fileItem.getName();
					System.out.println(fileName);
					if (fileName != null) {
						filename = FilenameUtils.getName(fileName);
						System.out.println(filename);
					}
					resourceDTO.setFileName(filename);
					resourceDTO.setUploadedDate(GetSqlDate.getDate(uploadDate));
					resourceDTO.setDescription(description);
					resourceDTO.setContent(filecontent);
					resourceDTO.setFileBytes(fileBytes);
					resourceDTO.setCreateUserId(username);
					resourceDTO.setCreateDate(GetSqlDate.getDate(uploadDate));
					resourceDTO.setModifyUserId(username);
					resourceDTO.setModifydate(GetSqlDate.getDate(uploadDate));
					resourceDTO.setFileSize(fileSize);
					flagFileUpload = projectDAO.uploadImage(resourceDTO);
				}

			} catch (Exception e) {
				System.out.println("error in uploading file in project:" + e);
			}
		}
		return flagFileUpload;
	}

	@Override
	public List<ResourceDTO> displayImages() {
		List<ResourceDTO> imagesList = projectDAO.getImagesList();
		return imagesList;
	}

	@Override
	public int getNoOfProjectRecords(String status) {

		int noOfRecords = projectDAO.getNoOfProjectRecords(status);
		return noOfRecords;
	}

	@Override
	public List<ProjectDTO> viewAllProjects(String projectStatus, int offset,
			int noOfRecords) {
		List<ProjectDTO> list = projectDAO.viewAllProjects(projectStatus,
				offset, noOfRecords);
		return list;
	}

	@Override
	public void deleteProjects(String[] projectName) {

		projectDAO.deleteUsers(projectName);

	}

	public int getNoOfProjectRecordsName(String projectName) {
		int noOfRecords = projectDAO.getNoOfProjectRecordsName(projectName);
		return noOfRecords;
	}

	public void deleteImage(int imageId) {

		projectDAO.deleteImages(imageId);
	}

	@Override
	public boolean uploadFile(HttpServletRequest request, String projectCode,
			String uploadDate, String fileGroup) {
		// TODO Auto-generated method stub
		return false;
	}

}
