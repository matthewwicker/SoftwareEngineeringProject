package DatabaseAccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entities.User;
public class UserDBManager {
	private static Driver driver = new Driver();
	/**
	 * add book to database
	 * @return -1 if failure and 1 if success
	 */
	public static int addUser(User user) {
		String query = "INSERT INTO users (email, fname, password,type) ";
		query += "VALUES ('" + user.getEmail() + "', '" + user.getfName() + "', '";
		query += user.getPassword() +  "','0')";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	/**
	 * get book from database
	 * @return -1 if failure and 1 if success
	 */
	public static int removeUser(User user) {
		String query = "DELETE from users where uid = " + user.getUid();
		int success = driver.delete(query);
		return success;
	}
	/**
	 * Search the items in books by searchParam
	 * @param searchParam
	 * @param searchItem
	 * @return
	 */
	public static ArrayList<User> searchUsers(String searchParam, String searchItem){
		ArrayList<User> search_results = new ArrayList<User>();
		String query = "select * from book where " + searchParam+ "= " + searchItem;
		ResultSet rs = driver.retrieve(query);
		User user = new User();
		if(rs != null){
			try {
				while(rs.next()){
					user.setUid(rs.getInt("uid"));
					user.setfName(rs.getString("fname"));
					user.setPassword(rs.getString("password"));
					user.setEmail(rs.getString("email"));
					user.setType(rs.getString("type"));
					search_results.add(user);
				}
				
			}
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return search_results;
	}
}
