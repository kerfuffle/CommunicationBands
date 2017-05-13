package net.kerfuffle.RaspiServer.Packets;

import net.kerfuffle.RaspiServer.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketCommand extends Packet{
	
	private int command = -1;
	
	public PacketCommand(int type)
	{
		super (null, Global.COMMAND);
		data = (id + "," + type + ",");
	}

	public PacketCommand (String data)
	{
		super(data, Global.COMMAND);
		String sp[] = data.split(",");
		command = Integer.parseInt(sp[1]);
	}
	
	public int getCommand()
	{
		return command;
	}
}
