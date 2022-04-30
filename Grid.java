import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Grid extends Canvas{
    //composite information about active piece and grid state.
    private static final Color[] colors = new Color[]{Color.WHITE, Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.PINK };
    private int[][] state = new int[14][14];
    private Piece active;
    public Grid(){
	setSize(500, 500);
	active = null;
    }
    public void paint(Graphics g){
	if(active == null) spawn();
	if(checkState()){
	    fillPiece();
	}
	for(int i = 0; i < state.length; i++){
	    for(int j = 0; j < state[0].length; j++){
		if(i == 0 || j == 0 || i == state.length -1 || j == state[0].length-1){
		    g.setColor(Color.BLACK);
		}else{
		    g.setColor(colors[state[i][j]]);
		}
		g.fillRect(i*30, j*30, 30, 30);
	    }
	}
    }
    private boolean checkState(){
	for(int i = 0; i < active.getRaw().length; i++){
	    for(int j = 0; j < active.getRaw()[0].length; j++){
		if(active.getRaw()[i][j] != 0 && state[active.getX() + j][active.getY() + i] != 0){
		    return false;
		}
	    }
	}
	return true;
    }
    private void fillPiece(){
	for(int i = 0; i < active.getRaw().length; i++){
	    for(int j = 0; j < active.getRaw()[0].length; j++){
		state[active.getX() + j][active.getY() + i] = active.getRaw()[i][j];
	    }
	}
    }
    public void drop(){
	active.drop();
    }
    public void spawn(){
	active = new T(1);
    }
}
