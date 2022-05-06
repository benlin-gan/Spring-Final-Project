import java.util.Iterator;
public class Piece implements Iterable<Pixel>{
    private double x;
    private double y;
    private int rotation;
    private Pair[][] offsets;
    private int[][] raw;
    private double lastUpdate;
    public Piece(int[][] raw, Pair[][] offsets){
	this.raw = raw;
	this.offsets = offsets;
    }
    public Piece(Piece p){
	this.x = p.x;
	this.y = p.y;
	this.raw = p.raw;
	this.offsets = p.offsets;
    }
    public Pair[] generateOffsets(int direction){
	int begin = rotation;
	int end = (rotation + direction) % 4;
	Pair[] toReturn = new Pair[offsets[0].length];
	for(int i = 0; i < toReturn.length; i++){
	    toReturn[i] = Pair.add(offsets[begin][i], offsets[end][i].inverse());
	}
	return toReturn;
    }
    public void translate(Pair offset){
	x += offset.X;
	y += offset.Y;
    }
    public void setLocation(Pair coords){
	//extremely cargo culty use of "encapsulation" here.
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
    public static int[][] rotate(int[][] input){
	//rotation about the center of a square grid
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
	    return reported < 4;
	}
	private void advance(){
	    col++;
	    if(col == p.raw[0].length){
		col = 0;
		row++;
	    }
	}
	public Pixel next(){
	    while(p.raw[row][col] == 0){
		advance();
	    }
	    reported++;
	    Pixel toReturn = new Pixel(col + (int) p.x, row + (int) p.y, p.raw[row][col]);
	    advance();
	    return toReturn;
	}
    }    
    public int[][] getRaw(){
	return raw;
    }
    public void synchronize(double time){
	lastUpdate = time;
    }
    public void drop(double time, double gravity){
	y += (time - lastUpdate) / gravity / 10.0;
	System.out.println(y);
	synchronize(time);
	//time of physics to simulate divided by cells per frame divided by frames per second;
    }
}
