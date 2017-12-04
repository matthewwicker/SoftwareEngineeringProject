package View;

import java.util.ArrayList;

import java.util.Enumeration;
import java.util.List;
import java.util.Random;

import java.time.LocalDateTime;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import Entities.Address;

import DatabaseAccess.BookDBManager;
import DatabaseAccess.CartDBManager;
import DatabaseAccess.CartItemDBManager;
import DatabaseAccess.PaymentDBManager;
import DatabaseAccess.PromoDBManager;
import DatabaseAccess.SupplierDBManager;
import DatabaseAccess.TransactionDBManager;
import DatabaseAccess.UserDBManager;
import Entities.Book;
import Entities.Payment;
import Entities.Promo;
import Entities.Supplier;
import Entities.Transaction;
import Entities.User;
import Logic.logic;
import Entities.Cart;
import Entities.CartItem;

public class GetHandlers {
	static logic logic = new logic();
	
	protected static User makeUser(HttpServletRequest request) {
		//What object do you want to get out of this interaction?
		User retval = new User();
		Address address = new Address();
		Payment payment = new Payment();
		
		//=========================================================
		//			VALUES YOU NEED FROM THE REQUEST
		//=========================================================
		String fname = "!*!";
		String lname = "!*!";
		String type = "!*!";
		String email = "!*!";
		String phonenumber = "!*!";
		String accounttype = "!*!";
		String username = "!*!";
		String password = "!*!";
		String addressline1 = "!*!";
		String addressline2 = "!*!";
		
		//=========================================================
		//		ITERATING THROUGHT THE ITEMS IN THE REQUEST
		//=========================================================
		Enumeration<String> params = request.getParameterNames(); 
	    while(params.hasMoreElements()){
	    		String paramName = params.nextElement();
	    		System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
	    		switch(paramName) {
	    			case "fname":
	    				fname = request.getParameter(paramName);
	    				break;
	    			case "lname":
	    				lname = request.getParameter(paramName);
	    				break;
	    			case "email":
	    				email = request.getParameter(paramName);
	    				break;
	    			case "type":
	    				type = request.getParameter(paramName);
	    				break;
	    			case "uname":
	    				username = request.getParameter(paramName);
	    				break;
	    			case "cpassword":
	    				password = request.getParameter(paramName);
	    				break;
	    			case "phone":
	    				phonenumber = request.getParameter(paramName);
	    				break;
	    			case "address1":
	    				addressline1 = request.getParameter(paramName);
	    				break;
	    			case "address2":
	    				addressline2 = request.getParameter(paramName);
	    				break;
	    			default:
	    				break;
	    		}
	     }
	    
	    //=========================================================
	    //				PERFORM DATA VALIDATION
	    //=========================================================
		// IM A NAUGHTY BOY (<--I'm disturbed <3java.time.LocalDateTime SNE) AND DIDNT ADD DATA VALIDATION - LOVE, MATT <3
	    if(fname != "!*!") {
	    		retval.setFname(fname);
	    		//System.out.println("set!");
	    	}
	    if(lname != "!*!") {
	    		retval.setLname(lname);
	    		//System.out.println("set!");
	    	}
	    if(email != "!*!") {
	    		retval.setEmail(email);
	    		//System.out.println("set!");
	    	}
	    if(password != "!*!") {
	    		retval.setPassword(password);
	    		//System.out.println("set!");
	    	}
	    if(phonenumber != "!*!") {
    		retval.setPhoneNumber(phonenumber);
    		//System.out.println("set!");
    	}
	    if(accounttype != "!*!") {
	    		retval.setType(accounttype);
	    		//System.out.println("set!");
	    	}
	    else {
	    		retval.setType("u");
	    }
	    if(username != "!*!") {
	    		retval.setUsername(username);
	    		//System.out.println("set!");
	    	}
		if(addressline1 != "!*!" ) {
			address.setAddress(addressline1 + addressline2);
			address.setBilling(1);
			//System.out.println("set!");
		}
		
		
		
		// HERE ARE THE REQUIRED VALUES
		if(fname == "!*!" || lname == "!*!" || email == "!*!" || password == "!*!") {
			return null;
		}
		//=======================-------------------===============
		//			SEND IT TO THE DATA ACCESS LAYER
		//=========================================================
		System.out.println("User email entered: " + retval.getEmail());
		Random rand = new Random();
		logic.addUser(retval, address, payment);
		String userID  = UserDBManager.getUserUserID("email", retval.getEmail());
    	CartDBManager.addCart(Integer.parseInt(userID));
		return retval;
		
	}
	
	
	
	protected static User signIn(HttpServletRequest request) {
		User retval = new User();
		
		String email = "!*!";
		String password = "!*!";
		
		Enumeration<String> params = request.getParameterNames(); 
	    while(params.hasMoreElements()){
	    		String paramName = params.nextElement();
	    		System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
	    		switch(paramName) {
	    			case "email":
	    				email = request.getParameter(paramName);
	    				break;
	    			case "password":
	    				password = request.getParameter(paramName);
	    				break;
	    			default:
	    				break;
	    		}
	     }
	    if(email != "!*!") {
	    		try {
	    			int userid = Integer.parseInt(email);
	    			String e = UserDBManager.getUserEmailFromUID(userid);
	    			retval.setEmail(e);
	    		} catch(Exception e) {
	    			retval.setEmail(email);
	    		}
	    }
	    if(password != "!*!") retval.setPassword(password);
	    
	    
	    if(email == "!*!" || password == "!*!") {
			return null;
		}
		return retval;
	}
	
	protected static User signInHeader(HttpServletRequest request) {
		User retval = new User();
		
		String email = "!*!";
		String password = "!*!";
		
		Enumeration<String> params = request.getParameterNames(); 
	    while(params.hasMoreElements()){
	    		String paramName = params.nextElement();
	    		System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
	    		switch(paramName) {
	    			case "qusername":
	    				email = request.getParameter(paramName);
	    				break;
	    			case "qpassword":
	    				password = request.getParameter(paramName);
	    				break;
	    			default:
	    				break;
	    		}
	     }
	    if(email != "!*!") retval.setEmail(email);
	    if(password != "!*!") retval.setPassword(password);
	    if(email == "!*!" || password == "!*!") {
			return null;
		}
		return retval;
	}
	protected static void updateQuantitySupplied(HttpServletRequest request) {
		User retval = new User();
		
		String isbn = "!*!";
		String qty = "!*!";
		
		Enumeration<String> params = request.getParameterNames(); 
	    while(params.hasMoreElements()){
	    		String paramName = params.nextElement();
	    		System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
	    		switch(paramName) {
	    			case "qty":
	    				isbn = request.getParameter(paramName);
	    				break;
	    			case "isbn":
	    				qty = request.getParameter(paramName);
	    				break;
	    			default:
	    				break;
	    		}
	     }
	    BookDBManager.setQuantityByISBN(qty, isbn);
		return;
	}
	protected static Book getItem(HttpServletRequest request, String isbn) {
		
		Book retval = new Book();
		ArrayList<Book> books = BookDBManager.searchBooks("isbn", isbn.replace(",",""));
        retval = books.get(0); 
    	return retval;
	}
	protected static Promo getPromo(HttpServletRequest request) {
		
		Promo retval = new Promo();
		String promocode = "!*!";
		
		Enumeration<String> params = request.getParameterNames(); 
	    while(params.hasMoreElements()){
	    		String paramName = params.nextElement();
	    		System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
	    		switch(paramName) {
	    			case "promocode":
	    				promocode = request.getParameter(paramName);
	    				break;
	    			default:
	    				break;
	    		}
	     }
	    if(PromoDBManager.searchPromo("code",promocode).size() == 0) {
		    retval.setPercentOff(0.0);
			}
		else {
	    	retval = PromoDBManager.searchPromo("code", promocode).get(0);
	    }
	    return retval;
	}
	protected static Cart putInCart(HttpServletRequest request, Book book, int uid) {
		
		Cart cart = new Cart(); 
		ArrayList<Cart> carts = CartDBManager.searchCart("uid", uid);
        if (carts.size() > 1) {
            cart = carts.get(carts.size() - 1);
        }
        else {

            cart = carts.get(0);
        }
        ArrayList<CartItem> cartitems = CartItemDBManager.searchCartItem("cartid", cart.getCartId());
        CartItem citem = new CartItem();
        boolean exists = false;
        
        for (CartItem cartitem : cartitems) {
        	if (cartitem.getISBN() == book.getISBN()) {
        		citem = cartitem;
        		citem.setNumBooks(citem.getNumBooks()+1);
        		exists = true;
        	}
        }
        if (exists) {
        	CartItemDBManager.changeQuantity(citem.getCartId(), citem.getISBN(), citem.getNumBooks());
        }
        else{
	        int success = CartItemDBManager.addCartItem(book, cart.getCartId());
        }   
    	return cart;
	}
	protected static Cart putItemsInCart(HttpServletRequest request, ArrayList<CartItem> cartitems, int uid) {
		
		Cart cart = new Cart(); 
		ArrayList<Cart> carts = CartDBManager.searchCart("uid", uid);
        if (carts.size() > 1) {
            cart = carts.get(carts.size() - 1);
        }
        else {

            cart = carts.get(0);
        }
        int cartid = cart.getCartId();
        for (CartItem cartitem : cartitems) {
        	CartItemDBManager.addCartItem(cartitem, cartid);
        }
    	return cart;
	}
	protected static Cart updateCartTotal(HttpServletRequest request, Cart cart, ArrayList<CartItem> cartitems) {
		
		Double total = 0.0;
        for ( CartItem item : cartitems) {
        	 item.setTotal();
        	 try {
        		 if (item.getTotal() > 0.0) {
        			 total += item.getTotal();
        		 }
        	 }
             catch (Exception e){
            	 total += item.getPrice();
             }
        }
        cart.setPrice(total);
	  	return cart;
	}
	protected static double finalCartTotal(HttpServletRequest request, Cart cart, ArrayList<CartItem> cartitems, double PercentOff) {
		
		cart = updateCartTotal(request, cart, cartitems);
		double total = cart.getPrice() + 4.99;
		total = total - PercentOff * total;
        return total;
	}
    //protected static Address getFirstAddress(HttpServletRequest request, int uid) {
		
		//ArrayList<Address> addresses = search(request, cart, cartitems);
		//double total = cart.getPrice() + 4.99;
        //return total;
	//}
	protected static Promo makePromo(HttpServletRequest request) {
		Promo retval = new Promo();
		
		String promocode = "!*!";
		String isbn = "!*!";
		String percentoff = "!*!";
		String sdate = "!*!";
		String edate = "!*!";
		
		
		Enumeration<String> params = request.getParameterNames(); 
	    while(params.hasMoreElements()){
	    		String paramName = params.nextElement();
	    		System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
	    		switch(paramName) {
	    			case "promocode":
	    				promocode = request.getParameter(paramName);
	    				break;
	    			case "isbn":
	    				isbn = request.getParameter(paramName);
	    				break;
	    			case "sdate":
	    				sdate = request.getParameter(paramName);
	    				break;
	    			case "edate":
	    				edate = request.getParameter(paramName);
	    				break;
	    			case "percent":
	    				percentoff = request.getParameter(paramName);
	    				break;
	    			default:
	    				break;
	    		}
	     }
	    
	    try {
	    		if(promocode != "!*!") retval.setCode(promocode);
	    		if(isbn != "!*!") retval.setISBN(Integer.parseInt(isbn));
	    		if(sdate != "!*!") retval.setStartDate(sdate);
	    		if(edate != "!*!") retval.setEndDate(edate);
	    		if(percentoff != "!*!") retval.setPercentOff(Double.parseDouble(percentoff));
	    }
	    catch(Exception e){
	    		e.printStackTrace();
	    		return null;
	    }

	    
	    //if(promocode == "!*!" || isbn == "!*!" || sdate == "!*!" || edate == "!*!" || percentoff == "!*!") {
	    	//	System.out.println("Null value caught");
	    //		return null;c
		//}

		return retval;
	}
	
	protected static User UpdateUser(HttpServletRequest request, User utoupdate) {
		//What object do you want to get out of this interaction?
		User retval = utoupdate;
		
		//=========================================================
		//			VALUES YOU NEED FROM THE REQUEST
		//=========================================================
		String fname = "!*!";
		String lname = "!*!";
		String type = "!*!";
		String email = "!*!";
		String phonenumber = "!*!";
		String accounttype = "!*!";
		String username = "!*!";
		String password = "!*!";
		String addressline1 = "!*!";
		String addressline2 = "!*!";
		
		//=========================================================
		//		ITERATING THROUGHT THE ITEMS IN THE REQUEST
		//=========================================================
		Enumeration<String> params = request.getParameterNames(); 
	    while(params.hasMoreElements()){
	    		String paramName = params.nextElement();
	    		System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
	    		switch(paramName) {
	    			case "fname":
	    				fname = request.getParameter(paramName);
	    				break;
	    			case "lname":
	    				lname = request.getParameter(paramName);
	    				break;
	    			case "email":
	    				email = request.getParameter(paramName);
	    				break;
	    			case "type":
	    				type = request.getParameter(paramName);
	    				break;
	    			case "uname":
	    				username = request.getParameter(paramName);
	    				break;
	    			case "password":
	    				password = request.getParameter(paramName);
	    				break;
	    			case "phone":
	    				phonenumber = request.getParameter(paramName);
	    				break;
	    			case "address1":
	    				addressline1 = request.getParameter(paramName);
	    				break;
	    			case "address2":
	    				addressline2 = request.getParameter(paramName);
	    				break;
	    			default:
	    				break;
	    		}
	     }
	    
	    //=========================================================
	    //				PERFORM DATA VALIDATION
	    //=========================================================
		// IM A NAUGHTY BOY AND DIDNT ADD DATA VALIDATION - LOVE, MATT <3
	    if(fname != "!*!") retval.setFname(fname);
	    if(lname != "!*!") retval.setLname(lname);
	    if(email != "!*!") retval.setEmail(email);
	    if(password != "!*!") retval.setPassword(password);
	    if(accounttype != "!*!") retval.setType(accounttype);
	    if(username != "!*!") retval.setUsername(username);
		if(addressline1 != "!*!" ) retval.setAddress(addressline1, addressline2);
		
		
		//=======================-------------------===============
		//			SEND IT TO THE DATA ACCESS LAYER
		//=========================================================
		return retval;
		
	}
	
	
	protected static Transaction CreateTransaction(HttpServletRequest request, User u, String code, Cart cart) {
		//What object do you want to get out of this interaction?
		Transaction retval = new Transaction();
//		Cart cart = new Cart();
//		// Get the cartID
//		ArrayList<Cart> carts = CartDBManager.searchCart("uid", u.getUid());
//        if (carts.size() > 1) {
//            cart = carts.get(carts.size() - 1);
//        }
//        else {
//
//            cart = carts.get(0);
//        }
		int cartID = cart.getCartId();
		double price = cart.getPrice();
		// Get the userID
		String userID = Integer.toString(u.getUid());
		// Get the ccid
		String ccid = PaymentDBManager.getUserCardID("uid", u.getUid()).replace(",", "");
		// Get the amount
		Enumeration<String> params = request.getParameterNames(); 
	    while(params.hasMoreElements()){
	    		String paramName = params.nextElement();
	    		System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
	    }
		// Get the date today
	    
		retval.setAmount(price);
		retval.setCartid(cartID);
		if (code == null) {
			retval.setPromoCode("");
		}
		else {
		    retval.setPromoCode(code);
		}
		System.out.println("HERE IS THE CART ID: " + retval.getCartid());
		CartDBManager.addCart(u.getUid());
		retval.setCcid(Integer.parseInt(ccid));;
		return retval;
		
	}
	protected static ArrayList<Transaction> getTransactions(HttpServletRequest request, int uid) {
//		//What object do you want to get out of this interaction?
		ArrayList<Transaction> retval= new ArrayList<Transaction>();
		ArrayList<Cart> carts = CartDBManager.searchCart("uid", uid);
		List<Integer> cartids = new ArrayList<Integer>();
        for ( int i = 0; i < carts.size(); i++) {
        	cartids.add(carts.get(i).getCartId());
	     }
        for (int cartid : cartids) {
        	retval.addAll(TransactionDBManager.searchTransaction("cartid",Integer.toString(cartid)));
        }
		return retval;		
	}
//	
	
	
	
	protected static Book CreateBook(HttpServletRequest request) {
		//What object do you want to get out of this interaction?
		Book retval = new Book();
		
		//=========================================================
		//			VALUES YOU NEED FROM THE REQUEST
		//=========================================================
		String isbn = "!*!";
		String title = "!*!";
		String author = "!*!";
		String price = "!*!";
		String genre = "!*!";
		String description = "!*!";
		String quantity = "!*!";
		String threshold = "!*!";
		String image = "!*!";
		String buyprice = "!*!";
		String publisher = "!*!";
		String publicationdate = "!*!";
		String supplier = "!*!";
		//=========================================================
		//		ITERATING THROUGHT THE ITEMS IN THE REQUEST
		//=========================================================
		Enumeration<String> params = request.getParameterNames(); 
	    while(params.hasMoreElements()){
	    		String paramName = params.nextElement();
	    		System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
	    		switch(paramName) {
	    			case "isbn":
	    				isbn = request.getParameter(paramName);
	    				break;
	    			case "title":
	    				title = request.getParameter(paramName);
	    				break;
	    			case "author":
	    				author = request.getParameter(paramName);
	    				break;
	    			case "price":
	    				price = request.getParameter(paramName);
	    				break;
	    			case "genre":
	    				genre = request.getParameter(paramName);
	    				break;
	    			case "description":
	    				description = request.getParameter(paramName);
	    				break;
	    			case "quantity":
	    				quantity = request.getParameter(paramName);
	    				break;
	    			case "thresh":
	    				threshold = request.getParameter(paramName);
	    				break;
	    			case "image":
	    				image = request.getParameter(paramName);
	    				break;
	    			case "publisher":
	    				publisher = request.getParameter(paramName);
	    				break;
	    			case "pubyear":
	    				publicationdate = request.getParameter(paramName);
	    				break;
	    			case "buyprice":
	    				buyprice = request.getParameter(paramName);
	    				break;
	    			case "supplier":
	    				System.out.println("*************************************");
	    				System.out.println("*************************************");
	    				System.out.println("*************************************");
	    				System.out.println("*************************************");
	    				System.out.println("*************************************");
	    				System.out.println("SETTING SUPPLIER TO: " + request.getParameter(paramName));
	    				supplier = request.getParameter(paramName);
	    				break;
	    			default:
	    				break;
	    		}
	     }
	    
	    //=========================================================
	    //				PERFORM DATA VALIDATION
	    //=========================================================
		// IM A NAUGHTY BOY AND DIDNT ADD DATA VALIDATION - LOVE, MATT <3
	    
	    try {
	    		if(author != "!*!") retval.setAuthor(author);
	    		if(title != "!*!") retval.setTitle(title);
	    		if(price != "!*!") retval.setPrice(Double.parseDouble(price));
	    		if(isbn != "!*!") retval.setISBN(Integer.parseInt(isbn));
	    		if(quantity != "!*!") retval.setQuantity(Integer.parseInt(quantity));
	    		if(threshold != "!*!") retval.setThreshold(Integer.parseInt(threshold));
	    		if(genre != "!*!") retval.setGenre(genre);
	    		if(description != "!*!") retval.setDescription(description);
	    		if(image != "!*!") retval.setImage(image);
	    		if(buyprice != "!*!") retval.setBuyingPrice(Double.parseDouble(buyprice));
	    		if(supplier != "!*!") {
	    			// If it can be parsed as an int;, then use it as supplier ID
	    			// else, search for the supplier ID and use that
	    			int supplierID;
	    			try {supplierID = Integer.parseInt(supplier);}
	    			catch(Exception e) {
	    				try {
	    				ArrayList<Supplier> results = SupplierDBManager.searchSupplier("name", supplier);
	    				supplierID = results.get(0).getSupplierid();
	    				}catch(Exception err) {supplierID = 1; err.printStackTrace();}
	    			}
	    			retval.setSupplier(supplierID);
	    		}
	    		if(publisher != "!*!") retval.setPublisher(publisher);
	    		if(publicationdate != "!*!") retval.setPublicationYear(Integer.parseInt(publicationdate));
	    }
	    catch(Exception e){
    			e.printStackTrace();
    			return null;
	    }

		//=======================-------------------===============
		//			SEND IT TO THE DATA ACCESS LAYER
		//=========================================================
	    BookDBManager.addBook(retval);
		return retval;
		
	}
}
