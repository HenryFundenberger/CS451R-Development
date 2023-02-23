package com.cs451.CHAD.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndpointController {

    @GetMapping("/testing")
    public String testing() {
        return "Hello world";
    }
}
