package net.kerfuffle.PatientClient.Packets;

import net.kerfuffle.PatientClient.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketLogin extends Packet{
	
	public PacketLogin()
	{
		data = (Global.LOGIN + ",");
	}
}
