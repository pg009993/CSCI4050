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
<title>Eshop Order</title>
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery.min.js"></script>
<!-- Custom Theme files -->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- Custom Theme files -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Eshop Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--webfont-->
<!-- for bootstrap working -->
	<script type="text/javascript" src="js/bootstrap-3.1.1.min.js"></script>
<!-- //for bootstrap working -->
<!-- cart -->
	<script src="js/simpleCart.min.js"> </script>
<!-- cart -->
<link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen" />
</head>
<body>
	<!-- header-section-starts -->
	<div class="header">
		<div class="header-top-strip">
			<div class="container">
				<div class="header-top-left">
					
				</div>
				<div class="header-right">
						<div class="cart box_1">
						<div class="clearfix"> </div>
						</div>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
	<!-- header-section-ends -->
	<div class="inner-banner">
		<div class="container">
			<div class="banner-top inner-head">
				<nav class="navbar navbar-default" role="navigation">
	    <div class="navbar-header">
	        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
	        </button>
				<div class="logo">
					<h1><a href="index.jsp"><span>E</span> -Shop</a></h1>
				</div>
	    </div>
	    <!--/.navbar-header-->
	
		<!-- registration-form -->
<div class="registration-form">
	<div class="container">
	<div class="dreamcrub">
			   	 <ul class="breadcrumbs">
                    <li class="home">
                       <a href="index.jsp" title="Go to Home Page">Home</a>&nbsp;
                       <span>&gt;</span>
                    </li>
                    <li class="women">
                       Order
                    </li>
                </ul>
                <ul class="previous">
                	<li><a href="index.jsp">Back to Previous Page</a></li>
                </ul>
                <div class="clearfix"></div>
			   </div>


		<h2>Order</h2>
		<div class="registration-grids">
			<div class="reg-form">
				<div class="reg">
					 <p>Almost done, please enter the following details to complete your order.</p>
					 <form action="BookServlet" method="post">
						 <ul>
							 <li class="text-info">First Name: </li>
							 <li><input type="text" value="" name="firstname" required></li>
						 </ul>
						 <ul>
							 <li class="text-info">Last Name: </li>
							 <li><input type="text" value="" name="lastname" required></li>
						 </ul>	
						 <ul>
							 <li class="text-info">Mobile Number:</li>
							 <li><input type="text" value="" name="number" required></li>
						 </ul>	
						  <ul>
							 <li class="text-info">Card Number: </li>
							 <li><input type="text" value="" name="cardnumber" required></li>
						 </ul>
						  <ul>
							 <li class="text-info">Security Code: </li>
							 <li><input type="text" value="" name="securitycode" required></li>
						 </ul>	
						 <ul>
							 <li class="text-info">Street:</li>
							 <li><input type="text" value="" name="street" required></li>
						 </ul>	
						  <ul>
							 <li class="text-info">City:</li>
							 <li><input type="text" value="" name="city" required></li>
						 </ul>	
						  <ul>
							 <li class="text-info">State:</li>
							 <li>  <select name="state" required>
								 <option selected="true" disabled="disabled" value=""> - State - </option>
								 <option value="AL">Alabama</option>
								 <option value="AK">Alaska</option>
								 <option value="AZ">Arizona</option>
								 <option value="AR">Arkansas</option>
								 <option value="CA">California</option>
								 <option value="CO">Colorado</option>
								 <option value="CT">Connecticut</option>
								 <option value="DE">Delaware</option>
								 <option value="DC">District Of Columbia</option>
								 <option value="FL">Florida</option>
								 <option value="GA">Georgia</option>
								 <option value="HI">Hawaii</option>
								 <option value="ID">Idaho</option>
								 <option value="IL">Illinois</option>
								 <option value="IN">Indiana</option>
								 <option value="IA">Iowa</option>
								 <option value="KS">Kansas</option>
								 <option value="KY">Kentucky</option>
								 <option value="LA">Louisiana</option>
								 <option value="ME">Maine</option>
								 <option value="MD">Maryland</option>
								 <option value="MA">Massachusetts</option>
								 <option value="MI">Michigan</option>
								 <option value="MN">Minnesota</option>
								 <option value="MS">Mississippi</option>
								 <option value="MO">Missouri</option>
								 <option value="MT">Montana</option>
								 <option value="NE">Nebraska</option>
								 <option value="NV">Nevada</option>
								 <option value="NH">New Hampshire</option>
								 <option value="NJ">New Jersey</option>
								 <option value="NM">New Mexico</option>
								 <option value="NY">New York</option>
								 <option value="NC">North Carolina</option>
								 <option value="ND">North Dakota</option>
								 <option value="OH">Ohio</option>
								 <option value="OK">Oklahoma</option>
								 <option value="OR">Oregon</option>
								 <option value="PA">Pennsylvania</option>
								 <option value="RI">Rhode Island</option>
								 <option value="SC">South Carolina</option>
								 <option value="SD">South Dakota</option>
								 <option value="TN">Tennessee</option>
								 <option value="TX">Texas</option>
								 <option value="UT">Utah</option>
								 <option value="VT">Vermont</option>
								 <option value="VA">Virginia</option>
								 <option value="WA">Washington</option>
								 <option value="WV">West Virginia</option>
								 <option value="WI">Wisconsin</option>
								 <option value="WY">Wyoming</option>
							 </select></li>
						 </ul>
						  <ul>
							 <li class="text-info">Zip: </li>
							 <li><input type="text" value="" name="zip" required></li>
						 </ul>					
						 <input type="submit" value="Complete Order" name="submitOrder" id="submit">
						 <p class="click">By clicking this button, you are agree to my  <a href="#">Policy Terms and Conditions.</a></p> 
					 </form>
				 </div>
			</div>
			
			<div class="clearfix"></div>
		</div>
	</div>
</div>
<!-- registration-form -->

		<div class="news-letter">
			<div class="container">
				<div class="join">
					<h6>JOIN OUR MAILING LIST</h6>
					<div class="sub-left-right">
						<form>
							<input type="text" value="Enter Your Email Here" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Enter Your Email Here';}" />
							<input type="submit" value="SUBSCRIBE" />
						</form>
					</div>
					<div class="clearfix"> </div>
				</div>
			</div>
		</div>
		
		  <div class="cards text-center">
				<img src="images/cards.jpg" alt="" />
		  </div>
		  <div class="copyright text-center">
				<p>� 2015 Eshop. All Rights Reserved | Design by   <a href="http://w3layouts.com">  W3layouts</a></p>
		  </div>
		</div>
		</div>
</body>
</html>