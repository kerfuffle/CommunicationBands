package net.kerfuffle.RaspiServer.Packets;

import net.kerfuffle.RaspiServer.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketCurrentConfig extends Packet{

	public PacketCurrentConfig(char currentLetter, int letterSet)
	{
		super(null, Global.CURRENT_CONFIG);
		data = id + "," + currentLetter + "," + letterSet + ",";
	}
	
}
