import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.*;

public class MyFrame extends JFrame {
	
	//máximo de particula por folha
	public static int quantBsp = 10;
	//quantidade total de particula
	int tamanho = 30;
	//ativar ou desativar o uso da BSP
	public boolean useBSP=true;
	
	
	//auxiliares para medir o tempo de execução
	public long timeMedia;
	public long countMedia=0;
	
	//auxiliar para debugar (tecla "1" para diminuir a quantidade de frame por segundo- tecla "2" para pausar a execução)
	MyKeyListener mkl = new MyKeyListener();
	
	//definição da nossa frame
	public static final int largura = 800;
	public static final int altura = 600;
	public static int colorCount = 0;
	//instancia do bsp
	BSP bsp = new BSP(colorRandom(), null, 1);
	//uso do random para aleatorizar alguns valores
	static Random random = new Random();
	//cores para serel sorteadas
	static Color color[] = { Color.PINK, Color.blue, Color.red, Color.yellow, Color.cyan, Color.green, Color.MAGENTA };
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	MyFrame() {
		//configurações do frame
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, largura + 100, altura + 100);
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		
		//criando os boids (particulas)
		for (int i = 0; i < tamanho; i++) {
			Color cor = colorRandom();
			Boid b = new Boid(getRandomNumber(0, largura), getRandomNumber(0, altura), cor);
			// add(b);
			bsp.Add(b);
		}
		//adicionando nosso bsp e o keyListaner ao frame
		add(bsp);
		addKeyListener(mkl);
		bsp.update();
		//iniciando nossa thread de movimento
		new Mover().start();

	}
	//funcao de sortaer numeros
	public static int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
	//funçao de sortear cores
	public static Color colorRandom() {
		return color[colorCount++ % color.length];
	}
	//thread de movimento
	public class Mover extends Thread {
		public void run() {

			while (true||countMedia<50) {
				long start = System.nanoTime();
				if (!mkl.pause) {
					if(useBSP) {
						//uso de bsp
						bsp.update();
					}else {
						//sem uso de bsp
						for (int i = 0; i < bsp.boid.size(); i++) {
							for (int j = i + 1; j < bsp.boid.size(); j++) {
								//testando colisão geral
								if (bsp.boid.get(i).Colidiu(bsp.boid.get(j))) {
									break;
								}
							}
							//chamando o update dos boids(movimento)
							 bsp.boid.get(i).Update();
						}
					}
				}
				//tempo de execução
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
