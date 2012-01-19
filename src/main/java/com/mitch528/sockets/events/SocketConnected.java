package com.mitch528.sockets.events;

import javax.swing.event.EventListenerList;

public class SocketConnected
{
	protected EventListenerList listenerList = new EventListenerList();
	
	public void addSocketConnectedEventListener(SocketConnectedEventListener listener)
	{
		listenerList.add(SocketConnectedEventListener.class, listener);
	}
	
	public void removeSocketConnectedEventListener(SocketConnectedEventListener listener)
	{
		listenerList.remove(SocketConnectedEventListener.class, listener);
	}
	
	public void executeEvent(SocketConnectedEvent evt)
	{
		
		Object[] listeners = listenerList.getListenerList();
		
		for (int i = 0; i < listeners.length; i += 2)
		{
			if (listeners[i] == SocketConnectedEventListener.class)
			{
				((SocketConnectedEventListener) listeners[i + 1]).socketConnected(evt);
			}
		}
		
	}
}
