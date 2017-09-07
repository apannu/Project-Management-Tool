package com.capgemini.cisco.portal.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FetchDesignationUtil 
{
	public static int getDesignationID(String designationName)
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int designationID = 0;
		try
		{
			connection = DBConnection.getDBConnection();
			String selectDesignationID = "select designation_ID from designation_Details where designation_Name = ?";
			preparedStatement = connection.prepareStatement(selectDesignationID);
			preparedStatement.setString(1, designationName);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				designationID = resultSet.getInt("designation_ID");
				System.out.println("designation inside dao is: "+resultSet.getInt("designation_id"));
			}
		}
		catch(Exception e)
		{
			System.out.println();
		}
		finally
		{
			try 
			{
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} 
			catch (SQLException e) 
			{
				System.out.println();
			}
			
		}
		
		return designationID;
	}
	
	public static String getDesignationName(int designationId)
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String designationName = "";
		try
		{
			connection = DBConnection.getDBConnection();
			String selectDesignationName = "select designation_name from designation_details where designation_id = ?";
			preparedStatement = connection.prepareStatement(selectDesignationName);
			preparedStatement.setInt(1, designationId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				designationName = resultSet.getString("designation_name");
			}
		}
		catch(Exception e)
		{
			System.out.println();
		}
		finally
		{
			try 
			{
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} 
			catch (SQLException e) 
			{
				System.out.println();
			}
			
		}
		return designationName;
	}
}
