package jp.co.falsystack.ssiach2ex.controllers;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@AutoConfigureMockMvc
@SpringBootTest
class HelloControllerTest {

    private final MockMvc mvc;

    @Test
    void helloUnauthenticated() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void helloAuthenticated() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(content().string("Hello!"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("john")
    void helloAuthenticatedWithUserDetails() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(content().string("Hello!"))
                .andExpect(status().isOk());
    }


}