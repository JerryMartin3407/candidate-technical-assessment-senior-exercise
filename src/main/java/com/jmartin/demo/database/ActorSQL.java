package com.jmartin.demo.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jmartin.demo.model.Actor;
import com.jmartin.demo.database.ActorRowMapper;

@Repository
public class ActorSQL{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SQL_NEW_ACTOR = "INSERT INTO ACTOR(ACTOR_NAME) VALUES(?)";
	private static final String SQL_UPDATE_ACTOR = "UPDATE ACTOR SET ACTOR_NAME = ? WHERE ACTOR_ID = ?";
	private static final String SQL_DELETE_ACTOR = "DELETE FROM ACTOR WHERE ACTOR_ID = ?";
	private static final String SQL_FIND_ALL_ACTORS = "SELECT * FROM ACTOR";
	private static final String SQL_FIND_BY_ACTOR_ID = "SELECT * FROM ACTOR WHERE ACTOR_ID = ?";
	private static final String SQL_FIND_BY_ACTOR_NAME = "SELECT * FROM ACTOR WHERE UPPER(ACTOR_NAME) LIKE UPPER('%' || ? || '%')";
	private static final String SQL_IS_ACTOR_IN_CREDITS = "SELECT count(1) FROM CREDITS WHERE ACTOR_ID = ?";

	public Actor findByActorId(long actorId) {
		Actor actor = jdbcTemplate.queryForObject(SQL_FIND_BY_ACTOR_ID, new ActorRowMapper(), new Object[] { actorId });
		return actor;
	}

	public List<Actor> findByActorName(String actorName) {
		List<Actor> actor = jdbcTemplate.query(SQL_FIND_BY_ACTOR_NAME, new ActorRowMapper(), new Object[] { actorName });
		return actor;
	}

	public List<Actor> findAllActors() {
		List<Actor> actors = jdbcTemplate.query(SQL_FIND_ALL_ACTORS, new ActorRowMapper());
		return actors;
	}

	public Actor addActor(String Name) {
		Actor actor = new Actor();
		List<Actor> actors = findByActorName(Name);

		if ( actors.isEmpty() )
		{
			jdbcTemplate.update(SQL_NEW_ACTOR, new Object[] { Name } );
			actors = findByActorName(Name);
		}

		actor = actors.get(0);

		return actor;
	}

	public Actor updateActor(Long id, String name ) {
		jdbcTemplate.update(SQL_UPDATE_ACTOR, new Object[] { name, id } );

		Actor actor = findByActorId(id);
		return actor;
	}

	public String deleteActor(long actorId) {
		String sReturn = "";
		String sName = "";
		try
		{
			Actor actor = findByActorId( actorId );
			sName = actor.getActorName();
		}
		catch(Exception e ) { sName = "Invalid Actor ID"; }

		int iTotal = jdbcTemplate.queryForObject(SQL_IS_ACTOR_IN_CREDITS, int.class, new Object[] { actorId });

		if ( iTotal == 0)
			if (sName == "Invalid Actor ID" )
				sReturn = sName;
			else
				{
					sReturn = "Actor " + sName + " has been deleted.";
					jdbcTemplate.update(SQL_DELETE_ACTOR, new Object[] { actorId });
				}
		else
			sReturn = "Actor " + sName + " cannot be deleted.";

		return sReturn;
	}
}