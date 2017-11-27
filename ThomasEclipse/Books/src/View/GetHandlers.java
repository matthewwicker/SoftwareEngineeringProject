package View;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import Entities.Book;
import Entities.Promo;
import Entities.User;

public class GetHandlers {
	protected static User makeUser(HttpServletRequest request) {
		//What object do you want to get out of this interaction?
		User retval = new User();
		
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
		// IM A NAUGHTY BOY AND DIDNT ADD DATA VALIDATION - LOVE, MATT <3
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
	    if(accounttype != "!*!") {
	    		retval.setType(accounttype);
	    		//System.out.println("set!");
	    	}
	    if(username != "!*!") {
	    		retval.setUsername(username);
	    		//System.out.println("set!");
	    	}
		if(addressline1 != "!*!" ) {
			retval.setAddress(addressline1, addressline2);
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
		return retval;
		
	}
	
	
	
	protected static User signIn(HttpServletRequest request) {
		User retval = new User();
		
		String email = "!*!";
		String username = "!*!";
		String password = "!*!";
		
		Enumeration<String> params = request.getParameterNames(); 
	    while(params.hasMoreElements()){
	    		String paramName = params.nextElement();
	    		System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
	    		switch(paramName) {
	    			case "email":
	    				email = request.getParameter(paramName);
	    				break;
	    			case "uname":
	    				username = request.getParameter(paramName);
	    				break;
	    			case "password":
	    				password = request.getParameter(paramName);
	    				break;
	    			default:
	    				break;
	    		}
	     }
	    
	    if(username != "!*!") retval.setUsername(username);
	    if(email != "!*!") retval.setEmail(email);
	    if(password != "!*!") retval.setPassword(password);
	    
	    
	    if(username == "!*!" || email == "!*!" || password == "!*!") {
			return null;
		}
	    
		return retval;
	}
	
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
	    			case "percentoff":
	    				percentoff = request.getParameter(paramName);
	    				break;
	    			default:
	    				break;
	    		}
	     }
	    
	    System.out.println("Check 1");
	    System.out.println(retval);
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
	    
	    System.out.println("Check 2");
	    
	    if(promocode == "!*!" || isbn == "!*!" || sdate == "!*!" || edate == "!*!" || percentoff == "!*!") {
			return null;
		}
	    
	    System.out.println("Check 3");
	    
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
	    			case "descr":
	    				description = request.getParameter(paramName);
	    				break;
	    			case "quantity":
	    				quantity = request.getParameter(paramName);
	    				break;
	    			case "thresh":
	    				threshold = request.getParameter(paramName);
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
	    		if(price != "!*!") retval.setPrice(Double.parseDouble(price));
	    		if(isbn != "!*!") retval.setISBN(Integer.parseInt(isbn));
	    		if(isbn != "!*!") retval.setQuantity(Integer.parseInt(quantity));
	    		if(threshold != "!*!") retval.setThreshold(Integer.parseInt(threshold));
	    		if(genre != "!*!") retval.setGenre(genre);
	    }
	    catch(Exception e){
    			e.printStackTrace();
    			return null;
	    }

		//=======================-------------------===============
		//			SEND IT TO THE DATA ACCESS LAYER
		//=========================================================
		return retval;
		
	}
}
