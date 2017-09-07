package com.capgemini.cisco.portal.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FetchProjectRoleIDUtil 
{
	public static int getProjectRoleID(String projectRoleName)
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int projectRoleID = 0;
		try
		{
			connection = DBConnection.getDBConnection();
			String projectRoleIDQuery = "select role_id from project_role_details where role_name = ?";
			preparedStatement = connection.prepareStatement(projectRoleIDQuery);
			preparedStatement.setString(1, projectRoleName);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				projectRoleID = resultSet.getInt("role_id");
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
		return projectRoleID;
	}
}
