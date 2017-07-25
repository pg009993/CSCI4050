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
<title>Eshop Checkout</title>
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
						<li><font color="white">Welcome, <%= request.getAttribute("username") %></font></li>
						<li><form action="BookServlet"><button name="editprofile">Edit Profile</button></form></a></li>	
						<li><form action="BookServlet"><button name="logout">Logout</button></form></a></li>
					</ul>
				</div>
				<div class="header-right">
					<div class="cart box_1">
						<a href="checkout.html">
							<h3>
								<span class="simpleCart_total"> $0.00 </span> (<span
									id="simpleCart_quantity" class="simpleCart_quantity"> 0
								</span>)<img src="images/bag.png" alt="">
							</h3>
						</a>
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
	<div class="inner-banner">
		<div class="container">
			<div class="banner-top inner-head">
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

						</ul>
					</div>
					<!--/.navbar-collapse-->
				</nav>
				<!--/.navbar-->
			</div>
		</div>
	</div>
	<!-- checkout -->
	<div class="cart-items">
		<div class="container">
			<div class="dreamcrub">
				<ul class="breadcrumbs">
					<li class="home"><a href="index.html" title="Go to Home Page">Home</a>&nbsp;
						<span>&gt;</span></li>
					<li class="women">Cart</li>
				</ul>
				<ul class="previous">
					<li><a href="index.html">Back to Previous Page</a></li>
				</ul>
				<div class="clearfix"></div>
			</div>
			<h2>MY SHOPPING BAG</h2>
			<br />
			</div>
			</div>
			<%@page import="java.sql.*"%>
			<%
											try
											{
											Class.forName("com.mysql.jdbc.Driver");
											String url="jdbc:mysql://localhost:3306/ecommerce";
											String username="root";
											String password="paw22nvc3";
											String uname = (String) request.getAttribute("username");
											String query="select * from cart where username='" + uname + "';";
											Connection conn=DriverManager.getConnection(url,username,password);
											Statement stmt=conn.createStatement();
											ResultSet rs=stmt.executeQuery(query);
											while(rs.next())
											{
											
											%>


			<br /> 
			<div class="col-md-4 product simpleCart_shelfItem text-center">
				<a><img src="images/p2.jpg" alt="" /></a>
				<form action="BookServlet" method="post">
								<h3>
									<a href="#"> <%=rs.getString("title") %> </a> <br />
									<span>ISBN: <%=rs.getString("ISBN") %></span> <br />
									<span><%=rs.getString("price") %></span>
								<input name="cartid" type="hidden" value="<%=rs.getInt("cartid") %>">
									
								</h3>
								<input type="submit" value="Remove From Cart" name="removeFromCart">
									<div class="clearfix"></div>
												</form>	
							</div>
		
		<%
									}
									%>

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

	<div class="container">

		<div class="join">
			<div class="sub-left-right">
				<form>
					<input type="hidden" value="Complete Order" />
				</form>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>

	<div class="news-letter">
		<div class="container">
			<div class="join">
				<h5>Enter Promo Code: </h5>
				<div class="sub-left-right">
					<form>
					<input type="text" name="code">
						<input type="submit" name="promoCode" value="Give Me A Discount">	
					</form>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<br />


	<!-- //checkout -->
	<div class="news-letter">
		<div class="container">
			<div class="join">
				<h6>Total: $ <%= request.getAttribute("total") %></h6>
				<div class="sub-left-right">
					<form>
						<input type="submit" name="completeOrder" value="Complete Order">	
					</form>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>





	<div class="footer">
		<div class="container">
			<div class="footer_top">
				<div class="span_of_4">
					<div class="col-md-3 span1_of_4">

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