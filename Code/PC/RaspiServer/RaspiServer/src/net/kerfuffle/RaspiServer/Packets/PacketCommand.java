package net.kerfuffle.RaspiServer.Packets;

import net.kerfuffle.RaspiServer.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketCommand extends Packet{
	
	public PacketCommand(int type)
	{
		super (null, Global.COMMAND);
		data = (id + "," + type + ",");
	}

}
