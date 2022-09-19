package com.jmartin.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import com.jmartin.demo.model.Movie;
import com.jmartin.demo.database.MovieSQL;

@RestController

@RequestMapping("movie")
public class MovieController {
	@Autowired
	private MovieSQL movieDB ;

    @GetMapping("/all")
    List allMovies() {
		List<Movie> movies = movieDB.findAllMovies();
            return movies;
    }

    @GetMapping("/find")
    List findMovie(@RequestParam(name="id", required = false) Long id, @RequestParam(name="name", required = false) String name ) {
		List<Movie> movies = new ArrayList<Movie>();
		Movie movie = new Movie();

		try
		{
			if ( id != null )
			{
				movie = movieDB.findByMovieId(id);
				movies.add(movie);
			}
			else if ( name != null)
			{
				movies = movieDB.findByMovieName(name);
			}
		}
		catch( Exception e ) { System.out.println( "ERROR" ); }

		return movies;
    }

    @GetMapping("/add")
    Movie addMovie(@RequestParam String name) {
		try
		{
			Movie movie = movieDB.addMovie(name);
	        return movie ;
		}
		catch( Exception e ) { return new Movie(); }
    }

    @GetMapping("/update")
    Movie updateMovie(@RequestParam Long id, @RequestParam String name) {
		try
		{
			Movie movie = movieDB.updateMovie(id, name);
	        return movie;
		}
		catch( Exception e ) { return new Movie(); }
    }

    @GetMapping("/delete")
    String deleteMovie(@RequestParam Long id ) {
		String sReturn = "";
		try
		{
			sReturn = movieDB.deleteMovie(id);
		}
		catch( Exception e ) { sReturn = "Error"; }

		return sReturn;
    }

}
