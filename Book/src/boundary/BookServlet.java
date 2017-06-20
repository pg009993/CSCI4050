package boundary;


import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String templateDir = "/WEB-INF/templates";
	private TemplateProcessor process;
	
    public BookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		process = new TemplateProcessor(templateDir, getServletContext());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String signup = request.getParameter("signup");
		String startover = request.getParameter("startover");
		
		if(signup!=null) {
			VerifyUser(request, response);
		}
		if(startover!=null) {
			StartOver(request, response);
		}
		
	}
	
	public void VerifyUser(HttpServletRequest request, HttpServletResponse response) {
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		root.put("firstname", request.getParameter("firstname"));
		root.put("lastname", request.getParameter("lastname"));
		root.put("month", request.getParameter("DOBMonth"));
		root.put("day", request.getParameter("DOBDay"));
		root.put("year", request.getParameter("DOBYear"));
		root.put("gender", request.getParameter("gender"));
		root.put("street", request.getParameter("street"));
		root.put("city", request.getParameter("city"));
		root.put("state", request.getParameter("state"));
		root.put("zip", request.getParameter("zip"));
		root.put("username", request.getParameter("username"));
		root.put("password", request.getParameter("password"));
		root.put("email", request.getParameter("email"));
		root.put("phone", request.getParameter("phone"));		
		String templateName = "code.ftl";
		process.processTemplate(templateName, root, request, response);
	}
	
	public void StartOver(HttpServletRequest request, HttpServletResponse response) {
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		String templateName = "signup.ftl";
		process.processTemplate(templateName, root, request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
