package com.viz.visualdataboard.dto.response;

import lombok.Data;

@Data
public class JwtResponse {

    private String jwt;

    public JwtResponse(String jwt) {
        this.jwt = jwt;
    }
}
