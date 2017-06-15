<!DOCTYPE html>
<html lang="">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
</head>

<body>
<?php
    
echo "Is the following information correct?\n"; 
        
    print $_POST['firstname'] . "   ";
    print $_POST['lastname'] . "     ";
    print $_POST['street'] . "        ";
    print $_POST['city'] . "         ";
    print $_POST['state'] . "         ";
   // print $_POST['password'] . "        ";
   // print $_POST['password-repeat'] . "         ";

    //send verification code to provided email. 
    
//verification code 
$bytes = random_bytes(3);
//var_dump(bin2hex($bytes)); 
    $verify_code = (bin2hex($bytes));    
?>
    
<form action="verify.php" method="post">
    <input type="submit" value="Yes."> 
    </form>
    
    <br/>
    
    <form action="register.html" method="post">
    <input type="submit" value="No.">
    </form>
    
    
</body>
</html>
