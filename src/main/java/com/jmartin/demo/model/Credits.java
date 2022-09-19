package com.jmartin.demo.model;

//import com.jmartin.demo.model.Actor;
//import com.jmartin.demo.model.Movie;

public class Credits {

	//private Long actorId;
	//private Long movieId;

	private Actor actor;
	private Movie movie;

	/*
	public Long getActorId() {
		return actorId;
	}

	public void setActorId(Long actorId) {
		this.actorId = actorId;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	*/

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	/*
	@Override
	public String toString() {
		String sReturn;
		Actor actor;
		Movie movie;

		ActorSQL actorDB = new ActorSQL();
		MovieSQL movieDB = new MovieSQL();

		System.out.println( "STRING VALUE" );

		actor = actorDB.findByActorId(actorId);
		movie = movieDB.findByMovieId(movieId);

		System.out.println( actorId );
		System.out.println( actor );

		sReturn = "[ actor : " + actor.toString() + ", movie : " + movie.toString() + "]";

		return sReturn;
		//return "[ Actor Id : " + actorId + ", Movie ID : " + movieId + "]";
		//return actor.toString() + movie.toString();

	}
	*/

}