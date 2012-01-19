package com.mitch528.sockets.events;

import javax.swing.event.EventListenerList;

public class ServerSocketStarted
{
	protected EventListenerList listenerList = new EventListenerList();
	
	public void addServerSocketStartedEventListener(ServerSocketStartedEventListener listener)
	{
		listenerList.add(ServerSocketStartedEventListener.class, listener);
	}
	
	public void removeServerSocketStartedEventListener(ServerSocketStartedEventListener listener)
	{
		listenerList.remove(ServerSocketStartedEventListener.class, listener);
	}
	
	public void executeEvent(ServerSocketStartedEvent evt)
	{
		
		Object[] listeners = listenerList.getListenerList();
		
		for (int i = 0; i < listeners.length; i += 2)
		{
			if (listeners[i] == ServerSocketStartedEventListener.class)
			{
				((ServerSocketStartedEventListener) listeners[i + 1]).serverSocketStarted(evt);
			}
		}
		
	}
}
