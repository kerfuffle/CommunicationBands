package net.kerfuffle.RaspiServer.Packets;

import net.kerfuffle.RaspiServer.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketCurrentLetter extends Packet{

	public PacketCurrentLetter(char c)
	{
		super (null, Global.CURRENT_LETTER);
		data = id + "," + c + ",";
	}
	
}
