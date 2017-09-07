package com.capgemini.cisco.portal.utility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DesignationNamesUtil 
{
	public static List<String> getDesignationNames()
	{
		List<String> designationList = new ArrayList<String>(); 
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try
		{
			connection = DBConnection.getDBConnection();
			String selectDesignationNames = "select designation_name from designation_Details";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(selectDesignationNames);
			while(resultSet.next())
			{
				designationList.add(resultSet.getString("designation_name"));
			}
		}
		catch(Exception e)
		{
			System.out.println("error10: "+e);
		}
		finally
		{
			try 
			{
				resultSet.close();
				statement.close();
				connection.close();
			} 
			catch (SQLException e) 
			{
				System.out.println("problem in closing connection");
			}
		}
		return designationList;
	}
	
}
