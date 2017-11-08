package DatabaseAccess;

import java.sql.*;

public class Driver {
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

}
