package com.ullink.slack.simpleslackapi;

public interface SlackChannel
{
    String getId();

    String getName();

    String getTopic();

    String getPurpose();

    boolean isDirect();

	void setName(String newName);

}
