package com.mitch528.sockets.Sockets;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.mitch528.sockets.events.MessageReceivedEvent;
import com.mitch528.sockets.events.MessageReceivedEventListener;
import com.mitch528.sockets.events.ServerSocketAcceptedEvent;
import com.mitch528.sockets.events.ServerSocketAcceptedEventListener;
import com.mitch528.sockets.events.ServerSocketStartedEvent;
import com.mitch528.sockets.events.SocketConnectedEvent;
import com.mitch528.sockets.events.SocketConnectedEventListener;
import com.mitch528.sockets.events.SocketDisconnectedEvent;
import com.mitch528.sockets.events.SocketDisconnectedEventListener;
import com.mitch528.sockets.events.ServerSocketStartedEventListener;

public class SocketsExample extends JavaPlugin
{
	
	private final Server server = new Server(9876);
	private final Client client = new Client("127.0.0.1", 9876);
	
	private Logger logger = Logger.getLogger("Minecraft");
	
	public void onDisable()
	{
		
		client.Disconnect();
		server.ShutdownAll();
		server.StopServer();
		
	}
	
	public void onEnable()
	{
		// TODO Auto-generated method stub
		
		server.getSocketAccepted().addServerSocketAcceptedEventListener(new ServerSocketAcceptedEventListener()
		{
			
			@Override
			public void socketAccepted(ServerSocketAcceptedEvent evt)
			{
				
				logger.info("Server - Client has connected (ID: " + evt.getHandler().getId() + ")");
				
				final SocketHandler handler = evt.getHandler();
				
				handler.getMessage().addMessageReceivedEventListener(new MessageReceivedEventListener()
				{
					
					@Override
					public void messageReceived(MessageReceivedEvent evt)
					{
						
						logger.info("Server - Received message from client - " + evt.getMessage());
						logger.info("Server - Sending reply to client");
						
						handler.SendMessage("Goodbye World!");
						
					}
					
				});
				
				handler.getDisconnected().addSocketDisconnectedEventListener(new SocketDisconnectedEventListener()
				{

					@Override
					public void socketDisconnected(SocketDisconnectedEvent evt)
					{
						
						logger.info("Server - Client " + evt.getID() + " disconnected");
						
					}
					
				});
				
			}
			
		});
		
		server.getServerSocketStarted().addServerSocketStartedEventListener(new ServerSocketStartedEventListener()
		{
			
			@Override
			public void serverSocketStarted(ServerSocketStartedEvent evt)
			{
				client.Connect();
			}
			
		});
		
		client.getHandler().getConnected().addSocketConnectedEventListener(new SocketConnectedEventListener()
		{
			
			@Override
			public void socketConnected(SocketConnectedEvent evt)
			{
				
				logger.info("Client - Connected to server!");
				logger.info("Client - Sending message to server.");
				
				client.SendMessage("Hello World!");
				
			}
			
		});
		
		client.getHandler().getMessage().addMessageReceivedEventListener(new MessageReceivedEventListener()
		{
			
			@Override
			public void messageReceived(MessageReceivedEvent evt)
			{
				
				logger.info("Client - I got the following message: " + evt.getMessage());
			
				client.Disconnect();
				
			}
			
		});
		
		client.getHandler().getDisconnected().addSocketDisconnectedEventListener(new SocketDisconnectedEventListener()
		{
			
			@Override
			public void socketDisconnected(SocketDisconnectedEvent evt)
			{
				
				logger.info("Client - Disconnected");
				
			}
			
		});
		
		server.start();
		
	}
}
