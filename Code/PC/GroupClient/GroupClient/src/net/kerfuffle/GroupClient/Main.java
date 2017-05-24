package net.kerfuffle.GroupClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import net.kerfuffle.GroupClient.Packets.PacketCommand;
import net.kerfuffle.GroupClient.Packets.PacketCurrentConfig;
import net.kerfuffle.GroupClient.Packets.PacketDisconnect;
import net.kerfuffle.GroupClient.Packets.PacketLogin;
import net.kerfuffle.GroupClient.Packets.PacketNewSentence;
import net.kerfuffle.Utilities.MyCode;
import net.kerfuffle.Utilities.Network.Client;
import net.kerfuffle.Utilities.Network.MyNetworkCode;
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

		int mode = Integer.parseInt(JOptionPane.showInputDialog(null, "Mode"));

		////////////
		PacketLogin login = new PacketLogin(mode);
		try
		{
			client.sendPacket(login);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		////////////

		game = new Game(mode);

		game.setCloseCode(new MyCode()
		{
			public void run()
			{
				client.close();
			}
		});

		client.setMyNetworkCode(new MyNetworkCode()
		{
			public void run(Packet packet)
			{
				if (packet.getId() == Global.DISCONNECT)
				{
					PacketDisconnect p = new PacketDisconnect(packet.getData());
					System.out.println(p.getMessage());
				}
				if (mode == Global.MIMIC)
				{
					if (packet.getId() == Global.CURRENT_CONFIG)
					{
						PacketCurrentConfig p = new PacketCurrentConfig(packet.getData());
						game.setLetterPos(p.getCurrentLetter());
						game.setLetterSet(p.getLetterSet());
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
				if (mode == Global.HISTORY)
				{
					if (packet.getId() == Global.NEW_SENTENCE)
					{
						PacketNewSentence p = new PacketNewSentence(packet.getData());
						game.addToHistory(p.getSentence());
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
