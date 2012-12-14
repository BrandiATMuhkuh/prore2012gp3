<?php


mysql_connect("localhost","e0726015","prore12");
mysql_select_db("e0726015_prore");

if (isset($_REQUEST['method'])) {
	
	if ($_REQUEST['method'] == "getUsers") {
		$result = mysql_query("SELECT * FROM sm_user  join sm_groupMember on sm_user.user_id = sm_groupMember.user_id WHERE group_id = ".$_REQUEST['group_id']);

		$antworten = array();
		
		while ($row=mysql_fetch_assoc($result)) {       
			print("-new-");
			print($row['user_id']."-!-");
			print($row['user_name']."-!-");
			print($row['user_joining_date']."-!-");
			print($row['default_activity']."\n");
		}
	} 
	
	if ($_REQUEST['method'] == "getGroupFromUser") {
		$result = mysql_query("SELECT * FROM sm_group join sm_groupMember on sm_group.group_id = sm_groupMember.group_id WHERE sm_groupMember.user_id = ".$_REQUEST['user_id']);

		$antworten = array();
		
		while ($row=mysql_fetch_assoc($result)) {       
			print("-new-");
			print($row['group_id']."-!-");
			print($row['group_name']."-!-");
			print($row['user_group_joning_date']."-!-");
			print($row['user_group_leaving_date']."\n");
		}
	} 
	
	if ($_REQUEST['method'] == "getWeeklyTargetsFromUser") {
		$result = mysql_query("SELECT * FROM sm_weeklyTarget a JOIN sm_category b ON a.category_id = b.category_id WHERE a.user_id = ".$_REQUEST['user_id']);

		$antworten = array();
		
		while ($row=mysql_fetch_assoc($result)) {       
			print("-new-");
			print($row['category_id']."-!-");
			print($row['category_name']."-!-");
			print($row['category_intensity']."-!-");
			print($row['weekly_target_min']."\n");
		}
	}
	
	if ($_REQUEST['method'] == "getActivitiesFromUser") {
		$result = mysql_query("SELECT * FROM `sm_activities` WHERE user_id = ".$_REQUEST['user_id']);

		$antworten = array();
		
		while ($row=mysql_fetch_assoc($result)) {       
			print("-new-");
			print($row['category_id']."-!-");
			print($row['group_id']."-!-");
			print($row['date']."-!-");
			print($row['starttime']."-!-");
			print($row['duration_min']."-!-");
			print($row['intensity']."-!-");
			print($row['points']."-!-");
			print($row['bonus_points']."\n");
		}
	}
	
	if ($_REQUEST['method'] == "getCategory") {
		$result = mysql_query("SELECT * FROM sm_category WHERE category_id = ".$_REQUEST['category_id']);

		$antworten = array();
		
		while ($row=mysql_fetch_assoc($result)) {       
			print("-new-");
			print($row['category_id']."-!-");
			print($row['category_name']."-!-");
			print($row['category_intensity']."\n");
		}
	}
	
	if ($_REQUEST['method'] == "getGroupMember") {
		$result = mysql_query("SELECT * FROM `sm_groupMember` a JOIN sm_user  b ON a.user_id = b.user_id WHERE a.user_id = ".$_REQUEST['user_id']);

		$antworten = array();
		
		while ($row=mysql_fetch_assoc($result)) {       
			print("-new-");
			print($row['group_id']."-!-");
			print($row['user_name']."-!-");
			print($row['user_joining_date']."-!-");
			print($row['user_group_joning_date']."-!-");
			print($row['default_activity']."\n");
		}
	}
}
 
mysql_close();

?>