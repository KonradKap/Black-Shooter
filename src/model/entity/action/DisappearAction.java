package src.model.entity.action;

import src.model.entity.Entity;
import src.utill.Vector2D;

public class DisappearAction implements EntityAction
{
	public void doAction(Entity target)
	{
		target.getBody().setPosition(new Vector2D(0, 0).subtract(
								     new Vector2D(target.getBody().getRadius(), target.getBody().getRadius())));
		target.getBody().setSize(0);
	}
}