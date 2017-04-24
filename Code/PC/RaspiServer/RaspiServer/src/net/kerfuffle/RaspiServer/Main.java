package net.kerfuffle.RaspiServer;

import java.net.SocketException;

import javax.swing.JOptionPane;

import net.kerfuffle.RaspiServer.Packets.PacketDisconnect;
import net.kerfuffle.RaspiServer.Packets.PacketLogin;
import net.kerfuffle.Utilities.Network.MyCode;
import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.Utilities.Network.Server;
import net.kerfuffle.Utilities.Network.User;

public class Main {

	private Server server;

	public void run() throws SocketException
	{
		int port = Integer.parseInt(JOptionPane.showInputDialog("Port"));

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
	}



	public static void main (String args[]) throws SocketException
	{
		new Main().run();
	}

}
