package com.file_service.repository;

import com.file_service.entity.FileAttachment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FileAttachmentRepository extends JpaRepository<FileAttachment, Long> {

    @Query("SELECT f FROM FileAttachment f WHERE f.fileId = :fileId AND f.active = 1")
    FileAttachment findByFileIdAndStatus(@Param("fileId") Long fileId);

    @Query("SELECT f FROM FileAttachment f WHERE f.parentId = :parentId AND f.fileTypeName = :fileTypeName AND f.active = 1")
    List<FileAttachment> findByParentIdAndFileTypeNameAndActiveStatus(Integer parentId, String fileTypeName);
}
