package net.kerfuffle.fingersimulator.Packets;

import net.kerfuffle.fingersimulator.Global;
import net.kerfuffle.utilities.network.Packet;

/**
 * Created by 12664 on 5/12/2017.
 */

public class PacketExternalSimulatorLogin extends Packet {

    public PacketExternalSimulatorLogin()
    {
        super(null, Global.EXTERNAL_SIMULATOR_LOGIN);
    }

}
