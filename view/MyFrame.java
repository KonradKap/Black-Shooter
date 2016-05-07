package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

import model.Model;

public class MyFrame extends JFrame
{
	
	MyFrame()
	{
		super(Model.title);
		setSize(WIDTH, HEIGHT);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(Color.BLUE);
	}
	
	static final long serialVersionUID = 1L;
	public static final int WIDTH  = 800;
	public static final int HEIGHT = 600;
}