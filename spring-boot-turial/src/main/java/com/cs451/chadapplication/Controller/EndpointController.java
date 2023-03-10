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

    @GetMapping("/Student_Record/get")
    @ResponseStatus(HttpStatus.OK)
    public StudentInfoResponse studentRecordResponse(@RequestParam(value = "umkcEmail") String umkcEmail){
        return service.studentRecordResponse(umkcEmail);
    }

    // Compatible UI Pages: Home, Position Description, View All Applications (Admin / Student)
    @PostMapping("/Position/Description")
    @ResponseStatus(HttpStatus.OK)
    public List<PositionDescriptionResponse> posDescription(@RequestBody PositionDescriptionRequest request){
        return service.posDescription(request);
    }

    @PostMapping("/Application/create")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void submitApplication(@RequestBody ApplicationDescriptionRequest request){
        service.submitApplication(request);
    }

    // Compatible UI Pages: View All Applications (Get for Application Entity)
    @PostMapping("/Application/viewAll/admin")
    @ResponseStatus(HttpStatus.OK)
    public List<ApplicationDescriptionResponse> getApplication(@RequestBody ApplicationDescriptionRequest request){
        return service.getApplication(request);
    }

    @GetMapping("/Application/viewAll/student")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentApplicationDescriptionResponse> studentAppDescription(@RequestParam(value = "umkcEmail") String umkcEmail){
        return service.studentAppDescription(umkcEmail);
    }

    @DeleteMapping("/Position/view")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete_position(@RequestParam(value = "classCode")String classCode){
        service.positionRemoval(classCode);
    }

    @DeleteMapping("/Application/view/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete_application(@RequestParam(value = "umkcEmail")String umkcEmail,
                                    @RequestParam(value = "classCode")String classCode){
        service.applicationRemoval(umkcEmail, classCode);
    }

    @PostMapping("/student/post")
    @ResponseStatus(HttpStatus.OK)
    public void updateStudentInfo(@RequestBody UpdateStudentInfoRequest request) {
        service.updateStudentInfo(request);
    }

    @GetMapping("/courses/get")
    @ResponseStatus(HttpStatus.OK)
    public List<CourseInfo> getCourses(@RequestParam(value = "umkcEmail") String umkcEmail) {
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
