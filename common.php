
<?php
// lines below create variables for server name, db, username, password
$servername = 'localhost:3306';
$dbname = 'ecommerce';
$username = 'root';
$password = 'root';
function insertToMyDB($item, $name){
	try {
	    $conn = new PDO("mysql:host=" . $GLOBALS['servername'] . ";dbname=" . $GLOBALS['dbname'], $GLOBALS['username'], $GLOBALS['password']);
	    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	    $query = "insert into todo (userid, item) values ((select id from users where username = '". $name ."'), '". $item ."');";
	    $stmt = $conn->prepare($query);
	    $stmt->execute();
	 
	} catch (PDOException $e) {
	    die('Database connection failed: ' . $e->getMessage());
	}
	$conn = null;
}
function deleteFromMyDB($item, $name){
	try {
	    $conn = new PDO("mysql:host=" . $GLOBALS['servername'] . ";dbname=" . $GLOBALS['dbname'], $GLOBALS['username'], $GLOBALS['password']);
	    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	    $query = "delete from todo  where userid = (select id from users where username = '". $name ."') and item = '". $item ."';";
	    $stmt = $conn->prepare($query);
	    $stmt->execute();
	 
	} catch (PDOException $e) {
	    die('Database connection failed: ' . $e->getMessage());
	}
	$conn = null;
}
?>