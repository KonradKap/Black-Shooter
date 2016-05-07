package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferStrategy;

//Theme song: https://www.youtube.com/watch?v=h0ffIJ7ZO4U

import model.entity.Entity;
import model.Model;

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
		Point2D.Double position = e.getPosition();
		int size = e.getSize(); 
	    g.drawOval((int)position.x-size, (int)position.y-size, size*2, size*2);
	    g.setColor(e.getAllegiance().getColor());
	    g.fillOval((int)position.x-size-1, (int)position.y-size-1, size*2+2, size*2+2);
	}
	
	public void draw() 
	{
		Graphics2D g2 = (Graphics2D) strategy_.getDrawGraphics();
		g2.setColor(Color.BLUE);
		g2.fillRect(0,0,800,600);
		for(Entity e : model_.getEntities())
			drawEntity(e, g2);
		g2.dispose();
		strategy_.show();
	}		
	
	private Model model_;
	private MyFrame mainFrame_ = new MyFrame();
	private Canvas canvas_ = new Canvas();
	private BufferStrategy strategy_;
}


