package com.mitch528.sockets.events;

import java.util.EventObject;

import com.mitch528.sockets.Sockets.SocketHandler;

@SuppressWarnings("serial")
public class ServerSocketAcceptedEvent extends EventObject
{
	
	private SocketHandler handler;
	
	public ServerSocketAcceptedEvent(Object source, SocketHandler handler)
	{
		
		super(source);
		
		this.handler = handler;
		
	}
	
	public SocketHandler getHandler()
	{
		return handler;
	}
	
}
