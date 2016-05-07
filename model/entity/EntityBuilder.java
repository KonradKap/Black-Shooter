package model.entity;

import java.awt.geom.Point2D;

//import model.entity.Entity;

public class EntityBuilder
{	
	public Entity build()
	{
		return new Entity(position_, velocity_, size_, allegiance_);
	}
	
	public EntityBuilder position(Point2D.Double position)
	{
		position_ = position;
		return this;
	}
	
	public EntityBuilder velocity(Point2D.Double velocity)
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
	
	private Point2D.Double position_ = new Point2D.Double(0, 0);
	private Point2D.Double velocity_ = new Point2D.Double(0, 0);
	private int size_ = 0;
	private Entity.Allegiance allegiance_ = Entity.Allegiance.WHITE;
}