package com.cs451.chadapplication.Service;

import com.cs451.chadapplication.Domain.*;
import com.cs451.chadapplication.Entity.*;
import com.cs451.chadapplication.Repository.*;
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
    We send back our umkcEmail and password to the server
    The server will check if the email and password are correct (in this case test with umkcEmail: chad@umkc.edu and password: password)
    If the email and password are correct, the server will return a JSON object with the following information:
    {
        "umkcEmail": "
        "isAdmin": true
    }
    test url: http://localhost:8080/login?umkcAmail=chad@umkc.edu&password=password
    */
    @Autowired
    PositionRepository positionRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    CoursesRepository coursesRepository;

    @Autowired
    StudentRecordRepository studentRecordRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    testRepository test;


    public LoginResponse login(String umkcEmail, String password) {
        LoginResponse response = new LoginResponse();

        // if email and password are correct, return email and isAdmin
        Optional<UserEntity> dbResponse = userRepository.findById(umkcEmail);

        if(dbResponse.get().getPassword().equals(password)){
            if (dbResponse.get().getIsAdmin() == 1){
                response.setIsAdmin(true);
            }
            else{
                response.setIsAdmin(false);
            }
            response.setUmkcEmail(dbResponse.get().getUmkcEmail());
        }
        else{
            response.setUmkcEmail("Invalid username or password");
            response.setIsAdmin(false);
        }



        return response;
    }

    public List<PositionDescriptionResponse> getPosition(PositionDescriptionRequest request){
        List<PositionDescriptionResponse> response = new ArrayList<>();

        // if list empty, return all courses
        if (request.getClassCodes().size() == 0) {
            Iterable<PositionEntity> dbResponse = positionRepository.findAll();

            for (PositionEntity entity : dbResponse) {
                PositionDescriptionResponse item = new PositionDescriptionResponse();
                BeanUtils.copyProperties(entity, item);
                response.add(item);
            }
        }

        // if list populated, query DB for those specific class codes and get their information
        if (request.getClassCodes().size() > 0) {
            for (String classCode : request.getClassCodes()) {
                Optional<PositionEntity> dbResponse = positionRepository.findById(classCode);

                PositionDescriptionResponse item = new PositionDescriptionResponse();
                BeanUtils.copyProperties(dbResponse.get(), item);

                response.add(item);
            }
        }

        return response;
    }

    public void createApplication(ApplicationDescriptionRequest request){
        for (String classCode : request.getClassCodes()) {
            Optional<PositionEntity> positionType = positionRepository.findById(classCode);

            ApplicationEntity entity = new ApplicationEntity();
            entity.setUmkcEmail(request.getUmkcEmail());
            entity.setClassCode(classCode);
            entity.setPositionType(positionType.get().getPositionType());
            entity.setDesiredHours(request.getDesiredHours());
            entity.setExperience(request.getExperience());

            applicationRepository.save(entity);
        }
    }

    public List<ApplicationDescriptionResponse> getApplicationAdmin(ApplicationDescriptionRequest request){
        // iterate through all classCodes in request (for loop)
            // query position table by classCode to get PositionType
            // save data to the application table (umkcEmail, classCode, and positionType per classCode)
        List<ApplicationDescriptionResponse> response = new ArrayList<>();

        // Application/viewAll/admin
        if(request.getClassCodes().size() > 0 ) {
            for (String classCode : request.getClassCodes()) {
                List<ApplicationEntity> entities = applicationRepository.findAllByClassCode(classCode);

                for (ApplicationEntity entity : entities) {
                    ApplicationDescriptionResponse item = new ApplicationDescriptionResponse();
                    BeanUtils.copyProperties(entity, item);
                    response.add(item);
                }
            }
        }
        // Application/viewAll/admin
        if(request.getClassCodes().size() == 0 ){ {
            Iterable<ApplicationEntity> dbResponse = applicationRepository.findAll();

            for (ApplicationEntity entity : dbResponse) {
                ApplicationDescriptionResponse item = new ApplicationDescriptionResponse();
                item.setUmkcEmail(entity.getUmkcEmail());
                item.setClassCode(entity.getClassCode());
                item.setPositionType(entity.getPositionType());

                response.add(item);
            }

        }
        }
        // Return will be a list of emails and position description
        return response;
    }

    public List<StudentApplicationDescriptionResponse> getApplicationStudent(String umkcEmail){
        List<StudentApplicationDescriptionResponse> response = new ArrayList<>();

        List<ApplicationEntity> dbResponse = applicationRepository.findAllByUmkcEmail(umkcEmail);

        for (ApplicationEntity entity : dbResponse) {
            StudentApplicationDescriptionResponse item = new StudentApplicationDescriptionResponse();
            BeanUtils.copyProperties(entity, item);
            // set classCode and positionType
            item.setPositionType(entity.getPositionType());
            item.setClassCodes(entity.getClassCode());
            item.setUmkcEmail(entity.getUmkcEmail());
            response.add(item);
        }

        return response;
    }

    public StudentInfoResponse getStudentRecord(String umkcEmail) {
        StudentInfoResponse response = new StudentInfoResponse();
        CourseInfo courseInfo = new CourseInfo();
        System.out.println("umkcEmail: " + umkcEmail);
        // Query courses table and format the response
        Optional<CoursesEntity> coursesEntity = coursesRepository.findById(umkcEmail);
        BeanUtils.copyProperties(coursesEntity.get(), courseInfo);

        // Query student table and format the response
        Optional<StudentRecordEntity> studentRecordEntity = studentRecordRepository.findById(umkcEmail);
        BeanUtils.copyProperties(studentRecordEntity.get(), response);
        response.setClassesCompleted(courseInfo);

        return response;
    }

    public void applicationResponse(String umkcEmail, String classCode) {
        //TODO: Save info to DB
    }

    public void deletePosition(String classCode) {
        //TODO: Remove from DB
    }

    public void deleteApplication(String umkcEmail, String classCode) {
        //TODO: Remove from DB
    }

    public void updateStudentInfo(UpdateStudentInfoRequest request) {
        // TODO: Update student info in DB
    }

    public List<CourseInfo> getCourses(String umkcEmail) {
        List<CourseInfo> response = new ArrayList<>();

        // TODO: Query DB

        return response;
    }

    public void createPosition(CreatePositionRequest request) {

        PositionEntity entity = new PositionEntity();

        entity.setClassCode(request.getClassCode());
        entity.setPositionType(request.getPositionType());
        entity.setPositionDescription(request.getPositionDescription());
        entity.setCreatedBy(request.getCreatedBy());
        entity.setPositionName(request.getPositionName());



        positionRepository.save(entity);


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
