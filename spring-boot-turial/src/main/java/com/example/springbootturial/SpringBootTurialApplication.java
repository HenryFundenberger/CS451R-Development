package com.example.springbootturial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController

public class SpringBootTurialApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootTurialApplication.class, args);
    }

    // Get Mapping for Hello World
    // test url: http://localhost:8080/hello
    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    // get default mapping for when they go to just the root of the site
    // test url: http://localhost:8080
    @GetMapping("/")
    public String sayHello() {
        return "Home Page of Spring Boot";
    }

    // Get Mapping for Hello User, that takes in a name, age, and email
    // test url: http://localhost:8080/user?name=John&age=25&email=hgfnff@umsystem.edu
    @GetMapping("/user")
    public String sayHelloUser(@RequestParam(value = "name", defaultValue = "World") String name,
            @RequestParam(value = "age", defaultValue = "0") int age,
            @RequestParam(value = "email", defaultValue = "none") String email) {
        return String.format("Hello %s! You are %d years old and your email is %s", name, age, email);
    }

    


    

    

}
