package model.entity;


import java.awt.Color;

import utill.Vector2D;

public class Entity
{
	Entity()
	{
		body_ = new Circle();
		velocity_ = new Vector2D(0, 0);
		allegiance_ = Allegiance.WHITE;
	}
	
	Entity(Circle body, Vector2D velocity, Allegiance allegiance)
	{
		body_ = new Circle(body);
		velocity_ = new Vector2D(velocity);
		allegiance_ = allegiance;
	}
	
	Entity(Vector2D position, int size, Vector2D velocity, Allegiance allegiance)
	{
		body_ = new Circle(position, size);		
		velocity_ = new Vector2D(velocity.x, velocity.y);
		allegiance_ = allegiance;
	}
	
	public Circle getBody()
	{
		return body_;
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

	public Allegiance getAllegiance()
	{
		return allegiance_;
	}
	
	public void setAllegiance(Allegiance allegiance)
	{
		allegiance_ = allegiance;
	}
	
	public void makeStep(double stepLength)
	{
		Vector2D distanceTravelled = new Vector2D(getVelocity());
		distanceTravelled.x *= stepLength;
		distanceTravelled.y *= stepLength;	
		body_.push(distanceTravelled);
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
	public static final int MAX_MASS = MAX_SIZE*MAX_SIZE;
	
	//TODO: make use of strategy pattern to implement different movement
	private Vector2D velocity_;
	private Circle body_;
	private Allegiance allegiance_;
}
