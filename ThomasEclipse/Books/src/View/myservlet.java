package View;
import DatabaseAccess.Driver;
import DatabaseAccess.BookDBManager;
import DatabaseAccess.UserDBManager;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	private int authcode = 0;
	
	Book newBook = new Book();
	User newUser = new User();
	private static final long serialVersionUID = 1L;
    Configuration cfg = null;
    private String templateDir = "/WEB-INF/templates";
    private Driver driver = new Driver();
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
				if(potentialUser != null) {
	        	  		int i = UserDBManager.addUser(potentialUser);
				}
				else {
	        	  		System.out.println("REQUREMENTS NOT SATISFIED");
				}
			}//Create user
			
			else if (task.equals("SignIn")){
        	  		User userRequestingAuth = GetHandlers.signIn(request);
        	  		//CHECK THAT USER IS IN THE DATABASE
        	  		boolean userExists = true;
        	  		if(userExists) {
        	  			template = "account.ftlh";
        	  			//PRIVLEDGES - SET THIS USERS ID INFO AND TYPE IN THIS CLASS
        	  		}
        	  		else {
        	  			//SHOW ERROR AND STAY ON THIS PAGE
        	  		}
			}//Sign In
			
			else if (task.equals("GoToCart")){
				//THIS NEEDS TO BE INVERTED AFTER WE GET ALL THE DB ACCESS WORKING.
    	  			if(thisUser == null && authcode == 0) {
    	  				template = "cart.ftlh";
    	  			}
    	  			else {
    	  				template = "signin.html";
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
					//Send the created book to data access
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
		if (template.equals("account.ftlh")) {
			if (type.equals('a')){
				template = "adminaccount.ftlh";
			}
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
