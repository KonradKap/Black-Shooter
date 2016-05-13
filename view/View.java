package view;

import java.awt.Font;

abstract public class View
{
	protected View()
	{
		//mainFrame_.setVisible(true);
		
		//mainFrame_.add(canvas_);
		//canvas_.setBounds(0,0,MyFrame.WIDTH,MyFrame.HEIGHT);		
		//canvas_.createBufferStrategy(2);
		//strategy_ = canvas_.getBufferStrategy();
	}
	
	abstract public void draw();
	
	public static MyFrame getFrame()
	{
		return mainFrame_;
	}
	
	static protected final int FONT_SIZE = 20;
	static protected final Font font_= new Font("Arial", Font.BOLD, FONT_SIZE);
	
	static protected MyFrame mainFrame_ = new MyFrame();
	//static protected Canvas canvas_ = new Canvas();
	//static protected BufferStrategy strategy_;	
}