package com.cs451.chadapplication.Domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionDescriptionResponse {
    private String position_name;
    private String position_description;
    private String position_type;
    private String class_code;
    private String created_by;
}
