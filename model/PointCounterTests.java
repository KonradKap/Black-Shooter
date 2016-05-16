package model;

import static org.junit.Assert.*;

import org.junit.Test;

import model.entity.Entity;

import org.junit.Before;
//import org.junit.BeforeClass;

public class PointCounterTests
{
	PointCounter counter;
	Entity entity;
	
	@Before
	public void setUp()
	{
		counter = new PointCounter();
		entity = new Entity();
	}
	
	@Test
	public void shootingBlacksShouldBeRewarded()
	{
		entity.setAllegiance(Entity.Allegiance.BLACK);
		entity.getBody().setSize(5);
		counter.update(entity);
		
		assertEquals(2475, counter.getCount());
	}
	
	@Test
	public void shootingSmallerBlacksShouldRewardMore()
	{
		entity.setAllegiance(Entity.Allegiance.BLACK);
		entity.getBody().setSize(49);
		counter.update(entity);
		int reward_one = counter.getCount();
		entity.getBody().setSize(1);
		counter.update(entity);
		int reward_two = counter.getCount() - reward_one;
		
		assertTrue(reward_two > reward_one);
	}
	
	@Test
	public void shootingWhitesShouldBePunished()
	{
		entity.getBody().setSize(5);
		counter.update(entity);
		
		assertEquals(-25, counter.getCount());
	}
	
	@Test
	public void shootingBiggerWhitesShouldPunishMore()
	{
		entity.getBody().setSize(49);
		counter.update(entity);
		int punish_one = Math.abs(counter.getCount());
		entity.getBody().setSize(1);
		counter.update(entity);
		int punish_two = Math.abs(counter.getCount()) - punish_one;
		
		assertTrue(punish_two < punish_one);
	}
}