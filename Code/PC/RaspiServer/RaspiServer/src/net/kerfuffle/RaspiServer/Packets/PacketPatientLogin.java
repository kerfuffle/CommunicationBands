package net.kerfuffle.RaspiServer.Packets;

import java.net.InetAddress;

import net.kerfuffle.RaspiServer.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketPatientLogin extends Packet{

	public PacketPatientLogin(String data, InetAddress ip, int port)
	{
		super (data, Global.LOGIN, ip, port);
	}
	
}
