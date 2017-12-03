package View;
import DatabaseAccess.Driver;
import DatabaseAccess.PromoDBManager;
import DatabaseAccess.TransactionDBManager;
import DatabaseAccess.BookDBManager;
import DatabaseAccess.CartDBManager;
import DatabaseAccess.CartItemDBManager;
import DatabaseAccess.UserDBManager;
import EmailNotifications.SendEmail;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import Entities.*;
import Logic.*;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * Servlet implementation class myservlet
 */
@WebServlet("/myservlet")
public class myservlet extends HttpServlet {

	private User thisUser = new User();
	private int authcode = -1;
	private String accountdir = "useraccount";
	
    Map<String, Object> root = new HashMap<>();
    private Promo userPromotion = new Promo();
	Book newBook = new Book();
	User newUser = new User();
	private static final long serialVersionUID = 1L;
    Configuration cfg = null;
    private String templateDir = "/WEB-INF/templates";
    private Driver driver = new Driver();
    private logic l = new logic();
    private BookDBManager BookManager = new BookDBManager();
    Cart cart;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myservlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(){
    	    cfg = new Configuration(Configuration.VERSION_2_3_25);
    	    cfg.setServletContextForTemplateLoading(getServletContext(), templateDir);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        root.put("loginbutton","Log In");
        root.put("promocode", "");
        root.put("message", "");
        userPromotion.setPercentOff(0.0);
        userPromotion.setCode("No Promotion Used");
}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out =  response.getWriter();
		response.setContentType("application/json");
		
	    String template = "home.ftlh";
	    String username = request.getParameter("username");
		String password = request.getParameter("password");
        String email="";
		String name="";
		String type="";
		Cookie cookies[] = request.getCookies();
		
		
		///////////PERFORM TASK////////////
		String task = request.getParameter("Task");
		System.out.println("HERE IS THE TASK NAME:");
	    System.out.println(task);
		try {
			
			if (task.equals("none")){}
			
			else if (task.equals("CreateUser")){
				User potentialUser = GetHandlers.makeUser(request);
				SendEmail sender = new SendEmail();
				sender.actuallySendEmail(potentialUser, SendEmail.REGISTRATION_CONFIRMATION);
				/*if(potentialUser != null) {
	        	  		int i = UserDBManager.addUser(potentialUser);
				}
				else {
	        	  		System.out.println("REQUREMENTS NOT SATISFIED");
				}*/
			}//Create user
			
			else if (task.equals("SignIn")){
        	  		User userRequestingAuth = GetHandlers.signIn(request);
        	  		
        	  		//CHECK THAT USER IS IN THE DATABASE
        	  		try {
        	  			User u = l.authorizeUser(userRequestingAuth);
        	  			boolean userExists = false;
        	  			if(u != null) userExists = true;
        	  			if(userExists) {
        	  				thisUser = u;
        	  				if(thisUser.getType().equals("u")) {
        	  					accountdir = "useraccount";
        	  				}
        	  				else if(thisUser.getType().equals("a")) {
        	  					System.out.println("USING THE ADMIN ACCOUNT DIR");
        	  					accountdir = "adminaccount";
        	  				}
        	  				else if(thisUser.getType().equals("s")) {
        	  					accountdir = "shipmentaccount";
        	  				}
        	  				else if(thisUser.getType().equals("m")) {
        	  					accountdir = "manageraccount";
        	  				}
        					root.put("user", thisUser);
        	  				template =  accountdir + "/account.ftlh";
        					root.put("loginbutton", "Hello " + thisUser.getFname());
        	  			}
        	  		}
        	  		catch(Exception e) {
        	  			e.printStackTrace();
        	  			template = "signinfail.ftlh";
        	  		}
			}//Sign In
			else if (task.equals("SignInHead")){
    	  		User userRequestingAuth = GetHandlers.signInHeader(request);
    	  		//CHECK THAT USER IS IN THE DATABASE
    	  		try {
    	  			User u = l.authorizeUser(userRequestingAuth);
    	  			boolean userExists = false;
    	  			if(u != null) userExists = true;
    	  			if(userExists) {
    	  				thisUser = u;
    	  				if(thisUser.getType().equals("u")) {
    	  					accountdir = "useraccount";
    	  				}
    	  				else if(thisUser.getType().equals("a")) {
    	  					System.out.println("USING THE ADMIN ACCOUNT DIR");
    	  					accountdir = "adminaccount";
    	  				}
    	  				else if(thisUser.getType().equals("s")) {
    	  					accountdir = "shipmentaccount";
    	  				}
    	  				else if(thisUser.getType().equals("m")) {
    	  					accountdir = "manageraccount";
    	  				}
    					root.put("user", thisUser);
    					root.put("loginbutton", "Hello " + thisUser.getFname());
    	    	  		
    	  			}
    	  		}
    	  		catch(Exception e) {
    	  			e.printStackTrace();
    	  			template = "signinfail.ftlh";
    	  		}
		}//Sign In		
			else if (task.equals("GoToCreateBook")){
				template = "editbook.ftlh";
			}//Go to create book
			
			else if (task.equals("GoToCreateUser")){
				if(authcode == 0) {
					template = "admincreate.ftlh";
				}
				else {
					template = "create.ftlh";
				}
			}//Go to create user
			
			else if (task.equals("UpdateInfo")){
				User updatedUser = GetHandlers.UpdateUser(request, thisUser);
				//SEN GetHandlersD TO THE DATABASE ACCESS
			}//Update Info
			
			else if (task.equals("SignOut")){
				thisUser = new User();
				authcode = 0;
				template = "home.ftlh";
				//Clear the user info
			}//Sign Out
			
			else if(task.equals("CreateBook")) {
				Book createdBook = GetHandlers.CreateBook(request);
				if(createdBook != null) {
					l.addBook(createdBook);
					System.out.println("SUCCESS CREATING BOOK!");
				}
				else {
					//KEEP HERE 
					System.out.println("FAILURE CREATING BOOK!");
					template = "editbook.ftlh";
				}
			} //Create Book
			
			
			 /* Here is the messed up part */
			else if(task.contains("GoToItem")) {
				System.out.println("Here is the value of the task: " + task);
				String title = task.split("_")[1];
				Book item = GetHandlers.getItem(request, title);
		        System.out.println("Checking");
			    System.out.println(request.getParameter("test"));
	            root.put("item", item);        
				template = "bookInfo.ftlh";
			} //Go to item
			
			else if(task.equals("AddToCart")) {
				if (thisUser.getFname() == null) {
					template = "signin.ftlh";
				}
				else {
				    task = "GoToCart";
		            Book item = (Book) root.get("item");
		            //System.out.println(thisUser.getUid());
		            cart = GetHandlers.putInCart(request, item, thisUser.getUid());
		            ArrayList<CartItem> cartitems = CartItemDBManager.searchCartItem("cartid", cart.getCartId());
			        cart = GetHandlers.updateCartTotal(request, cart, cartitems);
			        double total = GetHandlers.finalCartTotal(request, cart, cartitems, userPromotion.getPercentOff());
		            root.put("cartitems", cartitems);
		            root.put("cart", cart);
		            root.put("total", total);
					template = "cart.ftlh";
					root.put("message", "");
				}
					
		    } //Add item to cart
			
			else if(task.equals("GoToCart")) {
				if (thisUser.getFname()==null) {
					template = "signin.ftlh";
				}
				else {
                    Cart cart = new Cart();
				    ArrayList<Cart> carts = CartDBManager.searchCart("uid", thisUser.getUid());
			        if (carts.size() > 1) {
			           cart = carts.get(carts.size() - 1);
			        }
			        else {

			            cart = carts.get(0);
			        }		            
			        ArrayList<CartItem> cartitems = CartItemDBManager.searchCartItem("cartid", cart.getCartId());
		            cart = GetHandlers.updateCartTotal(request, cart, cartitems);
		            double total = GetHandlers.finalCartTotal(request, cart, cartitems,  userPromotion.getPercentOff());
	                root.put("cartitems", cartitems);
	                root.put("cart", cart);
	                root.put("total", total);
				    template = "cart.ftlh";
				}
			}
			
			else if(task.contains("UpdateCart")) {
				System.out.println("ATTEMPTING TO UPDATE CART AND HERE IS THE TASK STRING: " + task);
				System.out.println("*************************************");
				System.out.println("*************************************");
				System.out.println("*************************************");
				System.out.println("*************************************");
				System.out.println("*************************************");
				System.out.println("*************************************");
				System.out.println("*************************************");
				template = "cart.ftlh";
	            Cart cart = (Cart) root.get("cart");
				int isbn = 0; 
				int qty = 0;
				isbn = Integer.parseInt(task.split("_")[1].replace(",", ""));
				qty  = Integer.parseInt(task.split("_")[2].replace(",", ""));
				System.out.println("check0");
				
				if (qty > 0) {
					System.out.println("check");
	        	        CartItemDBManager.changeQuantity(cart.getCartId(), isbn, qty);

				    System.out.println("check");
				}
				else {
					CartItemDBManager.removeCartItem(cart.getCartId(), isbn);	
				}
		        ArrayList<CartItem> cartitems = CartItemDBManager.searchCartItem("cartid", cart.getCartId());
		        cart = GetHandlers.updateCartTotal(request, cart, cartitems);
		        double total = GetHandlers.finalCartTotal(request, cart, cartitems,  userPromotion.getPercentOff());
	            root.put("cartitems", cartitems);
	            root.put("total", total);
				template = "cart.ftlh";
			} //Update this cart information

			else if(task.equals("GoToPromotion")) {
				template = "editpromo.ftlh";
			} //Go to promo
			
			else if(task.equals("AddPromo")) {
				template = "editpromo.ftlh";
				Promo promotodelege = GetHandlers.makePromo(request);
				//Add it to the database
				int added = l.addPromo(promotodelege);
				if(added == 1) {
					template = "makepromosucc.ftlh";
					SendEmail sender = new SendEmail();
					//Get emails of those who subscribe to promotion emails
					ArrayList<User> sendTo = UserDBManager.searchUsers("getsPromo", "1");
					for(User u : sendTo) {
						sender.sendPromotionalEmail(u, promotodelege);
					}
				}
				else {
					template = "makepromofail.ftlh";
				}
				//Why aren't we getting to this line?
			} //Add promo to database
			
			else if(task.equals("DeletePromo")) {
				template = "editpromo.ftlh";
				Promo promotodelege = GetHandlers.makePromo(request);
				int removed = l.removePromo(promotodelege);
				if(removed == 1) {
					template = "removepromosucc.ftlh";
				}
				else {
					template = "removepromofail.ftlh";
				}
				//Why aren't we getting here
			} //Delete promo to database
			else if(task.equals("AddPromoToCart")) {
				template = "cart.ftlh";
				Promo promotodelege = GetHandlers.getPromo(request);
				userPromotion = promotodelege;
                if (userPromotion.getPercentOff()==0.0) {
                	root.put("message", "promo does not exist.");
                }
                else {
					root.put("message", "Add promo successful.");
					root.put("promocode", userPromotion.getCode());
				}
	            Cart cart = (Cart) root.get("cart");
	            ArrayList<CartItem> cartitems = (ArrayList<CartItem>) root.get("cartitems");
		        double total = GetHandlers.finalCartTotal(request, cart, cartitems,  userPromotion.getPercentOff());
	            root.put("total", total);
				template = "cart.ftlh";
			} //Add promo to this users cart
			
			else if(task.equals("Checkout")) {
				template = "checkout.ftlh";
				// not working.. also not super important
				//String promocodestring = userPromotion.getCode() + " - " + userPromotion.getPercentOff() + "% off " + userPromotion.getISBN();
				//root.put("promocode", promocodestring);
				
			} //Checkout with this user
			
			else if(task.equals("ConfirmPurchase")) {
				template = "checkoutConfirm.ftlh";
				
			} //Confirm Purchase
			else if(task.equals("GoToOrders")) {
				template = "orderhistory.ftlh";
				ArrayList<Transaction> trans = GetHandlers.getTransactions(request, thisUser.getUid());
				ArrayList<ArrayList<CartItem>> cartitems= new ArrayList<ArrayList<CartItem>>();
                root.put("trans", trans);
				int cartid = 0;
				for (Transaction tran : trans){
			        cartitems.add(CartItemDBManager.searchCartItem("cartid", tran.getCartid()));
				}
				root.put("citems_", cartitems);
			}
			else if(task.contains("MakeCart")) {
				int cartid = Integer.parseInt(task.split("_")[1]);
				ArrayList<CartItem> cartitems = CartItemDBManager.searchCartItem("cartid", cartid);
                Cart cart = GetHandlers.putItemsInCart(request, cartitems, thisUser.getUid());
                root.put("cart", cart);
		        cartitems = CartItemDBManager.searchCartItem("cartid", cart.getCartId());
	            cart = GetHandlers.updateCartTotal(request, cart, cartitems);
	            double total = GetHandlers.finalCartTotal(request, cart, cartitems,  userPromotion.getPercentOff());
                root.put("cartitems", cartitems);
                root.put("cart", cart);
                root.put("total", total);
			    template = "cart.ftlh";			
			    }
			else if(task.equals("GoToAccount")) {
				if (thisUser.getFname() == null) {
					template = "signin.ftlh";
				}
				else {
				    template = accountdir + "/account.ftlh";
				}
			} //Confirm Purchase
			else if(task.equals("Validation")) {
				template = accountdir + "/account.ftlh";
				
				int val = l.validateUser(thisUser, request.getParameter("vcode"));
				if(val == 1) {
					System.out.println("SUCCESS VALIDATING");
					template = accountdir + "/accountvalsucc.ftlh";
				}
				else {
					System.out.println("Failure VALIDATING");
					template = accountdir + "/accountvalfail.ftlh";
				}
			} //Confirm Purchase

			else if(task.equals("UpdatePromoPref")) {
				System.out.println("Hi?");
				//Invert User Pref
				System.out.println(thisUser.getEmail());
				String newpref = UserDBManager.getUserPreference("email", thisUser.getEmail());
				System.out.println("Hi?" + newpref);
				if(newpref.equals("1")) {
					System.out.println("Hi?2");
					//send them to a page saying they have unsubscribed for messages
					thisUser.setSubscribed("0");
					l.changePromoSetting("0", thisUser);
					SendEmail sender = new SendEmail();
					System.out.println("Hi?3");
					sender.actuallySendEmail(thisUser, SendEmail.UNSUBBED_PROMOTIONS);
					template = accountdir + "/accountpromofalse.ftlh";
					System.out.println("Hi?4");
				}
				else {
					//send them to a page saying they have subscribed for messages
					thisUser.setSubscribed("1");
					System.out.println("Hi?5");
					l.changePromoSetting("1", thisUser);
					SendEmail sender = new SendEmail();
					System.out.println("Hi?5");
					sender.actuallySendEmail(thisUser, SendEmail.SUBBED_PROMOTIONS);
					template = accountdir + "/accountpromotrue.ftlh";
				}
				
			} //Update Promo Pref
			
			else if(task.equals("GoToSuspend")) {
				template =  "suspend.ftlh";
			}
			else if(task.equals("GoToManageUsers")) {
				template =  accountdir + "/manageusers.ftlh";
			}
			else if(task.equals("GotToUpdateStatus")) {
				template =  "updateuserstatus.ftlh";
			}
			
			else if(task.equals("UpdateUserStatus")) {
				template =  "updateuserstatus.ftlh";
				String value = request.getParameter("email");
				System.out.println("User email from form: " + value);
				String newpref = UserDBManager.getUserStatus("email", value);
				String status_to_update = request.getParameter("status");
				if(!"apesm".contains(status_to_update) ) {
					template =  "updateuserstatusfail.ftlh";
				}
				else {
					l.changeStatus(request.getParameter("status"), value);
					SendEmail sender = new SendEmail();
					sender.actuallySendEmail(thisUser, SendEmail.ACCOUNT_STATUS_CHANGED);
					template =  "updateuserstatussucc.ftlh";
				}
			}
			
			else if(task.equals("SuspendUser")) {
				String newpref = UserDBManager.getUserSuspension("email", request.getParameter("email"));
				if(newpref.equals("-1")) {
					//Unable to find user -- unhandled rn. Matt knows how to handle this... tell him hes a fool for not doing it 
					// when he was supposed to -Matt
				}
				if(newpref.equals("1")) {
					//send them to a page saying they have unsubscribed for message
					l.changeSuspension("0", thisUser);
					SendEmail sender = new SendEmail();
					sender.actuallySendEmail(thisUser, SendEmail.ACCOUNT_STATUS_CHANGED_UNSUSPENDED);
					template =  "unsuspend.ftlh";
				}
				else {
					//send them to a page saying they have subscribed for messages
					l.changeSuspension("1", thisUser);
					SendEmail sender = new SendEmail();
					sender.actuallySendEmail(thisUser, SendEmail.ACCOUNT_STATUS_CHANGED_SUSPENDED);
					template =  "suspendsucc.ftlh";
				}
			} //Confirm Purchase
			
			else if(task.equals("GoToCreateSupplier")) {
				template =  "createsupplier.ftlh";
			}//Go To CreateSupplier
			
			else if(task.equals("CreateSupplier")) {
				template =  "createsupplier.ftlh";
				
				//Create user, then fill in the supplier database
			}//Go To CreateSupplier
			
			else if(task.equals("UpdateOrderStatus")) {
				template =  "updateOrderStatus.ftlh";
				ArrayList<User> usr = UserDBManager.searchUsers("email", request.getParameter("email"));
				int ordernum = Integer.parseInt(request.getParameter("ordernum"));
				String status = request.getParameter("status");
				try {
					TransactionDBManager.UpdateTransactitonStatus(status, ordernum);
					SendEmail sender = new SendEmail();
					sender.actuallySendEmail(usr.get(0), SendEmail.SHIPMENT_CONFIRMATION);
				}
				catch(Exception e) {
					System.out.println("uh oh, idiot.");
				}
				
			}//Update Order Status
			
			
			else if(task.equals("GoToUpdatePassword")) {
				template =  "changepassword.ftlh";
			}//Go To Update Password
			
			else if(task.equals("UpdatePassword")) {
				
				
				Enumeration<String> params = request.getParameterNames(); 
			    while(params.hasMoreElements()){
			    		String paramName = params.nextElement();
			    		System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
			     }
				template =  "changepassword.ftlh";
				if(request.getParameter("bpassword").equals(request.getParameter("cpassword"))) {
					System.out.println("PASSWORDS MATHCED");
					l.changePassword(request.getParameter("bpassword"), thisUser);
				}
			}//Update Password
			
			else if(task.equals("ForgotPassword")) {
				//template =  "forgotpassword.ftlh";
			}//Forgot Password
			
			else if(task.equals("MakePurchase")) {
				template = "checkoutConfirm.ftlh";
	            double total = (double) root.get("total");
	            Cart cart = (Cart) root.get("cart");
				//try {  MATT I changed it so cart.price is updated upon adding a promocode or changing quantity in cart
	            //I think this stuff isn't necessary now, but i'm leaving it in case it does something I don't understand.
				Transaction t = GetHandlers.CreateTransaction(request, thisUser, userPromotion.getCode(), cart);
				//t.setAmount(cart.getPrice());
				//try { t.setPromoCode(userPromotion.getCode());} 
				//catch (Exception e) { t.setPromoCode("null");}
				TransactionDBManager.addTransaction(t);
				//}
				//catch(Exception e) {e.printStackTrace();}
     			CartDBManager.addCart(thisUser.getUid());
     		    ArrayList<Cart> carts = CartDBManager.searchCart("uid", thisUser.getUid());
		        if (carts.size() > 1) {
		           cart = carts.get(carts.size() - 1);
		        }
			    else {

			         cart = carts.get(0);
	            }	
				
			}//Forgot Password
			else if (task.equals("Search")) {
	            ArrayList<Book> books = BookDBManager.searchBooks(request.getParameter("searchby"), request.getParameter("searchval"));
	            root.put("books", books);        
	            root.put("searchheader", "Search by " + request.getParameter("searchby") + ": " + request.getParameter("searchval") );
	            template = "genres.ftlh";    
	        }
	        else if (task.contains("Search")) {
	            ArrayList<Book> books = BookDBManager.searchBooks(task.split("_",3)[1], task.split("_",3)[2]);
	            for(Book x : books) {
	            		System.out.println(x.getTitle());
	            }
	            root.put("books", books); 
	            String array[] = {"fuck", "this", "shit"};
	            root.put("sequence", books);  
	            root.put("searchheader", "Search by " + task.split("_",3)[1] + ": " + task.split("_",3)[2] );
	            template = "genres.ftlh";
	        }
	        else {
	        		Enumeration<String> params = request.getParameterNames(); 
			    while(params.hasMoreElements()){
			    		String paramName = params.nextElement();
			    		System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
			     }
	        }
			
  		}
		catch(Exception e) {
		    //System.out.println("No task");
		}

   	   
        System.out.println(template);
		
		/////////NAVIGATE/////////
		//System.out.println(request.getParameter("navigator"));
		String navigate = request.getParameter("navigator");
		if (navigate != null){
			template = navigate;
		}
		try {
	     	runTemplate(request, response, template, root);
		}
		catch (Exception e) {
			System.out.println("Template not found");
			runTemplate(request, response, "home.ftlh", root);	
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}
	
	public void runTemplate(HttpServletRequest request, HttpServletResponse response, String templateName, Map<String, Object> root){
		Template template = null;
		
		try{
			template = cfg.getTemplate(templateName);
			response.setContentType("text/html");
			Writer out = response.getWriter();
			template.process(root, out);
		} catch (IOException e){
			e.printStackTrace();
		} catch(TemplateException e){
			e.printStackTrace();
		}
	}
	
	protected void setBook(HttpServletRequest request) {
		int book_isbn = 0;
		double book_price  = 0.0;
		int book_thresh  = 0;
		int book_quant  = 0;
		try {
			book_isbn  = Integer.parseInt(request.getParameter("isbn"));
		    book_price  = Double.parseDouble(request.getParameter("price"));
	        book_thresh  = Integer.parseInt(request.getParameter("threshold"));
		    book_quant  = Integer.parseInt(request.getParameter("quantity"));
		}
		catch(Exception e) {
			    System.out.println("<script type=\"text/javascript\">");
			    System.out.println("alert('Please fill numerical values into isbn, price, quant, and threshold');");
			    System.out.println("</script>");		
		}
		String book_title = request.getParameter("title");
		String book_author= request.getParameter("author");
		String book_genre = request.getParameter("genre");
		String book_desc  = request.getParameter("description");
		
		newBook.setISBN(book_isbn);
		newBook.setAuthor(book_author);
		newBook.setTitle(book_title);
	//	newBook.setGenre(book_genre);
		newBook.setSupplier('1');
		newBook.setPrice(book_price);
		newBook.setDescription(book_desc);
		newBook.setQuantity(book_quant);
		newBook.setThreshold(book_thresh);	
		
	}

}
