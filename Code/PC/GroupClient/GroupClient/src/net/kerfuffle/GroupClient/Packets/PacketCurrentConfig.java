package net.kerfuffle.GroupClient.Packets;

import net.kerfuffle.GroupClient.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketCurrentConfig extends Packet{
	
	private char currentLetter;
	private int letterSet;
	
	public PacketCurrentConfig(CharSequence data)
	{
		super(data, Global.CURRENT_CONFIG);
		String sp[] = data.toString().split(",");
		currentLetter = sp[1].charAt(0);
		letterSet = Integer.parseInt(sp[2]);
	}
	
	public char getCurrentLetter()
	{
		return currentLetter;
	}
	public int getLetterSet()
	{
		return letterSet;
	}
	
}
