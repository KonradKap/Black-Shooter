package src.model;

import java.util.concurrent.CopyOnWriteArrayList;

import src.model.entity.Entity;
import src.utill.Vector2D;

public class EntityCollisionHandler
{
	public void resolveCollisions(CopyOnWriteArrayList<Entity> entities, double elapsedSeconds)
	{
		for(int i = 0;   i < entities.size(); ++i)
		for(int j = i+1; j < entities.size(); ++j)
			resolveCollisions(entities.get(i), entities.get(j), elapsedSeconds);
	}
	
	public void resolveCollisions(Entity first, Entity second, double elapsedSeconds)
	{
		if(first.getBody().crosses(second.getBody()))
		{
			first.makeStep(-elapsedSeconds);
			second.makeStep(-elapsedSeconds);
			calculateVelocities(first, second);
			first.makeStep(elapsedSeconds);
			second.makeStep(elapsedSeconds);
		}
	}
	
	/**
	 * @see https://en.wikipedia.org/wiki/Elastic_collision#Two-dimensional
	 */
	public void calculateVelocities(Entity first, Entity second)
	{
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
}