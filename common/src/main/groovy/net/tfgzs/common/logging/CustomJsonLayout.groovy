package net.tfgzs.common.logging

import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.contrib.json.classic.JsonLayout

/**
 * Created by Liutengfei on 2016/8/11 0011.
 */
public class CustomJsonLayout extends JsonLayout {
    protected String logPrefix
    protected boolean logPrefixSwitch;

    CustomJsonLayout() {
        super();
        logPrefix = "~~~json~~~"
        logPrefixSwitch = true
    }

    @Override
    protected void addCustomDataToJsonMap(Map<String, Object> map, ILoggingEvent event) {
        if (event.getArgumentArray() && event.getArgumentArray()[0] instanceof Map) {
            map.putAll(event.getArgumentArray()[0])
        }
        if (event.getArgumentArray() && event.getArgumentArray()[0] instanceof String) {
            map.put("message", event.getArgumentArray()[0])
        }
    }

    @Override
    String doLayout(ILoggingEvent event) {
        if (logPrefixSwitch) {
            return logPrefix + super.doLayout(event)
        } else {
            return super.doLayout(event)
        }
    }

    String getLogPrefix() {
        return logPrefix
    }

    void setLogPrefix(String logPrefix) {
        this.logPrefix = logPrefix
    }

    boolean getLogPrefixSwitch() {
        return logPrefixSwitch
    }

    void setLogPrefixSwitch(boolean logPrefixSwitch) {
        this.logPrefixSwitch = logPrefixSwitch
    }
}
