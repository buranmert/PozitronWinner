package com.cnlms.rottenandroid.model;

import java.util.Map;

public class Movie {
	private String title;
	private int imdb_id;
	private String synopsis;
	private int rating;
	private String image_url;
	private Map<String,String> cast;
	String imdb;
	
	public Movie(String title, int imdb_id, String synopsis, int rating, String image_url, Map<String,String> cast, String imdb){
		this.title = title;
		this.cast = cast;
		this.imdb_id = imdb_id;
		this.rating = rating;
		this.synopsis = synopsis;
		this.image_url = image_url;
		this.imdb = imdb;
	}
	
	public String getTitle(){
		return title;
	}
	public Map<String,String> getCast(){
		return cast;
	}
	public String getSynopsis(){
		return synopsis;
	}
	public int getRating()
	{
		return rating;
	}
	public int getId(){
		return imdb_id;
	}
	public String getImage(){
		return image_url;
	}
	public String getIMDB(){
		return imdb;
	}
}
