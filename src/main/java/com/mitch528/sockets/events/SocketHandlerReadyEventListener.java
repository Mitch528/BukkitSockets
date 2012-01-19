package com.mitch528.sockets.events;

import java.util.EventListener;

public interface SocketHandlerReadyEventListener extends EventListener
{
	public void socketHandlerReady(SocketHandlerReadyEvent evt);
}
