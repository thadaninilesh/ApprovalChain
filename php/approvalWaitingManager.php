<?php
require "init.php";
$email = $_POST['email'];
$query = "SELECT e.employee_name, e.email, t.tasks_name, t.task_amount, t.tasks_description, g.group_name FROM employees e JOIN tasks t JOIN groups g WHERE e.email='$email' AND t.approval_status='0' AND e.id=g.manager_employee_id";
$result = mysqli_query($con, $query);
if(mysqli_num_rows($result)>0){
	$response = array();
	while($row=mysqli_fetch_array($result)){
		array_push($response,array("employee_name"=>$row[0], "employee_email"=>$row[2],"task_name"=>$row[2],"task_amount"=>$row[3],"task_description"=>$row[4]));
	}
	echo json_encode(array("server_response"=>$response));
}
else{
	echo "Data not found";
}
mysqli_close($con);
?>