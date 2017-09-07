package com.capgemini.cisco.portal.dto;

import java.sql.Date;

public class ProjectDTO extends BaseDTO
{
	private String projectObjective;
	private int timeOfCompletion;
	private int noOfModules;
	private Date startDate;
	private Date endDate;
	private int noOfTechnicalResources;
	private int noOfNonTechnicalResources;
	private String status;
	private String showManagerUntag;
	private String address;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getShowManagerUntag() {
		return showManagerUntag;
	}
	public void setShowManagerUntag(String showManagerUntag) {
		this.showManagerUntag = showManagerUntag;
	}
	public String getProjectObjective() {
		return projectObjective;
	}
	public void setProjectObjective(String projectObjective) {
		this.projectObjective = projectObjective;
	}
	public int getTimeOfCompletion() {
		return timeOfCompletion;
	}
	public void setTimeOfCompletion(int timeOfCompletion) {
		this.timeOfCompletion = timeOfCompletion;
	}
	public int getNoOfModules() {
		return noOfModules;
	}
	public void setNoOfModules(int noOfModules) {
		this.noOfModules = noOfModules;
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
	public int getNoOfTechnicalResources() {
		return noOfTechnicalResources;
	}
	public void setNoOfTechnicalResources(int noOfTechnicalResources) {
		this.noOfTechnicalResources = noOfTechnicalResources;
	}
	public int getNoOfNonTechnicalResources() {
		return noOfNonTechnicalResources;
	}
	public void setNoOfNonTechnicalResources(int noOfNonTechnicalResources) {
		this.noOfNonTechnicalResources = noOfNonTechnicalResources;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
