import java.awt.*;
import java.util.Random;
import javax.swing.*;

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

		for (int i = 0; i < ml.length; i++) {
			Color cor = color[random.nextInt(5)];
			ml[i] = new MyLabel(100 * i, 100 * i, p, cor);
			add(ml[i]);
		}

		new Mover().start();

	}

	public class Mover extends Thread {
		public void run() {

			while (true) {

				for (int i = 0; i < ml.length; i++) {
					for (int j = i + 1; j < ml.length; j++) {
						if(ml[i].Colidiu(ml[j])) {
							break;
						}
					}
					ml[i].Update();
				}

				try {
					sleep(10);

				} catch (Exception erro) {

				}

			}
		}

	}
}
