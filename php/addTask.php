<?php
require "init.php";
$tasks_name = $_POST['tasks_name'];
$tasks_description = $_POST['tasks_description'];
$task_amount = $_POST['task_amount'];
$approval_status = $_POST['approval_status'];
$email = $_POST['email'];
$query = "SELECT id FROM employees WHERE email='$email';";
$result = mysqli_query($con,$query);
if(mysqli_num_rows($result)>0){
	$row = mysqli_fetch_assoc($result);
	$id = $row['id'];
	$query = "INSERT INTO tasks(tasks_name,tasks_description,employee_id,task_amount,approval_status) VALUES('$tasks_name', '$tasks_description', '$id', '$task_amount','$approval_status')";
	if(mysqli_query($con, $query)){
	echo "Task successfully sent for approval";
}
else{
	echo "Could not save. Please try again! ".mysqli_error($con);
}
}
?>