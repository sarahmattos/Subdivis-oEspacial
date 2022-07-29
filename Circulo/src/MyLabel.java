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
	Ellipse2D.Float oval = new Ellipse2D.Float();
	Color color;
	float x, y;
	float a, b;
	int auxX = 1;
	int auxY = 1;
	float speed = 0;
	Point p = new Point();

	
	MyLabel(int _x, int _y, Point _p, Color _color) {
		//this.oval=_oval;
		
		this.a = 50f;
		this.b = 50f;
		this.x = _x;
		this.y = _y;
		this.p = _p;
		this.color = _color;
		
		oval.x=x;
		oval.y=y;
		oval.width=a;
		oval.height=b;
		aleatorizar();
		
		System.out.println(oval.height);
		setBounds((int)oval.x,(int)oval.y, (int)oval.width, (int)oval.height);
		//setBounds(100,100, 50, 50);
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
		
		//this.oval.x = this.x;
		//this.oval.y = this.y;
		//this.oval.width *= this.x;
		//this.oval.height *= this.y;
		
		
		g2.fillOval((int)oval.x,(int)oval.y, (int)oval.height, (int)oval.width);
		//g2.fillOval(100,100,50, 50);
		//oval.setFrame(0, 0, a, b);
		//area1 = new Area(oval);
		//g2.fill(area1); 
		//g2.fillOval(0, 0, a, b);
		//System.out.println(oval.x);
	}

	public  boolean testIntersection(Ellipse2D.Float oval1, Ellipse2D.Float oval2) {
		
		  Area area1 = new Area(oval1);
		area1.intersect(new Area(oval2));
		  System.out.println(area1.isEmpty());
		 // System.out.println(area1);
		  return !area1.isEmpty();
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

				if (x > 800 - 50 - 15 || x < 0) {
					contrarioX();
				}
				if (y > 600 - 50 - 40 || y < 0) {
					contrarioY();
				}

				setLocation((int)x, (int)y);

				try {
					sleep(10);

				} catch (Exception erro) {

				}

			}
		}

	}
}
