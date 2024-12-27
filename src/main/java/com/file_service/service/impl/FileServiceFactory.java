package com.file_service.service.impl;


import com.file_service.service.FileService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileServiceFactory {

    private FileService localFileService;

    @Value("${file.upload.service.type}")
    private String fileServiceType;

    public FileServiceFactory(FileService localFileService) {
        this.localFileService = localFileService;
    }

    public FileService getFileServiceInstance() {
            return localFileService;
    }

    public FileService getLocalFileService() {
        return localFileService;
    }

    public String getFileServiceType() {
        return fileServiceType;
    }
}
