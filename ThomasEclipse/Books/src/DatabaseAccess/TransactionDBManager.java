package DatabaseAccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;



import Entities.Transaction;
public class TransactionDBManager {
	private static Driver driver = new Driver();
	/**
	 * add a transaction to database
	 * @return -1 if failure and 1 if success
	 */
	public static int Transaction (Transaction transaction) {
		String query = "INSERT INTO `bookz`.`transaction` (transactionid, cartid, ccid, amount, date, promoid)";
		query += "VALUES ('" +  transaction.getTransactioncol() + "', '" + transaction.getCartid() + "', '";
		query += transaction.getCcid() + "', '"+ transaction.getAmount() + "', '" + transaction.getDate() + "')";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	/**
	 * remove a transaction from database
	 * @return -1 if failure and 1 if success
	 */
	public static int removeTransactiton(Transaction transaction) {
		String query = "DELETE from `bookz`.`transaction` WHERE transactionid = " + transaction.getTransactioncol();
		int success = driver.delete(query);
		return success;
	}
	
	/**
	 * Search the items in transaction by searchParam
	 * @param searchParam
	 * @param searchItem
	 * @return
	 */
	public static ArrayList<Transaction> searchTransaction(String searchParam, String searchItem){
		ArrayList<Transaction> search_results = new ArrayList<Transaction>();
		String query = "select * from transaction where " + searchParam+ "= '" + searchItem+"'";
		ResultSet rs = driver.retrieve(query);
		Transaction transaction = new Transaction();
		if(rs != null){
			try {
				while(rs.next()){
					transaction.setTransactioncol(rs.getInt("transactionid"));
					transaction.setCartid(rs.getInt("cartid"));
					transaction.setCcid(rs.getInt("ccid"));
					transaction.setAmount(rs.getDouble("amount"));
					transaction.setDate(rs.getString("date"));
					search_results.add(transaction);
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