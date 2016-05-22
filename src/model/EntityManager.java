package src.model;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import java.util.Random;

import src.model.entity.Entity;
import src.model.entity.EntityFactory;
import src.model.entity.action.EntityActionFactory;
import src.view.MyFrame;

public class EntityManager
{
	public EntityManager()
	{
		executor_.scheduleAtFixedRate(entityMaker_, 0, MAKE_REST_PERIOD, TimeUnit.MILLISECONDS);
		executor_.scheduleAtFixedRate(entityInvoker_, 0, INVOKE_REST_PERIOD , TimeUnit.MILLISECONDS);
	}
	
	public void start()
	{
		entityMaker_.resume();
		entityInvoker_.resume();
	}
	
	public void stop()
	{
		entityMaker_.pause();
		entityInvoker_.pause();
	}
	
	public CopyOnWriteArrayList<Entity> getEntities()
	{
		return entities_;
	}
	
	public void addEntity()
	{
		addEntity(EntityFactory.getInstance().generateRandom());
	}
	
	public boolean addEntity(Entity newEntity)
	{
		for(Entity e : entities_)
			if(newEntity.getBody().crosses(e.getBody()))
				return false;
		entities_.add(newEntity);
		return true;
	}
	
	public void update(double elapsedSeconds)
	{
		updatePosition(elapsedSeconds);
		collisionHandler_.resolveCollisions(entities_, elapsedSeconds);
	}
	
	public void updatePosition(double elapsedSeconds)
	{
		ArrayList<Entity> invisible = new ArrayList<Entity>();
		for(Entity e : entities_)
		{
			e.makeStep(elapsedSeconds);
			if(!e.getBody().isVisibleInside(MyFrame.windowBegin(), MyFrame.windowSize()))
				invisible.add(e);
		}
		entities_.removeAll(invisible);
	}
	
	private abstract class PausableRunnable implements Runnable
	{
		protected abstract void doAction();
		
		public void run()
		{
			if(running_)
				doAction();
		}
		
		public void pause()
	    {
	        running_ = false;
	    }

	    public void resume()
	    {
	        running_ = true;
	    }
	    
	    private boolean running_ = false;
	}
	
	private class Maker extends PausableRunnable
	{
	    protected void doAction() 
	    {
	    	addEntity();
	    }	    
	}
	
	private class Invoker extends PausableRunnable
	{
		protected void doAction()
		{
			EntityActionFactory.getInstance().generateRandom().doAction(
					entities_.get(generator_.nextInt(entities_.size())));
		}
		private Random generator_ = new Random();
	}
	
	private static final int MAKE_REST_PERIOD = 75;
	private static final int INVOKE_REST_PERIOD = 150;
	
	private EntityCollisionHandler collisionHandler_ = new EntityCollisionHandler();
	private final Invoker entityInvoker_ = new Invoker();
	private final Maker entityMaker_ = new Maker();
	private final ScheduledExecutorService executor_ = Executors.newScheduledThreadPool(2);

	private CopyOnWriteArrayList<Entity> entities_ = new CopyOnWriteArrayList<Entity>();
}