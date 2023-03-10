package com.cs451.chadapplication.Domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentApplicationDescriptionResponse {
    private String classCodes;
    private String positionType;
    private String umkcEmail;
}
