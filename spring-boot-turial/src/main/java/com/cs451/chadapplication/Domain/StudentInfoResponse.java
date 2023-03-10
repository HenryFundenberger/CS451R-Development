package com.cs451.chadapplication.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInfoResponse {
    private String major;
    private CourseInfo classesCompleted;
    private String firstName;
    private String lastName;
    private int studentId;
    private int gradYear;
    private String gradSemester;
    private String phoneNumber;
    private double gpa;
    private String address;
    private String suffix;
    private String currentDegree;
}
