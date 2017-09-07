package com.capgemini.cisco.portal.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckUserTagging {

	public static String checkTagging(String username) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String is_billable=null;
		try
		{
			connection = DBConnection.getDBConnection();
			String selectDesignationNames = "select is_billable from user_details where username=?";
			preparedStatement = connection.prepareStatement(selectDesignationNames);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				is_billable=resultSet.getString("is_billable");
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
				preparedStatement.close();
				connection.close();
			} 
			catch (SQLException e) 
			{
				System.out.println("problem in closing connection");
			}
		}
		return is_billable;
	}
}
