package com.jmartin.demo.model;

public class Movie {

	private Long movieId;
	private String movieName;

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	@Override
	public String toString() {
		return "[ Movie Id : " + movieId + ", Movie Name : " + movieName + "]";
	}
}