package net.kerfuffle.RaspiServer;

import java.net.InetAddress;

import net.kerfuffle.Utilities.Network.User;

public class GroupUser extends User{
	
	private int mode = Global.MIMIC;
	
	public GroupUser(InetAddress ip, int port, int mode)
	{
		super(null, ip,port);
		this.mode = mode;
	}
	
	public int getMode()
	{
		return mode;
	}
	
}
