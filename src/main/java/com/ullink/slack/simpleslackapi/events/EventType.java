package com.ullink.slack.simpleslackapi.events;

import java.util.HashMap;
import java.util.Map;

public enum EventType
{
    MESSAGE("message"),
    GROUP_RENAMED("group_rename"),
    GROUP_JOINED("group_joined"),
    GROUP_LEFT("group_left"),
    CHANNEL_RENAMED("channel_rename"),
    CHANNEL_JOINED("channel_joined"),
    CHANNEL_LEFT("channel_left"),
    OTHER("-");

    private static final Map<String, EventType> CODE_MAP = new HashMap<>();

    static
    {
        for (EventType enumValue : EventType.values())
        {
            CODE_MAP.put(enumValue.getCode(), enumValue);
        }
    }

    private String                              code;

    public static EventType getByCode(String code)
    {
        EventType toReturn = CODE_MAP.get(code);
        if (toReturn == null)
        {
            return OTHER;
        }
        return toReturn;
    }

    EventType(String code)
    {
        this.code = code;
    }

    public String getCode()
    {
        return code;
    }

}
