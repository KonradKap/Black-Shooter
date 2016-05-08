package model.entity;


import java.awt.Color;

import utill.Vector2D;

import view.MyFrame;

public class Entity
{
	Entity()
	{
		position_ = new Vector2D(0, 0);
		velocity_ = new Vector2D(0, 0);
		size_ = 0;
		allegiance_ = Allegiance.WHITE;
	}
	
	Entity(Vector2D position, Vector2D velocity, int size, Allegiance allegiance)
	{
		position_ = new Vector2D(position.x, position.y);
		velocity_ = new Vector2D(velocity.x, velocity.y);
		size_ = size;
		allegiance_ = allegiance;
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
	
	public Vector2D getVelocity()
	{
		return velocity_;
	}
	
	public void setVelocity(Vector2D velocity)
	{
		velocity_ = velocity;
	}
	
	public void accelerate(Vector2D acceleration)
	{
		velocity_.x += acceleration.x;
		velocity_.y += acceleration.y;
	}
	
	public int getSize()
	{
		return size_;
	}
	
	public void setSize(int size)
	{
		size_ = size;
	}
	
	public double getMass()
	{
		return size_*size_*Math.PI;
	}
	
	public Allegiance getAllegiance()
	{
		return allegiance_;
	}
	
	public void setAllegiance(Allegiance allegiance)
	{
		allegiance_ = allegiance;
	}
	
	public boolean crosses(Entity other)
	{
		if(getPosition().x + getSize() < other.getPosition().x - other.getSize() ||
		   getPosition().x - getSize() > other.getPosition().x + other.getSize())
				return false;
		
		if(getPosition().y + getSize() < other.getPosition().y - other.getSize() ||
		   getPosition().y - getSize() > other.getPosition().y + other.getSize())
				return false;
		
		return getPosition().distance(other.getPosition()) < getSize() + other.getSize();
	}
	
	public void makeStep(double stepLength)
	{
		Vector2D distanceTravelled = new Vector2D(getVelocity());
		distanceTravelled.x *= stepLength;
		distanceTravelled.y *= stepLength;	
		push(distanceTravelled);
	}
	
	public boolean isVisible()
	{
		if(getPosition().x + getSize() < 0 || getPosition().x - getSize() > MyFrame.WIDTH)
			return false;
		if(getPosition().y + getSize() < 0 || getPosition().y - getSize() > MyFrame.HEIGHT)
			return false;
		return true;
	}
	
	public enum Allegiance
	{
		WHITE,
		BLACK;
		
		static final int getCount()
		{
			return 2;
		}
		public Color getColor()
		{
			switch(this)
			{
			case WHITE:
				return Color.WHITE;
			case BLACK:
				return Color.BLACK;
			default:
				throw new EnumConstantNotPresentException(this.getClass(), this.toString());
			}
		}
	}
	
	static final int MAX_SIZE = 50;
	static final int MIN_SIZE = 5;
	static final double MAX_VELOCITY = 100;
	
	//TODO: make use of strategy pattern to implement different movement
	private Vector2D position_;
	private Vector2D velocity_;
	private int size_;
	private Allegiance allegiance_;
}