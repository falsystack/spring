package jp.falsystack.advanced_hello.app.v4;

import jp.falsystack.advanced_hello.trace.logtrace.LogTrace;
import jp.falsystack.advanced_hello.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

    private final OrderRepositoryV4 orderRepository;
    private final LogTrace trace;


    public void orderItem(String itemId) {
        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            protected Void call() {
                orderRepository.save(itemId);


                return null;
            }
        };
        template.execute("OrderController.request");
    }
}
