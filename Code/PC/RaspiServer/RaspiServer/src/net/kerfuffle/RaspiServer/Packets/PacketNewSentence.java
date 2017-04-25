package net.kerfuffle.RaspiServer.Packets;

import net.kerfuffle.RaspiServer.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketNewSentence extends Packet {

	public PacketNewSentence(String sentence)
	{
		super(null, Global.NEW_SENTENCE);
		data = id + "," + sentence + ",";
	}
	
}
