package src;

import src.view.MenuView;
import src.view.PlayView;
import src.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import src.controller.PlayController;
import src.model.MenuModel;
import src.model.Model;
import src.model.PlayModel;

public class Game implements ActionListener, Observer
{	
	public Game()
	{
		switchState(GameState.Menu);
	}
	
	public static void main(String[] args)
	{
		Game game = new Game();
		
		timer_.scheduleAtFixedRate(game.new UpdateTask(), 0, (long)frameTimeMili);	
	}
	
	private class UpdateTask extends TimerTask
	{
		public void run()
		{
			long currentTime = System.nanoTime();
			framesOmitted += (currentTime - prevoiusTime) / frameTimeNano;

	        for(; framesOmitted >= 1; --framesOmitted) 
	        {
	            getModel().update(frameTimeSec);
	            getView().render();
	        }
	        
	        prevoiusTime = currentTime;
		}
		
		long prevoiusTime = System.nanoTime();
		double framesOmitted = 0;
	}
	
	public void actionPerformed(ActionEvent event)
	{
		if(event.getActionCommand() == "Play")
		{
			switchState(GameState.Play);
		}
		else if(event.getActionCommand() == "Quit")
		{
			timer_.cancel();
			View.getFrame().setVisible(false);
			View.getFrame().dispatchEvent(new WindowEvent(View.getFrame(), WindowEvent.WINDOW_CLOSING));
		}
	}
	
	public void update(Observable source, Object message) 
	{
		currentPoints_ = (Integer) message;
		if(currentPoints_ != null)
		{
			if(currentPoints_.intValue() > maxPoints_.intValue())
				maxPoints_ = currentPoints_;
			
			switchState(GameState.Menu);
		}
	}
	
	private void switchState(GameState newState)
	{
		switch(newState)
		{
		case Menu:	
			switchToMenu();
			break;
		case Play:
			switchToPlay();
			break;
		}
	}
	
	private void switchToMenu()
	{
		MenuModel newMenuModel = null;
		MenuView newMenuView = null;
		try
		{
			newMenuModel = new MenuModel();		
			newMenuView = new MenuView(newMenuModel);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		newMenuView.getPlayButton().addActionListener(this);
		newMenuView.getQuitButton().addActionListener(this);
		if(maxPoints_.intValue() > 0)
			newMenuModel.showPoints(currentPoints_, maxPoints_);
		
		model_ = newMenuModel;
		view_ = newMenuView;
	}
	
	private void switchToPlay()
	{
		PlayModel newPlayModel = new PlayModel();
		PlayController newPlayController = new PlayController(newPlayModel);
		PlayView newPlayView = new PlayView(newPlayModel, newPlayController);
		
		newPlayModel.addObserver(this);
		newPlayModel.start();
		
		model_ = newPlayModel;	
		view_ = newPlayView; 
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
	
	static final int NANO = 1000000000;
	static final int MILI = 1000;
	
	static final int FPS  = 60;
	static final double frameTimeSec = 1./FPS; 
	static final double frameTimeMili = frameTimeSec * MILI;
	static final double frameTimeNano = frameTimeSec * NANO;

	private static Timer timer_ = new Timer();
	
	private Integer currentPoints_ = 0;
	private Integer maxPoints_ = 0;
	private Model model_; 
	private View view_;
	
}
