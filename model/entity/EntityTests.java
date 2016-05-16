package model.entity;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import utill.Vector2D;

public class EntityTests
{
	@Test
	public void emptyConstructorShouldFillEntityWithZerosAndSetsColorToWhite()
	{
		Entity e = new Entity();
		
		assertEquals(new Circle(), e.getBody());
		assertEquals(new Vector2D(), e.getVelocity());
		assertEquals(Entity.Allegiance.WHITE, e.getAllegiance());
	}
	
	@Test
	public void customConstructorShouldGiveProperParameters()
	{
		Entity e = new Entity(new Circle(new Vector2D(3, 4), 2), new Vector2D(3, 4), Entity.Allegiance.BLACK);
		
		assertEquals(new Circle(new Vector2D(3, 4), 2), e.getBody());
		assertEquals(new Vector2D(3, 4), e.getVelocity());
		assertEquals(Entity.Allegiance.BLACK, e.getAllegiance());
	}
	
	@Test
	public void acceleratingEntityShouldAddToItsVelocity()
	{
		Entity e = new Entity(); 
		e.setVelocity(new Vector2D(3, 3));
		
		e.accelerate(new Vector2D(3, 3));
		
		assertEquals(new Vector2D(6, 6), e.getVelocity());
		assertEquals(new Circle(), e.getBody());
		assertEquals(Entity.Allegiance.WHITE, e.getAllegiance());
	}
	
	@Test
	public void makingAStepShouldMoveEntityAccordingly()
	{
		Entity e = new Entity();
		e.setVelocity(new Vector2D(3, 3));
		
		e.makeStep(1.0);
		
		assertEquals(new Vector2D(3, 3), e.getBody().getPosition());
	}
	
	@Test
	public void allegianceShouldHaveColorBindToIt()
	{
		assertEquals(Color.BLACK, Entity.Allegiance.BLACK.getColor());
		assertEquals(Color.WHITE, Entity.Allegiance.WHITE.getColor());
	}
}