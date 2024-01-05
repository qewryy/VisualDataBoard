package com.viz.visualdataboard.dto.request;

import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SignUpRequest {
    private String username;
    private String password;
    private String email;
    private MultipartFile file;
    private String filename;
}
