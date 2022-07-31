import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.*;

public class MyFrame extends JFrame {
	public static int quantBsp = 10;
	int tamanho = 30;
	public boolean useBSP=true;
	
	
	
	public long timeMedia;
	public long countMedia=0;
	
	MyKeyListener mkl = new MyKeyListener();
	public static final int largura = 800;
	public static final int altura = 600;
	public static int colorCount = 0;
	BSP bsp = new BSP(colorRandom(), null, 1);
	static Random random = new Random();
	static Color color[] = { Color.PINK, Color.blue, Color.red, Color.yellow, Color.cyan, Color.green, Color.MAGENTA };
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	MyFrame() {

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, largura + 100, altura + 100);
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);

		for (int i = 0; i < tamanho; i++) {
			Color cor = colorRandom();
			Boid b = new Boid(getRandomNumber(0, largura), getRandomNumber(0, altura), cor);
			// add(b);
			bsp.Add(b);
		}
		add(bsp);
		addKeyListener(mkl);
		bsp.update();
		new Mover().start();

	}

	public static int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	public static Color colorRandom() {
		// return color[random.nextInt(color.length)];
		return color[colorCount++ % color.length];
		// return new
		// Color(getRandomNumber(0,255),getRandomNumber(0,255),getRandomNumber(0,255));
	}

	public class Mover extends Thread {
		public void run() {

			while (true||countMedia<50) {
				long start = System.nanoTime();
				if (!mkl.pause) {
					if(useBSP) {
						bsp.update();
					}else {
						for (int i = 0; i < bsp.boid.size(); i++) {
							for (int j = i + 1; j < bsp.boid.size(); j++) {
								if (bsp.boid.get(i).Colidiu(bsp.boid.get(j))) {
									break;
								}
							}
							 bsp.boid.get(i).Update();
						}
					}
				}
				
				long finish = System.nanoTime();
				long timeElapsed = finish - start;
				timeMedia+=timeElapsed;
				countMedia++;
				timeMedia/=countMedia;
				System.out.println(timeMedia);
				colorCount = 0;
				try {
					sleep(mkl.time);

				} catch (Exception erro) {

				}

			}
		}

	}

}
