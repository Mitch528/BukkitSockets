package com.mitch528.sockets.events;

import java.util.EventObject;

@SuppressWarnings("serial")
public class SocketDisconnectedEvent extends EventObject
{
	
	private int id;
	
	public SocketDisconnectedEvent(Object source, int id)
	{
		
		super(source);
		
		this.id = id;
		
	}
	
	public int getID()
	{
		return id;
	}
	
}
