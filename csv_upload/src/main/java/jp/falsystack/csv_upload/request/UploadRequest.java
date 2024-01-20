package jp.falsystack.csv_upload.request;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class UploadRequest {
    private String projectName;
    private String content;
    private MultipartFile file;

    @Builder
    public UploadRequest(String projectName, String content, MultipartFile file) {
        this.projectName = projectName;
        this.content = content;
        this.file = file;
    }
}
