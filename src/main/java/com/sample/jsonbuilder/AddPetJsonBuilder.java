package com.sample.jsonbuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddPetJsonBuilder
{
	private int id;
	private String name, status;
	
	private Map<String,Object> category = new HashMap<>();
	private Map<String,Object> tag = new HashMap<>();
	
	private List<String> photoUrls = new ArrayList<String>();
	private List<Map<String,Object>> tags = new ArrayList<>();
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setCategory(Map<String,Object> categoryMap) {
		this.category = categoryMap;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}
	
	public void setTags(Map<String,Object> tag) {
		this.tag = tag;
		this.tags.add(this.tag);
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Map<String,Object> getPetJson(){
		Map<String,Object> jsonBodyUsingMap = new HashMap<String,Object>();
		jsonBodyUsingMap.put("id", id);
		jsonBodyUsingMap.put("category", category);
		jsonBodyUsingMap.put("name", name);
		jsonBodyUsingMap.put("photoUrls", photoUrls);
		jsonBodyUsingMap.put("tags", tags);
		jsonBodyUsingMap.put("status", status);
		
		return jsonBodyUsingMap;
	}
	
}
