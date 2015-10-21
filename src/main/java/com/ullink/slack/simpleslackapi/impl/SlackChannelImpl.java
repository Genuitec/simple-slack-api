package com.ullink.slack.simpleslackapi.impl;

import com.ullink.slack.simpleslackapi.SlackChannel;

class SlackChannelImpl implements SlackChannel
{
    private final boolean direct;
    private String         id;
    private String         name;
    private String         topic;
    private String         purpose;

    SlackChannelImpl(String id, String name, String topic, String purpose, boolean direct)
    {
        this.id = id;
        this.name = name;
        this.topic = topic;
        this.purpose = purpose;
        this.direct = direct;
    }

    @Override
    public String getId()
    {
        return id;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getTopic()
    {
        return topic;
    }

    @Override
    public String toString() {
        return "SlackChannelImpl{" +
                "topic='" + topic + '\'' +
                ", purpose='" + purpose + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public String getPurpose()
    {
        return purpose;
    }

    @Override
    public boolean isDirect() {
        return direct;
    }
    
    @Override
    public void setName(String newName) {
    	this.name = newName;
    }
}
