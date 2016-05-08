package model;

import utill.Vector2D;

import model.entity.Entity;

final class CollisionHandler
{
	private CollisionHandler()
	{
	}
	
	public static void handle(Entity first, Entity second)
	{
		//https://en.wikipedia.org/wiki/Elastic_collision#Two-dimensional
		Vector2D directionVector = first.getPosition().subtract(second.getPosition());
		Vector2D relativeVelocity = first.getVelocity().subtract(second.getVelocity()); 
	
		double addedMass = first.getMass() + second.getMass();
		double massRatioOne = 2*second.getMass() / addedMass;
		double massRatioTwo = 2*first.getMass() / addedMass;
		
		double distanceSq = first.getPosition().distanceSq(second.getPosition());
		
		Vector2D nDirectionVector = directionVector.negate();
		Vector2D nRelativeVelocity = relativeVelocity.negate();
		
		double vectorRatioOne = relativeVelocity.dotProduct(directionVector) / distanceSq;
		double vectorRatioTwo = nRelativeVelocity.dotProduct(nDirectionVector) / distanceSq;
		
		first.accelerate(directionVector.multiply(massRatioOne*vectorRatioOne).negate());
		second.accelerate(nDirectionVector.multiply(massRatioTwo*vectorRatioTwo).negate());
	}
}