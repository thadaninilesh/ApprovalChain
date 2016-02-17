<?php

require "init.php";
$email = $_POST['email'];

$query = "SELECT id FROM employees WHERE email='$email'";
$result = mysqli_query($con, $query);
if(mysqli_num_rows($result)>0){
	$row = mysqli_fetch_assoc($result);
	$id = $row['id'];
	
	$query = "SELECT * FROM tasks WHERE employee_id='$id'";
	
	$response = array();
	$result = mysqli_query($con, $query);
	while($row = mysqli_fetch_array($result)){
		if($row[5]=='0'){
			$row[5] = "Not approved by anyone";
		}
		else if($row[5]=='1'){
			$row[5] = "Approved by manager";
		}
		else if($row[5]=='2'){
			$row[5] = "Approved";
		}
		array_push($response,array("task_name"=>$row[1],"task_description"=>$row[2],"task_amount"=>$row[4],"approval_status"=>$row[5]));
	}

	echo json_encode(array("server_response"=>$response));

	mysqli_close($con);

}
else{
	echo "Retreival could not happen!";
}




?>