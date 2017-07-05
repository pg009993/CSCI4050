package edu.uga.cs4300.boundary;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;

import edu.uga.cs4300.logiclayer.RecipeLogicImpl;
import edu.uga.cs4300.objectlayer.Recipe;
import edu.uga.cs4300.objectlayer.User;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;
import sun.misc.BASE64Encoder;

/**
 * Servlet implementation class RecipeServlet
 */
@WebServlet("/RecipeServlet")
public class RecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String templateDir = "/WEB-INF/templates";
	
	private TemplateProcessor process;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		process = new TemplateProcessor(templateDir, getServletContext());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String signup = request.getParameter("signup");
		String signin = request.getParameter("signin");
		String home = request.getParameter("home");
		String viewRecipes = request.getParameter("viewRecipe");
		//String createNewRecipe = request.getParameter("createrecipe");
		String createRecipe = request.getParameter("createButton");
		String loadPopularRecipes = request.getParameter("loadPopular");
		String viewRecipe = request.getParameter("loadRecipe");
		
		//james
				String favoriteRecipes = request.getParameter("favoriteRecipes");
				String sharedRecipes = request.getParameter("sharedRecipes");
				//james
				//display myRecipes in myProfile
				String myRecipes = request.getParameter("myRecipes");
		
		String logout = request.getParameter("logout");
		
		String viewProfile = request.getParameter("viewProfile");
		
		String loadByCategory = request.getParameter("loadByCategory");
		
		String[] viewOtherUser = request.getParameterValues("otherUser");
		
		String search = request.getParameter("searchButton");
		
		String getUserRecipes = request.getParameter("getUserRecipes");
		
		String favoriteRecipe = request.getParameter("favoriteRecipe");
		
		String friends = request.getParameter("friends");
		String friendRequest = request.getParameter("friendRequest");
		String acceptRequest = request.getParameter("acceptRequest");
		String rejectRequest = request.getParameter("rejectRequest");
		
		String rating = request.getParameter("rankRecipe");
		
		String openSignin = request.getParameter("openSigninPage");
		String openSignup = request.getParameter("openSignupPage");
		
		String[] friendsToShareWith = request.getParameterValues("friendIDs[]");
		
		String getMyFriends = request.getParameter("getMyFriends");
		
		if (signup != null)
		{
			System.out.println("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEY");
			addNewUser(request, response);
		}
		else if (signin != null)
		{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			RecipeLogicImpl loginUser = new RecipeLogicImpl();
			User user = loginUser.validateLogin(username.trim(), password.trim());
			if(user != null) {
				openHomePage(user, request, response);
			} else 
			{
				reloadLoginPage(request, response);
			}
		}
		else if (home != null)
		{
			goToHome(request, response);
		}
		else if (viewRecipes != null)
		{
			viewRecipes(request, response);
		}
		else if (createRecipe != null)
		{
			showRecipeMaker(request, response);
		}
		else if (ServletFileUpload.isMultipartContent(request))
		{
			createNewRecipe(request, response);
		}
		else if (loadPopularRecipes != null)
		{
			getPopularRecipes(request, response);
		}
		else if (viewRecipe != null)
		{
			viewSelectedRecipe(request, response);
		}
		else if (logout != null) {
			HttpSession session = request.getSession(false);
			session.invalidate();
			response.sendRedirect("index.html");
		}
		else if (viewProfile != null) {
			viewProfile(request, response);
		}
		else if (loadByCategory != null)
		{
			loadCategory(request, response, loadByCategory);
		}
		else if (viewOtherUser != null)
		{
			for (int i = 0; i < viewOtherUser.length; i++)
			{
				if (viewOtherUser[i] != null)
				{
					viewUser(request, response, viewOtherUser[i]);
				}
			}
		}
		else if(search != null) {
			String term = request.getParameter("term");
			String category = request.getParameter("category");
			Searched(request,response, category, term);
		}
		else if (getUserRecipes != null)
		{
			getOtherUserRecipes(request, response);
		}
		else if (favoriteRecipe != null)
		{
			favoriteCurrentRecipe(request, response);
		}
				//james
				//Display myRecipes in myProfile
				else if(myRecipes != null)
				{
					HttpSession session = request.getSession(false);	
					
					//UserId to get recipes for the profile
					int userId = (Integer)(session.getAttribute("id"));
					
					//used to call getMyRecipes
					RecipeLogicImpl userCtrl = new RecipeLogicImpl();
					
					//Array List that has recipes/ingredients/instructions
					ArrayList<Recipe> recipes = userCtrl.getMyRecipes(userId);
					
					//Put the recipes into JSON
					Gson g = new Gson();
					System.out.println(g.toJson(recipes));
					response.setContentType("application/json");
					try {
						response.getWriter().write(g.toJson(recipes));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				//james
				else if(favoriteRecipes !=null)
				{
					HttpSession session = request.getSession(false);	
					
					//UserId to get recipes for the profile
					int userId = (Integer)(session.getAttribute("id"));
					
					//used to call getMyRecipes
					RecipeLogicImpl userCtrl = new RecipeLogicImpl();
					
					//Array List that has recipes/ingredients/instructions
					ArrayList<Recipe> recipes = userCtrl.getFavoriteRecipes(userId);
					
					//Put the recipes into JSON
					Gson g = new Gson();
					System.out.println(g.toJson(recipes));
					response.setContentType("application/json");
					try {
						response.getWriter().write(g.toJson(recipes));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				else if(sharedRecipes != null)
				{
					HttpSession session = request.getSession(false);	
					
					//UserId to get recipes for the profile
					int userId = (Integer)(session.getAttribute("id"));
					
					//used to call getMyRecipes
					RecipeLogicImpl userCtrl = new RecipeLogicImpl();
					
					//list of shared recipes
					ArrayList<Recipe> recipeList = userCtrl.getSharedRecipes(userId);
					
					//Put the recipes into JSON
					Gson g = new Gson();
					System.out.println(g.toJson(recipeList));
					response.setContentType("application/json");
					try {
						response.getWriter().write(g.toJson(recipeList));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if (friends != null) { //lakshay
					if(request.getSession(false) != null) {
						viewFriends(request, response);
					} 
					else {
						response.sendRedirect("signin.html");
					}
				}
				else if (friendRequest != null) {
					String friendId = request.getParameter("id");
					try {
						addFriend(friendId, request, response);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if (acceptRequest != null) {
					String friendId = request.getParameter("id");
					acceptRequest(friendId, request, response);
				}
				else if (rejectRequest != null) {
					String friendId = request.getParameter("id");
					rejectRequest(friendId, request, response);
				}
				else if(rating!=null) {
					String i = request.getParameter("id");
					String r = request.getParameter("rank");
					int id = Integer.parseInt(i);
					int rank = Integer.parseInt(r);
					System.out.println("ELSE IF: RANK = " + r);
					Rank(request,response,id,rank);
				}
				else if (openSignin != null) {
					response.sendRedirect("signin.html");
				} 
				else if (openSignup != null) {
					response.sendRedirect("signup.html");
				}
				else if(friendsToShareWith != null)
				{
				//get user id 
				HttpSession session = request.getSession(false);	
				int myID = (Integer)(session.getAttribute("id"));

				ArrayList<Integer> friendsList = new ArrayList<Integer>();

				//ID of the recipe to be shared
				int recipeID = Integer.parseInt(request.getParameter("shareRecipeID"));


				for(int i = 0; i < friendsToShareWith.length; i++)
				{
				int temp = Integer.parseInt(friendsToShareWith[i]);
				friendsList.add(temp);
				}

				//Send recipe to be shared
				shareRecipe(recipeID, myID, friendsList );

				}
		
				else if (getMyFriends != null)
				{
				HttpSession session = request.getSession(false);	
				int userId = (Integer)(session.getAttribute("id"));

				getMyFriends(response, userId);
				}
	}
	
	private void rejectRequest(String friendId, HttpServletRequest request, HttpServletResponse response) {
		if(request.getSession(false) != null) {
			int userId = (Integer) request.getSession(false).getAttribute("id");
			RecipeLogicImpl userCtrl = new RecipeLogicImpl();
			String success = userCtrl.rejectRequest(userId, friendId);
			response.setContentType("text/plain");
			try {
				response.getWriter().write(success);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//lakshay
	private void acceptRequest(String friendId, HttpServletRequest request, HttpServletResponse response) {
		if(request.getSession(false) != null) {
			int userId = (Integer) request.getSession(false).getAttribute("id");
			RecipeLogicImpl userCtrl = new RecipeLogicImpl();
			String success = userCtrl.acceptRequest(userId, friendId);
			response.setContentType("text/plain");
			try {
				response.getWriter().write(success);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//lakshay
	private void addFriend(String friendId, HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, SQLException {
		if(request.getSession(false) != null) {
			int userId = (Integer) request.getSession(false).getAttribute("id");
			RecipeLogicImpl userCtrl = new RecipeLogicImpl();
			String result = userCtrl.addFriend(friendId, userId);
			try {
				response.setContentType("text/plain");
				response.getWriter().write(result);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("session is null");
		}
	}
	//lakshay
	private void viewFriends(HttpServletRequest request, HttpServletResponse response) {
		if(request.getSession(false) != null) {
			int userId = (Integer)request.getSession(false).getAttribute("id");
			RecipeLogicImpl userCtrl = new RecipeLogicImpl();
			List<User> friends = userCtrl.getFriends(userId);
			List<User> users = userCtrl.getUsers(userId);
			List<User> requests = userCtrl.getFriendRequests(userId);
			DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(df.build());
			root.put("friends", friends);
			root.put("users", users);
			root.put("fname", request.getSession(false).getAttribute("firstName"));
			root.put("checklogin", 1);
			root.put("requests", requests);
			String templateName = "friends.ftl";
			process.processTemplate(templateName, root, request, response);
		} else {
			System.out.println("session is null");
		}
		
	}

	
	private void favoriteCurrentRecipe(HttpServletRequest request, HttpServletResponse response) {
		RecipeLogicImpl userCtrl = new RecipeLogicImpl();
		String id = request.getParameter("id");
		int recipeId = Integer.parseInt(id);
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			int tempId = (int) session.getAttribute("id");
			Boolean check = userCtrl.checkFavorites(recipeId, tempId);
			if (check == false)
			{
				int temp = userCtrl.favoriteRecipe(recipeId, tempId);
				if (temp != 0)
				{
					response.setContentType("text/plain");
					try {
						response.getWriter().write("favorited");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					response.setContentType("text/plain");
					try {
						response.getWriter().write("failed");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} // if
			else
			{
				response.setContentType("text/plain");
				try {
					response.getWriter().write("alreadyFavorited");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else
		{
			response.setContentType("text/plain");
			try {
				response.getWriter().write("nosession");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void getOtherUserRecipes(HttpServletRequest request, HttpServletResponse response) {
		RecipeLogicImpl userCtrl = new RecipeLogicImpl();
		String id = request.getParameter("userId");
		int userId = Integer.parseInt(id);
		List<Recipe> temp = new ArrayList<Recipe>();
		temp = userCtrl.getUserRecipes(userId);
		Gson g = new Gson();
		response.setContentType("application/json");
		try {
			response.getWriter().write(g.toJson(temp));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Don't need list of recips for this one, just the Username

	private void viewUser(HttpServletRequest request, HttpServletResponse response, String otherUser) {
		RecipeLogicImpl userCtrl = new RecipeLogicImpl();
		int id = Integer.parseInt(otherUser);
		User temp = userCtrl.getUserInfo(id);
		if (temp != null)
		{
			HttpSession session = request.getSession(false);
			if (session == null)
			{
				DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				SimpleHash root = new SimpleHash(df.build());
				String templateName = "viewSelectedUser.ftl";
				root.put("checklogin", 0);
				root.put("fname", "Not Logged In");
				root.put("user", temp);
				root.put("otherId", id);
				process.processTemplate(templateName, root, request, response);
			}
			else
			{
				HttpSession session2 = request.getSession();
				DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
				SimpleHash root = new SimpleHash(df.build());
				String templateName = "viewSelectedUser.ftl";
				root.put("fname", session2.getAttribute("firstName"));
				root.put("checklogin", 1);
				root.put("user", temp);
				root.put("otherId", id);
				process.processTemplate(templateName, root, request, response);
			}
		}
	}

	private void loadCategory(HttpServletRequest request, HttpServletResponse response, String category) {
		RecipeLogicImpl userCtrl = new RecipeLogicImpl();
		List<Recipe> recipes = new ArrayList<Recipe>();
		recipes = userCtrl.viewByCategory(category);
		
		Gson g = new Gson();
		response.setContentType("application/json");
		try {
			response.getWriter().write(g.toJson(recipes));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void viewSelectedRecipe(HttpServletRequest request, HttpServletResponse response) {
		String tempId = request.getParameter("id");
		int id = Integer.parseInt(tempId);
		RecipeLogicImpl userCtrl = new RecipeLogicImpl();
		Recipe recipe = userCtrl.getRecipeById(id);
		Gson g = new Gson();
		response.setContentType("application/json");
		try {
			response.getWriter().write(g.toJson(recipe));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//JAMES 
	//Used for session checking
	private void viewProfile(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = request.getSession(false);	
		
		//User is logged in continue
		if (session != null)
		{
			//FTL stuff
			DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(df.build());
			
			int userId = (Integer)(session.getAttribute("id"));
			RecipeLogicImpl userCtrl = new RecipeLogicImpl();
			
			User profileUser = userCtrl.getUserByID(userId);
			
			System.out.println(profileUser.getUsername());
			
			
			String templateName = "viewProfile.ftl";
			root.put("userName", profileUser.getUsername());
			root.put("proPic", profileUser.getImage64());
			root.put("checklogin", 1); 
			process.processTemplate(templateName, root, request, response);
		}
		//User not logged in go to signin page
		else
		{
			try {
				response.sendRedirect("signin.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	private void getPopularRecipes(HttpServletRequest request, HttpServletResponse response) {
		RecipeLogicImpl userCtrl = new RecipeLogicImpl();
		List<Recipe> recipes = new ArrayList<Recipe>();
		recipes = userCtrl.getPopRecipes();
		
		Gson g = new Gson();
		response.setContentType("application/json");
		try {
			response.getWriter().write(g.toJson(recipes));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showRecipeMaker(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session != null)
		{
			DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(df.build());
			String templateName = "createrecipe.ftl";
			root.put("fname", session.getAttribute("firstName"));
			root.put("checklogin", 1);
			process.processTemplate(templateName, root, request, response);
		}
		else
		{
			try {
				response.sendRedirect("signin.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// Might add some more error checking in case database fails.

	private void createNewRecipe(HttpServletRequest request, HttpServletResponse response) {
		RecipeLogicImpl userCtrl = new RecipeLogicImpl();
		InputStream fileContent = null;
		String recipeName = null;
		String permission = null;
		String category = null;
		List<String> ingredients = new ArrayList<String>();
		List<String> instructions = new ArrayList<String>();
		try {
			List<FileItem> formItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			for (FileItem formItem : formItems)
			{
				if (formItem.isFormField())
				{
					String name = formItem.getFieldName();
					
					if (name.equals("recipename"))
					{
						recipeName = formItem.getString();
					}
					else if (name.equals("ingredients"))
					{
						ingredients.add(formItem.getString());
					}
					else if (name.equals("steps"))
					{
						instructions.add(formItem.getString());
					}
					else if (name.equals("visibility"))
					{
						permission = formItem.getString();
					}
					else if (name.equals("category"))
					{
						category = formItem.getString();
					}
				}
				else
				{
					fileContent = formItem.getInputStream();
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession(false);
		int user_id = (Integer)(session.getAttribute("id"));
		int check = userCtrl.createRecipe(recipeName, ingredients, instructions, permission, user_id, fileContent, category);
		if (check != 0)
		{
			DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(df.build());
			String templateName = "createrecipe.ftl";
			root.put("fname", session.getAttribute("firstName"));
			root.put("checklogin", 1);
			process.processTemplate(templateName, root, request, response);
		}
	}

	private void viewRecipes(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session == null)
		{
			DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(df.build());
			String templateName = "viewrecipe.ftl";
			root.put("checklogin", 0);
			root.put("fname", "Not Logged In");
			process.processTemplate(templateName, root, request, response);
		}
		else
		{
			HttpSession session2 = request.getSession();
			DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(df.build());
			String templateName = "viewrecipe.ftl";
			root.put("fname", session2.getAttribute("firstName"));
			root.put("checklogin", 1);
			process.processTemplate(templateName, root, request, response);
		}
	}

	private void goToHome(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session == null)
		{
			DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(df.build());
			String templateName = "homepage.ftl";
			root.put("checklogin", 0);
			root.put("fname", "Not Logged In");
			process.processTemplate(templateName, root, request, response);
		}
		else
		{
			HttpSession session2 = request.getSession();
			DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
			SimpleHash root = new SimpleHash(df.build());
			String templateName = "homepage.ftl";
			root.put("fname", session2.getAttribute("firstName"));
			root.put("checklogin", 1);
			process.processTemplate(templateName, root, request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void openHomePage(User user, HttpServletRequest request, HttpServletResponse response) {
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		String templateName = "homepage.ftl";
		
		HttpSession session = request.getSession();
		synchronized(session) {
			session.setMaxInactiveInterval(-1);
			session.setAttribute("id", user.getId());
			session.setAttribute("firstName", user.getFirst_name());
			session.setAttribute("lastName", user.getLast_name());
			session.setAttribute("username", user.getUsername());
			session.setAttribute("email", user.getEmail());
			session.setAttribute("picture", user.getImage64());
		}
		root.put("fname", session.getAttribute("firstName"));
		root.put("checklogin", 1);
		
		process.processTemplate(templateName, root, request, response);
	}
	
	public void reloadLoginPage(HttpServletRequest request, HttpServletResponse response) {
		DefaultObjectWrapperBuilder df = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(df.build());
		String templateName = "signin.ftl";
		process.processTemplate(templateName, root, request, response);
	}
	
	private void addNewUser(HttpServletRequest request, HttpServletResponse response) {
		String fname = request.getParameter("firstName");
		String lname = request.getParameter("lastName");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		RecipeLogicImpl userCtrl = new RecipeLogicImpl();
		
		userCtrl.addUser(fname, lname, username, password, email);
		
		try {
			response.sendRedirect("signup.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Searched(HttpServletRequest request, HttpServletResponse response, String category, String term) {		
		RecipeLogicImpl userCtrl = new RecipeLogicImpl();
		List<Recipe> recipes = new ArrayList<Recipe>();
		recipes = userCtrl.Search(category, term);
		
		Gson g = new Gson();
		response.setContentType("application/json");
		try {
			response.getWriter().write(g.toJson(recipes));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Rank(HttpServletRequest request, HttpServletResponse response, int id, int rank) {		
		System.out.println("SERVLET: RANK = " + rank);
		RecipeLogicImpl userCtrl = new RecipeLogicImpl();
		int i = userCtrl.Ranked(id, rank);
	}

	private void shareRecipe(int recipeID, int myID, ArrayList<Integer> friendsList)
	{
	RecipeLogicImpl userCtrl = new RecipeLogicImpl();

	for(int i = 0; i < friendsList.size(); i++)
	{
	userCtrl.shareRecipe(recipeID, myID, friendsList.get(i));
	}
	}

	//James
	//Get's list of friends for myProfile
	private void getMyFriends(HttpServletResponse response, int id)
	{
	RecipeLogicImpl userCtrl = new RecipeLogicImpl();
	List<User> friendList = userCtrl.getFriends(id);

	//Put the friends into JSON
	Gson g = new Gson();
	response.setContentType("application/json");
	try {
	response.getWriter().write(g.toJson(friendList));
	} catch (IOException e) {
	e.printStackTrace();
	}
	}

}
