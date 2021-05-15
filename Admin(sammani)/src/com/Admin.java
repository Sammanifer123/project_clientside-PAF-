package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Admin {

	public Connection connect() {
		Connection con = null;
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget_system", "root", "");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			return con;
	}
	
	
	public String insertAdmin(String name, String address, String email, String age, String pno, String user_type) {
		String output = " ";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database.";

			}

			// create a prepared statement
			String query = " insert into admin(`UID`,`Name`,`Age`,`Email`,`Address`,`contactNum`,`User_Type`)"
					+ " values (?, ?, ?, ?, ?, ? ,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, age);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, address);
			preparedStmt.setString(6, pno);
			preparedStmt.setString(7, user_type);
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newAdmin = readAdmin();
			 output = "{\"status\":\"success\", \"data\": \"" +
			 newAdmin + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readAdmin() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>User Name</th><th>Address</th><th>Email</th><th>Age</th><th>Phone No</th><th>User Type</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from admin";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set

			while (rs.next()) {
				String UID = Integer.toString(rs.getInt("UID"));
				String Name = rs.getString("Name");
				String Address = rs.getString("Address");
				String Email = rs.getString("Email");
				String Age = rs.getString("Age");
				String contactNum = rs.getString("contactNum");
				String User_Type = rs.getString("User_Type");

				// Add into the html table
				output += "<td>" + Name + "</td>";
				output += "<td>" + Address + "</td>";
				output += "<td>" + Email + "</td>";
				output += "<td>" + Age + "</td>";
				output += "<td>" + contactNum + "</td>";
				output += "<td>" + User_Type + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-adminid='" + UID + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-adminid='" + UID + "'></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the User.";
			System.err.println(e.getMessage());
		}
		return output;
	}
public String updateAdmin(String ID,String name, String address, String email, String age, String pno, String user_type){
	    
	    String output = "";

	    try{

	           Connection con = connect();
	           if (con == null){
	           return "Error while connecting to the database for updating.";
	           }
	           
	           // create a prepared statement
	 
				
	           String query = "UPDATE admin SET Name=?,Age=?,Email=?,Address=?,contactNum=?,User_Type=? WHERE UID=?";
	           PreparedStatement preparedStmt = con.prepareStatement(query);

	           preparedStmt.setString(1, name);
	           preparedStmt.setString(2, age );
	           preparedStmt.setString(3, email);
	           preparedStmt.setString(4, address);
	           preparedStmt.setString(5, pno);
	           preparedStmt.setString(6, user_type);
	           preparedStmt.setString(7, ID);
	         

	           // execute the statement
	           preparedStmt.execute();
	           con.close();

	           String newAdmin = readAdmin();
				 output = "{\"status\":\"success\", \"data\": \"" +
				 newAdmin + "\"}";
				 
	        }catch(Exception e){
	        	output = "{\"status\":\"error\", \"data\":\"Error while updating.\"}";
				System.err.println(e.getMessage());
	        }

	        return output;

	    }

		public String deleteAdmin(String UID) {

			String output = "";

			try { 

				Connection con = connect();
				if (con == null) {

					return "Error while connecting to the database for deleting.";
				}

				// create a prepared statement
				String query = "delete from admin where UID=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setString(1, UID);
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				 String newAdmin = readAdmin();
				 output = "{\"status\":\"success\", \"data\": \"" +
				 newAdmin + "\"}";

			} catch (Exception e) {

				output = "{\"status\":\"error\", \"data\":\"Error while updating.\"}";
				System.err.println(e.getMessage());
			}

			return output;
		}
}
