import javax.swing.*;
import java.awt.event.*;
import javax.swing.SwingUtilities;
public class Main implements Runnable{
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    public static final double FPS = 60.0;
    private static Clock clock = new Clock(); 
    private static Log log = new Log(clock);
    //Every single object must hold a reference to the same clock and log
    //this is done by 
    public void run(){
	System.setProperty("sun.java2d.opengl", "true"); //enable GPU acceleration
    	JFrame frame = new JFrame("A Totally Not Copyrighted Game");
	JPanel panel = new JPanel(); //Outermost component in the heirarchy;
	Sidebar sidebar = new Sidebar(log);
	JPanel info = new JPanel();
	info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS)); //Change layout to vertical grid
	PlayField playField = new PlayField(sidebar.getNext(), clock, log);
	EventQueue eventQueue = new EventQueue();
	frame.setSize(WIDTH, HEIGHT);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	playField.addKeyListener(new KeyListener(){
		public void keyPressed(KeyEvent e){}
		public void keyReleased(KeyEvent e){}
		public void keyTyped(KeyEvent e){
		    eventQueue.push(e);
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
	panel.add(sidebar);
	frame.add(panel);
	new Timer((int) (1000/FPS), new ActionListener(){
		public void actionPerformed(ActionEvent event){
		    clock.update(1.0/FPS);
		    sidebar.repaint();
		    score.update(playField.getScore());
		    lines.update(playField.getLines());
		    level.update(playField.getLevel());
		    info.repaint();
		    playField.drop();
		    playField.setVisible(!playField.getDone()); 
		    sidebar.setVisible(!playField.getDone()); //if game over, just show the score box
		    playField.repaint();
		    frame.pack();
		    KeyEvent e = eventQueue.pop();
		    if(e == null) return;
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
			if(playField.holdable()){
			    playField.setHoldable();
			    Piece temp = playField.getPiece();
			    playField.takePiece(sidebar.getHold().getPiece());
			    sidebar.getHold().takePiece(temp);
			}
		    }	
		}
	    }).start();
	frame.setVisible(true);
    }
    public static void main(String[] args){
	Main main = new Main();
	SwingUtilities.invokeLater(main);
    }
}
