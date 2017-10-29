package com.book.pkg;

import java.io.IOException;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class myservlet
 */
@WebServlet("/myservlet")
public class myservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out =  response.getWriter();
		response.setContentType("text/html");

                out.println("<html>");
                out.println("<head>");
		out.println("<script language=\"JavaScript\">");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/javascript/validate.js");
		requestDispatcher.include(request, response);
		out.println("</script>");
                out.println("<title>Books</title>");
                out.println("    <meta charset=\"utf-8\" />");
                out.println("    <link href=\"custom.css\" type=\"text/css\" rel=\"stylesheet\" />");
                out.println("");
                out.println("    <style>");
                out.println("    @import url('https://fonts.googleapis.com/css?family=Lobster');");
                out.println("    </style>");
                out.println("");
                out.println("</head>");
                out.println("<body>");
                out.println("    <div id=\"wrapper\">");
                out.println("        <div id=\"header\">");
                out.println("            <img src=\"book.png\" style=\"position: absolute; height: 80px\"/>");
                out.println("            <a href=\"home.html\" style=\"text-decoration:none\"><h1><small>your</small>Shelf</h1></a>");
                out.println("            <div id=\"search\" >");
                out.println("                <input type=\"text\" name=\"searchbar\" value=\"Search\" style=\"height:25px; border: 1px solid gray; float:relative;padding-right:0px\">");
                out.println("                <div class=\"bropdown\" style=\"float: absolute; height: 29px;padding-left:3px;padding-right:80px\">");
                out.println("                    <button class=\"bropbtn\" style=\"float: absolute;height: 29px;\">Search by &nabla;</button>");
                out.println("                      <div class=\"bropdown-content\">");
                out.println("                         <a> Title  </a> ");
                out.println("                         <a> Author </a> ");
                out.println("                         <a> ISBN   </a> ");
                out.println("                         <a> year   </a> </div></div>");
                out.println("           </div>");
                out.println("        </div>");
                out.println("        <div id=\"navigation\">");
                out.println("            <ul class=\"navi\">");
                out.println("                <li> <div class=\"dropdown\">");
                out.println("                     <button class=\"dropbtn\">Genres</button>");
                out.println("                       <div class=\"dropdown-content\">");
                out.println("                         <a href=\"genres.html\">Science Fiction</a>");
                out.println("                         <a href=\"genres.html\">Young Adult</a>");
                out.println("                         <a href=\"genres.html\">Kids</a>");
                out.println("                       </div> </div> </li>");
                out.println("                <li><a href=\"best.html\">Best Selling</a></li>");
                out.println("                <li><a href=\"deals.html\">Deals</a></li>");
                out.println("                <li><a href=\"favorites.html\">Our Favorites</a></li>");
                out.println("                <li style=\"float: right; padding-right: 95px\"><a href=\"account.html\">Account</a></li>");
                out.println("                <li style=\"float: right; padding-top: 0px\"><a href=\"cart.html\" style=\"padding-top: 0px\"><img src=\"cart.png\" style=\"height: 38px; align: top; border: 1.5px solid white; border-radius: 20px\"  /></a></li>");
                out.println("            </ul>");
                out.println("            <div class=\"bropdown\" style=\"float: right; padding-right: 10px; height: 20px; width:80px\">");
                out.println("                    <button class=\"bropbtn\" style=\"height: 35px; background: #DB7093\"><a href=\"signin.html\" style=\"color:white; text-decoration:none\">LOG IN</a></button>");
                out.println("                      <div class=\"bropdown-content\" style=\"padding: 20px\">");
                out.println("                          <div id=\"username\">");
                out.println("                              <input type=\"text\" name=\"username\" value=\"Username\" style=\"width: 120px\">");
                out.println("                          </div>");
                out.println("                          <div id=\"password\">");
                out.println("                              <input type=\"password\" name=\"password\" value=\"Password\" style=\"width: 120px\">");
                out.println("                         </div>");
                out.println("                          <button style=\"height: 30px\">");
                out.println("                          <a href=\"account.html\">Enter</a> ");
                out.println("                            </button></div></div>");
                out.println("        </div>");
                out.println("        <div id=\"content-background\">");
                out.println("        <div id=\"content\">");
		out.println("<form action=\"/Bookz/account.html\" onSubmit=\"return validateUserName(this.username.value,this.password.value) \">");
                out.println("username: <input type=\"text\" name=\"username\">");
                out.println("<br>");
                out.println("password: <input type=\"text\" name=\"password\">");
                out.println("<br>");
		out.println("<input type=\"submit\" name=\"Submit\">");
		out.println("</form>");
                out.println("            <div id=\"login\">");
                out.println("                <p> <a href=\"account.html\">Log In</a> </p>");
                out.println("            </div>");
                out.println("            <a href=\"support.html\">Having trouble?</a> or do you need to <a href=\"create.html\">create an account</a>");
                out.println("       </div></div>");
                out.println("        <div id=\"footer\">");
                out.println("            <ul class=\"footer\">");
                out.println("                <li><a href=\"support.html\">Support</a></li>");
                out.println("                <li><a href=\"contact.html\">Contact</a></li>");
                out.println("            </ul>");
                out.println("        </div>");
                out.println("    </div>");
                out.println("</body>");
                out.println("</html>");
	//	out.println("</body>");
	//	out.println("</html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
