package cs4050;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

@WebServlet(name="HttpExample", urlPatterns={"/"})
public class HttpExample extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected Configuration freemarker;
    
    @Override
    public void init() {
        // Initialize FreeMarker
        freemarker = new Configuration(Configuration.VERSION_2_3_25);
        freemarker.setServletContextForTemplateLoading(getServletContext(), "/templates");
        freemarker.setDefaultEncoding("UTF-8");
        freemarker.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map root = new HashMap();

        try {
            freemarker.getTemplate("example.ftl.html").process(root, response.getWriter());
        } catch(TemplateException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map root = new HashMap();
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String summary = request.getParameter("summary");

        root.put("title", title);
        root.put("author", author);
        root.put("summary", summary);

        try {
            freemarker.getTemplate("book.ftl.html").process(root, response.getWriter());
        } catch(TemplateException ex) {
            throw new RuntimeException(ex);
        }
    }
}
