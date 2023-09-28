package jp.falsystack.advanced.trace.callback;

import jp.falsystack.advanced.trace.TraceStatus;
import jp.falsystack.advanced.trace.logtrace.LogTrace;

public class TraceTemplate {

  private final LogTrace trace;

  public TraceTemplate(LogTrace trace) {
    this.trace = trace;
  }

  public <T> T execute(String message, TraceCallback<T> callback) {
    TraceStatus status = null;
    try {
      status = trace.begin(message);

      // call logic
      T result = callback.call();

      trace.end(status);
      return result;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
