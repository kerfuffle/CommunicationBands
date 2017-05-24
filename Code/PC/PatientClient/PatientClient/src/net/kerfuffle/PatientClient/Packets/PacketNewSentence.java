package net.kerfuffle.PatientClient.Packets;

import net.kerfuffle.PatientClient.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketNewSentence extends Packet{

	
	public PacketNewSentence(String sentence)
	{
		super(null, Global.NEW_SENTENCE);
		data = id + "," + sentence + ",";
	}
	
}
