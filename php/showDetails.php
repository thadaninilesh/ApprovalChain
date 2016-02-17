<?php
require "init.php";
$email = $_POST['email'];
$query = "SELECT * FROM employees WHERE email='$email'";
$result = mysqli_query($con,$query);
if(mysqli_num_rows($result)>0){
	while($row=mysqli_fetch_array($result)){
		if($row[5]=='2'){
			$row[5] = 'Boss';
		}
		else if($row[5]=='1'){
			$row[5] = 'Manager';
		}
		else if($row[5]=='0'){
			$row[5] = 'General Employee';
		}
		$query = "SELECT group_name FROM groups WHERE group_id='$row[6]'";
		$result = mysqli_query($con,$query);
		$group_name="";
		if(mysqli_num_rows($result)>0){
			$r = mysqli_fetch_assoc($result);
			$group_name = $r['group_name'];
			if($group_name==null){
				$group_name = "None";
			}
		}
		$response = array();
		array_push($response,array("name"=>$row[1],"email"=>$row[3],"phone"=>$row[2],"designation"=>$row[5],"group_name"=>$group_name));
	}
	echo json_encode(array("server_response"=>$response));
}
else{
	echo "No data found ".mysqli_error($con);
}
mysqli_close($con);

?>