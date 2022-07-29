import java.awt.Graphics;
import java.util.Random;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyLabel extends JLabel {

	int x, y, a, b;
	int auxX = 1;
	int auxY = 1;
	float speed = 0;
	Point p = new Point();

	MyLabel(int _x, int _y, Point _p) {
		this.a = 50;
		this.b = 50;
		this.p = _p;
		this.x = _x;
		this.y = _y;

		aleatorizar();

		setBounds(x, y, a, b);
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
		g2.fillOval(0, 0, a, b);
	}

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
