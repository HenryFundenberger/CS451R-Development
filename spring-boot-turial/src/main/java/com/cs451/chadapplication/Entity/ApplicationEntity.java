package com.cs451.chadapplication.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

// foreign key: app_email (references umkc_email from User table)
// foreign key: class_code (references class_code from Position table)

@Entity
@Data
@IdClass(ApplicationID.class)
@Table(name = "application")
public class ApplicationEntity {

    @Id
    @Column(name = "umkc_email")
    String umkcEmail;

    @Id
    @Column(name = "class_code")
    String classCode;

    @Column(name = "position_type")
    String positionType;

    @Column(name = "desired_hours")
    Integer desiredHours;

    @Column(name = "experience")
    String experience;
}
