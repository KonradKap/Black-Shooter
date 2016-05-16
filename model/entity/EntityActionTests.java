package model.entity;

import static org.junit.Assert.*;

import org.junit.Test;

import utill.Vector2D;
import view.MyFrame;

public class EntityActionTests
{
	@Test
	public void everyActionTypeShouldHaveANumber()
	{
		assertEquals(0, EntityAction.ActionType.Null.getValue());
		assertEquals(1, EntityAction.ActionType.SpeedUp.getValue());
		assertEquals(2, EntityAction.ActionType.Disappear.getValue());
		assertEquals(3, EntityAction.ActionType.ChangeAllegiance.getValue());
	}
	
	@Test
	public void everyActionTypeShouldBeAccessibleByThatNumber()
	{
		assertEquals(EntityAction.ActionType.Null, EntityAction.ActionType.valueOf(0));
		assertEquals(EntityAction.ActionType.SpeedUp, EntityAction.ActionType.valueOf(1));
		assertEquals(EntityAction.ActionType.Disappear, EntityAction.ActionType.valueOf(2));
		assertEquals(EntityAction.ActionType.ChangeAllegiance, EntityAction.ActionType.valueOf(3));
	}
	
	@Test
	public void nullActionShouldDoNothing()
	{
		Entity e = new Entity();
		new NullAction().doAction(e);
		
		assertEquals(new Circle(), e.getBody());
		assertEquals(new Vector2D(), e.getVelocity());
		assertEquals(Entity.Allegiance.WHITE, e.getAllegiance());
	}
	
	@Test
	public void speedUpActionShouldChangeEntitysSpeed()
	{
		Entity e = new Entity();
		new SpeedUpAction(new Vector2D(3, 3)).doAction(e);
		
		assertEquals(new Circle(), e.getBody());
		assertEquals(new Vector2D(3, 3), e.getVelocity());
		assertEquals(Entity.Allegiance.WHITE, e.getAllegiance());
	}
	
	@Test
	public void DisappearActionShouldMakeEntityNonVisible()
	{
		Entity e = new Entity(new Circle(new Vector2D(3, 3), 3), new Vector2D(), Entity.Allegiance.BLACK);
		assertTrue(e.getBody().isVisibleInside(MyFrame.windowBegin(), MyFrame.windowSize()));
		
		new DisappearAction().doAction(e);
		
		assertFalse(e.getBody().isVisibleInside(MyFrame.windowBegin(), MyFrame.windowSize()));
	}
	
	@Test
	public void changeAllegianceActionShouldDoSo()
	{
		Entity e = new Entity();
		new ChangeAllegianceAction().doAction(e);
		
		assertEquals(new Circle(), e.getBody());
		assertEquals(new Vector2D(), e.getVelocity());
		assertEquals(Entity.Allegiance.BLACK, e.getAllegiance());
	}
}
