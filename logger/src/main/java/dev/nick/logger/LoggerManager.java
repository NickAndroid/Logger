package dev.nick.logger;

import java.util.HashMap;

public class LoggerManager {


    final static HashMap<String, Logger> sLoggers = new HashMap<>();


    public static Logger getLogger(Class propertyClz) {

        String propName = propertyClz.getSimpleName();

        synchronized (sLoggers) {
            if (sLoggers.containsKey(propName)) return sLoggers.get(propName);
            Logger logger = new LoggerImpl(new LogTagBuilder() {
                @Override
                public String buildLogTag(String prop) {
                    return prop;
                }
            }, new CallingInfoBuilderImpl(), propName);

            sLoggers.put(propName, logger);

            return logger;
        }
    }
}
