package model;

public class Model 
{
	public Model()
	{
		entityManager_.addObserver(pointCounter_);
	}
	
	public void fire(int x, int y)
	{
		if(getAmmoManager().canFire())
		{
			getAmmoManager().fire();
			getEntityManager().fire(x,  y);
		}
	}
	
	public void reload()
	{
		getAmmoManager().reload();
	}
	
	
	public EntityManager getEntityManager()
	{
		return entityManager_;
	}
	
	public int getPointCount()
	{
		return pointCounter_.getCount();
	}
	
	AmmoManager getAmmoManager()
	{
		return ammoManager_;
	}
	
	
	private PointCounter pointCounter_ = new PointCounter();
	private AmmoManager ammoManager_ = new AmmoManager();
	private EntityManager entityManager_ = new EntityManager();
	public static final String TITLE = "Black Shooter"; 

}
