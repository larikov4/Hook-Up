package com.example.doit;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Test {
	
	public static List<Task> getTaskList(){		
		String url = "http://localhost:8080/ajax";
		
		Document document = null;
		try {
			document = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
		List<Task> tasks = new ArrayList<Task>();		
		String str = document.text();		

		
		Pattern pattern = Pattern.compile("(\\{.+?\\})");
		Matcher m = pattern.matcher(str);
		while(m.find()){
			tasks.add(Task.getFromJSON(m.group(0)));
		}
		return tasks;		
	}
	
	public static List<String> getStringList(){		
		String url = "http://localhost:8080/ajax";
		
		Document document = null;
		try {
			document = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
		List<String> tasks = new ArrayList<String>();		
		String str = document.text();		

		
		Pattern pattern = Pattern.compile("(\\{.+?\\})");
		Matcher m = pattern.matcher(str);
		while(m.find()){
			tasks.add(Task.getFromJSON(m.group(0)).toString());
		}
		return tasks;	
	}
	
	public static void main(String[] args) throws IOException, JSONException, URISyntaxException {		
		
		for(Task t : getTaskList()){
			System.out.println(t);
		}
		
		
		
		
		
//		str = str.substring(1, str.length()-1);
//	    String json = str;
//	    
//	    Map<String, String> map = new HashMap<String, String>();
//	    String[] parts = json.replaceAll("^\\{|\\}$","").split("\"?(:|,)(?![^\\{]*\\})\"?");
//	    for (int i = 0; i < parts.length -1; i+=2)
//	        map.put(parts[i], parts[i+1]);
//	    System.out.println(map.size() + " entries: " + map);
//	    for(String s:map.values())
//	    	System.out.println(s);
//		
		
//		
//
//		str.replace("},{", "}%{");
//		String arr [] = str.split("%");
//		
//		Task[] tasks = new Task[arr.length];
//		for (int i = 0; i < arr.length; i++) {
//			tasks[i] = Task.getFromJSON(arr[i]);
//			System.out.println(tasks[i]);
//		}
			
		

		
		

		
	}
}
