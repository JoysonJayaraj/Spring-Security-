package com.te.jspiders.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TrainerDTO {
    private String trainerId;
    private String trainerName;
    private String password;
}
