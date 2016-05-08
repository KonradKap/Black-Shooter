import view.View;

import java.awt.Toolkit;

//import java.awt.geom.Point2D;

import controller.Controller;
import model.Model;

public class Main
{
	public static void main(String[] args)
	{
		Controller controller = new Controller();
		Model model = new Model();
		View view = new View(model);
		
		final int UPS = 600;
		final int FPS = 60;
		boolean running = true;
		final int NANO = 1000000000;
		
		long initialTime = System.nanoTime();
		final double timeU = (double)NANO / UPS;
		final double timeF = (double)NANO / FPS;
		double deltaU = 0;
		double deltaF = 0;

	    while (running) 
	    {
	        long currentTime = System.nanoTime();
	        deltaU += (currentTime - initialTime) / timeU;
	        deltaF += (currentTime - initialTime) / timeF;
	        initialTime = currentTime;

	        while (deltaU >= 1) 
	        {
	            //getInput();
	            model.update(1.0/UPS);
	            deltaU--;
	        }

	        while (deltaF >= 1) 
	        {
	            view.draw();
	            Toolkit.getDefaultToolkit().sync();
	            deltaF--;
	        }
     
	        try
	        {
	            long gameTime = (initialTime - System.nanoTime() + (long)timeF) / 1000000;
	            Thread.sleep(gameTime);
	        }
	        catch(Exception e)
	        {
	        }
	    }
	}
}
