package com.cs451.chadapplication.Controller;

import com.cs451.chadapplication.Domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.cs451.chadapplication.Service.EndpointService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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
    public StudentInfoResponse studentRecordResponse(@RequestParam(value = "umkc_email") String umkc_email){
        return service.studentRecordResponse(umkc_email);
    }

    // Compatible UI Pages: Home, Position Description, View All Applications (Admin / Student)
    @PostMapping("/Position/Description")
    @ResponseStatus(HttpStatus.OK)
    public List<PositionDescriptionResponse> posDescription(@RequestBody PositionDescriptionRequest request){
        return service.posDescription(request);
    }

    @PostMapping("/Application/view")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void view_application(@RequestParam(value = "umkc_email")String umkc_email,
                                            @RequestParam(value = "class_code")String class_code){
        service.applicationResponse(umkc_email, class_code);
    }

    // Compatible UI Pages: View All Applications (Get for Application Entity)
    @PostMapping("/Application/viewAll/admin")
    @ResponseStatus(HttpStatus.OK)
    public ApplicationDescriptionResponse appDescription(@RequestBody ApplicationDescriptionRequest request){
        return service.appDescription(request);
    }

    @GetMapping("/Application/viewAll/student")
    @ResponseStatus(HttpStatus.OK)
    public StudentApplicationDescriptionResponse studentAppDescription(@RequestParam(value = "umkc_email") String umkc_email){
        return service.studentAppDescription(umkc_email);
    }

    @DeleteMapping("/Position/view")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete_position(@RequestParam(value = "class_code")String class_code){
        service.positionRemoval(class_code);
    }

    @DeleteMapping("/Application/view/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete_application(@RequestParam(value = "umkc_email")String umkc_email,
                                    @RequestParam(value = "class_code")String class_code){
        service.applicationRemoval(umkc_email, class_code);
    }

    @PostMapping("/student/post")
    @ResponseStatus(HttpStatus.OK)
    public void updateStudentInfo(@RequestBody UpdateStudentInfoRequest request) {
        service.updateStudentInfo(request);
    }

    @GetMapping("/courses/get")
    @ResponseStatus(HttpStatus.OK)
    public List<CourseInfo> getCourses(@RequestParam(value = "umkc_email") String umkc_email) {
        return service.getCourses(umkc_email);
    }

    // PDF testing Section
    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.OK)
    // what kind of data should be request if the front end is sending formData with a file

    public String upload(@RequestParam(value = "file") MultipartFile file) {
        return service.upload(file);
    }
    @GetMapping("/download")
    @ResponseStatus(HttpStatus.OK)
    public String download() {
        return service.download();
    }
}
