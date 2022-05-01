import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.SwingUtilities;
public class Main implements Runnable{
    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;
    public void run(){
    	JFrame frame = new JFrame("My Spring Final Project");
	JPanel panel = new JPanel();
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
		    }else if(e.getKeyChar() == ' '){
			g.hardDrop();
		    }
		}
	    });
	panel.add(g);
	DisplayBox score = new DisplayBox("score", g.getScore());
	panel.add(score);
	frame.add(panel);
	frame.pack();
	frame.setVisible(true);
	new Timer(100, new ActionListener(){
		private int state = 0;
		public void actionPerformed(ActionEvent e){
		    if(++state == 10){
			g.repaint();
			score.update(g.getScore());
			state = 0;
		    }else if (state == 9){
			g.drop();
		    }
		}
	    }).start();
    }
    public static void main(String[] args){
	Main main = new Main();
	SwingUtilities.invokeLater(main);
    }
}
