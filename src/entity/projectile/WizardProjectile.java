package entity.projectile;

import entity.spawner.ParticleSpawner;
import graphics.Screen;
import graphics.Sprite;

public class WizardProjectile extends Projectile
{
	public static final int FIRE_RATE = 20; // higher is slower.

	public WizardProjectile(int x, int y, double dir)
	{
		super(x, y, dir);
		range = 200;
		speed = 4;
		damage = 20;
		sprite = Sprite.fireball;

		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
	public void update()
	{
		if (level.tileCollision((int) (x + nx), (int) (y + ny), 9, 3, 3)) // x, y of projectile; nx, ny directions of projectile, size of projectile
		{
//			Particle p = new Particle((int) x, (int) y, 50);
//			level.add(p);
			level.add(new ParticleSpawner((int) x, (int) y, 40, 100, level)); // my: 40 
			remove(); // removes bullet
		}
		
		move();
	}
	
	protected void move()
	{
		x += nx;
		y += ny;

		if (distance() > range) remove();	
	}
	
	private double distance()
	{
		double dist = 0;
		dist = Math.sqrt((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y));
		return dist;
	}

	public void render(Screen screen)
	{
		screen.renderProjectile((int) x - 8,(int) y - 8, this);
	}
	
}
