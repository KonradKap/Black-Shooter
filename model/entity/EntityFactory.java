package model.entity;

import utill.Vector2D;
import java.util.Random;

//import model.entity.Entity.Allegiance;
import view.MyFrame;

public class EntityFactory
{
	public static EntityFactory getInstance()
	{
		return factory_;
	}
	
	public Entity generateRandom()
	{
		return builder_.position(new Vector2D(generator_.nextDouble()*MyFrame.WIDTH, generator_.nextDouble()*MyFrame.HEIGHT))
					   .velocity(new Vector2D(generator_.nextDouble()*Entity.MAX_VELOCITY*2.0d-Entity.MAX_VELOCITY, generator_.nextDouble()*Entity.MAX_VELOCITY*2.0d-Entity.MAX_VELOCITY))
				       .size(generator_.nextInt(Entity.MAX_SIZE-Entity.MIN_SIZE) + Entity.MIN_SIZE)
				       .allegiance(Entity.Allegiance.values()[generator_.nextInt(Entity.Allegiance.getCount())])
				       .action(EntityActionFactory.getInstance().generateRandom())
					   .build();
	}
	
	private EntityFactory()
	{	
	}
	
	private EntityBuilder builder_ = new EntityBuilder();
	private Random generator_ = new Random();
	private static EntityFactory factory_ = new EntityFactory(); 
}