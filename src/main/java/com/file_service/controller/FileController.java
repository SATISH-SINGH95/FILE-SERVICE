package com.file_service.controller;

import com.file_service.dto.FileDataDto;
import com.file_service.dto.FileDataDtoRequest;
import com.file_service.entity.FileAttachment;
import com.file_service.service.FileAttachmentService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("file/api/v1")
public class FileController {

    private FileAttachmentService fileAttachmentService;

    public FileController(FileAttachmentService fileAttachmentService) {
        this.fileAttachmentService = fileAttachmentService;
    }


    @PostMapping("/save-file")
    public ResponseEntity<?> getFileId(@RequestParam("file") MultipartFile file,
                                       @RequestParam("parentId") Integer parentId,
                                       @RequestParam("fileTypeName") String fileTypeName) {
        try {
            FileAttachment fileAttachment = fileAttachmentService.saveFileAttachment(file, parentId, fileTypeName);
            return new ResponseEntity<>(fileAttachment, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("file/{fileId}")
	public ResponseEntity<?> getFileId(@PathVariable Long fileId) {
			FileDataDto fileDataDto = fileAttachmentService.getFileDataDtoById(fileId);
			return new ResponseEntity<>(fileDataDto, HttpStatus.OK);
	}

    @PostMapping("get-file-data")
	public ResponseEntity<?> getFileDataDtoByParentIdAndFileTypeName(@RequestBody FileDataDtoRequest fileDataDtoRequest) {
		List<FileDataDto> response = fileAttachmentService.getFileDataDtoByParentIdAndFileTypeName(fileDataDtoRequest.getParentId(), fileDataDtoRequest.getFileTypeName(), fileDataDtoRequest.getFileChecker());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


}
