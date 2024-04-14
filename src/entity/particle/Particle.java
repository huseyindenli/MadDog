package entity.particle;

import entity.Entity;
import graphics.Screen;
import graphics.Sprite;

public class Particle extends Entity
{
	private Sprite sprite;
	
	private int life;
	private int time = 0;
	
	protected double xx, yy, zz;
	protected double xa, ya, za;
	
	public Particle(int x, int y, int life)
	{
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.life = life + (random.nextInt(17) - 7);
		this.sprite = Sprite.particle_normal;
		
		this.xa = (random.nextGaussian()) ;
		this.ya = (random.nextGaussian()) ;
		
//		if (this.xa < 0)
//		{
//			xa = 0.1;
//		}
		
//		this.ya = (random.nextGaussian());
//		this.za = (random.nextGaussian());
		this.zz = 4; // my: 4 my: 37 with effect
	}

	public void update() 
	{
		time++;
		
		if (time > 500)
		{
			time = 0;
		}
		
		if (time > life)
		{
			remove();
		}
		
//		za -= 1; // every update -0.1, -0,2, -0,3, -0.6 ...
		
		move(xx + xa, (yy + ya) + (zz + za));

		// effect
//		if (zz < 0)
//		{
//			zz = 0;
//			za *= -0.9;
//			xa *= 1.55;
//			ya *= -1.4;
//		}
				
	}
	
	private void move(double x, double y)
	{
		if (collision(x, y))
		{
			this.xa *= -0.5;
			this.ya *= -0.5;
			this.za *= -0.5;
		}
		
		this.xx += xa;
		this.yy += ya;
		this.zz += za;
	}
	
	public boolean collision(double x, double y) // xa - direction
	{
		boolean solid = false;
		
		for(int c = 0; c < 4; c++)
		{
			double xt = (x - c % 2 * 16) / 16; // 16 size of our tiles
			double yt = (y - c / 2 * 16) / 16 ;
			
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

	public void render(Screen screen)
	{
		screen.renderSprite((int) xx - 1, (int) yy + 6 - (int) zz, sprite, true);
	}
	
}
