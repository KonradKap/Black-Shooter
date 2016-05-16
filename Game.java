import view.MenuView;
import view.PlayView;
import view.View;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import controller.PlayController;
import model.MenuModel;
import model.Model;
import model.PlayModel;

public class Game implements ActionListener, Observer
{	
	public void actionPerformed(ActionEvent event)
	{
		if(event.getActionCommand() == "Play")
		{
			try	
			{
				switchState(GameState.Play);
			}
			catch(IOException e)
			{
				e.printStackTrace();
				System.exit(1);
			}
		}
		else if(event.getActionCommand() == "Quit")
		{
			View.getFrame().setVisible(false);
			//TODO: "Do you really want to quit the game
			running_ = false;
		}
	}
	
	public void update(Observable source, Object message) 
	{
		Integer points = (Integer) message;
		if(points != null)
		{
			if(points.intValue() > maxPoints_.intValue())
				maxPoints_ = points;
			
			try	
			{
				switchState(GameState.Menu);
			}
			catch(IOException e)
			{
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
	
	public Game() throws IOException
	{
		switchState(GameState.Menu);
	}
	
	public static void main(String[] args) throws Exception
	{
		Game game = new Game();
		final int FPS = 60;
		
		final int NANO = 1000000000;
		
		long initialTime = System.nanoTime();
		final double timeF = (double)NANO / FPS;
		double deltaF = 0;
		
		game.getModel().start();
	    while (game.running_) 
	    {
	        long currentTime = System.nanoTime();
	        deltaF += (currentTime - initialTime) / timeF;
	        initialTime = currentTime;

	        while (deltaF >= 1) 
	        {
	            game.getModel().update(1.0/FPS);
	            deltaF--;
	            game.getView().draw();
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
	    View.getFrame().dispatchEvent(new WindowEvent(View.getFrame(), WindowEvent.WINDOW_CLOSING));
	}
	
	private void switchState(GameState newState) throws IOException
	{
		switch(newState)
		{
		case Menu:	
			MenuModel newMenuModel = new MenuModel();		
			//MenuController newMenuController = new MenuController(newMenuModel);
			MenuView newMenuView = new MenuView(newMenuModel);
			
			newMenuView.getPlayButton().addActionListener(this);
			newMenuView.getQuitButton().addActionListener(this);
			if(maxPoints_.intValue() > 0)
				newMenuModel.showPoints(maxPoints_);
			
			model_ = newMenuModel;
			view_ = newMenuView;
			break;
		case Play:
			PlayModel newPlayModel = new PlayModel();
			PlayController newPlayController = new PlayController(newPlayModel);
			PlayView newPlayView = new PlayView(newPlayModel, newPlayController);
			
			newPlayModel.addObserver(this);
			
			model_ = newPlayModel;	
			view_ = newPlayView; 
			break;
		}
		
		model_.start();
	}
	
	public enum GameState
	{
		Menu,
		Play
	}
	
	public Model getModel()
	{
		return model_;
	}
	
	public View getView()
	{
		return view_;
	}
	
	
	private Integer maxPoints_ = 0;
	private boolean running_ = true;
	private Model model_; 
	private View view_;
	
}