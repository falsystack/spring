package jp.falsystack.csv_upload.service;

import jp.falsystack.csv_upload.request.UploadRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {
    public void upload(UploadRequest request) {

        MultipartFile file = request.getFile();


    }
}
