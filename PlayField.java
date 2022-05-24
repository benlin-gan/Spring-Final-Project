import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
public class PlayField extends Grid{
    //composite information about the active piece into the grid state 
    private int score;
    private int level;
    private int lines;
    private Piece active;
    private Piece ghost;
    private Holder feeder; 
    private Log log;
    //feeder and log have graphical representations, and so must exist indpendently
    //but their behavior is closely coupled to that of the PlayField, so it needs to hold some their refrences
    //this is why OOP is kind of a cargo cult
    private Clock clock;
    private boolean done;
    private boolean canHold; //can only hold once between piece locks
    private boolean majorCombo;
    private int minorCombo;
    public PlayField(Holder feeder, Clock clock, Log log){
	super(24, 14);
	score = 0;
	level = 1;
	lines = 0;
	this.feeder = feeder;
	this.clock = clock;
	this.log = log;
	canHold = true;
	done = false;
	majorCombo = false;
	minorCombo = -1;
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
	//a copy of the piece translated down to where it hits the ground;
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
	if(checkState(active)) return; //early exit if the piece is in a valid position
	active.translate(offset.inverse()); //return to original position if not
    }
    public void hardDrop(){
	score += 2 * cellsBelow() * level;
	active = ghost; 
	ghost = null;
	fillPiece(active); //the active piece is now just part of the board state
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
	//gravity is in units of cells per frame;
	return Math.pow((0.8 - ((level - 1) * 0.007)), (level - 1));
    }
    public void spawn(){
	takePiece(feeder.getPiece());
	active.setLocation(new Pair(5, 1));
	if(!checkState(active)) done = true;
	active.synchronize(clock.getTime());
	feeder.spawn();
	canHold = true;
    }
    private void clear(){
	int cleared = 0;
	//for loop counts number of lines that are full, ignoring the black boundraries, and clears them.
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
	updateScore(cleared);
	//gravity in tetris is a stable sort where all the empty lines go to the top
	//One of the only times where Java's extra indirection for array types is useful;
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
    }
    private void updateScore(int cleared){
	String message = "";
	int actionScore = 0;
	if(cleared == 1) {
	    actionScore += 100 * level;
	    message += "Single ";
	    majorCombo = false;
	}
	else if(cleared == 2){
	    actionScore += 300 * level;
	    message += "Double ";
	    majorCombo = false;
	} 
	else if(cleared == 3){
	    actionScore += 500 * level;
	    message += "Triple ";
	    majorCombo = false;
	}
	else if(cleared == 4){
	    if(majorCombo){
		actionScore += 1200 * level;
		message += "Back-to-Back Tetris ";
	    }else{
	    	actionScore += 800 * level;
	    	message += "Tetris ";
		majorCombo = true;
	    }
	}
	if(cleared == 0){
	    minorCombo = -1;
	}else{
	    minorCombo++;
	    int bonus = minorCombo * 50;
	    if(bonus > 0){
		message += "Combo (x" + minorCombo + ") ";
		actionScore += bonus * level;
	    }
	}
	if(actionScore > 0) log.setMessage(message + "+" + actionScore);
	lines += cleared;
	levelUp(); //sets the new level based on the total lines cleared
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
	return active; //not ghost
    }
    public void takePiece(Piece piece){
	if(piece == null){ //only happens if this is the first time the user holds a peice
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
    public boolean holdable(){
	return canHold;
    }
    public void setHoldable(){
	canHold = false; //can only be set to false from outside the object. 
    }
}
