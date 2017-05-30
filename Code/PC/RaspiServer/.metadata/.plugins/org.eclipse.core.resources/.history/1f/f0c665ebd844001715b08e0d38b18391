package net.kerfuffle.Utilities;

public class Timer implements Runnable{

	private Thread t;
	private String name;
	private volatile boolean running = false;
	private long startTime, elapsedTime, max = -1;
	
	public Timer(String name)
	{
		this.name = name;
	}
	
	public void setMax(long max)
	{
		this.max = max;
	}
	
	public void start()
	{
		startTime = System.currentTimeMillis();
		running = true;
		t = new Thread(this, name);
		t.start();
	}
	
	public void run()
	{
		while (running)
		{
			if (max != -1)
			{
				if (elapsedTime < max)
				{
					elapsedTime = System.currentTimeMillis()-startTime;
				}
			}
			else
			{
				elapsedTime = System.currentTimeMillis()-startTime;
			}
		}
	}
	
	public void reset()
	{
		startTime = System.currentTimeMillis();
	}
	
	public long getElapsedTime()
	{
		return elapsedTime;
	}
	
	
	public void close()
	{
		running = false;
	}
	
}
