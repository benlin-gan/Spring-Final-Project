import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
public class PlayField extends Grid{
    //composite information about active piece and grid state.
    private int score;
    private int level;
    private int lines;
    private Piece active;
    private Piece ghost;
    private Holder feeder;
    private Log log;
    private Clock clock;
    private boolean done;
    public PlayField(Holder feeder, Clock clock, Log log){
	super(24, 14);
	score = 0;
	level = 1;
	lines = 0;
	this.feeder = feeder;
	this.clock = clock;
	this.log = log;
	done = false;
	spawn();
	ghost = null;

    }
    public void paint(Graphics g){
	//main execution path for this class;
	//called as a result of repaint() in Main;
	Graphics2D g2 = (Graphics2D) g;
	makeGhost();
	outlinePiece(ghost);
	fillPiece(active);
	super.paint(g2);
	removePiece(active);
	removePiece(ghost);
    }
    private void makeGhost(){
	ghost = new Piece(active);
	ghost.translate(new Pair(0, cellsBelow()));
    }
    private int cellsBelow(){
	int out = 0;
	while(checkState(active, out)){
	    out++;
	}
	return out - 1;
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
	score += 2 * cellsBelow() * level;
	active = ghost;
	ghost = null;
	fillPiece(active);
	spawn();
	clear();	
    }
    public void softDrop(){
	if(cellsBelow() > 0){
	    score += level;
	    active.translate(new Pair(0, 1));
	}
    }
    public void drop(){
	if(cellsBelow() == 0){
	    if(active.locked(clock.getTime())){
		fillPiece(active);
		spawn();
		clear();
	    }
	}else{
	    active.drop(clock.getTime(), getGravity());
	}
    }
    private double getGravity(){
	return Math.pow((0.8 - ((level - 1) * 0.007)), (level - 1));
    }
    public void spawn(){
	takePiece(feeder.getPiece());
	active.setLocation(new Pair(5, 1));
	if(!checkState(active)) done = true;
	active.synchronize(clock.getTime());
	feeder.spawn();
    }
    private void clear(){
	int cleared = 0;
	for(int i = fill.length - 2; i > 0; i--){
	    int[] line = fill[i];
	    boolean shouldClear = true;
	    for(int j = 1; j < line.length - 1; j++){
		if(line[j] == 0) shouldClear = false;
	    }
	    if(shouldClear){
		cleared++;
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
	if(cleared == 1) {
		score += 100 * level;
		log.setMessage("Single!");
	}
	else if(cleared == 2) score += 300 * level;
	else if(cleared == 3) score += 500 * level;
	else if(cleared == 4) score += 800 * level;
	lines += cleared;
	levelUp();
    }
    private void levelUp(){
	level = lines / 10 + 1;
    }
    public int getScore(){
	return score;
    }
    public int getLines(){
	return lines;
    }
    public int getLevel(){
	return level;
    }
    protected Piece getPiece(){
	return active;
    }
    public void takePiece(Piece piece){
	if(piece == null){
		spawn();
	}else{
		active = piece;
		active.setLocation(new Pair(5, 1));
	}
	active.synchronize(clock.getTime());
    }
    public boolean getDone(){
	    return done;
    }
}
