package model;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import java.util.Random;

import model.entity.Entity;
import model.entity.EntityFactory;
import model.entity.EntityActionFactory;
import utill.Vector2D;
import view.MyFrame;


//TODO: Ragdolls maybe?
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
		resolveCollisions(elapsedSeconds);
	}
	
	private void updatePosition(double elapsedSeconds)
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
	
	private void resolveCollisions(double elapsedSeconds)
	{
		for(int i = 0;   i < entities_.size(); ++i)
		for(int j = i+1; j < entities_.size(); ++j)
		{ 

			Entity first  = entities_.get(i);
			Entity second = entities_.get(j);
			if(first.getBody().crosses(second.getBody()))
			{
				first.makeStep(-elapsedSeconds);
				second.makeStep(-elapsedSeconds);
				calculateVelocities(first, second);
				first.makeStep(elapsedSeconds);
				second.makeStep(elapsedSeconds);
			}
		}
	}
	
	private void calculateVelocities(Entity first, Entity second)
	{
		//https://en.wikipedia.org/wiki/Elastic_collision#Two-dimensional
		Vector2D directionVector = first.getBody().getPosition().subtract(second.getBody().getPosition());
		Vector2D relativeVelocity = first.getVelocity().subtract(second.getVelocity()); 
	
		double addedMass = first.getBody().getMass() + second.getBody().getMass();
		double massRatioOne = 2*second.getBody().getMass() / addedMass;
		double massRatioTwo = 2*first.getBody().getMass() / addedMass;
		
		double distanceSq = first.getBody().getPosition().distanceSq(second.getBody().getPosition());
		
		Vector2D nDirectionVector = directionVector.negate();
		Vector2D nRelativeVelocity = relativeVelocity.negate();
		
		double vectorRatioOne = relativeVelocity.dotProduct(directionVector) / distanceSq;
		double vectorRatioTwo = nRelativeVelocity.dotProduct(nDirectionVector) / distanceSq;
		
		first.accelerate(directionVector.multiply(massRatioOne*vectorRatioOne).negate());
		second.accelerate(nDirectionVector.multiply(massRatioTwo*vectorRatioTwo).negate());	
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
	
	private final Invoker entityInvoker_ = new Invoker();
	private final Maker entityMaker_ = new Maker();
	private final ScheduledExecutorService executor_ = Executors.newScheduledThreadPool(2);
	private static final int MAKE_REST_PERIOD = 75;
	private static final int INVOKE_REST_PERIOD = 150;
	private CopyOnWriteArrayList<Entity> entities_ = new CopyOnWriteArrayList<Entity>();
}