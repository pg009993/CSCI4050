<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <link href="CSS/sign.css" type="text/css" rel="stylesheet"/>
  <title>Sign Up</title>
</head>
<body>
    <form action="BookServlet" method="get">
      <span class="text">Verification Code:</span> <input class="text input" type="text" name="name" maxlength="45"/>
      <input class="button" type="submit" value="Verify" name="verify"/>
    </form>
    
  	<button class="button" name="resend">Resend Verification Code</button>
  	<br />
  	Given Info:<br />
  	Name: ${firstname} ${lastname}<br />
  	Birthday: ${month} ${day} ${year} <br />
  	Gender: ${gender} <br />
  	Address: ${street} ${city} ${state} ${zip}<br />
  	Username: ${username} <br />
  	Email: ${email} <br />
  	Phone Number: ${phone} <br />
  	    <form action="BookServlet" method="get">
	  	<button class="button" name="startover">START OVER</button>
		</form>

</body>
</html>
