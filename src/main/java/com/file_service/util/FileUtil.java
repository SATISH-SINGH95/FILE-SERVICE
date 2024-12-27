package com.file_service.util;


import com.file_service.constants.FileConstant;
import com.file_service.dto.FileDetailDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class FileUtil {
    private FileUtil() {
    }

    protected static final Set<String> EXTENSION = new HashSet<>(
            Arrays.asList("png", "jpeg", "pdf", "csv", "doc", "jpg", "zip", "xls", "xlsx", "docx"));

    public static File convertMultiPartFileToFile(MultipartFile file, String newfilename) throws IOException {
        File convertedFile = new File(newfilename);
        File parentDir = convertedFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
        }
        return convertedFile;
    }

    public static boolean isAllowedExtension(String extension) {
        if (StringUtils.isNotBlank(extension)) {
            return EXTENSION.contains(extension.toLowerCase());
        }
        return Boolean.FALSE;
    }

    public static FileDetailDto getFilenameDto(final MultipartFile file, String fileTypeName) {
        if (file == null) {
            return null;
        }
        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if (originalFilename != null && originalFilename.lastIndexOf(".") > -1) {
            extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        }
        StringBuilder b = new StringBuilder(100);
        b.append(FileConstant.PARENT_DIRECTORY).append(FileConstant.FILE_SEPARATOR)
                .append(formatFileTypeToPath(fileTypeName)).append(FileConstant.FILE_SEPARATOR);
        String basePath = b.toString();
        b.append(UUID.randomUUID()).append('.').append(extension);
        Long fileSizeKb = file.getSize();// now file size is in bytes
        return new FileDetailDto(basePath, b.toString(), originalFilename, extension, fileSizeKb);
    }

    private static String formatFileTypeToPath(String fileType) {
        return fileType.toLowerCase().replace("_", "-");
    }
}
