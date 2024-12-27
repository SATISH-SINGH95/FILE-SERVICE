package com.file_service.dto;

import lombok.*;
import org.apache.commons.lang3.StringUtils;
public class FileDetailDto {

    private String basePath;
    private String filePath;
    private String originalFilename;
    private String fileExtension;
    private Long fileSizeKb;

    public boolean isNotEmpty() {
        return StringUtils.isNotEmpty(originalFilename) && StringUtils.isNotEmpty(filePath);
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public Long getFileSizeKb() {
        return fileSizeKb;
    }

    public void setFileSizeKb(Long fileSizeKb) {
        this.fileSizeKb = fileSizeKb;
    }

    public FileDetailDto(String basePath, String filePath, String originalFilename, String fileExtension, Long fileSizeKb) {
        this.basePath = basePath;
        this.filePath = filePath;
        this.originalFilename = originalFilename;
        this.fileExtension = fileExtension;
        this.fileSizeKb = fileSizeKb;
    }

    public FileDetailDto() {
    }
}
