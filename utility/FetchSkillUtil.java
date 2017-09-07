package com.capgemini.cisco.portal.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FetchSkillUtil 
{
	public static int getSkillID(String skillName)
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int skillID = 0;
		try
		{
			connection = DBConnection.getDBConnection();
			String selectSkillID = "select Skill_ID from SkillSet_Details where Skill_Name = ?";
			preparedStatement = connection.prepareStatement(selectSkillID);
			preparedStatement.setString(1, skillName);
			resultSet = preparedStatement.executeQuery(selectSkillID);
			while(resultSet.next())
			{
				skillID = resultSet.getInt("Skill_ID");
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
		return skillID;
	}
	public static String getSkillName(int skillID)
	{
		String skillName = "";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try
		{
			connection = DBConnection.getDBConnection();
			String selectSkillName = "select skill_name from skillset_details where skill_id = ?";
			preparedStatement = connection.prepareStatement(selectSkillName);
			preparedStatement.setInt(1, skillID);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				skillName = resultSet.getString("skill_name");
			}
		}
		catch(Exception e)
		{
			System.out.println("error2344"+e.getMessage());
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
		return skillName;
	}
}
