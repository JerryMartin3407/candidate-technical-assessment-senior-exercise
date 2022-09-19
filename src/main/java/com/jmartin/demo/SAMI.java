package com.jmartin.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;





import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@EnableScheduling
@RestController
@EnableAutoConfiguration

public class SAMI implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(SAMI.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("SAMI - Search Actor Movie Index");
	}

    @GetMapping("/error")
	String noData(){
		return "SAMI Error - No Data Found";
	}
}