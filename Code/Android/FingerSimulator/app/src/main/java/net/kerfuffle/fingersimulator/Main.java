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

public class Main implements Runnable{

    private Client client;
    private Thread t;
    private boolean send = false;
    private int command;
    private volatile boolean running = false;

    public void start()
    {
        running = true;
        t = new Thread(this, "MainClient");
        t.start();
    }
    public void run()
    {
        PacketExternalSimulatorLogin pesl = new PacketExternalSimulatorLogin();
        try
        {
            client.sendPacket(pesl);
        } catch (IOException e){e.printStackTrace();}

        while (running)
        {
            if (send)
            {
                PacketCommand p = new PacketCommand(command);
                try
                {
                    client.sendPacket(p);
                }
                catch (IOException e){}
                send = false;
            }
        }
    }

    public Main(InetAddress ip, int port)
    {
        try
        {
            client = new Client("Finger Simulator Client", ip, port);
        }
        catch (SocketException e){}

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
       this.command = command;
       send = true;
   }

   public void close()
   {
       running = false;
   }
}
