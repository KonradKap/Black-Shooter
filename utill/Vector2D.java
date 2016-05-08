package utill;

import java.awt.geom.Point2D;

public class Vector2D
{
	public double x;
	public double y;
	
	public Vector2D()
	{
		x = 0;
		y = 0;
	}
	
	public Vector2D(double argX, double argY)
	{
		x = argX;
		y = argY;
	}
	
	public Vector2D(Vector2D other)
	{
		x = other.x;
		y = other.y;
	}
	
	public Vector2D(Point2D.Double other)
	{
		x = other.x;
		y = other.y;
	}
	
	public Vector2D add(Vector2D other)
	{
		return new Vector2D(x+other.x, y+other.y);
	}
	
	public Vector2D subtract(Vector2D other)
	{
		return new Vector2D(x-other.x, y-other.y);
	}
	
	public Vector2D multiply(double scalar)
	{
		return new Vector2D(x*scalar, y*scalar);
	}
	
	public Vector2D divide(double scalar)
	{
		return new Vector2D(x/scalar, y/scalar);
	}
	
	public Vector2D negate()
	{
		return new Vector2D(-x, -y);
	}
	
	public double distance(Vector2D end)
	{
		return Math.sqrt(distanceSq(end));
	}
	
	public double distanceSq(Vector2D end)
	{
		double xParam = end.x - x;
		double yParam = end.y - y;
		return xParam*xParam + yParam*yParam;
	}
	
	public double dotProduct(Vector2D other)
	{
		return x*other.x + y*other.y;
	}
	
	public boolean equals(Vector2D other)
	{
		return x == other.x && y == other.y;
	}
}