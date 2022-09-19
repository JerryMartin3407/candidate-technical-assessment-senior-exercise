package com.jmartin.demo.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jmartin.demo.model.Movie;

public class MovieRowMapper implements RowMapper<Movie> {

	@Override
	public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
		Movie movie = new Movie();

		movie.setMovieId(rs.getLong("MOVIE_ID"));
		movie.setMovieName(rs.getString("MOVIE_NAME"));

		return movie;
	}
}