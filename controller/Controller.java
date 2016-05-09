package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Model;

public class Controller implements MouseListener, KeyListener
{
	public Controller(Model target)
	{
		target_ = target;
	}
	
	public void keyTyped(KeyEvent event)
	{
		//System.out.println("TYPED: " + event.getKeyChar());
	}
	
	public void keyReleased(KeyEvent event)
	{
		//System.out.println("RELEASED: " + event.getKeyChar());
	}
	
	public void keyPressed(KeyEvent event)
	{
		//System.out.println("PRESSED: " + event.getKeyChar());
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
		//System.out.println("EXITED");
	}
	
	public void mouseClicked(MouseEvent event)
	{
		//System.out.println("M_CLICKED: " + event.getButton());
	}
	
	public void mouseReleased(MouseEvent event)
	{
		//System.out.println("M_RELEASED: " + event.getButton());
	}
	
	public void mouseEntered(MouseEvent event)
	{
		//System.out.println("ENTERED");
	}
	
	private Model target_;
}
