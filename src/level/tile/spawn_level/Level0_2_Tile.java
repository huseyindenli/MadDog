package level.tile.spawn_level;

import graphics.Screen;
import graphics.Sprite;
import level.tile.Tile;

public class Level0_2_Tile extends Tile
{

	public Level0_2_Tile(Sprite spriteObj)
	{
		super(spriteObj);
	}
	
	public void render(int i, int j, Screen screen) // in tile precision.
	{
		screen.renderTile(i << 4, j << 4, this); // after multiplication we get pixel precision.
	} 

	// overrides the original one
	public  boolean solid()
	{
		return true;
	}
}
