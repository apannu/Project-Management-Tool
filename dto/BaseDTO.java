package com.capgemini.cisco.portal.dto;

public class BaseDTO 
{
	private java.sql.Date createDate;
	private String createUserId;
	private java.sql.Date modifydate;
	private String modifyUserId;
	private int project_code;
	private int locationID;
	private String locationName;
	private String username;
	private String deliveryManager;
	private String projectCodeStatus;
	private String projectName;
	private String projectManager;
	private String projectRoleName;
	private String firstName;
	private String lastName;
	private String ciscoUsername;
	
	public String getCiscoUsername() {
		return ciscoUsername;
	}
	public void setCiscoUsername(String ciscoUsername) {
		this.ciscoUsername = ciscoUsername;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getProjectRoleName() {
		return projectRoleName;
	}
	public void setProjectRoleName(String projectRoleName) {
		this.projectRoleName = projectRoleName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectManager() {
		return projectManager;
	}
	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}
	public String getProjectCodeStatus() {
		return projectCodeStatus;
	}
	public void setProjectCodeStatus(String projectCodeStatus) {
		this.projectCodeStatus = projectCodeStatus;
	}
	public String getDeliveryManager() {
		return deliveryManager;
	}
	public void setDeliveryManager(String deliveryManager) {
		this.deliveryManager = deliveryManager;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getLocationID() {
		return locationID;
	}
	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public int getProject_code() {
		return project_code;
	}
	public void setProject_code(int project_code) {
		this.project_code = project_code;
	}
	public java.sql.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.sql.Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public java.sql.Date getModifydate() {
		return modifydate;
	}
	public void setModifydate(java.sql.Date modifydate) {
		this.modifydate = modifydate;
	}
	public String getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(String modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
}
