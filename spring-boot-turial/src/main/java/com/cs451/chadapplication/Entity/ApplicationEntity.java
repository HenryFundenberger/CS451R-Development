package com.cs451.chadapplication.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

// foreign key: app_email (references umkc_email from User table)
// foreign key: class_code (references class_code from Position table)

@Entity
@Data
@Table(name = "application")
public class ApplicationEntity {
    @Id
    @Column(name = "umkc_email")
    String umkc_email;

    @Column(name = "class_code")
    String class_code;

    @Column(name = "position_type")
    String position_type;

    @Column(name = "desired_hours")
    Integer desired_hours;

    @Column(name = "experience")
    String experience;
}
