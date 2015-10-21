package com.ullink.slack.simpleslackapi.impl;

import com.ullink.slack.simpleslackapi.events.SlackEventType;
import com.ullink.slack.simpleslackapi.events.SlackIMClose;

public class SlackIMCloseImpl implements SlackIMClose {

	private String user;
	private String channel;

	SlackIMCloseImpl(String user, String channel) {
		this.user = user;
		this.channel = channel;
	}
	
	@Override
	public String getUser() {
		return user;
	}

	@Override
	public String getChannel() {
		return channel;
	}

	@Override
	public SlackEventType getEventType() {
		return SlackEventType.SLACK_IM_CLOSE;
	}

}
