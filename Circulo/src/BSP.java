import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JLabel;

public class BSP extends JLabel {
	ArrayList<Boid> boid = new ArrayList<Boid>();
	int positionX, positionY, altura, largura;
	Color color;
	
	public BSP(int positionX, int positionY, int altura, int largura, Color color) {
		
		this.positionX = positionX;
		this.positionY = positionY;
		this.altura = altura;
		this.largura = largura;
		this.color = color;
	}

	public void Add(Boid b) {
		boid.add(b);
	}
	public void update() {
		for (int i = 0; i < boid.size(); i++) {
			for (int j = i + 1; j < boid.size(); j++) {
				if(boid.get(i).Colidiu(boid.get(j))) {
					break;
				}
			}
			boid.get(i).Update();
		}

	}
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.drawRect(positionX, positionY,largura,altura);
		for (int i = 0; i < boid.size(); i++) {
		boid.get(i).draw(g);
		}
		
	}
	public void paint(Graphics g) {
		//g.clearRect(0, 0, 800, 600);
		draw((Graphics2D)g);
		repaint(100, 0, 0, 800, 600);
		
		System.out.print("entrou");
	}
}
