package level;

import java.util.ArrayList;
import java.util.List;

import entity.Entity;
import entity.particle.Particle;
import entity.projectile.Projectile;
import graphics.Screen;
import level.tile.Tile;

public class Level
{
	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	protected int tile_size;
	
	private List<Entity> entities = new ArrayList<Entity>();
	public List<Projectile> projectiles = new ArrayList<Projectile>();
	public List<Particle> particles = new ArrayList<Particle>();
	
	public static Level spawnLevel = new SpawnLevel("/levels/level0.png");
	
	public Level(int width, int height)
	{
		this.width = width;
		this.height = height;
		this.tilesInt = new int[width * height];
		generateLevel(); // generates a random level
	}
	
	public Level(String path)
	{
		loadLevel(path);
		generateLevel(); 
		
		// kademe olusturulunca calisir
//		add(new Spawner(3 * 3, 4 * 3, Spawner.Type.PARTICLE, 500, this)); 
	}

	protected void generateLevel()
	{
		
	}
	
	protected void loadLevel(String path)
	{
		
	}
	
	public void update() // updates level and entities within
	{
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		
		for(int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		
		for(int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
		}
		remove();
	}
	
	private void remove()
	{
		for(int i = 0; i < entities.size(); i++) {
			if(entities.get(i).isRemoved())
			{
				entities.remove(i);
			}
		}
		
		for(int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isRemoved())
			{
				projectiles.remove(i);
			}
		}
		
		for(int i = 0; i < particles.size(); i++) {
			if (particles.get(i).isRemoved())
			{
				particles.remove(i);
			}
		}
	}
	
//	private void time()
//	{
//		
//	}
//	
	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) // xa - direction
	{
		boolean solid = false;
		
//		for(int c = 0; c < 4; c++)
//		{
//			int xt = (x + c % 2 * size / 2 - 2) / 16;
//			int yt = (y+ c / 2 * size / 6 - 0) / 16 ;
//			if (getTile(xt, yt).solid())
//			{
//				solid = true;
//			}
//		}
		
		for(int c = 0; c < 4; c++)
		{
			int xt = (x - c % 2 * size + xOffset) >> 4;
			int yt = (y - c / 2 * size + yOffset) >> 4 ;
			if (getTile(xt, yt).solid())
			{
				solid = true;
			}
		}
		
		return solid;
	}
	
	// xScroll and yScroll describes where the player is. (player is dealing with pixel precision), but when we are dealing with tiles we are dealing with tile 
	// precision because we want to know exactly which tile needs to get rendered.
	public void render(int xScroll, int yScroll, Screen screenObject) // renders everything
	{
		screenObject.setOffset(xScroll, yScroll);
		// vertical line (y); like x = 0 line.
		// these 4 define the render region.
		// pixel coords. => tile coords.
		int x0 = xScroll >> 4; // because we want to deal with tile level(not a pixel level)
		int x1 = (xScroll + screenObject.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screenObject.height + 16) >> 4;
		
		for(int y = y0; y < y1; y++) { // when we render we need to convert it back to pixel precision because we are rendering individual pixels not tiles.
			for(int x = x0; x < x1; x++)
			{
				getTile(x, y).render(x, y, screenObject); 
			}
		}
		
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screenObject);
		}
		
		for(int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screenObject);
		}
		
		for(int i = 0; i < particles.size(); i++) {
			particles.get(i).render(screenObject);
		}
	}
	
	public void add(Entity e)
	{
		e.init(this);
		if (e instanceof Particle)
		{
			particles.add((Particle) e);
		}
		else if (e instanceof Projectile) 
		{
			projectiles.add((Projectile) e);
		}
		else
		{
			entities.add(e);
		}
	}

	// we will render what this returns.
	// we retrieve a peticular tile in the sprite sheet.
	// public static  final int col_spawn_red = 0xFFFF0000;
	// public static Tile aaa = new Level0_1_Tile(Sprite.level0_1);
	public Tile getTile(int x, int y) // <= i, j mi olmali ? HAYIR
	{
		if(x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTileObject; 
		
		if(tiles[x + y * width] == Tile.col_spawn_red) return Tile.aaa;
		if(tiles[x + y * width] == Tile.col_spawn_green) return Tile.bbb;
		if(tiles[x + y * width] == Tile.col_spawn_blue) return Tile.ccc;
		if(tiles[x + y * width] == Tile.col_spawn_white) return Tile.ddd;
		if(tiles[x + y * width] == Tile.col_spawn_black) return Tile.eee;
		if(tiles[x + y * width] == Tile.col_spawn_orange) return Tile.fff;
		
		return Tile.voidTileObject;
	}

	public List<Projectile> getProjectiles()
	{
		return projectiles;
	}
	
}
