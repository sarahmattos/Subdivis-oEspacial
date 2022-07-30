import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JLabel;

public class BSP {
	ArrayList<Boid> boid = new ArrayList<Boid>();
	int positionX, positionY, altura, largura;
	
	public BSP() {
		altura=200;
		largura=200;
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
		g.setColor(Color.BLACK);
		g.drawRect(positionX, positionY,largura,altura);
		for (int i = 0; i < boid.size(); i++) {
		boid.get(i).draw(g);
		}
		
	}
}
