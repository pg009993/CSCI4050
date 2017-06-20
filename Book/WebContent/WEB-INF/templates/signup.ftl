<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <link href="CSS/sign.css" type="text/css" rel="stylesheet"/>
  <title>Sign Up</title>
</head>
<body>
  <div class="center">
    <form action="BookServlet" method="get">
      <span class="text">Name:</span> <input class="text input" type="text" name="name" maxlength="45"/><br>
      <span class="text">Address:</span> <input class="text input" type="text" name="address" maxlength="45"/><br>
      <span class="text">Username:</span> <input class="text input" type="text" name="username" maxlength="45"/><br>
      <span class="text">Password:</span> <input class="text input" type="password" name="password" maxlength="45"/><br>
      <span class="text">Email:</span> <input class="text input" type="text" name="email" maxlength="45"/><br>
      <span class="text">Birthday:</span> <input class="text input" type="text" name="birthday" maxlength="45"/><br>
      <input class="button" type="submit" value="Sign up" name="signup"/>
    </form>
  </div>
</body>
</html>
