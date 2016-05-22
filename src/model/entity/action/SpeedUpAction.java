package src.model.entity.action;

import src.model.entity.Entity;
import src.utill.Vector2D;

public class SpeedUpAction implements EntityAction
{
	public SpeedUpAction(Vector2D acceleration)
	{
		acceleration_ = acceleration;
	}
	
	public void doAction(Entity target)
	{
		target.setVelocity(acceleration_);
	}
	
	private Vector2D acceleration_;
}