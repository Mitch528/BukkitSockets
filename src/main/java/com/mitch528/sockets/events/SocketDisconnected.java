package com.mitch528.sockets.events;

import javax.swing.event.EventListenerList;

public class SocketDisconnected
{
	protected EventListenerList listenerList = new EventListenerList();
	
	public void addSocketDisconnectedEventListener(SocketDisconnectedEventListener listener)
	{
		listenerList.add(SocketDisconnectedEventListener.class, listener);
	}
	
	public void removeSocketDisconnectedEventListener(SocketDisconnectedEventListener listener)
	{
		listenerList.remove(SocketDisconnectedEventListener.class, listener);
	}
	
	public void executeEvent(SocketDisconnectedEvent evt)
	{
		
		Object[] listeners = listenerList.getListenerList();
		
		for (int i = 0; i < listeners.length; i += 2)
		{
			if (listeners[i] == SocketDisconnectedEventListener.class)
			{
				((SocketDisconnectedEventListener) listeners[i + 1]).socketDisconnected(evt);
			}
		}
		
	}
}
