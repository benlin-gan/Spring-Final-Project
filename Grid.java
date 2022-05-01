import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Grid extends Canvas{
    //composite information about active piece and grid state.
    private static final Color[] colors = {Color.WHITE, Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.PINK, Color.BLACK};
    private int[][] fill;
    private int[][] border;
    private int score;
    private Piece active;
    private Piece ghost;
    private Bag bag;
    public Grid(){
	setSize(500, 800);
	score = 0;
	active = null;
	ghost = null;
	bag = new Bag();
	fill = new int[24][14];
	border = new int[24][14];
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
		Shape box = new Rectangle(j*30+1, i*30+1, 28, 28);
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
	    if(fill[p.Y][p.X] != 0) return false;
	}
	return true;
    }
    private void fillPiece(Piece piece){
	for(Pixel p : piece){
	    fill[p.Y][p.X] = p.STATE;
	}
    }
    private void outlinePiece(Piece piece){
	for(Pixel p : piece){
	    border[p.Y][p.X] = p.STATE;
	}
    }
    private void removePiece(Piece piece){
	for(Pixel p: piece){
	    fill[p.Y][p.X] = 0;
	    border[p.Y][p.X] = 0;
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
    public void hardDrop(){
	active = ghost;
	ghost = null;
	fillPiece(active);
	spawn();
	clear();	
    }
    public void drop(){
	active.drop();
	if(!checkState(active)){
	    active.undrop();
	    fillPiece(active);
	    clear();
	    spawn();
	}
    }
    public void spawn(){
	active = bag.draw();
    }
    private void clear(){
	int lines = 0;
	for(int i = fill.length - 2; i > 0; i--){
	    int[] line = fill[i];
	    boolean shouldClear = true;
	    for(int j = 1; j < line.length - 1; j++){
		if(line[j] == 0) shouldClear = false;
	    }
	    if(shouldClear){
		lines++;
	    }
	    for(int j = 1; j < line.length - 1; j++){
		int toCopy = i - lines;
		if(toCopy > 0){
		    line[j] = fill[toCopy][j];
		}else{
		    line[j] = 0;
		}
	    }
	}
	if(lines == 1) score += 100;
	else if(lines == 2) score += 300;
	else if(lines == 3) score += 500;
	else if(lines == 4) score += 800;
    }
    public int getScore(){
	return score;
    }
}
