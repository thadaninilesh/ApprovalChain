<?php
require "init.php";
$email = $_POST['email'];
//$query = "SELECT e.employee_name, e.email, e.phone FROM employees e JOIN groups g WHERE e.email='$email' AND e.id=g.manager_employee_id";
$query = "SELECT group_id FROM employees WHERE email='$email'";
$result = mysqli_query($con, $query);
if(mysqli_num_rows($result)>0){
	while($row = mysqli_fetch_array($result)){
		$id = $row[0];
	}
	$query = "SELECT employee_name, email, phone FROM employees WHERE group_id=$id";
	$result = mysqli_query($con, $query);
	if(mysqli_num_rows($result)>0){
		$response = array();
		while($row = mysqli_fetch_array($result)){
			array_push($response,array("employeeName"=>$row[0], "employeeEmail"=>$row[1],"employeePhone"=>$row[2]));
		}
		echo json_encode(array("server_response"=>$response));
	}
}
else{
	echo "Data not found";
}
mysqli_close($con);
?>