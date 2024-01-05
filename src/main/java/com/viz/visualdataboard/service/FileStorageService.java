package com.viz.visualdataboard.service;

import com.viz.visualdataboard.exception.FileStorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {
    @Value("${FILE.UPLOAD-DIR}")
    private String fileUploadDir;
    public String storeFile(MultipartFile file) {
        if (file == null || file.getOriginalFilename() == null) {
            throw new FileStorageException("업로드된 파일이 없거나 파일명이 없습니다.");
        }

        // 원본 파일 이름 확인
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());

        // 중복 방지를 위한 파일 이름 생성
        String fileName = UUID.randomUUID().toString() + "_" + originalFileName;

        try {
            // 파일 이름에 부적절한 문자가 있는지 확인
            if (fileName.contains("..")) {
                throw new FileStorageException("파일명에 부적절한 문자가 포함되어 있습니다: " + fileName);
            }

            // 파일 저장 경로 설정
            Path targetLocation = Paths.get(fileUploadDir).resolve(fileName);

            // 파일 저장
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // 저장된 파일 경로 반환
            return targetLocation.toString();
        } catch (IOException ex) {
            throw new FileStorageException("파일 저장에 실패했습니다. 다시 시도해주세요.", ex);
        }
    }
}
