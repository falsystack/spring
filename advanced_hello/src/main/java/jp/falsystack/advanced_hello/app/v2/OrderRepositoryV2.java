package jp.falsystack.advanced_hello.app.v2;

import jp.falsystack.advanced_hello.trace.TraceId;
import jp.falsystack.advanced_hello.trace.TraceStatus;
import jp.falsystack.advanced_hello.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final HelloTraceV2 trace;

    public void save(TraceId traceId, String itemId) {


        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId, "OrderRepositoryV1.request");
            // 저장 로직
            if (itemId.equals("ex")) {
                throw new IllegalStateException("例外発生！");
            }
            sleep(1000);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; // 예외를 꼭 다시 던져주어야 한다.
        }
    }

    private void sleep(int mills) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
