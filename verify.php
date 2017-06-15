<?php
include 'common.php';
?> 

<!DOCTYPE html>
<html lang="">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
</head>

<body>
    <?php
    //verification code 
    $bytes = random_bytes(3);
    //var_dump(bin2hex($bytes)); 
    $verify_code = (bin2hex($bytes)); 

   // $email = $_POST['email']; 
    $to      = 'pwg88852@uga.edu';
    $subject = 'E-Commerce Verification Code';
    $message = 'This code is will expire in 10 minutes. Your code is ' . $verify_code;
    $headers = "From: ecommerce-support@ecommerce.com\r\n";
    $headers .= "Reply-To: ecommerce-support@ecommerce.com\r\n";
    $headers .= "Return-Path: ecommerce-support@ecommerce.com\r\n";
    $headers .= "CC: ecommerce@ecommerce.com\r\n";
    $headers .= "BCC: hidden@ecommerce.com\r\n";

    if ( mail($to,$subject,$message,$headers) ) {
   echo "The email has been sent!";
   } else {
   echo "The email has failed!";
   }
    ?>
    
</body>
</html>
