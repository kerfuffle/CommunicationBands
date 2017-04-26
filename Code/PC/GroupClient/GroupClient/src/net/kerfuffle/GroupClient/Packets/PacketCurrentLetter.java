package net.kerfuffle.GroupClient.Packets;

import net.kerfuffle.GroupClient.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketCurrentLetter extends Packet{

	private char currentLetter;
	
	public PacketCurrentLetter(String data)
	{
		super (data, Global.CURRENT_LETTER);
		
		String sp[] = data.split(",");
		currentLetter = sp[1].charAt(0);
	}
	
	public char getCurrentLetter()
	{
		return currentLetter;
	}
	
}
