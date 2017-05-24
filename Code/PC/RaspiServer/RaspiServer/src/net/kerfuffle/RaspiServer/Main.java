package net.kerfuffle.RaspiServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import net.kerfuffle.RaspiServer.Packets.PacketCommand;
import net.kerfuffle.RaspiServer.Packets.PacketCurrentConfig;
import net.kerfuffle.RaspiServer.Packets.PacketDisconnect;
import net.kerfuffle.RaspiServer.Packets.PacketLogin;
import net.kerfuffle.Utilities.Network.MyNetworkCode;
import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.Utilities.Network.Server;
import net.kerfuffle.Utilities.Network.User;

public class Main {

	private FingerSimulator fingerSimulator;
	private ExternalFingerSimulator efs;
	
	private ArrayList<GroupUser> groupUsers = new ArrayList<GroupUser>();
	
	private Server server;
	private FingerListener fingerListener;
	
	private InetAddress patientIp;
	private int patientPort;

	public void run() throws SocketException, UnknownHostException
	{
		int port = Integer.parseInt(JOptionPane.showInputDialog("Port to host on."));
		
		server = new Server("RaspiServer", port);

		server.setMyNetworkCode(new MyNetworkCode()
		{
			public void run (Packet packet) throws IOException
			{
				//System.out.println(packet.getIp());
				
				if (packet.getId() == Global.LOGIN)
				{
					PacketLogin p = new PacketLogin(packet.getData(), packet.getIp(), packet.getPort());
					User u = new User(null, packet.getIp(), packet.getPort());
					GroupUser gu = new GroupUser(packet.getIp(), packet.getPort(), p.getMode());
					groupUsers.add(gu);
					server.addUser(u);
					
					if (efs != null)
					{
						PacketCurrentConfig pcc = new PacketCurrentConfig(efs.currentLetter(), efs.getLetterSet());
						server.sendToUser(pcc, packet.getIp(), packet.getPort());
					}
				}
				if (packet.getId() == Global.PATIENT_LOGIN)
				{
					patientIp = packet.getIp();
					patientPort = packet.getPort();
					if (efs != null)
					{
						efs.setPatient(patientIp, patientPort);
					}
					
					//fingerSimulator = new FingerSimulator(server, patientIp, patientPort, groupUsers);
					//fingerSimulator.start();
				}
				if (packet.getId() == Global.EXTERNAL_SIMULATOR_LOGIN)
				{
					efs = new ExternalFingerSimulator(server, packet.getIp(), packet.getPort(), patientIp, patientPort, groupUsers);
					System.out.println("remote logged in");
				}
				if (packet.getId() == Global.COMMAND)
				{
					if (efs !=null)
					{
						if (efs.getIp().toString().equals(packet.getIp().toString()) && efs.getPort() == packet.getPort())
						{
							PacketCommand p = new PacketCommand(packet.getData());
							efs.sendNextCommand(p.getCommand());
						}
					}
				}
				if (packet.getId() == Global.DISCONNECT)
				{
					PacketDisconnect p = new PacketDisconnect(packet.getData(), packet.getIp(), packet.getPort());
					server.removeUser(packet.getIp(), packet.getPort());
				}
			}
		});
		
		server.start();
		
		//fingerListener = new FingerListener(server, patientIp, patientPort);
		//fingerListener.start();
	}



	public static void main (String args[]) throws SocketException, UnknownHostException
	{
		new Main().run();
	}

}
