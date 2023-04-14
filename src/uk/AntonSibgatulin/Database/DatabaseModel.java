package uk.AntonSibgatulin.Database;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import uk.AntonSibgatulin.User.User;
import uk.AntonSibgatulin.User.UserType;


import java.sql.*;

public class DatabaseModel {
	
	
	  ResultSet resultSet;
	  int res;
	  Connection connection = null;
	  Statement statement = null;
	 
	  




public DatabaseModel(){
    System.out.println("Registering JDBC driver...");
try {
    Class.forName("com.mysql.jdbc.Driver");
}catch(ClassNotFoundException e){

}
try {
    System.out.println("Creating database connection...");
    connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

    System.out.println("Executing statement...");
    statement = connection.createStatement();
}catch(SQLException e)
{
    System.out.println("lol");
}
}
    /**
     * JDBC Driver and database url
     */
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/tanks";

    /**
     * User and Password
     */
    static final String USER = "root";
    static final String PASSWORD = "Dert869$$";



	
	
	public ArrayList<JSONObject> getJSONArray(long id){
		ArrayList<JSONObject> array = new ArrayList<>();
		String args = "SELECT * FROM `avangar` WHERE `id` = ?";
		
		try(PreparedStatement statement = connection.prepareStatement(args)){
			statement.setLong(1, id);
			
			try(ResultSet result  = statement.executeQuery()){
				while(result.next())
				{
					try {
						JSONObject json = new JSONObject (result.getString("tanks"));
						JSONObject json1 = new JSONObject (result.getString("weapons"));
						JSONObject json2 = new JSONObject (result.getString("inventar"));
						array.add(json);
						array.add(json1);
						//System.out.println(result.getString("weapons"));
						array.add(json2);
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
			}
		}catch(SQLException e){
			
		}
		
		
		
		return array;
	}
	
	
	
	public User getUser (String login,String password){
		String args = "SELECT * FROM `users` WHERE `login` = ? AND `password` = ? ";
		try(PreparedStatement statement = connection.prepareStatement(args)){
			statement.setString(1, login);
			statement.setString(2, password);
			try(ResultSet result  = statement.executeQuery()){
				while(result.next())
				{
					System.out.println(result.getString("login"));
					if(login.equals(result.getString("login")) && password.equals(result.getString("password")))
					{
						UserType userType = null;
						int i = result.getInt("UserType");
						if(i == 0)userType = UserType.USER;
						if(i == 1)userType = UserType.TESTER;
						if(i == 2)userType = UserType.MODER;
						if(i == 3)userType = UserType.ADMIN;
						if(i == 4)userType = UserType.DEVELOP;
						ArrayList<JSONObject> jsonArray = this.getJSONArray(result.getLong("id"));
						User user = new User (login,password,result.getDouble("money"),result.getInt("score"),result.getLong("id"),userType,jsonArray.get(0),jsonArray.get(1),jsonArray.get(2));
						return user;
					}
					
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("[ERROR] Database.class error ");
		}
		return null;
		
	}

}
