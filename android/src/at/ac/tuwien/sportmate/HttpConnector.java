package at.ac.tuwien.sportmate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class HttpConnector extends AsyncTask<Void, Void, HttpEntity> {

	
	private static HttpConnector instance = null;
	private ArrayList<NameValuePair> nameValuePairs;
	private String serviceName;
	
	public static HttpConnector instance(){
		if (instance == null){
			return new HttpConnector();
		} else {
			return instance;
		}
		
	}
	
	public HttpConnector(){
		this.serviceName = null;
		this.nameValuePairs = null;
	}
	
	public HttpConnector(String serviceName, ArrayList<NameValuePair> nameValuePairs) {
		this.serviceName = serviceName;
		this.nameValuePairs = nameValuePairs;
	}
	
	@Override
	protected HttpEntity doInBackground(Void... params) {
		try{
			HttpClient httpclient = new DefaultHttpClient();
	        HttpPost httppost = new HttpPost(serviceName);
	        if (nameValuePairs != null){
	        	httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        }
	        HttpResponse response = httpclient.execute(httppost);
	        return response.getEntity();
		} catch (UnsupportedEncodingException e){
			Log.e("HttpConnector", "UnsupportedEncodingException: " + e);
		} catch (ClientProtocolException e){
			Log.e("HttpConnector", "ClientProtocolException: " + e);
		} catch (IOException e){
			Log.e("HttpConnector", "IOException: " + e);
		} 
		
		return null;
	};
	
	@Override
	protected void onPostExecute(HttpEntity result) {
		
	};

	public void setNameValuePairs(ArrayList<NameValuePair> nameValuePairs) {
		this.nameValuePairs = nameValuePairs;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	

}
