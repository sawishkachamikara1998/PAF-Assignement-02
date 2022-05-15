package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import com.jersy.dbconnect.dbConnection;

import user.bean.changePasswordBean;

public class UserDao {

	// register

	public static String registerDao(String email, String name, String password, String mobile) {

		int otp = new Random().nextInt(345);

		Connection con = dbConnection.connect();

		try {

			PreparedStatement ps1 = con.prepareStatement("select email from user_new where email=?");
			ps1.setString(1, email);
			ResultSet rrs = ps1.executeQuery();

			if (rrs.next()) {
				return "Already Exist";
			} else {

				
				PreparedStatement ps = con.prepareStatement("insert into user_new values(?,?,?,?,?, ?, ?)");
				ps.setString(1, null);
				ps.setString(2, name);
				ps.setString(3, email);
				ps.setString(4, password);
				ps.setString(5, mobile);
				ps.setInt(6, otp);
				ps.setString(7, "inActive");

				int i = ps.executeUpdate();

				if (i > 0) {
					return "User Registered Successfully";
				} else {
					return "Registered Fail";
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return "fail";

	}

	// check email & password

	public static boolean checkEmailPwd(changePasswordBean changePasswordBean) {

		Connection con = dbConnection.connect();

		try {

			PreparedStatement ps = con.prepareStatement("select * from user where email=? and password=?");
			ps.setNString(1, changePasswordBean.getEmail());
			ps.setNString(2, changePasswordBean.getPassword());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}

	// change password

	public static boolean changePassword(changePasswordBean changePasswordBean) {

		Connection con = dbConnection.connect();

		try {

			PreparedStatement ps = con.prepareStatement("update user set password=? where email=?");
			ps.setNString(1, changePasswordBean.getNewpassword());
			ps.setNString(2, changePasswordBean.getEmail());
			int i = ps.executeUpdate();

			if (i > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}

	// update user profile

	public static boolean updateUserDetails(String id, String email, String name, String mobile, String password) {

		Connection con = dbConnection.connect();


		try {

			PreparedStatement ps = con
					.prepareStatement("update user set name=?, mobile=?, password=?, email=?  where ID=?");
			ps.setNString(1, name);
			ps.setNString(2, mobile);
			ps.setNString(3, password);
			ps.setNString(4, email);
			ps.setInt(5, Integer.parseInt(id));

			int i = ps.executeUpdate();

			if (i > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}

	// delete user profile

	public static boolean deleteUser(String ID) {

		Connection con = dbConnection.connect();

		try {

			PreparedStatement ps = con.prepareStatement("DELETE FROM user_new WHERE userID=?");
			ps.setInt(1, Integer.parseInt(ID));
			int i = ps.executeUpdate();

			if (i > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}

	// login

	public static String loginDao(String email, String password) {

		String output = "";
		Connection con = dbConnection.connect();

		try {

			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Email</th><th>Name</th>" + "<th>Password</th>"
					+ "<th>User's Mobile</th>" + "<th>OTP</th>" + "<th>Status</th>"
					+ "<th>Update</th><th>Remove</th></tr>";

			PreparedStatement ps1 = con.prepareStatement("select * from user where email=? and password=?");
			ps1.setString(1, email);
			ps1.setString(2, password);
			ResultSet rrs = ps1.executeQuery();

			if (rrs.next()) {

				String name = rrs.getString(1);
				String Email = rrs.getString(2);
				String Password = rrs.getString(3);
				String mobile = rrs.getString(4);
				String otp = rrs.getString(5);
				String status = rrs.getString(6);

				PreparedStatement ps2 = con.prepareStatement("update user set status = ? where email = ?");
				ps2.setString(1, "online");
				ps2.setNString(2, email);
				int i = ps2.executeUpdate();

				output += "<tr><td><input id='hidIDUpdate'" + " name='hidIDUpdate' " + " type='hidden' value='" + email
						+ "'>" + email + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + Password + "</td>";
				output += "<td>" + mobile + "</td>";
				output += "<td>" + otp + "</td>";
				output += "<td>" + status + "</td>";
				// buttons
				output += "<td><input name='btnUpdate'" + " type='button' value='Update'"
						+ "class='btnUpdate btn btn-secondary'></td>" + "<td><input name='btnRemove'"
						+ "type='button' value='Remove'" + " class='btnRemove btn btn-danger'" + " data-itemid='"
						+ email + "'>" + "</td></tr>";

				if (i > 0) {

					return output;
				}

			} else {

				return "failed";

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return "fail";

	}


	// read users

	public static String readUsers() {

		String output = "";

		try {

			Connection con = dbConnection.connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";

			}

			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>User's Name</th><th>User's E-mail</th>" + "<th>User's Mobile</th>"
					+ "<th>Active or Not</th> </tr>";

			String query = "select * from user";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String name = rs.getString(1);
				String email = rs.getString(2);
				String mobile = rs.getString(4);
				String status = rs.getString(6);

				// Add into the html table
				output += "<tr><td>" + name + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + mobile + "</td>";
				output += "<td>" + status + "</td>";

			}
			con.close();

			// Complete the html table
			output += "</table>";

		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;

	}

	// read all users
	public String readCard() {
		String output = "";
		try {
			Connection con = dbConnection.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>User's Email</th><th>User's Name</th>" + "<th>User's Mobile</th>"
					+ "<th>password</th>" + "<th>Active or Not</th>" + "<th>Update</th><th>Remove</th></tr>";
			String query = "select * from user_new";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String ID = Integer.toString(rs.getInt(1));
				String name = rs.getString(2);
				String email = rs.getString(3);
				String password = rs.getString(4);
				String mobile = rs.getString(5);
				String status = rs.getString(7);

				// Add into the html table
				output += "<tr><td><input id='hidIDUpdate'" + " name='hidIDUpdate' " + " type='hidden' value='" + ID
						+ "'>" + email + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + mobile + "</td>";
				output += "<td>" + password + "</td>";
				output += "<td>" + status + "</td>";

				// buttons
//				output += "<td><input name='btnUpdate'" + " type='button' value='Update'"
//						+ "class='btnUpdate btn btn-secondary'></td>" + "<td> <input name='btnRemove'"
//						+ "type='button' value='Remove'" + " class='btnRemove btn btn-danger'" + " data-itemid='"
//						+ email + "'>" + "</td></tr>";

				output += "<td><input name='btnUpdate' type='button' value='Update' class=' btnUpdate btn btn-secondary'></td> "
						+ "<td><form method='post' action='register.jsp'> " + "<input name='btnRemove' type='submit' "
						+ "value='Remove' class='btn btn-danger'> " + "<input name='hidItemIDDelete' type='hidden' "
						+ "value='" + ID + "'>" + "</form></td></tr>";

			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the card details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
