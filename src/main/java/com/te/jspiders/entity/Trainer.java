package com.te.jspiders.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Trainer {
    @Id
    private String trainerId;
    private String trainerName;

}