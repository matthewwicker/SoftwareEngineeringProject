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
	
	/**
	 * Gets information from the database
	 * @return null if failure and ResultSet if success
	 */
	public ResultSet retrieve(String query){
		con = dbAccess.connect();
		ResultSet rs = dbAccess.retrieve(con, query);
		return rs;
	}
	
	/**
	 * Adds a new item to the database
	 * @return 0 if failure and 1 if success
	 */
	public int create(String query){
		con = dbAccess.connect();
		int result = dbAccess.create(con, query);
		return result;
	}
	
	/**
	 * Updates a row in the database
	 * @return 0 if failure and 1 if success
	 */
	public int update(String query)
	{
		con = dbAccess.connect();
		int result = dbAccess.update(con, query);
		return result;
	}
	
	/**
	 * Deletes a row in the database
	 * @return 0 if failure and 1 if success
	 */
	public int delete(String query)
	{
		con = dbAccess.connect();
		int result = dbAccess.delete(con, query);
		dbAccess.disconnect(con);
		return result;
	}
	
	public ResultSet login(String username, String password){
        con = dbAccess.connect();
        String query = String.format("select * from users where email='%s' and password='%s'", username,password);
        
        ResultSet rs = dbAccess.retrieve(con, query);
        return rs;
    }
	public void disconnect(){
		dbAccess.disconnect(con);
	}
}
