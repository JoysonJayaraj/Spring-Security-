package com.te.jspiders.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AdminDTO {
    private String adminId;
    private String adminName;
    private String password;
}
