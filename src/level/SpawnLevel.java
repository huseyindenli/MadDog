package level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpawnLevel extends Level
{	
	public SpawnLevel(String path)
	{
		super(path);
	}
	
	protected void loadLevel(String path)
	{
		try
		{
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			
			width = image.getWidth();
			height = image.getHeight();
			
			int w = width;
			int h = height;
			
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("exception: could not load level file.");
		}
	}
	
	// Grass = 0x00FF00, Flower = 0xFFFF00, Rock = 0x7F7F00
	protected void generateLevel()
	{
	}
}
