import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.SwingUtilities;
public class Main implements Runnable{
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    public void run(){
    	JFrame frame = new JFrame("My Spring Final Project");
	JPanel panel = new JPanel();
	JPanel holders = new JPanel();
	holders.setLayout(new BoxLayout(holders, BoxLayout.Y_AXIS));
	JPanel info = new JPanel();
	info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS)); 
	Holder hold = new Holder();
	Holder next = new Holder();
	PlayField playField = new PlayField(next);
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
			playField.softDrop();
		    }else if(e.getKeyChar() == ' '){
			playField.hardDrop();
		    }else if(e.getKeyChar() == 'c'){
			Piece temp = playField.getPiece();
			playField.takePiece(hold.getPiece());
			hold.takePiece(temp);
		    }
		}
	    });
	panel.add(playField);
	DisplayBox score = new DisplayBox("score", playField.getScore());
	DisplayBox lines = new DisplayBox("lines", playField.getLines());
	DisplayBox level = new DisplayBox("level", playField.getLevel());
	info.add(score);
	info.add(level);
	info.add(lines);
	panel.add(info);
	holders.add(bigger(new JLabel("HOLD")));
	holders.add(hold);
	holders.add(bigger(new JLabel("NEXT")));
	holders.add(next);
	panel.add(holders);
	frame.add(panel);
	frame.pack();
	new Timer(100, new ActionListener(){
		private int state = 0;
		public void actionPerformed(ActionEvent e){
		    if(++state == 10){
			playField.repaint();
			hold.repaint();
			next.repaint();
			score.update(playField.getScore());
			lines.update(playField.getLines());
			level.update(playField.getLevel());
			state = 0;
		    }else if (state == 9){
			playField.drop();
		    }
		    frame.pack();
		}
	    }).start();
	    frame.setVisible(true);
    }
    public static void main(String[] args){
	Main main = new Main();
	SwingUtilities.invokeLater(main);
    }
    private JLabel bigger(JLabel label){
	label.setFont(new Font("Serif", Font.BOLD, 20));
	return label;
    }
}
