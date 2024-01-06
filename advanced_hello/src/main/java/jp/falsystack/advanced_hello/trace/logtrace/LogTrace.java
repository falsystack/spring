package jp.falsystack.advanced_hello.trace.logtrace;

import jp.falsystack.advanced_hello.trace.TraceStatus;

public interface LogTrace {
    TraceStatus begin(String message);

    void end(TraceStatus status);

    void exception(TraceStatus status, Exception e);
}
