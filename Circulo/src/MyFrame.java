import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
public class MyFrame extends JFrame{
	MyLabel[] ml= new MyLabel[10];
	Point p = new Point(0,1);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	MyFrame(){
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 600);
		setResizable(false);
		
		for (int i=0;i<5;i++) {
			ml[i] = new MyLabel(100*i,100*i, p);
			add(ml[i]);
		}
		
		
		
	}
	
	
}
