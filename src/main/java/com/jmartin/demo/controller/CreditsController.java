package com.jmartin.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import com.jmartin.demo.model.Credits;
import com.jmartin.demo.database.CreditsSQL;


@RestController

@RequestMapping("credits")
public class CreditsController {
	@Autowired
	private CreditsSQL creditsDB ;

    @GetMapping("/all")
    List allCredits() {
		List<Credits> credits = creditsDB.findAllCredits();
		return credits;
    }

    @GetMapping("/find/actor")
    List findActorRoles(@RequestParam(name="id", required = false) Long id, @RequestParam(name="name", required=false) String name ) {
		if ( id != null )
			try
			{
				List<Credits> credits = creditsDB.findByActorId(id);
					return credits;
			}
			catch( Exception e ) { return null; }
		else if ( name != null )
			try
			{
				List<Credits> credits = creditsDB.findByActorName(name);
					return credits;
			}
			catch( Exception e ) { return null; }
		else
			return null;
    }

    @GetMapping("/find/movie")
    List findMovieRoles(@RequestParam(name="id", required = false) Long id, @RequestParam(name="name", required=false) String name ) {
		if ( id != null )
			try
			{
				List<Credits> credits = creditsDB.findByMovieId(id);
					return credits;
			}
			catch( Exception e ) { return null; }
		else if ( name != null )
			try
			{
				List<Credits> credits = creditsDB.findByMovieName(name);
					return credits;
			}
			catch( Exception e ) { return null; }
		else
			return null;
    }

    @GetMapping("/add")
    Credits addCreditsById(@RequestParam Long actorId, @RequestParam Long movieId ) {
		try
		{
			Credits credits = creditsDB.addCreditsById(actorId, movieId);
	        return credits ;
		}
		catch( Exception e ) { return new Credits(); }
    }

    @GetMapping("/delete")
    String deleteCreditsById(@RequestParam Long actorId, @RequestParam Long movieId ) {
		try
		{
			String sResults = creditsDB.deleteCreditsById(actorId, movieId);
	        return sResults;
		}
		catch( Exception e ) { return "Credits NOT deleted"; }
    }

}
