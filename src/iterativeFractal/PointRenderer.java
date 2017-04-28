package iterativeFractal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class PointRenderer extends JPanel implements MouseListener
{
	private static final long serialVersionUID = -8564762771569206113L;
	
	private ArrayList<Point> outerPoints;
	private ArrayList<Point> innerPoints;
	Object lock;
	int oIt;
	
	public PointRenderer()
	{
		oIt = 0;
		outerPoints = new ArrayList<>();
		innerPoints = new ArrayList<>();
		this.setPreferredSize(new Dimension(800, 800));
		lock = new Object();
		this.addMouseListener(this);
	}
	
	public void addOuterPoints(ArrayList<Point> i)
	{
		outerPoints = i;
	}
	
	public void addInnerPoint(Point p)
	{
		synchronized(lock)
		{
			innerPoints.add(p);
		}
	}
	
	@Override
	public void paint(Graphics g)
	{
		System.out.println("repaint");
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.setColor(Color.RED);
		for(Point p : outerPoints)
		{
			g2d.fillOval((int)Math.round(p.x)-5+getWidth()/2, (int)Math.round(p.y)-5+getHeight()/2, 10, 10);
		}
		g2d.setColor(Color.WHITE);
		synchronized(lock)
		{
			for(Point p : innerPoints)
			{
				g2d.fillOval((int)Math.round(p.x)-1+getWidth()/2, (int)Math.round(p.y)-1+getHeight()/2, 2, 2);
			}
		}
		g2d.dispose();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		outerPoints.set(oIt%Main.POINTS, new Point(e.getX()-getWidth()/2, e.getY()-getHeight()/2));
		oIt++;
		innerPoints.clear();
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
}
