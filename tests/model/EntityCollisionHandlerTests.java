package tests.model;

import static org.junit.Assert.*;

import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Before;
import org.junit.Test;

import src.model.EntityCollisionHandler;
import src.model.entity.Entity;
import src.model.entity.EntityBuilder;
import src.utill.Vector2D;

public class EntityCollisionHandlerTests
{
	Entity left;
	Entity middle;
	Entity right;
	CopyOnWriteArrayList<Entity> entities;
	EntityCollisionHandler handler;
	
	@Before
	public void setUp()
	{
		entities = new CopyOnWriteArrayList<>();
		handler = new EntityCollisionHandler();
		
		EntityBuilder builder = new EntityBuilder();
		
		left = builder.size(3).build();
		middle = builder.build();
		right = builder.build();
	}
	
	@Test
	public void entitiesWithSameMassesShouldSwapVelocities() throws Exception
	{
		middle.setVelocity(new Vector2D(1, 1));
		middle.getBody().setPosition(new Vector2D(3, 3));
		
		handler.calculateVelocities(left, middle);
		
		assertEquals(new Vector2D(1, 1), left.getVelocity());
		assertEquals(new Vector2D(0, 0), middle.getVelocity());
	}
	
	@Test
	public void lighterEntitiesShouldBeThrownFaster() throws Exception
	{
		left.getBody().setSize(1.5);
		middle.setVelocity(new Vector2D(1, 1));
		middle.getBody().setPosition(new Vector2D(3, 3));
		
		handler.calculateVelocities(left, middle);
		
		assertEquals(new Vector2D(0.6, 0.6), middle.getVelocity());
		assertEquals(new Vector2D(1.6, 1.6), left.getVelocity());
	}
	
	@Test
	public void heavierEntitesShouldBeThrownSlower() throws Exception
	{
		left.getBody().setSize(6);
		middle.setVelocity(new Vector2D(1, 1));
		middle.getBody().setPosition(new Vector2D(3, 3));
		
		handler.calculateVelocities(left, middle);
		
		assertEquals(new Vector2D(-0.6, -0.6), middle.getVelocity());
		assertEquals(new Vector2D(0.4, 0.4), left.getVelocity());
	}
	
	@Test
	public void newtonsCraddleWithOneTurnEntityOrder()
	{
		left.setVelocity(new Vector2D(10, 0));
		middle.getBody().setPosition(new Vector2D(5.99, 0));
		right.getBody().setPosition(new Vector2D(11.98, 0));
		entities.add(left);
		entities.add(middle);
		entities.add(right);
		
		handler.resolveCollisions(entities, 1);
		
		assertEquals(new Vector2D(0, 0), left.getVelocity());
		assertEquals(new Vector2D(0, 0), middle.getVelocity());
		assertEquals(new Vector2D(10, 0), right.getVelocity());
	}
	
	@Test
	public void newtonsCraddleWithTwoTurnEntityOrder()
	{
		left.setVelocity(new Vector2D(10, 0));
		middle.getBody().setPosition(new Vector2D(5.99, 0));
		right.getBody().setPosition(new Vector2D(11.98, 0));
		entities.add(right);
		entities.add(middle);
		entities.add(left);
		
		handler.resolveCollisions(entities, 1);
		
		assertEquals(new Vector2D(0, 0), left.getVelocity());
		assertEquals(new Vector2D(10, 0), middle.getVelocity());
		assertEquals(new Vector2D(0, 0), right.getVelocity());
	}
}