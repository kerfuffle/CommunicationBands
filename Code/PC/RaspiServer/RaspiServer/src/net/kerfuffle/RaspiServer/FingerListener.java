package net.kerfuffle.RaspiServer;

import static com.pi4j.io.gpio.RaspiPin.*;

import com.pi4j.io.gpio.Pin;

import net.kerfuffle.RaspiServer.Packets.PacketCommand;
import net.kerfuffle.Utilities.Network.Server;
import static net.kerfuffle.RaspiServer.Global.*;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

public class FingerListener implements Runnable{

	private Thread t;
	private boolean running = false;

	private Server server;
	private InetAddress patientIp;
	private int patientPort;

	private Finger finger[] = new Finger[10];
	private final Pin pin[] = {GPIO_00, GPIO_01, GPIO_02, GPIO_03, GPIO_04, GPIO_05, GPIO_06, GPIO_07, GPIO_08, GPIO_09};
	private final int fingerType[] = {LEFT_PINKY, LEFT_RING, LEFT_MIDDLE, LEFT_POINTER, LEFT_THUMB, RIGHT_THUMB, RIGHT_POINTER, RIGHT_RING, RIGHT_PINKY};

	public FingerListener (Server server, InetAddress patientIp, int patientPort)
	{
		this.server = server;
		this.patientIp = patientIp;
		this.patientPort = patientPort;

		for (int i = 0; i < finger.length; i++)
		{
			finger[i] = new Finger(pin[i], fingerType[i]);
		}
	}

	public void start()
	{
		running = true;
		t = new Thread(this, "RaspiFingerListener");
		t.start();
	}

	public void run()
	{
		ArrayList<Finger> triggered = new ArrayList<Finger>();
		while (running)
		{
			for (Finger f : finger)
			{
				if (f.isTriggered())
				{
					triggered.add(f);
				}
			}

			PacketCommand p = null;
			if (triggered.size() == 1)
			{
				Finger f = triggered.get(0);

				if (f.getFingerType() == LEFT_POINTER)	
				{
					p = new PacketCommand(LEFT);
				}
				if (f.getFingerType() == RIGHT_POINTER)
				{
					p = new PacketCommand(RIGHT);
				}
				if (f.getFingerType() == LEFT_THUMB)
				{
					p = new PacketCommand(BACKSPACE);
				}
				if (f.getFingerType() == RIGHT_THUMB)
				{
					p = new PacketCommand(LETTER_ENTER);
				}
				if (f.getFingerType() == RIGHT_PINKY)
				{
					p = new PacketCommand(SPACE);
				}
			}
			else
			{
				if (hasSentenceEnterCombo(triggered))
				{
					p = new PacketCommand(SENTENCE_ENTER);
				}
			}


			try 
			{
				server.sendToUser(p, patientIp, patientPort);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}

			triggered.clear();
		}

	}


	private boolean hasFingerType(ArrayList<Finger>fs, int type)
	{
		for (Finger f : fs)
		{
			if (f.getFingerType() == type)
			{
				return true;
			}
		}
		return false;
	}

	private boolean hasSentenceEnterCombo(ArrayList<Finger>fs)
	{
		if (fs.size() == 2)
		{
			if (hasFingerType(fs, LEFT_POINTER) && hasFingerType(fs, RIGHT_POINTER))
			{
				return true;
			}
		}
		return false;
	}

}
