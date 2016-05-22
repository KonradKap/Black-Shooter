package src.model.entity.action;

import src.model.entity.Entity;

public class ChangeAllegianceAction implements EntityAction
{
	public void doAction(Entity target)
	{
		if(target.getAllegiance() == Entity.Allegiance.BLACK)
			target.setAllegiance(Entity.Allegiance.WHITE);
		else
			target.setAllegiance(Entity.Allegiance.BLACK);
	}
}