package dev.nick.logger;

import android.util.Log;

class LoggerImpl implements Logger {

    LogTagBuilder mLogTagBuilder;
    CallingInfoBuilder mCallingInfoBuilder;

    String mLogTag;

    public LoggerImpl(LogTagBuilder tagBuilder, CallingInfoBuilder infoBuilder, String prop) {
        this.mLogTagBuilder = tagBuilder;
        this.mCallingInfoBuilder = infoBuilder;
        this.mLogTag = mLogTagBuilder.buildLogTag(prop);
    }

    @Override
    public boolean isDebuggable(int level) {
        return true;
    }

    @Override
    public void funcEnter() {
        Log.d(mLogTag, mCallingInfoBuilder.getCallingInfo() + "---ENTER");
    }

    @Override
    public void funcExit() {
        Log.d(mLogTag, mCallingInfoBuilder.getCallingInfo() + "---EXIT");
    }

    @Override
    public void info(Object o) {
        Log.d(mLogTag, mCallingInfoBuilder.getCallingInfo()
                + "---"
                + String.valueOf(o));
    }

    @Override
    public void debug(Object o) {
        Log.d(mLogTag, mCallingInfoBuilder.getCallingInfo()
                + "---"
                + String.valueOf(o));
    }

    @Override
    public void warn(Object o) {
        Log.d(mLogTag, mCallingInfoBuilder.getCallingInfo()
                + "---"
                + String.valueOf(o));
    }

    @Override
    public void error(Object o) {
        Log.d(mLogTag, mCallingInfoBuilder.getCallingInfo()
                + "---"
                + String.valueOf(o));
    }

    @Override
    public void trace(String traceMsg, Throwable throwable) {
        Log.d(mLogTag, traceMsg
                + mCallingInfoBuilder.getCallingInfo()
                + "---"
                + Log.getStackTraceString(throwable));
    }
}
