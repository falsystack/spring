package jp.falsystack.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {

//  @Slf4j 로 대체
//  private final Logger log = LoggerFactory.getLogger(getClass());

  @RequestMapping("/log-test")
  public String logTest() {
    String name = "Spring";
    log.info("name = {} ", name);
    log.debug("name = {} ", name);

    return "ok";
  }
}
