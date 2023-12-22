package jp.falsystack.upload_review.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import jp.falsystack.upload_review.domain.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileStore {

  @Value("${file.dir}")
  private String fileDir;

  public String getFullPath(String fileName) {
    return fileDir + fileName;
  }

  public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
    var storeFileResult = new ArrayList<UploadFile>();
    for (MultipartFile multipartFile : multipartFiles) {
      if (!multipartFile.isEmpty()) {
        storeFileResult.add(storeFile(multipartFile));
      }
    }
    return storeFileResult;
  }

  public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
    if (multipartFile.isEmpty()) {
      return null;
    }

    var originalFilename = multipartFile.getOriginalFilename();
    if (!StringUtils.hasText(originalFilename)) {
      return null;
    }
    // image.png
    var ext = extractExt(originalFilename);

    // 서버에 저장하는 파일명
    var uuid = UUID.randomUUID().toString();
    var storeFileName = uuid + "." + ext;

    multipartFile.transferTo(new File(getFullPath(storeFileName)));
    return new UploadFile(originalFilename, storeFileName);
  }

  private static String extractExt(String originalFilename) {
    var pos = originalFilename.lastIndexOf(".");
    return originalFilename.substring(pos + 1);
  }


}
