package graphics;

import java.util.Arrays;
import entity.projectile.Projectile;
import level.tile.Tile;

// OUR RENDERER
public class Screen
{
    public int width, height; // game sinif icinde girilen 300x180 degerleri burada saklaniyor.
    //private Random random = new Random();
    private int MAP_SIZE = 64;
  //  private int MAP_SIZE_MASK = MAP_SIZE - 1;
    public int xOffset; 
    public int yOffset; 
    public int[] pixels;
    public int[] tiles = new int[MAP_SIZE * MAP_SIZE];

    public Screen(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.pixels = new int[width * height];

//        for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++)
//        {
//            tiles[i] = random.nextInt(0xFFFFFF);
//        }
    }
    
    public void clear()
    {
        Arrays.fill(pixels, 0x0);
    }
    
    public void renderSheet(int X, int Y, SpriteSheet sheet, boolean fixed) // xp - xposition
    {
    	if (fixed)
		{
    	   	X -= xOffset;
        	Y -= yOffset;			
		}
    	
    	for(int yDummy = 0; yDummy < sheet.HEIGHT; yDummy++) {
			int yOfActualSprite = yDummy + Y;
    		for(int xDummy = 0; xDummy < sheet.WIDTH; xDummy++)
    		{
    			int xOfActualSprite = xDummy + X;
    			if (xOfActualSprite < 0 || xOfActualSprite >= width || yOfActualSprite < 0 || yOfActualSprite >= height) continue; 
    			pixels[xOfActualSprite + yOfActualSprite * width] = sheet.pixels[ xDummy + yDummy * sheet.WIDTH];
    		}
    	}
    }
    
    public void renderSprite(int X, int Y, Sprite spriteObject, boolean fixed) // xp - xposition
    {
    	if (fixed)
		{
    	   	X -= xOffset;
        	Y -= yOffset;			
		}
    	
    	for(int yDummy = 0; yDummy < spriteObject.getHeight(); yDummy++) {
			int yOfActualSprite = yDummy + Y;
    		for(int xDummy = 0; xDummy < spriteObject.getWidth(); xDummy++)
    		{
    			// width is in Screen.
    			int xOfActualSprite = xDummy + X;
    			if (xOfActualSprite < 0 || xOfActualSprite >= width || yOfActualSprite < 0 || yOfActualSprite >= height) continue; // skips this iteration loop
    		 // pixels is in Screen                                                                           pixels is in Sprite
    			pixels[xOfActualSprite + yOfActualSprite * width] = spriteObject.pixels[ xDummy + yDummy * spriteObject.getWidth()];
    		}
    	}
    }
    
    // this draws the tiles on the screen, but Tile class gives the command to render.
    public void renderTile(int X, int Y, Tile tile) // here tile means tile object(can not be class b/c class does not have a body)
    {
    	X -= xOffset; // "-" because we want the tiles to move the opposite direction of the player direction.
    	Y -= yOffset;
    	for(int y = 0; y < tile.spriteObject.SIZE; y++) {
    		int yAbsolute = y + Y; 
        	for(int x = 0; x < tile.spriteObject.SIZE; x++) {
        		int xAbsolute = x + X;
        		// why do we render whole map if we only see the screen sizes of it.
        		if(xAbsolute < -tile.spriteObject.SIZE || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) break; // haritanin acilma efekti bununla ilgili. We are rendeing only visibles.
        		if(xAbsolute < 0) xAbsolute = 0;
        		pixels[xAbsolute + yAbsolute * width] = tile.spriteObject.pixels[x + y * tile.spriteObject.SIZE]; // renders to the screen
        	}
    	}
    }
    
    public void renderTile(int X, int Y, Sprite spriteObject) // here tile means tile object(can not be class b/c class does not have a body)
    {
    	X -= xOffset; // "-" because we want the tiles to move the opposite direction of the player direction.
    	Y -= yOffset;
    	for(int y = 0; y < spriteObject.SIZE; y++) {
    		int yAbsolute = y + Y; 
        	for(int x = 0; x < spriteObject.SIZE; x++) {
        		int xAbsolute = x + X;
        		// why do we render whole map if we only see the screen sizes of it.
        		if(xAbsolute < -spriteObject.SIZE || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) break; // haritanin acilma efekti bununla ilgili. We are rendeing only visibles.
        		if(xAbsolute < 0) xAbsolute = 0;
        		pixels[xAbsolute + yAbsolute * width] = spriteObject.pixels[x + y * spriteObject.SIZE]; // renders to the screen
        	}
    	}
    }
    
    public void renderProjectile(int X, int Y, Projectile p) // here tile means tile object(can not be class b/c class does not have a body)
    {
    	X -= xOffset; // "-" because we want the tiles to move the opposite direction of the player direction.
    	Y -= yOffset;
    	for(int y = 0; y < p.getSpriteSize() ; y++) {
    		int yAbsolute = y + Y; 
        	for(int x = 0; x < p.getSpriteSize(); x++) {
        		int xAbsolute = x + X;
        		// why do we render whole map if we only see the screen sizes of it.
        		if(xAbsolute < -p.getSpriteSize() || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) break; // haritanin acilma efekti bununla ilgili. We are rendeing only visibles.
        		if(xAbsolute < 0) xAbsolute = 0;
        		int col = p.getSprite().pixels[x + y * p.getSprite().SIZE];
        		if (col != 0xFFFF00FF)
        			pixels[xAbsolute + yAbsolute * width] = col; // renders to the screen
        	}
    	}
    }
    
    public void renderPlayer(int X, int Y, Sprite sprite, int flip)
    {
    	X -= xOffset; // "-" because we want the tiles to move the opposite direction of the player direction.
    	Y -= yOffset;
    	for(int y = 0; y < 32; y++) {
    		int yAbsolute = y + Y; 
    		
    		int ySprite = y;
    		if (flip == 2 || flip == 3)
    		{
        		ySprite = 31 - y;
    		}
    		
        	for(int x = 0; x < 32; x++) {
        		int xAbsolute = x + X;
        		
        		int xSprite = x;
        		if (flip == 1 || flip == 3)
        		{
            		xSprite = 31 - x;
        		}
        		
        		// why do we render whole map if we only see the screen sizes of it.
        		if(xAbsolute < -32 || xAbsolute >= width || yAbsolute < 0 || yAbsolute >= height) break; // haritanin acilma efekti bununla ilgili. We are rendeing only visibles.
        		if(xAbsolute < 0) xAbsolute = 0;
        		int col = sprite.pixels[xSprite + ySprite * 32];
        		if (col != 0xFFFF00FF) pixels[xAbsolute + yAbsolute * width] = col; // renders to the screen
        	}
    	}
    }
    
    public void setOffset(int xOffset, int yOffset)
    {
    	this.xOffset = xOffset;
    	this.yOffset = yOffset;
    }
}
