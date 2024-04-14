package level.tile;

import graphics.Screen;
import graphics.Sprite;

public class VoidTile extends Tile
{

	public VoidTile(Sprite spriteObj)
	{
		super(spriteObj);	
	}
	
	public void render(int i, int j, Screen screen) // in tile precision
	{
		screen.renderTile(i << 4, j << 4, this); // after multiplication we get pixel precision.
	}
	
}
