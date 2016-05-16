package model;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class PlayModel extends Observable implements Model
{
	public void update(double elapsedSeconds)
	{
		getEntityManager().update(elapsedSeconds);
	}
	
	public void start()
	{
		getEntityManager().start();
		new Timer().schedule(timer_, 0, SECOND);	
	}
	
	public void resume()
	{
		getEntityManager().start();
	}
	
	public void pause()
	{
		getEntityManager().stop();
	}
	public PlayModel()
	{
	}
	
	public int getRemainingTime()
	{
		return GAME_TIME - timer_.getTime();
	}
	
	public EntityManager getEntityManager()
	{
		return entityManager_;
	}
	
	public AmmoManager getAmmoManager()
	{
		return ammoManager_;
	}
	
	public PointCounter getPointCounter()
	{
		return pointCounter_;
	}
	
	public int getPointCount()
	{
		return pointCounter_.getCount();
	}
	
	private class GameEndTimer extends TimerTask
	{
	    private int time = 0;
	    public void run()
	    {
	       time++;

	       if(time == GAME_TIME)
	       {
	    	   setChanged();
	    	   notifyObservers(new Integer(getPointCount()));
	       }
	    }
	    public int getTime()
	    {
	    	return time;
	    }
	};
	
	private GameEndTimer timer_ = new GameEndTimer();
	
	private static final int GAME_TIME = 120;
	private static final int SECOND = 1000;
	private AmmoManager ammoManager_ = new AmmoManager();
	private PointCounter pointCounter_ = new PointCounter();
	private EntityManager entityManager_ = new EntityManager();
}
