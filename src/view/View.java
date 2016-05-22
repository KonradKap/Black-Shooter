package src.view;

import java.awt.Font;

abstract public class View
{
	protected View()
	{
	}
	
	public void render()
	{
		mainFrame_.repaint();
	}
	
	public static MyFrame getFrame()
	{
		return mainFrame_;
	}
	
	static protected final int FONT_SIZE = 20;
	static protected final Font font_= new Font("Arial", Font.BOLD, FONT_SIZE);
	
	static protected MyFrame mainFrame_ = new MyFrame();	
}