package jp.falsystack.total.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import jp.falsystack.total.domain.Post;
import jp.falsystack.total.repository.PostRepository;
import jp.falsystack.total.request.PostCreate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private PostRepository postRepository;

  @Test
  @DisplayName("posts create")
  void postCreate() throws Exception {
    var request = PostCreate.builder()
        .title("this is title")
        .content("this is content")
        .build();
    var json = objectMapper.writeValueAsString(request);

    mockMvc.perform(post("/posts")
            .contentType(APPLICATION_JSON)
            .content(json))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  @DisplayName("포스트 요청시 타이틀값은 필수다")
  void requiredTitle() throws Exception {
    var request = PostCreate.builder()
        .title(null)
        .content("this is content")
        .build();
    var json = objectMapper.writeValueAsString(request);

    mockMvc.perform(post("/posts")
            .contentType(APPLICATION_JSON)
            .content(json))
        .andExpect(status().isBadRequest())
        .andExpectAll(
            jsonPath("$.code", is("400")),
            jsonPath("$.message", is("잘못된 요청입니다.")),
            jsonPath("$.validation.title", is("타이틀은 필수 입니다."))
        )
        .andDo(print());
  }

  @Test
  @DisplayName("포스트 요청시 DB에 값이 저장된다.")
  void postSaveToDB() throws Exception {
    var request = PostCreate.builder()
        .title("this is title")
        .content("this is content")
        .build();
    var json = objectMapper.writeValueAsString(request);

    mockMvc.perform(post("/posts")
            .contentType(APPLICATION_JSON)
            .content(json))
        .andExpect(status().isOk())
        .andDo(print());

    var post = postRepository.findAll().get(0);
    assertThat(post.getTitle()).isEqualTo("this is title");
    assertThat(post.getContent()).isEqualTo("this is content");

  }

  @Test
  @DisplayName("포스트 여러개 조회")
  void getList() throws Exception {
    var post1 = Post.builder()
        .title("테스트 타이틀1")
        .content("테스트 컨텐츠1")
        .build();
    var post2 = Post.builder()
        .title("테스트 타이틀2")
        .content("테스트 컨텐츠2")
        .build();
    postRepository.saveAll(List.of(post1, post2));

    mockMvc.perform(get("/posts")
            .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.size()", is(2)))
        .andExpect(jsonPath("$[0].id", is(post1.getId().intValue())))
        .andExpect(jsonPath("$[0].title", is("테스트 타이틀1")))
        .andExpect(jsonPath("$[0].content", is("테스트 컨텐츠1")))
        .andDo(print());
  }

}