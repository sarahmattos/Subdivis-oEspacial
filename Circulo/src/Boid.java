import java.awt.Graphics;
import java.util.Random;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.geom.Rectangle2D;

public class Boid  {

	public Color color;
	int x, y, a, b;
	int auxX = 1;
	int auxY = 1;
	float speed = 0;

	Boid(int _x, int _y,Color _color) {
		this.a = 20;
		this.b = 20;
		this.x = _x;
		this.y = _y;
		this.color = _color;
		aleatorizar();

		//setBounds(x, y, a, b);
		//setVisible(true);
		// new Mover().start();
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
		speed=0.5f;
	}

	public void draw(Graphics2D g, Color c) {
		g.setColor(c);
		g.fillOval(x, y, a, b);
		//System.out.print("draw");
	}

	public void contrarioX() {
		auxX *= -1;
	}

	public void contrarioY() {
		auxY *= -1;
	}

	public void Update() {
		
		x += 10f * auxX;
		y += 10f *auxY ;

		
		if (x > MyFrame.largura - a - 15) {
			
			auxX=-1;
		}else if( x < a+10) {
			auxX=1;
		}
		if (y > MyFrame.altura - b - 40  ) {
			auxY=-1;
		}else if(y < b+10) {
			auxY=1;
		}

		//setLocation(x, y);
	}
	public boolean TesteColission(Boid ml3)
	{
		double x;
        double y;


        x = this.x - ml3.x;
        y = this.y - ml3.y;

        if (Math.sqrt((x*x) + (y*y)) < this.a) {
            return true;
        } else {
            return false;
        }
	}
		public boolean Colidiu(Boid ml2) {
	
		if(TesteColission(ml2)) {
			
			
			contrarioX();
			contrarioY();
			ml2.color=Color.black;
			ml2.contrarioX();
			ml2.contrarioY();
			 return true;
		}
		 return false;
	}
		public int getPositionX() {
			return x;
		}

		public int getPositionY() {
			return y;
		}

		public int getAltura() {
			return a;
		}

		public int getLargura() {
			return b;
		}
}
