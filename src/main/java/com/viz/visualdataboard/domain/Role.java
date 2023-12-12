package com.viz.visualdataboard.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleID;

    @Column(nullable = false)
    private String roleName;

    @OneToMany(mappedBy = "role")
    private List<User> users = new ArrayList<>();
}
