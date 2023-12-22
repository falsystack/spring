package jp.falsystack.upload_review.controller;

import static java.nio.charset.StandardCharsets.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/servlet/v2")
public class ServletUploadControllerV2 {

  @Value("${file.dir}")
  private String fileDir;

  @GetMapping("/upload")
  public String newFile() {
    return "upload-form";
  }

  @PostMapping("/upload")
  public String saveFileV2(HttpServletRequest request) throws ServletException, IOException {
    log.info("request = {}", request);

    var itemName = request.getParameter("itemName");
    log.info("itemName = {}", itemName);

    // http multipart 의 그 part 이다.
    var parts = request.getParts();
    log.info("parts = {}", parts);

    for (Part part : parts) {
      log.info("=== PART ===");
      log.info("part.name = {}", part.getName());
      // part 도 헤더와 바디로 구분된다.
      var headerNames = part.getHeaderNames();
      for (String headerName : headerNames) {
        log.info("header = {}: {},", headerName, part.getHeader(headerName));
      }
      // 편의 메서드
      // Content-Disposition: form-data; name="file"; filename="TH_IH14A083_26.jpg"
      log.info("submittedFilename = {}", part.getSubmittedFileName());

      // 데이터 읽기
      var inputStream = part.getInputStream();
      var body = StreamUtils.copyToString(inputStream, UTF_8);
      log.info("body = {}", body);

      // 파일 저장
      if (StringUtils.hasText(part.getSubmittedFileName())) {
        var fullPath = fileDir + part.getSubmittedFileName();
        log.info("파일 저장 fullPath = {}", fullPath);
        part.write(fullPath);
      }
    }

    return "upload-form";
  }
}
