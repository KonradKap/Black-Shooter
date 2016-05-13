package model;

public interface Model
{
	public void update(double elapsedSeconds);
	public void start();
	public void pause();
	
	public static final String TITLE = "Black Shooter"; 
}