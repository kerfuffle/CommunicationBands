package net.kerfuffle.PatientClient.Packets;

import net.kerfuffle.PatientClient.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketDisconnect extends Packet{
	
	private String message;
	
	public PacketDisconnect (String data, Object obj)	//server disconnects
	{
		super(data, Global.DISCONNECT);
		
		String sp[] = data.split(",");
		message = sp[1];
		
	}
	public PacketDisconnect (String message)
	{
		data = (id + "," + message + ",");
	}
	
	public String getMessage()
	{
		return message;
	}

}
