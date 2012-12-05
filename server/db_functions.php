<?php


mysql_connect("localhost","e0826174","pass4Root");
mysql_select_db("e0826174_mydb");

if (isset($_REQUEST['method'])) {
	
	if ($_REQUEST['method'] == "getUser") {
		$result = mysql_query("SELECT * FROM User");

		$antworten = array();
		
		while ($row=mysql_fetch_assoc($result)) {       
			print("-new-");
			print($row['user_id']."-!-");
			print($row['user_name']."-!-");
			print($row['user_joining_date']."-!-");
			print($row['default_activity']."\n");
		}
	} 
	else if ($_REQUEST['method'] == "") {
		$sql = "" 
		
	}
	else if ($_REQUEST['method'] == "") {
		$sql = "" 
	}
}
 
mysql_close();

?>