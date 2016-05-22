package src.view;

import javax.swing.*;

import src.model.Model;
import src.utill.Vector2D;

public class MyFrame extends JFrame
{
	
	MyFrame()
	{
		super(Model.TITLE);
		setSize(WIDTH, HEIGHT);
		setBounds(0, 0, WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}
	
	public static Vector2D windowBegin()
	{
		return new Vector2D(0, 0);
	}
	
	public static Vector2D windowSize()
	{
		return new Vector2D(WIDTH, HEIGHT);
	}
	
	
	static final long serialVersionUID = 1L;
	public static final int WIDTH  = 800;
	public static final int HEIGHT = 600;
}