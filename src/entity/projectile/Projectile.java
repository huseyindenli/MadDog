package entity.projectile;

import java.util.Random;

import entity.Entity;
import graphics.Sprite;

public abstract class Projectile extends Entity
{
	protected final int xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double x, y; // overrides int x, y in Entity
	protected double nx, ny;
	protected double distance;
	protected double speed, range, damage;
	
	protected final Random random = new Random();
	
	public Projectile(int x, int y, double dir)
	{
		this.xOrigin = x;
		this.yOrigin = y;
		angle = dir;		
		this.x = x;
		this.y = y;
	}
	
	protected void move()
	{
		
	}

	public Sprite getSprite()
	{
		return sprite;
	}
	
	public int getSpriteSize()
	{
		return sprite.SIZE;
	}
}
