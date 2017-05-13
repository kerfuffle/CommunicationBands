package net.kerfuffle.fingersimulator;

import net.kerfuffle.fingersimulator.Packets.PacketCommand;
import net.kerfuffle.fingersimulator.Packets.PacketExternalSimulatorLogin;
import net.kerfuffle.utilities.network.Client;
import net.kerfuffle.utilities.network.MyCode;
import net.kerfuffle.utilities.network.Packet;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by 12664 on 5/12/2017.
 */

public class Main {

    private Client client;

    public Main(InetAddress ip, int port)
    {
        try
        {
            client = new Client("Finger Simulator Client", ip, port);
        }
        catch (SocketException e){}


        PacketExternalSimulatorLogin pesl = new PacketExternalSimulatorLogin();
        try
        {
            client.sendPacket(pesl);
        } catch (IOException e){}


        client.setMyCode(new MyCode() {
            @Override
            public void run(Packet packet) throws IOException
            {
                // Do not think we are receiving any packets
            }
        });

        client.start();
    }

   public void sendCommand(int command)
   {
       PacketCommand p = new PacketCommand(command);
       try
       {
           client.sendPacket(p);
       }
       catch (IOException e){}
   }
}
