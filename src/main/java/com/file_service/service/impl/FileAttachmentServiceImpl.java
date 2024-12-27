package com.file_service.service.impl;

import com.file_service.repository.FileAttachmentRepository;
import com.file_service.dto.FileDataDto;
import com.file_service.dto.FileDetailDto;
import com.file_service.entity.FileAttachment;
import com.file_service.service.FileAttachmentService;
import com.file_service.service.FileService;
import com.file_service.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileAttachmentServiceImpl implements FileAttachmentService {

    Logger log = LoggerFactory.getLogger(FileAttachmentServiceImpl.class);
    private FileServiceFactory fileServiceFactory;
    private FileAttachmentRepository fileAttachmentRepository;

    public FileAttachmentServiceImpl(FileServiceFactory fileServiceFactory, FileAttachmentRepository fileAttachmentRepository) {
        this.fileServiceFactory = fileServiceFactory;
        this.fileAttachmentRepository = fileAttachmentRepository;
    }

    @Override
    @Transactional
    public FileAttachment saveFileAttachment(MultipartFile file, Integer parentId, String fileTypeName) throws IOException {

        try {
            FileService fileService = fileServiceFactory.getFileServiceInstance();
            FileDetailDto fileDetailDto = FileUtil.getFilenameDto(file, fileTypeName);
            if (!FileUtil.isAllowedExtension(fileDetailDto.getFileExtension())) {
                log.info("File extension is {}", fileDetailDto.getFileExtension());
                throw new RuntimeException("File with '" + fileDetailDto.getFileExtension() + "' extension is not allowed!!");
            }
            fileService.uploadFile(file, fileDetailDto);
            FileAttachment fileAttachment = new FileAttachment();
            fileAttachment.setParentId(parentId);
            fileAttachment.setFileTypeName(fileTypeName);
            fileAttachment.setFileName(fileDetailDto.getOriginalFilename());
            fileAttachment.setFilePath(fileDetailDto.getFilePath());
            fileAttachment.setFileSize(fileDetailDto.getFileSizeKb());
            fileAttachment.setCreatedOn(LocalDateTime.now());
            fileAttachment.setActive(1);

            return fileAttachmentRepository.save(fileAttachment);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @Override
    public FileDataDto getFileDataDtoById(Long fileId) {

        FileAttachment byIdAndActiveStatus = fileAttachmentRepository.findByFileIdAndStatus(fileId);
        FileDataDto fileDataDto = convertToFileDataDto(byIdAndActiveStatus, true);
        return fileDataDto;
    }

    public FileDataDto convertToFileDataDto(FileAttachment fileAttachment, Boolean fileChecker) {
        try {
            FileService fileService = fileServiceFactory.getFileServiceInstance();
            String fullPath = fileAttachment.getFilePath();
            int lastIndex = fullPath.lastIndexOf('/');
            String fileNameWithExtension2 = fullPath.substring(lastIndex + 1);
            String pathOnly = fullPath.substring(0, lastIndex) + "/";
            String fileData = null;
            if (fileChecker) {
                fileData = fileService.getBase64EncodedFile(pathOnly, fileNameWithExtension2);
            }
            return new FileDataDto(fileAttachment.getFileId(),fileAttachment.getFileName(), fileData, fileAttachment.getFileSize(), fileAttachment.getFileTypeName());
        } catch (Exception e) {
            throw new RuntimeException("Failed to get file data", e);
        }
    }

    @Override
    public List<FileDataDto> getFileDataDtoByParentIdAndFileTypeName(Integer parentId, String fileTypeName, Boolean fileChecker) {

        List<FileDataDto> list =new ArrayList<>();
        List<FileAttachment> fileAttachments = fileAttachmentRepository.findByParentIdAndFileTypeNameAndActiveStatus(parentId, fileTypeName);
        if (fileAttachments == null || fileAttachments.isEmpty()) {
            return list;
        }
        list= fileAttachments.stream()
                .map(fileAttachment -> convertToFileDataDto(fileAttachment, fileChecker))
                .collect(Collectors.toList());
        return list;
    }
}
