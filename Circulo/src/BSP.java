import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JLabel;

public class BSP extends JLabel {
	int filhoId;
	int max = 5;
	BSP pai;
	ArrayList<Boid> boid = new ArrayList<Boid>();
	int positionX, positionY, tamanho;
	bspCut cut;
	Color color;
	BSP folha1, folha2;
	int media;
	boolean foiDiv = false;

	public BSP(Color color, BSP _pai, int _filhoId) {

		this.color = color;
		cut = new bspCut();
		this.pai = _pai;
		this.filhoId = _filhoId;
	}

	public void Add(Boid b) {
		boid.add(b);
	}

	public void cut() {

		foiDiv = boid.size() > max;
		if (foiDiv) {
			bspCutResult result = cut.getResult(boid);
			for (int i = 0; i < result.bFolha1.size(); i++) {
				result.bFolha1.get(i).color = Color.CYAN;
			}
			for (int i = 0; i < result.bFolha2.size(); i++) {
				result.bFolha2.get(i).color = Color.GRAY;
			}
			if (result.count % 2 == 0) {
				positionX = 0;
				positionY = result.media;
				
				folha1 = new BSP(MyFrame.colorRandom(), this,1);
				folha2 = new BSP(MyFrame.colorRandom(), this,2);
			} else {
				
				positionX = result.media;
				positionY = 0;
				
				folha1 = new BSP(MyFrame.colorRandom(), this,1);
				folha2 = new BSP(MyFrame.colorRandom(), this,2);
			}
			media=result.media;
			folha1.boid = result.bFolha1;
			folha1.cut.count = cut.count + 1;
			folha2.boid = result.bFolha2;
			folha1.cut.count = cut.count + 1;
		}
	}

	public void update() {

		if (!foiDiv) {
			cut();
			for (int i = 0; i < boid.size(); i++) {
				for (int j = i + 1; j < boid.size(); j++) {
					if (boid.get(i).Colidiu(boid.get(j))) {
						break;
					}
				}
				// boid.get(i).Update();
			}
		} else {
			folha1.update();
			folha2.update();
		}
	}

	public void draw(Graphics2D g) {
		if (!foiDiv) {
			// g.setColor(color);
			// g.drawRect(positionX, positionY, largura, altura);

			for (int i = 0; i < boid.size(); i++) {
				boid.get(i).draw(g, color);
			}
		} else {
			if (folha1 != null) {
				folha1.draw(g);
			}
			if (folha2 != null) {
				folha2.draw(g);
			}
			g.setColor(color.black);

			if (positionX == 0) {
				if (pai == null) {
					tamanho = MyFrame.largura;
					
				}else {
					tamanho=pai.media;
				}
				if(filhoId==1) {
					g.drawLine(0, positionY, tamanho, positionY);
				}else {
					g.setColor(Color.orange);
					g.drawLine(tamanho, positionY, MyFrame.largura, positionY);
				}
				
				
			} else if (positionY == 0) {
				if (pai == null) {
					tamanho = MyFrame.altura;
				}else{
					tamanho=pai.media;
				}
				if(filhoId==1) {
					g.drawLine(positionX, 0, positionX, tamanho);
				}else {
					g.setColor(Color.RED);
					g.drawLine(positionX, tamanho, positionX, MyFrame.altura);
				}
				
				
			}
		}

	}

	public void paint(Graphics g) {
		// g.clearRect(0, 0, 800, 600);
		draw((Graphics2D) g);
		repaint(100, 0, 0, 800, 600);

	}

}
