package model;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.junit.Before;
import org.junit.Test;

public class AmmoManagerTests
{
	AmmoManager manager;
	
	@Before
	public void setUp()
	{
		manager = new AmmoManager();
	}
	
	@Test
	public void shouldHasMaximumAmmoByDefalut()
	{
		assertTrue(manager.hasMaxCount());
	}
	
	@Test (expected = InvalidParameterException.class)
	public void ammoCountShouldNotBeNegative()
	{
		manager.setCount(-1);
	}
	
	@Test
	public void decrementationShouldDecrement() //Test names -,-
	{
		int beforeCount = manager.getCount();
		manager.decrementCount();
		
		assertEquals(beforeCount - 1, manager.getCount());
	}
	
	@Test
	public void resettingCountShouldResetCount() //it continues
	{
		manager.decrementCount();
		manager.resetCount();
		
		assertTrue(manager.hasMaxCount());
	}
	
	@Test
	public void shouldNotBeAbleToShootWhileReloading()
	{
		manager.startReloading();
		assertTrue(manager.getCount() > 0);
		assertFalse(manager.canFire());
	}
	
	@Test
	public void shouldNotBeAbleToShootWithoutAmmo()
	{
		manager.setCount(0);
		assertFalse(manager.isReloading());
		assertFalse(manager.canFire());
	}
	
	
}