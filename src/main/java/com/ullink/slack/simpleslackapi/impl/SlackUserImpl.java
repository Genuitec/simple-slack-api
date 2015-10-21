package com.ullink.slack.simpleslackapi.impl;

import com.ullink.slack.simpleslackapi.SlackUser;

class SlackUserImpl extends SlackPersonaImpl implements SlackUser
{
	private String imChannelID;
    String id;

    @Override
    public String toString()
    {
        return "SlackUserImpl{" + "id='" + id + '\'' + ", userName='" + userName + '\'' + ", realName='" + realName + '\'' + ", userMail='" + userMail + '\'' + ", isDeleted=" + deleted + '\'' + ", isAdmin=" + admin + '\'' + ", isOwner="
            + owner + '\'' + ", isPrimaryOwner=" + primaryOwner + '\'' + ", isRestricted=" + restricted + '\'' + ", isUltraRestricted=" + ultraRestricted + ", timeZone=" + timeZone + ", timeZoneLabel=" + timeZoneLabel + ", timeZoneOffset="
            + timeZoneOffset + "}";
    }

    SlackUserImpl(String id, String userName, String realName, String userMail, boolean deleted, boolean admin, boolean owner, boolean primaryOwner, boolean restricted, boolean ultraRestricted, boolean bot, String timeZone,
        String timeZoneLabel, Integer timeZoneOffset, String teamName)
    {
        super(id, userName, realName, userMail, deleted, admin, owner, primaryOwner, restricted, ultraRestricted, bot, timeZone, timeZoneLabel, timeZoneOffset, teamName);
    }
    
    public String getIMChannelID() {
    	return imChannelID;
    }
    
    public void setImChannelID(String imChannelID) {
		this.imChannelID = imChannelID;
	}

	@Override
	public boolean hasIMChannel() {
		return imChannelID != null && !imChannelID.isEmpty();
	}
}
