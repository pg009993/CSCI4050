package edu.uga.cs4300.persistlayer;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;
import edu.uga.cs4300.objectlayer.Recipe;
import edu.uga.cs4300.objectlayer.User;
import sun.misc.BASE64Encoder;

public class RecipePersistImpl {
	
	// Not used anymore

	public int persistUser(User u) {
		Connection c = DbAccessImpl.connect();
		String query = "INSERT INTO users (first_name,last_name,username,password,email) VALUES"
				+ "('" + u.getFirst_name() + "','" + u.getLast_name() + "','"
				+ u.getUsername() + "','" + u.getPassword() + "','" + u.getEmail() + "')";
		int temp = DbAccessImpl.create(c,  query);
		DbAccessImpl.disconnect(c);
		return temp;
	} // persistUser()
	
	public int persistUserProfile(User u, InputStream input) {
		Connection c = DbAccessImpl.connect();
		int temp = 0;
		String query = "INSERT INTO users (first_name,last_name,username,password,email,picture) VALUES"
				+ " (?,?,?,?,?,?)";
		PreparedStatement prep = null;
		try {
			prep = c.prepareStatement(query);
			prep.setString(1, u.getFirst_name());
			prep.setString(2, u.getLast_name());
			prep.setString(3, u.getUsername());
			prep.setString(4, u.getPassword());
			prep.setString(5, u.getEmail());
			prep.setBlob(6, input);
		
			temp = prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbAccessImpl.disconnect(c);
		return temp;
	}
	
	public User checkUser(String username, String password) {
		Connection connect = DbAccessImpl.connect();
		User temp = null;
		String query = "SELECT * FROM users WHERE username = " + "'" + username + "'" + " AND password = " + "'" + password + "'";
		ResultSet rs = DbAccessImpl.retrieve(connect, query);
		BASE64Encoder encoder = new BASE64Encoder();
		try {
		if (!rs.next()) {
		} 
		else 
		{
			int blobLength;
			byte[] blobArray = null;
			String image64 = null;
			Blob blob = rs.getBlob("picture");
			blobLength = (int) blob.length();
			blobArray = blob.getBytes(1, blobLength);
			image64 = encoder.encode(blobArray);
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			String userName = rs.getString("username");
			String passWord = rs.getString("password");
			String email = rs.getString("email");
			int id = rs.getInt("id");
			temp = new User(id, firstName, lastName, userName, passWord, email, image64);
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbAccessImpl.disconnect(connect);
		return temp;
	}
	
	// Works to get all recipes along with username
	
	// Make sure to only get top 10 based on rating
	
	public List<Recipe> getPopularRecipes() {
		String query = "SELECT * FROM recipes r JOIN users u ON r.users_id = u.id WHERE permissions = 'public' ORDER BY rank DESC LIMIT 10";
		Connection connect = DbAccessImpl.connect();
		ResultSet set = DbAccessImpl.retrieve(connect, query);
		List<Recipe> temp = new ArrayList<Recipe>();
		BASE64Encoder encoder = new BASE64Encoder();
		try {
			while (set.next())
			{
				int blobLength;
				byte[] blobArray = null;
				String image64 = null;
				Blob blob = set.getBlob("recipe_image");
				blobLength = (int) blob.length();
				blobArray = blob.getBytes(1, blobLength);
				image64 = encoder.encode(blobArray);
				
				int recipeId = set.getInt(1);
				String recipeName = set.getString("recipe_name");
				Float rank = set.getFloat("rank");
				int userId = set.getInt("users_id");
				String username = set.getString("username");
				
				Recipe recipe = new Recipe(recipeId, recipeName, rank, username, image64, userId);
				temp.add(recipe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbAccessImpl.disconnect(connect);
		return temp;
	}

	// Doesnt work
	
	public List<Recipe> getRecipes() {
		List<Recipe> recipes = new ArrayList<Recipe>();
		Connection connect = DbAccessImpl.connect();
		String query = "SELECT * FROM recipes JOIN users u ON u.id = users_id";
		ResultSet rs = DbAccessImpl.retrieve(connect, query);
		BASE64Encoder encoder = new BASE64Encoder();
		try {
			while (rs.next()) {
				int id = rs.getInt(1);
				int user_id = rs.getInt(7);
				String name = rs.getString("recipe_name");
				float rank = rs.getFloat("rank");
				String permissions = rs.getString("permissions");
				String username = rs.getString("username");
				Blob image = rs.getBlob("recipe_image");
				int blobLength;
				byte[] blobArray;
				String image64 = null;
				try {
					blobLength = (int) image.length();
					blobArray = image.getBytes(1, blobLength);
					image64 = encoder.encode(blobArray);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (permissions.equals("public")) {
				Recipe recipe = new Recipe(id, name, rank, username, image64, user_id);
				recipes.add(recipe);
				}
				//Blob image = rs.getBlob("recipe_image");
				//Recipe recipe = new Recipe(id, name, rank, username, image, user_id);
				/*if(permissions.equals("public")) {
					recipes.add(recipe);
				}*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recipes;
	}

	public int addRecipeToDatabase(String recipeName, List<String> ingredients, List<String> instructions,
			String permission, int user_id, InputStream fileContent, String category) {
		Connection c = DbAccessImpl.connect();
		String query = "INSERT INTO recipes (recipe_name,permissions,rank,num_ratings,users_id,recipe_image,sum_ratings,category) Values"
				+ " (?,?,?,?,?,?,?,?)";
		String query2 = "INSERT INTO ingredients (recipes_id,ingredient) VALUES (?,?)";
		String query3 = "INSERT INTO instructions (recipes_id,instruction_number,instruction) VALUES (?,?,?)";
		
		int temp = 0;
		int recipeId = 0;
		PreparedStatement prep = null;
		try {
			prep = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			prep.setString(1, recipeName);
			prep.setString(2, permission);
			prep.setFloat(3, 0);
			prep.setInt(4, 0);
			prep.setInt(5, user_id);
			prep.setBlob(6, fileContent);
			prep.setInt(7, 0);
			prep.setString(8, category);
			temp = prep.executeUpdate();
			
			ResultSet set = prep.getGeneratedKeys();
			if (set.next())
			{
			recipeId = set.getInt(1);	
			for (int i = 0; i < ingredients.size(); i++)
				{
					prep = c.prepareStatement(query2);
					prep.setInt(1, recipeId);
					prep.setString(2, ingredients.get(i));
					temp += prep.executeUpdate();
				}
			for (int i = 0; i < instructions.size(); i++)
			{
				prep = c.prepareStatement(query3);
				prep.setInt(1, recipeId);
				prep.setInt(2, i + 1);
				prep.setString(3, instructions.get(i));
				temp += prep.executeUpdate();
			}
		}
		else 
			{
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbAccessImpl.disconnect(c);
		return temp;
	}

	public Recipe getRecipe(int id) {
		String query = "SELECT * FROM recipes r JOIN users u ON r.users_id = u.id "
				+ "WHERE r.id = '" + id + "'";
		Connection connect = DbAccessImpl.connect();
		ResultSet set = DbAccessImpl.retrieve(connect, query);
		//List<Recipe> temp = new ArrayList<Recipe>();
		Recipe temp = null;
		BASE64Encoder encoder = new BASE64Encoder();
		String image64 = null;
		int recipeId = 0;
		String recipeName = null;
		String permission = null;
		Float rank = 0.0f;
		int userId = 0;
		String username = null;
		
		try {
			while (set.next())
			{
				int blobLength;
				byte[] blobArray = null;
				//String image64 = null;
				Blob blob = set.getBlob("recipe_image");
				blobLength = (int) blob.length();
				blobArray = blob.getBytes(1, blobLength);
				image64 = encoder.encode(blobArray);
				
				recipeId = set.getInt(1);
				recipeName = set.getString("recipe_name");
				permission = set.getString("permissions");
				rank = set.getFloat("rank");
				userId = set.getInt("users_id");
				username = set.getString("username");
				
				temp = new Recipe(recipeId, recipeName, rank, username, image64, userId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		List<String> ingredients = new ArrayList<String>();
		String query2 = "SELECT * FROM ingredients WHERE recipes_id = '" + id + "'";
		set = DbAccessImpl.retrieve(connect, query2);
		try {
			while (set.next()) 
			{
				String tempIngredient = set.getString("ingredient");
				ingredients.add(tempIngredient);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<String> instructions = new ArrayList<String>();
		String query3 = "SELECT * FROM instructions WHERE recipes_id = '" + id + "'";
		set = DbAccessImpl.retrieve(connect, query3);
		try {
			while (set.next()) 
			{
				String tempInstruction = set.getString("instruction");
				instructions.add(tempInstruction);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		temp = new Recipe(recipeId, recipeName, rank, username, image64, userId, ingredients, instructions);
		DbAccessImpl.disconnect(connect);
		return temp;
	}
	
	//View recipe depending on what category the user chooses.
	//Calls database to select based on user input.
	
	public List<Recipe> viewByCategory(String category) {
		String query = null;
		if (!category.equals("All"))
		{
		query = "SELECT * FROM recipes r JOIN users u ON r.users_id = u.id WHERE permissions = 'public' AND"
				+ " category = '" + category + "'";
		//query = "SELECT * FROM recipes WHERE category = '" + category + "'" + " AND permissions = 'public'";
		}
		else
		{
		query = "SELECT * FROM recipes r JOIN users u ON r.users_id = u.id WHERE permissions = 'public'";
		}
		BASE64Encoder encoder = new BASE64Encoder();
		List<Recipe> list = new ArrayList<Recipe>();
		Connection connect = DbAccessImpl.connect();
		ResultSet set = DbAccessImpl.retrieve(connect, query);
		try {
			while(set.next()) {
				int blobLength;
				byte[] blobArray = null;
				String image64 = null;
				Blob blob = set.getBlob("recipe_image");
				blobLength = (int) blob.length();
				blobArray = blob.getBytes(1, blobLength);
				image64 = encoder.encode(blobArray);
				
				int recipeId = set.getInt(1);
				String recipeName = set.getString("recipe_name");
				Float rank = set.getFloat("rank");
				int userId = set.getInt("users_id");
				String username = set.getString("username");
				
				Recipe recipe = new Recipe(recipeId, recipeName, rank, username, image64, userId);
				list.add(recipe);
			} //while
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbAccessImpl.disconnect(connect);
		return list;
	}
	
	//Takes in user input for search terms.
	//Searches database depending on user input for the category and search terms.
	
	public List<Recipe> Search(String category, String term) {
		BASE64Encoder encoder = new BASE64Encoder();
		List<Recipe> list = new ArrayList<Recipe>();
		Connection connect = DbAccessImpl.connect();
		if(category.equals("All")) {
			String query = "SELECT * FROM recipes r JOIN users u ON r.users_id = u.id WHERE recipe_name LIKE '%" + term + "%' AND permissions = 'public'";
			ResultSet set = DbAccessImpl.retrieve(connect, query);
			try {
				while(set.next()) {
					int blobLength;
					byte[] blobArray = null;
					String image64 = null;
					Blob blob = set.getBlob("recipe_image");
					blobLength = (int) blob.length();
					blobArray = blob.getBytes(1, blobLength);
					image64 = encoder.encode(blobArray);
					
					int recipeId = set.getInt(1);
					String recipeName = set.getString("recipe_name");
					Float rank = set.getFloat("rank");
					int userId = set.getInt("users_id");
					String username = set.getString("username");
					
					Recipe recipe = new Recipe(recipeId, recipeName, rank, username, image64, userId);
					list.add(recipe);
				} //while
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DbAccessImpl.disconnect(connect);
			return list;
		}//if
		else {
			String query = "SELECT * FROM recipes r JOIN users u ON r.users_id = u.id WHERE recipe_name LIKE '%" + term + "%' AND permissions = 'public' AND category = '" + category + "'";
			ResultSet set = DbAccessImpl.retrieve(connect, query);
			try {
				while(set.next()) {
					int blobLength;
					byte[] blobArray = null;
					String image64 = null;
					Blob blob = set.getBlob("recipe_image");
					blobLength = (int) blob.length();
					blobArray = blob.getBytes(1, blobLength);
					image64 = encoder.encode(blobArray);
					
					int recipeId = set.getInt(1);
					String recipeName = set.getString("recipe_name");
					Float rank = set.getFloat("rank");
					int userId = set.getInt("users_id");
					String username = set.getString("username");
					
					Recipe recipe = new Recipe(recipeId, recipeName, rank, username, image64, userId);
					list.add(recipe);
				} //while
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DbAccessImpl.disconnect(connect);
			return list;
		}//else
	}
	
	
	public List<Recipe> getRecipesByUserId(int id) {
		String query = "SELECT * FROM recipes r JOIN users u ON r.users_id = u.id WHERE permissions = 'public' AND r.users_id = "
				+ "'" + id + "'";
		Connection connect = DbAccessImpl.connect();
		ResultSet set = DbAccessImpl.retrieve(connect, query);
		List<Recipe> temp = new ArrayList<Recipe>();
		BASE64Encoder encoder = new BASE64Encoder();
		try {
			while (set.next())
			{
				int blobLength;
				byte[] blobArray = null;
				String image64 = null;
				Blob blob = set.getBlob("recipe_image");
				blobLength = (int) blob.length();
				blobArray = blob.getBytes(1, blobLength);
				image64 = encoder.encode(blobArray);
				
				int recipeId = set.getInt(1);
				String recipeName = set.getString("recipe_name");
				Float rank = set.getFloat("rank");
				int userId = set.getInt("users_id");
				String username = set.getString("username");
				
				Recipe recipe = new Recipe(recipeId, recipeName, rank, username, image64, userId);
				temp.add(recipe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbAccessImpl.disconnect(connect);
		return temp;
	}

	public User getInfoUser(int id) {
		Connection c = DbAccessImpl.connect();
		String query = "SELECT id, username, picture FROM users WHERE id = '"
				+ id + "'";
		ResultSet set = DbAccessImpl.retrieve(c, query);
		User u = null;
		BASE64Encoder encoder = new BASE64Encoder();
		try {
			if (set.next())
			{
				int blobLength;
				byte[] blobArray = null;
				String image64 = null;
				Blob blob = set.getBlob("picture");
				blobLength = (int) blob.length();
				blobArray = blob.getBytes(1, blobLength);
				image64 = encoder.encode(blobArray);
				int userid = set.getInt("id");
				String username = set.getString("username");
				u = new User(userid, username, image64);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbAccessImpl.disconnect(c);
		return u;
	}

	public Boolean checkFavoriteRecipes(int recipeId, int userId) {
		Connection c = DbAccessImpl.connect();
		Boolean check = null;
		String query = "SELECT * FROM favoriterecipes WHERE users_id = '" + userId + "' AND recipes_id = '" + recipeId + "'";
		ResultSet set = DbAccessImpl.retrieve(c, query);
		try {
			if (!set.next())
			{
				check = false;
			}
			else
			{
				check = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbAccessImpl.disconnect(c);
		return check;
	}

	public int addToFavorites(int recipeId, int userId) {
		Connection c = DbAccessImpl.connect();
		String query = "INSERT INTO favoriterecipes (users_id,recipes_id) VALUES"
				+ "('" + userId + "','" + recipeId + "')";
		int temp = DbAccessImpl.create(c, query);
		DbAccessImpl.disconnect(c);
		return temp;
	}
	
		
		//James
		//Returns a list of ingredients associated with the recipe ID
		public List<String> getIngredients(int recipeID, Connection con)
		{
			List<String> ingredients = new ArrayList<String>();
			String query = "SELECT ingredient FROM ingredients i WHERE i.recipes_id = " + recipeID;
			ResultSet set = DbAccessImpl.retrieve(con, query);
			
			try{
				while(set.next())
				{
					String ing = set.getString("ingredient");
					ingredients.add(ing);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return ingredients;
		}
		
		
		//James *DELETE LATER*
		public List<String> getInstructions(int recipeID, Connection con) 
		{
			List<String> instructions = new ArrayList<String>();
			String query =  "SELECT instruction FROM instructions i WHERE i.recipes_id = " + recipeID;
			ResultSet set = DbAccessImpl.retrieve(con, query);
			
			try{
				while(set.next())
				{
					String ins = set.getString("instruction");
					instructions.add(ins);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return instructions;
		}
		
		//JAMES
		//Returns list of favorite recipes
		public ArrayList<Recipe> getFavoriteRecipes(int userID)
		{
			//List to be returned
			ArrayList<Recipe> returnList = new ArrayList<Recipe>();
			
			//Variables to store the image
			BASE64Encoder encoder = new BASE64Encoder();
			String image64 = null;
			
			//Connect to database
			Connection connect = DbAccessImpl.connect();
			String query = "SELECT * FROM recipes r JOIN users ON users.id = " + userID + " JOIN favoriterecipes f ON f.users_id =" + userID + " WHERE r.id = f.recipes_id;";
			ResultSet rs = DbAccessImpl.retrieve(connect, query);
			
			try{
				while(rs.next())
				{
					//Get recipe id
					int id = rs.getInt(1);
					//Get recipe name
					String recipeName = rs.getString("recipe_name");
					//Get recipe rank
					float rank = rs.getFloat("rank");
					//Get number of ratings
					int numRating = rs.getInt("num_ratings");
					//Get list of ingredients
					List<String> ingredients  = getIngredients(id, connect);
					//Get list of instructions
					List<String> instructions = getInstructions(id, connect); 
					
					//Get recipe image
					int blobLength;
					byte[] blobArray = null;
					//String image64 = null;
					Blob blob = rs.getBlob("recipe_image");
					blobLength = (int) blob.length();
					blobArray = blob.getBytes(1, blobLength);
					image64 = encoder.encode(blobArray);
					
					String username = rs.getString("username");
					
					int userId = rs.getInt(10);
					
					//Create a temporary recipe
					//Recipe temp = new Recipe(id, recipeName, rank, numRating, ingredients, instructions, image64);
					Recipe temp = new Recipe(id, recipeName, rank, username, image64, userId);
					//Add the temp recipe to returnList
					returnList.add(temp);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return returnList;
		}
		
		//JAMES
		//return a list of the shared recipe 
		public ArrayList<Recipe> getSharedRecipes(int userID)
		{
			//List to be returned
			ArrayList<Recipe> returnList = new ArrayList<Recipe>();
			
			//Variables to store the image
			BASE64Encoder encoder = new BASE64Encoder();
			String image64 = null;
			
			//Connect to database
			Connection connect = DbAccessImpl.connect();
			String query = "SELECT * FROM recipes r JOIN users ON users.id = " + userID + " JOIN sharedrecipes s ON s.friend_id=" + userID + " WHERE s.recipes_id = r.id;";
			ResultSet rs = DbAccessImpl.retrieve(connect, query);
			
			try{
				while(rs.next())
				{
					//Get recipe id
					int id = rs.getInt(1);
					//Get recipe name
					String recipeName = rs.getString("recipe_name");
					//Get recipe rank
					float rank = rs.getFloat("rank");
					//Get number of ratings
					int numRating = rs.getInt("num_ratings");
					//Get list of ingredients
					List<String> ingredients  = getIngredients(id, connect);
					//Get list of instructions
					List<String> instructions = getInstructions(id, connect); 
					
					//Get recipe image
					int blobLength;
					byte[] blobArray = null;
					//String image64 = null;
					Blob blob = rs.getBlob("recipe_image");
					blobLength = (int) blob.length();
					blobArray = blob.getBytes(1, blobLength);
					image64 = encoder.encode(blobArray);
					
					String username = rs.getString("username");
					
					int userId = rs.getInt(10);
					
					//Create a temporary recipe
					//Recipe temp = new Recipe(id, recipeName, rank, numRating, ingredients, instructions, image64);
					Recipe temp = new Recipe(id, recipeName, rank, username, image64, userId);
					//Add the temp recipe to returnList
					returnList.add(temp);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return returnList;
		}
		
		public User getUserByID(int userID)
		{
			//Variables to store the image
			BASE64Encoder encoder = new BASE64Encoder();
			String image64 = null;
			
			//Connect to database
			Connection connect = DbAccessImpl.connect();
			String query = "SELECT * FROM users WHERE users.id =" + userID + ";";
			
			User returnUser = null;
			
			ResultSet rs = DbAccessImpl.retrieve(connect, query);
			try{
				while(rs.next())
				{
					int id = rs.getInt("id");
					String firstName = rs.getString("first_name");
					String lastName = rs.getString("last_name");
					String username = rs.getString("username");
					//Doing this so I can use the same constructor
					String password = "pass";
					String email    = "email";
					
					//Get recipe image
					int blobLength;
					byte[] blobArray = null;
					//String image64 = null;
					Blob blob = rs.getBlob("picture");
					blobLength = (int) blob.length();
					blobArray = blob.getBytes(1, blobLength);
					image64 = encoder.encode(blobArray);
					
					returnUser = new User(id, firstName, lastName, username,password, email, image64);
					
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return returnUser;
		}
		
		//James
		//Gets list of recipes by userID
		public ArrayList<Recipe> persistMyRecipes(int userID)
		{
			//ArrayList to store the list of user's recipes
			ArrayList<Recipe> returnList = new ArrayList<Recipe>();
			
			//Variables to store the image
			BASE64Encoder encoder = new BASE64Encoder();
			String image64 = null;
			
			//Connect to database
			Connection connect = DbAccessImpl.connect();
			//Select all recipes associated with the userID
			String query = "SELECT * FROM recipes JOIN users ON users.id = " + userID + " WHERE recipes.users_id = " + userID + ";";
			ResultSet rs = DbAccessImpl.retrieve(connect, query);
			try {
					while(rs.next())
					{
						//Get recipe id
						int id = rs.getInt(1);
						//Get recipe name
						String recipeName = rs.getString("recipe_name");
						//Get recipe rank
						float rank = rs.getFloat("rank");
						//Get number of ratings
						int numRating = rs.getInt("num_ratings");
						//Get list of ingredients
						List<String> ingredients  = getIngredients(id, connect);
						//Get list of instructions
						List<String> instructions = getInstructions(id, connect); 
						
						//Get recipe image
						int blobLength;
						byte[] blobArray = null;
						//String image64 = null;
						Blob blob = rs.getBlob("recipe_image");
						blobLength = (int) blob.length();
						blobArray = blob.getBytes(1, blobLength);
						image64 = encoder.encode(blobArray);
						
						String username = rs.getString("username");
						
						int userId = rs.getInt(10);
						
						//Create a temporary recipe
						//Recipe temp = new Recipe(id, recipeName, rank, numRating, ingredients, instructions, image64);
						Recipe temp = new Recipe(id, recipeName, rank, username, image64, userId);
						//Add the temp recipe to returnList
						returnList.add(temp);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
	 		return returnList;
		}

		//lakshay
		public List<User> getFriends(int id) {
			Connection connection = DbAccessImpl.connect();
			String query = "SELECT * FROM friendslist JOIN users u ON friend_id = u.id WHERE users_id = " + "'" + id + "'";
			ResultSet rs = DbAccessImpl.retrieve(connection, query);
			List<User> friends = new ArrayList<User>();
			try {
				while (rs.next()) {
					User u = new User(rs.getString("first_name"), rs.getString("last_name"), this.getBlob(rs.getBlob("picture")), rs.getInt(4));
					friends.add(u);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return friends;
		}
		// lakshay
		private String getBlob(Blob picture) {
			BASE64Encoder encoder = new BASE64Encoder();
			String image64 = null;
			try {
				int blobLength = (int) picture.length();
				byte[] blobArray = picture.getBytes(1, blobLength);
				image64 = encoder.encode(blobArray);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return image64;
		}
		
		
		//lakshay 
		public List<User> getFriendRequests(int id) {
			Connection connection = DbAccessImpl.connect();
			String query = "SELECT * FROM friendrequests JOIN users u ON friend_id = u.id WHERE users_id = " + "'" + id + "'";
			ResultSet rs = DbAccessImpl.retrieve(connection, query);
			List<User> requests = new ArrayList<User>();
			try {
				while (rs.next()) {
					User u = new User(rs.getString("first_name"), rs.getString("last_name"), this.getBlob(rs.getBlob("picture")), rs.getInt("friend_id"));
					requests.add(u);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return requests;
		}
		//lakshay
		public String addFriend(String friendId, int userId) throws NumberFormatException, SQLException {
			Connection connection = DbAccessImpl.connect();
			String query = "SELECT * FROM friendrequests WHERE users_id = " + "'" + friendId + "'" + " AND friend_id = " + "'" + userId + "'";
			ResultSet rs = DbAccessImpl.retrieve(connection, query);
			String duplicateRequest = "true";
			if (!rs.next()) {
				query = "INSERT INTO friendrequests (users_id, friend_id) VALUES(?,?)";
				PreparedStatement ps = null;
				try {
					ps = connection.prepareStatement(query);
					ps.setInt(1, Integer.parseInt(friendId));
					ps.setInt(2, userId);
					ps.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				duplicateRequest = "false";
			} 
			return duplicateRequest;
		}
		
		//lakshay
		public String acceptRequest(int userId, String friendId) {
			Connection connection = DbAccessImpl.connect();
			String query = "INSERT INTO friendslist (users_id, friend_id) VALUES(?, ?)";
			PreparedStatement ps = null;
			String success = "notAdded";
			try {
				ps = connection.prepareStatement(query);
				ps.setInt(1, userId);
				ps.setInt(2, Integer.parseInt(friendId));
				ps.executeUpdate();
				query = "INSERT INTO friendslist (users_id, friend_id) VALUES(?, ?)";
				ps = connection.prepareStatement(query);
				ps.setInt(1, Integer.parseInt(friendId));
				ps.setInt(2, userId);
				ps.executeUpdate();
				success = "added";
			} catch (SQLException e) {
				e.printStackTrace();
			}
			deleteRequest(userId, friendId);
			return success;
		}
		
		//lakshay
		public void deleteRequest(int userId, String friendId) {
			Connection connection = DbAccessImpl.connect();
			String query = "DELETE FROM friendrequests WHERE friend_id = ? AND users_id = ?";
			try {
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setInt(1, Integer.parseInt(friendId));
				ps.setInt(2, userId);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//lakshay
		public String rejectRequest(int userId, String friendId) {
			Connection connection = DbAccessImpl.connect();
			String query = "DELETE FROM friendrequests WHERE friend_id = ? AND users_id = ?";
			String success = "notRejected";
			try {
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setInt(1, Integer.parseInt(friendId));
				ps.setInt(2, userId);
				ps.executeUpdate();
				success = "rejected";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return success;
		} 
		
		//lakshay
		public List<User> getUsers(int id) {
			Connection connection = DbAccessImpl.connect();
			String query = "SELECT * FROM friendslist WHERE users_id = " + "'" + id + "'";
			ResultSet rs = DbAccessImpl.retrieve(connection, query);
			List<Integer> intArray = new ArrayList<Integer>();
			try {
				while(rs.next()) {
					intArray.add(rs.getInt("friend_id"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			query = "SELECT * FROM users WHERE id != " + "'" + id + "'";
			for (int i = 0; i < intArray.size(); i++) {
				query += " AND id != " + "'" + intArray.get(i) + "'";
			}
			rs = DbAccessImpl.retrieve(connection, query);
			List<User> users = new ArrayList<User>();
			try {
				while (rs.next()) {
					User u = new User(rs.getString("first_name"), rs.getString("last_name"), this.getBlob(rs.getBlob("picture")), rs.getInt("id"));
					users.add(u);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return users;
		}
		
		//Updates rank of recipe based on user input.
		//Changes some entries in database to update average.

		public int UpdateRank(int id, int rank) {
			System.out.println("PERSIST: RANK = " + rank);
			Connection connect = DbAccessImpl.connect();
			String query = "SELECT num_ratings, sum_ratings FROM recipes r WHERE r.id = '" + id + "'";
			ResultSet rs = DbAccessImpl.retrieve(connect, query);
			float numRatings=0, sumRatings=0;
			try {
				if(rs.next()) {
					numRatings = rs.getInt("num_ratings");
					sumRatings = rs.getInt("sum_ratings");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			numRatings += 1;
			sumRatings += rank;
			float newRank = sumRatings/numRatings;
			System.out.println("NEWRANK: " + newRank);
			
			String query2 = "UPDATE recipes r SET num_ratings = '" + numRatings + "', sum_ratings = '" + sumRatings + "', rank = '" + newRank + "' WHERE r.id = '" + id + "'";
			
			return DbAccessImpl.update(connect, query2);
			}
		
		//Persist layer to check for duplicate username
		//Selects from database to compare existing username to parameter username
		public boolean PersistCheckUser(String username) {
			System.out.println("PERSIST");
			Connection connect = DbAccessImpl.connect();
			String query = "SELECT * FROM users WHERE username = '" + username + "'";
			ResultSet rs = DbAccessImpl.retrieve(connect,  query);
			boolean exists = false;
			
			try {
				while(rs.next()) {
					String name = rs.getString("username");
					if(name.equals(username)) {
						exists = true;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return exists;
		}
		
		//James
		//Share the recipe with your friend
		//myID gets put into the friend_id and shareID gets put into the users_id
		public void shareRecipe(int recipeID, int myID, int shareID)
		{
		Connection connection = DbAccessImpl.connect();
		String query = "SELECT * FROM sharedrecipes s WHERE s.users_id="+shareID+" AND s.friend_id=" + myID + " AND s.recipes_id=" + recipeID +";";	
		ResultSet rs1 = DbAccessImpl.retrieve(connection, query);

		//Check if the recipe has already been shared
		//rs1 == null means that the recipe does not exist in the database yet, so we will add it
		try {
		if(!rs1.next())
		{
		query = "INSERT INTO sharedrecipes (users_id, recipes_id, friend_id) VALUES ("+shareID+","+recipeID+","+myID+")";
		DbAccessImpl.create(connection, query);
		}
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} 
		DbAccessImpl.disconnect(connection);
		}
}
