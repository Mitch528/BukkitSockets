package com.mitch528.sockets.events;

import java.util.EventObject;

@SuppressWarnings("serial")
public class ServerSocketStartedEvent extends EventObject
{

	public ServerSocketStartedEvent(Object source)
	{
		super(source);
	}
	
}
