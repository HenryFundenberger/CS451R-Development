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



    // get default mapping for when they go to just the root of the site
    // test url: http://localhost:8080
    @GetMapping("/")
    public String sayHello() {
        return "Home Page of Spring Boot";
    }



    /*
    Added by Henry Fundenberger
    Login End Points
    We send back our umkc_email and password to the server
    The server will check if the email and password are correct (in this case test with umkc_email: chad@umkc.edu and password: password)
    If the email and password are correct, the server will return a JSON object with the following information:
    {
        "umkc_email": "
        "is_admin": true
    }
    test url: http://localhost:8080/login?umkc_email=chad@umkc.edu&password=password
 */
    @GetMapping("/login")
    public String login(@RequestParam(value = "umkc_email", defaultValue = "none") String umkc_email,
                        @RequestParam(value = "password", defaultValue = "none") String password) {
        if (umkc_email.equals("chad@umkc.edu") && password.equals("password")) {
            return "{\"umkc_email\": \"" + umkc_email + "\", \"is_admin\": true}";
        } else {
            return "{\"umkc_email\": \"" + umkc_email + "\", \"is_admin\": false}";
        }
    }









    }
