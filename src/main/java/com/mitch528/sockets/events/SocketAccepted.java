package com.mitch528.sockets.events;

import javax.swing.event.EventListenerList;

public class SocketAccepted
{
	protected EventListenerList listenerList = new EventListenerList();
	
	public void addSocketAcceptedEventListener(SocketAcceptedEventListener listener)
	{
		listenerList.add(SocketAcceptedEventListener.class, listener);
	}
	
	public void removeSocketAcceptedEventListener(SocketAcceptedEventListener listener)
	{
		listenerList.remove(SocketAcceptedEventListener.class, listener);
	}
	
	public void executeEvent(SocketAcceptedEvent evt)
	{
		
		Object[] listeners = listenerList.getListenerList();
		
		for (int i = 0; i < listeners.length; i += 2)
		{
			if (listeners[i] == SocketAcceptedEventListener.class)
			{
				((SocketAcceptedEventListener) listeners[i + 1]).socketAccepted(evt);
			}
		}
		
	}
}
