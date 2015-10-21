package com.ullink.slack.simpleslackapi.impl;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.events.SlackEventType;
import com.ullink.slack.simpleslackapi.events.SlackGroupRenamed;

class SlackGroupRenamedImpl implements SlackGroupRenamed
{
    private SlackChannel slackChannel;
    private String       newName;

    SlackGroupRenamedImpl(SlackChannel slackChannel, String newName)
    {
        this.slackChannel = slackChannel;
        this.newName = newName;
    }

    @Override
    public SlackChannel getSlackChannel()
    {
        return slackChannel;
    }

    @Override
    public String getNewName()
    {
        return newName;
    }

    @Override
    public SlackEventType getEventType()
    {
        return SlackEventType.SLACK_GROUP_RENAMED;
    }

}
