package com.ullink.slack.simpleslackapi;

import java.io.IOException;
import java.util.Collection;

import com.ullink.slack.simpleslackapi.impl.SlackChatConfiguration;
import com.ullink.slack.simpleslackapi.listeners.SlackChannelRenamedListener;
import com.ullink.slack.simpleslackapi.listeners.SlackGroupJoinedListener;
import com.ullink.slack.simpleslackapi.listeners.SlackMessageDeletedListener;
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener;
import com.ullink.slack.simpleslackapi.listeners.SlackMessageUpdatedListener;

public interface SlackSession
{

    Collection<SlackChannel> getChannels();

    Collection<SlackUser> getUsers();

    Collection<SlackBot> getBots();

    SlackChannel findChannelByName(String channelName);

    SlackChannel findChannelById(String channelId);

    SlackUser findUserById(String userId);

    SlackUser findUserByUserName(String userName);

    SlackUser findUserByEmail(String userMail);

    SlackPersona sessionPersona();

    SlackBot findBotById(String botId);
    
    void connect() throws IOException;

    void disconnect() throws IOException;

    SlackMessageHandle deleteMessage(String timeStamp, SlackChannel channel);

    SlackMessageHandle sendMessage(SlackChannel channel, String message, SlackAttachment attachment, SlackChatConfiguration chatConfiguration);

    SlackMessageHandle sendMessage(SlackChannel channel, String message, SlackAttachment attachment);
    
    SlackMessageHandle updateMessage(String timeStamp, SlackChannel channel, String message);

    SlackMessageHandle sendMessageOverWebSocket(SlackChannel channel, String message, SlackAttachment attachment);

    SlackMessageHandle addReactionToMessage(SlackChannel channel, String messageTimeStamp, String emojiCode);

    SlackPersona.SlackPresence getPresence(SlackPersona persona);

    void addChannelRenamedListener(SlackChannelRenamedListener listener);

    void removeChannelRenamedListener(SlackChannelRenamedListener listener);

    void addMessageDeletedListener(SlackMessageDeletedListener listener);

    void removeMessageDeletedListener(SlackMessageDeletedListener listener);

    void addMessagePostedListener(SlackMessagePostedListener listener);

    void removeMessagePostedListener(SlackMessagePostedListener listener);

    void addMessageUpdatedListener(SlackMessageUpdatedListener listener);

    void removeMessageUpdatedListener(SlackMessageUpdatedListener listener);

    void addGroupJoinedListener(SlackGroupJoinedListener listener);

    void removeGroupJoinedListener(SlackGroupJoinedListener listener);

	boolean isConnected();

}
