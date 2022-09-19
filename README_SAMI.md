Take Home Challenge Level (Senior-Level)

Search Actor Movie Index (SAMI)

Overview

Create a process for handling the search of Movies, specifically cross-referencing Actors to the Movies in which they starred. This process utilizes a basic Spring Boot application to serve as a REST API with an in-memory HyperSQL Database executed using Maven.

The HyperSQL database contains three simple tables, Actors, Movies, and Credits. Each table contains a unique ID along with the relevant information corresponding to the table.

The Actors table contains the Unique ID and Actor Name.
The Movies table contains the Unique ID and the Movie Name.
The Credits table contains a Unique ID, along with the Actor ID and Movie ID.

The table data definition was kept simple, although it could easily be expanded to contain additional information.  For instance, the Credits table could be expanded to contain information on the Character Name used by the Actor in the movie.

Once the data definition for the tables was set up, it was necessary to create the proper Models and retrieval methods in the application. 

The process loads a default database into memory and allows for retrieval, addition, modification, and deletion of the data.

Execution

To execute the program, use <PATH for MAVEN>/bin/mvn spring-boot:run in the main folder with the POM.XML file.  Once the process starts, it may be accessed via a web browser or CURL using localhost:8080.

As a note, if using CURL, then the syntax will need modification when either the name contains a space or there are multiple parameters involved.

The process has been divided into sections corresponding to the search parameters.

1.	ACTOR – the following options are available for execution.

actor/all : this process will provide a JSON formatted output of all of the Actors that are currently in the database.
localhost:8080/actor/all

actor/find : this process will find and return the Actor information in a JSON formatted output. The process utilizes URL parameters to provide the required search parameters.  There are two options available, searching for an Actor by the Unique ID or by the Actor Name. Searches utilizing the name will return any matching Actors with that Name.  For instance, if a full name is given such as Tom Cruise, then it should return only one Actor, however, if only a single or partial name is given, such as Tom, then all Actors with TOM in the name will be returned. If no matching values are found, then an empty set will be returned.
	localhost:8080/actor/find?id=1 
localhost:8080/actor/find?name=Tom

actor/add : this process will add the name provided in the URL parameter to the in-memory database. The result will be returned in a JSON formatted output. If the name already exists, then the process will return the existing information rather than duplicating the value. 
	localhost:8080/actor/add?name=Bob Thornton

	curl -G "localhost:8080/actor/add" --data-urlencode "name=Bob Thornton"

actor/update : this process will update the existing entry for an Actor. In the case of a name that was misspelled this would allow for the correction of that data. It is necessary to provide two URL arguments as the update requires the unique Actor ID to be known.
	localhost:8080/actor/update?id=9&name=Billy Bob Thornton

curl -G "localhost:8080/actor/update" --data-urlencode "id=9” --data-urlencode "name=Billy Bob Thornton"

actor/delete : this process will remove an Actor from the in-memory database. The system will verify that the Actor does not exist in the Credits table. If it does exist in the Credits table, then the deletion will not be allowed. This does return a text message indicating success or failure of the process.
	localhost:8080/actor/delete?id=9

2.	Movie – the following options are available for execution.

movie/all : this process will provide a JSON formatted output of all of the Movies that are currently in the database.
localhost:8080/movie/all

movie/find : this process will find and return the Movie information in a JSON formatted output. The process utilizes URL parameters to provide the required search parameters.  There are two options available, searching for a Movie by the Unique ID or by the Movie Name. Searches utilizing the name will return any matching Movies with that Name.  For instance, if a full name is given such as Star Wars, then it should return only one Movie, however, if only a single or partial name is given, such as Star, then all Movies with STAR in the name will be returned. If no matching values are found, then an empty set will be returned.
	localhost:8080/movie/find?id=1 
localhost:8080/movie/find?name=Star Trek

movie/add : this process will add the name provided in the URL parameter to the in-memory database. The result will be returned in a JSON formatted output. If the name already exists, then the process will return the existing information rather than duplicating the value. 
	localhost:8080/movie/add?name=Space Ball

movie/update : this process will update the existing entry for a Movie. In the case of a name that was misspelled this would allow for the correction of that data. It is necessary to provide two URL arguments as the update requires the unique Movie ID to be known.
	localhost:8080/movie/update?id=36&name=Space Balls

movie/delete : this process will remove a Movie from the in-memory database. The system will verify that the Movie does not exist in the Credits table. If it does exist in the Credits table, then the deletion will not be allowed. This does return a text message indicating success or failure of the process.
	localhost:8080/movie/delete?id=36

3.	Credits – the following options are available for execution.

credits/all : this process will provide a JSON formatted output of all of the Credits that are currently in the database.
localhost:8080/credits/all

credits/find/actor : this process will find and return the Credits for a specified Actor either by Unique ID or Name with the information in a JSON formatted output. 
	localhost:8080/credits/find/actor?id=1 
localhost:8080/credits/find/actor?name=Harrison

credits/find/movie : this process will find and return the Credits for a specified Movie either by Unique ID or Name with the information in a JSON formatted output. 
	localhost:8080/credits/find/movie?id=1 
localhost:8080/credits/find/movie?name=Raiders

credits/add : this process will add a new Credits entry (Actor + Movie). The result will be returned in a JSON formatted output. If the Credit already exists, then the process will return the existing information rather than duplicating the value. If either the Actor or Movie does not exist, then an empty set will be returned.
	localhost:8080/credits/add?actorId=1&movieId=30
	
The following will return the empty set (based on the initial data load) as there is not an Actor with a Unique ID of 11.
	localhost:8080/credits/add?actorId=11&movieId=30

credits/delete : this process will remove Credits from the in-memory database. This does return a text message indicating success or failure of the process.
	localhost:8080/credits/delete?actorId=1&movieId=30


