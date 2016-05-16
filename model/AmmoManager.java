package model;

import java.security.InvalidParameterException;

public class AmmoManager
{
	public AmmoManager()
	{
	}
	
	public int getCount()
	{
		return currentCount_;
	}
	
	public void resetCount()
	{
		currentCount_ = MAX_COUNT;
	}
	
	public void setCount(int newCount)
	{
		if(newCount < 0)
			throw new InvalidParameterException("Ammo count cannot be negative");
		currentCount_ = newCount;
	}
	
	public void decrementCount()
	{
		setCount(getCount() - 1);
	}
	
	public boolean hasMaxCount()
	{
		return currentCount_ == MAX_COUNT;
	}
	
	public boolean canFire()
	{
		return currentCount_ > 0 && !reloading_;
	}
	
	public boolean isReloading()
	{
		return reloading_;
	}
	
	public void startReloading()
	{
		reloading_ = true;
	}
	
	public void stopReloading()
	{
		reloading_ = false;
	}
	
	private int currentCount_ = MAX_COUNT;
	private boolean reloading_ = false;
	private final static int MAX_COUNT = 5;
}