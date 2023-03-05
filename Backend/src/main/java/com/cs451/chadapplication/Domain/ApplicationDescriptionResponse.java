package com.cs451.chadapplication.Domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDescriptionResponse {
    private List<String> umkc_email;
    private String positionType;
}
