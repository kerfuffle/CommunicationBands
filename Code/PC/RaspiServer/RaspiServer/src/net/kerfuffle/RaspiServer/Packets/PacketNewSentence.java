package net.kerfuffle.RaspiServer.Packets;

import net.kerfuffle.RaspiServer.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketNewSentence extends Packet {

	public PacketNewSentence(CharSequence data)
	{
		super(data, Global.NEW_SENTENCE);
	}
}
