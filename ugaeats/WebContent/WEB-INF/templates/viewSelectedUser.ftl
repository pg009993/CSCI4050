<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UGA Eats</title>
<script src="JS/jquery.js" type="text/javascript"></script>
<script src="JS/viewUser.js" type="text/javascript"></script>
<link href="CSS/index.css" type="text/css" rel="stylesheet"/>
</head>
<body>
	<input type="hidden" id="userId" value="${otherId}"/>
	<h1>${user.getUsername()}</h1>
	<div class="topDiv">
		<form class="formHome" action="RecipeServlet" method="get">
			<div class="buttons">
			<button class="left button" name="home">Home</button>
			<button class="left button" name="viewRecipe">View Recipes</button>
			<button class="left button" name="viewProfile">View Profile</button>
			</div>
			<#if checklogin == 0>
			<div class="sign">
			<button class="button right" name="openSigninPage">Sign-In</button>
			<button class="button right" name="openSignupPage">Sign-Up</button>
			</div>
			<#elseif checklogin == 1>
			<div class="sign">
			<p class="right label">${fname}</p>
			<button class="button" name="logout">Logout</button>
			</div>
			</#if>
			<img class="logo" src="https://pbs.twimg.com/profile_images/378800000274109762/d435f305c1c6737cf29659aa6592537c.jpeg">
		</form>
	</div>
	<div><img src="data:image/jpeg;base64,${user.getImage64()}"/></div>
	<form class="formHome" action="RecipeServlet" method="get">
		<div id="recipeList">RecipeHolder</div>
		<div id="recipeView">RecipeViewer</div>
	</form>
</body>
</html>