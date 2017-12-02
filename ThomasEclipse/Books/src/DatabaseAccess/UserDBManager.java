package DatabaseAccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entities.User;
public class UserDBManager {
	private static Driver driver = new Driver();
	/**
	 * add user to database
	 * @return -1 if failure and 1 if success
	 */
	public static int addUser(User user) {
		String query = "INSERT INTO users (email, fname, lname, password, phonenumber, type, verify) ";
		query += "VALUES ('" + user.getEmail() + "', '" + user.getFname() + "', '" + user.getLname()+"', ";
		//query += "AES_ENCRYPT('" + user.getPassword() + "', UNHEX(SHA2('test', 512)))" + "', '" +  user.getPhoneNumber() + "', '" + user.getType() + "', '0')";
		query += "'" + user.getPassword() + "', '" +  user.getPhoneNumber() + "', '" + user.getType() + "', '0')";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
	public static int setPromoPref(String value, User user) {
		String query = "UPDATE users SET getsPromo = '"+ value +"' WHERE email = '"+user.getEmail() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
	public static int setSuspension(String value, User user) {
		String query = "UPDATE users SET suspended = '"+ value +"' WHERE email = '"+user.getEmail() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
	public static int setPassword(String value, User user) {
		//String query = "UPDATE users SET password = AES_ENCRYPT('"+ value + "', UNHEX(SHA2('test',512)))" +"' WHERE email = '"+user.getEmail() +"'; ";
		String query = "UPDATE users SET password = '"+ value + "' WHERE email = '"+user.getEmail() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
	public static int setStatus(String value, User user) {
		String query = "UPDATE users SET type = '"+ value +"' WHERE email = '"+user.getEmail() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
	
	/**
	 * remove user from database
	 * @return -1 if failure and 1 if success
	 */
	public static int removeUser(User user) {
		String query = "DELETE from users where uid = " + user.getUid();
		int success = driver.delete(query);
		return success;
	}
	/**
	 * Search the items in users by searchParam
	 * @param searchParam
	 * @param searchItem
	 * @return
	 */
	public static ArrayList<User> searchUsers(String searchParam, String searchItem){
		ArrayList<User> search_results = new ArrayList<User>();
		//String query = "select *, AES_DECRYPT(password, UNHEX(SHA2('test',512))) from users where " + searchParam+ "= '" + searchItem + "'";
		String query = "select * from users where " + searchParam+ "= '" + searchItem + "'";
		ResultSet rs = driver.retrieve(query);
		User user = new User();
		if(rs != null){
			try {
				while(rs.next()){
					user.setUid(rs.getInt("uid"));
					user.setFname(rs.getString("fname"));
					user.setLname(rs.getString("lname"));
					user.setPhoneNumber(rs.getString("phonenumber"));
					//user.setPassword(rs.getString("AES_DECRYPT(password, UNHEX(SHA2('test',512)))"));
					user.setPassword(rs.getString("password"));
					user.setEmail(rs.getString("email"));
					user.setType(rs.getString("type"));
					user.setSubscribed(rs.getString("getsPromo"));
					search_results.add(user);
				}
				driver.disconnect();
			}
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return search_results;
	}
	
	
	
	public static String getUserPreference(String searchParam, String searchItem){
		ArrayList<User> search_results = new ArrayList<User>();
		String query = "select * from users where " + searchParam+ "= '" + searchItem + "'";
		ResultSet rs = driver.retrieve(query);
		User user = new User();
		if(rs != null){
			try {
				while(rs.next()){
					return rs.getString("getsPromo");
				}
				driver.disconnect();
			}
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "-1";
			}
		}
		return "-1";
	}
	
	public static String getUserSuspension(String searchParam, String searchItem){
		ArrayList<User> search_results = new ArrayList<User>();
		String query = "select * from users where " + searchParam+ "= '" + searchItem + "'";
		ResultSet rs = driver.retrieve(query);
		if(rs != null){
			try {
				while(rs.next()){
					return rs.getString("suspended");
				}
				driver.disconnect();
			}
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "-1";
			}
		}
		return "-1";
	}
	
	public static String getUserStatus(String searchParam, String searchItem){
		ArrayList<User> search_results = new ArrayList<User>();
		String query = "select * from users where " + searchParam+ "= '" + searchItem + "'";
		ResultSet rs = driver.retrieve(query);
		if(rs != null){
			try {
				while(rs.next()){
					return rs.getString("type");
				}
				driver.disconnect();
			}
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "-1";
			}
		}
		return "-1";
	}
}
