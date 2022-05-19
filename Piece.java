import java.util.Iterator;
public class Piece implements Iterable<Pixel>{
    //handles the manipulation of piece data
    private double x; 
    private double y; 
    //note x and y are ill defined unless the piece is in a grid
    //x and y are doubles to allow for smoother animation, but only their int versions will ever be reported to the outside world;
    //If you forget this you will get an IndexOutOfBounds Exception.
    private int rotation;
    private Pair[][] offsets;
    private int[][] raw;
    private double lastUpdate;
    public Piece(int[][] raw, Pair[][] offsets){
	//the platonic ideal of a piece; should be preserved as the piece moves between coordinate systems;
	this.raw = raw;
	this.offsets = offsets;
    }
    public Piece(Piece p){
	//A copy constructor
	this.x = p.x;
	this.y = p.y;
	this.raw = p.raw;
	this.offsets = p.offsets;
    }
    public Pair[] generateOffsets(int direction){
	//generate offsets necessary for rotation based on intial and final rotation state;
	int begin = rotation;
	int end = (rotation + direction) % 4;
	Pair[] toReturn = new Pair[offsets[0].length];
	for(int i = 0; i < toReturn.length; i++){
	    toReturn[i] = Pair.add(offsets[begin][i], offsets[end][i].inverse());
	}
	return toReturn;
    }
    public void translate(Pair offset){
	//mutator method that translates the piece;
	x += offset.X;
	y += offset.Y;
    }
    public void setLocation(Pair coords){
	//extremely cargo culty use of "encapsulation" here.
	while(rotation != 0){
	    rotate(1);
	}
	x = coords.X;
	y = coords.Y;
    } 
    public void rotate(int direction){
	//mutator for rotation and raw
	rotation = (rotation + direction) % 4;
	int times = direction % 4;
	for(int i = 0; i < times; i++){
	    raw = rotate(raw);
	}
    }
    private static int[][] rotate(int[][] input){
	//arbitrary rotation about the center of a square grid
	int l = input.length;
	int[][] output = new int[l][l];
	for(int row = 0; row < l/2 + 1; row++){
	    for(int col = row; col < l/2 + 1; col++){
		int topLeft  = input[row][col];
		int topRight = input[col][l - row - 1];
		int bottomRight = input[l - row - 1][l - col - 1];
		int bottomLeft = input[l - col - 1][row];
		output[col][l - row - 1] = topLeft;
		output[l - row - 1][l - col - 1] = topRight;
		output[l - col - 1][row] = bottomRight;
		output[row][col] = bottomLeft;
	    }
	}
	return output;
    }
    public PieceIterator iterator(){
	return new PieceIterator(this);
    }
    class PieceIterator implements Iterator<Pixel>{
	//A way to make iterating over a piece in a for-each loop return the grid locations it should cover;
	private Piece p;
	private int row;
	private int col;
	private int reported;
	public PieceIterator(Piece p){
	    this.p = p;
	    row = 0;
	    col = 0;
	    reported = 0;
	}
	public boolean hasNext(){
	    return reported < 4; //all pieces cover four squares;
	}
	private void advance(){
	    col++;
	    if(col == p.raw[0].length){
		col = 0;
		row++;
	    }
	}
	public Pixel next(){
	    //keep searching until you find a filled pixel;
	    while(p.raw[row][col] == 0){
		advance();
	    }
	    reported++;
	    Pixel toReturn = new Pixel(col + (int) p.x, row + (int) p.y, p.raw[row][col]); //return the coordinates of that pixel in its actual space;
	    advance();
	    return toReturn;
	}
    }    
    public void synchronize(double time){
	lastUpdate = time;
    }
    public void drop(double time, double gravity){
	y += (time - lastUpdate) / gravity;
	synchronize(time);
    }
    public boolean locked(double time){
	//a piece is still controllable after it hits the floor for 0.5 seconds;
	return (time - lastUpdate) > 0.5;
    }
}