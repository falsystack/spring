package jp.falsystack.advanced_hello.app.v4;

import jp.falsystack.advanced_hello.trace.TraceStatus;
import jp.falsystack.advanced_hello.trace.logtrace.LogTrace;
import jp.falsystack.advanced_hello.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {

    private final OrderServiceV4 orderService;
    private final LogTrace trace;

    @GetMapping("/v4/request")
    public String request(String itemId) {

        AbstractTemplate<String> template = new AbstractTemplate<>(trace) {
            @Override
            protected String call() {
                orderService.orderItem(itemId);

                return "ok";
            }
        };
        return template.execute("OrderController.request");
    }
}
