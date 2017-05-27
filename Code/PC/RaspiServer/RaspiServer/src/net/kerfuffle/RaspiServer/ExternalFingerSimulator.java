package net.kerfuffle.RaspiServer;

import static net.kerfuffle.RaspiServer.Global.BACKSPACE;
import static net.kerfuffle.RaspiServer.Global.CAPSLOCK;
import static net.kerfuffle.RaspiServer.Global.LEFT;
import static net.kerfuffle.RaspiServer.Global.LETTER_ENTER;
import static net.kerfuffle.RaspiServer.Global.RIGHT;
import static net.kerfuffle.RaspiServer.Global.SENTENCE_ENTER;
import static net.kerfuffle.RaspiServer.Global.SPACE;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

import net.kerfuffle.RaspiServer.Packets.PacketCommand;
import net.kerfuffle.RaspiServer.Packets.PacketNewSentence;
import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.Utilities.Network.Server;

public class ExternalFingerSimulator {
	
	private Server server;
	private ArrayList<GroupUser> groupUsers;
	private InetAddress patientIp;
	private int patientPort = -1;
	
	private InetAddress ip;
	private int port;
	
	private ServerLetterSet letterSet;
	
	public ExternalFingerSimulator(Server server, InetAddress ip, int port, InetAddress patientIp, int patientPort, ArrayList<GroupUser> groupUsers)
	{
		this.server = server;
		this.groupUsers = groupUsers;
		this.ip = ip;
		this.port = port;
		this.patientIp = patientIp;
		this.patientPort = patientPort;
		
		letterSet = new ServerLetterSet();
	}
	
	public InetAddress getIp()
	{
		return ip;
	}
	public int getPort()
	{
		return port;
	}
	
	public void setPatient(InetAddress ip, int port)
	{
		patientIp = ip;
		patientPort = port;
	}
	
	public int getLetterSet()
	{
		return letterSet.getSet();
	}
	public char currentLetter()
	{
		return letterSet.getCurrentLetter();
	}
	
	private boolean caps = false;
	public void sendNextCommand(int nextCommand)
	{
		Packet p = new PacketCommand(nextCommand);
		
		if (nextCommand == Global.CAPSLOCK)
		{
			caps = !caps;
		
			if (caps)
			{
				letterSet.setSet(ServerLetterSet.UPPER);
			}
			else
			{
				letterSet.setSet(ServerLetterSet.LOWER);
			}
		}
		if (nextCommand == Global.LEFT)
		{
			letterSet.shiftLeft();
		}
		if (nextCommand == Global.RIGHT)
		{
			letterSet.shiftRight();
		}
		
		
		if (p != null && patientIp != null && patientPort != -1)
		{
			try 
			{
				for (GroupUser gu : groupUsers)
				{
					if (gu.getMode() == Global.MIMIC)
					{
						server.sendToUser(p, gu.getIp(), gu.getPort());
					}
				}
				server.sendToUser(p, patientIp, patientPort);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
	}
}
