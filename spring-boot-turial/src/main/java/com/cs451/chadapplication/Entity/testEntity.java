package com.cs451.chadapplication.Entity;

import jakarta.persistence.*;


import lombok.Data;

import java.sql.Blob;

@Entity
@Data
@Table(name = "test")
public class testEntity {
    @Id
    @Column(name = "pdfID")
    String pdfID;

    @Lob
    @Column(name = "pdfFile")
    Blob pdfFile;
}
