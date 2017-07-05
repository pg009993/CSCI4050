package edu.uga.cs4300.logiclayer;


import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.uga.cs4300.objectlayer.Recipe;
import edu.uga.cs4300.objectlayer.User;
import edu.uga.cs4300.persistlayer.RecipePersistImpl;

public class RecipeLogicImpl {
	RecipePersistImpl userPersist;
	
	public RecipeLogicImpl() {
		userPersist = new RecipePersistImpl();
	}
	
	public int addUser(String first_name, String last_name, String username, String password, String email) {
		User u = new User(first_name, last_name, username, password, email);
		return userPersist.persistUser(u);
	}
	
	public User validateLogin(String username, String password) {
		return userPersist.checkUser(username, password);
	}
	
	public List<Recipe> retrieveRecipes() {
		return userPersist.getRecipes();
	}

	public int createRecipe(String recipeName, List<String> ingredients, List<String> instructions, String permission,
			int user_id, InputStream fileContent, String category) {
		return userPersist.addRecipeToDatabase(recipeName, ingredients, instructions, permission, user_id, fileContent, category);
	}

	public List<Recipe> getPopRecipes() {
		return userPersist.getPopularRecipes();
	}

	public Recipe getRecipeById(int id) {
		return userPersist.getRecipe(id);
	}

	public int sendNewUserInfo(String fname, String lname, String uname, String password, String email,
			InputStream fileContent) {
		User u = new User(fname, lname, uname, password, email);
		return userPersist.persistUserProfile(u, fileContent);
	}
	
	public ArrayList<Recipe> getMyRecipes(int userID)
	{
		return userPersist.persistMyRecipes(userID);
	}
	
	//Calls persist layer to sort recipes by category and display them.
	public List<Recipe> viewByCategory(String category) {
		return userPersist.viewByCategory(category);
	}
	
	//Calls persist layer to search based on user input  of category and search term.
	public List<Recipe> Search(String category, String term) {
		return userPersist.Search(category, term);
	}

	public List<Recipe> getUserRecipes(int id) {
		return userPersist.getRecipesByUserId(id);
	}

	public User getUserInfo(int id) {
		return userPersist.getInfoUser(id);
	}

	public Boolean checkFavorites(int recipeId, int userId) {
		return userPersist.checkFavoriteRecipes(recipeId, userId);
	}

	public int favoriteRecipe(int recipeId, int userId) {
		return userPersist.addToFavorites(recipeId, userId);
	}
	
	public User getUserByID(int userID)
	{
		return userPersist.getUserByID(userID);
	}
	
	public ArrayList<Recipe> getFavoriteRecipes (int userID)
	{
		return userPersist.getFavoriteRecipes(userID);
	}
	
	public ArrayList<Recipe> getSharedRecipes (int userID)
	{
		return userPersist.getSharedRecipes(userID);
	}
	//james--
	
	public List<User> getFriends(int id) {
		return userPersist.getFriends(id);
	}
	
	public List<User> getUsers(int id) {
		return userPersist.getUsers(id);
	}
	
	public String addFriend(String friendId, int userId) throws NumberFormatException, SQLException {
		return userPersist.addFriend(friendId, userId);
	}
	
	public List<User> getFriendRequests(int id) {
		return userPersist.getFriendRequests(id);
	}
	
	public String acceptRequest(int userId, String friendId) {
		return userPersist.acceptRequest(userId, friendId);
	}
	
	public String rejectRequest(int userId, String friendId) {
		return userPersist.rejectRequest(userId, friendId);
	}

	//Logic layer to implement a ranking system for recipes
	//Calls persist layer
	public int Ranked(int id, int rank) {
		System.out.println("LOGIC: RANK = " + rank);
		return userPersist.UpdateRank(id, rank);
	}
	
	//Logic layer to check for duplicate username
	//Calls persist layer
	public boolean LogicCheckUser(String username) {
		return userPersist.PersistCheckUser(username);
	}
	
	public void shareRecipe(int recipeID, int myID, int shareID)
	{
	userPersist.shareRecipe(recipeID, myID, shareID);
	}
	
}
