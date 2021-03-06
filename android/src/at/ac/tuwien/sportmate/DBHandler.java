package at.ac.tuwien.sportmate;

import java.io.InputStream;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import android.util.Log;

public class DBHandler {

	// TODO
	final static String serviceName = "http://web.student.tuwien.ac.at/~e0826174/db_functions.php";

	public static BoGroup getGroupFromUser(int user_id) {

		Log.d("DBHandler", "getGroupFromUser for user_id = " + user_id);

		boolean ok = true;

		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs
				.add(new BasicNameValuePair("method", "getGroupFromUser"));
		nameValuePairs.add(new BasicNameValuePair("user_id", String
				.valueOf(user_id)));

		String serverResponse = DBHandler.sendRequestToServer(serviceName,
				nameValuePairs);

		if (serverResponse == null || serverResponse.equals("nok")) {
			ok = false;
		}

		Scanner sc = new Scanner(serverResponse);
		sc.useDelimiter(ServerResponseHandler.instance().getLineDelimiter());
		String line = "";

		BoGroup g = new BoGroup();

		while (sc.hasNext()) {
			line = sc.next();
			// Log.d("ServerResponse", line);
			String[] values = line.split("-!-");

			int group_id = Integer.parseInt(values[0]);
			String group_name = values[1];

			g.group_id = group_id;
			g.group_name = group_name;

		}

		if (ok)
			return g;
		return null;
	}

	public static BoCategory getCategory(int category_id) {

		Log.d("DBHandler", "getCategory for category_id = " + category_id);

		boolean ok = true;

		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("method", "getCategory"));
		nameValuePairs.add(new BasicNameValuePair("category_id", String
				.valueOf(category_id)));

		String serverResponse = DBHandler.sendRequestToServer(serviceName,
				nameValuePairs);

		if (serverResponse == null || serverResponse.equals("nok")) {
			ok = false;
		}

		Scanner sc = new Scanner(serverResponse);
		sc.useDelimiter(ServerResponseHandler.instance().getLineDelimiter());
		String line = "";

		BoCategory c = new BoCategory();

		while (sc.hasNext()) {
			line = sc.next();
			// Log.d("ServerResponse", line);
			String[] values = line.split("-!-");

			int cat_id = Integer.parseInt(values[0]);
			String category_name = values[1];
			double category_intensity = Double.parseDouble(values[2]);

			c.category_id = cat_id;
			c.category_name = category_name;
			c.category_intensity = category_intensity;
		}

		if (ok)
			return c;
		return null;
	}

	public static ArrayList<BoCategory> getCategories() {

		boolean ok = true;

		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("method", "getCategories"));

		String serverResponse = DBHandler.sendRequestToServer(serviceName,
				nameValuePairs);

		if (serverResponse == null || serverResponse.equals("nok")) {
			ok = false;
		}

		Scanner sc = new Scanner(serverResponse);
		sc.useDelimiter(ServerResponseHandler.instance().getLineDelimiter());
		String line = "";

		ArrayList<BoCategory> result = new ArrayList<BoCategory>();

		while (sc.hasNext()) {
			line = sc.next();
			// Log.d("ServerResponse", line);
			String[] values = line.split("-!-");

			BoCategory c = new BoCategory();

			int cat_id = Integer.parseInt(values[0]);
			String category_name = values[1];
			double category_intensity = Double.parseDouble(values[2]);

			c.category_id = cat_id;
			c.category_name = category_name;
			c.category_intensity = category_intensity;

			result.add(c);
		}

		if (ok)
			return result;
		return null;
	}

	public static BoGroupMember getGroupMember(int user_id) {

		Log.d("DBHandler", "getGroupMember for user_id = " + user_id);

		boolean ok = true;

		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("method", "getGroupMember"));
		nameValuePairs.add(new BasicNameValuePair("user_id", String
				.valueOf(user_id)));

		String serverResponse = DBHandler.sendRequestToServer(serviceName,
				nameValuePairs);

		if (serverResponse == null || serverResponse.equals("nok")) {
			ok = false;
		}

		Scanner sc = new Scanner(serverResponse);
		sc.useDelimiter(ServerResponseHandler.instance().getLineDelimiter());
		String line = "";

		BoGroupMember m = new BoGroupMember();

		while (sc.hasNext()) {
			line = sc.next();
			// Log.d("ServerResponse", line);
			String[] values = line.split("-!-");

			int group_id = Integer.parseInt(values[0]);
			String user_name = values[1];
			String user_joining_date_String = values[2];
			String user_group_joning_date_String = values[3];
			int default_activity = Integer.parseInt(values[4]);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			try {
				m.user_id = user_id;
				m.group_id = group_id;
				m.user_name = user_name;
				m.user_joining_date = new Date(sdf.parse(
						user_joining_date_String).getTime());
				m.user_group_joining_date = new Date(sdf.parse(
						user_group_joning_date_String).getTime());
				m.default_activity = default_activity;
			} catch (java.text.ParseException e) {
				Log.d("ParseException", "Error while Parsing: Dates");
				ok = false;
			}
		}

		if (ok)
			return m;
		return null;
	}

	public static ArrayList<BoWeeklyTarget> getWeeklyTargetsFromUser(int user_id) {

		Log.d("DBHandler", "getWeeklyTargetsFromUser for user_id = " + user_id);

		boolean ok = true;

		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("method",
				"getWeeklyTargetsFromUser"));
		nameValuePairs.add(new BasicNameValuePair("user_id", String
				.valueOf(user_id)));

		String serverResponse = DBHandler.sendRequestToServer(serviceName,
				nameValuePairs);

		if (serverResponse == null || serverResponse.equals("nok")) {
			ok = false;
		}

		Scanner sc = new Scanner(serverResponse);
		sc.useDelimiter(ServerResponseHandler.instance().getLineDelimiter());
		String line = "";

		ArrayList<BoWeeklyTarget> result = new ArrayList<BoWeeklyTarget>();

		while (sc.hasNext()) {
			line = sc.next();
			// Log.d("ServerResponse", line);
			String[] values = line.split("-!-");

			int category_id = Integer.parseInt(values[0]);
			String category_name = values[1];
			double category_intensity = Double.parseDouble(values[2]);
			int weekly_target_min = Integer.parseInt(values[3]);
			String dateString = values[4];
			String timeString = values[5];
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			Date date;
			BoWeeklyTarget w = new BoWeeklyTarget();
			try {
				date = new Date(sdf.parse(dateString).getTime());
			
			//Time time = Time.valueOf(timeString);
			
			w.category = new BoCategory(category_id, category_name,
					category_intensity);
			w.weekly_target_min = weekly_target_min;
			w.setTarget_changed_at_date(date);
			//w.setTarget_changed_at_time(time);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result.add(w);
		}

		if (ok)
			return result;
		return null;
	}

	public static ArrayList<BoGroupMember> getUsersFromGroup(int group_id) {

		Log.d("DBHandler", "getUsersFromGroup for group_id = " + group_id);

		boolean ok = true;

		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("method", "getUsers"));
		nameValuePairs.add(new BasicNameValuePair("group_id", String
				.valueOf(group_id)));

		String serverResponse = sendRequestToServer(serviceName, nameValuePairs);

		if (serverResponse == null || serverResponse.equals("nok")) {
			ok = false;
		}

		Scanner sc = new Scanner(serverResponse);
		sc.useDelimiter(ServerResponseHandler.instance().getLineDelimiter());
		String line = "";

		ArrayList<BoGroupMember> result = new ArrayList<BoGroupMember>();

		while (sc.hasNext()) {
			line = sc.next();
			// Log.d("ServerResponse", line);
			String[] values = line.split("-!-");

			int user_id = Integer.parseInt(values[0]);
			String user_name = values[1];
			// Log.d("User: ", user_name);
			String user_joining_date = values[2];
			int default_activity = Integer.parseInt(values[3]);

			BoGroupMember m = new BoGroupMember();
			m.user_id = user_id;
			m.user_name = user_name;
			m.user_group_joining_date = java.sql.Date
					.valueOf(user_joining_date);
			m.default_activity = default_activity;
			result.add(m);
		}

		if (ok)
			return result;
		return null;
	}

	public static ArrayList<BoActivity> getAllActivitesFromUser(int user_id) {

		Log.d("DBHandler", "getActivitesFromUser for user_id = " + user_id);

		boolean ok = true;

		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("method",
				"getActivitiesFromUser"));
		nameValuePairs.add(new BasicNameValuePair("user_id", String
				.valueOf(user_id)));

		String serverResponse = sendRequestToServer(serviceName, nameValuePairs);

		if (serverResponse == null || serverResponse.equals("nok")) {
			return null;
		}

		Scanner sc = new Scanner(serverResponse);
		sc.useDelimiter(ServerResponseHandler.instance().getLineDelimiter());
		String line = "";

		ArrayList<BoActivity> result = new ArrayList<BoActivity>();

		while (sc.hasNext()) {
			line = sc.next();
			// Log.d("ServerResponse", line);
			String[] values = line.split("-!-");

			int category_id = Integer.parseInt(values[0]);
			int group_id = Integer.parseInt(values[1]);
			String dateString = values[2];
			String startTimeString = values[3];
			int duration_min = Integer.parseInt(values[4]);
			int intensity = Integer.parseInt(values[5]);
			double points = Double.parseDouble(values[5]);
			double bonus_points = Double.parseDouble(values[6]);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			BoActivity a = new BoActivity();

			try {
				a.category = getCategory(category_id);
				a.group_id = group_id;
				a.user_id = user_id;
				java.util.Date helpDate = sdf.parse(dateString);
				a.date = new java.sql.Date(helpDate.getTime());
				a.starttime = java.sql.Time.valueOf(startTimeString);
				a.duration_min = duration_min;
				a.intensity = intensity;
				a.points = points;
				a.bonus_points = bonus_points;
			} catch (java.text.ParseException e) {
				Log.d("ParseException", "Error while Parsing: " + dateString);
				ok = false;
			}

			result.add(a);
		}
		return result;

	}

	public static ArrayList<BoActivity> getWeeklyActivitiesFromUser(int user_id) {

		Log.d("DBHandler", "getWeeklyActivitiesFromUser for user_id = "
				+ user_id);

		boolean ok = true;

		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("method",
				"getWeeklyActivitiesFromUser"));
		nameValuePairs.add(new BasicNameValuePair("user_id", String
				.valueOf(user_id)));

		String serverResponse = sendRequestToServer(serviceName, nameValuePairs);

		Log.d("ServerResponseHIER", serverResponse);

		if (serverResponse == null || serverResponse.equals("nok")) {
			return null;
		}

		Scanner sc = new Scanner(serverResponse);
		sc.useDelimiter(ServerResponseHandler.instance().getLineDelimiter());
		String line = "";

		ArrayList<BoActivity> result = new ArrayList<BoActivity>();
		if (!serverResponse.equals("")) {
			while (sc.hasNext()) {
				line = sc.next();
				// Log.d("ServerResponse", line);
				String[] values = line.split("-!-");

				int category_id = Integer.parseInt(values[0]);
				int group_id = Integer.parseInt(values[1]);
				String dateString = values[2];
				String startTimeString = values[3];
				int duration_min = Integer.parseInt(values[4]);
				double intensity = Double.parseDouble(values[5]);
				double points = Double.parseDouble(values[5]);
				double bonus_points = Double.parseDouble(values[6]);

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				BoActivity a = new BoActivity();

				try {
					a.category = getCategory(category_id);
					a.group_id = group_id;
					a.user_id = user_id;
					java.util.Date helpDate = sdf.parse(dateString);
					a.date = new java.sql.Date(helpDate.getTime());
					a.starttime = java.sql.Time.valueOf(startTimeString);
					a.duration_min = duration_min;
					a.intensity = intensity;
					a.points = points;
					a.bonus_points = bonus_points;
				} catch (java.text.ParseException e) {
					Log.d("ParseException", "Error while Parsing: "
							+ dateString);
					ok = false;
				}

				result.add(a);
			}
		}
		return result;

	}

	public static int getActiveGroupMemberCount(int user_id, int group_id) {

		boolean ok = true;

		int activeGroupMembers = 0;

		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("method",
				"getActiveGroupMemberCount"));
		nameValuePairs.add(new BasicNameValuePair("user_id", String
				.valueOf(user_id)));
		nameValuePairs.add(new BasicNameValuePair("group_id", String
				.valueOf(group_id)));

		String serverResponse = DBHandler.sendRequestToServer(serviceName,
				nameValuePairs);

		if (serverResponse == null || serverResponse.equals("nok")) {
			ok = false;
		}

		Scanner sc = new Scanner(serverResponse);
		sc.useDelimiter(ServerResponseHandler.instance().getLineDelimiter());
		String line = "";

		while (sc.hasNext()) {
			line = sc.next();
			// Log.d("ServerResponse", line);
			String[] values = line.split("-!-");

			activeGroupMembers = Integer.parseInt(values[0]);
		}

		return activeGroupMembers;
	}

	public static ArrayList<BoGroupMember> getActiveGroupMembers(
			int user_id, int group_id) {

		boolean ok = true;

		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("method",
				"getActiveGroupMembers"));
		nameValuePairs.add(new BasicNameValuePair("user_id", String
				.valueOf(user_id)));
		nameValuePairs.add(new BasicNameValuePair("group_id", String
				.valueOf(group_id)));

		String serverResponse = DBHandler.sendRequestToServer(serviceName,
				nameValuePairs);

		if (serverResponse == null || serverResponse.equals("nok")) {
			ok = false;
		}

		Scanner sc = new Scanner(serverResponse);
		sc.useDelimiter(ServerResponseHandler.instance().getLineDelimiter());
		String line = "";

		ArrayList<BoGroupMember> result = new ArrayList<BoGroupMember>();

		while (sc.hasNext()) {

			line = sc.next();
			// Log.d("ServerResponse", line);
			String[] values = line.split("-!-");

			BoGroupMember m = new BoGroupMember();

			int id = Integer.parseInt(values[0]);
			String user_name = values[1];

			m.user_id = user_id;
			m.group_id = group_id;
			m.user_name = user_name;

			result.add(m);

		}

		return result;
	}

	public static boolean setActive(int user_id, int active) {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("method", "setActive"));
		nameValuePairs.add(new BasicNameValuePair("user_id", String
				.valueOf(user_id)));
		nameValuePairs.add(new BasicNameValuePair("active", String
				.valueOf(active)));

		String serverResponse = sendRequestToServer(serviceName, nameValuePairs);

		if (serverResponse.equals("ok")) {
			return true;
		}

		return false;

	}

	public static boolean updateWeeklyTargets(int user_id, int cat1_mins,
			int cat2_mins, int cat3_mins, int cat4_mins, int cat5_mins, Date date, 
	Time time) {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		Log.d("DBHandler", "updateWeeklyTargets for user_id = "
				+ user_id);
		
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("method",
				"updateWeeklyTargets"));
		nameValuePairs.add(new BasicNameValuePair("user_id", String
				.valueOf(user_id)));
		nameValuePairs.add(new BasicNameValuePair("cat1_mins", String
				.valueOf(cat1_mins)));
		nameValuePairs.add(new BasicNameValuePair("cat2_mins", String
				.valueOf(cat2_mins)));
		nameValuePairs.add(new BasicNameValuePair("cat3_mins", String
				.valueOf(cat3_mins)));
		nameValuePairs.add(new BasicNameValuePair("cat4_mins", String
				.valueOf(cat4_mins)));
		nameValuePairs.add(new BasicNameValuePair("cat5_mins", String
				.valueOf(cat5_mins)));
		nameValuePairs.add(new BasicNameValuePair("target_changed_at_date", s.format(date)));
		nameValuePairs.add(new BasicNameValuePair("target_changed_at_time", time.toString()));

		String serverResponse = sendRequestToServer(serviceName, nameValuePairs);
		
		if (serverResponse.equals("ok")) {
			return true;
		}

		return false;
	}

	public static boolean updateUserDefaultCategory(int user_id, int category_id) {

		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("method",
				"updateUserDefaultCategory"));
		nameValuePairs.add(new BasicNameValuePair("user_id", String
				.valueOf(user_id)));
		nameValuePairs.add(new BasicNameValuePair("category_id", String
				.valueOf(category_id)));

		String serverResponse = sendRequestToServer(serviceName, nameValuePairs);

		if (serverResponse.equals("ok")) {
			return true;
		}

		return false;
	}

	public static boolean addActivity(BoActivity activity) {

		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("method", "addActivity"));
		nameValuePairs.add(new BasicNameValuePair("user_id", String
				.valueOf(activity.user_id)));
		nameValuePairs.add(new BasicNameValuePair("group_id", String
				.valueOf(activity.group_id)));
		nameValuePairs.add(new BasicNameValuePair("category_id", String
				.valueOf(activity.category.category_id)));
		nameValuePairs.add(new BasicNameValuePair("date", activity.date
				.toString()));
		nameValuePairs.add(new BasicNameValuePair("starttime",
				activity.starttime.toString()));
		nameValuePairs.add(new BasicNameValuePair("duration_min", String
				.valueOf(activity.duration_min)));
		nameValuePairs.add(new BasicNameValuePair("intensity", String
				.valueOf(activity.intensity)));
		nameValuePairs.add(new BasicNameValuePair("points", String
				.valueOf(activity.points)));
		nameValuePairs.add(new BasicNameValuePair("bonus_points", String
				.valueOf(activity.bonus_points)));
		

		String serverResponse = sendRequestToServer(serviceName, nameValuePairs);

		if (serverResponse.equals("ok")) {
			return true;
		}

		return false;
	}

	/**
	 * This method takes a list of name-value-pairs and sends them to a given
	 * http adress
	 * 
	 * @param serviceName
	 * @param nameValuePairs
	 * @return the answer of the web service as a string
	 */
	private static String sendRequestToServer(String serviceName,
			ArrayList<NameValuePair> nameValuePairs) {

		InputStream is = null;
		// String result = "";

		try {
			HttpConnector httpConnector = HttpConnector.instance();
			httpConnector.setServiceName(serviceName);
			if (nameValuePairs != null) {
				httpConnector.setNameValuePairs(nameValuePairs);
			}
			HttpEntity entity = httpConnector.execute((Void) null).get();
			is = entity.getContent();
		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
		}

		// convert response to string
		try {
			ServerResponseHandler srh = ServerResponseHandler.instance();
			// give the input stream to a asynchTask to handle networking
			srh.setInputStream(is);
			String result = srh.execute((Void) null).get();
			is.close();

			if (result.equals("ok")) {
				// Log.d("SendRequestToServer", "Server return is: " + result);
				return "ok";
			} else if (result.equals("nok")) {
				// Log.d("SendRequestToServer", "Server return is: " + result);
				return null;
			} else {
				Log.d("SendRequestToServer", "Server return is: " + result);
				return result;
			}

		} catch (Exception e) {
			Log.e("log_tag", "Error converting result " + e.toString());
		}

		return null;
	}
}
