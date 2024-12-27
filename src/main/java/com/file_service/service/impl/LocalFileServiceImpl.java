package com.file_service.service.impl;

import com.file_service.constants.FileConstant;
import com.file_service.dto.FileDetailDto;
import com.file_service.service.FileService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@Service
public class LocalFileServiceImpl implements FileService {

    @Value("${file.upload.local.directory}")
    private String localBaseDirectory;
    @Override
    public void uploadFile(MultipartFile file, FileDetailDto fileDetailDto) throws IOException {
        if (!(ObjectUtils.isNotEmpty(fileDetailDto) && fileDetailDto.isNotEmpty())) {
            throw new RuntimeException("File input is empty");
        }
        if (StringUtils.isNotBlank(localBaseDirectory)) {
            fileDetailDto.setBasePath(localBaseDirectory + FileConstant.FILE_SEPARATOR + fileDetailDto.getBasePath());
            fileDetailDto.setFilePath(localBaseDirectory + FileConstant.FILE_SEPARATOR+fileDetailDto.getFilePath());

        }

        File folder = new File(fileDetailDto.getBasePath());
        if (!folder.exists()) {
            folder.mkdirs();

        }
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, Paths.get(fileDetailDto.getFilePath()));
        } catch (IOException e) {

            e.printStackTrace();
            throw e;
        }


    }
    @Override
    public String getBase64EncodedFile(String path, String name) {

        try (InputStream intStream = new FileInputStream(path  + name)) {
            return getBase64EncodedFile(intStream);
        } catch(Exception e){
            throw new RuntimeException();
        }
    }

    public String getBase64EncodedFile(InputStream inputStream) throws IOException {
        byte[] byteArray = readInputStreamToByteArray(inputStream);
        return Base64.getEncoder().encodeToString(byteArray);
    }

    public byte[] readInputStreamToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384];
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }
}

