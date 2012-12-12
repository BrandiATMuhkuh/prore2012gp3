package at.ac.tuwien.sportmate;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import android.util.Log;

public class DBHandler {
	
	
	
	//TODO 
	final static String serviceName = "http://web.student.tuwien.ac.at/~e0826174/db_functions.php";
	
	public static BoGroup getGroupFromUser(int user_id){
		
		boolean ok = true;
		
	    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	    nameValuePairs.add(new BasicNameValuePair("method", "getGroupFromUser"));
	    nameValuePairs.add(new BasicNameValuePair("user_id", String.valueOf(user_id)));
	    
	    String serverResponse = DBHandler.sendRequestToServer(serviceName, nameValuePairs);
	    
	    if (serverResponse == null || serverResponse.equals("nok")) {
	    	ok = false;
	    }
	    
	    Scanner sc = new Scanner(serverResponse);
        sc.useDelimiter(ServerResponseHandler.instance().getLineDelimiter());
        String line = "";
	    
	    BoGroup g = new BoGroup();
	    
	    while (sc.hasNext()) {
            line = sc.next();
        	//Log.d("ServerResponse", line);
            String[] values = line.split("-!-");
            
            int group_id = Integer.parseInt(values[0]);
            String group_name = values[1];
            
            g.group_id = group_id;
            g.group_name = group_name;
            
        }
	    
	    if (ok) return g;
	    return null;
	}
	

	public static ArrayList<BoWeeklyTarget> getWeeklyTargetsFromUser(int user_id){
		
		boolean ok = true;
		
	    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	    nameValuePairs.add(new BasicNameValuePair("method", "getWeeklyTargetsFromUser"));
	    nameValuePairs.add(new BasicNameValuePair("user_id", String.valueOf(user_id)));
	    
	    String serverResponse = DBHandler.sendRequestToServer(serviceName, nameValuePairs);
	    
	    if (serverResponse == null || serverResponse.equals("nok")) {
	    	ok = false;
	    }
	    
	    Scanner sc = new Scanner(serverResponse);
        sc.useDelimiter(ServerResponseHandler.instance().getLineDelimiter());
        String line = "";
        
        ArrayList<BoWeeklyTarget> result = new ArrayList<BoWeeklyTarget>();
	    
        while (sc.hasNext()) {
            line = sc.next();
        	//Log.d("ServerResponse", line);
            String[] values = line.split("-!-");
            
            int category_id = Integer.parseInt(values[0]);
            String category_name = values[1];
            double category_intensity = Double.parseDouble(values[2]);
            int weekly_target_min = Integer.parseInt(values[3]);
            
            BoWeeklyTarget w = new BoWeeklyTarget();
            w.category = new BoCategory(category_id, category_name, category_intensity);
            w.weekly_target_min = weekly_target_min;
            result.add(w);
        }
	    
	    
	    if (ok) return result;
	    return null;
	}
	
	public static ArrayList<BoGroupMember> getUsersFromGroup(int group_id){
		
		boolean ok = true;
		
	    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	    nameValuePairs.add(new BasicNameValuePair("method", "getUsers"));
	    nameValuePairs.add(new BasicNameValuePair("group_id", String.valueOf(group_id)));
	    
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
        	//Log.d("ServerResponse", line);
            String[] values = line.split("-!-");
            
            int user_id = Integer.parseInt(values[0]);
            String user_name = values[1];
            Log.d("User: ", user_name);
            String user_joining_date = values[2];
            int default_activity = Integer.parseInt(values[3]);
            
            BoGroupMember m = new BoGroupMember();
            m.user_id = user_id; 
            m.user_name = user_name; 
            m.user_group_joining_date = java.sql.Date.valueOf(user_joining_date);
            m.default_activity = default_activity; 
            result.add(m);
        }
	    
	    if (ok) return result;
	    return null;
	}
	
	public ArrayList<String> getQuestionSets(){
		
		ArrayList<String> result = new ArrayList<String>();
		
		String serverResponse = this.sendRequestToServer(serviceName, null);
	    
	    if (serverResponse == null || serverResponse.equals("nok")) {
	    	return null;
	    }
	    
	    Scanner sc = new Scanner(serverResponse);
        sc.useDelimiter(ServerResponseHandler.instance().getLineDelimiter());
        String line = "";
	    
        while (sc.hasNext()) {
            line = sc.next();
            Log.d("ServerResponse", "loading set: " + line);
            result.add(line);
        }  
        return result;
		
	}
	
	
	private String savePatient(String firstname, String lastname, Date bd) {
		
		
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	    nameValuePairs.add(new BasicNameValuePair("vorname", firstname));
	    nameValuePairs.add(new BasicNameValuePair("nachname", lastname));
	    
	    //transfer Date expression into mysql format (e.g. "1987-10-4");
	    String bdString = (bd.getYear()+1900)+"-"+(bd.getMonth()+1)+"-"+(+bd.getDate());
	    Log.d("Saving Patient", "birthday: "+ bdString);
	    nameValuePairs.add(new BasicNameValuePair("geburtsdatum", bdString));
	    
	    String serverResponse = this.sendRequestToServer(serviceName, nameValuePairs);
	    
	    if (serverResponse.equals("nok") || serverResponse.equals("ok") || serverResponse == null){
	    	return null;
	    } else {
	    	return serverResponse;
	    }
	}
	
	
	
	private static String sendRequestToServer(String serviceName, ArrayList<NameValuePair> nameValuePairs){
		
		InputStream is = null;
	    //String result = "";
		
		try {
            HttpConnector httpConnector = HttpConnector.instance();
            httpConnector.setServiceName(serviceName);
            if (nameValuePairs != null){
            	httpConnector.setNameValuePairs(nameValuePairs);
            }
            HttpEntity entity = httpConnector.execute((Void)null).get();
            is = entity.getContent();
	    } catch(Exception e){
	            Log.e("log_tag", "Error in http connection "+e.toString());
	    }
	    
	    //convert response to string
	    try {
		    	ServerResponseHandler srh = ServerResponseHandler.instance();
		    	//give the input stream to a asynchTask to handle networking
		    	srh.setInputStream(is);
		    	String result = srh.execute((Void)null).get();
		    	is.close();
	            
	            Log.d("ServerResponse", result);
	            if (result.equals("ok")){
	            	Log.d("SendRequestToServer", "Server return is: " + result);
	            	return "ok";
	            } else if (result.equals("nok")){
	            	Log.d("SendRequestToServer", "Server return is: " + result);
	            	return null;
	            } else {
	            	Log.d("SendRequestToServer", "Server return is: " + result);
	            	return result;
	            }
	            
	    } catch(Exception e){
	            Log.e("log_tag", "Error converting result "+e.toString());
	    }
	    
	    return null;
	}
}
