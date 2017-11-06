package first;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class webApp extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.getWriter().println("Hello World form webApp");
	
	String username = "John Smith";//req.getParameter("username");
	String password = "smith.john";//req.getParameter("password");
	
	BabyDriver driver = new BabyDriver();
	
	String canAccess = driver.signIn(username, password);
	//if(canAccess)
	resp.getWriter().println(canAccess);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
