package DatabaseAccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;



import Entities.CartItem;
public class CartItemDBManager {
	private static Driver driver = new Driver();
	/**
	 * add a cartItem to database
	 * @return -1 if failure and 1 if success
	 */
	public static int CartItem (CartItem cart) {
		String query = "INSERT INTO `bookz`.`cart` (isbn, numbooks, cartid)";
		query += "VALUES ('" + cart.getISBN() + "', '";
		query += cart.getNumBooks() + "', '" + cart.getCartId() + "')";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	/**
	 * remove a cartItem from database
	 * @return -1 if failure and 1 if success
	 */
	public static int removeCartItem(CartItem cart) {
		String query = "DELETE from `bookz`.`cartitem` WHERE cartid = " + cart.getCartId();
		int success = driver.delete(query);
		return success;
	}
	
	/**
	 * Search the items in cartitems by searchParam
	 * @param searchParam
	 * @param searchItem
	 * @return
	 */
	public static ArrayList<CartItem> searchCartItem(String searchParam, String searchItem){
		ArrayList<CartItem> search_results = new ArrayList<CartItem>();
		String query = "select * from cartitem where " + searchParam+ "= " + searchItem;
		ResultSet rs = driver.retrieve(query);
		CartItem cart = new CartItem();
		if(rs != null){
			try {
				while(rs.next()){
					cart.setISBN(rs.getInt("ISBN"));
					cart.setNumBooks(rs.getInt("numbooks"));
					cart.setCartId(rs.getInt("cartid"));
					search_results.add(cart);
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
