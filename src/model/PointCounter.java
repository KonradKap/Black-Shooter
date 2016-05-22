package src.model;

import src.model.entity.Entity;
import src.model.entity.EntityFactory;

public class PointCounter
{
	public void update(Entity target)
	{
		if(target.getAllegiance() == Entity.Allegiance.BLACK)
			count_ += EntityFactory.MAX_MASS - (int)target.getBody().getMass();
		else
			count_ -= (int)target.getBody().getMass();
	}
	
	public int getCount()
	{
		return count_;
	}
	
	private int count_ = 0;
}