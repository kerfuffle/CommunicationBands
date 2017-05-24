package net.kerfuffle.groupclient.Packets;

import net.kerfuffle.groupclient.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketNewSentence extends Packet{

	private String sentence;
	
	public PacketNewSentence(CharSequence data)
	{
		super(data, Global.NEW_SENTENCE);
		String sp[] = data.toString().split(",");
		sentence = sp[1];
	}
	
	public String getSentence()
	{
		return sentence;
	}
	
}
