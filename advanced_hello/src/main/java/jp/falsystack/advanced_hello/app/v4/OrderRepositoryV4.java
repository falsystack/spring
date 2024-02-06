package jp.falsystack.advanced_hello.app.v4;

import jp.falsystack.advanced_hello.trace.TraceStatus;
import jp.falsystack.advanced_hello.trace.logtrace.LogTrace;
import jp.falsystack.advanced_hello.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

    private final LogTrace trace;

    public void save(String itemId) {
        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            protected Void call() {
                // 저장 로직
                if (itemId.equals("ex")) {
                    throw new IllegalStateException("例外発生！");
                }
                sleep(1000);
                return null;
            }
        };
        template.execute("OrderRepositoryV4.request");
    }

    private void sleep(int mills) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
