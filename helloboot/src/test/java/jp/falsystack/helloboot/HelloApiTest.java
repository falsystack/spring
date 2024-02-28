package jp.falsystack.helloboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

public class HelloApiTest {

    @Test
    void helloApi() {
        TestRestTemplate rest = new TestRestTemplate();
        ResponseEntity<String> resp =
                rest.getForEntity("http://localhost:8080/hello?name={name}", String.class, "Spring");


    }
}
