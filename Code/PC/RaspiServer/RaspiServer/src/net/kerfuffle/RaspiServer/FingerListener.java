package net.kerfuffle.RaspiServer;

import net.kerfuffle.Utilities.Network.Server;

public class FingerListener implements Runnable{

	private Thread t;
	private boolean running = false;
	
	private Server server;
	
	public FingerListener (Server server)
	{
		this.server = server;
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
	}
	
}