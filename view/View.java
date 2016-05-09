package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

//Theme song: https://www.youtube.com/watch?v=h0ffIJ7ZO4U

import model.entity.Entity;
import model.Model;
import utill.Vector2D;

public class View
{
	public View(Model model)
	{
		model_ = model;		
		canvas_.setBounds(0,0,MyFrame.WIDTH,MyFrame.HEIGHT);
		mainFrame_.add(canvas_);
		canvas_.createBufferStrategy(2);
		strategy_ = canvas_.getBufferStrategy();
	}
	
	private void drawEntity(Entity e, Graphics2D g)
	{
		Vector2D position = new Vector2D(e.getBody().getPosition());
		int size = e.getBody().getRadius(); 
	    g.drawOval((int)position.x-size, (int)position.y-size, size*2, size*2);
	    g.setColor(e.getAllegiance().getColor());
	    g.fillOval((int)position.x-size-1, (int)position.y-size-1, size*2+2, size*2+2);
	}
	
	public void draw() 
	{
		Graphics2D g2 = (Graphics2D) strategy_.getDrawGraphics();
		g2.setColor(Color.BLUE);
		g2.fillRect(0,0,MyFrame.WIDTH,MyFrame.HEIGHT);
		for(Entity e : model_.getEntityManager().getEntities())
			drawEntity(e, g2);
	
		g2.setFont(font_);
		g2.setColor(Color.RED);
		g2.drawString("Points: " + Integer.toString(model_.getPointCount()), POINT_POS_X, POINT_POS_Y);
		
		g2.dispose();
		strategy_.show();
	}		
	
	public MyFrame getFrame()
	{
		return mainFrame_;
	}
	
	public Canvas getCanvas()
	{
		return canvas_;
	}
	
	private Model model_;
	private MyFrame mainFrame_ = new MyFrame();
	private Canvas canvas_ = new Canvas();
	private BufferStrategy strategy_;	
	private final Font font_= new Font("Arial", Font.BOLD, FONT_SIZE);
	
	private static final int FONT_SIZE = 20;	
	private static final int POINT_POS_X = 10;
	private static final int POINT_POS_Y = FONT_SIZE + 10;
	
}


