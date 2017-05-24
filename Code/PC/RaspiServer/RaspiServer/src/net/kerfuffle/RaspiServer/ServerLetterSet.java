package net.kerfuffle.RaspiServer;

import java.util.Arrays;

public class ServerLetterSet {

	public static final int LOWER = 0, UPPER = 1;
	
	public static char[] lower = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	public static char[] upper = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

	public char currentSet[] = new char[26];
	private int set = LOWER;
	private int amountVisible = 9;
	
	public ServerLetterSet()
	{
		currentSet = lower;
	}
	
	public int getSet()
	{
		return set;
	}
	
	public char getCurrentLetter()
	{
		int index = amountVisible/2;
		char current = currentSet[index];
		return current;
	}
	
	public boolean isCaps()
	{
		if (set == LOWER)
		{
			return false;
		}
		if (set == UPPER)
		{
			return true;
		}
		
		return false;
	}
	
	public void shiftLeft()
	{
		char temp[] = Arrays.copyOf(currentSet, currentSet.length);

		for (int i = 0; i < currentSet.length; i++)
		{
			if (i == 0)
			{
				currentSet[i] = temp[currentSet.length-1];
				continue;
			}

			currentSet[i] = temp[i-1];
		}
	}
	public void shiftRight()
	{
		char temp[] = Arrays.copyOf(currentSet, currentSet.length);

		for (int i = 0; i < currentSet.length; i++)
		{
			if (i == currentSet.length-1)
			{
				currentSet[i] = temp[0];
				continue;
			}

			currentSet[i] = temp[i+1];
		}
	}
	
	public void setSet(int set)
	{
		this.set=set;

		if (set == LOWER)
		{
			for (char l : currentSet)
			{
				l = Character.toLowerCase(l);
			}
		}
		if (set == UPPER)
		{
			for (char l : currentSet)
			{
				l = Character.toUpperCase(l);
			}
		}
	}
}
