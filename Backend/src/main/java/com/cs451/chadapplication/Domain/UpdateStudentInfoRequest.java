package com.cs451.chadapplication.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentInfoRequest {
    private String phone_number;
    private String address;
    private String suffix;
    private String first_name;
    private String last_name;
    private int resumeID;
    private int gtaCertID;
    private int transcriptID;
    private String umkc_email;
}
