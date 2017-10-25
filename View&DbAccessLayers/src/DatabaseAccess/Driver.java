package DatabaseAccess;

import java.sql.*;

public class Driver {

	public static void main(String[] args) {
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection myConn=DriverManager.getConnection("jdbc:mysql://localhost:3306/imdb2","root","password");
			
			Statement myStmt=myConn.createStatement();
			
			ResultSet myRs= myStmt.executeQuery("Select * from actors");
			
			while(myRs.next()){
				
				System.out.println(myRs.getString("id") + myRs.getString("first_name"));
				
			}
			myConn.close();
			
		}catch(Exception E){
			
		}

	}

}
