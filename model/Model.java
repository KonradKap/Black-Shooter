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
		//http://gamedevelopment.tutsplus.com/tutorials/when-worlds-collide-simulating-circle-circle-collisions--gamedev-769
		for(int i = 0;   i < entities_.size(); ++i)
		for(int j = i+1; j < entities_.size(); ++j)
		{ 
			Entity first  = entities_.get(i);
			Entity second = entities_.get(j);
			if(first.crosses(second))
			{
				double addedMass = first.getSize() + second.getSize();
				double subtractedMass = first.getSize() - second.getSize();
				
				Point2D.Double collisionPoint = new Point2D.Double();
				
				collisionPoint.x = ((first.getPosition().x * second.getSize()) + (second.getPosition().x * first.getSize())) / addedMass;			 
				collisionPoint.y = ((first.getPosition().y * second.getSize()) + (second.getPosition().y * first.getSize())) / addedMass;
				
				Point2D.Double Vector1 = new Point2D.Double();
				Point2D.Double Vector2 = new Point2D.Double();

				Vector1.x = (first.getVelocity().x * subtractedMass + (2 * second.getSize() * second.getVelocity().x)) / addedMass;
				Vector1.y = (first.getVelocity().y * subtractedMass + (2 * second.getSize() * second.getVelocity().y)) / addedMass;
				Vector2.x = (second.getVelocity().x * (-subtractedMass) + (2 * first.getSize() * first.getVelocity().x)) / addedMass;
				Vector2.y = (second.getVelocity().y * (-subtractedMass) + (2 * first.getSize() * first.getVelocity().y)) / addedMass;

				first.setVelocity(Vector1);
				second.setVelocity(Vector2);
				
				first.makeStep(elapsedSeconds);
				second.makeStep(elapsedSeconds);
			}
		}
	}
	
	
	
	public static String title = "Black Shooter"; 
	private ArrayList<Entity> entities_ = new ArrayList<Entity>();
}