package com.capgemini.cisco.portal.dto;

import java.sql.Date;

public class UserDTO extends BaseDTO
{
	private int employeeId;
	private String password;
	private String userType;
	private String ciscoEmailId;
	private String capgeminiEmailId;
	private String primaryContactNumber;
	private String emergencyContactNumber;
	private Date ciscoDOJ;
	private String designation;
	private String isBillable;
	private String isActive;
	private String capgeminiManagerName;
	private String ciscoManagerName;
	private int totalExperience;
	private String serviceLine;
	private String primarySkill;
	private String secondarySkill_01;
	private String secondarySkill_02;
	private int billHours;
	private Date startDate;
	private Date endDate;
	private String isPresentlyTagged;
	private String isOnShore;
	private int projectRoleID;
	private String managerName2;
	private String is_delivery_manager;
	private String newDesignation;

	public String getNewDesignation() {
		return newDesignation;
	}
	public void setNewDesignation(String newDesignation) {
		this.newDesignation = newDesignation;
	}
	public String getIs_delivery_manager() {
		return is_delivery_manager;
	}
	public void setIs_delivery_manager(String is_delivery_manager) {
		this.is_delivery_manager = is_delivery_manager;
	}
	public String getManagerName2() {
		return managerName2;
	}
	public void setManagerName2(String managerName2) {
		this.managerName2 = managerName2;
	}
	public int getBillHours() {
		return billHours;
	}
	public void setBillHours(int billHours) {
		this.billHours = billHours;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getIsPresentlyTagged() {
		return isPresentlyTagged;
	}
	public void setIsPresentlyTagged(String isPresentlyTagged) {
		this.isPresentlyTagged = isPresentlyTagged;
	}
	public String getIsOnShore() {
		return isOnShore;
	}
	public void setIsOnShore(String isOnShore) {
		this.isOnShore = isOnShore;
	}
	public int getProjectRoleID() {
		return projectRoleID;
	}
	public void setProjectRoleID(int projectRoleID) {
		this.projectRoleID = projectRoleID;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getCiscoEmailId() {
		return ciscoEmailId;
	}
	public void setCiscoEmailId(String ciscoEmailId) {
		this.ciscoEmailId = ciscoEmailId;
	}
	public String getCapgeminiEmailId() {
		return capgeminiEmailId;
	}
	public void setCapgeminiEmailId(String capgeminiEmailId) {
		this.capgeminiEmailId = capgeminiEmailId;
	}
	public String getPrimaryContactNumber() {
		return primaryContactNumber;
	}
	public void setPrimaryContactNumber(String primaryContactNumber) {
		this.primaryContactNumber = primaryContactNumber;
	}
	public String getEmergencyContactNumber() {
		return emergencyContactNumber;
	}
	public void setEmergencyContactNumber(String emergencyContactNumber) {
		this.emergencyContactNumber = emergencyContactNumber;
	}
	public Date getCiscoDOJ() {
		return ciscoDOJ;
	}
	public void setCiscoDOJ(Date ciscoDOJ) {
		this.ciscoDOJ = ciscoDOJ;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getIsBillable() {
		return isBillable;
	}
	public void setIsBillable(String isBillable) {
		this.isBillable = isBillable;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getCapgeminiManagerName() {
		return capgeminiManagerName;
	}
	public void setCapgeminiManagerName(String capgeminiManagerName) {
		this.capgeminiManagerName = capgeminiManagerName;
	}
	public String getCiscoManagerName() {
		return ciscoManagerName;
	}
	public void setCiscoManagerName(String ciscoManagerName) {
		this.ciscoManagerName = ciscoManagerName;
	}
	public int getTotalExperience() {
		return totalExperience;
	}
	public void setTotalExperience(int totalExperience) {
		this.totalExperience = totalExperience;
	}
	public String getServiceLine() {
		return serviceLine;
	}
	public void setServiceLine(String serviceLine) {
		this.serviceLine = serviceLine;
	}
	public String getPrimarySkill() {
		return primarySkill;
	}
	public void setPrimarySkill(String primarySkill) {
		this.primarySkill = primarySkill;
	}
	public String getSecondarySkill_01() {
		return secondarySkill_01;
	}
	public void setSecondarySkill_01(String secondarySkill_01) {
		this.secondarySkill_01 = secondarySkill_01;
	}
	public String getSecondarySkill_02() {
		return secondarySkill_02;
	}
	public void setSecondarySkill_02(String secondarySkill_02) {
		this.secondarySkill_02 = secondarySkill_02;
	}
	

}
