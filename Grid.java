import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Grid extends Canvas{
    //composite information about active piece and grid state.
    private static final Color[] colors = {Color.WHITE, Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.PINK, Color.BLACK};
    private int[][] fill;
    private int[][] border;
    private Piece active;
    private Piece ghost;
    public Grid(){
	setSize(500, 500);
	active = null;
	ghost = null;
	fill = new int[14][24];
	border = new int[14][24];
	for(int i = 0; i < fill.length; i++){
	    for(int j = 0; j < fill[0].length; j++){
		if(i == 0 || j == 0 || i == fill.length -1 || j == fill[0].length-1){
		    fill[i][j] = 8;
		}
		border[i][j] = 0;
	    }
	}
    }
    public void paint(Graphics g){
	//main execution path for this class;
	//called as a result of repaint() in Main;
	Graphics2D g2 = (Graphics2D) g;
	//g2.setStroke(new BasicStroke());
	if(active == null) spawn();
	 makeGhost();
	 outlinePiece(ghost);
	 fillPiece(active);
	for(int i = 0; i < fill.length; i++){
	    for(int j = 0; j < fill[0].length; j++){
		Shape box = new Rectangle(i*30+1, j*30+1, 28, 28);
		g2.setPaint(colors[fill[i][j]]);
		g2.fill(box);
		g2.setPaint(colors[border[i][j]].brighter());
		g2.draw(box);
	    }
	}
	removePiece(active);
	removePiece(ghost);
    }
    private boolean checkState(Piece piece){
	//returns true if piece is in a valid position;
	for(Pixel p : piece){
	    if(fill[p.X][p.Y] != 0 && p.STATE != 0) return false;
	}
	return true;
    }
    private void fillPiece(Piece piece){
	for(Pixel p : piece){
	    if(p.STATE != 0) fill[p.X][p.Y] = p.STATE;
	}
    }
    private void outlinePiece(Piece piece){
	for(Pixel p : piece){
	    if(p.STATE != 0) border[p.X][p.Y] = p.STATE;
	}
    }
    private void removePiece(Piece piece){
	for(Pixel p: piece){
	    if(p.STATE != 0){
		fill[p.X][p.Y] = 0;
		border[p.X][p.Y] = 0;
	    }
	}
    }
    private void makeGhost(){
	ghost = active.clone();
	while(checkState(ghost)){
	    ghost.drop();
	}
	ghost.undrop();
    }
    public void rotate(int direction){
	Pair[] offsets = active.generateOffsets(direction);
	active.rotate(direction);
	for(Pair offset : offsets){
	    active.translate(offset);
	    if(checkState(active)) return;
	    active.translate(offset.inverse());
	}
	//if no rotation succeeded, return rotation state to original;
	active.rotate(4-direction);
    }
    public void shift(int x){
	Pair offset = new Pair(x, 0);
	active.translate(offset);
	if(checkState(active)) return;
	active.translate(offset.inverse());
    }
    public void drop(){
	active.drop();
	if(!checkState(active)){
	    active.undrop();
	    fillPiece(active);
	    spawn();
	}
    }
    public void spawn(){
	active = new T();
    }
}
