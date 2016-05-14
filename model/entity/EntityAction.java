package model.entity;

import java.util.HashMap;
import java.util.Map;

import model.entity.Entity;
import utill.Vector2D;

interface EntityAction
{
	abstract public void doAction(Entity target);
	enum ActionType
	{
		Null(0),
		SpeedUp(1),
		Disappear(2);
		
		int value_;
		private static Map<Integer, ActionType> map = new HashMap<Integer, ActionType>();
		
		static {
	        for (ActionType actionEnum : ActionType.values()) {
	            map.put(actionEnum.getValue(), actionEnum);
	        }
	    }
		
		private ActionType(int value)
		{
			value_ = value;
		}
		public int getValue()
		{
			return value_;
		}
		static public ActionType valueOf(int value)
		{
			return map.get(value);
		}
	}
}

class NullAction implements EntityAction
{
	public void doAction(Entity target)
	{
	}
}

class SpeedUpAction implements EntityAction
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

class DisappearAction implements EntityAction
{
	public void doAction(Entity target)
	{
		target.getBody().setPosition(new Vector2D(0, 0).subtract(
								     new Vector2D(target.getBody().getRadius(), target.getBody().getRadius())));
		target.getBody().setSize(0);
	}
}