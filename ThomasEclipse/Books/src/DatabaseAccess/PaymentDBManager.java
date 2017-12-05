package DatabaseAccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entities.Address;
import Entities.Book;
import Entities.Cart;
import Entities.User;
import Entities.Payment;
import Entities.Promo;
public class PaymentDBManager {
	private static Driver driver = new Driver();
	/**
	 * Adds a new payment to the database
	 * @return 0 if failure and 1 if success
	 */
	public static int addPayment(Payment payment)
	{
		String query = "INSERT INTO payment (cc_number, expdate, type, uid, aid) ";
		query += "VALUES ('" + payment.getCc_number() + "', '"+payment.getExpdate()+"', '"+payment.getType()+"', '" + payment.getUser() + "', '"+ payment.getAid()+"')";
		System.out.println(query);
		int value = driver.create(query);
		
		return value;
	}
	
	public static int setCCNumber(String value, Payment payment) {
		String query = "UPDATE payment SET cc_number = '"+ value + "' WHERE ccid = '"+payment.getCcid() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
	public static int setAid(String value, Payment payment) {
		String query = "UPDATE payment SET aid = '"+ value + "' WHERE ccid = '"+payment.getCcid() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
	public static int removePaymment(Payment payment) {
		String query = "DELETE from `bookz`.`payment` WHERE ccid = '" + payment.getCcid() + "'";
		int success = driver.delete(query);
		return success;
	}
	
	public static int setValid(Boolean valid, Payment payment) {
		int value;
		if(valid)
		{
			value = 1;
		}
		else
			value = 0;
		String query = "UPDATE payment SET valid = '"+ value + "' WHERE ccid = '"+payment.getCcid() +"'; ";
		int success = 0;
		success = driver.create(query);
		return success;
	}
	/**
	 * Search the items in books by searchParam
	 * @param searchParam
	 * @param searchItem
	 * @return an ArrayList<Payment>
	 */
	public static ArrayList<Payment> searchPayment(String searchParam, String searchItem){
		ArrayList<Payment> search_results = new ArrayList<Payment>();
		String query = "select * from payment where " + searchParam+ "= '" + searchItem+"' AND valid = 1";
		ResultSet rs = driver.retrieve(query);
		Payment payment = new Payment();
		if(rs != null){
			try {
				while(rs.next()){
					payment.setCcid(rs.getInt("ccid"));
					payment.setCc_number(rs.getString("cc_number"));
					payment.setExpdate(rs.getString("expdate"));
					payment.setType(rs.getString("type"));
					payment.setUser(rs.getInt("user"));
					payment.setAid(rs.getInt("aid"));
					search_results.add(payment);
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
	
	public static String getUserCardID(String searchParam, int searchItem){
		ArrayList<User> search_results = new ArrayList<User>();
		String query = "select * from payment where " + "uid"+ "= '" + searchItem + "'";
		System.out.println("*************************");
		
		System.out.print(query);
		ResultSet rs = driver.retrieve(query);
		if(rs != null){
			try {
				while(rs.next()){
					return rs.getString("ccid");
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

