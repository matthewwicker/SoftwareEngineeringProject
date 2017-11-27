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
		String query = "INSERT INTO users (email, fname, lname, password,type) ";
		query += "VALUES ('" + user.getEmail() + "', '" + user.getfName() + "', '" + user.getlName()+"', '";
		query += user.getPassword() +  "','0')";
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
	// THIS NEEDS TO BE FIXED -- IM SORRY
	public static ArrayList<User> searchUsers(String searchParam, String searchItem){
		ArrayList<User> search_results = new ArrayList<User>();
		String query = "select * from user where " + searchParam+ "= '" + searchItem + "'";
		ResultSet rs = driver.retrieve(query);
		User user = new User();
		if(rs != null){
			try {
				while(rs.next()){
					user.setUid(rs.getInt("uid"));

					user.setfName(rs.getString("fname"));
					user.setlName(rs.getString("lname"));

					user.setPassword(rs.getString("password"));
					user.setEmail(rs.getString("email"));
					user.setType(rs.getString("type"));
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
}
