package model;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MenuModel implements Model
{
	public void update(double elapsedSeconds)
	{
		
	}
	
	public void start()
	{
		
	}
	
	public void pause()
	{
		
	}
	
	public MenuModel() throws IOException
	{
		image_ = ImageIO.read(new File("resources/background.png"));
	}
	
	public BufferedImage getBackground()
	{
		return image_;
	}
	
	public void showPoints(Integer count)
	{
		message_ = new String[]{"Your best score is: " + count.toString(),
								"Better luck next time",
								"( ͡° ͜ʖ ͡°)"};
	}
	
	public String[] getMessage()
	{
		return message_;
	}
	
	private final String[] instructions_ = {"Press LMB to shoot",
										    "Press RMB to reload",
										    "Shoot the black to gather points",
										    "Try not to shoot the white",
										    "Good Luck! ( ͡° ͜ʖ ͡°)"};
	
	private String[] message_ = instructions_;
	private final BufferedImage image_;
	
}