package com.cs451.chadapplication.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

// foreign key: umkc_email (references umkc_email from User table)

@Entity
@Data
@Table(name = "student_record")
public class StudentRecordEntity {
    @Id
    @Column(name = "umkc_email")
    String umkcEmail;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "student_id")
    Integer studentId;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "suffix")
    String suffix;

    @Column(name = "address")
    String address;

    @Column(name = "grad_semester")
    String gradSemester;

    @Column(name = "grad_year")
    Integer gradYear;

    @Column(name = "major")
    String major;

    @Column(name = "gpa")
    Float gpa;

    @Column(name = "current_degree")
    String currentDegree;

    @Column(name = "resume")
    String resume;

    @Column(name = "transcript")
    String transcript;

    @Column(name = "gta_doc")
    String gtaDoc;
}
