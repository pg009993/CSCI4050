<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UGA Eats</title>
<script src="JS/jquery.js" type="text/javascript"></script>
<script src="JS/myProfile.js" type="text/javascript"></script>
<link href="CSS/index.css" type="text/css" rel="stylesheet"/>
<link href="CSS/myProfile.css" type="text/css" rel="stylesheet"/>

</head>
<body>
	<div class="topDiv">
		<div class="buttons">
			<form class="formHome" action="RecipeServlet" method="get">
				<button class="button" name="home">Home</button>
				<button class="button" name="viewRecipe">View Recipes</button>
				<button class="button right" name="logout">Logout</button>
				<h4 class="right label">${userName}</h4>
			</form>
		</div>
		<img id="proPic" class="logo" src="data:image/jpeg;base64,${proPic}">
	</div>
	
	<div id="recipesContainer">
		<div class="recipeTab">
			<button class="tabLinks" onclick="openRecipes(event, 'MyRecipes')"> My Recipes</button>
			<button class="tabLinks" onclick="openRecipes(event, 'FavoriteRecipes')" >Favorite Recipes</button>
			<button class="tabLinks" onclick="openRecipes(event, 'SharedRecipes')">Shared Recipes</button>
		</div>
		
		<div id="MyRecipes" class="tabcontent">
			<div id="recipeHolder"></div>
		</div>
		
		<div id="FavoriteRecipes" class="tabcontent">
			<div id="favoriteHolder"></div>
		</div>
		
		<div id="SharedRecipes" class="tabcontent">
			<div id="sharedHolder"></div>
		</div>
		
		<div id="recipeViewer"></div>
	</div>
	
	<div id="friendContainer">

	<h3> Friends </h3>
	<div id="friends"> </div>

</div>
	
	
	
	
</body>
</html>