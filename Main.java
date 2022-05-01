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
	PlayField playField = new PlayField();
	frame.setSize(WIDTH, HEIGHT);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	playField.addKeyListener(new KeyListener(){
		public void keyPressed(KeyEvent e){}
		public void keyReleased(KeyEvent e){}
		public void keyTyped(KeyEvent e){
		    if(e.getKeyChar() == 'e'){
			playField.rotate(1);
		    }else if(e.getKeyChar() == 'q'){
			playField.rotate(3);
		    }else if(e.getKeyChar() == 'a'){
			playField.shift(-1);
		    }else if(e.getKeyChar() == 'd'){
			playField.shift(1);
		    }else if(e.getKeyChar() == 's'){
			playField.drop();
		    }else if(e.getKeyChar() == ' '){
			playField.hardDrop();
		    }
		}
	    });
	panel.add(playField);
	DisplayBox score = new DisplayBox("score", playField.getScore());
	Holder hold = new Holder();
	panel.add(hold);
	panel.add(score);
	frame.add(panel);
	frame.pack();
	frame.setVisible(true);
	new Timer(100, new ActionListener(){
		private int state = 0;
		public void actionPerformed(ActionEvent e){
		    if(++state == 10){
			playField.repaint();
			hold.repaint();
			score.update(playField.getScore());
			state = 0;
		    }else if (state == 9){
			playField.drop();
		    }
		}
	    }).start();
    }
    public static void main(String[] args){
	Main main = new Main();
	SwingUtilities.invokeLater(main);
    }
}
