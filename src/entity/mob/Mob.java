package entity.mob;

import entity.Entity;
import entity.projectile.Projectile;
import entity.projectile.WizardProjectile;
import graphics.Sprite;

// mobs do have sprites.
public abstract class Mob extends Entity
{
	protected Sprite sprite;
	protected int dir = 0; // north, east, south, west.
	protected boolean moving = false; // mobs usually have animation when they are moving.
	protected boolean walking = false;
		
	public void move(double xa, double ya) // change of axis +1, -1, 0.
	{
		if (xa != 0 && ya != 0)
		{
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		// sag taraf cherno'da Direction.RIGHT, Direction.LEFT, ...
		if(xa > 0) dir = 1; // 0 north(up), 1east(right), 2 south(down), 3 left(west) 
		if(xa < 0) dir = 3;
		if(ya > 0) dir = 2;
		if(ya < 0) dir = 0;
		
		for (int x = 0; x < Math.abs(xa); x++)
		{
			if (!collision(abs(xa), ya)) // wall collision
			{
				this.x += abs(xa);
			}	
		}
		
		for (int y = 0; y < Math.abs(ya); y++)
		{
			if (!collision(xa, abs(ya))) // wall collision
			{
				this.y += abs(ya);
			}	
		}
	}
	
	private int abs(double value)
	{
		if (value < 0)
		{
			return -1;
		}
		return 1;
	}
	
	public void update() // mobs need to be updated like location, position
	{
		
	}
	

	protected void shoot(int x, int y, double dir)
	{		
		Projectile p = new WizardProjectile(x, y, dir);
		level.add(p);
	}
	
	private boolean collision(double xa, double ya)
	{
		boolean solid = false;
		
		for(int c = 0; c < 4; c++)
		{
			double xt = ((x + xa) - c % 2 * 16) / 16; // xa amount of translation
			double yt = ((y + ya) - c / 2 * 16) / 16;
			
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			
			if (c % 2 == 0) // checking for the left side 
			{
				ix = (int) Math.floor(xt);
			}
			
			if (c / 2 == 0) // checking for the left side 
			{
				iy = (int) Math.floor(yt);
			}
			
			if (level.getTile(ix, iy).solid())
			{
				solid = true;
			}
		}
		
		return solid;
	}
	
	public void render()
	{
		
	}
}
