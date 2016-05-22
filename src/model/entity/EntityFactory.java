package src.model.entity;

import src.utill.Vector2D;
import java.util.Random;

//import model.entity.Entity.Allegiance;
import src.view.MyFrame;

public class EntityFactory
{
	public static EntityFactory getInstance()
	{
		return factory_;
	}
	
	public Entity generateRandom()
	{
		return builder_.position(new Vector2D(generator_.nextDouble()*MyFrame.WIDTH, generator_.nextDouble()*MyFrame.HEIGHT))
					   .velocity(new Vector2D(generator_.nextDouble()*MAX_VELOCITY*2.0d-MAX_VELOCITY, generator_.nextDouble()*MAX_VELOCITY*2.0d-MAX_VELOCITY))
				       .size(generator_.nextInt(MAX_SIZE-MIN_SIZE) + MIN_SIZE)
				       .allegiance(Entity.Allegiance.values()[generator_.nextInt(Entity.Allegiance.getCount())])
					   .build();
	}
	
	private EntityFactory()
	{	
	}
	
	
	public static final int MAX_SIZE = 50;
	public static final int MIN_SIZE = 5;
	public static final double MAX_VELOCITY = 100;
	public static final int MAX_MASS = MAX_SIZE*MAX_SIZE;
	
	private EntityBuilder builder_ = new EntityBuilder();
	private Random generator_ = new Random();
	private static EntityFactory factory_ = new EntityFactory(); 
}