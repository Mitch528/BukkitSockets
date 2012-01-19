package com.mitch528.sockets.events;

import javax.swing.event.EventListenerList;

public class ServerSocketAccepted
{
	protected EventListenerList listenerList = new EventListenerList();
	
	public void addServerSocketAcceptedEventListener(ServerSocketAcceptedEventListener listener)
	{
		listenerList.add(ServerSocketAcceptedEventListener.class, listener);
	}
	
	public void removeServerSocketAcceptedEventListener(ServerSocketAcceptedEventListener listener)
	{
		listenerList.remove(ServerSocketAcceptedEventListener.class, listener);
	}
	
	public void executeEvent(ServerSocketAcceptedEvent evt)
	{
		
		Object[] listeners = listenerList.getListenerList();
		
		for (int i = 0; i < listeners.length; i += 2)
		{
			if (listeners[i] == ServerSocketAcceptedEventListener.class)
			{
				((ServerSocketAcceptedEventListener) listeners[i + 1]).socketAccepted(evt);
			}
		}
		
	}
}
