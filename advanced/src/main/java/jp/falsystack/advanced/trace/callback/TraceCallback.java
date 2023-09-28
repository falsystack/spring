package jp.falsystack.advanced.trace.callback;

@FunctionalInterface
public interface TraceCallback<T> {
  T call();
}
