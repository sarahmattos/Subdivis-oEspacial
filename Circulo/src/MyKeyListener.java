import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class MyKeyListener implements KeyListener {
	public long time =100;
	public boolean pause=false;
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        	//pause ou diinuição de frames por segundo
        	if(e.getKeyCode()==49) {
        		if(time==100) {
        			time=1000;
        		}else {
        			time=100;
        		}
        	}
        	if(e.getKeyCode()==50) {
        		pause=!pause;
        	}
        }
    }