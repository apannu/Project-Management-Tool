package com.capgemini.cisco.portal.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.cisco.portal.dto.ProjectDTO;
import com.capgemini.cisco.portal.dto.ResourceDTO;
import com.capgemini.cisco.portal.dto.UserDTO;

public interface ProjectManagementInterface 
{
	List<UserDTO> fillManagerNames();

	List<ProjectDTO> fillLocationNames();

	Map<Integer, List<ProjectDTO>> getProjectDetails(String projectName);

	List<ResourceDTO> fileList(String projectName);

	boolean uploadFile(HttpServletRequest request, String projectCode,
			String uploadDate, String fileGroup);

	boolean addProject(ProjectDTO projectDTO) throws Exception;

	List<ResourceDTO> displayImages();

	boolean downloadFile(String fileName, HttpServletResponse response);

	boolean downloadImage(String imageName, HttpServletResponse response);

	int getNoOfProjectRecords(String status);

	List<ProjectDTO> viewAllProjects(String projectStatus, int offset,
			int noOfRecords);

	void deleteProjects(String[] projectName);

	List<ProjectDTO> viewProject(String projectName);


	boolean uploadFile(HttpServletRequest request, String projectCode,
			String uploadDate, String fileGroup, String username);

	boolean uploadImage(HttpServletRequest request, String description,
			String uploadDate, String username);
}
