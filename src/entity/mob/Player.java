package entity.mob;

import entity.projectile.Projectile;
import entity.projectile.WizardProjectile;
import graphics.AnimatedSprite;
import graphics.Screen;
import graphics.Sprite;
import graphics.SpriteSheet;
import input.Keyboard;
import input.Mouse;
import main.Game;

public class Player extends Mob
{
	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking = false;
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
	private AnimatedSprite left= new AnimatedSprite(SpriteSheet.player_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 3);
	
	private AnimatedSprite animSprite = down;
	
	private int fireRate = 10;
	
	public Player(Keyboard input)
	{
		this.input = input;
		this.sprite = Sprite.player_forward;
	}
	
	public Player(int x, int y, Keyboard input)
	{
		this.x = x;
		this.y = y;
		this.input = input;
		this.sprite = Sprite.player_forward;
		fireRate = WizardProjectile.FIRE_RATE;
	}
	
	public void update()
	{	
		if (walking)
		{
			animSprite.update();
		}
		else
		{
			animSprite.setFrame(0);
		}
		
		if (fireRate > 0)
		{
			fireRate--;
		}
		
		int xa = 0, ya = 0;
		
		if (anim < 75000) anim++; 
		else
			anim = 0;
		
		if(input.up)
		{
			animSprite = up;
			ya -= 2;
//          System.out.print("UP:" + Keyboard.keys[87] + " ");
//          System.out.print("down:" + Keyboard.keys[83] + " ");
//          System.out.print("left:" + Keyboard.keys[65] + " ");
//          System.out.print("right:" + Keyboard.keys[68] + " ");
		}
		else if(input.down)
		{
			animSprite = down;
			ya += 2;
			
//          System.out.print("up:" + Keyboard.keys[87] + " ");
//          System.out.print("DOWN:" + Keyboard.keys[83] + " ");
//          System.out.print("left:" + Keyboard.keys[65] + " ");
//          System.out.print("right:" + Keyboard.keys[68] + " ");
		}
		
		if(input.right)
		{
			xa += 2;
			animSprite = right;
//          System.out.print("up:" + Keyboard.keys[87] + " ");
//          System.out.print("down:" + Keyboard.keys[83] + " ");
//          System.out.print("left:" + Keyboard.keys[65] + " ");
//          System.out.print("RIGHT:" + Keyboard.keys[68] + " ");
		}
		else if(input.left)
		{
			xa -= 2;
			animSprite = left;
//          System.out.print("up:" + Keyboard.keys[87] + " ");
//          System.out.print("down:" + Keyboard.keys[83] + " ");
//          System.out.print("LEFT:" + Keyboard.keys[65] + " ");
//          System.out.print("right:" + Keyboard.keys[68] + " ");
		}
		
		if (Mouse.getButton() == 1)
		{
//			shoot(x, y, dir);
			updateShooting();
		}
		
		if(xa != 0 || ya != 0)
		{
			move(xa, ya);
			walking = true;
		}
		else
		{
			walking = false;
		}
		
		clear();
		updateShooting();
	}
	
	private void clear()
	{
		for(int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if (p.isRemoved())
			{
				level.getProjectiles().remove(i);
			}
		}
	}

	protected void updateShooting()
	{
		if (Mouse.getButton() == 1 && fireRate <= 0)
		{
			double dx = Mouse.getMouseX() - (Game.getWindowWidth() / 2);
			double dy = Mouse.getMouseY() - (Game.getWindowHeight() / 2);
			double dir = Math.atan2(dy, dx);
			shoot(x, y, dir);
			fireRate = WizardProjectile.FIRE_RATE;
		}
	}

	public void render(Screen screen)
	{
		int flip = 0;
		
		if (dir == 0) 
		{
			sprite = Sprite.player_forward;
			if (walking)
			{
				if (anim % 20 > 10)
				{
					sprite = Sprite.player_forward_1;
				}
				else
				{
					sprite = Sprite.player_forward_2;
				}
			}
		}
		
		if (dir == 1)
		{
			sprite = Sprite.player_side;
			if (walking)
			{
				if (anim % 20 > 10)
				{
					sprite = Sprite.player_side_1;
				}
				else
				{
					sprite = Sprite.player_side_2;
				}
			}
		}
		
		if (dir == 2) 
		{
			sprite = Sprite.player_back;
			if (walking)
			{
				if (anim % 20 > 10)
				{
					sprite = Sprite.player_back_1;
				}
				else
				{
					sprite = Sprite.player_back_2;
				}
			}
		}
		
		if (dir == 3)
		{
			sprite = Sprite.player_side;
			if (walking)
			{
				if (anim % 20 > 10)
				{
					sprite = Sprite.player_side_1;
				}
				else
				{
					sprite = Sprite.player_side_2;
				}
			}
			flip = 1;
		}
		sprite = animSprite.getSprite(); 
		screen.renderPlayer(x - 16, y - 16, sprite, flip);
	}
}
