package com.mitch528.sockets.events;

import java.util.EventListener;

public interface SocketConnectedEventListener extends EventListener
{
	public void socketConnected(SocketConnectedEvent evt);
}
