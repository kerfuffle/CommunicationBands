package net.kerfuffle.GroupClient.Packets;

import net.kerfuffle.GroupClient.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketLogin extends Packet{

	public PacketLogin()
	{
		super(null, Global.LOGIN);
		data = id + ",";
	}
	
}
