package dev.nick.logger;

public interface Logger {

    boolean isDebuggable(int level);

    void funcEnter();

    void funcExit();

    void info(Object o);

    void debug(Object o);

    void warn(Object o);

    void error(Object o);

    void trace(String trackMsg, Throwable throwable);
}
