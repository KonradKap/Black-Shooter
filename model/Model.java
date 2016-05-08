package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import model.entity.*;

public class Model 
{
	public Model()
	{
		//for(int i = 0; i < 40; ++i)
		//	addEntity();
		//timer_.schedule(new entityMake_(), 0, Model.MAKE_REST_PERIOD);
		executor_.scheduleAtFixedRate(entityMake_, 0, Model.MAKE_REST_PERIOD, TimeUnit.MILLISECONDS);
	}
	
	public CopyOnWriteArrayList<Entity> getEntities()
	{
		return entities_;	
	}
	
	public void update(double elapsedSeconds)
	{
		updatePosition(elapsedSeconds);
		resolveCollisions(elapsedSeconds);
	}
	
	public void addEntity()
	{
		Entity newEntity = EntityFactory.getInstance().generateRandom();
		for(Entity e : entities_)
			if(newEntity.crosses(e))
				return;
		entities_.add(newEntity);
	}
	
	private void updatePosition(double elapsedSeconds)
	{
		ArrayList<Entity> invisible = new ArrayList<Entity>();
		for(Iterator<Entity> iterator = entities_.iterator(); iterator.hasNext();)
		{
			Entity e = iterator.next();
			e.makeStep(elapsedSeconds);
			if(!e.isVisible())
				invisible.add(e);
		}
		entities_.removeAll(invisible);
	}
	
	private void resolveCollisions(double elapsedSeconds)
	{
		for(int i = 0;   i < entities_.size(); ++i)
		for(int j = i+1; j < entities_.size(); ++j)
		{ 

			Entity first  = entities_.get(i);
			Entity second = entities_.get(j);
			if(/*first != null && second != null && */first.crosses(second))
			{
				first.makeStep(-elapsedSeconds);
				second.makeStep(-elapsedSeconds);
				CollisionHandler.handle(first, second);
			}
		}
	}
	
	private Runnable entityMake_ = new Runnable() 
	{
	    public void run() 
	    {
	        addEntity();
	    }
	};
	/* 
	private class entityMake_ extends TimerTask 
	{
	    public void run() 
	    {
	       addEntity();
	    }
	}
	*/
	
	//Timer timer_ = new Timer();
	
	private ScheduledExecutorService executor_ = Executors.newScheduledThreadPool(1);
	
	private static int MAKE_REST_PERIOD = 75;
	public static String title = "Black Shooter"; 
	private CopyOnWriteArrayList<Entity> entities_ = new CopyOnWriteArrayList<Entity>();
}
