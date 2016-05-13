package controller;

import java.awt.event.MouseEvent;

import model.PlayModel;

public class PlayController implements Controller
{
	public PlayController(PlayModel target)
	{
		target_ = target;
	}
	
	public void mousePressed(MouseEvent event)
	{
		int buttonCode = event.getButton();
		switch(buttonCode)
		{
		case MouseEvent.BUTTON1:
			target_.fire(event.getX(), event.getY());
			break;
		case MouseEvent.BUTTON3:
			target_.reload();
			break;
		default:
			return;
		}			
	}
	
	public void mouseExited(MouseEvent event)
	{		
	}
	
	public void mouseClicked(MouseEvent event)
	{	
	}
	
	public void mouseReleased(MouseEvent event)
	{	
	}
	
	public void mouseEntered(MouseEvent event)
	{
	}
	
	private PlayModel target_;
}
