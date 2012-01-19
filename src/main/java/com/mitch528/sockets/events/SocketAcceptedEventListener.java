package com.mitch528.sockets.events;

import java.util.EventListener;

public interface SocketAcceptedEventListener extends EventListener
{
	public void socketAccepted(SocketAcceptedEvent evt);
}
