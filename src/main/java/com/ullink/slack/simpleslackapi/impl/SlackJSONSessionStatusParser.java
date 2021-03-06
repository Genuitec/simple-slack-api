package com.ullink.slack.simpleslackapi.impl;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackPersona;
import com.ullink.slack.simpleslackapi.SlackUser;

class SlackJSONSessionStatusParser
{
    private static final Logger       LOGGER   = LoggerFactory.getLogger(SlackJSONSessionStatusParser.class);

    private Map<String, SlackChannel> channels = new HashMap<>();
    private Map<String, SlackUser>    users    = new HashMap<>();

    private SlackPersona sessionPersona;

    private String                    webSocketURL;

    private String                    toParse;

    private String                    error;

    SlackJSONSessionStatusParser(String toParse)
    {
        this.toParse = toParse;
    }

    Map<String, SlackChannel> getChannels()
    {
        return channels;
    }

    Map<String, SlackUser> getUsers()
    {
        return users;
    }

    public String getWebSocketURL()
    {
        return webSocketURL;
    }

    public String getError()
    {
        return error;
    }
    
    void parse() throws ParseException
    {
        LOGGER.debug("parsing session status : " + toParse);
        JSONParser parser = new JSONParser();
        JSONObject jsonResponse = (JSONObject) parser.parse(toParse);
        Boolean ok = (Boolean)jsonResponse.get("ok");
        if (Boolean.FALSE.equals(ok)) {
            error = (String)jsonResponse.get("error");
            return;
        }
        
        JSONObject selfJson = (JSONObject) jsonResponse.get("self");
        JSONObject teamJson = (JSONObject) jsonResponse.get("team");
        sessionPersona = SlackJSONParsingUtils.buildSlackUser(selfJson, teamJson);

        JSONArray usersJson = (JSONArray) jsonResponse.get("users");
        for (Object jsonObject : usersJson)
        {
            JSONObject jsonUser = (JSONObject) jsonObject;
            SlackUser slackUser = SlackJSONParsingUtils.buildSlackUser(jsonUser, teamJson);
            LOGGER.debug("slack user found : " + slackUser.getId());
            users.put(slackUser.getId(), slackUser);
        }

        JSONArray imsJson = (JSONArray) jsonResponse.get("ims");
        if (imsJson != null) {
	        for (Object jsonObject : usersJson)
	        {
	            JSONObject jsonIm = (JSONObject) jsonObject;
	            String name = (String) jsonIm.get("name");
	            String channelID = (String) jsonIm.get("id");
	            for (SlackUser next : users.values()) {
	            	if (next.getUserName().equals(name)) {
		            	((SlackUserImpl)next).setImChannelID(channelID);
			            LOGGER.debug("slack im for user found : " + next);
			            break;
		            }
	            }
	        }
        }

        JSONArray botsJson = (JSONArray) jsonResponse.get("bots");
        if (botsJson != null) {
            for (Object jsonObject : botsJson)
            {
                JSONObject jsonBot = (JSONObject) jsonObject;
                SlackUser slackUser = SlackJSONParsingUtils.buildSlackUser(jsonBot, teamJson);
                LOGGER.debug("slack bot found : " + slackUser.getId());
                users.put(slackUser.getId(), slackUser);
            }
        }

        JSONArray channelsJson = (JSONArray) jsonResponse.get("channels");
        for (Object jsonObject : channelsJson)
        {
            JSONObject jsonChannel = (JSONObject) jsonObject;
            JSONArray membersJson = (JSONArray) jsonChannel.get("members");
            boolean isPersonaInGroup = false;
            if (membersJson != null)
            {
                for (Object jsonMembersObject : membersJson)
                {
                    String memberId = (String) jsonMembersObject;
					if (sessionPersona.getId().equals(memberId))
						isPersonaInGroup = true;
                }
            }
            
            if (isPersonaInGroup) {
	            SlackChannelImpl channel = SlackJSONParsingUtils.buildSlackChannel(jsonChannel);
	            LOGGER.debug("slack public channel found : " + channel.getId());
	            channels.put(channel.getId(), channel);
            }
        }

        JSONArray groupsJson = (JSONArray) jsonResponse.get("groups");
        for (Object jsonObject : groupsJson)
        {
            JSONObject jsonChannel = (JSONObject) jsonObject;
            SlackChannelImpl channel = SlackJSONParsingUtils.buildSlackChannel(jsonChannel);
            LOGGER.debug("slack private group found : " + channel.getId());
            channels.put(channel.getId(), channel);
        }

        webSocketURL = (String) jsonResponse.get("url");

    }

    public SlackPersona getSessionPersona() {
        return sessionPersona;
    }
}
