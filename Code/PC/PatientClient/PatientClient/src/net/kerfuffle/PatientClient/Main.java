package net.kerfuffle.PatientClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import net.kerfuffle.PatientClient.Packets.PacketCommand;
import net.kerfuffle.PatientClient.Packets.PacketDisconnect;
import net.kerfuffle.PatientClient.Packets.PacketPatientLogin;
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
		
		////////////
		PacketPatientLogin login = new PacketPatientLogin();
		try
		{
			client.sendPacket(login);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		////////////
		
		game = new Game();
		
		client.setMyCode(new MyCode()
		{
			public void run(Packet packet)
			{
				if (packet.getId() == Global.DISCONNECT)
				{
					PacketDisconnect p = new PacketDisconnect(packet.getData());
					System.out.println(p.getMessage());
				}
				if (packet.getId() == Global.COMMAND)
				{
					PacketCommand p = new PacketCommand(packet.getData());
					int type = p.getType();
					
					if (type == Global.LEFT)
					{
						game.goLeft();
					}
					if (type == Global.RIGHT)
					{
						game.goRight();
					}
					if (type == Global.SENTENCE_ENTER)
					{
						game.sentenceEnter();
					}
					if (type == Global.LETTER_ENTER)
					{
						game.letterEnter();
					}
					if (type == Global.CAPSLOCK)
					{
						game.capslock();
					}
					if (type == Global.SPACE)
					{
						game.space();
					}
					if (type == Global.BACKSPACE)
					{
						game.backspace();
					}
				}
			}
		});

		client.start();
		game.run();

	}









	public static void main (String args[]) throws NumberFormatException, SocketException, UnknownHostException
	{
		new Main().run();
	}

}
