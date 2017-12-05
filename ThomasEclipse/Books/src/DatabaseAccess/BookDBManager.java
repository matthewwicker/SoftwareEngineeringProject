package DatabaseAccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entities.Address;
import Entities.Book;
public class BookDBManager {
	private static Driver driver = new Driver();
	/**
	 * add book to database
	 * @return -1 if failure and 1 if success
	 */
	public static int addBook(Book book) {
		String query = "INSERT INTO book (isbn, title, author, price, description, image, genre, rating, quantity, supplier, threshold, edition, publisher, publicationyear, buyingprice) ";
		query += "VALUES ('" + book.getISBN() + "', '" + book.getTitle() + "', '";
		query += book.getAuthor() + "', '" + book.getPrice() + "', '" + book.getDescription() + "', ";
		query += "'"+book.getImage()+"', " + "'"+book.getGenre()+"', '"+ book.getRating() + "', '" + book.getQuantity() + "', ";
		query += "'"+ book.getSupplier() + "', '" + book.getThreshold() + "', '"+ book.getEdition()+"', '"+ book.getPublisher()+"', '";
		query += book.getPublicationYear()+"', '"+ book.getBuyingPrice()+ "')";
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
		String query = "select * from book where " + searchParam+ " LIKE '%" + searchItem + "%' AND valid = 1";
		ResultSet rs = driver.retrieve(query);
		if(rs != null){
			try {
				while(rs.next()){
					Book book = new Book();

					book.setISBN(rs.getInt("ISBN"));
					book.setTitle(rs.getString("title"));
					book.setAuthor(rs.getString("author"));
					book.setPrice(rs.getDouble("price"));
					book.setDescription(rs.getString("description"));
					book.setImage(rs.getString("image"));
					book.setGenre(rs.getString("genre"));
					book.setRating(rs.getInt("rating"));
					book.setQuantity(rs.getInt("quantity"));
					book.setSupplier(rs.getInt("supplier"));
					book.setThreshold(rs.getInt("threshold"));
					book.setEdition(rs.getInt("edition"));
					book.setPublisher(rs.getString("publisher"));
					book.setPublicationYear(rs.getInt("publicationYear"));
					book.setBuyingPrice(rs.getDouble("buyingprice"));
					book.setSellingPrice(rs.getDouble("sellingPrice"));
					search_results.add(book);
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
	
	public static ArrayList<Book> searchBooks(String searchParam, int searchItem){
		ArrayList<Book> search_results = new ArrayList<Book>();
		String query = "select * from book where " + searchParam+ " = '" + searchItem + "' AND valid = 1";
		ResultSet rs = driver.retrieve(query);
		if(rs != null){
			try {
				while(rs.next()){
					Book book = new Book();

					book.setISBN(rs.getInt("ISBN"));
					book.setTitle(rs.getString("title"));
					book.setAuthor(rs.getString("author"));
					book.setDescription(rs.getString("description"));
					book.setImage(rs.getString("image"));
					book.setGenre(rs.getString("genre"));
					book.setPrice(rs.getDouble("price"));
					book.setRating(rs.getInt("rating"));
					book.setQuantity(rs.getInt("quantity"));
					book.setThreshold(rs.getInt("threshold"));
					book.setSupplier(rs.getInt("supplier"));
					search_results.add(book);
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

	public static int setTitle(String value, Book book) {
		String query = "UPDATE book SET title = '"+ value + "' WHERE ISBN = '"+book.getISBN() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
	public static int setAuthor(String value, Book book) {
		String query = "UPDATE book SET author = '"+ value + "' WHERE ISBN = '"+book.getISBN() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
	public static int setDescription(String value, Book book) {
		String query = "UPDATE book SET description = '"+ value + "' WHERE ISBN = '"+book.getISBN() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
	public static int setPrice(String value, Book book) {
		String query = "UPDATE book SET price = '"+ value + "' WHERE ISBN = '"+book.getISBN() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
	public static int setQuantity(String value, Book book) {
		String query = "UPDATE book SET quantity = '"+ value + "' WHERE ISBN = '"+book.getISBN() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	public static int setQuantityByISBN(String value, String isbn) {
		String query = "UPDATE book SET quantity = '"+ value + "' WHERE ISBN = '"+isbn +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}	
	public static int setThreshold(String value, Book book) {
		String query = "UPDATE book SET threshold = '"+ value + "' WHERE ISBN = '"+book.getISBN() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
	public static int setImage(String value, Book book) {
		String query = "UPDATE book SET image = '"+ value + "' WHERE ISBN = '"+book.getISBN() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
	public static int setGenre(String value, Book book) {
		String query = "UPDATE book SET genre = '"+ value + "' WHERE ISBN = '"+book.getISBN() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
	public static int setSupplier(String value, Book book) {
		String query = "UPDATE book SET supplier = '"+ value + "' WHERE ISBN = '"+book.getISBN() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
	public static int setEdition(String value, Book book) {
		String query = "UPDATE book SET edition = '"+ value + "' WHERE ISBN = '"+book.getISBN() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
	public static int setPublisher(String value, Book book) {
		String query = "UPDATE book SET publisher = '"+ value + "' WHERE ISBN = '"+book.getISBN() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
	public static int setPublicationYear(String value, Book book) {
		String query = "UPDATE book SET publicationyear = '"+ value + "' WHERE ISBN = '"+book.getISBN() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
	public static int setBuyingPrice(String value, Book book) {
		String query = "UPDATE book SET buyingprice = '"+ value + "' WHERE ISBN = '"+book.getISBN() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
	public static int setSellingPrice(String value, Book book) {
		String query = "UPDATE book SET sellingprice = '"+ value + "' WHERE ISBN = '"+book.getISBN() +"'; ";
		System.out.println(query);
		int success = 0;
		success = driver.create(query);
		return success;
	}
	
	public static int setValid(Boolean valid, Book book) {
		int value;
		if(valid)
		{
			value = 1;
		}
		else
			value = 0;
		String query = "UPDATE book SET sellingprice = '"+ value + "' WHERE ISBN = '"+book.getISBN() +"'; ";
		int success = 0;
		success = driver.create(query);
		return success;
	}
}