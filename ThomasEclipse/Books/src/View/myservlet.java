package View;
import DatabaseAccess.Driver;
import DatabaseAccess.BookDBManager;
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

	private User thisUser = null;
	private int authcode = -1;
	private String accountdir = "useraccount";
	
	
	Book newBook = new Book();
	User newUser = new User();
	private static final long serialVersionUID = 1L;
    Configuration cfg = null;
    private String templateDir = "/WEB-INF/templates";
    private Driver driver = new Driver();
    private logic l = new logic();
    private BookDBManager BookManager = new BookDBManager();
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
		
		Map<String, Object> root = new HashMap<>();
		
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
        	  				template =  accountdir + "/account.ftlh";
        	  			}
        	  		}
        	  		catch(Exception e) {
        	  			e.printStackTrace();
        	  			template = "signinfail.ftlh";
        	  		}
			}//Sign In
			
			else if (task.equals("GoToCart")){
				//THIS NEEDS TO BE INVERTED AFTER WE GET ALL THE DB ACCESS WORKING.
    	  			if(thisUser == null && authcode == 0) {
    	  				template = "cart.ftlh";
    	  			}
    	  			else {
    	  				template = "signin.ftlh";
    	  			}
			}//Go to cart
			
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
				//SEND TO THE DATABASE ACCESS
			}//Update Info
			
			else if (task.equals("SignOut")){
				thisUser = null;
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
			
			else if(task.equals("GoToItem")) {
				//Somehow, we need to put the info from the clicked-on book to the bookInfo page
				template = "bookInfo.ftlh";
			} //Go to item
			
			else if(task.equals("AddToCart")) {
				// Somehow, we need to pull the information from the specific page
				template = "cart.ftlh";
			} //Add item to cart
			
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
				Promo promotodelege = GetHandlers.makePromo(request);
				//Why aren't we getting to this line?
			} //Add promo to this users cart
			
			else if(task.equals("Checkout")) {
				template = "checkout.ftlh";
				//Compute the exact costs and move to the 
			} //Checkout with this user
			
			else if(task.equals("UpdateCart")) {
				template = "checkout.ftlh";
			} //Update this cart information
			
			else if(task.equals("ConfirmPurchase")) {
				template = "checkoutConfirm.ftlh";
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
			
			else if(task.equals("GotToUpdateStatus")) {
				template =  "updateuserstatus.ftlh";
			}
			
			else if(task.equals("UpdateUserStatus")) {
				template =  "updateuserstatus.ftlh";
				String newpref = UserDBManager.getUserStatus("email", request.getParameter("email"));
				String status_to_update = request.getParameter("status");
				if(!"apesm".contains(status_to_update) ) {
					template =  "updateuserstatusfail.ftlh";
				}
				else {
					thisUser.setSuspended(request.getParameter("email"));
					l.changeStatus(request.getParameter("status"), thisUser);
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
			
			
			else if(task.equals("GoToUpdatePassword")) {
				template =  "changepassword.ftlh";
			}//Go To Update Password
			
			else if(task.equals("UpdatePassword")) {
				
				
				Enumeration<String> params = request.getParameterNames(); 
			    while(params.hasMoreElements()){
			    		String paramName = params.nextElement();
			    		System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
			     }
				
				
				System.out.println("INSIDE THE USECASE");
				template =  "changepassword.ftlh";
				if(request.getParameter("bpassword").equals(request.getParameter("cpassword"))) {
					System.out.println("PASSWORDS MATHCED");
					l.changePassword(request.getParameter("bpassword"), thisUser);
				}
			}//Update Password
			
			else if(task.equals("ForgotPassword")) {
				//template =  "forgotpassword.ftlh";
			}//Forgot Password
			
			
			
  		}
		catch(Exception e) {
		    //System.out.println("No task");
		}
		
		
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
			runTemplate(request, response, "home.html", root);	
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
