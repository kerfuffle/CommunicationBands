package net.kerfuffle.groupclient.Packets;

import net.kerfuffle.groupclient.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketLogin extends Packet{
	
	public PacketLogin(int id)
	{
		data = (Global.LOGIN + "," + id + ",");
	}
}
