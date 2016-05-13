package controller;

import java.awt.event.MouseEvent;

import model.MenuModel;

public class MenuController implements Controller
{
	public MenuController(MenuModel target)
	{
		target_ = target;
	}
	
	public void mousePressed(MouseEvent event)
	{ 
		//if(event.getButton() == MouseEvent.BUTTON1);
			//target_.pressed(event.getX(), event.getY());
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
		//System.out.println("ENTERED");
	}
	
	private MenuModel target_;
}

