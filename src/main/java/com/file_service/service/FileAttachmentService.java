package com.file_service.service;

import com.file_service.dto.FileDataDto;
import com.file_service.entity.FileAttachment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileAttachmentService {
    FileAttachment saveFileAttachment(MultipartFile file, Integer parentId, String fileTypeName) throws IOException;

    FileDataDto getFileDataDtoById(Long fileId);

    List<FileDataDto> getFileDataDtoByParentIdAndFileTypeName(Integer parentId, String fileTypeName,Boolean fileChecker);
}
