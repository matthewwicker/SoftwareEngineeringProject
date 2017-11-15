package Logic;
import Entities.*;

import java.sql.SQLException;
import java.util.ArrayList;

import DatabaseAccess.*;

public class logic {
	private static UserDBManager UManager = new UserDBManager();
	private static AddressDBManager AManager = new AddressDBManager();
	private static PaymentDBManager PManager = new PaymentDBManager();
	
	public int addUser(User user, Address address, Payment payment){
		int success = UManager.addUser(user);
		if(success == -1){
			return -1;
		}
		ArrayList<User> newUser = UManager.searchUsers("email", user.getEmail());
		int userId = newUser.get(0).getUid();
		address.setUid(userId);
		
		success = AManager.addAddress(address);
		if(success == -1){
			return -1;
		}
		ArrayList<Address> newAddress = AManager.searchAddresses("uid", userId);
		int addressId = newAddress.get(0).getAid();
		payment.setUser(userId);
		payment.setAid(addressId);
		
		success = PManager.addPayment(payment);
		if(success == -1){
			return -1;
		}
		return 0;
	}
}
