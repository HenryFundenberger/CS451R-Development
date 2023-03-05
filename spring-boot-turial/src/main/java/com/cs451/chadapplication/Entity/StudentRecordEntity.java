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
    String umkc_email;

    @Column(name = "first_name")
    String first_name;

    @Column(name = "last_name")
    String last_name;

    @Column(name = "student_id")
    Integer student_id;

    @Column(name = "phone_number")
    String phone_number;

    @Column(name = "suffix")
    String suffix;

    @Column(name = "address")
    String address;

    @Column(name = "grad_semester")
    String grad_semester;

    @Column(name = "grad_year")
    Integer grad_year;

    @Column(name = "major")
    String major;

    @Column(name = "gpa")
    Float gpa;

    @Column(name = "current_degree")
    String current_degree;

    @Column(name = "resume")
    String resume;

    @Column(name = "transcript")
    String transcript;

    @Column(name = "gta_doc")
    String gta_doc;
}
