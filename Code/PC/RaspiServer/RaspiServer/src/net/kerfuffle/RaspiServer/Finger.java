package net.kerfuffle.RaspiServer;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import net.kerfuffle.Utilities.Timer;

public class Finger {

	private final GpioController gpio;
	private final GpioPinDigitalInput vibe;
	private int fingerType = -1;
	private boolean triggered = false;
	
	private int count = 0;
	private final int TIMEOUT_TICKS = 20000;
	
	public Finger (Pin pin, int fingerType)
	{
//		timer = new Timer("TimerFinger: " + fingerType);
//		timer.setMax(TIMEOUT);
//		timer.start();
		
		this.fingerType=fingerType;
		
		gpio = GpioFactory.getInstance();
		vibe = gpio.provisionDigitalInputPin(pin, PinPullResistance.PULL_DOWN);
		vibe.setShutdownOptions(true);

		vibe.addListener(new GpioPinListenerDigital() {

			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) 
			{
				if (count >= TIMEOUT_TICKS)
				{
					triggered = true;
					count = 0;
				}
				
			}

		});

	}
	
	public void update()
	{
		if (count < TIMEOUT_TICKS)
		{
			count++;
			System.out.println(count);
		}
	}
	
	
	public boolean isTriggered()
	{
		boolean temp = triggered;
		triggered = false;
		return temp;
	}

	public int getFingerType()
	{
		return fingerType;
	}
	
}
