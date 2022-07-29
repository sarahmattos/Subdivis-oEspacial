import java.awt.Graphics;
import java.awt.geom.Area;
import java.util.Random;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.geom.Ellipse2D;
import java.awt.event.*;
public class MyLabel extends JLabel {
	Ellipse2D.Double oval = new Ellipse2D.Double();
	Color color;
	int x, y, a, b;
	int auxX = 1;
	int auxY = 1;
	 Area area1; 
	 Area area2;
	float speed = 0;
	Point p = new Point();

	MyLabel(int _x, int _y, Point _p, Color _color) {
		this.a = 100;
		this.b = 100;
		this.p = _p;
		this.x = _x;
		this.y = _y;
		this.color = _color;
		aleatorizar();
		this.oval.x=this.x;
		this.oval.y=this.y;
		this.oval.width=this.a;
		this.oval.height=this.b;
		setBounds((int)this.oval.x, (int)this.oval.y, (int)this.oval.width, (int)this.oval.height);
		setVisible(true);
		new Mover().start();
	}

	public void aleatorizar() {

		Random random = new Random();
		while (speed < 2 || speed > 8) {
			speed = random.nextFloat() * 10;
		}
		auxX = random.nextInt(2);
		if (auxX == 0) {
			auxX = -1;
		}
		auxY = random.nextInt(2);
		if (auxY == 0) {
			auxY = -1;
		}
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		//oval.setFrame(0, 0, a, b);
		//area1 = new Area(oval);
		//g2.fill(area1); 
		//this.oval.x = this.x;
		//this.oval.y = this.y;
		//this.oval.width *= a;
		//this.oval.height *= b;
		g2.fillOval((int)this.oval.x, (int)this.oval.y, (int)this.oval.width, (int)this.oval.height);
	}

	public  boolean testIntersection(Ellipse2D.Double oval1, Ellipse2D.Double oval2) {
		
		Area areaA = new Area(oval1);
		areaA.intersect(new Area(oval2));
		  System.out.println(areaA.isEmpty());
		  
		  return !areaA.isEmpty();
		}
	/*
	public void Collision(Area area1  ,Area area2 )
	{
		Area@6366ebe0
		if(area1 != area2)
		{
			//if(area1.intersects(area2))
			{
				//return true;
			}else 
			{
				//return false;
			}
		}
		
		//return false;
	}
	*/
	public void contrarioX() {
		auxX *= -1;
	}

	public void contrarioY() {
		auxY *= -1;
	}

	public class Mover extends Thread {
		public void run() {

			while (true) {

				x -= speed * auxX;
				y -= speed * auxY;

				if (x > 800 - a - 15 || x < 0) {
					contrarioX();
				}
				if (y > 600 - b - 40 || y < 0) {
					contrarioY();
				}

				setLocation(x, y);

				try {
					sleep(10);

				} catch (Exception erro) {

				}

			}
		}

	}
}
