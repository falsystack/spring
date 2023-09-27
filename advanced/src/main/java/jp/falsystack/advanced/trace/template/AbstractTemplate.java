package jp.falsystack.advanced.trace.template;

import jp.falsystack.advanced.trace.TraceStatus;
import jp.falsystack.advanced.trace.logtrace.LogTrace;

public abstract class AbstractTemplate<T> {

  private final LogTrace trace;

  public AbstractTemplate(LogTrace trace) {
    this.trace = trace;
  }

  public T execute(String message) {
    TraceStatus status = null;
    try {
      status = trace.begin(message);

      // call logic
      T result = call();

      trace.end(status);
      return result;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  protected abstract T call();
}
