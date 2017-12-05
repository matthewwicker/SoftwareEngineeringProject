/*package Logic;

import java.util.ArrayList;

import Entities.Promo;
import Entities.Book;
import Entities.Transaction;
import Entities.User;
import Entities.Cart;
import Entities.CartItem;

import DatabaseAccess.PromoDBManager;
import DatabaseAccess.BookDBManager;
import DatabaseAccess.TransactionDBManager;
import DatabaseAccess.UserDBManager;
import DatabaseAccess.CartDBManager;
import DatabaseAccess.CartItemDBManager;

public class reports {
	
	public ArrayList<String> promoUse()
	{
		ArrayList<String> report = new ArrayList<String>();
		report.add("Total ");
		ArrayList<Promo> promos = PromoDBManager.searchPromo();
		for(int i = 0; i < promos.size(); i++)
		{
			ArrayList<Transaction> trans = TransactionDBManager.searchTransaction("promoid", promos.get(i).getCode());
			report.add(""+ promos.get(i).getCode()+"_" + trans.size());
		}
		return report;
	}
	
	public ArrayList<String> booksPurchased()
	{
		ArrayList<String> report = new ArrayList<String>();
		report.add("Total ");
		ArrayList<Book> books = BookDBManager.searchBooks("select * from books");
		for(int i = 0; i < books.size(); i++) 
		{
			int total = 0;
			String query = "SELECT * FROM caritem INNER JOIN transaction ON cartid = cartid WHERE isbn = '" + books.get(i).getISBN() +"'";
			ArrayList<CartItem> items = CartItemDBManager.searchCartItem(query);
			total = total + items.get(i).getNumBooks();
			report.add(""+books.get(i).getISBN()+"_"+total);
		}
		return report;
	}
	
	public ArrayList<String> bookSales()
	{
		ArrayList<String> report = new ArrayList<String>();
		report.add("Total ");
		ArrayList<Book> books = BookDBManager.searchBooks("select * from books");
		for(int i = 0; i < books.size(); i++) 
		{
			double total = 0;
			String query = "SELECT * FROM ((promo INNER JOIN transaction ON promo.code = transaction.promoid) INNER JOIN caritem ON transaction.cartid = caritem.cartid) WHERE isbn = " + books.get(i).getISBN();
			ArrayList<CartItem> items = CartItemDBManager.searchCartItem(query);
			ArrayList<Promo> promos = PromoDBManager.searchPromo(query);
			for(int j = 0; j < items.size(); j++)
			{
				total = total + ((items.get(j).getNumBooks() * books.get(i).getPrice()) * promos.get(j).getPercentOff());
			}
			total = total + items.get(i).getNumBooks();
			report.add(""+books.get(i).getISBN()+"_"+total);
		}
		return report;
	}
	
	public ArrayList<String> currentShipments()
	{
		ArrayList<String> report = new ArrayList<String>();
		report.add("Total ");
		
		String query = "SELECT * FROM transaction";
		ArrayList<Transaction> trans = TransactionDBManager.searchTransaction(query);
		
		for(int i = 0; i < trans.size(); i++)
		{
			query = "SELECT * FROM caritem WHERE cartid = "+trans.get(i).getCartid();
			ArrayList<CartItem> items = CartItemDBManager.searchCartItem(query);
			int total = 0;
			for(int j = 0; j < items.size(); j++)
			{
				total = total + items.get(i).getNumBooks();
			}
			report.add(trans.get(i).getCartid()+"_"+total+"_"+trans.get(i).getStatus());
		}
		return report;
	}
	
	public ArrayList<String> userInfo()
	{
		ArrayList<String> report = new ArrayList<String>();

		String query = "SELECT * FROM users";
		ArrayList<User> users = UserDBManager.searchUsers(query);
		int total = users.size();
		report.add("Total number of users: " + total);

		query = "SELECT * FROM users WHERE suspended = 1";
		report = userInfoStruct(report,query,"Suspended");
		
		query = "SELECT * FROM users WHERE autorized = 0";
		report = userInfoStruct(report,query,"Require Authorization");
		
		query = "SELECT * FROM users WHERE type = p";
		report = userInfoStruct(report,query,"Suppliers");
		
		query = "SELECT * FROM users WHERE type = s";
		report = userInfoStruct(report,query,"Shippers");
		
		return report;	
	}
	
	private ArrayList<String> userInfoStruct(ArrayList<String> report, String query, String title)
	{
		ArrayList<User> users = UserDBManager.searchUsers(query);
		report.add(title+"_User ID_Email_First Name_Last Name_Phone Number");
		for(int i = 0; i < users.size(); i++)
		{
			report.add(" _"+users.get(i).getUid()+"_"+users.get(i).getEmail()+"_"+users.get(i).getFname()+"_"+users.get(i).getLname()+"_"+users.get(i).getPhoneNumber());
		}
		return report;
	}
	
	public ArrayList<String> sBookSales(int id)
	{
		ArrayList<String> report = new ArrayList<String>();
		report.add("Total ");
		ArrayList<Book> books = BookDBManager.searchBooks("select * from books WHERE supplier = "+ id);
		for(int i = 0; i < books.size(); i++) 
		{
			double total = 0;
			String query = "SELECT * FROM ((promo INNER JOIN transaction ON promo.code = transaction.promoid) INNER JOIN caritem ON transaction.cartid = caritem.cartid) WHERE isbn = " + books.get(i).getISBN();
			ArrayList<CartItem> items = CartItemDBManager.searchCartItem(query);
			ArrayList<Promo> promos = PromoDBManager.searchPromo(query);
			for(int j = 0; j < items.size(); j++)
			{
				total = total + ((items.get(j).getNumBooks() * books.get(i).getPrice()) * promos.get(j).getPercentOff());
			}
			total = total + items.get(i).getNumBooks();
			report.add(""+books.get(i).getISBN()+"_"+total);
		}
		return report;
	}
	
	public ArrayList<String> sBookInventory(int id)
	{
		ArrayList<String> report = new ArrayList<String>();
		report.add("Total ");
		String query = "SELECT * FROM books WHERE supplier = " + id;
		ArrayList<Book> books = BookDBManager.searchBooks(query);
		for(int i = 0; i < books.size();i++)
		{
			report.add(books.get(i).getISBN()+"_"+books.get(i).getQuantity()+"_"+books.get(i).getThreshold());
		}
		return report;
	}
	
}*/
