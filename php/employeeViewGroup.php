<?php

require "init.php";
$email = $_POST['email'];
$query = "SELECT group_id FROM employees WHERE email='$email'";
$result = mysqli_query($con, $query);
if(mysqli_num_rows($result)>0){
	$row = mysqli_fetch_assoc($result);
	$group_id = $row['group_id'];
	$query = "SELECT group_name FROM groups WHERE group_id='$group_id'";
	$result = mysqli_query($con, $query);
	if(mysqli_num_rows($result)>0){
		$row = mysqli_fetch_assoc($result);
		$group_name = $row['group_name'];
		$query = "SELECT * FROM employees WHERE group_id='$group_id'";
		$result = mysqli_query($con, $query);
		$response = array();
		$result = mysqli_query($con, $query);
		while($row = mysqli_fetch_array($result)){
			array_push($response,array("name"=>$row[1],"group_name"=>$group_name));
		}
		echo json_encode(array("server_response"=>$response));

		mysqli_close($con);
	}
}
else{
	echo "Retreival could not happen!";
}

?>