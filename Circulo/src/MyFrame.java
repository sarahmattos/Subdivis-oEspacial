import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class MyFrame extends JFrame {
	BSP bsp = new BSP();
	
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
		int tamanho=5;
		for (int i = 0; i < tamanho; i++) {
			Color cor = color[random.nextInt(5)];
			Boid b= new Boid(100 * i, 100 * i,  cor);
			add(b);
			bsp.Add(b);
		}

		new Mover().start();

	}

	public class Mover extends Thread {
		public void run() {

			while (true) {

				bsp.update();
				try {
					sleep(10);

				} catch (Exception erro) {

				}

			}
		}

	}
}
