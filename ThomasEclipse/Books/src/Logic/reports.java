package Logic;

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
	
	public static ArrayList<String> promoUse()
	{
		ArrayList<String> report = new ArrayList<String>();
		report.add(" ");
		ArrayList<Promo> promos = PromoDBManager.searchPromo("SELECT * FROM promo");
		for(int i = 0; i < promos.size(); i++)
		{
			ArrayList<Transaction> trans = TransactionDBManager.searchTransaction("promoid", promos.get(i).getCode());
			report.add(""+ promos.get(i).getCode()+"_" + trans.size());
		}
		return report;
	}
	
	public static ArrayList<String> booksPurchased()
	{   
		ArrayList<String> report = new ArrayList<String>();
		report.add(" ");
		ArrayList<Book> books = BookDBManager.searchBooks("select * from book");
		for(int i = 0; i < books.size(); i++) 
		{   
			int total = 0;
			String query = "SELECT * FROM caritem INNER JOIN transaction ON caritem.cartid = transaction.cartid WHERE isbn = '" + books.get(i).getISBN() +"'";
			ArrayList<CartItem> items = CartItemDBManager.searchCartItem(query);
            for (int j = 0; j < items.size(); j++) {
			total = total + items.get(j).getNumBooks();}
			report.add(""+books.get(i).getISBN()+"_"+total);
		}
		return report;
	}
	
	public static ArrayList<String> bookSales()
	{   
		ArrayList<String> report = new ArrayList<String>();
		report.add(" ");
		ArrayList<Book> books = BookDBManager.searchBooks("select * from book");
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
			report.add(""+books.get(i).getISBN()+"_$"+total);
		}
		return report;
	}
	
	public static ArrayList<String> currentShipments()
	{   
		ArrayList<String> report = new ArrayList<String>();
		report.add(" ");
		String query = "SELECT * FROM transaction";
		ArrayList<Transaction> trans = TransactionDBManager.searchTransaction(query);
		for(int i = 0; i < trans.size(); i++)
		{
			query = "SELECT * FROM caritem WHERE cartid = "+trans.get(i).getCartid();
			ArrayList<CartItem> items = CartItemDBManager.searchCartItem(query);
			int total = 0;
			for(int j = 0; j < items.size(); j++)
			{
				total = total + items.get(j).getNumBooks();
			}
			report.add(trans.get(i).getCartid()+"_"+total+"_"+trans.get(i).getStatus());
		}
		return report;
	}
	
	public static ArrayList<String> userInfo()
	{
		ArrayList<String> report = new ArrayList<String>();
        report.add(" ");
		String query = "SELECT * FROM users";
		ArrayList<User> users = UserDBManager.searchUsers(query);
		int total = users.size();
		report.add("Total number of users: " + total);

		query = "SELECT * FROM users WHERE suspended = 1";
		report = userInfoStruct(report,query,"Suspended");
		
		query = "SELECT * FROM users WHERE verify = 0";
		report = userInfoStruct(report,query,"Require Authorization");
		
		query = "SELECT * FROM users WHERE type = 'p'";
		report = userInfoStruct(report,query,"Suppliers");
		
		query = "SELECT * FROM users WHERE type = 's'";
		report = userInfoStruct(report,query,"Shippers");
		
		return report;	
	}
	
	private static ArrayList<String> userInfoStruct(ArrayList<String> report, String query, String title)
	{
		ArrayList<User> users = UserDBManager.searchUsers(query);
		report.add(" ");
		report.add(title+"_User ID_Email_First Name_Last Name_Phone Number");
		System.out.println(title + users.size());
		for(int i = 0; i < users.size(); i++)
		{
			report.add(" _"+users.get(i).getUid()+"_"+users.get(i).getEmail()+"_"+users.get(i).getFname()+"_"+users.get(i).getLname()+"_"+users.get(i).getPhoneNumber());
		}
		return report;
	}
	
	public static ArrayList<String> sBookSales(int id)
	{
		ArrayList<String> report = new ArrayList<String>();
		report.add(" ");
		ArrayList<Book> books = BookDBManager.searchBooks("select * from book WHERE supplier = "+ id);
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
			report.add(""+books.get(i).getISBN()+"_"+total);
		}
		return report;
	}
	
	public static ArrayList<String> sBookInventory(int id)
	{
		ArrayList<String> report = new ArrayList<String>();
		report.add(" ");
		String query = "SELECT * FROM book WHERE supplier = " + id;
		ArrayList<Book> books = BookDBManager.searchBooks(query);
		for(int i = 0; i < books.size();i++)
		{
			report.add(books.get(i).getISBN()+"_"+books.get(i).getQuantity()+"_"+books.get(i).getThreshold());
		}
		return report;
	}
	
}
