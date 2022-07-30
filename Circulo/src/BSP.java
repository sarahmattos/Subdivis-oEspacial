import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JLabel;

public class BSP extends JLabel {
	ArrayList<Boid> boid = new ArrayList<Boid>();
	int positionX, positionY, altura, largura;
	bspCut cut;
	Color color;
	BSP folha1, folha2;
	boolean foiDiv = false;

	public BSP(int positionX, int positionY, int largura,  int altura, Color color) {

		this.positionX = positionX;
		this.positionY = positionY;
		this.altura = altura;
		this.largura = largura;
		this.color = color;
		cut = new bspCut();
	}

	public void Add(Boid b) {
		boid.add(b);
	}

	public void cut() {
		foiDiv=boid.size()>2;
		if(foiDiv) {
			 bspCutResult result = cut.getResult(boid);
			 for(int i=0; i< result.bFolha1.size(); i++) {
				 result.bFolha1.get(i).color =Color.CYAN;
			 }
			 for(int i=0; i< result.bFolha2.size(); i++) {
				 result.bFolha2.get(i).color =Color.GRAY;
			 }
			 int largura1,largura2, altura1, altura2;
			 if(result.count%2==0) {
				 largura1= largura;
				 largura2 =largura;
				 altura1= altura-(MyFrame.altura - result.media);
				 altura2 = altura- altura1;
						 
				 folha1 = new BSP(positionX, positionY,largura1, altura1,color);
				 folha2 = new BSP(positionX, altura1 ,largura2, altura2,color);
			 }
			 
		}
	}

	public void update() {

		cut();

		if (!foiDiv) {
			for (int i = 0; i < boid.size(); i++) {
				for (int j = i + 1; j < boid.size(); j++) {
					if (boid.get(i).Colidiu(boid.get(j))) {
						break;
					}
				}
				// boid.get(i).Update();
			}
		}else {
			folha1.update();
			folha2.update();
		}
	}

	public void draw(Graphics2D g) {
		if (!foiDiv) {
			g.setColor(color);
			g.drawRect(positionX, positionY, largura, altura);
		}else {
			folha1.draw(g);
			folha2.draw(g);
		}
		for (int i = 0; i < boid.size(); i++) {
			boid.get(i).draw(g);
		}

	}

	public void paint(Graphics g) {
		// g.clearRect(0, 0, 800, 600);
		draw((Graphics2D) g);
		repaint(100, 0, 0, 800, 600);

	}

}
