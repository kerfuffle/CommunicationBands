package net.kerfuffle.RaspiServer;

import static com.pi4j.io.gpio.RaspiPin.*;

import com.pi4j.io.gpio.Pin;

import net.kerfuffle.Utilities.Network.Server;
import static net.kerfuffle.RaspiServer.Global.*;

public class FingerListener implements Runnable{

	private Thread t;
	private boolean running = false;
	
	private Server server;
	
	private Finger finger[] = new Finger[10];
	private final Pin pin[] = {GPIO_00, GPIO_01, GPIO_02, GPIO_03, GPIO_04, GPIO_05, GPIO_06, GPIO_07, GPIO_08, GPIO_09};
	private final int fingerType[] = {LEFT_PINKY, LEFT_RING, LEFT_MIDDLE, LEFT_POINTER, LEFT_THUMB, RIGHT_THUMB, RIGHT_POINTER, RIGHT_RING, RIGHT_PINKY};
	
	public FingerListener (Server server)
	{
		this.server = server;
		
		for (int i = 0; i < finger.length; i++)
		{
			finger[i] = new Finger(pin[i]);
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
		// get stuff from original EDD repository to hypothetically read in input from gpio
		for (Finger f : finger)
		{
			if (f.isTriggered())
			{
				
			}
		}
	}
	
}
