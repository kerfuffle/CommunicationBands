package net.kerfuffle.GroupClient.Packets;

import net.kerfuffle.GroupClient.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketCommand extends Packet{

	private int type = -1;
	
	public PacketCommand(String data)
	{
		super(data, Global.COMMAND);
		
		String sp[] = data.split(",");
		type = Integer.parseInt(sp[1]);
	}
	
	public int getType()
	{
		return type;
	}
	
}
