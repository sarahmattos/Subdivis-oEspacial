import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class MyFrame extends JFrame {
	
	public static final int largura=800;
	public static final int altura =600;
	BSP bsp = new BSP(0,0,altura,largura,colorRandom());
	static Random random = new Random();
	static Color color[] = { Color.PINK, Color.blue, Color.red, Color.yellow, Color.orange };
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	MyFrame() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, largura, altura);
		setResizable(false);
		getContentPane().setBackground(Color.black);
		int tamanho=5;
		for (int i = 0; i < tamanho; i++) {
			Color cor = colorRandom();
			Boid b= new Boid(100 * i, 100 * i,  cor);
			//add(b);
			bsp.Add(b);
		}
		add(bsp);
		new Mover().start();

	}
	public static Color colorRandom() {
		return color[random.nextInt(5)];
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
