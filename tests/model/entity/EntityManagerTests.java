package tests.model.entity;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.Before;

import src.model.EntityManager;
import src.model.entity.Entity;
import src.model.entity.EntityBuilder;
import src.utill.Vector2D;

public class EntityManagerTests
{
	EntityManager manager;
	Entity entity;
	Entity other;
	
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
	public void updatePositionShouldUpdatePositionOfAllEntities() 
	{
		entity.setVelocity(new Vector2D(1, 1));
		other.setVelocity(new Vector2D(3, 3));
		manager.addEntity(other);
		
		manager.updatePosition(1);
		
		assertEquals(new Vector2D(4, 4), entity.getBody().getPosition());
		assertEquals(new Vector2D(3, 3), other.getBody().getPosition());
	}
	
	@Test
	public void entitesThatMovedOutsideWindowShouldBeRemoved()
	{
		entity.setVelocity(new Vector2D(-6, -6));

		manager.updatePosition(1);
		
		assertTrue(manager.getEntities().isEmpty());
	}
	
	@Test
	public void updatesThatCauseCollisionsShouldSolveThem()
	{
		other.getBody().setPosition(new Vector2D(9, 3));
		other.getBody().setSize(3);
		entity.setVelocity(new Vector2D(1, 0));
		manager.addEntity(other);
		
		manager.update(1);

		assertEquals(new Vector2D(10, 3), other.getBody().getPosition());
		assertEquals(new Vector2D(3, 3), entity.getBody().getPosition());
	}
	
	@Test
	public void withNoCollisionsComingUpdateShouldJustChangePositions()
	{
		other.getBody().setPosition(new Vector2D(10, 3));
		other.getBody().setSize(3);
		entity.setVelocity(new Vector2D(1, 0));
		manager.addEntity(other);
		
		manager.update(1);

		assertEquals(new Vector2D(10, 3), other.getBody().getPosition());
		assertEquals(new Vector2D(4, 3), entity.getBody().getPosition());
	}
}