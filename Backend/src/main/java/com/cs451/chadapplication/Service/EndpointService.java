package com.cs451.chadapplication.Service;

import com.cs451.chadapplication.Domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EndpointService {

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
    public LoginResponse login(String umkc_email, String password) {
        LoginResponse response = new LoginResponse();

        // TODO: need to query DB here
        if (!umkc_email.equals("chad@umkc.edu") || !password.equals("password")) {
            throw new RuntimeException();
        }

        response.setIs_admin(true);
        response.setUmkc_email("chad@umkc.edu");
        return response;
    }

    public List<PositionDescriptionResponse> posDescription(PositionDescriptionRequest request){
        List<PositionDescriptionResponse> response = new ArrayList<>();

        // TODO: need to query DB here
        // if list empty, return all courses
        if (request.getClass_codes().size() == 0) {
            response.add(new PositionDescriptionResponse("Grader", "Hello world", "yes", "CS201", "me"));
            response.add(new PositionDescriptionResponse("Grader", "Hello world", "yes", "CS301", "me"));
            response.add(new PositionDescriptionResponse("Grader", "Hello world", "yes", "CS401", "me"));
            response.add(new PositionDescriptionResponse("Grader", "Hello world", "yes", "CS501", "me"));
            response.add(new PositionDescriptionResponse("Grader", "Hello world", "yes", "CS601", "me"));
            response.add(new PositionDescriptionResponse("Grader", "Hello world", "yes", "CS701", "me"));
        }

        // TODO: need to query DB here
        // if list populated, query DB for those specific class codes and get their information
        if (request.getClass_codes().size() > 0) {
            for (String class_codes : request.getClass_codes()) {
                response.add(new PositionDescriptionResponse("Grader", "Hello world", "yes", class_codes, "me"));
            }
        }

        return response;
    }

    public ApplicationDescriptionResponse appDescription(ApplicationDescriptionRequest request){
        ApplicationDescriptionResponse response = new ApplicationDescriptionResponse();

        //TODO: need to query DB here
        if(request.getClass_codes().size() > 0){
            List<String> emails = new ArrayList<>();
            emails.add("hgfnff@umsystem.edu");
            emails.add("chad@umsystem.edu");
            response.setUmkc_email(emails);
            response.setPositionType("Grader");
        }


        // Return will be a list of emails and position description
        return response;
    }

    public StudentInfoResponse studentRecordResponse(String umkc_email) {
        StudentInfoResponse response = new StudentInfoResponse();

        //TODO: need to query student entity

        //TODO: need to query courses entity

        return response;
    }

    public void applicationRespone(String umkc_email, String class_code) {
        //TODO: Save info to DB
    }

}
