package com.file_service.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_file_attachments")
public class FileAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;
    private Integer parentId;
    private String fileTypeName;
    private String fileName;
    private String filePath;
    private Long fileSize; //should be file_size_kb
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
    private Integer active;
    private Integer createdBy;
    private String description;

    @PrePersist
    protected void onCreate() {
        createdOn = LocalDateTime.now();
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getFileTypeName() {
        return fileTypeName;
    }

    public void setFileTypeName(String fileTypeName) {
        this.fileTypeName = fileTypeName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FileAttachment(Long fileId, Integer parentId, String fileTypeName, String fileName, String filePath, Long fileSize, LocalDateTime createdOn, LocalDateTime modifiedOn, Integer active, Integer createdBy, String description) {
        this.fileId = fileId;
        this.parentId = parentId;
        this.fileTypeName = fileTypeName;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.active = active;
        this.createdBy = createdBy;
        this.description = description;
    }

    public FileAttachment() {
    }
}


