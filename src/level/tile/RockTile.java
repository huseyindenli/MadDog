//package level.tile;
//
//import graphics.Screen;
//import graphics.Sprite;
//
//public class RockTile extends Tile
//{
//
//	public RockTile(Sprite spriteObj)
//	{
//		super(spriteObj);
//	}
//	
//	public void render(int i, int j, Screen screen) // in tile precision.
//	{
//		screen.renderTile(i << 4, j << 4, this); // after multiplication we get pixel precision.
//	}
//	
//	public boolean solid()
//	{
//		return true; 
//	}
//}
