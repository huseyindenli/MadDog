package entity.spawner;

import entity.Entity;
import entity.particle.Particle;
import level.Level;

public class Spawner extends Entity
{	
	public enum Type
	{
		MOB, 
		PARTICLE
	}
	
	private Type type;
	
	public Spawner(int x, int y, Type type, int amount, Level level) // x, y of map ; entity to spawn.
	{
		init(level);
		this.x = x;
		this.y = y;
		this.type = type;
	}
}
