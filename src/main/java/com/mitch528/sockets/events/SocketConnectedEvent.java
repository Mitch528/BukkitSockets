package com.mitch528.sockets.events;

import java.util.EventObject;

@SuppressWarnings("serial")
public class SocketConnectedEvent extends EventObject
{
	
	private int id;
	
	public SocketConnectedEvent(Object arg0, int id)
	{
		
		super(arg0);
		
		this.id = id;
		
	}
	
	public int getID()
	{
		return id;
	}
	
}
