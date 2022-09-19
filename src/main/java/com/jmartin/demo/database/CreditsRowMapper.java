package com.jmartin.demo.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jmartin.demo.model.Credits;
import com.jmartin.demo.model.Actor;
import com.jmartin.demo.model.Movie;
import com.jmartin.demo.database.ActorSQL;
import com.jmartin.demo.database.MovieSQL;
import org.springframework.beans.factory.annotation.Autowired;

public class CreditsRowMapper implements RowMapper<Credits> {
	private final ActorSQL actorDB;
	private final MovieSQL movieDB;

    public CreditsRowMapper(final ActorSQL actorDB, final MovieSQL movieDB) {
        this.actorDB = actorDB;
        this.movieDB = movieDB;
    }

	@Override
	public Credits mapRow(ResultSet rs, int rowNum) throws SQLException {
		Credits credits = new Credits();

		Long actorID = rs.getLong("ACTOR_ID");
		Long movieID = rs.getLong("MOVIE_ID");

		Actor actor = actorDB.findByActorId( actorID );
		Movie movie = movieDB.findByMovieId( movieID );

		credits.setActor( actor );
		credits.setMovie( movie);

		return credits;
	}
}