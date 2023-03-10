package com.cs451.chadapplication.Entity;

import jakarta.persistence.*;
import lombok.Data;



        import jakarta.persistence.Entity;
        import jakarta.persistence.Table;


// foreign key: course_email (references umkc_email from User table)

@Entity
@Data
@Table(name = "courses")
public class CoursesEntity {
    @Id
    @Column(name = "umkc_email")
    String umkcEmail;
    @Column(name = "cs101")
    Integer cs101;
    @Column(name = "cs191")
    Integer cs191;
    @Column(name = "cs201r")
    Integer cs201r;
    @Column(name = "cs291")
    Integer cs291;
    @Column(name = "cs303")
    Integer cs303;
    @Column(name = "cs320")
    Integer cs320;
    @Column(name = "cs349")
    Integer cs349;
    @Column(name = "cs394r")
    Integer cs394r;
    @Column(name = "cs404")
    Integer cs404;
    @Column(name = "cs441")
    Integer cs441;
    @Column(name = "cs449")
    Integer cs449;
    @Column(name = "cs456")
    Integer cs456;
    @Column(name = "cs457")
    Integer cs457;
    @Column(name = "cs458")
    Integer cs458;
    @Column(name = "cs461")
    Integer cs461;
    @Column(name = "cs465r")
    Integer cs465r;
    @Column(name = "cs470")
    Integer cs470;
    @Column(name = "cs5520")
    Integer cs5520;
    @Column(name = "cs5525")
    Integer cs5525;
    @Column(name = "cs5552a")
    Integer cs5552a;
    @Column(name = "cs5565")
    Integer cs5565;
    @Column(name = "cs5573")
    Integer cs5573;
    @Column(name = "cs5590pa")
    Integer cs5590pa;
    @Column(name = "cs5592")
    Integer cs5592;
    @Column(name = "cs5596a")
    Integer cs5596a;
    @Column(name = "cs5596b")
    Integer cs5596b;
    @Column(name = "ece216")
    Integer ece216;
    @Column(name = "ece226")
    Integer ece226;
    @Column(name = "ece228")
    Integer ece228;
    @Column(name = "ece241")
    Integer ece241;
    @Column(name = "ece276")
    Integer ece276;
    @Column(name = "ece302")
    Integer ece302;
    @Column(name = "ece330")
    Integer ece330;
    @Column(name = "ece341r")
    Integer ece341r;
    @Column(name = "ece428r")
    Integer ece428r;
    @Column(name = "ece458")
    Integer ece458;
    @Column(name = "ece466")
    Integer ece466;
    @Column(name = "ece477")
    Integer ece477;
    @Column(name = "ece486")
    Integer ece486;
    @Column(name = "ece5558")
    Integer ece5558;
    @Column(name = "ece5560")
    Integer ece5560;
    @Column(name = "ece5567")
    Integer ece5567;
    @Column(name = "ece5577")
    Integer ece5577;
    @Column(name = "ece5578")
    Integer ece5578;
    @Column(name = "ece5586")
    Integer ece5586;
    @Column(name = "it222")
    Integer it222;
    @Column(name = "it321")
    Integer it321;
    @Column(name = "cs101l")
    Integer cs101l;
    @Column(name = "cs201l")
    Integer cs201l;
    @Column(name = "ece227")
    Integer ece227;
    @Column(name = "ece229")
    Integer ece229;
    @Column(name = "ece277")
    Integer ece277;
    @Column(name = "ece303")
    Integer ece303;
    @Column(name = "ece377")
    Integer ece377;
    @Column(name = "ece331")
    Integer ece331;
    @Column(name = "ece427")
    Integer ece427;
    @Column(name = "ece429")
    Integer ece429;


}
