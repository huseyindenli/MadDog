package level.tile;

import graphics.Screen;
import graphics.Sprite;
import level.tile.spawn_level.Level0_1_Tile;
import level.tile.spawn_level.Level0_2_Tile;
import level.tile.spawn_level.Level0_3_Tile;
import level.tile.spawn_level.Level0_4_1Tile;
import level.tile.spawn_level.Level0_5_Tile;
import level.tile.spawn_level.Level0_6_Tile;

// extended object is created here
// Tile = sprite + ...
// hem Sprite sinifini icine alir, hem de Sprite sinifi icinde olusturulmus static nesneyi burada kullanir.
public class Tile
{
	public int x, y;
	public Sprite spriteObject; // <= Sprite nesnesi.
	
	// grassSprite Sprite sinifinda yaratilmis olan static bir nesne oldugu icin yeniden yaratamayiz, ama ona erisimiz var.
//	public static Tile grassTileObject = new GrassTile(Sprite.grassSprite); // => we can access the extended class properties.
//	public static Tile flowerTileObject = new FlowerTile(Sprite.flowerSprite); // => we can access the extended class properties.
//	public static Tile rockTileObject = new RockTile(Sprite.rockSprite); // => we can access the extended class properties.
	
	public static Tile voidTileObject =  new VoidTile(Sprite.voidSprite);
	
	public static  final int col_spawn_red = 0xFFFF0000;
	public static  final int col_spawn_green = 0xFF00FF00; 
	public static  final int col_spawn_blue = 0xFF0000FF; 
	public static  final int col_spawn_white = 0xFFFFFFFF;
	public static  final int col_spawn_black = 0xFF000000;
	public static  final int col_spawn_orange = 0xFFFF4000;
	
	public static Tile aaa = new Level0_1_Tile(Sprite.level0_1);
	public static Tile bbb = new Level0_2_Tile(Sprite.level0_2);
	public static Tile ccc = new Level0_3_Tile(Sprite.level0_3);
	public static Tile ddd = new Level0_4_1Tile(Sprite.level0_4);
	public static Tile eee = new Level0_5_Tile(Sprite.level0_5);
	public static Tile fff = new Level0_6_Tile(Sprite.level0_6);
	
	public Tile(Sprite spriteObj)
	{
		this.spriteObject = spriteObj;
	}
	
	// every tile renders itself. There is no method in Level which says render this tile here. Basicall what Level holds a list of all the tiles in the map we need to render
	// basically it manages that list. Hareket ettigimiz de bosalan ve acilan yerlerin nasil organize edilicegini belirler.
	// What actually draws these tiles on the screen is the tile class, b/c tile class calls the render class which activates the specific renderTile method in the Screen
	// class.
	public void render(int x, int y, Screen screenObj) //  -1-
	{
		//screenObj.renderTile(x, y, this); // we can not put x, y of -1- here because they are in different format.
	}// empty but we can not delete it because if we delete this method, we get an error in render of Level class.
	
	public boolean solid()
	{
		return false; 
	}
}
