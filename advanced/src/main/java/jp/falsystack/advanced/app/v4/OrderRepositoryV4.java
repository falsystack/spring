package jp.falsystack.advanced.app.v4;

import jp.falsystack.advanced.trace.TraceStatus;
import jp.falsystack.advanced.trace.logtrace.LogTrace;
import jp.falsystack.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

  private final LogTrace trace;

  public void save(String itemId) {
    AbstractTemplate<Void> template = new AbstractTemplate<Void>(trace) {
      @Override
      protected Void call() {
        //저장 로직
        if (itemId.equals("ex")) {
          throw new IllegalStateException("예외 발생!");
        }
        sleep(1000);
        return null;
      }
    };
    template.execute("OrderRepository.save");
  }

  private void sleep(int mills) {
    try {
      Thread.sleep(mills);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
