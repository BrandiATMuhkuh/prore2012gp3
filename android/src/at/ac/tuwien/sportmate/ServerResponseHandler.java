package at.ac.tuwien.sportmate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


import android.os.AsyncTask;

public class ServerResponseHandler extends AsyncTask<Void, Void, String>{

	private static ServerResponseHandler instance = null;
	private InputStream inputStream;
	private String output;
	
	private String lineDelimiter;
	private String newDataIndicator;
	private String emptyIndicator;
	
	
	public static ServerResponseHandler instance(){
		if (instance == null){
			return new ServerResponseHandler();
		} else {
			return instance;
		}
	}
	
	public ServerResponseHandler(){
		this.lineDelimiter = "\n";
		this.newDataIndicator = "-new-";
		this.emptyIndicator = "-empty-";
	}
	
	public String getOutput() {
		return output;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	@Override
	protected String doInBackground(Void... params) {
		StringBuilder sb = new StringBuilder();
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1")); 
	        String line = null;
	        
	        while ((line = reader.readLine()) != null) {
	        	if (line.startsWith(emptyIndicator)){
	        		return "";
	        	}
	        	
	        	if (line.startsWith(newDataIndicator)) {
	        		line = line.replaceAll(newDataIndicator, "");
	        		sb.append(lineDelimiter);
	        	}
	        	sb.append(line);
	        }
		} catch (UnsupportedEncodingException e){
			
		} catch (IOException e){
			
		}
		return sb.toString();
	}

	public String getLineDelimiter() {
		return lineDelimiter;
	}

	public void setLineDelimiter(String lineDelimiter) {
		this.lineDelimiter = lineDelimiter;
	}
	
	

}
