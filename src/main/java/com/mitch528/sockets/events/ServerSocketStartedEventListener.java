package com.mitch528.sockets.events;

import java.util.EventListener;

public interface ServerSocketStartedEventListener extends EventListener
{
	public void serverSocketStarted(ServerSocketStartedEvent evt);
}
