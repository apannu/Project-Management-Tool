package com.capgemini.cisco.portal.exception;

public class PortalException 
{
	private String displayMessage;
	public PortalException(String exceptionMessage)
	{
		displayMessage = exceptionMessage;
	}
	public String toString()
	{
		System.out.println("Exception: "+displayMessage);
		return displayMessage;
	}
}
