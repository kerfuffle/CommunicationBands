package net.kerfuffle.RaspiServer;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Scanner;

import net.kerfuffle.RaspiServer.Packets.PacketCommand;
import net.kerfuffle.Utilities.Network.Server;

import static net.kerfuffle.RaspiServer.Global.*;

public class FingerSimulator implements Runnable{

	private Thread t;
	private boolean running = false;
	private InetAddress patientIp;
	private int patientPort;
	private Server server;
	
	private ArrayList<GroupUser> groupUsers;
	
	public FingerSimulator (Server server, InetAddress patientIp, int patientPort, ArrayList<GroupUser>groupUsers)
	{
		this.patientIp=patientIp;
		this.patientPort=patientPort;
		this.server = server;
		this.groupUsers = groupUsers;
	}
	
	public void start()
	{
		running = true;
		t = new Thread(this, "Finger Simulator");
		t.start();
	}
	
	public void run()
	{
		Scanner scan = new Scanner(System.in);
		while (running)
		{
			String in = scan.nextLine();
			PacketCommand p = null;
			
			if (in.equalsIgnoreCase("left"))
			{
				 p = new PacketCommand(LEFT);
			}
			if (in.equalsIgnoreCase("right"))
			{
				 p = new PacketCommand(RIGHT);
			}
			if (in.equalsIgnoreCase("space"))
			{
				 p = new PacketCommand(SPACE);
			}
			if (in.equalsIgnoreCase("letter"))
			{
				 p = new PacketCommand(LETTER_ENTER);
			}
			if (in.equalsIgnoreCase("sentence"))
			{
				 p = new PacketCommand(SENTENCE_ENTER);
			}
			if (in.equalsIgnoreCase("back"))
			{
				 p = new PacketCommand(BACKSPACE);
			}
			if (in.equalsIgnoreCase("caps"))
			{
				p = new PacketCommand(CAPSLOCK);
			}
			
			
			if (p != null)
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
		
		
		scan.close();
	}
	
}
