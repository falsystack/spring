package jp.falsystack.total.service;

import jp.falsystack.total.domain.Post;
import jp.falsystack.total.repository.PostRepository;
import jp.falsystack.total.request.PostCreate;
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

}
