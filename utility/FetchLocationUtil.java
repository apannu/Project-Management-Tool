package com.capgemini.cisco.portal.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FetchLocationUtil 
{
	public static int getLocationID(String locationName)
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int locationID = 0;
		try
		{
			connection = DBConnection.getDBConnection();
			String selectLocationID = "select location_id from location_details where location_name = ?";
			preparedStatement = connection.prepareStatement(selectLocationID);
			preparedStatement.setString(1, locationName);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				locationID = resultSet.getInt("location_id");
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
		return locationID;
	}
	
	public static String getLocationName(int locationID)
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String locationName =null;
		try
		{
			connection = DBConnection.getDBConnection();
			String selectLocationID = "select location_name from location_details where location_id = ?";
			preparedStatement = connection.prepareStatement(selectLocationID);
			preparedStatement.setInt(1, locationID);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				locationName = resultSet.getString("location_name");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
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
		return locationName;
	}
}
