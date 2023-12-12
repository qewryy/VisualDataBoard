package com.viz.visualdataboard.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usersettings")
public class UserSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int settingID;

    @OneToOne
    @JoinColumn(name="UserID")
    private User user;

    private String theme;

    private String preferredData;
}