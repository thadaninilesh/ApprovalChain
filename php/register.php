<?php

require "init.php";
$employee_name = $_POST['employee_name'];
$password = hash('sha512',$_POST['password']);
$email = $_POST['email'];
$phone = $_POST['phone'];
$flag = $_POST['flag'];
//these above identifiers are coming from the BackgroundTask class
$sql_query = "INSERT INTO employees(employee_name,phone,email,password,flag) VALUES('$employee_name', $phone, '$email','$password',$flag)";

if(mysqli_query($con, $sql_query)){
	echo "Registeration successful! Login to continue";
}
else{
	echo "Registration".mysqli_error($con);
}

?>