package net.kerfuffle.RaspiServer;

import java.net.SocketException;

import javax.swing.JOptionPane;

import net.kerfuffle.Utilities.Network.MyCode;
import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.Utilities.Network.Server;

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
					
				}
				if (packet.getId() == Global.DISCONNECT)
				{

				}
			}
		});
	}



	public static void main (String args[]) throws SocketException
	{
		new Main().run();
	}

}
