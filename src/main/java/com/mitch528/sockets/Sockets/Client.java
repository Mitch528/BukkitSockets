package com.mitch528.sockets.Sockets;

import java.net.Socket;

import com.mitch528.sockets.events.SocketConnected;
import com.mitch528.sockets.events.SocketConnectedEvent;

public class Client
{
	
	private Socket sock;
	
	private SocketHandler handler;
	
	private String host;
	private int port;
	
	private SocketConnected connected;
	
	public Client(String host, int port)
	{
		
		this.host = host;
		this.port = port;
		
		this.connected = new SocketConnected();
		
	}
	
	public void Connect()
	{
		
		try
		{
			
			sock = new Socket(host, port);
			
			while (!sock.isConnected())
			{
				
			}
			
			handler = new SocketHandler(sock, 0);
			
			connected.executeEvent(new SocketConnectedEvent(this, 0));
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	public void SendMessage(String message)
	{
		handler.SendMessage(message);
	}
	
	public void Disconnect()
	{
		handler.Disconnect();
	}
	
	public SocketHandler getHandler()
	{
		return handler;
	}
	
	public Socket getSocket()
	{
		return sock;
	}
	
	public SocketConnected getConnected()
	{
		return connected;
	}
	
}
