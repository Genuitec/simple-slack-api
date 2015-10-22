package com.ullink.slack.simpleslackapi;

import java.net.Proxy;

public class SlackProxyData {
	private Proxy.Type type;
	private String host;
	private int port;
	private String username;
	private String password;
	
	public final Proxy.Type getType() {
		return type;
	}
	
	public final void setType(Proxy.Type type) {
		this.type = type;
	}
	
	public final String getHost() {
		return host;
	}
	
	public final void setHost(String host) {
		this.host = host;
	}
	
	public final int getPort() {
		return port;
	}
	
	public final void setPort(int port) {
		this.port = port;
	}
	
	public final String getUsername() {
		return username;
	}
	
	public final void setUsername(String username) {
		this.username = username;
	}
	
	public final String getPassword() {
		return password;
	}

	public final void setPassword(String password) {
		this.password = password;
	}
}
