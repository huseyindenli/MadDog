package entity;

import java.util.Random;

import graphics.Screen;
import level.Level;
// any object except maps.
public abstract class Entity // entities can or can not have sprites.
{
	// entity location on the map.
	public int x, y; // if entity does not have a sprite these x and y will be redundant. Entities can move.
	private boolean removed = false; // entity is removed from level or not.
	protected Level level;
	protected final Random random = new Random();
	
	public void update() 
	{
	}
	
	public void render(Screen screen)
	{
		
	}
	
	public void remove() // removes entities from level.
	{
		removed = true;
	}
	
	public boolean isRemoved()
	{
		return removed;
	}
	
	public void init(Level level)
	{
		this.level = level;
	}
}
