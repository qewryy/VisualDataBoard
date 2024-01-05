package com.viz.visualdataboard.dto.response;

import com.viz.visualdataboard.domain.User;
import lombok.Data;

@Data
public class UserResponse {
    private int userId;
    private String username;
    private String email;
    private String role;

    public static UserResponse of(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getUserID());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setRole(user.getRole().getRoleName());

        return userResponse;
    }
}
