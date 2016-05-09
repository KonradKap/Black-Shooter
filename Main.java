import view.View;

import java.awt.Toolkit;

//import java.awt.geom.Point2D;

import controller.Controller;
import model.Model;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		Model model = new Model();
		model.getEntityManager().startRandomGeneration();
		
		View view = new View(model);
		Controller controller = new Controller(model);
		view.getCanvas().addMouseListener(controller);
		view.getCanvas().addKeyListener(controller);
		
		final int FPS = 60;
		boolean running = true;
		final int NANO = 1000000000;
		
		long initialTime = System.nanoTime();
		final double timeF = (double)NANO / FPS;
		double deltaF = 0;

	    while (running) 
	    {
	        long currentTime = System.nanoTime();
	        deltaF += (currentTime - initialTime) / timeF;
	        initialTime = currentTime;

	        while (deltaF >= 1) 
	        {
	            //controller.getInput();
	            model.getEntityManager().update(1.0/FPS);
	            deltaF--;
	            view.draw();
		        Toolkit.getDefaultToolkit().sync();
	        }
     
	        try
	        {
	            long gameTime = (System.nanoTime() - initialTime + (long)timeF) / 1000000;
	            Thread.sleep(gameTime);
	        }
	        catch(InterruptedException e)
	        {
	        }
	    }
	}
}
