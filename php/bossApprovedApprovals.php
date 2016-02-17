<?php

require "init.php";
$email = $_POST['email'];
$query = "SELECT id FROM employees WHERE email='$email'";
$result = mysqli_query($con, $query);
if(mysqli_num_rows($result)>0){
	while($row = mysqli_fetch_array($result)){
		$id = $row[0];
	}
	$query = "SELECT e.employee_name,e.email,t.tasks_name,t.tasks_description,t.task_amount,t.approval_status FROM employees e JOIN tasks t JOIN groups g WHERE t.approval_status='2'";
	$result = mysqli_query($con,$query);
	if(mysqli_num_rows($result)>0){
		$response = array();
		while($row = mysqli_fetch_array($result)){
			if($row[5]=='2'){
				$row[5] = "Approved by Boss";
			}
			else if($row[5]=='1'){
				$row[5] = "Approved by you";
			}
			else if($row[5]=='0'){
				$row[5] = "Approval pending from you";
			}
			array_push($response,array("employeeName"=>$row[0], "employeeEmail"=>$row[1],"taskName"=>$row[2],"taskDescription"=>$row[3],"taskAmount"=>$row[4],"taskStatus"=>$row[5]));		
		}
		echo json_encode(array("server_response"=>$response));
	}
	else{
		echo "No approvals found";
	}
}
else{
	echo "Error".mysqli_error($con);
}
mysqli_close($con);

?>