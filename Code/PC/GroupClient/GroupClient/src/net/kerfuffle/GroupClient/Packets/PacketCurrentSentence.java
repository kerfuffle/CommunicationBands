package net.kerfuffle.GroupClient.Packets;

import net.kerfuffle.GroupClient.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketCurrentSentence extends Packet{

	private String currentSentence;
	
	public PacketCurrentSentence (String data)
	{
		super(data,Global.CURRENT_SENTENCE);
		
		String sp[] = data.split(",");
		currentSentence = sp[1];
	}
	
	public String getCurrentSentence()
	{
		return currentSentence;
	}
	
}
