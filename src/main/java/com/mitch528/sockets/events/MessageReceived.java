package com.mitch528.sockets.events;

import javax.swing.event.EventListenerList;

public class MessageReceived
{
	protected EventListenerList listenerList = new EventListenerList();
	
	public void addMessageReceivedEventListener(MessageReceivedEventListener listener)
	{
		listenerList.add(MessageReceivedEventListener.class, listener);
	}
	
	public void removeMessageReceivedEventListener(MessageReceivedEventListener listener)
	{
		listenerList.remove(MessageReceivedEventListener.class, listener);
	}
	
	public void executeEvent(MessageReceivedEvent evt)
	{
		
		Object[] listeners = listenerList.getListenerList();
		
		for (int i = 0; i < listeners.length; i += 2)
		{
			if (listeners[i] == MessageReceivedEventListener.class)
			{
				((MessageReceivedEventListener) listeners[i + 1]).messageReceived(evt);
			}
		}
		
	}
}
