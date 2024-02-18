package com.te.jspiders.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer roleId;

    @Column(unique = true)
    private String roleName;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<AppUser> appUsers;
}