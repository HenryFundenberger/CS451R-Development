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
    public LoginResponse login(@RequestParam(value = "umkcEmail", defaultValue = "none") String umkcEmail,
                               @RequestParam(value = "password", defaultValue = "none") String password) {
        return service.login(umkcEmail, password);
    }

    @GetMapping("/student/get")
    @ResponseStatus(HttpStatus.OK)
    public StudentInfoResponse getStudentRecord(@RequestParam(value = "umkcEmail") String umkcEmail){
        return service.getStudentRecord(umkcEmail);
    }

    // Compatible UI Pages: Home, Position Description, View All Applications (Admin / Student)
    @PostMapping("/position/get")
    @ResponseStatus(HttpStatus.OK)
    public List<PositionDescriptionResponse> getPosition(@RequestBody PositionDescriptionRequest request){
        return service.getPosition(request);
    }

    @PostMapping("/position/create")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createPosition(@RequestBody CreatePositionRequest request) {
        service.createPosition(request);
    }

    @PostMapping("/application/create")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createApplication(@RequestBody ApplicationDescriptionRequest request){
        service.createApplication(request);
    }

    // Compatible UI Pages: View All Applications (Get for Application Entity)
    @PostMapping("/application/get/admin")
    @ResponseStatus(HttpStatus.OK)
    public List<ApplicationDescriptionResponse> getApplicationAdmin(@RequestBody ApplicationDescriptionRequest request){
        return service.getApplicationAdmin(request);
    }

    @GetMapping("/application/get/student")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentApplicationDescriptionResponse> getApplicationStudent(@RequestParam(value = "umkcEmail") String umkcEmail){
        return service.getApplicationStudent(umkcEmail);
    }

    @DeleteMapping("/position/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePosition(@RequestParam(value = "classCode")String classCode){
        service.deletePosition(classCode);
    }

    @DeleteMapping("/application/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteApplication(@RequestParam(value = "umkcEmail")String umkcEmail,
                                    @RequestParam(value = "classCode")String classCode){
        service.deleteApplication(umkcEmail, classCode);
    }

    @PutMapping("/student/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateStudentInfo(@RequestBody UpdateStudentInfoRequest request) {
        service.updateStudentInfo(request);
    }

    @GetMapping("/courses/get")
    @ResponseStatus(HttpStatus.OK)
    public CourseInfo getCourses(@RequestParam(value = "umkcEmail") String umkcEmail) {
        return service.getCourses(umkcEmail);
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
