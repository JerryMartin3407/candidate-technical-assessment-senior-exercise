package com.jmartin.demo.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jmartin.demo.model.Actor;
import com.jmartin.demo.model.Movie;
import com.jmartin.demo.model.Credits;
import com.jmartin.demo.database.CreditsRowMapper;

import com.jmartin.demo.database.ActorSQL;
import com.jmartin.demo.database.MovieSQL;

@Repository
public class CreditsSQL{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ActorSQL actorDB;

	@Autowired
	private MovieSQL movieDB;

	private static final String SQL_NEW_CREDITS = "INSERT INTO CREDITS(ACTOR_ID, MOVIE_ID) VALUES(?,?)";
	private static final String SQL_DELETE_CREDITS = "DELETE FROM CREDITS WHERE ACTOR_ID = ? and MOVIE_ID = ?";
	private static final String SQL_FIND_ALL_CREDITS = "SELECT * FROM CREDITS";
	private static final String SQL_FIND_CREDITS = "SELECT * FROM CREDITS WHERE ACTOR_ID = ? AND MOVIE_ID = ?";
	private static final String SQL_FIND_BY_ACTOR_ID = "SELECT * FROM CREDITS WHERE ACTOR_ID = ?";
	private static final String SQL_FIND_BY_ACTOR_NAME = "SELECT * FROM CREDITS WHERE ACTOR_ID in (select actor_id from actor where upper(actor_name) like upper( '%' || ? || '%' ) )";
	private static final String SQL_FIND_BY_MOVIE_ID = "SELECT * FROM CREDITS WHERE MOVIE_ID = ?";
	private static final String SQL_FIND_BY_MOVIE_NAME = "SELECT * FROM CREDITS WHERE MOVIE_ID in (select movie_id from movie where upper(movie_name) like upper( '%' || ? || '%' ) )";

	public Credits findCredits(long actorId, long movieId ) {
		Credits credits = new Credits();
		try
		{
			credits = jdbcTemplate.queryForObject(SQL_FIND_CREDITS, new CreditsRowMapper(actorDB, movieDB ), new Object[] { actorId, movieId });
		}
		catch(Exception e) { credits = credits; }
		return credits;
	}

	public List<Credits> findByActorId(long actorId) {
		List<Credits> credits = jdbcTemplate.query(SQL_FIND_BY_ACTOR_ID, new CreditsRowMapper(actorDB, movieDB ), new Object[] { actorId });
		return credits;
	}

	public List<Credits> findByActorName(String actorname) {
		List<Credits> credits = jdbcTemplate.query(SQL_FIND_BY_ACTOR_NAME, new CreditsRowMapper(actorDB, movieDB ), new Object[] { actorname });
		return credits;
	}

	public List<Credits> findByMovieId(long movieId) {
		List<Credits> credits = jdbcTemplate.query(SQL_FIND_BY_MOVIE_ID, new CreditsRowMapper(actorDB, movieDB ), new Object[] { movieId });
		return credits;
	}

	public List<Credits> findByMovieName(String moviename) {
		List<Credits> credits = jdbcTemplate.query(SQL_FIND_BY_MOVIE_NAME, new CreditsRowMapper(actorDB, movieDB ), new Object[] { moviename });
		return credits;
	}

	public List<Credits> findAllCredits() {
		List<Credits> credits = jdbcTemplate.query(SQL_FIND_ALL_CREDITS, new CreditsRowMapper(actorDB, movieDB ));

		return credits;
	}

	public Credits addCreditsById( long actorId, long movieId ) {
		Credits credits = new Credits();

		Actor actor = actorDB.findByActorId( actorId );
		Movie movie = movieDB.findByMovieId( movieId );

		if ( actor.getActorId() != null )
			if ( movie.getMovieId() != null )
			{
				credits = findCredits( actorId, movieId );
				actor = credits.getActor();

				if ( actor == null )
				{
					jdbcTemplate.update(SQL_NEW_CREDITS, new Object[] { actorId, movieId } );
					credits = findCredits(actorId, movieId);
				}
			}

		return credits;
	}

	public String deleteCreditsById( long actorId, long movieId ) {
		jdbcTemplate.update(SQL_DELETE_CREDITS, new Object[] { actorId, movieId });

		return "Credits have been deleted.";
	}
}