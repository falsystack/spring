package jp.falsystack.csv_upload.controller;

import jp.falsystack.csv_upload.request.UploadRequest;
import jp.falsystack.csv_upload.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UploadController {

    private final UploadService uploadService;

    @PostMapping("/upload")
    public void upload(UploadRequest request) {
        uploadService.upload(request);
    }
}
