package com.devfun.vo;

public class MovieVO {
	
	public String movie_name;
	public String director;
	public String type;
	
	
	@Override
	public String toString() {
		return "MovieVO [movie_name=" + movie_name + ", director=" + director + ", type=" + type + "]";
	}


	public String getMovie_name() {
		return movie_name;
	}


	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}


	public String getDirector() {
		return director;
	}


	public void setDirector(String director) {
		this.director = director;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	

}
