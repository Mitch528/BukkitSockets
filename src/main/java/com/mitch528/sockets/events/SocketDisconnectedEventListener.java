package com.mitch528.sockets.events;

import java.util.EventListener;

public interface SocketDisconnectedEventListener extends EventListener
{
	public void socketDisconnected(SocketDisconnectedEvent evt);
}
