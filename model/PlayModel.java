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
	
	public void pause()
	{
		getEntityManager().stop();
	}
	public PlayModel()
	{
		getEntityManager().addObserver(pointCounter_);
	}
	
	public void fire(int x, int y)
	{
		if(getAmmoManager().canFire())
		{
			getAmmoManager().fire();
			getEntityManager().fire(x,  y);
		}
	}
	
	public int getRemainingTime()
	{
		return GAME_TIME - timer_.getTime();
	}
	
	public void reload()
	{
		getAmmoManager().reload();
	}
	
	
	public EntityManager getEntityManager()
	{
		return entityManager_;
	}
	
	public int getPointCount()
	{
		return pointCounter_.getCount();
	}
	
	public AmmoManager getAmmoManager()
	{
		return ammoManager_;
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
	private PointCounter pointCounter_ = new PointCounter();
	private AmmoManager ammoManager_ = new AmmoManager();
	private EntityManager entityManager_ = new EntityManager();
}
