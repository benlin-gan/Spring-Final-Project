import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Grid extends Canvas{
    //composite information about active piece and grid state.
    private static final Color[] colors = new Color[]{Color.WHITE, Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.PINK, Color.BLACK };
    private int[][] state;
    private Piece active;
    public Grid(){
	setSize(500, 500);
	active = null;
	state = new int[14][24];
	for(int i = 0; i < state.length; i++){
	    for(int j = 0; j < state[0].length; j++){
		if(i == 0 || j == 0 || i == state.length -1 || j == state[0].length-1){
		    state[i][j] = 8;
		}
	    }
	}
    }
    public void paint(Graphics g){
	if(active == null) spawn();
	if(checkState()){
	    fillPiece();
	}
	for(int i = 0; i < state.length; i++){
	    for(int j = 0; j < state[0].length; j++){
		g.setColor(colors[state[i][j]]);
		g.fillRect(i*30, j*30, 30, 30);
	    }
	}
	removePiece();
    }
    private boolean checkState(){
	//returns true if active piece is in a valid position;
	for(Pixel p : active){
	    if(state[p.X][p.Y] != 0 && p.STATE != 0) return false;
	}
	return true;
    }
    private void fillPiece(){
	for(Pixel p : active){
	    if(p.STATE != 0) state[p.X][p.Y] = p.STATE;
	}
    }
    private void removePiece(){
	for(Pixel p: active){
	    if(p.STATE != 0) state[p.X][p.Y] = 0;
	}
    }
    public void rotate(int direction){
	Pair[] offsets = active.generateOffsets(direction);
	active.rotate(direction);
	for(Pair offset : offsets){
	    active.translate(offset);
	    if(checkState()) return;
	    
	    active.translate(offset.inverse());
	}
	//if no rotation succeeded, return rotation state to original;
	active.rotate(-direction);
    }
    public void shift(int x){
	Pair offset = new Pair(x, 0);
	active.translate(offset);
	if(checkState()) return;
	active.translate(offset.inverse());
    }
    public void drop(){
	active.drop();
	if(!checkState()){
	    active.undrop();
	    fillPiece();
	    spawn();
	}
    }
    public void spawn(){
	active = new T();
    }
}
