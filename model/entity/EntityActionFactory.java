package model.entity;

import java.util.Random;

import utill.Vector2D;

class EntityActionFactory
{
	public static EntityActionFactory getInstance()
	{
		return factory_;
	}
	
	public EntityAction generateRandom()
	{
		int a = generator_.nextInt(EntityAction.ActionType.values().length);
		return get(EntityAction.ActionType.valueOf(a));
	}
	
	public EntityAction get(EntityAction.ActionType action)
	{
		switch(action)
		{
		case Null:
			return new NullAction();
		case SpeedUp:
			return new SpeedUpAction(new Vector2D(generator_.nextInt((int)Entity.MAX_VELOCITY), generator_.nextInt((int)Entity.MAX_VELOCITY)));
		case Disappear:
			return new DisappearAction();
		default:
			throw new EnumConstantNotPresentException(action.getClass(), action.toString());
		}
	}
	
	private EntityActionFactory()
	{	
	}
	
	private Random generator_ = new Random();
	private static EntityActionFactory factory_ = new EntityActionFactory(); 
}