package com.example.mapapp;

import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Rest {
	
	private static final String TASK_URL = "https://192.168.56.1/task";
	
	public static String doGetTask(String aT){
		String result = null;

		try {
			String response = new HttpGetHelper().execute(constructGetTask(TASK_URL, aT)).get();			
			JSONArray json = new JSONArray(response);
			result = parse(json, "");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}		
	
	public static String doPostTask(String aT, String description, String deadline){
		String result = null;
		try {
			String response = new HttpPostHelper().execute(constructPostTask(TASK_URL, aT, description, deadline)).get();
			if(response.contains("Error")){
				throw new Exception("Invalid response!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}	
	
	private static String parse(JSONArray json, String indent) throws JSONException{
		String result = null;
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<json.length();i++){    
		    JSONObject e = json.getJSONObject(i);
		    String done = e.getString("isDone").equals("true")?" (done)":"";
		    if(e.getString("deadline")!=null);
		    sb.append(indent)
		    	.append(e.getString("description"))
		    	.append(" until ")
		    	.append(e.getString("deadline").substring(0,10))
		    	.append(done)
		    	.append("\n");
		    JSONArray subtasks = e.optJSONArray("subtasks");
		    if(subtasks!=null){
		    	sb.append(parse(subtasks, "\t\t"));
		    }  
		}		
		result = sb.toString();
		return result;		
	}
	
	private static String constructGetTask(String url, String aT){
	    if(!url.endsWith("?"))
	        url += "?";

	    List<NameValuePair> params = new LinkedList<NameValuePair>();

        params.add(new BasicNameValuePair("accessToken", String.valueOf(aT)));

	    String paramString = URLEncodedUtils.format(params, "utf-8");

	    url += paramString;
	    return url;
	}
	
	private static String constructPostTask(String url, String aT, String description, String deadline){
	    if(!url.endsWith("?"))
	        url += "?"; 

	    List<NameValuePair> params = new LinkedList<NameValuePair>();

        params.add(new BasicNameValuePair("accessToken", String.valueOf(aT)));
        params.add(new BasicNameValuePair("description", String.valueOf(description)));
        params.add(new BasicNameValuePair("deadline", String.valueOf(deadline)));
        
	    String paramString = URLEncodedUtils.format(params, "utf-8"); 

	    url += paramString;
	    return url; 
	}
		
}
