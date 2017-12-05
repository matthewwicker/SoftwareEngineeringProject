package DatabaseAccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import Entities.Cart;
import Entities.Promo;
import Entities.Supplier;
public class SupplierDBManager {
	private static Driver driver = new Driver();
	/**
	 * add a supplier to database
	 * @return -1 if failure and 1 if success
	 */
	public static int addSupplier (Supplier supplier) {
		String query = "INSERT INTO `bookz`.`supplier` (supplierid, name, uid, contactname, contactcell, contactbusiness)";
		query += "VALUES ('" +  supplier.getSupplierid() + "', '";
		query += supplier.getName() + "', '"+ supplier.getUid() + "', '"
		+ supplier.getContactName() + "', ' " + supplier.getContactCell()+ "', '" +supplier.getContactBuisness()+ "')";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	/**
	 * remove a supplier from database
	 * @return -1 if failure and 1 if success
	 */
	public static int removeSupplier(Supplier supplier) {
		String query = "DELETE from `bookz`.`supplier` WHERE supplierid = " + supplier.getSupplierid();
		int success = driver.delete(query);
		return success;
	}
	
	public static int setName(String value, Supplier supplier) {
		String query = "UPDATE promo SET name = '"+ value + "' WHERE supplierid = '"+ supplier.getSupplierid() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
	public static int setUid(String value, Supplier supplier) {
		String query = "UPDATE promo SET uid = '"+ value + "' WHERE supplierid = '"+ supplier.getSupplierid() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
	public static int setValid(Boolean valid, Supplier supplier) {
		int value;
		if(valid)
		{
			value = 1;
		}
		else
			value = 0;
		String query = "UPDATE supplier SET valid = '"+ value + "' WHERE supplierid = '"+ supplier.getSupplierid()+"'; ";
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
	/**
	 * Search the items in supplier by searchParam
	 * @param searchParam
	 * @param searchItem
	 * @return
	 */
	public static ArrayList<Supplier> searchSupplier(String searchParam, String searchItem){
		ArrayList<Supplier> search_results = new ArrayList<Supplier>();
		String query = "select * from supplier where " + searchParam+ "= '" + searchItem+"' valid = 1";
		ResultSet rs = driver.retrieve(query);
		Supplier supplier = new Supplier();
		if(rs != null){
			try {
				while(rs.next()){
					supplier.setSupplierid(rs.getInt("supplierid"));
					supplier.setName(rs.getString("name"));
					supplier.setUid(rs.getInt("uid"));
					search_results.add(supplier);
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
	public static ArrayList<Supplier> searchSupplier(String query){
		ArrayList<Supplier> search_results = new ArrayList<Supplier>();
		ResultSet rs = driver.retrieve(query);
		Supplier supplier = new Supplier();
		if(rs != null){
			try {
				while(rs.next()){
					supplier.setSupplierid(rs.getInt("supplierid"));
					supplier.setName(rs.getString("name"));
					supplier.setUid(rs.getInt("uid"));
					search_results.add(supplier);
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