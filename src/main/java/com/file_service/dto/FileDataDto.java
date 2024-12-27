package com.file_service.dto;

public class FileDataDto {
	private Long fileId;
	private String fileName; // Original file name
	private String fileData; // Base64 encoded file content
	private Long fileSize;
	private String filetype;

    public Long getFileId() {
        return fileId;
    }
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFileData() {
        return fileData;
    }
    public void setFileData(String fileData) {
        this.fileData = fileData;
    }
    public Long getFileSize() {
        return fileSize;
    }
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
    public String getFiletype() {
        return filetype;
    }
    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }
    public FileDataDto(Long fileId, String fileName, String fileData, Long fileSize, String filetype) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileData = fileData;
        this.fileSize = fileSize;
        this.filetype = filetype;
    }
    public FileDataDto() {
    }




}
