package model;

import java.util.Timer;
import java.util.TimerTask;

class AmmoManager
{
	public AmmoManager()
	{
	}
	
	public int getCount()
	{
		return currentCount;
	}
	
	public void fire()
	{
		if(canFire())
			currentCount--;
	}
	
	public boolean canFire()
	{
		return currentCount > 0 && !reloading_;
	}
	
	public void reload()
	{
		if(currentCount == MAX_COUNT || reloading_)
			return;
		reloading_ = true;
		new Timer().schedule
		( 
			new TimerTask() 
			{
				public void run() 
				{
					reloading_ = false;
					currentCount = MAX_COUNT;
		        }
		    }, 
			RELOAD_MILI
		);
	}
	
	
	private int currentCount = MAX_COUNT;
	private boolean reloading_ = false;
	private static final int RELOAD_MILI = 2000;
	private final static int MAX_COUNT = 5;
}