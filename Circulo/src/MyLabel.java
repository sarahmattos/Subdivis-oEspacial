import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class MyLabel extends JLabel{
	
	int x,y,a,b;
	Point p= new Point();
	
	MyLabel(int _x, int _y, Point _p){
		this.a=_x;
		this.b=_y;
		this.p=_p;
		this.x=_x;
		this.y=_y;
		setBounds(x, y, 100, 100);
		setVisible(true);
		new Mover().start();
	}
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.fillOval(0,0,100,100);
	}
	
	
	/*
	public void Update()
	{
		int x = 400;
		x -= 2f * -1;
		setLocation(x, 0);
		
	}
	*/
	
	public class Mover extends Thread {
		public void run() {
			
			int auxX=1;
			int auxY=1;
			while(true) {
				x -= 2f * auxX;
				y-= 2f * auxY;
				if(x>700||x<0) {
					auxX *= -1;
				}
				if(y>500||y<0) {
					auxY *= -1;
				}
				setLocation(x, y);
				try {
					sleep(10);
				
				}catch(Exception erro) {}
				
			}
		}
		
	}
}
