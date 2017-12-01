package DatabaseAccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import Entities.Promo;
import Entities.Supplier;
public class SupplierDBManager {
	private static Driver driver = new Driver();
	/**
	 * add a supplier to database
	 * @return -1 if failure and 1 if success
	 */
	public static int Supplier (Supplier supplier) {
		String query = "INSERT INTO `bookz`.`supplier` (supplierid, name, uid)";
		query += "VALUES ('" +  supplier.getSupplierid() + "', '";
		query += supplier.getName() + "', '"+ supplier.getUid() + "')";
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
	
	/*public static int setName(String value, Supplier supplier) {
		String query = "UPDATE promo SET enddate = '"+ value + "' WHERE code = '"+ promo.getCode() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}*/
	
	/**
	 * Search the items in supplier by searchParam
	 * @param searchParam
	 * @param searchItem
	 * @return
	 */
	public static ArrayList<Supplier> searchSupplier(String searchParam, String searchItem){
		ArrayList<Supplier> search_results = new ArrayList<Supplier>();
		String query = "select * from supplier where " + searchParam+ "= '" + searchItem+"'";
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