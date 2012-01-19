package com.mitch528.sockets.Sockets;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

import com.mitch528.sockets.events.MessageReceived;
import com.mitch528.sockets.events.MessageReceivedEvent;
import com.mitch528.sockets.events.SocketDisconnected;
import com.mitch528.sockets.events.SocketDisconnectedEvent;

public class SocketHandler implements Runnable
{
	
	private Socket sock;
	
	private int bytesReceived = 0;
	private int messageSize = -1;
	
	private byte[] buffer = new byte[4];
	
	private InputStream in;
	private OutputStream out;
	
	private SocketDisconnected disconnected;
	private MessageReceived message;
	
	private String hostName;
	
	private int id;
	
	public SocketHandler(Socket sock, int id)
	{
		
		this.sock = sock;
		this.id = id;
		
		this.disconnected = new SocketDisconnected();
		this.message = new MessageReceived();
		
		Thread sockThread = new Thread(this);
		
		sockThread.start();
		
	}
	
	private void HandleConnection()
	{
		
		try
		{
			
			this.hostName = sock.getInetAddress().getHostName();
			
			in = sock.getInputStream();
			out = sock.getOutputStream();
			
			startReading();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void SendMessage(String message)
	{
		writeToStream(message);
	}
	
	private void startReading()
	{
		
		if (!sock.isConnected() || sock.isClosed())
			return;
		
		buffer = new byte[buffer.length - bytesReceived];
		
		try
		{
			
			if (bytesReceived == -1) //end of stream
			{
				
				Disconnect();
				
				return;
				
			}
			
			bytesReceived = in.read(buffer);
			
			if (messageSize == -1) //still reading size of data
			{
				
				if (bytesReceived == 4) //received size information
				{
					
					messageSize = ByteBuffer.wrap(buffer).getInt(0);
					
					if (messageSize < 0)
					{
						throw new Exception();
					}
					
					buffer = new byte[messageSize];
					
					bytesReceived = 0;
					
				}
				
				if (messageSize != 0) //need more data
				{
					startReading();
				}
				
			}
			
			else
			{
				
				if (bytesReceived == messageSize) //message body received
				{
					
					StringBuffer sb = new StringBuffer();
					sb.append(new String(buffer));
					
					message.executeEvent(new MessageReceivedEvent(this, id, sb.toString()));
					
					//reset
					bytesReceived = 0;
					messageSize = -1;
					buffer = new byte[4];
					
					startReading(); //start reading again
					
				}
				else
				//need more data
				{
					startReading();
				}
				
			}
		}
		catch (Exception e)
		{
			
		}
	}
	
	private void writeToStream(String message)
	{
		
		if (!sock.isConnected() || sock.isClosed())
			return;
		
		byte[] sizeinfo = new byte[4];
		
		byte[] data = message.getBytes();
		
		ByteBuffer bb = ByteBuffer.allocate(sizeinfo.length);
		bb.putInt(message.getBytes().length);
		
		try
		{
			
			out.write(bb.array());
			out.write(data);
			out.flush();
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	public void Disconnect()
	{
		
		try
		{
			
			sock.shutdownInput();
			sock.shutdownOutput();
			
			sock.close();
			
			disconnected.executeEvent(new SocketDisconnectedEvent(this, id));
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public String getHostName()
	{
		return hostName;
	}
	
	public SocketDisconnected getDisconnected()
	{
		return disconnected;
	}
	
	public MessageReceived getMessage()
	{
		return message;
	}
	
	public Socket getSocket()
	{
		return sock;
	}
	
	public void run()
	{
		HandleConnection();
	}
	
}
