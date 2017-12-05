package Logic;
import Entities.*;

import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import DatabaseAccess.*;
import EmailNotifications.SendEmail;

public class logic {
	private static UserDBManager UManager = new UserDBManager();
	private static AddressDBManager AManager = new AddressDBManager();
	private static PaymentDBManager PManager = new PaymentDBManager();
	private static PromoDBManager PrManager = new PromoDBManager();
	private static BookDBManager BManager = new BookDBManager();
	
	public int validateUser(User u, String validation){
		System.out.println("In validate method, with code: xxxxxx" + validation + "xxxxx");
		System.out.println("Trying to validate: " + u.getEmail());
		ArrayList<User> newUser = UManager.searchUsers("email", u.getEmail());
		System.out.println("Size of returned list: " + newUser.size());
		System.out.println("Validation code should be: xxxxx" + newUser.get(0).getUid() + "xxxxx");
		if(validation.equals(Integer.toString(newUser.get(0).getUid()))){
			Date dateInQuestion = u.getSignupdate();
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateInQuestion);
			cal.add(Calendar.HOUR, 48);
			Date futureDate = cal.getTime();
			
			
			if(dateInQuestion.after(futureDate)) {
				System.out.println("failure due to time constraint");
				return 0;
			}
			Driver driver = new Driver();
			String query = "UPDATE users SET verify = '" + 1 +"' WHERE uid = "+ newUser.get(0).getUid();
			int value = driver.update(query);
			System.out.println("VALIDATED CORRECTLY");
			SendEmail sender = new SendEmail();
			sender.actuallySendEmail(u, sender.ACCOUNT_VERIFICATION);
			return 1;
		}
		System.out.println("VALIDATED FAILED");
		return 0;
	}
	public int addBook(Book book) {
		int success = BManager.addBook(book);
		return success;
	}
	public User authorizeUser(User u){
		ArrayList<User> newUser = new ArrayList<User>();
		User user = new User(); 
				
		try {
			newUser =  UManager.searchUsers("email", u.getEmail());
			user = newUser.get(0);
		}  catch (Exception e) {
			newUser = UManager.searchUsers("uid",u.getEmail());
			user = newUser.get(0);
		}
		
		
		if(u.getPassword().equals(newUser.get(0).getPassword())){
			System.out.println("SUCCESS TO SIGN IN");
			ArrayList<Address> adds = AddressDBManager.searcShippingAddress("uid", Integer.toString(user.getUid()), "0");
			if (adds.size() > 0) {
				user.setShipadd(adds.get(adds.size()-1).getAddress());
			}
			else {
				user.setShipadd("");
			}
			adds = AddressDBManager.searcShippingAddress("uid", Integer.toString(user.getUid()), "1");
			if (adds.size() > 0) {
				user.setBilladd(adds.get(adds.size()-1).getAddress());
			}
			else {
				user.setBilladd("");
			}
			return user;
		}
		else {
			ArrayList<User> otherUsers = UManager.searchUsers("uid", Integer.toString(u.getUid()));
			if(u.getPassword().equals(otherUsers.get(0).getPassword())){
				System.out.println("SUCCESS TO SIGN IN");
				user = otherUsers.get(0);
				ArrayList<Address> adds = AddressDBManager.searcShippingAddress("uid",  Integer.toString(user.getUid()), "0");
				if (adds.size() > 0) {
					user.setShipadd(adds.get(adds.size()-1).getAddress());
				}
				else {
					user.setShipadd("");
				}
				adds = AddressDBManager.searcShippingAddress("uid",  Integer.toString(user.getUid()), "1");
				if (adds.size() > 0) {
					user.setBilladd(adds.get(adds.size()-1).getAddress());
				}
				else {
					user.setBilladd("");
				}
				return user;			}
		}
		return null;
	}
	
	public int addPromo(Promo promo) {
		return PrManager.addPromo(promo);
	}
	
	public int removePromo(Promo promo) {
		return PrManager.removePromo(promo);
	}
	
	public int changePromoSetting(String value, User user) {
		return UManager.setPromoPref(value, user);
	}
	public int changeSuspension(String value, User user) {
		return UManager.setSuspension(value, user);
	}
	public int changeStatus(String value, String user) {
		return UManager.setStatus(value, user);
	}
	public int changePassword(String value, User user) {
		return UManager.setPassword(value, user);
	}
	
	public int addUser(User user, Address address, Payment payment){
		int success = UManager.addUser(user);
		if(success == -1){
			return -1;
		}
		ArrayList<User> newUser = UManager.searchUsers("email", user.getEmail());
		int userId = newUser.get(0).getUid();
		address.setUid(userId);
		
		success = AManager.addAddress(address);
		address.setBilling(0);
		success = AManager.addAddress(address);
		if(success == -1){
			return -1;
		}
		ArrayList<Address> newAddress = AManager.searcAddress("uid", ""+userId);
		int addressId = newAddress.get(0).getAid();
		payment.setUser(userId);
		payment.setAid(addressId);
		
		success = PManager.addPayment(payment);
		if(success == -1){
			return -1;
		}
		return 0;
	}
	public int editAccount(User user, Address address1, Address address2){
		
		//int success = UManager
		
		return 0;
	}
}
