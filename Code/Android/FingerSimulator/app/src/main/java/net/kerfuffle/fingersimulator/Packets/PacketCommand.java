package net.kerfuffle.fingersimulator.Packets;

import net.kerfuffle.fingersimulator.Global;
import net.kerfuffle.utilities.network.Packet;

/**
 * Created by 12664 on 5/12/2017.
 */

public class PacketCommand extends Packet {

    public PacketCommand(int command)
    {
        super(null, Global.COMMAND);
        data = id +","+ command + ",";
    }

}
