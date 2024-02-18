package com.te.jspiders.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StudentDTO {
    private String studentId;
    private String studentName;
    private String password;
}