package net.kerfuffle.RaspiServer.Packets;

import net.kerfuffle.RaspiServer.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketCurrentSentence extends Packet {

	public PacketCurrentSentence(String sentence)
	{
		super(null, Global.CURRENT_SENTENCE);
		data = id + "," + sentence + ",";
	}
	
}
