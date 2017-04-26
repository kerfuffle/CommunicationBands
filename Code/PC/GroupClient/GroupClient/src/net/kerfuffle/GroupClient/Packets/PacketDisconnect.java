package net.kerfuffle.GroupClient.Packets;

import net.kerfuffle.GroupClient.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketDisconnect extends Packet{

	private String message;
	
	public PacketDisconnect(String data, Object obj)
	{
		super (data, Global.DISCONNECT);
		
		String sp[] = data.split(",");
		message = sp[1];
	}
	
	public PacketDisconnect (String message)
	{
		super (null, Global.DISCONNECT);
		data = id + "," + message + ",";
	}
	
	public String getMessage()
	{
		return message;
	}
	
}
