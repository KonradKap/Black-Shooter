package model;

import java.util.Observable;
import java.util.Observer;

import model.entity.Entity;

class PointCounter implements Observer
{
	public void update(Observable source, Object message)
	{
		Entity target = (Entity) message;
		if(target.getAllegiance() == Entity.Allegiance.BLACK)
			count_ += Entity.MAX_MASS - (int)target.getBody().getMass();
		else
			count_ -= (int)target.getBody().getMass();
	}
	
	int getCount()
	{
		return count_;
	}
	
	private int count_ = 0;
}