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
	boolean divido=false;
	
	public BSP(int positionX, int positionY, int altura, int largura, Color color) {
		
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
	public void update() {
		for (int i = 0; i < boid.size(); i++) {
			for (int j = i + 1; j < boid.size(); j++) {
				if(boid.get(i).Colidiu(boid.get(j))) {
					break;
				}
			}
			//boid.get(i).Update();
		}
		if(boid.size()>2) {
			 bspCutResult result = cut.getResult(boid);
			 for(int i=0; i< result.bFolha1.size(); i++) {
				 result.bFolha1.get(i).color =Color.CYAN;
			 }
			 for(int i=0; i< result.bFolha2.size(); i++) {
				 result.bFolha2.get(i).color =Color.GRAY;
			 }

			 //System.out.println(result.bFolha1.size()+"."+result.bFolha2.size());
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
		
	}
	
}
