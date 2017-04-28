package iterativeFractal;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class Main
{
	public final static int POINTS = 3;
	
	
	

	public static void main(String[] args) throws InterruptedException
	{
		ArrayList<Point> points = new ArrayList<>();
		double theta = (Math.PI * 2 / POINTS);
		for(int i = 0; i < POINTS; i++)
		{
			double angle = (theta * i) - (Math.PI/2);
			points.add(new Point(300*Math.cos(angle), 300*Math.sin(angle)));
		}
		PointRenderer r = new PointRenderer();
		r.addOuterPoints(points);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(r);
		frame.pack();
		frame.setVisible(true);
		Point p = new Point(0,0);
		Thread.sleep(1000);
		r.addInnerPoint(p);
		frame.repaint();
		final ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
		ses.scheduleWithFixedDelay(() -> frame.repaint(), 1, 1, TimeUnit.SECONDS);
		frame.addComponentListener(new ComponentAdapter()
		{
            @Override
            public void componentHidden(ComponentEvent e)
            {
                System.exit(0);
                ((JFrame)(e.getComponent())).dispose();
            }
        });
		while(true)
		{
			int n = (int)(Math.random() * POINTS);
			Point next = points.get(n);
			double newX = (next.x-p.x)*0.5+p.x;
			double newY = (next.y-p.y)*0.5+p.y;
			p = new Point(newX, newY);
			r.addInnerPoint(p);
			Thread.sleep(1);
		}
	}

}
