package View;
import DatabaseAccess.Driver;
import DatabaseAccess.BookDBManager;
import DatabaseAccess.UserDBManager;
import DatabaseAccess.PromoDBManager;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

	Book newBook = new Book();
	User newUser = new User();
<<<<<<< HEAD
	Promo newPromo = new Promo();
=======
	Address newAddress = new Address();
	Payment newPayment = new Payment();
>>>>>>> 52c7a1ec8edaa2a90e1927d7976be162b9f92415
	private static final long serialVersionUID = 1L;
    Configuration cfg = null;
    private String templateDir = "/WEB-INF/templates";
    private Driver driver = new Driver();
    private BookDBManager BookManager = new BookDBManager();
    private PromoDBManager PromoManager = new PromoDBManager();
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
		
	    String template = "home.html";
	    String username = request.getParameter("username");
		String password = request.getParameter("password");
        String email="";
		String name="";
		String type="";
		
		Map<String, Object> root = new HashMap<>();
		
		///////////PERFORM TASK////////////
		String task = request.getParameter("Task"); 
	    System.out.println(task);
		try {
          if (task.equals("CreateBook")){
              setBook(request);
              int i = BookDBManager.addBook(newBook);
          }
	     else if(task.equals("CreatePromo")) {
	    	 setPromo(request);
	     }
	     else if(task.equals("DeletePromo")) {
	    	 deletePromo(request);
	     }
          else if (task.equals("CreateUser")){
  		      System.out.println("passed!");
	          setUser(request);
	          int i = UserDBManager.addUser(newUser);
	       }
          else if (task.equals("SignIn")){
        		ResultSet rs = driver.login(username, password);
        		if(rs != null){
        			try {
        				while(rs.next()){
        					email = rs.getString("email");
        					name = rs.getString("name");
        					}
        					
        				}
        			 catch (SQLException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        		    	}
        			template = "account.ftlh";
        			//template = "account.ftlh";
        			if(email.equals("")){
        				template = "signin.html";
        				out.println("<script type=\"text/javascript\">");
        			    out.println("alert('User or password incorrect');");
        			    out.println("</script>");
        			}
        			else{
        				String[] nameArray = name.split(" ");
        				String fname = nameArray[0];
        				String lname = nameArray[1];
        				root.put("username", username);
        				root.put("fname", fname);
        				root.put("lname", lname);
        				root.put("email", email);
        			}
           		}      	
           }
  		}
		catch(Exception e) {
		    System.out.println("No task");
		}
		
		
		/////////NAVIGATE/////////
		System.out.println(request.getParameter("navigator"));
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
	protected void setUser(HttpServletRequest request) {
		int user_phone = 0;
		try {
			user_phone  = Integer.parseInt(request.getParameter("Phone"));
		}
		catch(Exception e) {
			    System.out.println("<script type=\"text/javascript\">");
			    System.out.println("alert('Please remove characters from phone');");
			    System.out.println("</script>");		
		}
		String fname = request.getParameter("fName"); 
		String lname = request.getParameter("lName");
		String user_email= request.getParameter("Email");
		String user_password = request.getParameter("Password");
		String phoneNumber = "" + user_phone;
		
		newUser.setfName(fname);
		newUser.setlName(lname);
		newUser.setEmail(user_email);
		newUser.setPassword(user_password);	
		newUser.setPhoneNumber(phoneNumber);
		
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String address = address1 + address2;
		
		newAddress.setAddress(address);
		
		
		int ccid = Integer.parseInt(request.getParameter("ccid"));
		int cc_number = Integer.parseInt(request.getParameter("cc_number"));
		
		newPayment.setCcid(ccid);
		newPayment.setCc_number(cc_number);
		
		
	}
	
protected void setPromo (HttpServletRequest request) {
		
		int promoCode = 0;
		int promoISBN = 0;
		double promoPercent = 0;
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-M-d");
		
		LocalDate promoStartDate = null;
		LocalDate promoEndDate = null;
		
		try {
			promoCode  = Integer.parseInt(request.getParameter("code"));
			promoISBN  = Integer.parseInt(request.getParameter("isbn"));
			promoPercent  = Double.parseDouble(request.getParameter("percent"));
		    promoStartDate  = LocalDate.parse(request.getParameter("sdate"), format);
		    promoEndDate  = LocalDate.parse(request.getParameter("edate"), format);
		}
		catch(Exception e) {
			    System.out.println("<script type=\"text/javascript\">");
			    System.out.println("alert('Please fill numerical values into isbn, price, quant, and threshold');");
			    System.out.println("</script>");		
		}

		
		newPromo.setCode(promoCode);
		newPromo.setISBN(promoISBN);
		newPromo.setPercentOff(promoPercent);
		newPromo.setStartDate(promoStartDate);
		newPromo.setEndDate(promoEndDate);
		
		PromoManager.addPromo(newPromo);
	
}

protected void deletePromo (HttpServletRequest request) {
		
		int promoCode = 0;

		
		try {
			promoCode  = Integer.parseInt(request.getParameter("code"));
			
		}
		catch(Exception e) {
			    System.out.println("<script type=\"text/javascript\">");
			    System.out.println("alert('Please fill numerical values into isbn, price, quant, and threshold');");
			    System.out.println("</script>");		
		}

		
		newPromo.setCode(promoCode);
		
		
		PromoManager.removePromo(newPromo);
		
	}

}


