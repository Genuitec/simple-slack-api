package com.ullink.slack.simpleslackapi.events;

public interface SlackGroupRenamed extends SlackChannelEvent
{
	public String getNewName();
}
