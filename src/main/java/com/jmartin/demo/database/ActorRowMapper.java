package com.jmartin.demo.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jmartin.demo.model.Actor;

public class ActorRowMapper implements RowMapper<Actor> {

	@Override
	public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Actor actor = new Actor();

		actor.setActorId(rs.getLong("ACTOR_ID"));
		actor.setActorName(rs.getString("ACTOR_NAME"));

		return actor;
	}
}