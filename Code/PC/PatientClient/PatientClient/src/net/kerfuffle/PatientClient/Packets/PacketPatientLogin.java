package net.kerfuffle.PatientClient.Packets;

import java.net.InetAddress;

import net.kerfuffle.PatientClient.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketPatientLogin extends Packet{

	public PacketPatientLogin(String data, InetAddress ip, int port)
	{
		super (data, Global.PATIENT_LOGIN, ip, port);
	}
	
	public PacketPatientLogin()
	{
		super (null, Global.PATIENT_LOGIN);
		data = id + ",";
	}
	
}
