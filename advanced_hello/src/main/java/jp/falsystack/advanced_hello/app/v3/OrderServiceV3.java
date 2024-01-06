package jp.falsystack.advanced_hello.app.v3;

import jp.falsystack.advanced_hello.trace.TraceId;
import jp.falsystack.advanced_hello.trace.TraceStatus;
import jp.falsystack.advanced_hello.trace.hellotrace.HelloTraceV2;
import jp.falsystack.advanced_hello.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;


    public void orderItem(String itemId) {

        TraceStatus status = null;
        try {
            status = trace.begin("OrderService.request");
            orderRepository.save(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; // 예외를 꼭 다시 던져주어야 한다.
        }

    }
}
