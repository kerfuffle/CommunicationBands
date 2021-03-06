package net.kerfuffle.PatientClient;

import java.util.Arrays;

import net.kerfuffle.Utilities.GUI.Text.Font;

public class LetterSet {

	public static final int LOWER = 0, UPPER = 1;

	public static char[] lower = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	public static char[] upper = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

	private int set = LOWER;

	public Letter currentSet[] = new Letter[26];

	private Font font;
	private Font letter_select_font;
	private float y, leftBoundX, rightBoundX;
	private int amountVisible;

	public LetterSet(Font font, Font letter_select_font, int amountVisible, float leftBoundX, float rightBoundX, float y)
	{
		this.font = font;
		this.amountVisible = amountVisible;
		this.leftBoundX = leftBoundX; 
		this.rightBoundX = rightBoundX;
		this.y=y;
		this.letter_select_font=letter_select_font;

		initSet();
	}

	public char getCurrentLetter()
	{
		int index = amountVisible/2;
		Letter current = currentSet[index];
		return current.getLetter();
	}

	private void drawCurrentLetter()
	{
		int index = amountVisible/2;
		Letter current = currentSet[index];
		Letter drawing = new Letter(current.getLetter(), letter_select_font, 0-font.getWidth(String.valueOf(current.getLetter()))/2, current.y + 150);
		drawing.draw();
	}

	public void draw()
	{
		for (int i = 0; i < amountVisible; i++)
		{
			currentSet[i].draw();
		}
		drawCurrentLetter();
	}


	public void shiftLeft()
	{
		Letter temp[] = Arrays.copyOf(currentSet, currentSet.length);

		for (int i = 0; i < currentSet.length; i++)
		{
			if (i == 0)
			{
				currentSet[i] = temp[currentSet.length-1];
				currentSet[i].x = leftBoundX+font.getWidth("g") + ((0) * spaceBetween());
				continue;
			}

			currentSet[i] = temp[i-1];
			currentSet[i].x = leftBoundX+font.getWidth("g") + ((i) * spaceBetween());
		}
	}
	public void shiftRight()
	{

		Letter temp[] = Arrays.copyOf(currentSet, currentSet.length);

		for (int i = 0; i < currentSet.length; i++)
		{
			if (i == currentSet.length-1)
			{
				currentSet[i] = temp[0];
				currentSet[i].x = leftBoundX+font.getWidth("g") + ((currentSet.length-1) * spaceBetween());
				continue;
			}

			currentSet[i] = temp[i+1];
			currentSet[i].x = leftBoundX+font.getWidth("g") + ((i) * spaceBetween());
		}
	}

	private float spaceBetween()
	{
		float ret = (Math.abs(leftBoundX) + Math.abs(rightBoundX)) / amountVisible;
		return ret;
	}

	private void initSet()
	{
		currentSet = new Letter[lower.length];
		for (int i = 0; i < lower.length; i++)
		{
			currentSet[i] = new Letter(lower[i], font, (leftBoundX+font.getWidth("g")) + (i * spaceBetween()), y);
		}
	}
	public void setSet(int set)
	{
		this.set=set;

		if (set == LOWER)
		{
			for (Letter l : currentSet)
			{
				l.toLowerCase();
			}
		}
		if (set == UPPER)
		{
			for (Letter l : currentSet)
			{
				l.toUpperCase();
			}
		}
	}

}
