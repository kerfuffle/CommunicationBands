package net.kerfuffle.PatientClient;

import java.awt.FontFormatException;
import java.io.FileInputStream;
import java.io.IOException;

import net.kerfuffle.Utilities.GUI.DavisGUI;
import net.kerfuffle.Utilities.GUI.Quad;
import net.kerfuffle.Utilities.GUI.RGB;
import net.kerfuffle.Utilities.GUI.Triangle;
import net.kerfuffle.Utilities.GUI.Text.Font;

public class Game extends DavisGUI{

	private LetterSet letterSet;
	private Font font, letter_select_font;
	
	private Triangle leftTri;
	private Triangle rightTri;
	private Quad center;

	public Game()
	{
		super("PatientClient", 1300, 700);
	}

	public void childInit()
	{

		center = new Quad(-50, -310, 100,100, new RGB(0,0,0));
		center.setOuterBorder(10, 10, new RGB(1,0,0));
		
		try 
		{
			font = new Font(new FileInputStream("res/Helvetica.ttf"), 72);
			letter_select_font = new Font(new FileInputStream("res/Helvetica.ttf"), 92);
		} 
		catch (FontFormatException | IOException ex) 
		{
			font = new Font();
		}

		letterSet = new LetterSet(font,letter_select_font, 5, -500, 500, -300);
		
		leftTri = new Triangle(-600, -300, new RGB(1,0,1));
		leftTri.setRelativeVertex(0, 0, 0);
		leftTri.setRelativeVertex(1, 0, 100);
		leftTri.setRelativeVertex(2, -50, 50);
		
		rightTri = new Triangle(600, -300, new RGB(1,0,1));
		rightTri.setRelativeVertex(0, 0, 0);
		rightTri.setRelativeVertex(1, 0, 100);
		rightTri.setRelativeVertex(2, 50, 50);
		
		
	}
	
	public void childLoop()
	{
		letterSet.draw();
		leftTri.draw();
		rightTri.draw();
	}




	public void goLeft()
	{
		letterSet.shiftLeft();
		//blink left arrow
	}
	public void goRight()
	{
		letterSet.shiftRight();
		//blink right arrow
	}
	public void sentenceEnter()
	{

	}
	public void letterEnter()
	{

	}
	public void capslock()
	{
		letterSet.setSet(LetterSet.UPPER);
	}
}
