package com.mitch528.sockets.Sockets;

import java.net.Socket;

public class Client
{
	
	private Socket sock;
	
	private SocketHandler handler = new SocketHandler();
	
	private String host;
	private int port;
	
	public Client(String host, int port)
	{
		
		this.host = host;
		this.port = port;
		
	}
	
	public void Connect()
	{
		
		try
		{
			
			sock = new Socket(host, port);
			
			handler.setSocket(sock);
			handler.setID(0);
			
			handler.start();
			
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
	
}
