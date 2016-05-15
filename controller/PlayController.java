package controller;

import java.util.Timer;
import java.util.TimerTask;

import model.AmmoManager;
import model.PlayModel;
import model.entity.Entity;
import utill.Vector2D;

public class PlayController implements Controller
{
	public PlayController(PlayModel target)
	{
		target_ = target;
	}
	
	public void fire(int x, int y)
	{
		if(target_.getAmmoManager().canFire())
		{
			shootAt(x, y);
			target_.getAmmoManager().decrementCount();
		}
	}
	
	private void shootAt(int x, int y)
	{
		Vector2D target = new Vector2D(x, y);
		for(Entity e : target_.getEntityManager().getEntities())
			if(e.getBody().contains(target))
			{
				target_.getPointCounter().update(e);
				target_.getEntityManager().getEntities().remove(e);
			}
	}
	
	public void reload()
	{
		if(target_.getAmmoManager().getCount() == AmmoManager.MAX_COUNT ||
		   target_.getAmmoManager().isReloading())
			return;
		target_.getAmmoManager().startReloading();
		new Timer().schedule
		( 
			new TimerTask() 
			{
				public void run() 
				{
					target_.getAmmoManager().stopReloading();
					target_.getAmmoManager().setCount(AmmoManager.MAX_COUNT);
		        }
		    }, 
			RELOAD_MILI
		);
	}
	
	private PlayModel target_;
	
	private static final int RELOAD_MILI = 1500;
}
