package com.jmartin.demo.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jmartin.demo.model.Movie;
import com.jmartin.demo.database.MovieRowMapper;

@Repository
public class MovieSQL{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SQL_NEW_MOVIE = "INSERT INTO MOVIE(MOVIE_NAME) VALUES(?)";
	private static final String SQL_UPDATE_MOVIE = "UPDATE MOVIE SET MOVIE_NAME = ? WHERE MOVIE_ID = ?";
	private static final String SQL_DELETE_MOVIE = "DELETE FROM MOVIE WHERE MOVIE_ID = ?";
	private static final String SQL_FIND_ALL_MOVIES = "SELECT * FROM MOVIE";
	private static final String SQL_FIND_BY_MOVIE_ID = "SELECT * FROM MOVIE WHERE MOVIE_ID = ?";
	private static final String SQL_FIND_BY_MOVIE_NAME = "SELECT * FROM MOVIE WHERE UPPER(MOVIE_NAME) LIKE UPPER( '%' || ? || '%')";
	private static final String SQL_IS_MOVIE_IN_CREDITS = "SELECT count(1) FROM CREDITS WHERE MOVIE_ID = ?";

	public Movie findByMovieId(long movieId) {
		Movie movie = jdbcTemplate.queryForObject(SQL_FIND_BY_MOVIE_ID, new MovieRowMapper(),
				new Object[] { movieId });
		return movie;
	}

	public List<Movie> findByMovieName(String movieName) {
		List<Movie> movies = jdbcTemplate.query(SQL_FIND_BY_MOVIE_NAME, new MovieRowMapper(), new Object[] {movieName} );
		return movies;
	}

	public List<Movie> findAllMovies() {
		List<Movie> movies = jdbcTemplate.query(SQL_FIND_ALL_MOVIES, new MovieRowMapper());
		return movies;
	}

	public Movie addMovie(String Name) {
		Movie movie = new Movie();
		List<Movie> movies = findByMovieName( Name );

		if (movies.isEmpty() )
		{
			jdbcTemplate.update(SQL_NEW_MOVIE, new Object[] { Name } );
			movies = findByMovieName( Name );
		}
		movie = movies.get(0);
		return movie;
	}

	public Movie updateMovie(Long id, String name ) {
		jdbcTemplate.update(SQL_UPDATE_MOVIE, new Object[] { name, id } );

		Movie movie = findByMovieId( id );
		return movie;
	}

	public String deleteMovie(long movieId) {
		String sReturn = "";
		String sName = "";
		try
		{
			Movie movie = findByMovieId( movieId );
			sName = movie.getMovieName();
		}
		catch(Exception e ) { sName = "Invalid Movie ID"; }

		int iTotal = jdbcTemplate.queryForObject(SQL_IS_MOVIE_IN_CREDITS, int.class, new Object[] { movieId });

		if ( iTotal == 0)
			if (sName == "Invalid Movie ID" )
				sReturn = sName;
			else
				{
					sReturn = "Movie " + sName + " has been deleted.";
					jdbcTemplate.update(SQL_DELETE_MOVIE, new Object[] { movieId });
				}
		else
			sReturn = "Movie " + sName + " cannot be deleted.";

		return sReturn;
	}
}