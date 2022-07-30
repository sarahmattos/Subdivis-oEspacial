import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JLabel;

public class BSP extends JLabel {
	ArrayList<Boid> boid = new ArrayList<Boid>();
	int positionX, positionY, altura, largura;
	
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.fillRect(positionX, positionY,  largura,altura);
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
}
