package net.kerfuffle.RaspiServer;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import net.kerfuffle.RaspiServer.Packets.PacketDisconnect;
import net.kerfuffle.RaspiServer.Packets.PacketLogin;
import net.kerfuffle.Utilities.Network.MyCode;
import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.Utilities.Network.Server;
import net.kerfuffle.Utilities.Network.User;

public class Main {

	private Server server;
	private FingerListener fingerListener;
	
	private InetAddress patientIp;
	private int patientPort;

	public void run() throws SocketException, UnknownHostException
	{
		int port = Integer.parseInt(JOptionPane.showInputDialog("Port to host on."));
		String patient = JOptionPane.showInputDialog("Patient IP:Port");

		String sp[] = patient.split(":");
		patientIp = InetAddress.getByName(sp[0]);
		patientPort = Integer.parseInt(sp[1]);
		
		server = new Server("RaspiServer", port);

		server.setMyCode(new MyCode()
		{
			public void run (Packet packet)
			{
				if (packet.getId() == Global.LOGIN)
				{
					//PacketLogin p = new PacketLogin(packet.getData(), packet.getIp(), packet.getPort());
					User u = new User(null, packet.getIp(), packet.getPort());
					server.addUser(u);
				}
				if (packet.getId() == Global.DISCONNECT)
				{
					PacketDisconnect p = new PacketDisconnect(packet.getData(), packet.getIp(), packet.getPort());
					server.removeUser(packet.getIp(), packet.getPort());
				}
			}
		});
		
		fingerListener = new FingerListener(server, patientIp, patientPort);
		fingerListener.start();
	}



	public static void main (String args[]) throws SocketException, UnknownHostException
	{
		new Main().run();
	}

}
