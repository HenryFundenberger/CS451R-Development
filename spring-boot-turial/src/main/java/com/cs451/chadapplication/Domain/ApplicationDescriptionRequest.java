package com.cs451.chadapplication.Domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDescriptionRequest {
    private String umkcEmail;
    private List<String> classCodes;
    private Integer desiredHours;
    private String experience;
}
