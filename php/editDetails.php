<?php 
require "init.php";
$email = $_POST['email'];
$name = $_POST['name'];
$phone = $_POST['phone'];
$query = "UPDATE employees SET employee_name='$name', phone='$phone' WHERE email='$email'";

if(mysqli_query($con,$query)){	
	echo "Details updated successfully!";
}
else{
	echo "Invalid credentials, please try again ".mysqli_error($con);
}


?>