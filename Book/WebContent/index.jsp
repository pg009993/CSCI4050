<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html>
<head>
<title>Eshop Main</title>
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery.min.js"></script>
<!-- Custom Theme files -->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- Custom Theme files -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Eshop Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--webfont-->
<!-- for bootstrap working -->
<script type="text/javascript" src="js/bootstrap-3.1.1.min.js"></script>
<!-- //for bootstrap working -->
<!-- cart -->
<script src="js/simpleCart.min.js"> </script>
<!-- cart -->
<link rel="stylesheet" href="css/flexslider.css" type="text/css"
	media="screen" />
</head>
<body>
	<!-- header-section-starts -->
	<div class="header">
		<div class="header-top-strip">
			<div class="container">
				<div class="header-top-left">
					<ul>
						<li><a href="signin.html"><span
				
								class="glyphicon glyphicon-user"> </span>Login</a></li>
								
								
						<li><a href="register.html"><span
								class="glyphicon glyphicon-lock"> </span>Create an Account</a></li>
					</ul>
				</div>
				<div class="header-right">
					<div class="cart box_1">

						<p>
							<a href="javascript:;" class="simpleCart_empty">Empty cart</a>
						</p>
						<div class="clearfix"></div>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!-- header-section-ends -->
	<div class="banner-top">
		<div class="container">
			<nav class="navbar navbar-default" role="navigation">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<div class="logo">
						<h1>
							<a href="index.html"><span>E</span> -Shop</a>
						</h1>
					</div>
				</div>
				<!--/.navbar-header-->

				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="index.html">Home</a></li>
						<li class="dropdown">
							<ul class="dropdown-menu multi-column columns-3">
								<div class="row">

									<div class="clearfix"></div>
								</div>
							</ul>
						</li>

						<li><a href="contact.html">CONTACT</a></li>
					</ul>
				</div>
				<!--/.navbar-collapse-->
			</nav>
			<!--/.navbar-->
		</div>
	</div>
	

	<div class="news-letter">
			<div class="container">
				<div class="join">
					<h6>Looking for something? </h6>
					<div class="sub-left-right">
						
		<form action="BookServlet" method="post">
			<div>
			<span>Search:</span>
			<input type="text"  name="searchfor"> 
 			<select name="searchtype">
 				<option value="author" selected >Author</option>
 				<option value="title">Title</option>
 				<option value="genre">Genre</option>
 			</select>
 			</div>
 		<div>
 		</div>
 		<input type="submit" value="Search" name="SearchBooks"> 
	</form>
					</div>
					<div class="clearfix"> </div>
				</div>
			</div>
		</div>



	
	
	
	<img src="books.jpg" style="width: 1300px; height: 428px;">









	<div class="banner-info">
		<h3>Books</h3>
		<p>Start your shopping here...</p>
	</div>

	</div>
	<div class="clearfix"></div>
	</div>
	</div>
	</div>
	<!-- content-section-starts-here -->
	<div class="container">
		<div class="main-content">
			<div class="online-strip">
				<div class="col-md-4 follow-us">
					<h3>
						follow us : <a class="twitter" href="#"></a><a class="facebook"
							href="#"></a>
					</h3>
				</div>
				<div class="col-md-4 shipping-grid">
					<div class="shipping">
						<img src="images/shipping.png" alt="" />
					</div>
					<div class="shipping-text">
						<h3>Free Shipping</h3>
						<p>on orders over $ 199</p>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="col-md-4 online-order">
					<p>Order online</p>
					<h3>Tel:999 4567 8902</h3>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="products-grid">
				<header>
					<h3 class="head text-center">Products</h3>
				</header>


				<%@page import="java.sql.*"%>
				<%
											try
											{
											Class.forName("com.mysql.jdbc.Driver");
											String url="jdbc:mysql://localhost:3306/ecommerce";
											String username="root";
											String password="paw22nvc3";
											String query="select * from books where quantity > 0 order by title";
											Connection conn=DriverManager.getConnection(url,username,password);
											Statement stmt=conn.createStatement();
											ResultSet rs=stmt.executeQuery(query);
											while(rs.next())
											{
											
											%>


				<div class="col-md-4 product simpleCart_shelfItem text-center">
					<a><img src="images/p2.jpg" alt="" /></a>
					<div class="mask"></div>
					<form action="BookServlet" method="post">
						<a class="product_name"><%=rs.getString("title") %></a>
						<p>
							<a class="item_add" href="#"><i></i> <span class="item_price"><%=rs.getString("price")%></span></a>
						</p>
						<a class="product_name">Publisher: <%=rs.getString("publisher") %></a>
						<a class="product_name">Author: <%=rs.getString("author") %></a> <a
							class="product_name">In Stock: <%=rs.getInt("quantity") %></a> <a
							class="product_name">ISBN: <%=rs.getString("ISBN") %></a> <a
							class="product_name">Genre: <%=rs.getString("genre") %></a> 
							<input name="isbn" type="hidden" value="<%=rs.getString("ISBN") %>">
						<input name="title" type="hidden" value="<%=rs.getString("title") %>"> 
						<input name="price" type="hidden" value="<%=rs.getString("price") %>">
						<input type="submit" value="Add To Cart" name="addToCart">

					</form>
				</div>








				<%
									}
									%>

				</table>
				<%
    								rs.close();
   							    stmt.close();
   							    conn.close();
  								  }
								catch(Exception e)
								{
								    e.printStackTrace();
								    }
								%>
			</div>
		</div>

	</div>

	<!-- content-section-ends-here -->
	
	<div class="footer">
		<div class="container">
			<div class="footer_top">
				<div class="span_of_4">

					<div class="clearfix"></div>
				</div>
			</div>
			<div class="cards text-center">
				<img src="images/cards.jpg" alt="" />
			</div>
			<div class="copyright text-center">
				<p>
					© 2015 Eshop. All Rights Reserved | Design by <a
						href="http://w3layouts.com"> W3layouts</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>