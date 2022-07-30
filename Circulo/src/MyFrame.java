import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class MyFrame extends JFrame {
	
	public static final int largura=800;
	public static final int altura =600;
	BSP bsp = new BSP(0,0,largura,altura,colorRandom());
	static Random random = new Random();
	static Color color[] = { Color.PINK, Color.blue, Color.red, Color.yellow, Color.orange };
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	MyFrame() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, largura+100, altura+100);
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		int tamanho=6;
		for (int i = 0; i < tamanho; i++) {
			Color cor = colorRandom();
			Boid b= new Boid(getRandomNumber(0,largura), getRandomNumber(0,altura),  cor);
			//add(b);
			bsp.Add(b);
		}
		add(bsp);
		new Mover().start();

	}
	public int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
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
