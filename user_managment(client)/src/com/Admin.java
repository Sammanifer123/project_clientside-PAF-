package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Admin {
	public Connection connect() {
		System.out.println("Inside connect method");
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget_system", "root", "");
			// Provide the correct details: DBServer/DBName, username, password
			
			System.out.println("successfully connected");
		} catch (Exception e) {
			System.out.println("not connected");
			e.printStackTrace();
		}
		return con;
	}
	public String insertAdmin(String name, String address, String email, String age, String pno,
			String user_type) {
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
			String newAdmins = readAdmin();
			 output = "{\"status\":\"success\", \"data\": \"" +
			 newAdmins + "\"}";
		} catch (Exception e) {
			 output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
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
				String pno = rs.getString("contactNum");
				String user_type = rs.getString("User_Type");

				// Add into the html table
				output += "<tr><td>" + Name + "</td>";
				output += "<td>" + Address + "</td>";
				output += "<td>" + Email + "</td>";
				output += "<td>" + Age + "</td>";
				output += "<td>" + pno + "</td>";
				output += "<td>" + user_type + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-adminid='" + UID + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-adminid='" + UID + "'></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
			}
			catch (Exception e) {
			output = "Error while reading the User.";
			System.err.println(e.getMessage());
		}
		return output;
	}
public String updateAdmin(String UID,String name, String address, String email, String age, String pno,
		String user_type){
	    
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
	           preparedStmt.setString(2,age );
	           preparedStmt.setString(3, email);
	           preparedStmt.setString(4, address);
	           preparedStmt.setString(5, pno);
	           preparedStmt.setString(6, user_type);
	           preparedStmt.setString(7, UID);
	         

	           // execute the statement
	           preparedStmt.execute();
	           con.close();
	           String newAdmins = readAdmin();
	           output = "{\"status\":\"success\", \"data\": \"" +
	      			 newAdmins + "\"}";
	           


	        }catch(Exception e){
	        	output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
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

				   String newAdmins = readAdmin();
		           output = "{\"status\":\"success\", \"data\": \"" +
		      			 newAdmins + "\"}";

			} catch (Exception e) {

				output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
				 System.err.println(e.getMessage());
			}

			return output;
		}

}