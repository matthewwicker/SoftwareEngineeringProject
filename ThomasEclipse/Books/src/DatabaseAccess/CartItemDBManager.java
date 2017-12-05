package DatabaseAccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import Entities.Book;
import Entities.Cart;
import Entities.CartItem;
public class CartItemDBManager {
	private static Driver driver = new Driver();
	/**
	 * add a cartItem to database
	 * @return -1 if failure and 1 if success
	 */
	public static int CartItem (CartItem cart) {
		String query = "INSERT INTO `bookz`.`cart` (isbn, numbooks, cartid, price)";
		query += "VALUES ('" + cart.getISBN() + "', '";
		query += cart.getNumBooks() + "', '" + cart.getCartId() + "', '" + cart.getPrice() + "')";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	/**
	 * remove a cartItem from database
	 * @return -1 if failure and 1 if success
	 */
	public static int removeCartItem(int cartid, int isbn) {
		String query = "DELETE from `bookz`.`caritem` WHERE isbn = '" + isbn + "' and cartid = " + cartid;
		int success = driver.delete(query);
		return success;
	}
	public static int addCartItem(Book book, int uid) {
		String query = "INSERT INTO caritem (isbn, numbooks, cartid) ";
		query += "VALUES ('" + book.getISBN() + "', '1', ' "+ uid +  "')";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	public static int changeQuantity(int cartid, int isbn, int change) {
		String query = "UPDATE caritem SET numbooks = '" + change + "' WHERE isbn = '" + isbn + "' and cartid = '" + cartid + "'";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	public static int addCartItem(CartItem cartitem, int cartid) {
		String query = "INSERT INTO caritem (isbn, numbooks, cartid) ";
		query += "VALUES ('" + cartitem.getISBN() + "', '" + cartitem.getNumBooks() + "', ' "+ cartid +  "')";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	/**
	 * Search the items in cartitems by searchPara
	 * m25.00
	 * @param searchParam
	 * @param searchItem
	 * @return
	 */
	public static ArrayList<CartItem> searchCartItem(String searchParam, String searchItem){
		ArrayList<CartItem> search_results = new ArrayList<CartItem>();
		String query = "select * from caritem where " + searchParam+ "= '" + searchItem+"' AND valid = 1";
		ResultSet rs = driver.retrieve(query);
		CartItem cart = new CartItem();
		Book tempBook = new Book();
		if(rs != null){
			try {
				while(rs.next()){
					cart.setISBN(rs.getInt("ISBN"));
					cart.setNumBooks(rs.getInt("numbooks"));
					cart.setCartId(rs.getInt("cartid"));
					tempBook = BookDBManager.searchBooks("isbn", cart.getISBN()).get(0);
					cart.setPrice(tempBook.getPrice());
					cart.setTitle(tempBook.getTitle());
					cart.setNumBooks(rs.getInt("numbooks"));
					cart.setTotal();
					search_results.add(cart);
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
	
public static ArrayList<CartItem> searchCartItem(String searchParam, int searchItem){
	ArrayList<CartItem> search_results = new ArrayList<CartItem>();
	String query = "select * from caritem where " + searchParam+ "= '" + searchItem+"'";
	ResultSet rs = driver.retrieve(query);
	Book tempBook = new Book();
	if(rs != null){
		try {
			while(rs.next()){

				CartItem cart = new CartItem();
				cart.setISBN(rs.getInt("ISBN"));
				cart.setNumBooks(rs.getInt("numbooks"));
				cart.setCartId(rs.getInt("cartid"));
				tempBook = BookDBManager.searchBooks("isbn", cart.getISBN()).get(0);
				cart.setPrice(tempBook.getPrice());
				cart.setTitle(tempBook.getTitle());
				cart.setTotal();
				search_results.add(cart);
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

public static int setValid(Boolean valid, CartItem cart) {
	int value;
	if(valid)
	{
		value = 1;
	}
	else
		value = 0;
	String query = "UPDATE caritem SET valid = '"+ value + "' WHERE cartid = '"+cart.getCartId() +"'; ";
	int success = 0;
	success = driver.create(query);
	return success;
}

}