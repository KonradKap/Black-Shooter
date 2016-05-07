import view.View;

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
		long time = System.nanoTime();
		while(true)
		{
			model.update((double)(System.nanoTime() - time) / 1000000000.0d);
			time = System.nanoTime();
			view.draw();
		}	
	}
}
