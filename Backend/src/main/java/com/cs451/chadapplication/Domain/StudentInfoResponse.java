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
    private CourseInfo classes_completed;
    private String first_name;
    private String last_name;
    private int student_id;
    private int grad_year;
    private String grad_semester;
    private String phone_number;
    private double gpa;
    private String address;
    private String suffix;
    private String current_degree;
}
