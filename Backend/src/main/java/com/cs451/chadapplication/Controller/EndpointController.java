package com.cs451.chadapplication.Controller;

import com.cs451.chadapplication.Domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.cs451.chadapplication.Service.EndpointService;

import java.util.List;

@RestController
public class EndpointController {
    @Autowired
    EndpointService service;

    // get default mapping for when they go to just the root of the site
    // test url: http://localhost:8080
    @GetMapping("/")
    public String sayHello() {
        return "Home Page of Spring Boot";
    }

    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestParam(value = "umkc_email", defaultValue = "none") String umkc_email,
                               @RequestParam(value = "password", defaultValue = "none") String password) {
        return service.login(umkc_email, password);
    }

    @GetMapping("/Student_Record/get")
    @ResponseStatus(HttpStatus.OK)
    public StudentInfoResponse student_record(@RequestParam(value = "umkc_email") String umkc_email){
        return service.studentRecordResponse(umkc_email);
    }

    // Compatible UI Pages: Home, Position Description, View All Applications (Admin / Student)
    @GetMapping("/Position/Description")
    @ResponseStatus(HttpStatus.OK)
    public List<PositionDescriptionResponse> posDescription(@RequestBody PositionDescriptionRequest request){
        return service.posDescription(request);
    }

    @PostMapping("/Application/view")
    @ResponseStatus(HttpStatus.OK)
    public void view_application(@RequestParam(value = "umkc_email")String umkc_email,
                                            @RequestParam(value = "class_code")String class_code){
        service.applicationRespone(umkc_email, class_code);
    }

    // Compatible UI Pages: View All Applications (Get for Application Entity)
    @GetMapping("/Application/viewAll/admin")
    @ResponseStatus(HttpStatus.OK)
    public ApplicationDescriptionResponse appDescription(@RequestBody ApplicationDescriptionRequest request){
        return service.appDescription(request);
    }

    @GetMapping("Application/viewAll/student")
    @ResponseStatus(HttpStatus.OK)
    public StudentApplicationDescriptionResponse studentAppDescription(@RequestBody StudentApplicationDescriptionRequest request){
        return service.studentAppDescription(request);
    }


}
