package com.mitch528.sockets.events;

import java.util.EventListener;

public interface MessageReceivedEventListener extends EventListener
{
	public void messageReceived(MessageReceivedEvent evt);
}
