package com.mitch528.sockets.Sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.mitch528.sockets.events.ServerSocketAccepted;
import com.mitch528.sockets.events.ServerSocketAcceptedEvent;
import com.mitch528.sockets.events.ServerSocketStarted;
import com.mitch528.sockets.events.ServerSocketStartedEvent;
import com.mitch528.sockets.events.SocketHandlerReadyEvent;
import com.mitch528.sockets.events.SocketHandlerReadyEventListener;

public class Server extends Thread
{
	
	private int port;
	
	private int counter = 0;
	
	private List<SocketHandler> handlers = new ArrayList<SocketHandler>();
	
	private ServerSocket server;
	
	private ServerSocketStarted started;
	private ServerSocketAccepted accepted;
	
	public Server(int port)
	{
		
		this.port = port;
		
		this.started = new ServerSocketStarted();
		this.accepted = new ServerSocketAccepted();
		
	}
	
	private void StartListening()
	{
		
		try
		{
			
			server = new ServerSocket(port);
			
			started.executeEvent(new ServerSocketStartedEvent(this));
			
			while (true)
			{
				
				Socket sock = server.accept();
				
				final SocketHandler handler = new SocketHandler(sock, ++counter);
				
				handler.getReady().addSocketHandlerReadyEventListener(new SocketHandlerReadyEventListener()
				{
					
					@Override
					public void socketHandlerReady(SocketHandlerReadyEvent evt)
					{
						accepted.executeEvent(new ServerSocketAcceptedEvent(this, handler));
					}
					
				});
				
				handler.start();
				
				handlers.add(handler);
				
			}
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void ShutdownAll()
	{
		
		for (SocketHandler handler : handlers)
		{
			handler.Disconnect();
		}
		
		handlers.clear();
		
	}
	
	public void StopServer()
	{
		try
		{
			server.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SocketHandler[] getHandlers()
	{
		return handlers.toArray(new SocketHandler[handlers.size()]);
	}
	
	public SocketHandler getHandler(int index)
	{
		return handlers.get(index);
	}
	
	public ServerSocketStarted getServerSocketStarted()
	{
		return started;
	}
	
	public ServerSocketAccepted getSocketAccepted()
	{
		return accepted;
	}
	
	@Override
	public void run()
	{
		StartListening();
	}
	
}
