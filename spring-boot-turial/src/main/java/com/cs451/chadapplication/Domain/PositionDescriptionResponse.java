package com.cs451.chadapplication.Domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionDescriptionResponse {
    private String positionName;
    private String positionDescription;
    private String positionType;
    private String classCode;
    private String createdBy;
}
