package tests.model.entity;

import static org.junit.Assert.*;

import org.junit.Test;

import src.model.entity.Circle;
import src.model.entity.Entity;
import src.model.entity.EntityBuilder;
import src.utill.Vector2D;

public class EntityBuilderTests
{
	@Test
	public void notSettingAnythingShouldGenerateDefaultEntity()
	{
		Entity e = new EntityBuilder().build();
		
		assertEquals(new Circle(), e.getBody());
		assertEquals(new Vector2D(), e.getVelocity());
		assertEquals(Entity.Allegiance.WHITE, e.getAllegiance());
	}
	
	@Test
	public void settingPositionShouldChangeJustThePosition()
	{
		Entity e = new EntityBuilder().position(new Vector2D(3, 3)).build();
		
		assertEquals(new Circle(new Vector2D(3, 3), 0), e.getBody());
		assertEquals(new Vector2D(), e.getVelocity());
		assertEquals(Entity.Allegiance.WHITE, e.getAllegiance());
	}
	
	@Test
	public void settingSizeShouldChangeJustTheSize()
	{
		Entity e = new EntityBuilder().size(3).build();
		
		assertEquals(new Circle(new Vector2D(), 3), e.getBody());
		assertEquals(new Vector2D(), e.getVelocity());
		assertEquals(Entity.Allegiance.WHITE, e.getAllegiance());
	}
	
	@Test
	public void settingVelocityShouldChangeJustTheVelocity()
	{
		Entity e = new EntityBuilder().velocity(new Vector2D(3, 3)).build();
		
		assertEquals(new Circle(), e.getBody());
		assertEquals(new Vector2D(3, 3), e.getVelocity());
		assertEquals(Entity.Allegiance.WHITE, e.getAllegiance());
	}
	
	@Test
	public void settingAllegianceShouldChangeJustTheAllegiance()
	{
		Entity e = new EntityBuilder().allegiance(Entity.Allegiance.BLACK).build();
		
		assertEquals(new Circle(), e.getBody());
		assertEquals(new Vector2D(), e.getVelocity());
		assertEquals(Entity.Allegiance.BLACK, e.getAllegiance());
	}
}