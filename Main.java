import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Main{
    public static final int WIDTH = 500;
    public static final int HEIGHT = 400;
    public static void main(String[] args){
	JFrame frame = new JFrame("My Spring Final Project");
	Grid g = new Grid();
	frame.setSize(WIDTH, HEIGHT);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.add(g);
	frame.pack();
	frame.setVisible(true);
	new Timer(1000, new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    g.drop();
		    g.repaint();
		}
	    }).start();
    }
}
