import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Arrays;
import java.util.Comparator;
public class PlayField extends Grid{
    //composite information about active piece and grid state.
    private int score;
    private Piece active;
    private Piece ghost;
    private Bag bag;
    public PlayField(){
	super(24, 14);
	score = 0;
	active = null;
	ghost = null;
	bag = new Bag();

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
	super.paint(g);
	removePiece(active);
	removePiece(ghost);
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
		for(int j = 1; j < line.length - 1; j++){
		    line[j] = 0;
		}
	    }
	}
	Arrays.sort(fill, 1, fill.length - 1, new Comparator<int[]>(){
		public int compare(int[] a, int[] b){
		    if(isEmpty(a) == isEmpty(b)) return 0;
		    if(isEmpty(a) && !isEmpty(b)) return -1;
		    return 1;
		}
		private boolean isEmpty(int[] a){
		    for(int i = 1; i < a.length - 1; i++){
			if(a[i] != 0) return false;
		    }
		    return true;
		}
	    });
	if(lines == 1) score += 100;
	else if(lines == 2) score += 300;
	else if(lines == 3) score += 500;
	else if(lines == 4) score += 800;
    }
    public int getScore(){
	return score;
    }
    protected Piece getPiece(){
	return active;
    }
    public void takePiece(Piece piece){
	active = piece.cleaned(5);
    }
}