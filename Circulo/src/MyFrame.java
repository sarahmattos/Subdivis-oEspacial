import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import javax.swing.*;
import java.awt.geom.Ellipse2D;

public class MyFrame extends JFrame {
	MyLabel[] ml = new MyLabel[10];
	Point p = new Point(0, 1);
	Random random = new Random();
	Color color[] = { Color.PINK, Color.blue, Color.red, Color.yellow, Color.orange };
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	MyFrame() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 600);
		setResizable(false);

		for (int i = 0; i < 2; i++) {
			Color cor = color[random.nextInt(5)];
			ml[i] = new MyLabel(100*i, 100*i, p, cor);
			add(ml[i]);
		}
		new colission().start();
	}

	public class colission extends Thread {
		public void run() {

			while (true) {
				if (ml[0].testIntersection(ml[1].oval, ml[1].oval)) {
					System.out.println("colidiu");
				}
				try {
					sleep(10);

				} catch (Exception erro) {

				}
			}
		}
	}

}
