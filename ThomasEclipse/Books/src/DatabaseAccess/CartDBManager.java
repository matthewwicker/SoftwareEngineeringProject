package DatabaseAccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;



import Entities.Cart;
public class CartDBManager {
	private static Driver driver = new Driver();
	/**
	 * add a cart to database
	 * @return -1 if failure and 1 if success
	 */
	public static int Cart (Cart cart) {
		String query = "INSERT INTO `bookz`.`cart` (cartid, uid)";
		query += "VALUES ('" +  cart.getCartId() + "', '";
		query += cart.getUId() + "')";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	/**
	 * remove a cart from database
	 * @return -1 if failure and 1 if success
	 */
	public static int removeCart(Cart cart) {
		String query = "DELETE from `bookz`.`cart` WHERE cartid = " + cart.getCartId();
		int success = driver.delete(query);
		return success;
	}
	
	/**
	 * Search the items in cart by searchParam
	 * @param searchParam
	 * @param searchItem
	 * @return
	 */
	public static ArrayList<Cart> searchCart(String searchParam, String searchItem){
		ArrayList<Cart> search_results = new ArrayList<Cart>();
		String query = "select * from cart where " + searchParam+ "= '" + searchItem+"'";
		ResultSet rs = driver.retrieve(query);
		Cart cart = new Cart();
		if(rs != null){
			try {
				while(rs.next()){
					cart.setCartId(rs.getInt("cartid"));
					cart.setUId(rs.getInt("uid"));
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