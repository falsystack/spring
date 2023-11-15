package jp.falsystack.total.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import jp.falsystack.total.domain.Post;
import jp.falsystack.total.repository.PostRepository;
import jp.falsystack.total.request.PostCreate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostServiceTest {

  @Autowired
  private PostService postService;
  @Autowired
  private PostRepository postRepository;

  @Test
  @DisplayName("포스트 요청시 DB에 값이 저장된다.")
  void saveInToDB() {
    // given
    var request = PostCreate.builder()
        .title("타이틀 입니다.")
        .content("내용 입니다.")
        .build();
    // when
    postService.write(request);
    // then
    Post post = postRepository.findAll().get(0);
    assertThat(post.getTitle()).isEqualTo("타이틀 입니다.");
  }

  @Test
  @DisplayName("글 여러개 조회")
  void getList() {
    // given
    var post1 = Post.builder()
        .title("타이틀 입니다.")
        .content("내용 입니다.")
        .build();
    postRepository.save(post1);

    var post2 = Post.builder()
        .title("타이틀 입니다.")
        .content("내용 입니다.")
        .build();
    postRepository.save(post2);
    // when
    var posts = postService.getList();

    // then
    assertThat(posts).hasSize(2);
  }

}