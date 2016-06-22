package dev.nick.logger;

public class LogTagBuilder {
    public String getCallingInfo() {
        StackTraceElement[] traceElements = Thread.currentThread().getStackTrace();
        if (traceElements.length <= 3) return null;
        StackTraceElement element = traceElements[3];
        return element.getMethodName()
                + "@" +
                element.getLineNumber() +
                "#" +
                element.getLineNumber();
    }
}
