package first;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbAccessImpl extends DbAccessConfiguration implements DbAccessInterface {

	/**
	 * Connects to the mysql
	 */
	public Connection connect(){
		Connection con = null;
		try{
			Class.forName(DB_DRIVE_NAME);
			con = DriverManager.getConnection(DB_CONNECTION_URL, DB_CONNECTION_USERNAME, DB_CONNECTION_PASSWORD);
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return con;
	}
	
	/**
	 * Retrieves the result set for the query passed to it
	 * and returns that result set
	 */
	public ResultSet retrieve(Connection con, String query){
		ResultSet rset = null;
		try{
			Statement stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			return rset;
		} catch(SQLException e){
			e.printStackTrace();
			return rset;
		}
	}
	
	/**
	 * creates what was passed in the query and returns whether it
	 * was successful
	 */
	public int create(Connection con, String query){
		try{
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			return 1;
		} catch (SQLException e){
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Updates what was passed in through query and returns
	 * whether it was successful
	 */
	public int update(Connection con, String query){
		try{
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			return 1;
		} catch (SQLException e){
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Deletes what was passed in through query and returns
	 * whether it was successful
	 */
	public int delete(Connection con, String query){
		try{
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			return 1;
		} catch (SQLException e){
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * disconnects from the mysql
	 */
	public void disconnect(Connection con){
		try{
			con.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
}
