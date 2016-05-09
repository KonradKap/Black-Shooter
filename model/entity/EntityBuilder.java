package model.entity;

import utill.Vector2D;

//import model.entity.Entity;

public class EntityBuilder
{	
	public Entity build()
	{
		return new Entity(position_, size_, velocity_, allegiance_);
	}
	
	public EntityBuilder position(Vector2D position)
	{
		position_ = position;
		return this;
	}
	
	public EntityBuilder velocity(Vector2D velocity)
	{
		velocity_ = velocity;
		return this;
	}
	
	public EntityBuilder size(int size)
	{
		size_ = size;
		return this;
	}
	
	public EntityBuilder allegiance(Entity.Allegiance allegiance)
	{
		allegiance_ = allegiance;
		return this;
	}
	
	private Vector2D position_ = new Vector2D(0, 0);
	private Vector2D velocity_ = new Vector2D(0, 0);
	private int size_ = 0;
	private Entity.Allegiance allegiance_ = Entity.Allegiance.WHITE;
}