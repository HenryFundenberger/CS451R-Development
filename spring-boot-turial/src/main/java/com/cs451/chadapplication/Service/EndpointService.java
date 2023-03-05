package com.cs451.chadapplication.Service;

import com.cs451.chadapplication.Domain.*;
import com.cs451.chadapplication.Entity.PositionEntity;
import com.cs451.chadapplication.Entity.testEntity;
import com.cs451.chadapplication.Repository.PositionRepository;
import com.cs451.chadapplication.Repository.testRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    PositionRepository positionRepository;

    @Autowired
    testRepository test;


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

        // if list empty, return all courses
        if (request.getClass_codes().size() == 0) {
            Iterable<PositionEntity> dbResponse =  positionRepository.findAll();

            for (PositionEntity entity : dbResponse) {
                PositionDescriptionResponse item = new PositionDescriptionResponse();
                BeanUtils.copyProperties(entity, item);
                response.add(item);
            }
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

    public StudentApplicationDescriptionResponse studentAppDescription(String umkc_email){
        StudentApplicationDescriptionResponse response = new StudentApplicationDescriptionResponse();

        if (umkc_email.equals("chad@umkc.edu")){
            List<String> class_codes = new ArrayList<>();
            class_codes.add("CS201");
            class_codes.add("CS301");
            class_codes.add("CS401");
            class_codes.add("CS501");
            class_codes.add("CS601");
            class_codes.add("CS701");
            response.setClass_codes(class_codes);
            response.setPositionType("Grader");
        }

        return response;
    }

    public StudentInfoResponse studentRecordResponse(String umkc_email) {
        StudentInfoResponse response = new StudentInfoResponse();

        //TODO: need to query student entity

        //TODO: need to query courses entity




        return response;
    }

    public void applicationResponse(String umkc_email, String class_code) {
        //TODO: Save info to DB
    }

    public void positionRemoval(String class_code) {
        //TODO: Remove from DB
    }

    public void applicationRemoval(String umkc_email, String class_code) {
        //TODO: Remove from DB
    }

    public void updateStudentInfo(UpdateStudentInfoRequest request) {
        // TODO: Update student info in DB
    }

    public List<CourseInfo> getCourses(String umkc_email) {
        List<CourseInfo> response = new ArrayList<>();

        // TODO: Query DB

        return response;
    }



    // PDF Testing
    public String upload(MultipartFile file) {
        try {

            byte[] bytes = file.getBytes();

            Blob b = new SerialBlob(bytes);
            //String base64 = Base64.getEncoder().encodeToString(b.getBytes(1, (int) b.length()));

            testEntity entity = new testEntity();
            entity.setPdfID("asdf");
            entity.setPdfFile(b);

            test.save(entity);
            return "success";

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public String download(){
        try {
            Optional<testEntity> entity = test.findById("asdf");
            Blob b = entity.get().getPdfFile();
            String base64 = Base64.getEncoder().encodeToString(b.getBytes(1, (int) b.length()));
            return base64;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
