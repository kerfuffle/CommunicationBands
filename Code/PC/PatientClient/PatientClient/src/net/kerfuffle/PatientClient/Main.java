package net.kerfuffle.PatientClient;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import net.kerfuffle.Utilities.Network.Client;
import net.kerfuffle.Utilities.Network.MyCode;
import net.kerfuffle.Utilities.Network.Packet;

public class Main {

	private Client client;
	private Game game;


	public void run() throws NumberFormatException, SocketException, UnknownHostException
	{
		String in = JOptionPane.showInputDialog("IP:Port");
		String sp[] = in.split(":");
		client = new Client("Patient Client", InetAddress.getByName(sp[0]), Integer.parseInt(sp[1]));

		Global.Type = Global.CLIENT;
		
		client.setMyCode(new MyCode()
		{
			public void run(Packet packet)
			{
				if (packet.getId() == Global.LEFT)
				{
					
				}
				if (packet.getId() == Global.RIGHT)
				{
					
				}
				if (packet.getId() == Global.SENTENCE_ENTER)
				{
					
				}
				if (packet.getId() == Global.LETTER_ENTER)
				{
					
				}
			}
		});

		client.start();

		game = new Game(client);


	}









	public static void main (String args[]) throws NumberFormatException, SocketException, UnknownHostException
	{
		new Main().run();
	}

}
