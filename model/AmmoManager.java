package model;

public class AmmoManager
{
	public AmmoManager()
	{
	}
	
	public int getCount()
	{
		return currentCount_;
	}
	
	public void setCount(int newCount)
	{
		currentCount_ = newCount;
	}
	
	public void decrementCount()
	{
		setCount(getCount() - 1);
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
	public final static int MAX_COUNT = 5;
}