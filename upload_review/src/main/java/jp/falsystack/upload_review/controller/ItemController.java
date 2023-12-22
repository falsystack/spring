package jp.falsystack.upload_review.controller;

import java.io.IOException;
import java.util.List;
import jp.falsystack.upload_review.domain.Item;
import jp.falsystack.upload_review.domain.ItemRepository;
import jp.falsystack.upload_review.domain.UploadFile;
import jp.falsystack.upload_review.file.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

  private final ItemRepository itemRepository;
  private final FileStore fileStore;

  @GetMapping("/items/new")
  public String newItem(@ModelAttribute ItemForm form) {
    return "item-form";
  }

  @PostMapping("/items/new")
  public String saveItem(@ModelAttribute ItemForm form, RedirectAttributes redirectAttributes)
      throws IOException {
    var attachFile = fileStore.storeFile(form.getAttachFile());
    var storeImageFiles = fileStore.storeFiles(form.getImageFiles());

    // DB에 저장
    var item = new Item();
    item.setItemName(form.getItemName());
    item.setAttachFIle(attachFile);
    item.setImageFiles(storeImageFiles);
    itemRepository.save(item);

    redirectAttributes.addAttribute("itemId", item.getId());

    return "redirect:/items/{itemId}";
  }

  @GetMapping("/items/{id}")
  public String items(@PathVariable Long id, Model model) {
    var item = itemRepository.findById(id);
    model.addAttribute("item", item);
    return "item-view";
  }
}
