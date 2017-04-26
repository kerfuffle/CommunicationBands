package net.kerfuffle.GroupClient;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import net.kerfuffle.GroupClient.Packets.PacketCurrentLetter;
import net.kerfuffle.GroupClient.Packets.PacketCurrentSentence;
import net.kerfuffle.GroupClient.Packets.PacketDisconnect;
import net.kerfuffle.GroupClient.Packets.PacketNewSentence;
import net.kerfuffle.Utilities.Network.Client;
import net.kerfuffle.Utilities.Network.MyCode;
import net.kerfuffle.Utilities.Network.Packet;

public class Main {

	private Client client;
	private Game game;

	public void run() throws NumberFormatException, SocketException, UnknownHostException
	{
		String in = JOptionPane.showInputDialog("Server IP:Port");
		String sp[] = in.split(":");
		client = new Client("Group Client", InetAddress.getByName(sp[0]), Integer.parseInt(sp[1]));

		game = new Game();
		
		client.setMyCode(new MyCode()
		{
			public void run(Packet packet)
			{
				if (packet.getId() == Global.DISCONNECT)
				{
					PacketDisconnect p = new PacketDisconnect(packet.getData(), null);
					System.out.println(p.getMessage());
				}
				if (packet.getId() == Global.NEW_SENTENCE)
				{
					PacketNewSentence p = new PacketNewSentence(packet.getData());
					game.addNewSentence(p.getSentence());
				}
				if (packet.getId() == Global.CURRENT_LETTER)
				{
					PacketCurrentLetter p = new PacketCurrentLetter(packet.getData());
					game.setCurrentLetter(p.getCurrentLetter());
				}
				if (packet.getId() == Global.CURRENT_SENTENCE)
				{
					PacketCurrentSentence p = new PacketCurrentSentence(packet.getData());
					game.setCurrentSentence(p.getCurrentSentence());
				}
			}
		});
	}



	public static void main (String args[]) throws NumberFormatException, SocketException, UnknownHostException
	{
		new Main().run();
	}

}