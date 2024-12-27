# File Service API

## Overview
The File Service API provides endpoints for handling file attachments in a parent-child context. It allows uploading files, retrieving file metadata, and querying file details based on specific criteria. This API is designed for efficient file management in applications that require file storage and retrieval capabilities.

## Key Features
- Upload files with metadata.
- Retrieve file metadata using a file ID.
- Query files based on parent ID and file type name.

## API Endpoints

### 1. Save File
Uploads a file and associates it with a parent ID and file type.

**Endpoint:**
```
POST /file/api/v1/save-file
```

**Request Parameters:**
- `file` (MultipartFile) - The file to upload.
- `parentId` (Integer) - The ID of the parent entity.
- `fileTypeName` (String) - The type of the file.

**Response:**
- **201 Created:** Returns the saved `FileAttachment` entity.
- **Error:** If an error occurs during the file save operation.

**Example cURL Request:**
```bash
curl --location 'localhost:9191/file/api/v1/save-file' \
--form 'file=@"/C:/Users/Yoga/Desktop/place of business.jpeg"' \
--form 'parentId="1"' \
--form 'fileTypeName="IMAGE"'
```

---

### 2. Retrieve File Metadata by File ID
Fetches metadata of a file based on its unique file ID.

**Endpoint:**
```
POST /file/api/v1/file/{fileId}
```

**Path Variables:**
- `fileId` (Long) - The unique identifier of the file.

**Response:**
- **200 OK:** Returns a `FileDataDto` containing metadata of the file.
- **Error:** If the file ID does not exist.

**Example cURL Request:**
```bash
curl --location --request POST 'localhost:9191/file/api/v1/file/1' \
--data ''
```

---

### 3. Get File Data by Parent ID and File Type
Queries files based on `parentId`, `fileTypeName`, and an optional `fileChecker` flag.

**Endpoint:**
```
POST /file/api/v1/get-file-data
```

**Request Body:**
```json
{
  "parentId": 123,
  "fileTypeName": "invoice",
  "fileChecker": true
}
```

**Response:**
- **200 OK:** Returns a list of `FileDataDto` objects matching the criteria.
- **Error:** If no files match the criteria.

**Example cURL Request:**
```bash
curl --location 'localhost:9191/file/api/v1/get-file-data' \
--header 'Content-Type: application/json' \
--data '{
    "parentId": 1,
    "fileTypeName": "IMAGE",
    "fileChecker": false
}'
```

---

## DTOs

### FileDataDto
Represents metadata for a file.
```java
public class FileDataDto {
  private Long fileId;
	private String fileName;
	private String fileData; 
	private Long fileSize;
	private String filetype;

}
```

### FileDataDtoRequest
Represents the request structure for querying file data.
```java
public class FileDataDtoRequest {
    private Integer parentId;
    private String fileTypeName;
    private Boolean fileChecker;
}
```

---

## Service Integration
This controller relies on the `FileAttachmentService` to handle business logic. Ensure that the service is properly implemented to support:
- Saving files to storage.
- Fetching file metadata by file ID.
- Querying files by parent ID and file type.

---

## How to Use
1. Clone the repository.
2. Ensure the necessary dependencies for Spring Boot and file upload are included in your `pom.xml`.
3. Implement the `FileAttachmentService` interface for your storage mechanism (e.g., local storage, cloud storage).
4. Start the application and interact with the endpoints using your preferred API testing tool.

---

## Dependencies
Ensure you have the following dependencies in your project:
- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Lombok
- Apache Commons FileUpload (optional)
- My SQL

---

## Contribution
Contributions are welcome! If you encounter any issues or have suggestions for improvements, feel free to open an issue or submit a pull request.


