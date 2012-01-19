package com.mitch528.sockets.Sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.mitch528.sockets.events.SocketAccepted;
import com.mitch528.sockets.events.SocketAcceptedEvent;

public class Server extends Thread
{
	
	private int port;
	
	private int counter = 0;
	
	private List<SocketHandler> handlers = new ArrayList<SocketHandler>();
	
	private SocketAccepted accepted;
	
	private ServerSocket server;
	
	public Server(int port)
	{
		
		this.accepted = new SocketAccepted();
		
		this.port = port;
		
	}
	
	private void StartListening()
	{
		
		try
		{
			
			server = new ServerSocket(port);
			
			while (true)
			{
				
				Socket sock = server.accept();
				
				SocketHandler handler = new SocketHandler(sock, ++counter);
				
				handlers.add(handler);
				
				accepted.executeEvent(new SocketAcceptedEvent(this, handler));
				
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
	
	public SocketAccepted getAccepted()
	{
		return accepted;
	}
	
	@Override
	public void run()
	{
		StartListening();
	}
	
}
