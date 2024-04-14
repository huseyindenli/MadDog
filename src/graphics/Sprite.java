package graphics;

public class Sprite // grass which is a Sprite object in Sprite class.
{
	public final int SIZE; // size of a particular sprite. maybe some monster in the game 32x32(2x2 tiles) instead of 16x16(1x1).
	private int X; // coords. of left upper corner of particular sprite in sheet.  
	private int Y;
	private int width; 
	private int height; 
	public int[] pixels; // MAIN DATA (THE SPRITE) will be here.
	protected SpriteSheet sheetObj; // we only need the pixels part of sheet(sheet.pixels[]) (we do not need path and size parts). Here we can not use static b/c we can have more than one sheet, so we included and object.
	
	public static Sprite voidSprite = new Sprite(16, 0xFF00FFFF); // voidSprite color
	// level0 sprites
	public static Sprite level0_1 = new Sprite(16, 0, 0, SpriteSheet.level0_sheet); // 1
	public static Sprite level0_2 = new Sprite(16, 1, 0, SpriteSheet.level0_sheet); // 2
	public static Sprite level0_3 = new Sprite(16, 2, 0, SpriteSheet.level0_sheet); // 3
	public static Sprite level0_4 = new Sprite(16, 0, 1, SpriteSheet.level0_sheet); // 4
	public static Sprite level0_5 = new Sprite(16, 1, 1, SpriteSheet.level0_sheet); // 5
	public static Sprite level0_6 = new Sprite(16, 2, 1, SpriteSheet.level0_sheet); // 6
	// projectile sprites
	public static Sprite fireball = new Sprite(16,  0, 0, SpriteSheet.projectile_fireball);
	// player sprites 
	public static Sprite player_forward = new Sprite(32, 0, 5, SpriteSheet.player_sheet);
	public static Sprite player_back = new Sprite(32, 2, 5, SpriteSheet.player_sheet);
	public static Sprite player_side = new Sprite(32, 1, 5, SpriteSheet.player_sheet);
	public static Sprite player_forward_1 = new Sprite(32, 0, 6, SpriteSheet.player_sheet);
	public static Sprite player_forward_2 = new Sprite(32, 0, 7, SpriteSheet.player_sheet);
	public static Sprite player_side_1 = new Sprite(32, 1, 6, SpriteSheet.player_sheet);
	public static Sprite player_side_2 = new Sprite(32, 1, 7, SpriteSheet.player_sheet);
	public static Sprite player_back_1 = new Sprite(32, 2, 6, SpriteSheet.player_sheet);
	public static Sprite player_back_2 = new Sprite(32, 2, 7, SpriteSheet.player_sheet);
	// particles
	public static Sprite particle_normal = new Sprite(3, 0xAAAAAA);
       
	protected Sprite(SpriteSheet sheet, int width, int height)
	{
		if (width == height)
		{
			SIZE = width;
		} 
		else
		{
			SIZE = -1;
		}
		
		this.width = width;
		this.height = height;
		this.sheetObj = sheet;
	}
	
	// constructor 0
	public Sprite(int width, int height, int color)
	{
		this.SIZE = -1; 
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
		setColor(color); // color'i pixels[i]'e atar.
	}
	
	// constructor 1
	public Sprite(int size, int i, int j, SpriteSheet sheetObj) // size = 32 for a big monster. sheet: path, size, int[] pixels.
	{
		this.SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE]; 
		this.X = i * size;
		this.Y = j * size; 
		this.sheetObj = sheetObj; 
		load(); 
	}
	
	// constructor 2
	public Sprite(int size, int color)
	{
		this.SIZE = size;
		this.width = size;
		this.height = size;
		this.pixels = new int[SIZE * SIZE];
		setColor(color); 
	}

	public Sprite(int[] pixels, int width, int height)
	{
		if (width == height)
		{
			SIZE = width;
		} 
		else
		{
			SIZE = -1;
		}
		
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}

	private void setColor(int color)
	{
		for(int i = 0; i < width * height; i++)
		{
			pixels[i] = color;
		}
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	// extracts a single sprite(say a 32x32 monster) from the sheet.
	private void load()
	{
		for(int y = 0; y < SIZE; y++) {
			for(int x = 0; x < SIZE; x++) 
			{
				pixels[x + y * SIZE] = sheetObj.pixels[(x + this.X) + (y + this.Y) * sheetObj.SIZE]; 
			}
		}
	}
}
