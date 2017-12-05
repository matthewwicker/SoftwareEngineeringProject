package DatabaseAccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entities.Cart;
import Entities.Payment;
import Entities.Promo;
public class PromoDBManager {
	private static Driver driver = new Driver();
	/**
	 * add book to database
	 * @return -1 if failure and 1 if success
	 */
	public static int addPromo (Promo promo) {
		String query = "INSERT INTO `bookz`.`promo` (code, percentoff, startdate, enddate)";
		query += "VALUES ('" + promo.getCode() + "', '" + promo.getISBN() + "', '";
		query += promo.getPercentOff() + "', '" + promo.getStartDate() + "', '" + promo.getEndDate() + "')";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	/**
	 * get book from database
	 * @return -1 if failure and 1 if success
	 */
	public static int removePromo(Promo promo) {
		String query = "DELETE from `bookz`.`promo` WHERE code = '" + promo.getCode() + "'";
		int success = driver.delete(query);
		return success;
	}
	
	public static int setCode(String value, Promo promo) {
		String query = "UPDATE promo SET code = '"+ value + "' WHERE code = '"+promo.getCode() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
		
	public static int setPercentOff(String value, Promo promo) {
		String query = "UPDATE promo SET percentoff = '"+ value + "' WHERE code = '"+promo.getCode() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
	public static int setStartDate(String value, Promo promo) {
		String query = "UPDATE promo SET startdate = '"+ value + "' WHERE code = '"+promo.getCode() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
	public static int setEndDate(String value, Promo promo) {
		String query = "UPDATE promo SET enddate = '"+ value + "' WHERE code = '"+promo.getCode() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
	public static int setValid(Boolean valid, Promo promo) {
		int value;
		if(valid)
		{
			value = 1;
		}
		else
			value = 0;
		String query = "UPDATE promo SET valid = '"+ value + "' WHERE code = '"+promo.getCode() +"'; ";
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
		/**
		 * Search the items in promos by searchParam
		 * @param searchParam
		 * @param searchItem
		 * @return
		 */
		public static ArrayList<Promo> searchPromo(String searchParam, String searchItem){
			ArrayList<Promo> search_results = new ArrayList<Promo>();
			String query = "select * from promo where " + searchParam+ "= '" + searchItem+"' AND valid = 1";
			System.out.println(query);
			try {
			ResultSet rs = driver.retrieve(query);
			if(rs != null){
				try {
					while(rs.next()){
						Promo promo = new Promo();
						promo.setCode(rs.getString("code"));
						promo.setPercentOff(rs.getDouble("percentoff"));
						promo.setEndDate(rs.getString("enddate"));
						promo.setStartDate(rs.getString("startdate"));
						search_results.add(promo);
					}
					driver.disconnect();
				}
				 catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
			catch(Exception e) {e.printStackTrace();}
			System.out.println("HELLO???????????????????");
			System.out.println(search_results.size());
			return search_results;
		}
		public static ArrayList<Promo> searchPromo(String query){
			ArrayList<Promo> search_results = new ArrayList<Promo>();
			System.out.println(query);
			try {
			ResultSet rs = driver.retrieve(query);
			if(rs != null){
				try {
					while(rs.next()){
						Promo promo = new Promo();
						promo.setCode(rs.getString("code"));
						promo.setPercentOff(rs.getDouble("percentoff"));
						promo.setEndDate(rs.getString("enddate"));
						promo.setStartDate(rs.getString("startdate"));
						search_results.add(promo);
					}
					driver.disconnect();
				}
				 catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
			catch(Exception e) {e.printStackTrace();}
			System.out.println("HELLO???????????????????");
			System.out.println(search_results.size());
			return search_results;
		}
				
	}


