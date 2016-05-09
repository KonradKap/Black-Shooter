package model.entity;

import java.security.InvalidParameterException;

import utill.Vector2D;
import view.MyFrame;

public class Circle
{
	public Circle()
	{
		position_ = new Vector2D(0, 0);
		radius_ = 0;
	}
	
	public Circle(Vector2D position, int radius)
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
	
	public int getRadius()
	{
		return radius_;
	}
	
	public void setSize(int radius)
	{
		radius_ = radius;
	}
	
	public double getMass()
	{
		return radius_*radius_;
	}
	
	public boolean crosses(Circle other)
	{
		if(getPosition().x + getRadius() < other.getPosition().x - other.getRadius() ||
		   getPosition().x - getRadius() > other.getPosition().x + other.getRadius())
				return false;
		
		if(getPosition().y + getRadius() < other.getPosition().y - other.getRadius() ||
		   getPosition().y - getRadius() > other.getPosition().y + other.getRadius())
				return false;
		
		return getPosition().distance(other.getPosition()) < getRadius() + other.getRadius();
	}
	
	public boolean isVisible()
	{
		if(getPosition().x + getRadius() < 0 || getPosition().x - getRadius() > MyFrame.WIDTH)
			return false;
		if(getPosition().y + getRadius() < 0 || getPosition().y - getRadius() > MyFrame.HEIGHT)
			return false;
		return true;
	}
	
	public boolean contains(Vector2D point)
	{
		if(point.x < getPosition().x - getRadius() || point.x > getPosition().x + getRadius())
			return false;
		if(point.y < getPosition().y - getRadius() || point.y > getPosition().y + getRadius())
			return false;
		
		return getPosition().distance(point) < getRadius();
	}
	
	private Vector2D position_;
	private int radius_;
}