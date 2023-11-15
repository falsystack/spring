package jp.falsystack.total.service;

import java.util.List;
import jp.falsystack.total.domain.Post;
import jp.falsystack.total.repository.PostRepository;
import jp.falsystack.total.request.PostCreate;
import jp.falsystack.total.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;

  public void write(PostCreate postCreate) {
    var post = Post.builder()
        .title(postCreate.getTitle())
        .content(postCreate.getContent())
        .build();
    postRepository.save(post);
  }

  public PostResponse get(Long postId) {
    var post = postRepository.findById(postId)
        .orElseThrow(() -> new IllegalArgumentException("nono"));
    return PostResponse.builder()
        .id(post.getId())
        .title(post.getTitle())
        .content(post.getContent())
        .build();
  }

  public List<PostResponse> getList() {
    return postRepository
        .findAll()
        .stream()
        .map(PostResponse::from)
        .toList();
  }
}
