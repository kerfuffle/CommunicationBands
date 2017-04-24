package net.kerfuffle.RaspiServer.Packets;

import java.net.InetAddress;

import net.kerfuffle.RaspiServer.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketDisconnect extends Packet{
	
	private String message;
	
	public PacketDisconnect (String data, InetAddress ip, int port)
	{
		super(data, Global.DISCONNECT, ip, port);
		
		String sp[] = data.split(",");
		message = sp[1];
	}
	public PacketDisconnect (String message)
	{
		super(null, Global.DISCONNECT);
		this.message = message;
		data = id + "," + message + ",";
	}
	
	public String getMessage()
	{
		return message;
	}
}
