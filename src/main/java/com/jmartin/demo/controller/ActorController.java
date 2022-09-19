package com.jmartin.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import com.jmartin.demo.model.Actor;
import com.jmartin.demo.database.ActorSQL;

@RestController

@RequestMapping("actor")
public class ActorController {
	@Autowired
	private ActorSQL actorDB ;

    @GetMapping("/all")
    List allActors() {
		List<Actor> actors = actorDB.findAllActors();
            return actors;
    }

    @GetMapping("/find")
    List findActorID(@RequestParam(name="id", required = false) Long id, @RequestParam(name="name", required = false) String name ) {
		List<Actor> actors = new ArrayList<Actor>();
		Actor actor = new Actor();

		try
		{
			if ( id != null )
			{
				actor = actorDB.findByActorId(id);
				actors.add(actor);
			}
			else if ( name != null )
			{
				actors = actorDB.findByActorName(name);
			}
		}
		catch( Exception e ) { System.out.println( "Error"); e.printStackTrace(); }

		return actors;
    }



    @GetMapping("/add")
    Actor addActor(@RequestParam String name) {
		try
		{
			Actor actor = actorDB.addActor(name);
	        return actor ;
		}
		catch( Exception e ) { return new Actor(); }
    }

    @GetMapping("/update")
    Actor updateActor(@RequestParam Long id, @RequestParam String name) {
		try
		{
			Actor actor = actorDB.updateActor(id, name);
	        return actor;
		}
		catch( Exception e ) { return new Actor(); }
    }

    @GetMapping("/delete")
    String deleteActor(@RequestParam Long id ) {
		String sReturn = "";
		try
		{
			sReturn = actorDB.deleteActor(id);
		}
		catch( Exception e ) { sReturn = "Error"; }

		return sReturn;
    }
}
