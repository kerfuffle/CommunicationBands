package net.kerfuffle.GroupClient;

import java.util.ArrayList;

public class Game implements Runnable{

	private Thread t;
	private boolean running = false;
	
	private char currentLetter;
	private String currentSentence;
	private ArrayList <String> history = new ArrayList<String>();
	
	private final int HISTORY_ONLY = 0, HISTORY_AND_WORD = 1, FULL = 2;
	private int mode = HISTORY_ONLY;
	
	public Game()
	{
		
	}
	
	public void start()
	{
		running = true;
		t = new Thread(this, "GroupClient Game");
		t.start();
	}
	
	public void run()
	{
		while (running)
		{
			
		}
	}
	
	
	public void setCurrentLetter(char c)
	{
		currentLetter = c;
	}
	public void addNewSentence(String str)
	{
		history.add(str);
	}
	public void setCurrentSentence(String str)
	{
		currentSentence = str;
	}
	
}
