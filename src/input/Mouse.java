package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener
{
	private static int mouseX = -1;
	private static int mouseY = -1;
	private static int button = -1;	
//	private static int mouseW = -1;
	
	public static int getButton()
	{
		return button;
	}
	
	public static int getMouseX()
	{
		return mouseX;
	}

	public static int getMouseY()
	{
		return mouseY;
	}

//	public static int getMouseW()
//	{
////		return mouseW;
//	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e)
	{
//		mouseW = e.getWheelRotation();
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		button = e.getButton();
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		button = -1;
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
	}
	
}
