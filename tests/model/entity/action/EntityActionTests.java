package tests.model.entity.action;

import static org.junit.Assert.*;


import org.junit.Test;

import src.model.entity.Circle;
import src.model.entity.Entity;
import src.model.entity.action.*;
import src.utill.Vector2D;
import src.view.MyFrame;

public class EntityActionTests
{
	@Test
	public void everyActionTypeShouldHaveANumber()
	{
		assertEquals(0, EntityActionFactory.ActionType.Null.getValue());
		assertEquals(1, EntityActionFactory.ActionType.SpeedUp.getValue());
		assertEquals(2, EntityActionFactory.ActionType.Disappear.getValue());
		assertEquals(3, EntityActionFactory.ActionType.ChangeAllegiance.getValue());
	}
	
	@Test
	public void everyActionTypeShouldBeAccessibleByThatNumber()
	{
		assertEquals(EntityActionFactory.ActionType.Null, EntityActionFactory.ActionType.valueOf(0));
		assertEquals(EntityActionFactory.ActionType.SpeedUp, EntityActionFactory.ActionType.valueOf(1));
		assertEquals(EntityActionFactory.ActionType.Disappear, EntityActionFactory.ActionType.valueOf(2));
		assertEquals(EntityActionFactory.ActionType.ChangeAllegiance, EntityActionFactory.ActionType.valueOf(3));
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
	public void disappearActionShouldMakeEntityNonVisible()
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
