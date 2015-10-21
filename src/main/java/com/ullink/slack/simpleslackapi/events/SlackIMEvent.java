package com.ullink.slack.simpleslackapi.events;

public interface SlackIMEvent extends SlackEvent
{
	public String getUser();
	
	public String getChannel();
}
