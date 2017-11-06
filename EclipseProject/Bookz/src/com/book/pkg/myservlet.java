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
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out =  resp.getWriter();
		String username = "John Smith";//req.getParameter("username");
		String password = "smith.john";//req.getParameter("password");
		resp.getWriter().println("Hello World form webApp");
		BabyDriver driver = new BabyDriver();
		String canAccess = driver.signIn(username, password);
		//if(canAccess)
		resp.getWriter().println(canAccess);
	//	RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/javascript/validate.js");
	//	requestDispatcher.include(request, resp);
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
