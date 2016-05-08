package model;

import java.awt.geom.Point2D;

import model.entity.Entity;

final class CollisionHandler
{
	private CollisionHandler()
	{
	}
	
	private static double dot(Point2D.Double first, Point2D.Double second)
	{
		return first.x*second.x + first.y*second.y;
	}
	
	private static Point2D.Double negate(Point2D.Double vector)
	{
		return new Point2D.Double(-vector.x, -vector.y);
	}
	
	private static Point2D.Double multiply(Point2D.Double vector, double scalar)
	{
		return new Point2D.Double(vector.x * scalar, vector.y * scalar);
	}
	
	public static void handle(Entity first, Entity second)
	{
		//https://en.wikipedia.org/wiki/Elastic_collision#Two-dimensional
		Point2D.Double directionVector = 
				new Point2D.Double(first.getPosition().x - second.getPosition().x, 
						           first.getPosition().y - second.getPosition().y);
		Point2D.Double relativeVelocity = 
				new Point2D.Double(first.getVelocity().x - second.getVelocity().x, 
						           first.getVelocity().y - second.getVelocity().y);
	
		double addedMass = first.getSize() + second.getSize();
		double massRatioOne = 2*second.getSize() / addedMass;
		double massRatioTwo = 2*first.getSize() / addedMass;
		
		double distanceSq = first.getPosition().distanceSq(second.getPosition());
		
		double vectorRatioOne = dot(relativeVelocity, directionVector) / distanceSq;
		double vectorRatioTwo = dot(negate(relativeVelocity), negate(directionVector)) / distanceSq;
		
		first.accelerate(negate(multiply(directionVector, massRatioOne*vectorRatioOne)));
		second.accelerate(negate(multiply(negate(directionVector), massRatioTwo*vectorRatioTwo)));
	}
}