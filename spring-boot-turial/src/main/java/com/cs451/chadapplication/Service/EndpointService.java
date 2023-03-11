package com.cs451.chadapplication.Service;

import com.cs451.chadapplication.Domain.*;
import com.cs451.chadapplication.Entity.*;
import com.cs451.chadapplication.Repository.*;
import com.fasterxml.jackson.databind.util.BeanUtil;
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
        try {
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
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred");
        }
    }

    public List<PositionDescriptionResponse> getPosition(PositionDescriptionRequest request){
        try {
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
                    try {
                        Optional<PositionEntity> dbResponse = positionRepository.findById(classCode);
                        PositionDescriptionResponse item = new PositionDescriptionResponse();
                        BeanUtils.copyProperties(dbResponse.get(), item);
                        response.add(item);
                    } catch (Exception e) {
                        throw new RuntimeException("Error getting position for class code: " + classCode);
                    }
                }
            }

            return response;
        }
        catch (RuntimeException e){
            throw e;
        }
        catch (Exception e){
            throw new RuntimeException("An unknown error occurred");
        }

    }

    public void createApplication(ApplicationDescriptionRequest request){
        try {
            for (String classCode : request.getClassCodes()) {
                Optional<PositionEntity> positionType = positionRepository.findById(classCode);

                if (positionType.isEmpty()) {
                    throw new RuntimeException("Unable to apply for position: " + classCode);
                }

                ApplicationEntity entity = new ApplicationEntity();
                entity.setUmkcEmail(request.getUmkcEmail());
                entity.setClassCode(classCode);
                entity.setPositionType(positionType.get().getPositionType());
                entity.setDesiredHours(request.getDesiredHours());
                entity.setExperience(request.getExperience());

                applicationRepository.save(entity);
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred");
        }
    }

    public List<ApplicationDescriptionResponse> getApplicationAdmin(ApplicationDescriptionRequest request){
        try {
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
            if(request.getClassCodes().size() == 0 ){
                Iterable<ApplicationEntity> dbResponse = applicationRepository.findAll();

                for (ApplicationEntity entity : dbResponse) {
                    ApplicationDescriptionResponse item = new ApplicationDescriptionResponse();
                    item.setUmkcEmail(entity.getUmkcEmail());
                    item.setClassCode(entity.getClassCode());
                    item.setPositionType(entity.getPositionType());

                    response.add(item);
                }

            }
            // Return will be a list of emails and position description
            return response;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred");
        }

    }

    public List<StudentApplicationDescriptionResponse> getApplicationStudent(String umkcEmail){
        try {
            List<StudentApplicationDescriptionResponse> response = new ArrayList<>();

            // Query application table by umkcEmail
            List<ApplicationEntity> dbResponse = applicationRepository.findAllByUmkcEmail(umkcEmail);

            // Error handling for if entry isn't found
            if (dbResponse.isEmpty()) {
                throw new RuntimeException("Unable to find by: " + umkcEmail);
            }

            for (ApplicationEntity entity : dbResponse) {
                // formatting response
                StudentApplicationDescriptionResponse item = new StudentApplicationDescriptionResponse();
                BeanUtils.copyProperties(entity, item);
                item.setPositionType(entity.getPositionType());
                item.setClassCodes(entity.getClassCode());
                item.setUmkcEmail(entity.getUmkcEmail());
                response.add(item);
            }

            return response;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred");
        }
    }


    public StudentInfoResponse getStudentRecord(String umkcEmail) {
        try {

            StudentInfoResponse response = new StudentInfoResponse();
            CourseInfo courseInfo = new CourseInfo();

            // Query courses table and format the response
            Optional<CoursesEntity> coursesEntity = coursesRepository.findById(umkcEmail);

            // Error handling for if no entry is found
            if (coursesEntity.isEmpty()) {
                throw new RuntimeException("Unable to find course by: " + umkcEmail);
            }

            // Query student table and format the response
            BeanUtils.copyProperties(coursesEntity.get(), courseInfo);
            Optional<StudentRecordEntity> studentRecordEntity = studentRecordRepository.findById(umkcEmail);

            // Error handling for if no entry is found
            if (studentRecordEntity.isEmpty()) {
                throw new RuntimeException("Unable to find student record by: " + umkcEmail);
            }

            // Formatting response
            BeanUtils.copyProperties(studentRecordEntity.get(), response);
            response.setClassesCompleted(courseInfo);

            return response;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred");
        }
    }

    public void deletePosition(String classCode) {
        try {
            // deleting all associated entries to position
            applicationRepository.deleteAllByClassCode(classCode);
            // deleting position
            positionRepository.deleteById(classCode);
        } catch (Exception e) {
            throw new RuntimeException("An unexptected error occurred");
        }
    }

    public void deleteApplication(String umkcEmail, String classCode) {
        try {
            // delete where umkcEmail = umkcEmail and classCode = classCode
            applicationRepository.deleteAllByUmkcEmailAndClassCode(umkcEmail, classCode);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred");
        }
    }

    public void updateStudentInfo(UpdateStudentInfoRequest request) {
        Optional<StudentRecordEntity> entity = studentRecordRepository.findById(request.getUmkcEmail());

        if (entity.isEmpty()) {
            throw new RuntimeException("Unable to find by: " + request.getUmkcEmail());
        }

        if (request.getFirstName() != null ) {
            entity.get().setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            entity.get().setLastName(request.getLastName());
        }
        if (request.getAddress() != null) {
            entity.get().setAddress(request.getAddress());
        }
        if (request.getPhoneNumber() != null) {
            entity.get().setPhoneNumber(request.getPhoneNumber());
        }
        if (request.getSuffix() != null) {
            entity.get().setSuffix(request.getSuffix());
        }

        studentRecordRepository.save(entity.get());
    }

    public CourseInfo getCourses(String umkcEmail) {
        try {
            // Querying DB for course information
            CourseInfo response = new CourseInfo();
            Optional<CoursesEntity> dbResponse = coursesRepository.findById(umkcEmail);

            // Exception for if something is not returned
            if (dbResponse.isEmpty()) {
                throw new RuntimeException("Unable to find by: " + umkcEmail);
            }

            // Formatting data to response
            BeanUtils.copyProperties(dbResponse.get(), response);
            return response;
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred");
        }
    }

    public void createPosition(CreatePositionRequest request) {
        try {
            PositionEntity entity = new PositionEntity();

            // setting attributes from request
            entity.setClassCode(request.getClassCode());
            entity.setPositionType(request.getPositionType());
            entity.setPositionDescription(request.getPositionDescription());
            entity.setCreatedBy(request.getCreatedBy());
            entity.setPositionName(request.getPositionName());

            // saving updates to DB
            positionRepository.save(entity);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occured");
        }
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
