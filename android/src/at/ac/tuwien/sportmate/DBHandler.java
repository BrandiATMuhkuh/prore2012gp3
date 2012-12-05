package at.ac.tuwien.sportmate;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import android.util.Log;

public class DBHandler {
	
	
	
	//TODO 
	String serviceName = "http://web.student.tuwien.ac.at/~e0826174/db_functions.php";
	
	public String getUser(Integer id){
		
		boolean ok = true;
		
	    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	    nameValuePairs.add(new BasicNameValuePair("fid", id.toString()));
	    
	    String result = this.sendRequestToServer(serviceName, nameValuePairs);
	    
	    if (result == null || result.equals("nok")) {
	    	ok = false;
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
		
		String serviceName = "http://web.student.tuwien.ac.at/~e0826174/med/app/savePatient.php";
		
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
	
	
	
	private String sendRequestToServer(String serviceName, ArrayList<NameValuePair> nameValuePairs){
		
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
