package src.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import src.controller.PlayController;
import src.model.entity.Entity;
import src.model.PlayModel;
import src.utill.Vector2D;

public class PlayView extends View
{
	public PlayView(PlayModel model, PlayController controller)
	{
		model_ = model;
		controller_ = controller;
		mainFrame_.getContentPane().removeAll();
		mainFrame_.getContentPane().add(new PlayPanel());
		mainFrame_.addMouseListener(createMouseListener());
	}
	
	class PlayPanel extends JPanel
	{
		public PlayPanel()
		{
			setSize(MyFrame.WIDTH, MyFrame.HEIGHT);
		}
		
		public void paintComponent(Graphics g) {
	        super.paintComponent(g);

	        draw(g);
	    }
		
		public void draw(Graphics g) 
		{
			Graphics2D g2 = (Graphics2D) g;
			
			drawBackground(g2);
			for(Entity e : model_.getEntityManager().getEntities())
				drawEntity(e, g2);
			
			drawInterface(g2);
			
			g2.dispose();
		}
		
		private void drawBackground(Graphics2D g2)
		{
			g2.setColor(Color.BLUE);
			g2.fillRect(0,0,MyFrame.WIDTH,MyFrame.HEIGHT);
		}
		
		private void drawEntity(Entity e, Graphics2D g)
		{
			Vector2D position = new Vector2D(e.getBody().getPosition());
			double size = e.getBody().getRadius(); 
		    g.drawOval((int)(position.x-size), (int)(position.y-size), (int)(size*2), (int)(size*2));
		    g.setColor(e.getAllegiance().getColor());
		    g.fillOval((int)(position.x-size-1), (int)(position.y-size-1), (int)(size*2+2), (int)(size*2+2));
		}
		
		private void drawInterface(Graphics2D g2)
		{
			g2.setFont(font_);
			g2.setColor(Color.RED);
			
			g2.drawString("Points: " + Integer.toString(model_.getPointCount()), POINT_POS_X, POINT_POS_Y);
			g2.drawString("Remaining Time: " + Integer.toString(model_.getRemainingTime()), TIME_POS_X, TIME_POS_Y);
			
			if(!model_.getAmmoManager().isReloading())
				g2.drawString("Bullets: " + Integer.toString(model_.getAmmoManager().getCount()), AMMO_POS_X, AMMO_POS_Y);
			else
				g2.drawString("Reloading...", AMMO_POS_X, AMMO_POS_Y);
		}

		static final long serialVersionUID = 1L;
	}
	
	private MouseListener createMouseListener()
	{
		return new MouseInputAdapter()
			{
				public void mousePressed(MouseEvent event)
				{
					switch(event.getButton())
					{
					case MouseEvent.BUTTON1:
						controller_.fire(event.getX(), event.getY());
						break;
					case MouseEvent.BUTTON3:
						controller_.reload();
						break;
					default:
						return;
					}			
				}
			};
	}
	
	private PlayModel model_;
	private PlayController controller_;

	private static final int POINT_POS_X = 10;
	private static final int POINT_POS_Y = FONT_SIZE + 10;
	private static final int TIME_POS_X = 540;
	private static final int TIME_POS_Y = FONT_SIZE + 10;
	private static final int AMMO_POS_X = 10;
	private static final int AMMO_POS_Y = FONT_SIZE + 540;
}


