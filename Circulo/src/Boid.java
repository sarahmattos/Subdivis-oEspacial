import java.awt.Graphics;
import java.util.Random;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.geom.Rectangle2D;

public class Boid  {

	public Color color;
	int x, y, a, b;
	int auxX = 1;
	int auxY = 1;
	float speed = 0;
	//metodo construtor
	Boid(int _x, int _y,Color _color) {
		this.a = 20;
		this.b = 20;
		this.x = _x;
		this.y = _y;
		this.color = _color;
		aleatorizar();

	}
	//função para aleatorizar a velocidade e direção inicial
	public void aleatorizar() {

		Random random = new Random();
		while (speed < 2 || speed > 8) {
			speed = random.nextFloat() * 10;
		}
		auxX = random.nextInt(2);
		if (auxX == 0) {
			auxX = -1;
		}
		auxY = random.nextInt(2);
		if (auxY == 0) {
			auxY = -1;
		}
		//no final deixei a speed fixa para debugar melhor os testes
		speed=0.5f;
	}

	//funçao desenhar
	public void draw(Graphics2D g, Color c) {
		g.setColor(c);
		g.fillOval(x, y, a, b);
	}

	//invertendo direção em X
	public void contrarioX() {
		auxX *= -1;
	}
	//invertendo direção em Y
	public void contrarioY() {
		auxY *= -1;
	}
	//update
	public void Update() {
		//movimento
		x += 10f * auxX;
		y += 10f *auxY ;

		//colissão com o mundo
		if (x > MyFrame.largura - a - 15) {
			
			auxX=-1;
		}else if( x < a+10) {
			auxX=1;
		}
		if (y > MyFrame.altura - b - 40  ) {
			auxY=-1;
		}else if(y < b+10) {
			auxY=1;
		}

	}
	//testando colisão
	public boolean TesteColission(Boid ml3)
	{
		double x;
        double y;


        x = this.x - ml3.x;
        y = this.y - ml3.y;

        if (Math.sqrt((x*x) + (y*y)) < this.a) {
            return true;
        } else {
            return false;
        }
	}
		//gerenciando o que fazer quando colidir
		public boolean Colidiu(Boid ml2) {
	
		if(TesteColission(ml2)) {
			
			
			contrarioX();
			contrarioY();
			ml2.color=Color.black;
			ml2.contrarioX();
			ml2.contrarioY();
			 return true;
		}
		 return false;
	}
		//gets de algumas variaveis
		public int getPositionX() {
			return x;
		}

		public int getPositionY() {
			return y;
		}

		public int getAltura() {
			return a;
		}

		public int getLargura() {
			return b;
		}
}
