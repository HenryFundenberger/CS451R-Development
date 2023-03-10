package com.cs451.chadapplication.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class UserEntity {
    @Id
    @Column(name = "umkc_email")
    String umkcEmail;

    @Column(name ="password")
    String password;

    @Column(name ="is_admin")
    int isAdmin;
}
