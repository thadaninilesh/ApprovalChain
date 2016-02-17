<?php

require "init.php";

$email = $_POST['email'];
$password = hash('sha512',$_POST['password']);

$sql_query = "SELECT flag FROM employees WHERE email like '$email' AND password like '$password'";
$result = mysqli_query($con, $sql_query);
if(mysqli_num_rows($result)>0){
	$row = mysqli_fetch_assoc($result);
	$flag = $row['flag'];
	echo $flag;
}
else{
	echo "Invalid credentials, please try again";
}
?>