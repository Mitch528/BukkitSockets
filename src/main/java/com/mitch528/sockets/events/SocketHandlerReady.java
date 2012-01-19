package com.mitch528.sockets.events;

import javax.swing.event.EventListenerList;

public class SocketHandlerReady
{
	
	protected EventListenerList listenerList = new EventListenerList();
	
	public void addSocketHandlerReadyEventListener(SocketHandlerReadyEventListener listener)
	{
		listenerList.add(SocketHandlerReadyEventListener.class, listener);
	}
	
	public void removeSocketHandlerReadyEventListener(SocketHandlerReadyEventListener listener)
	{
		listenerList.remove(SocketHandlerReadyEventListener.class, listener);
	}
	
	public void executeEvent(SocketHandlerReadyEvent evt)
	{
		
		Object[] listeners = listenerList.getListenerList();
		
		for (int i = 0; i < listeners.length; i += 2)
		{
			if (listeners[i] == SocketHandlerReadyEventListener.class)
			{
				((SocketHandlerReadyEventListener) listeners[i + 1]).socketHandlerReady(evt);
			}
		}
		
	}
	
}
