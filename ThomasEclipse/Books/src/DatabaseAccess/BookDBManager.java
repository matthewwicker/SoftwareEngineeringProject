package DatabaseAccess;
import Entities.Book;
public class BookDBManager {
	/**
	 * add book to database
	 * @return -1 if failure and 1 if success
	 */
	public static int addBook(Book book) {
		String query = "INSERT INTO book (isbn, title, author, price, description, image, genre, rating, quantity, supplier, threshold,)";
		query += "values ( " + book.getISBN() + ", " + book.getTitle() + ", ";
		query += book.getAuthor() + ", " + book.getPrice() + ", " + book.getDescription() + ", ";
		query += "0, " + "0, "+ book.getRating() + ", " + book.getQuantity() + ", ";
		query += "0, " + book.getThreshold() + ")";
		int success = 1;
		return -1;
	}
	/**
	 * get book from database
	 * @return -1 if failure and 1 if success
	 */
	public static int removeBook(Book book) {
		String query = "DELETE from book where isbn = " + book.getISBN();
		return -1;
	}
	/**
	 * 
	 * @param searchParam
	 * @param searchItem
	 * @return
	 */
	public static Book[] searchBooks(String searchParam, String searchItem){
		Book[] search_results = null;
		String query = "select * from book where" + searchParam+ "= " + searchItem;
		return search_results;
	}
}
