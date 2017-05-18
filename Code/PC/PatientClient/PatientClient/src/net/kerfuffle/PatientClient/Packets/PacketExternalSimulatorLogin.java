package net.kerfuffle.PatientClient.Packets;

import net.kerfuffle.PatientClient.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketExternalSimulatorLogin extends Packet{

	public PacketExternalSimulatorLogin()
	{
		super(null, Global.EXTERNAL_SIMULATOR_LOGIN);
		data = id +",";
	}
	
}
