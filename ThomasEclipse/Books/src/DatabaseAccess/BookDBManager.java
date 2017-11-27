package DatabaseAccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entities.Book;
public class BookDBManager {
	private static Driver driver = new Driver();
	/**
	 * add book to database
	 * @return -1 if failure and 1 if success
	 */
	public static int addBook(Book book) {
		String query = "INSERT INTO book (isbn, title, author, price, description, image, genre, rating, quantity, supplier, threshold) ";
		query += "VALUES ('" + book.getISBN() + "', '" + book.getTitle() + "', '";
		query += book.getAuthor() + "', '" + book.getPrice() + "', '" + book.getDescription() + "', ";
		query += "'0', " + "'0', '"+ book.getRating() + "', '" + book.getQuantity() + "', ";
		query += "'0', '" + book.getThreshold() + "')";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	/**
	 * get book from database
	 * @return -1 if failure and 1 if success
	 */
	public static int removeBook(Book book) {
		String query = "DELETE from book where isbn = " + book.getISBN();
		int success = driver.delete(query);
		return success;
	}
	/**
	 * Search the items in books by searchParam
	 * @param searchParam
	 * @param searchItem
	 * @return
	 */
	public static ArrayList<Book> searchBooks(String searchParam, String searchItem){
		ArrayList<Book> search_results = new ArrayList<Book>();
		String query = "select * from book where " + searchParam+ " = '" + searchItem + "'";
		ResultSet rs = driver.retrieve(query);
		Book book = new Book();
		if(rs != null){
			try {
				while(rs.next()){
					book.setISBN(rs.getInt("ISBN"));
					book.setTitle(rs.getString("title"));
					book.setAuthor(rs.getString("author"));
					book.setDescription(rs.getString("description"));
					book.setPrice(rs.getDouble("price"));
					book.setQuantity(rs.getInt("quantity"));
					book.setThreshold(rs.getInt("threshold"));
					search_results.add(book);
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
