package com.book.pkg;
/*
public class BabyDriver {


	public static void main(String[] args) {
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookz","root","cho");
			
			Statement state = con.createStatement();
			
			ResultSet result = state.executeQuery("Select * from users");
			
			while(result.next())
			{
				System.out.println(result.getInt("uid")+" "+ result.getString("email")+" "+result.getString("name")+" "+result.getString("password")+" "+result.getInt("type"));
			}
					
		}
		catch(Exception exc)
		{
			exc.printStackTrace();
		}
	}

}*/


import java.sql.*;

public class BabyDriver {
	static Connection con;
	private static DbAccessImpl dbAccess = new DbAccessImpl();

	public static void main(String[] args) {
		
		con = dbAccess.connect();
		String query = ("select * from users");
		ResultSet rs = dbAccess.retrieve(con, query);
		try {
			while(rs.next()){
				
				System.out.println(rs.getString("uid") + rs.getString("name"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbAccess.disconnect(con);

	}
	
	public String signIn(String user, String pass)
	{
		con = dbAccess.connect();
		String query = ("select * from users");
		ResultSet rs = dbAccess.retrieve(con, query);
		try {
			String match = "false";
			while(rs.next()){
				String name = rs.getString("name");
				if (name.equals(user)) {	
					String password = rs.getString("password");
					if (password.equals(pass)) {
						match = "true";
					}
				}
			}
			dbAccess.disconnect(con);
			return match;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dbAccess.disconnect(con);
			return "an error occured";
		}
	
	}

}
