package dev.nick.logger;

class CallingInfoBuilderImpl implements CallingInfoBuilder {

    @Override
    public String getCallingInfo() {
        StackTraceElement[] traceElements = Thread.currentThread().getStackTrace();
        if (traceElements.length <= 4) return null;
        StackTraceElement element = traceElements[4];
        return element.getMethodName()
                + "()@" +
                element.getFileName() +
                "#" +
                element.getLineNumber();
    }
}
