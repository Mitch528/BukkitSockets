package com.mitch528.sockets.events;

import java.util.EventListener;

public interface ServerSocketAcceptedEventListener extends EventListener
{
	public void socketAccepted(ServerSocketAcceptedEvent evt);
}
