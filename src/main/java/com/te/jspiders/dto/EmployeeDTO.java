package com.te.jspiders.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeDTO {
    private String employeeId;
    private String employeeName;
    private String password;
}