package src.model.entity;

import java.security.InvalidParameterException;

import src.utill.Vector2D;

public class Circle
{
	public Circle()
	{
		position_ = new Vector2D(0, 0);
		radius_ = 0;
	}
	
	public Circle(Vector2D position, double radius)
	{
		position_ = new Vector2D(position);
		if(radius < 0)
			throw new InvalidParameterException("Radius cannot be negative");
		radius_ = radius;
	}
	
	public Circle(Circle other)
	{
		position_ = new Vector2D(other.getPosition());
		radius_ = other.getRadius();
	}
	
	public Vector2D getPosition()
	{
		return position_;
	}
	
	public void setPosition(Vector2D position)
	{
		position_ = position;
	}
	
	public void push(Vector2D offset)
	{
		position_.x += offset.x;
		position_.y += offset.y;
	}
	
	public double getRadius()
	{
		return radius_;
	}
	
	public void setSize(double radius)
	{
		if(radius < 0)
			throw new InvalidParameterException("Radius cannot be negative");
		radius_ = radius;
	}
	
	public double getMass()
	{
		return radius_*radius_;
	}
	
	public boolean crosses(Circle other)
	{
		if(boundingBoxesCross(other))
			return getPosition().distance(other.getPosition()) < getRadius() + other.getRadius();
		
		return false;
	}
	
	public boolean boundingBoxesCross(Circle other)
	{
		if(getPosition().x + getRadius() <= other.getPosition().x - other.getRadius() ||
		   getPosition().x - getRadius() >= other.getPosition().x + other.getRadius())
				return false;
		
		if(getPosition().y + getRadius() <= other.getPosition().y - other.getRadius() ||
		   getPosition().y - getRadius() >= other.getPosition().y + other.getRadius())
				return false;
		
		return true;
	}
	
	public boolean isVisibleInside(Vector2D position, Vector2D size)
	{
		if(getPosition().x + getRadius() <= position.x || getPosition().x - getRadius() >= position.x+size.x)
			return false;
		if(getPosition().y + getRadius() <= position.y || getPosition().y - getRadius() >= position.y+size.y)
			return false;
		return true;
	}
	
	public boolean contains(Vector2D point)
	{
		return this.crosses(new Circle(point, 0));
	}
	
	public boolean equals(Object other)
	{
	    if (other == null) 
	    	return false;
	    if (other == this) 
	    	return true;
	    if (!(other instanceof Circle))
	    	return false;
	    Circle otherCircle = (Circle)other;
		return position_.equals(otherCircle.getPosition()) &&
			   radius_ == otherCircle.getRadius();
	}
	
	private Vector2D position_;
	private double radius_;
}