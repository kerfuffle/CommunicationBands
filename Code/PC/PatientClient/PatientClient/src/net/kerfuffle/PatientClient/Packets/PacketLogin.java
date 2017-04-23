package net.kerfuffle.PatientClient.Packets;

import net.kerfuffle.PatientClient.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketLogin extends Packet{

	private String username;
	
	public PacketLogin(String username)
	{
		this.username=username;
		data = (Global.LOGIN + "," + username + ",");
	}
	
	
	
}
