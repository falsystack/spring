package jp.falsystack.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MappingController {

  @RequestMapping("/hello-basic")
  public String helloBasic() {
    log.info("hello basic");

    return "ok";
  }

  @GetMapping("/mapping-get-v2")
  public String mappingGetV2() {
    log.info("mapping-get-v2");

    return "ok";
  }

  @GetMapping("/mapping/{userId}")
  public String mappingPathV1(@PathVariable("userId") String data) {
    log.info("data = {} ", data);

    return "ok";
  }

//  @GetMapping("/mapping/{userId}")
  public String mappingPathV2(@PathVariable String userId) {
    log.info("data = {} ", userId);

    return "ok";
  }

  @GetMapping("/mapping/users/{userId}/orders/{orderId}")
  public String mappingPathV3(@PathVariable String userId, @PathVariable String orderId) {
    log.info("userId = {} ", userId);
    log.info("orderId = {} ", orderId);

    return "ok";
  }

  @GetMapping(value = "/mapping/param", params = "mode=debug")
  public String mappingParam() {
    log.info("mappingParam");

    return "ok";
  }

  @GetMapping(value = "/mapping-header", headers = "mode=debug")
  public String mappingHeader() {
    log.info("MappingController.mappingHeader");

    return "ok";
  }

  @PostMapping(value = "mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
  public String mappingConsumes() {
    log.info("MappingController.mappingConsumes");

    return "ok";
  }

  @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
  public String mappingProduces() {
    log.info("MappingController.mappingProduces");

    return "ok";
  }
}
