package main;
import graphics.Screen;
import graphics.SpriteSheet;
import input.Keyboard;
import input.Mouse;
import level.Level;
import level.TileCoord;

import javax.swing.*;

import entity.mob.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable 
{
    private static final long serialVersionUID = 1L;
    
	private Thread thread; 
    private boolean running = false;
    private JFrame frame; 
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    private Screen screenObj;
    private Keyboard keyObj;
    private Level levelObj;
    private Player playerObj;
    private static int width = 320 * 1; 
    private static int height = 180 * 1;
    private static int scale = 3;
    
    public static int getWindowWidth()
	{
		return width * scale;
	}
    
	public static int getWindowHeight()
	{
		return height * scale;
	}
	
	public static int getScale()
	{
		return scale;
	}

	public static String title = "THE GAME!";

    public Game()
    {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);
        
        screenObj = new Screen(width, height);
        frame = new JFrame();
        keyObj = new Keyboard(); // <==
        //levelObj = new RandomLevel(64, 64);
        levelObj = Level.spawnLevel;
        TileCoord playerSpawn = new TileCoord(3, 4);
        playerObj = new Player(playerSpawn.getX(), playerSpawn.getY(), keyObj);      		
        playerObj.init(levelObj);
        addKeyListener(keyObj); // <==
        
        Mouse mouse = new Mouse();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        addMouseWheelListener(mouse);
    }
    public synchronized void start()
    {
        running = true;
        thread = new Thread(this, "game");
        thread.start();
    }
    public synchronized void stop()
    {
        running = false;
        try
        {
            thread.join();
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    public void run()
    {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double NS = 1000000000.0 / 50.0; //   [1] / [seconds]
        double delta = 0;
        int frames = 0;
        int updates = 0;
        while (running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / NS; // [delta] = 1 /  (1 / [seconds]) = [seconds]
            lastTime = now;
            while (delta >= 1)
            {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - timer >= 1000)
            {
                timer+=1000;
//                System.out.println(updates + " ups," + frames + " fps");
                frame.setTitle(title + "  " + updates + " ups," + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }
    
    private void update()
    {
        keyObj.update();
        playerObj.update();
        levelObj.update();
    }
    
    private void render() 
    {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null)
        {
            createBufferStrategy(3);
            return;
        }
        screenObj.clear();
        int xScroll = playerObj.x - screenObj.width / 2;
        int yScroll = playerObj.y - screenObj.height / 2;
        levelObj.render(xScroll, yScroll, screenObj); // we are calling our levels render method.
        playerObj.render(screenObj);	
        screenObj.renderSheet(40, 40, SpriteSheet.player.player_down, false);
        System.arraycopy(screenObj.pixels, 0, pixels, 0, pixels.length);
        
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Verdana", 0, 20));
        //g.fillRect(Mouse.getMouseX() - 32, Mouse.getMouseY() - 32, 64, 64);
        
        //g.drawString("Button: " + Mouse.getButton(), 80, 80);
        //g.drawString("Mouse Wheel: " + Mouse.getMouseW(), 80, 100);
        //g.drawString("x: " + playerObj.x + ", y: " + playerObj.y, 10, 20);
        
        g.dispose();
        bs.show();
    }
    
    public static void main(String[] args)
    {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle(Game.title);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);
        game.start();
    }
}
