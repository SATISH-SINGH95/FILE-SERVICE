package com.file_service.dto;

public class FileDataDtoRequest {

    private Integer parentId;
    private String fileTypeName;
    private Boolean fileChecker;
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
    public Boolean getFileChecker() {
        return fileChecker;
    }
    public void setFileChecker(Boolean fileChecker) {
        this.fileChecker = fileChecker;
    }

}
