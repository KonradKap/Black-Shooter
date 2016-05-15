package model;

import model.entity.Entity;

public class PointCounter
{
	public void update(Entity target)
	{
		if(target.getAllegiance() == Entity.Allegiance.BLACK)
			count_ += Entity.MAX_MASS - (int)target.getBody().getMass();
		else
			count_ -= (int)target.getBody().getMass();
	}
	
	public int getCount()
	{
		return count_;
	}
	
	private int count_ = 0;
}