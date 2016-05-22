package src.controller;

import java.util.Timer;
import java.util.TimerTask;

import src.model.PlayModel;
import src.model.entity.Entity;
import src.utill.Vector2D;

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
	
	private Entity findShot(int x, int y)
	{
		Vector2D target = new Vector2D(x, y);
		for(Entity e : target_.getEntityManager().getEntities())
			if(e.getBody().contains(target))
				return e;
		return new Entity();
	}
	
	private void shootAt(int x, int y)
	{	
		Entity e = findShot(x, y);
		target_.getPointCounter().update(e);
		target_.getEntityManager().getEntities().remove(e);
	}
	
	public void reload()
	{
		if(target_.getAmmoManager().hasMaxCount() ||
		   target_.getAmmoManager().isReloading())
			return;
		target_.getAmmoManager().startReloading();
		new Timer().schedule(new ReloadTask(), RELOAD_MILI);
	}
	
	private class ReloadTask extends TimerTask
	{
		public void run() 
		{
			target_.getAmmoManager().stopReloading();
			target_.getAmmoManager().resetCount();
        }
	}
	
	private PlayModel target_;
	
	private static final int RELOAD_MILI = 1500;
}
