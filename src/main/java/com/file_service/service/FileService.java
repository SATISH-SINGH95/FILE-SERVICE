package com.file_service.service;

import com.file_service.dto.FileDetailDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    void uploadFile(MultipartFile file, FileDetailDto fileDetailDto) throws IOException;

    String getBase64EncodedFile(String pathOnly, String fileNameWithExtension2);
}