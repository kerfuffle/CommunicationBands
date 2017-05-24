package net.kerfuffle.groupclient;

import net.kerfuffle.Utilities.Network.Client;
import net.kerfuffle.Utilities.Network.MyNetworkCode;
import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.groupclient.Packets.PacketLogin;
import net.kerfuffle.groupclient.Packets.PacketNewSentence;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by 12664 on 5/23/2017.
 */

public class Main implements Runnable{

    private Thread t;
    private volatile boolean running = false;
    private Client client;

    private InetAddress ip;
    private int port;

    private boolean hasNewSentence = false;
    private String newSentence;

    private boolean caps = false;
    public Main(InetAddress ip, int port)
    {
        this.ip=ip;
        this.port=port;

        try
        {
            client = new Client("Finger Simulator Client", ip, port);
        }
        catch (SocketException e){}

        client.setMyNetworkCode(new MyNetworkCode() {

            public void run(Packet packet) throws IOException
            {
                if (packet.getId() == Global.NEW_SENTENCE)
                {
                    PacketNewSentence p = new PacketNewSentence(packet.getData());
                    newSentence = p.getSentence();
                    hasNewSentence = true;
                }
                if (packet.getId() == Global.DISCONNECT)
                {
                    //meh
                }
            }
        });

        client.start();
    }

    public boolean hasNewSentence()
    {
        boolean temp = hasNewSentence;
        hasNewSentence = false;
        return temp;
    }
    public String getNewSentence()
    {
        return newSentence;
    }

    public void start()
    {
        running = true;
        t = new Thread(this, "Main GroupClient");
        t.start();
    }

    public void run()
    {
        PacketLogin pl = new PacketLogin(Global.HISTORY);
        try
        {
            client.sendPacket(pl);
        }
        catch (IOException e){e.printStackTrace();}
    }
}
