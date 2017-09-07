package com.capgemini.cisco.portal.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FetchLocationIDUtil 
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
			System.out.println(locationName);
			String selectLocationID = "select location_id from location_details where location_name = ?";
			preparedStatement = connection.prepareStatement(selectLocationID);
			preparedStatement.setString(1, locationName);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				locationID = resultSet.getInt("location_id");
			}
		}
		catch(Exception e)
		{
			System.out.println("Error in getting location id");
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
}
