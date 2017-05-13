package net.kerfuffle.RaspiServer.Packets;

import net.kerfuffle.RaspiServer.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketExternalSimulatorLogin extends Packet{

	public PacketExternalSimulatorLogin()
	{
		super(null, Global.EXTERNAL_SIMULATOR_LOGIN);
	}
	
}
