package com.cs451.chadapplication.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentInfoRequest {
    private String phoneNumber;
    private String address;
    private String suffix;
    private String firstName;
    private String lastName;
    private int resumeID;
    private int gtaCertID;
    private int transcriptID;
    private String umkcEmail;
}
