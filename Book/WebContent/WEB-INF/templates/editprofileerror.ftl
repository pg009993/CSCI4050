<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html>
<head>
<title>Eshop a Flat E-Commerce Bootstrap Responsive Website Template | Register :: w3layouts</title>
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
					<ul>
						<li><font color="white">Welcome, ${username} </font></li>
						<li><form action="BookServlet"><button name="editprofile">Edit Profile</button></form></a></li>	
						<a href="signin.html"><span class="glyphicon glyphicon-user"> </span>Logout</a></li>
					</ul>
				</div>
				<div class="header-right">
						<div class="cart box_1">
							<a href="checkout.html">
								<h3> <span class="simpleCart_total"> $0.00 </span> (<span id="simpleCart_quantity" class="simpleCart_quantity"> 0 </span>)<img src="images/bag.png" alt=""></h3>
							</a>	
							<p><a href="javascript:;" class="simpleCart_empty">Empty cart</a></p>
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
					<h1><a href="index.html"><span>E</span> -Shop</a></h1>
				</div>
	    </div>
	    <!--/.navbar-header-->
	
		<!-- registration-form -->
<div class="registration-form">
	<div class="container">
	<div class="dreamcrub">
			   	 <ul class="breadcrumbs">
                    <li class="home">
                       <a href="index.html" title="Go to Home Page">Home</a>&nbsp;
                       <span>&gt;</span>
                    </li>
                    <li class="women">
                       Edit Profile
                    </li>
                </ul>
                <ul class="previous">
                	<li><a href="index.html">Back to Previous Page</a></li>
                </ul>
                <div class="clearfix"></div>
			   </div>

			<!--JS function to ensure passwords match--> 
			   <script type="text/javascript">
			   	function checkPassword(){
			   		var password = $("#password").val();
			   		var password2 = $("#password2").val();
			   	
			   	if(password != password2){
   					 $("#nonMatch").html("Passwords do not match!");
   					 $('#submit').prop('disabled', true);
			   		} 
			   		else{
			   			$("#nonMatch").html("Passwords match!");
			   			$('#submit').prop('disabled', false);
			   		}
			   	}

			   $(document).ready(function(){
   					$("#password, #password2").keyup(checkPassword);
				});
			   </script>

		<h2>Edit Profile</h2>
		<div class="registration-grids">
			<div class="reg-form">
				<div class="reg">
					 <p>Welcome, please enter the following details to continue.</p>
					 <p>If you have previously registered with us, <a href="#">click here</a></p>
					 
 					<b><font color="red" size="4"> Username taken. Please try again.</font></b> <br />

					 <form action="BookServlet">
						 <ul>
							 <li class="text-info">First Name: </li>
							 <li><input type="text" value="${firstname}" name="firstname"></li>
						 </ul>
						 <ul>
							 <li class="text-info">Last Name: </li>
							 <li><input type="text" value="${lastname}" name="lastname"></li>
						 </ul>	

						<ul>
							<li class="text-info">Birth Date: </li>

							<li> 
								<select name="DOBMonth">
								<option>Month</option>
								<option value="January">January</option>
								<option value="Febuary">Febuary</option>
								<option value="March">March</option>
								<option value="April">April</option>
								<option value="May">May</option>
								<option value="June">June</option>
								<option value="July">July</option>
								<option value="August">August</option>
								<option value="September">September</option>
								<option value="October">October</option>
								<option value="November">November</option>
								<option value="December">December</option>
							</select>
							<select name="DOBDay">
								<option>Day</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
								<option value="13">13</option>
								<option value="14">14</option>
								<option value="15">15</option>
								<option value="16">16</option>
								<option value="17">17</option>
								<option value="18">18</option>
								<option value="19">19</option>
								<option value="20">20</option>
								<option value="21">21</option>
								<option value="22">22</option>
								<option value="23">23</option>
								<option value="24">24</option>
								<option value="25">25</option>
								<option value="26">26</option>
								<option value="27">27</option>
								<option value="28">28</option>
								<option value="29">29</option>
								<option value="30">30</option>
								<option value="31">31</option>
							</select>
							<select name="DOBYear">
								<option>Year</option>
									<option value="1999">1999</option>
									<option value="1998">1998</option>
									<option value="1997">1997</option>
									<option value="1996">1996</option>
									<option value="1995">1995</option>
									<option value="1994">1994</option>
								<option value="1993">1993</option>
								<option value="1992">1992</option>
								<option value="1991">1991</option>
								<option value="1990">1990</option>
								<option value="1989">1989</option>
								<option value="1988">1988</option>
								<option value="1987">1987</option>
								<option value="1986">1986</option>
								<option value="1985">1985</option>
								<option value="1984">1984</option>
								<option value="1983">1983</option>
								<option value="1982">1982</option>
								<option value="1981">1981</option>
								<option value="1980">1980</option>
								<option value="1979">1979</option>
								<option value="1978">1978</option>
								<option value="1977">1977</option>
								<option value="1976">1976</option>
								<option value="1975">1975</option>
								<option value="1974">1974</option>
								<option value="1973">1973</option>
								<option value="1972">1972</option>
								<option value="1971">1971</option>
								<option value="1970">1970</option>
								<option value="1969">1969</option>
								<option value="1968">1968</option>
								<option value="1967">1967</option>
								<option value="1966">1966</option>
								<option value="1965">1965</option>
								<option value="1964">1964</option>
								<option value="1963">1963</option>
								<option value="1962">1962</option>
								<option value="1961">1961</option>
								<option value="1960">1960</option>
								<option value="1959">1959</option>
								<option value="1958">1958</option>
								<option value="1957">1957</option>
								<option value="1956">1956</option>
								<option value="1955">1955</option>
								<option value="1954">1954</option>
								<option value="1953">1953</option>
								<option value="1952">1952</option>
								<option value="1951">1951</option>
								<option value="1950">1950</option>
								<option value="1949">1949</option>
								<option value="1948">1948</option>
								<option value="1947">1947</option>
							</select> 
							</li>
						</ul>


						<ul>
							<li class="text-info">Gender: </li>
							<li>
								<input type="radio" name="gender" value="Male"> Male
    							<input type="radio" name="gender" value="Female"> Female<br>
    						</li>
						</ul>
						<ul>
							 <li class="text-info">Email: </li>
							 <li><input type="text" value="${email}" name="email"></li>
						 </ul>
						 <ul>
						 	<li class="text-info">Old Password: </li>
						 	<li><input type="password" name="oldpass" id="oldpass"></li>
						 </ul>			 			 
						 <ul>
							 <li class="text-info">New Password: </li>
							 <li><input type="password" onchange="checkPassword(); value="" name="password" id="password" required></li>
							<div class="matchClass" id="nonMatch"></div>
						 </ul>
						 <ul>
							 <li class="text-info"">Re-enter New Password:</li>
							 <li><input type="password" onchange="checkPassword(); value="" name="password2" id="password2" required></li>
						 </ul>
						 <ul>
							 <li class="text-info">Mobile Number:</li>
							 <li><input type="text" value="${phone}" name="number"></li>
						 </ul>		
						 <ul>
							 <li class="text-info">Street:</li>
							 <li><input type="text" value="${street}" name="street"></li>
						 </ul>	
						  <ul>
							 <li class="text-info">City:</li>
							 <li><input type="text" value="${city}" name="city"></li>
						 </ul>	
						  <ul>
							 <li class="text-info">State:</li>
							 <li>  <select name="state">
								 <option> - State - </option>
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
							 <li><input type="text" value="${zip}" name="zip"></li>
						 </ul>					
						 <input type="submit" value="EDIT PROFILE" name="submitedit" id="submit">
						 <p class="click">By clicking this button, you are agree to my  <a href="#">Policy Terms and Conditions.</a></p> 
					 </form>
				 </div>
			</div>
			<div class="reg-right">
				 <h3>Completely Free Account</h3>
				 <div class="strip"></div>
				 <p>Pellentesque neque leo, dictum sit amet accumsan non, dignissim ac mauris. Mauris rhoncus, lectus tincidunt tempus aliquam, odio 
				 libero tincidunt metus, sed euismod elit enim ut mi. Nulla porttitor et dolor sed condimentum. Praesent porttitor lorem dui, in pulvinar enim rhoncus vitae. Curabitur tincidunt, turpis ac lobortis hendrerit, ex elit vestibulum est, at faucibus erat ligula non neque.</p>
				 <h3 class="lorem">Lorem ipsum dolor.</h3>
				 <div class="strip"></div>
				 <p>Tincidunt metus, sed euismod elit enim ut mi. Nulla porttitor et dolor sed condimentum. Praesent porttitor lorem dui, in pulvinar enim rhoncus vitae. Curabitur tincidunt, turpis ac lobortis hendrerit, ex elit vestibulum est, at faucibus erat ligula non neque.</p>
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