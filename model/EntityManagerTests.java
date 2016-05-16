package model;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;

import model.entity.Entity;
import model.entity.EntityBuilder;
import utill.Vector2D;

public class EntityManagerTests
{
	EntityManager manager;
	Entity entity;
	Entity other;
	
	static Method prv_updatePosition;
	static Method prv_calculateVelocities;
	
	@BeforeClass
	public static void staticSetUp() throws Exception
	{
		prv_updatePosition = EntityManager.class
				.getDeclaredMethod("updatePosition", double.class);
		
		prv_updatePosition.setAccessible(true);	
			
		prv_calculateVelocities = EntityManager.class
				.getDeclaredMethod("calculateVelocities", new Class[]{Entity.class, Entity.class});
		
		prv_calculateVelocities.setAccessible(true);
	}
	
	@Before
	public void setUp()
	{
		manager = new EntityManager();
		entity = new EntityBuilder()
					 .position(new Vector2D(3, 3))
					 .size(3)
					 .build();
		other  = new EntityBuilder().build();
		
		manager.addEntity(entity);
	}
	
	@Test
	public void addingTheSameEntityShouldFail()
	{
		assertFalse(manager.addEntity(entity));
		assertEquals(1, manager.getEntities().size());
	}
	
	@Test
	public void addingEntityShouldFailIfEntitiesWouldOverlap()
	{
		other.getBody().setPosition(new Vector2D(3, 4));
		other.getBody().setSize(4);

		assertTrue(entity.getBody().crosses(other.getBody()));
		assertFalse(manager.addEntity(other));
		assertEquals(1, manager.getEntities().size());
	}
	
	@Test
	public void addingNonCrossingEntitiesShouldSucceed()
	{
		other.getBody().setPosition(new Vector2D(6, 6));
		other.getBody().setSize(1);
		
		assertFalse(entity.getBody().crosses(other.getBody()));
		assertTrue(manager.addEntity(other));
		assertEquals(2, manager.getEntities().size());
	}
	
	@Test
	public void updatePositionShouldUpdatePositionOfAllEntities() throws Exception
	{
		entity.setVelocity(new Vector2D(1, 1));
		other.setVelocity(new Vector2D(3, 3));
		manager.addEntity(other);
		
		prv_updatePosition.invoke(manager, 1);
		
		assertEquals(new Vector2D(4, 4), entity.getBody().getPosition());
		assertEquals(new Vector2D(3, 3), other.getBody().getPosition());
	}
	
	@Test
	public void entitesThatMovedOutsideWindowShouldBeRemoved() throws Exception
	{
		entity.setVelocity(new Vector2D(-6, -6));

		prv_updatePosition.invoke(manager, 1);
		
		assertTrue(manager.getEntities().isEmpty());
	}

	@Test
	public void entitiesWithSameMassesShouldSwapVelocities() throws Exception
	{
		other.getBody().setSize(3);
		entity.setVelocity(new Vector2D(1, 1));
		
		prv_calculateVelocities.invoke(manager, entity, other);
		
		assertEquals(new Vector2D(0, 0), entity.getVelocity());
		assertEquals(new Vector2D(1, 1), other.getVelocity());
	}
	
	@Test
	public void lighterEntitiesShouldBeThrownFaster() throws Exception
	{
		other.getBody().setSize(1.5);
		entity.setVelocity(new Vector2D(1, 1));
		
		prv_calculateVelocities.invoke(manager, entity, other);
		
		assertEquals(new Vector2D(0.6, 0.6), entity.getVelocity());
		assertEquals(new Vector2D(1.6, 1.6), other.getVelocity());
	}
	
	@Test
	public void heavierEntitesShouldBeThrownSlower() throws Exception
	{
		other.getBody().setSize(6);
		entity.setVelocity(new Vector2D(1, 1));
		
		prv_calculateVelocities.invoke(manager, entity, other);
		
		assertEquals(new Vector2D(-0.6, -0.6), entity.getVelocity());
		assertEquals(new Vector2D(0.4, 0.4), other.getVelocity());
	}
}