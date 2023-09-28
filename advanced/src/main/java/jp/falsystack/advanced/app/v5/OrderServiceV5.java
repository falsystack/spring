package jp.falsystack.advanced.app.v5;

import jp.falsystack.advanced.trace.callback.TraceCallback;
import jp.falsystack.advanced.trace.callback.TraceTemplate;
import jp.falsystack.advanced.trace.logtrace.LogTrace;
import jp.falsystack.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {

  private final OrderRepositoryV5 orderRepository;
  private final TraceTemplate template;

  public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace trace) {
    this.orderRepository = orderRepository;
    this.template = new TraceTemplate(trace);
  }

  public void orderItem(String itemId) {
    template.execute("OrderService.orderItem", (TraceCallback<Void>) () -> {
      orderRepository.save(itemId);
      return null;
    });
  }
}
