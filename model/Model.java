package model;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import model.entity.*;

public class Model
{
	public Model()
	{
		for(int i = 0; i < 40; ++i)
			addEntity();
	}
	
	public ArrayList<Entity> getEntities()
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
		for(Entity e : entities_)
			e.makeStep(elapsedSeconds);
	}
	
	private void resolveCollisions(double elapsedSeconds)
	{
		for(int i = 0;   i < entities_.size(); ++i)
		for(int j = i+1; j < entities_.size(); ++j)
		{ 
			Entity first  = entities_.get(i);
			Entity second = entities_.get(j);
			if(first.crosses(second))
			{
				first.makeStep(-elapsedSeconds);
				second.makeStep(-elapsedSeconds);
				CollisionHandler.handle(first, second);
			}
		}
	}
	
	
	
	public static String title = "Black Shooter"; 
	private ArrayList<Entity> entities_ = new ArrayList<Entity>();
}
