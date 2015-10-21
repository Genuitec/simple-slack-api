package com.ullink.slack.simpleslackapi;

public interface SlackUser extends SlackPersona, SlackBot
{
    public String getIMChannelID();

	public boolean hasIMChannel();
}
