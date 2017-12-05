package DatabaseAccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entities.Address;
import Entities.Book;
import Entities.User;
public class AddressDBManager {
	private static Driver driver = new Driver();
	/**
	 * Adds a new address to the database
	 * @return 0 if failure and 1 if success
	 */
	public static int addAddress(Address address)
	{
		String query = "INSERT INTO address (address, billing, uid) ";
		query += "VALUES ('" + address.getAddress() + "', '" + address.isBilling() + "', '"+ address.getUid()+"')";
		System.out.println(query);
		int value = driver.create(query);
		
		return value;
	}
	/**
	 * remove address from database
	 * @return -1 if failure and 1 if success
	 */
	public static int removeAddress(Address address) {

		String query = "DELETE from address where aid = " + address.getAid();

		int success = driver.delete(query);
		return success;
	}
	/**
	 * Search the items in address by searchParam
	 * @param searchParam
	 * @param searchItem
	 * @return an ArrayList<Address>
	 */
	public static ArrayList<Address> searcAddress(String searchParam, String searchItem){
		ArrayList<Address> search_results = new ArrayList<Address>();
		String query = "select * from address where " + searchParam+ "= '" + searchItem + "'";
		ResultSet rs = driver.retrieve(query);
		if(rs != null){
			try {
				while(rs.next()){
					Address address = new Address();
					address.setAid(rs.getInt("aid"));
					address.setAddress(rs.getString("address"));
					address.setBilling(rs.getInt("billing"));
					address.setUid(rs.getInt("uid"));
					search_results.add(address);
				}
			}
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		driver.disconnect();
		return search_results;
	}
	public static ArrayList<Address> searcShippingAddress(String searchParam, String uid, String billing){
		ArrayList<Address> search_results = new ArrayList<Address>();
		String query = "select * from address where uid = '" + uid +  "' and billing = '" + billing + "'";
		System.out.println(query);
		ResultSet rs = driver.retrieve(query);
		Address address = new Address();
		if(rs != null){
			try {
				while(rs.next()){
					address.setAid(rs.getInt("aid"));
					address.setAddress(rs.getString("address"));
					address.setBilling(rs.getInt("billing"));
					address.setUid(rs.getInt("uid"));
					search_results.add(address);
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
	
	public static ArrayList<Address> searcShippingAddress(String query){
		ArrayList<Address> search_results = new ArrayList<Address>();
		System.out.println(query);
		ResultSet rs = driver.retrieve(query);
		Address address = new Address();
		if(rs != null){
			try {
				while(rs.next()){
					address.setAid(rs.getInt("aid"));
					address.setAddress(rs.getString("address"));
					address.setBilling(rs.getInt("billing"));
					address.setUid(rs.getInt("uid"));
					search_results.add(address);
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
	
	public static int setAddress(String value, Address address) {
		String query = "UPDATE address SET address = '"+ value + "' WHERE aid = '"+address.getAid() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
	public static int setBilling(String value, Address address) {
		String query = "UPDATE address SET billing = '"+ value + "' WHERE aid = '"+address.getAid() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
	public static int setUid(String value, Address address) {
		String query = "UPDATE address SET uid = '"+ value + "' WHERE aid = '"+address.getAid() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
	public static int setValid(Boolean valid, Address address) {
		int value;
		if(valid)
		{
			value = 1;
		}
		else
			value = 0;
		String query = "UPDATE address SET valid = '"+ value + "' WHERE aid = '"+address.getAid() +"'; ";
		int success = 0;
		success = driver.create(query);
		return success;
	}
}
