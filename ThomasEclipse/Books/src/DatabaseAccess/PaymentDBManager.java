package DatabaseAccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entities.Address;
import Entities.User;
import Entities.Payment;
public class PaymentDBManager {
	private static Driver driver = new Driver();
	/**
	 * Adds a new payment to the database
	 * @return 0 if failure and 1 if success
	 */
	public static int addPayment(Payment payment)
	{
		String query = "INSERT INTO payment (cc_number, uid, aid) ";
		query += "VALUES ('" + payment.getCc_number() + "', '" + payment.getUser() + "', '"+ payment.getAid()+"')";
		int value = driver.create(query);
		
		return value;
	}
	
	/**
	 * Search the items in books by searchParam
	 * @param searchParam
	 * @param searchItem
	 * @return an ArrayList<Payment>
	 */
	public static ArrayList<Payment> searchPayment(String searchParam, String searchItem){
		ArrayList<Payment> search_results = new ArrayList<Payment>();
		String query = "select * from payment where " + searchParam+ "= " + searchItem;
		ResultSet rs = driver.retrieve(query);
		Payment payment = new Payment();
		if(rs != null){
			try {
				while(rs.next()){
					payment.setCc_number(rs.getInt("cc_number"));
					payment.setUser(rs.getInt("uid"));
					payment.setAid(rs.getInt("aid"));
					search_results.add(payment);
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

