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
		resp.getWriter().println("Hello World form webApp");

	//	RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/javascript/validate.js");
	//	requestDispatcher.include(request, resp);
	//	out.println("</body>");
	//	out.println("</html>");
		doPost(request,resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = "John Smith";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		BabyDriver driver = new BabyDriver();
		String canAccess = driver.signIn(username, password);
		if(canAccess.equals("true")) {
			resp.sendRedirect("account.html");
		}
		else {
			resp.sendRedirect("signin.html");
		}
		//doGet(request, resp);
	}
}
