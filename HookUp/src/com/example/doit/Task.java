package com.example.doit;

import java.sql.Date;

import org.json.JSONObject;

public class Task {

	private int id;
	private String description;
	private Date deadline;
	
	

	public Task(int id, String description, Date deadline) {
		this.id = id;
		this.description = description;
		this.deadline = deadline;
	}
	
	public static Task getFromJSON(String s){
		try{
			JSONObject obj = new JSONObject(s);	
			int id = obj.getInt("id");
			String description = obj.getString("description");
			String deadline = obj.getString("deadline").substring(0, 10);
			return new Task(id, description , Date.valueOf(deadline));
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", description=" + description
				+ ", deadline=" + deadline + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

}
