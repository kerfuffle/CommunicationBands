package net.kerfuffle.PatientClient;

import net.kerfuffle.Utilities.GUI.Text.Font;

public class WordSet {

	private StringBuilder sentence;
	private Font font;
	private boolean limitReached = false;
	private String lastLine = null;
	private final int linesAllowed = 3;
	
	public WordSet(Font font)
	{
		sentence = new StringBuilder();
		this.font = font;
	}
	
	public void addLetter(char c)
	{	
		if (!limitReached)
		{
			if (sentence.toString().contains("\n"))
			{
				String sp[] = sentence.toString().split("\n");
				if (sp.length > linesAllowed)
				{
					limitReached = true;
					return;
				}
				if (font.getWidth(sp[sp.length-1]) > 1300 && sp.length != linesAllowed)
				{
					sentence.append('\n');
				}
				if (font.getWidth(sp[sp.length-1]) > 1300 && sp.length == linesAllowed)
				{
					return;
				}
			}
			else
			{
				if (font.getWidth(sentence.toString()) > 1300)
				{
					sentence.append('\n');
				}
			}
			
			sentence.append(c);
		}
	}
	public void removeLastLetter()
	{
		if (sentence.length() > 0)
		{
			if (sentence.length() > 1)
			{
				if (sentence.charAt(sentence.length()-2) == '\n')
				{
					sentence.deleteCharAt(sentence.length()-1);
					sentence.deleteCharAt(sentence.length()-1);
				}
				else
				{
					sentence.deleteCharAt(sentence.length()-1);
				}
			}
			else
			{
				sentence.deleteCharAt(sentence.length()-1);
			}
			
			
			if (limitReached)
			{
				limitReached = false;
			}
		}
	}
	public void addSpace()
	{
		if (!limitReached)
		{
			sentence.append(" ");
		}
	}
	
	public void clear()
	{
		sentence.delete(0, sentence.length());
	}

	public void draw()
	{
		font.drawText(sentence.toString(), -1300/2, -50);
		
		if (limitReached)
		{
			// draw limit reached warning sign (tell them to delete some letters or send their message)
			// try to replace this with a scrolling feature in the future
		}
	}
	
	public String toString()
	{
		return sentence.toString();
	}
}
