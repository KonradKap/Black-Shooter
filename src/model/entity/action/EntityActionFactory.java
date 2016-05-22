package src.model.entity.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import src.model.entity.EntityFactory;
import src.utill.Vector2D;

public class EntityActionFactory
{	
	public static EntityActionFactory getInstance()
	{
		return factory_;
	}
	
	public EntityAction generateRandom()
	{
		int a = generator_.nextInt(ActionType.values().length);
		return get(ActionType.valueOf(a));
	}
	
	public EntityAction get(ActionType action)
	{
		switch(action)
		{
		case Null:
			return new NullAction();
		case SpeedUp:
			return new SpeedUpAction(new Vector2D(generator_.nextInt((int)EntityFactory.MAX_VELOCITY), generator_.nextInt((int)EntityFactory.MAX_VELOCITY)));
		case Disappear:
			return new DisappearAction();
		case ChangeAllegiance:
			return new ChangeAllegianceAction();
		default:
			throw new EnumConstantNotPresentException(action.getClass(), action.toString());
		}
	}
	
	private EntityActionFactory()
	{	
	}
	
	public enum ActionType
	{
		Null(0),
		SpeedUp(1),
		Disappear(2),
		ChangeAllegiance(3);
		
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
	
	private Random generator_ = new Random();
	private static EntityActionFactory factory_ = new EntityActionFactory(); 
}