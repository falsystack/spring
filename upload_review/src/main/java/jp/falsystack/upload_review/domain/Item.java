package jp.falsystack.upload_review.domain;

import java.util.List;
import lombok.Data;

@Data
public class Item {

  private Long id;
  private String itemName;
  private UploadFile attachFIle;
  private List<UploadFile> imageFiles;

}
