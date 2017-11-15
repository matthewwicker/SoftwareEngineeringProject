package DatabaseAccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entities.Promo;
public class PromoDBManager {
	private static Driver driver = new Driver();
	/**
	 * add book to database
	 * @return -1 if failure and 1 if success
	 */
	public static int addPromo (Promo promo) {
		String query = "INSERT INTO `bookz`.`promo` (code, isbn, percentoff, startdate, enddate)";
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
		String query = "DELETE from `bookz`.`promo` WHERE code = " + promo.getCode();
		int success = driver.delete(query);
		return success;
	}
	
}
