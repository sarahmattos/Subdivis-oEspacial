import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JLabel;

public class BSP extends JLabel {
	int filhoId;
	//int max = 5;
	BSP pai;
	ArrayList<Boid> boid = new ArrayList<Boid>();
	int positionX, positionY, tamanho;
	bspCut cut;
	Color color;
	BSP folha1, folha2;
	int media;
	boolean foiDiv = false;

	//método construtor
	public BSP(Color color, BSP _pai, int _filhoId) {

		this.color = color;
		cut = new bspCut();
		this.pai = _pai;
		this.filhoId = _filhoId;
	}
	//adicionar boid no bsp
	public void Add(Boid b) {
		boid.add(b);
	}

	public void cut() {
		//enquanto o numero de boids for maior que nossa quantidade determinada, as divisões irão acontecer
		foiDiv = boid.size() > MyFrame.quantBsp;
		if (foiDiv) {
			//corte horizontal ou vertical
			bspCutResult result = cut.getResult(boid);
			
			if (result.count % 2 == 0) {
				//horizontal
				positionX = 0;
				positionY = result.media;
				
				folha1 = new BSP(MyFrame.colorRandom(), this,1);
				folha2 = new BSP(MyFrame.colorRandom(), this,2);
			} else {
				//vertical
				positionX = result.media;
				positionY = 0;
				
				folha1 = new BSP(MyFrame.colorRandom(), this,1);
				folha2 = new BSP(MyFrame.colorRandom(), this,2);
			}
			//as folhas recebem a lista de seus boids
			media=result.media;
			folha1.boid = result.bFolha1;
			folha1.cut.count = cut.count + 1;
			folha2.boid = result.bFolha2;
			folha1.cut.count = cut.count + 1;
			if(pai!=null) {
				boid.clear();
			}
			
		}
	}

	public void update() {
		cut();
		if (!foiDiv) {
			//testa colisão
			for (int i = 0; i < boid.size(); i++) {
				for (int j = i + 1; j < boid.size(); j++) {
					if (boid.get(i).Colidiu(boid.get(j))) {
						break;
					}
				}
				//movimento
				 boid.get(i).Update();
			}
			
		} else {
			//chama o update das folhas
			folha1.update();
			folha2.update();
		}
	}

	public void draw(Graphics2D g) {
		if (!foiDiv) {
			//desenha
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
				//primeira linha horizontal
				if (pai == null||pai.pai==null) {
					tamanho = MyFrame.largura;
					g.drawLine(0, positionY, tamanho, positionY);
				}
				//desenha linha dos filhos descontinuada
				/*else {
					tamanho=pai.media;
				}
				if(filhoId==1) {
					g.drawLine(0, positionY, tamanho, positionY);
				}else {
					g.setColor(Color.orange);
					g.drawLine(tamanho, positionY, MyFrame.largura, positionY);
				}
				*/
				
			} else if (positionY == 0) {
				if (pai == null) {
					//primeira linha vertical
					tamanho = MyFrame.altura;
					g.drawLine(positionX, 0, positionX, tamanho);
				}
				/*else{
					tamanho=pai.media;
				}
				if(filhoId==1) {
					g.drawLine(positionX, 0, positionX, tamanho);
				}else {
					g.setColor(Color.RED);
					g.drawLine(positionX, tamanho, positionX, MyFrame.altura);
				}
				*/
				
			}
		}

	}

	public void paint(Graphics g) {
		draw((Graphics2D) g);
		repaint(100, 0, 0, MyFrame.largura+100, MyFrame.altura+100);

	}

}
