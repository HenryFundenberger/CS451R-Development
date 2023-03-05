package com.cs451.chadapplication.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

// foreign key: email (references umkc_email from User table)

@Entity
@Data
@Table(name = "position")
public class PositionEntity {


    @Column(name = "position_type")
    String position_type;

    @Id
    @Column(name = "class_code")
    String class_code;

    @Column(name = "position_description")
    String position_description;

    @Column(name = "created_by")
    String created_by;

    @Column(name = "position_name")
    String position_name;
}
