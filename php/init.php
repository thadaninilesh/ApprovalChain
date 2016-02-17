<?php
$db_name = "approvalchain";
$mysql_user = "root";
$mysql_pass = "";
$server_name = "localhost";

$con = mysqli_connect($server_name, $mysql_user, $mysql_pass, $db_name);
if(!$con){
	//echo "Connection error".mysqli_connect_error();
}
else{
	//echo "Connection successful<br>";
}

?>