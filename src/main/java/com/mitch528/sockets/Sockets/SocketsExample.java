package com.mitch528.sockets.Sockets;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.mitch528.sockets.events.MessageReceivedEvent;
import com.mitch528.sockets.events.MessageReceivedEventListener;
import com.mitch528.sockets.events.SocketAcceptedEvent;
import com.mitch528.sockets.events.SocketAcceptedEventListener;
import com.mitch528.sockets.events.SocketConnectedEvent;
import com.mitch528.sockets.events.SocketConnectedEventListener;
import com.mitch528.sockets.events.SocketDisconnectedEvent;
import com.mitch528.sockets.events.SocketDisconnectedEventListener;

public class SocketsExample extends JavaPlugin
{
	
	private Server server = new Server(9876);
	private Client client = new Client("localhost", 9876);
	
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
		
		server.getAccepted().addSocketAcceptedEventListener(new SocketAcceptedEventListener()
		{
			
			public void socketAccepted(SocketAcceptedEvent evt) //this is called when a client has been accepted
			{
				
				final SocketHandler handler = evt.getHandler();
				
				logger.info("SOCKET ACCEPTED! Host: " + handler.getHostName());
				
				handler.getDisconnected().addSocketDisconnectedEventListener(new SocketDisconnectedEventListener()
				{
					
					public void socketDisconnected(SocketDisconnectedEvent sdEvt)
					{
						
						logger.info("Client disconnected from server! ID: " + sdEvt.getID());
						
					}
					
				});
				
				handler.getMessage().addMessageReceivedEventListener(new MessageReceivedEventListener()
				{
					
					public void messageReceived(MessageReceivedEvent mrEvt) //this is called when a message has been received from the client
					{
						
						String message = mrEvt.getMessage();
						
						logger.info("Message received from client: " + message + " (ID: " + mrEvt.getID() + ")");
						logger.info("Replying to client");
						
						handler.SendMessage("Goodbye world!");
						
					}
					
				});
				
			}
			
		});
		
		client.getConnected().addSocketConnectedEventListener(new SocketConnectedEventListener()
		{
			
			public void socketConnected(SocketConnectedEvent evt) //this is called when the client has connected to the server
			{
				
				logger.info("Connected to server!");
				logger.info("Sending message to server");
				
				client.SendMessage("Hello World");
				
				client.getHandler().getDisconnected().addSocketDisconnectedEventListener(new SocketDisconnectedEventListener()
				{
					
					public void socketDisconnected(SocketDisconnectedEvent evt) //this is called when the client has disconnected from the server
					{
						
						logger.info("Disconnected from server");
						
					}
					
				});
				
				client.getHandler().getMessage().addMessageReceivedEventListener(new MessageReceivedEventListener()
				{
					
					public void messageReceived(MessageReceivedEvent evt) //called when a message has been received from the server
					{
						
						String message = evt.getMessage();
						
						logger.info("Message received from server: " + message);
						
					}
					
				});
				
			}
			
		});
		
		new Thread(new Runnable()
		{
			
			public void run()
			{
				// TODO Auto-generated method stub
				
				try
				{
					
					server.start();
					
					Thread.sleep(5000);
					
					client.Connect();
					
					Thread.sleep(10000);
					
					client.Disconnect();
					
				}
				catch (Exception e)
				{
					
				}
				
			}
			
		}).start();
		
	}
}
