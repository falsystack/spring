package jp.falsystack.advanced.trace.logtrace;

import jp.falsystack.advanced.trace.TraceStatus;

public interface LogTrace {

  TraceStatus begin(String message);

  void end(TraceStatus status);

  void exception(TraceStatus status, Exception e);

}
