package com.capgemini.cisco.portal.dto;

import java.io.InputStream;

public class ResourceDTO extends BaseDTO
{
	private int documentID;
	private int imageID;
	private String fileName;
	private String documentType;
	private java.sql.Date uploadedDate;
	private String documentGroup;
	private InputStream content;
	private byte[] fileBytes;
	private float fileSize;
	private String description;
	private String pollTopic;
	private String pollDescription;
	private java.sql.Date pollEndDate;
	private String pollStatus;
	private String pollType;
	private String pollReplyText;
	private java.sql.Date pollReplyDate;
	private int pollID;
	private int yesRowCount = 0;
	private int noRowCount = 0;
	
	public int getYesRowCount() {
		return yesRowCount;
	}
	public void setYesRowCount(int yesRowCount) {
		this.yesRowCount = yesRowCount;
	}
	public int getNoRowCount() {
		return noRowCount;
	}
	public void setNoRowCount(int noRowCount) {
		this.noRowCount = noRowCount;
	}
	public int getPollID() {
		return pollID;
	}
	public void setPollID(int pollID) {
		this.pollID = pollID;
	}
	public String getPollReplyText() {
		return pollReplyText;
	}
	public void setPollReplyText(String pollReplyText) {
		this.pollReplyText = pollReplyText;
	}
	public java.sql.Date getPollReplyDate() {
		return pollReplyDate;
	}
	public void setPollReplyDate(java.sql.Date pollReplyDate) {
		this.pollReplyDate = pollReplyDate;
	}
	public String getPollType() {
		return pollType;
	}
	public void setPollType(String pollType) {
		this.pollType = pollType;
	}
	public java.sql.Date getPollEndDate() {
		return pollEndDate;
	}
	public void setPollEndDate(java.sql.Date pollEndDate) {
		this.pollEndDate = pollEndDate;
	}
	public String getPollTopic() {
		return pollTopic;
	}
	public void setPollTopic(String pollTopic) {
		this.pollTopic = pollTopic;
	}
	public String getPollDescription() {
		return pollDescription;
	}
	public void setPollDescription(String pollDescription) {
		this.pollDescription = pollDescription;
	}
	public String getPollStatus() {
		return pollStatus;
	}
	public void setPollStatus(String pollStatus) {
		this.pollStatus = pollStatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getFileSize() {
		return fileSize;
	}
	public void setFileSize(float fileSize) {
		this.fileSize = fileSize;
	}
	public int getDocumentID() {
		return documentID;
	}
	public void setDocumentID(int documentID) {
		this.documentID = documentID;
	}
	public int getImageID() {
		return imageID;
	}
	public void setImageID(int imageID) {
		this.imageID = imageID;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public java.sql.Date getUploadedDate() {
		return uploadedDate;
	}
	public void setUploadedDate(java.sql.Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}
	public String getDocumentGroup() {
		return documentGroup;
	}
	public void setDocumentGroup(String documentGroup) {
		this.documentGroup = documentGroup;
	}
	public InputStream getContent() {
		return content;
	}
	public void setContent(InputStream content) {
		this.content = content;
	}
	public byte[] getFileBytes() {
		return fileBytes;
	}
	public void setFileBytes(byte[] fileBytes) {
		this.fileBytes = fileBytes;
	}
}
