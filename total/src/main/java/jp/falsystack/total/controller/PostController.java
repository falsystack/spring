package jp.falsystack.total.controller;

import jakarta.validation.Valid;
import java.util.List;
import jp.falsystack.total.request.PostCreate;
import jp.falsystack.total.response.PostResponse;
import jp.falsystack.total.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;

  @PostMapping("/posts")
  public void write(@Valid @RequestBody PostCreate request) {
    postService.write(request);
  }

  @GetMapping("/posts/{postId}")
  public PostResponse get(@PathVariable Long postId) {
    return postService.get(postId);
  }

  @GetMapping("/posts")
  public List<PostResponse> getList() {
    return postService.getList();
  }

}
