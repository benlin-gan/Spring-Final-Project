import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Main{
    public static final int WIDTH = 500;
    public static final int HEIGHT = 800;
    public static void main(String[] args){
	JFrame frame = new JFrame("My Spring Final Project");
	Grid g = new Grid();
	frame.setSize(WIDTH, HEIGHT);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	g.addKeyListener(new KeyListener(){
		public void keyPressed(KeyEvent e){}
		public void keyReleased(KeyEvent e){}
		public void keyTyped(KeyEvent e){
		    if(e.getKeyChar() == 'e'){
			g.rotate(1);
		    }else if(e.getKeyChar() == 'q'){
			g.rotate(3);
		    }else if(e.getKeyChar() == 'a'){
			g.shift(-1);
		    }else if(e.getKeyChar() == 'd'){
			g.shift(1);
		    }else if(e.getKeyChar() == 's'){
			g.drop();
		    }
		}
	    });
	frame.add(g);
	frame.pack();
	frame.setVisible(true);
	new Timer(50, new ActionListener(){
		private int state = 0;
		public void actionPerformed(ActionEvent e){
		    if(++state == 10){
			g.repaint();
			state = 0;
		    }else if (state == 9){
			g.drop();
		    }
		}
	    }).start();

    }
}
