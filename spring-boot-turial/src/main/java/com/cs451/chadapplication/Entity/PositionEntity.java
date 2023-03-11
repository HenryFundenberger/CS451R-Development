package com.cs451.chadapplication.Entity;

import jakarta.persistence.*;
import lombok.Data;

// foreign key: email (references umkc_email from User table)

@Entity
@Data

@Table(name = "position")
public class PositionEntity {


    @Column(name = "position_type")
    String positionType;

    @Id
    @Column(name = "class_code")
    String classCode;

    @Column(name = "position_description")
    String positionDescription;

    @Column(name = "created_by")
    String createdBy;


    @Column(name = "position_name")
    String positionName;
}
